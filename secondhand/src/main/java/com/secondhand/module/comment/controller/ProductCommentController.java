package com.secondhand.module.comment.controller;


import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.comment.service.IProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品留言表 前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-03-25
 */
@RestController
@RequestMapping("/api/productComment")
public class ProductCommentController {
    @Autowired
    private IProductCommentService iProductCommentService;
    //添加评论  -- 一级评论 id = 0 二级评论 传上一级id

    /**
     * 商品留言列表
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public ApiResult getProductComment(@PathVariable("productId") Long productId){
        return  iProductCommentService.getProductComment(productId);
    }

}
