package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.LeaveMessage;
import com.secondhand.module.product.mapper.LeaveMessageMapper;
import com.secondhand.module.product.service.LeaveMessageService;
import com.secondhand.module.product.vo.LeaveMessageVo;
import com.secondhand.module.sys.vo.CurrentUserVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LeaveMessageServiceImpl extends ServiceImpl<LeaveMessageMapper, LeaveMessage> implements LeaveMessageService {

    @Override
    public IPage<LeaveMessage> searchByProductIdAndPage(Long ProductId, Page<LeaveMessage> page) {
        LambdaQueryWrapper<LeaveMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LeaveMessage::getProductId,ProductId);
        return this.page(page,wrapper);
    }

    @Override
    public List<LeaveMessage> searchByProductIdAndPage(Long ProductId) {
        LambdaQueryWrapper<LeaveMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LeaveMessage::getProductId,ProductId);
        return this.list(wrapper);
    }

    @Override
    public Boolean upload(LeaveMessageVo leaveMessage) {
        LeaveMessage leaveMessage1 = new LeaveMessage();
        CurrentUserVo currentUserVo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();
        BeanUtils.copyProperties(leaveMessage,leaveMessage1);
        leaveMessage1.setUid(currentUserVo.getUserId().intValue());
        leaveMessage1.setUname(currentUserVo.getUserName());
        return this.save(leaveMessage1);
    }

}
