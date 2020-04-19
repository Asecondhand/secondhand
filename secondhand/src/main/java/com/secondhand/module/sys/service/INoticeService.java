package com.secondhand.module.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.TimeAo;
import com.secondhand.module.sys.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
public interface INoticeService extends IService<Notice> {

    ApiResult getNoticeDetail(Long noticeId);

    PageApiResult getNoticeList( Page page,String keyWord);

    ApiResult addNoticeList(Notice ao);

    ApiResult updateNoticeType(Long noticeId);

    ApiResult getSiteList();

    ApiResult getProductList();

    ApiResult getTagList(TimeAo ao);

}
