package com.secondhand.module.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.module.sys.entity.LeaveMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.module.sys.vo.LeaveMessageVo;

import java.util.List;

public interface LeaveMessageService extends IService<LeaveMessage>{

    /**
     * 通过Product id 查询留言列表
     */
    IPage<LeaveMessage> searchByProductIdAndPage(Long ProductId, Page<LeaveMessage> page);

    List<LeaveMessage> searchByProductIdAndPage(Long ProductId);
    /**
     * 上传留言
     */
    Boolean upload(LeaveMessageVo leaveMessage);

}
