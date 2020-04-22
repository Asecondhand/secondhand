package com.secondhand.module.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.sys.ao.TimeAo;
import com.secondhand.module.sys.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.sys.vo.NoticeTitleVo;
import com.secondhand.module.sys.vo.TagSumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    IPage<NoticeTitleVo> getNoticeList(Page page,@Param("keyWord") String keyWord);

    Integer getViewNum();

    Integer getNoticeNum();

    Integer getProductNum();

    List<TagSumVo> getTagList(@Param("ao") TimeAo ao);

    List<TagSumVo> getProductList();

}
