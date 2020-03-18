package com.secondhand.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.entity.Product;

/**
 * 商品服务类
 */
public interface ProductService extends IService<Product> {

    //商品发布
    boolean issue(Product product);

    Product search(String id);

    boolean changeStatus(int status, Long productId);

    IPage<Product> getProductPageByUserId(Long userId , Page page);

    IPage<Product>  getSoldOutByUserId(Long userId, Page page);

    ApiResult updateProductById(Long id);

    IPage<Product>  saleProductByUserId(Long userId, Page page);
}


