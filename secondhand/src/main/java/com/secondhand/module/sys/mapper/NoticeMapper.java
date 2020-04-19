package com.secondhand.module.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.sys.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.sys.vo.NoticeTitleVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    IPage<NoticeTitleVo> getNoticeList(@Param("keyWord") String keyWord, Page page);

}
