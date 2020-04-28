package com.secondhand.module.product.controller;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.DTO.IconDTO;
import com.secondhand.module.product.DTO.UserAttrDTO;
import com.secondhand.module.product.vo.ProductVo;
import com.secondhand.module.sys.service.UserAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    UserAttrService userAttrService;
    @RequestMapping("{id}/product/issue")
    public ApiResult<List<ProductVo>> getProductList(@PathVariable String id){
        return null;
    }
    /**
     * 获得用户主页信息
     */
    @RequestMapping("/info")
    public ApiResult<UserAttrDTO> getUserInfo(){
        return ApiResult.success(userAttrService.getCurrentUserInfo());
    }

    @PutMapping("/icon")
    public ApiResult changeIcon(@RequestBody IconDTO icon){
        return ApiResult.success(userAttrService.changeIcon(icon.getIcon()));
    }

}
