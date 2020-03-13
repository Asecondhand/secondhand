package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.vo.ProductVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-09 17:32
 **/
@RestController
@RequestMapping("/api/user/workspace")
public class UserWorkSpaceController {


    @RequestMapping("{id}/product/issue")
    public ApiResult<List<ProductVo>> getProductList(@PathVariable String id){
        return null;
    }

}
