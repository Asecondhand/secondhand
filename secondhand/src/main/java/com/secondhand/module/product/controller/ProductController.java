package com.secondhand.module.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.DTO.ProductDTO;
import com.secondhand.module.product.entity.Product;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.module.product.vo.ProductVo;
import com.secondhand.util.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @program: secondhand3
 * @description: 商品控制器
 * @author: zangjan
 * @create: 2020-02-25 10:16
 **/
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @Value("${resources.productsPath}")
    private String productsPath;

    @Value("${resources.path}")
    private String rootPath;

    /**
     * 商品发布
     */
    @PostMapping("/issue")
    public ApiResult<Boolean> issueProduct(@RequestBody ProductDTO productDTO) {
        return ApiResult.success(productService.issue(productDTO));
    }

    /**
     * 查询商品详情 需要把留言区的评论一起返回
     * 不需要登录
     * @return
     */
    @GetMapping("/search/{id}")
    public ApiResult<ProductVo> searchById(@PathVariable String id) {
        return ApiResult.success(productService.search(id));
    }

    /**
     * 修改商品的上下架
     */

    @PostMapping("/status")
    public ApiResult<Boolean> changeStatus(@RequestParam("status") int status, @RequestParam("productId") Long productId) {
        return ApiResult.success(productService.changeStatus(status, productId));
    }
    /**
     * 查询用户发布的商品
     * @param userId
     * @param page
     * @return
     */
    @GetMapping("/{userId}")
    public ApiResult getPageByUserId(@PathVariable Long userId, Page page){
       return ApiResult.success(productService.getProductPageByUserId(userId,page));
    }

    /**
     * "下架宝贝"
     * 查询用户下架的商品
     * @param page
     * @return
     */
    @GetMapping("/soldOut")
    public ApiResult getSoldOutByUserId(Page page) {
        Long userId = ShiroUtils.getUserId();
        return ApiResult.success(productService.getSoldOutByUserId(userId,page));
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ApiResult updateProductById(@PathVariable Long id) {
        return productService.updateProductById(id);
    }

    /**
     * "我卖出的"
     * 获取已卖出的商品
     * @param page
     * @return
     */
    @GetMapping("/sale")
    public ApiResult saleProductByUserId(Page page){
        Long userId = ShiroUtils.getUserId();
        return ApiResult.success(productService.getSaleProductByUserId(userId,page));
    }

}
