package com.secondhand.module.order.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secondhand.module.mime.entity.UserBuy;
import com.secondhand.module.mime.entity.UserSale;
import com.secondhand.module.mime.service.IUserBuyService;
import com.secondhand.module.mime.service.IUserSaleService;
import com.secondhand.module.order.dto.OrderDTO;
import com.secondhand.module.order.dto.UpdateOrderDTO;
import com.secondhand.module.order.entity.OrderProduct;
import com.secondhand.module.order.serivce.AccountService;
import com.secondhand.module.order.serivce.OrderProductService;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.entity.UserAttr;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.redis.RedisTool;
import com.secondhand.util.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.order.mapper.OrderMapper;
import com.secondhand.module.order.entity.Order;
import com.secondhand.module.order.serivce.OrderService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author zangjan
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    ProductService productService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    RedisTool redisTool;
    @Autowired
    @Resource(name = "SerializableRedisTemplate")
    RedisTemplate redisTemplate;
    @Autowired
    IUserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    IUserBuyService iUserBuyService;
    @Autowired
    IUserSaleService iUserSaleService;
    @Autowired
    UserAttrService userAttrService;
    //订单关联商品id
    //订单需要记录买家
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        OrderProduct orderProduct = new OrderProduct();
        Product product = productService.getById(orderDTO.getProductid());
//        if(product == null || !redisTemplate.hasKey("productNum")){
//            return  false;
//        }
        //更改库存这个操作要加锁
        //拿着相同的订单，同时请求两边
       HashOperations hashOperations =  redisTemplate.opsForHash();
       Object productNum = hashOperations.get("productNum",String.valueOf(orderDTO.getProductid()));
       if(Integer.parseInt(String.valueOf(productNum))<0){
           throw new ServiceException("商品数量不足");
       }
       //原子操作减一
      Long result =  hashOperations.increment("productNum", String.valueOf(orderDTO.getProductid()), new Integer(-1));
      if(result<0){
          hashOperations.increment("productNum", String.valueOf(orderDTO.getProductid()), new Integer(1));
          throw new ServiceException("商品数量不足");
      }
//        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {
//            @Override
//            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
//                operations.watch((K) "productNum");
//                BoundHashOperations boundHashOperations = operations.boundHashOps((K) "productNum");
//                Object productNumber =  boundHashOperations.get(String.valueOf(orderDTO.getProductid()));
//                operations.multi();
//                boundHashOperations.put(String.valueOf(orderDTO.getProductid()),  String.valueOf(Integer.valueOf(String.valueOf(productNumber))-1));
//                List<Object> result = operations.exec();
//                return result;
//            }
//        };
//        List<Boolean> list= (List<Boolean>) redisTemplate.execute(sessionCallback);
        order.setOrderid(orderDTO.getOrderid());
        //初始化订单状态和版本
        order.setStatus(0);
        order.setVersion(0);
        try {
            this.save(order);
        }catch (Exception ex){
            hashOperations.increment("productNum", String.valueOf(orderDTO.getProductid()), new Integer(1));
            throw new ServiceException(ex.getMessage());
        }
        orderProduct.setOrderid(orderDTO.getOrderid());
        orderProduct.setProductid(orderDTO.getProductid());
        try {
            orderProductService.save(orderProduct);
        }catch (Exception ex){
            hashOperations.increment("productNum", String.valueOf(orderDTO.getProductid()), new Integer(1));
            throw new ServiceException(ex.getMessage());
        }
        return true;
    }

    //RC
    /**
     * 更新订单
     *
     * @param updateOrderDTO
     * @return Boolean
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public Boolean updateOrder(UpdateOrderDTO updateOrderDTO) {
        return this.baseMapper.updateOrder(updateOrderDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean payOrder(OrderDTO orderDTO) {
        User user =userService.getById(orderDTO.getUid());
        Product product = productService.search(String.valueOf(orderDTO.getProductid()));
        if(user.getBalance().compareTo(product.getProductPrice())==-1){
            throw new ServiceException("余额不足");
        }
        boolean result = accountService.transfer(product.getProductPrice().intValue(), orderDTO.getUid(), product.getUserId());
        if(!result){
            throw new ServiceException("转账失败");
        }
        Order order = this.getById(orderDTO.getOrderid());
        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();
        updateOrderDTO.setStatus(1);
        updateOrderDTO.setOrderid(orderDTO.getOrderid());
        updateOrderDTO.setVersion(order.getVersion());
        result = updateOrder(updateOrderDTO);
        if(!result){
            throw new ServiceException("订单更新失败");
        }
        //更新商品数量
        if(product.getProductNum()-1<0){
            throw new ServiceException("商品数量不足");
        }
        Integer productNum = product.getProductNum();
        product.setProductNum(productNum-1);
        productService.update(product,new LambdaQueryWrapper<Product>().eq(Product::getProductNum,productNum));
        List<Integer> list = new ArrayList();
        //卖家
        list.add(product.getUserId());
        //买家
        list.add(orderDTO.getUid());
        List<UserAttr> userAttrs = userAttrService.list(new LambdaQueryWrapper<UserAttr>().in(UserAttr::getUid,list));
        for (UserAttr userAttr: userAttrs) {
            if(userAttr.getUid().equals(product.getUserId())){
                UserSale userSale = new UserSale();
                userSale.setBuyerId((int) orderDTO.getUid().longValue());
                userSale.setOrderId(orderDTO.getOrderid());
                userSale.setProductId(product.getId());
                userSale.setStatus(1);
                //卖家id
                userSale.setUid(Math.toIntExact(product.getUserId().longValue()));
                iUserSaleService.save(userSale);
                Integer sellNum = userAttr.getSellNum();
                userAttr.setSellNum(sellNum+1);
                userAttrService.update(userAttr,new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getSellNum,sellNum));
            }else {
                UserBuy userBuy = new UserBuy();
                userBuy.setUid(Math.toIntExact(Long.valueOf(orderDTO.getUid())));
                userBuy.setOrderId(orderDTO.getOrderid());
                userBuy.setProductId(orderDTO.getProductid());
                iUserBuyService.save(userBuy);
                userAttr = userAttrService.getOne(new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getUid,Long.valueOf(orderDTO.getUid())));
                Integer buyNum = userAttr.getBuyNum();
                userAttr.setBuyNum(buyNum+1);
                userAttrService.update(userAttr,new LambdaQueryWrapper<UserAttr>().eq(UserAttr::getBuyNum,buyNum));
            }
        }


        //更新余额时，带余额限制
        //更新卖家余额
        //更新订单状态
        //更新买家我买到的
        //更新卖家我卖出的
        return true;
    }
}

