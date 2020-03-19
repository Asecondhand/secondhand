package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.secondhand.module.mime.vo.HomePageVO;
import com.secondhand.module.mime.vo.ProductInfoVo;
import com.secondhand.module.product.DTO.ProductDTO;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.entity.LeaveMessage;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.entity.ProductPic;
import com.secondhand.module.product.mapper.ProductMapper;
import com.secondhand.module.product.service.LeaveMessageService;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.module.product.vo.ProductVo;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.module.sys.service.ProductPicService;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.util.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService, InitializingBean {

    @Autowired
    LeaveMessageService leaveMessageService;
    @Autowired
    IUserService iUserService;

    @Autowired
    ProductPicService productPicService;

    private static BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1000000);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean issue(ProductDTO productDTO) {
        //检查userid 是否存在
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //商品发布的时候，可以通知所有的follow
        //观察者模式
        if (user.getUserId() == productDTO.getUserId().intValue()) {
            Product product =new Product();
            BeanUtils.copyProperties(productDTO,product);
            this.save(product);
            String[] productPic = productDTO.getProductPic();
            List<ProductPic> list = new ArrayList<>();
            for (String s : productPic) {
                list.add(new ProductPic(Math.toIntExact(product.getId()),s));
            }
          return  productPicService.saveBatch(list);
        }
        throw new ServiceException("用户验证出现错误,无法登录");
    }

    /**
     * 分页查询 需要把评论留言返回 返回VO对象
     *
     * @param id
     * @return
     */
    @Override
    public ProductVo search(String id) {
        //id 小于0
        //如果不在布隆过滤器里，直接返回false
        if (!bloomFilter.mightContain(Integer.parseInt(id)))
            throw new ServiceException("查询的id可能不存在");
        Product product = this.getById(id);
        if (product == null)
            return null;
        ProductVo productVo = new ProductVo();
        List<LeaveMessage> leaveMessageList = leaveMessageService.searchByProductIdAndPage(Long.valueOf(id));
        List<ProductPic> productPicList = productPicService.list(new LambdaQueryWrapper<ProductPic>().eq(ProductPic::getPid,id));
        BeanUtils.copyProperties(product, productVo);
        productVo.setLeaveMessages(leaveMessageList);
        productVo.setProductPics(productPicList);
        return productVo;
    }


    /**
     * @param status
     * @return
     */
    @Override
    public boolean changeStatus(int status, Long productId) {
        Product product = this.getById(productId);
        if (product == null)
            return false;
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        System.out.println(request.getAttribute("userinfo"));
        //获得当前登录对象
        CurrentUserVo userVo = (CurrentUserVo) request.getAttribute("userinfo");
        if (userVo.getUserId() != product.getUserId().intValue())
            throw new ServiceException("权限不足");
        status = status == 1 ? 1 : 0;
        product.setProductStatus(status);
        return this.updateById(product);
    }

    @Override
    public IPage<Product> getProductPageByUserId(Long userId, Page page) {
        return this.page(page, new LambdaQueryWrapper<Product>().eq(Product::getUserId, userId));
    }

    /**
     * 获取已下架商品
     * @param userId
     * @return
     */
    @Override
    public ApiResult getSoldOutByUserId(Long userId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Product::getUserId,userId)
                .eq(Product::getProductStatus,1);
        List<Product> list = this.list(queryWrapper);
        return ApiResult.success(list);
    }

    /**
     * 删除商品
     * @param id
     */
    @Override
    public ApiResult updateProductById(Long id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Product::getId,id);
        boolean update =  this.remove(queryWrapper);
        if (update==false){
            return ApiResult.fail("商品已删除");
        }
        return ApiResult.success("删除商品成功");
    }

    @Override
    public ApiResult mineProductByUserId(Long userId) {
        List<ProductInfoVo> list = baseMapper.mineProductByUserId(userId);
        HomePageVO vo = new HomePageVO();
        vo.setMineNum(list.size());
        vo.setProductInfoVos(list);
        return ApiResult.success(vo);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
    }
}



