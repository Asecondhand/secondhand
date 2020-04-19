package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.entity.Notice;
import com.secondhand.module.sys.mapper.NoticeMapper;
import com.secondhand.module.sys.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.vo.NoticeTitleVo;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public ApiResult getNoticeDetail(Long noticeId) {
        Notice notice = this.getById(noticeId);
        return ApiResult.success(notice);
    }

    @Override
    public PageApiResult getNoticeList(String keyWord, Page page) {
        IPage<NoticeTitleVo> iPage = baseMapper.getNoticeList(keyWord,page);
        return new PageApiResult(iPage);
    }

    @Override
    public ApiResult addNoticeList(Notice ao) {
        return this.save(ao)?ApiResult.success("成功"):ApiResult.fail("失败");
    }

    @Override
    public ApiResult updateNoticeType(Long noticeId) {
        Notice notice = new Notice();
        notice.setType(1);
        notice.setEditTime(new Date());
        notice.setNoticeId(Math.toIntExact(noticeId));
        return this.updateById(notice)?ApiResult.success("成功"):ApiResult.fail("失败");
    }
}
