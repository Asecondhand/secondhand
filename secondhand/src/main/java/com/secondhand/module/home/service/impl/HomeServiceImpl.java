package com.secondhand.module.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.home.service.HomeService;
import com.secondhand.module.home.vo.HomeVo;
import com.secondhand.module.home.vo.ProductPicVO;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.entity.ProductPic;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.module.product.vo.ProductVo;
import com.secondhand.module.sys.service.ProductPicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-30 13:18
 **/
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    ProductService productService;
    @Autowired
    ProductPicService productPicService;
    @Override
    public HomeVo getHome() {
        Page<Product> page = new Page<>();
        IPage<Product> product = productService.page(page,new LambdaQueryWrapper<Product>().orderByDesc(Product::getCreateTime));
        HomeVo homeVo = new HomeVo();
        IPage<ProductPicVO> productPicVOIPage = product.convert(product1 -> {
           List<ProductPic> picList = productPicService.list(new LambdaQueryWrapper<ProductPic>().eq(ProductPic::getPid,product1.getId()));
           List<String> picString = new ArrayList<>();
            picList.forEach(productPic -> picString.add(productPic.getProductPic()));
           ProductPicVO productPicVO1 = new ProductPicVO();
            BeanUtils.copyProperties(product1,productPicVO1);
            productPicVO1.setPicList(picString);
            return productPicVO1;
        });
        homeVo.setProduct(productPicVOIPage);
        return homeVo;
    }
}
