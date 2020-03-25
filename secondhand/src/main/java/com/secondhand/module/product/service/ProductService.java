package com.secondhand.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.mime.vo.ProductInfoVo;
import com.secondhand.module.product.DTO.ProductDTO;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.vo.ProductVo;

/**
 * 商品服务类
 */
public interface ProductService extends IService<Product> {

    //商品发布
    boolean issue(ProductDTO productDTO);

    ProductVo search(String id);

    //根据用户id查找商品
    ProductVo search(Long id);
    boolean changeStatus(int status, Long productId);

    IPage<Product> getProductPageByUserId(Long userId , Page page);

    ApiResult  getSoldOutByUserId(Long userId);

    ApiResult updateProductById(Long id);

    ApiResult mineProductByUserId(Long userId);

    ApiResult personalDynamic(Long userId);

    ApiResult getUserRelease(Long userId);
}


