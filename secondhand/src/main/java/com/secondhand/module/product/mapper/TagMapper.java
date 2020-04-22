package com.secondhand.module.product.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.product.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.product.vo.TagVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
public interface TagMapper extends BaseMapper<Tag> {

    IPage<TagVo> findTag(Page page, @Param("keyWord") String keyWord);

}
