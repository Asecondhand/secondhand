package com.secondhand.module.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.product.ao.TagAo;
import com.secondhand.module.product.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
public interface ITagService extends IService<Tag> {

    PageApiResult findTag(String keyWord, Page page);

    ApiResult addTag(TagAo ao);

    ApiResult findTagBefore();

    ApiResult delTag(Long tagId);
}
