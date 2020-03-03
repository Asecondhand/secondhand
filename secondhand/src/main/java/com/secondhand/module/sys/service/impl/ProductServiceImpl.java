package com.secondhand.module.sys.service.impl;

import com.secondhand.module.sys.entity.LeaveMessage;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.IUserService;
import com.secondhand.module.sys.service.LeaveMessageService;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.module.sys.vo.ProductVo;
import com.secondhand.util.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.mapper.ProductMapper;
import com.secondhand.module.sys.entity.Product;
import com.secondhand.module.sys.service.ProductService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    LeaveMessageService leaveMessageService;
    @Autowired
    IUserService iUserService;
    @Override
    public boolean issue(Product product) {
        //检查userid 是否存在
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user.getUserId() == product.getUserId().intValue()){
            if(iUserService.getById(product.getUserId())!=null)
                return this.save(product);
            throw new ServiceException("用户不存在，请重新输入");
        }
        throw  new ServiceException("用户验证出现错误,无法登录");
    }

    /**
     * 分页查询 需要把评论留言返回 返回VO对象
     * @param id
     * @return
     */
    @Override
    public Product search(String id) {
        //id 小于0
        Product product = this.getById(id);
        if(product == null)
            return null;
        ProductVo productVo = new ProductVo();
        List<LeaveMessage> leaveMessageList = leaveMessageService.searchByProductIdAndPage(Long.valueOf(id));
        BeanUtils.copyProperties(product,productVo);
        productVo.setLeaveMessages(leaveMessageList);
        return productVo;
    }

    /**
     *
     * @param status
     * @return
     */
    @Override
    public boolean changeStatus(int status,Long productId) {
        Product product = this.getById(productId);
        if(product == null)
            return false;
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        System.out.println(request.getAttribute("userinfo"));
        //获得当前登录对象
        CurrentUserVo userVo = (CurrentUserVo) request.getAttribute("userinfo");
        if(userVo.getUserId() != product.getUserId().intValue())
            throw new ServiceException("权限不足");
        status = status==1 ? 1 : 0;
        product.setProductStatus(status);
        return this.updateById(product);
    }
}


