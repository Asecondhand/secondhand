package com.secondhand.module.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.FeedAo;
import com.secondhand.module.sys.ao.FeedbackAo;
import com.secondhand.module.sys.entity.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
public interface IFeedbackService extends IService<Feedback> {

    PageApiResult listFeedback(Page page, Integer type);

    ApiResult addFeedback(FeedAo ao);

    ApiResult updateFeedback(FeedbackAo ao, Integer status);

    ApiResult delFeedback(FeedbackAo ao);

    PageApiResult getFeedback(Page page);
}
