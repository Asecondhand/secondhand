package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.product.ao.TagAo;
import com.secondhand.module.product.entity.Tag;
import com.secondhand.module.product.mapper.TagMapper;
import com.secondhand.module.product.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.vo.TagVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Override
    public PageApiResult findTag(String keyWord, Page page) {
        IPage<TagVo> iPage = baseMapper.findTag(page,keyWord);
        return new PageApiResult(iPage);
    }

    @Override
    public ApiResult addTag(TagAo ao) {
        if(StringUtils.isBlank(ao.getTagName())){
            return  ApiResult.fail("标签名不能为空");
        }
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Tag::getTagName,ao.getTagName());
        Tag entity = this.getOne(queryWrapper);
        if (entity!=null){
            return  ApiResult.fail("标签已存在");
        }
        Tag tag = new Tag();
        tag.setTagName(ao.getTagName());
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        tag.setIsDelete(0);
        return this.save(tag)?ApiResult.success("成功"):ApiResult.fail("失败");
    }

    @Override
    public ApiResult findTagBefore() {
        List<Tag> list = this.list();
        List<TagVo> vos = new ArrayList<>();
        list.forEach(x->{
            TagVo entity = new TagVo();
            BeanUtils.copyProperties(x,entity);
            vos.add(entity);
        });
        return ApiResult.success(vos);
    }

    @Override
    public ApiResult delTag(Long tagId) {
        Tag tag = new Tag();
        tag.setUpdateTime(new Date());
        tag.setTagId(Math.toIntExact(tagId));
        tag.setIsDelete(1);
        return this.updateById(tag)?ApiResult.success("成功"):ApiResult.fail("失败");
    }
}
