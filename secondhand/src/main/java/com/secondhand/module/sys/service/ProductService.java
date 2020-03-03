package com.secondhand.module.sys.service;

import com.secondhand.module.sys.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 商品服务类
 */
public interface ProductService extends IService<Product> {

    //商品发布
    boolean issue(Product product);

    Product search(String id);

    boolean changeStatus(int status, Long productId);


}


