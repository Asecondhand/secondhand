package com.secondhand.module.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.product.ao.TagAo;
import com.secondhand.module.product.entity.Tag;
import com.secondhand.module.product.service.ITagService;
import com.secondhand.module.sys.ao.FindUserAo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/api/productTag")
public class TagController {

    @Autowired
    private ITagService iTagService;

    // 查询搜索
    @GetMapping("/find")
    public PageApiResult findTag(String keyWord,
                                     Integer pageIndex,
                                     Integer pageSize){
        Page page = new Page<>(pageIndex, pageSize);
        return iTagService.findTag(keyWord,page);
    }

    //新增
    @PostMapping("/add")
    public ApiResult addTag(@RequestBody TagAo ao ){
        return iTagService.addTag(ao);
    }

    // 查询搜索
    @GetMapping("/list")
    public ApiResult findTagBefore(){
        return iTagService.findTagBefore();
    }

    //修改 -- 更新product表

    // 删除
    @DeleteMapping("/del/{tagId}")
    public ApiResult delTag(@PathVariable("tagId") Long tagId){
        return iTagService.delTag(tagId);
    }
}
