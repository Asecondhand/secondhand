package com.secondhand.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.common.basemethod.PageApiResult;
import com.secondhand.module.sys.ao.TimeAo;
import com.secondhand.module.sys.entity.Notice;
import com.secondhand.module.sys.mapper.NoticeMapper;
import com.secondhand.module.sys.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.vo.DataVo;
import com.secondhand.module.sys.vo.NoticeTitleVo;
import com.secondhand.module.sys.vo.TagSumVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public ApiResult getNoticeDetail(Long noticeId) {
        Notice notice = this.getById(noticeId);
        return ApiResult.success(notice);
    }

    @Override
    public PageApiResult getNoticeList(Page page, String keyWord) {
        IPage<NoticeTitleVo> iPage = baseMapper.getNoticeList(page, keyWord);
        return new PageApiResult(iPage);
    }

    @Override
    public ApiResult addNoticeList(Notice ao) {
        return this.save(ao) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }

    @Override
    public ApiResult updateNoticeType(Long noticeId) {
        Notice notice = new Notice();
        notice.setType(1);
        notice.setEditTime(new Date());
        notice.setNoticeId(Math.toIntExact(noticeId));
        return this.updateById(notice) ? ApiResult.success("成功") : ApiResult.fail("失败");
    }


    @Override
    public ApiResult getSiteList() {
        Integer viewNum = baseMapper.getViewNum();
        Integer noticeNum = baseMapper.getNoticeNum();
        Integer productNum = baseMapper.getProductNum();
        return ApiResult.success(new DataVo(viewNum, noticeNum, productNum));
    }

    @Override
    public ApiResult getProductList() {
        List<TagSumVo> list = baseMapper.getProductList();
        Double sum = 0.0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getTotal();
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setTotal( new BigDecimal(list.get(i).getTotal()/sum)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return ApiResult.success(list);
    }

    @Override
    public ApiResult getTagList(TimeAo ao) {
        List<TagSumVo> list = baseMapper.getTagList(ao);
        return ApiResult.success(list);
    }

    @Override
    public ApiResult getNotice() {
        List<Notice> list = baseMapper.getNotice();
        return ApiResult.success(list);
    }
}
