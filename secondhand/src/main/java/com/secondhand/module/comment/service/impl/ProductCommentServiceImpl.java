package com.secondhand.module.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.comment.ao.ProductCommentAO;
import com.secondhand.module.comment.entity.ProductComment;
import com.secondhand.module.comment.mapper.ProductCommentMapper;
import com.secondhand.module.comment.service.IProductCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.comment.vo.ChildrenCommentVO;
import com.secondhand.module.comment.vo.ParentCommentVO;
import com.secondhand.module.comment.vo.ProductCommentVO;
import com.secondhand.module.sys.vo.CurrentUserVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品留言表 服务实现类
 * </p>
 *
 * @author Erica
 * @since 2020-03-25
 */
@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements IProductCommentService {


    @Override
    public ApiResult addProductComment(ProductCommentAO ao) {
        CurrentUserVo userVo = (CurrentUserVo) SecurityUtils.getSubject().getPrincipal();
        ProductComment productComment = new ProductComment();
        productComment.setProductId(ao.getProductId());
        productComment.setCommentPid(ao.getCommentPid());
        productComment.setMessage(ao.getMessage());
        productComment.setUid(userVo.getUserId());
        productComment.setIcon(userVo.getHeaderPicture());
        productComment.setUname(userVo.getUserName());
        productComment.setIsDelete(0);
        return this.save(productComment) ? ApiResult.success("评论成功") : ApiResult.fail("评论失败");
    }

    @Override
    public ApiResult getProductComment(Long productId) {
        // 查询商品所有父级评论
        List<ParentCommentVO> parentComments = getParentComments(productId);
        // 查询商品所有的子级评论
        List<ChildrenCommentVO> childrenComments = baseMapper.getChildrenComments(productId);
        // 得到评论list
        List<ProductCommentVO> productCommentVOS = getProductComments(parentComments, childrenComments);
        return ApiResult.success(productCommentVOS);
    }

    /**
     * 获取商品所有的评论
     *
     * @param parentComments
     * @param childrenComments
     * @return
     */
    private List<ProductCommentVO> getProductComments(List<ParentCommentVO> parentComments, List<ChildrenCommentVO> childrenComments) {
        List<ProductCommentVO> comments = new ArrayList<>();
        // 只有父级评论
        if (parentComments.size() > 0 && childrenComments.size() <= 0) {
            for (int i = 0; i < parentComments.size(); i++) {
                // 将父级评论添加到出参实体中
                ProductCommentVO productCommentVO = new ProductCommentVO();
                productCommentVO.setParentComments(parentComments.get(i));
                comments.add(productCommentVO);
            }
        }
        // 父级评论和对应的子级评论
        if (parentComments.size() > 0 && childrenComments.size() > 0) {
            for (int i = 0; i < parentComments.size(); i++) {
                List<ChildrenCommentVO> childrenCommentVOS = new ArrayList<>();
                for (int j = 0; j < childrenComments.size(); j++) {
                    // 找到父级评论id = 子级评论的Pid
                    if (parentComments.get(i).getCommentId().equals(childrenComments.get(j).getCommentPid())) {
                        // 添加到对应的父级评论下面的子级评论列表
                        childrenCommentVOS.add(childrenComments.get(j));
                        // 查找子级评论下面的子级评论
                        List<ChildrenCommentVO> vos = getChildren(childrenComments.get(j).getCommentId(),childrenComments);
                        if (vos.size()>0){
                            for (ChildrenCommentVO vos1:vos){
                                childrenCommentVOS.add(vos1);
                            }
                        }
                    }
                }
                // 创建评论输出
                ProductCommentVO productCommentVO = new ProductCommentVO();
                // 添加父级评论
                productCommentVO.setParentComments(parentComments.get(i));
                // 添加对应的子级评论列表
                productCommentVO.setChildrenComments(childrenCommentVOS);
                comments.add(productCommentVO);

            }
        }
        return comments;
    }

    //递归查找二级评论
    public List<ChildrenCommentVO> getChildren(Long pid, List<ChildrenCommentVO> ao) {
        List<ChildrenCommentVO> vos = new ArrayList<>();
        for (int i = 0; i < ao.size(); i++) {
            if (ao.get(i).getCommentPid().equals(pid)){
                vos.add(ao.get(i));
            }
        }
        return vos;
    }

    // 查询商品所有父级评论
    List<ParentCommentVO> getParentComments(Long productId) {
        QueryWrapper<ProductComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProductComment::getProductId, productId)
                .eq(ProductComment::getCommentPid, 0)
                .eq(ProductComment::getIsDelete, 0)
                .orderByDesc(ProductComment::getCreateTime);
        List<ProductComment> productComments = this.list(queryWrapper);
        List<ParentCommentVO> voList = new ArrayList<>();
        if (productComments.size() > 0) {
            for (ProductComment vo : productComments) {
                ParentCommentVO parentCommentVO = new ParentCommentVO();
                BeanUtils.copyProperties(vo, parentCommentVO);
                voList.add(parentCommentVO);
            }
        }
        return voList;
    }

}
