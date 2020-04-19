package com.secondhand.module.sys.service.impl;

import com.secondhand.module.sys.entity.Feedback;
import com.secondhand.module.sys.mapper.FeedbackMapper;
import com.secondhand.module.sys.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

}
