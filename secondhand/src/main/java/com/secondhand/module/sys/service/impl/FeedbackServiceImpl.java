package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.FeedAo;
import com.secondhand.module.sys.ao.FeedbackAo;
import com.secondhand.module.sys.entity.Feedback;
import com.secondhand.module.sys.mapper.FeedbackMapper;
import com.secondhand.module.sys.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.vo.CurrentUserVo;
import com.secondhand.module.sys.vo.FeedbackVo;
import com.secondhand.util.shiro.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-04-18
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Override
    public PageApiResult listFeedback(Page page, Integer type) {
        IPage<FeedbackVo> iPage = baseMapper.listFeedback(page,type);
        return new PageApiResult(iPage);

    }

    // 添加
    @Override
    public ApiResult addFeedback(FeedAo ao) {
        CurrentUserVo userVo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();
        Feedback entity = new Feedback();
        BeanUtils.copyProperties(ao,entity);
        entity.setStatus(2);
        entity.setUid(Math.toIntExact(ShiroUtils.getUserId()));
        if (StringUtils.isBlank(ao.getUsername())){
            ao.setUsername(userVo.getUserName());
        }
        return this.save(entity) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }

    //首页 更新
    @Override
    public ApiResult updateFeedback(FeedbackAo ao, Integer status) {
        List<Long> ids = ao.getIds();
        //更新
        QueryWrapper<Feedback> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().in(Feedback::getFeedbackId,ids);
        Feedback entity = new Feedback();
        entity.setStatus(status);
        return this.update(entity,queryWrapper)?ApiResult.success("成功"):ApiResult.fail("失败");
    }

    // 删除
    @Override
    public ApiResult delFeedback(FeedbackAo ao) {
        List<Long> ids = ao.getIds();
        //更新
        QueryWrapper<Feedback> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().in(Feedback::getFeedbackId,ids);
        Feedback entity = new Feedback();
        entity.setIsDelete(1);
        return this.update(entity,queryWrapper)?ApiResult.success("成功"):ApiResult.fail("失败");
    }

    //首页 未处理事项
    @Override
    public PageApiResult getFeedback(Page page) {
        IPage<FeedbackVo> iPage = baseMapper.getFeedback(page);
        return new PageApiResult(iPage);
    }
}
