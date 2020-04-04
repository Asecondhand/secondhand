package com.secondhand.module.comment.service.impl;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.comment.ao.ProductCommentAO;
import com.secondhand.module.comment.entity.ProductComment;
import com.secondhand.module.comment.mapper.ProductCommentMapper;
import com.secondhand.module.comment.service.IProductCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.comment.vo.ChildrenCommentVO;
import com.secondhand.module.comment.vo.ParentCommentVO;
import com.secondhand.module.comment.vo.ProductCommentVO;
import com.secondhand.module.comment.vo.ProductCommentsVO;
import com.secondhand.module.sys.vo.CurrentUserVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
        // 查询所有的评论
        List<ProductCommentsVO> allComments = baseMapper.getallComments(productId);
        // 存放父级评论
        List<ProductCommentsVO> parentComments = new ArrayList<>();
        for (ProductCommentsVO item : allComments) {
            if (item.getCommentPid() == 0) {
                parentComments.add(item);
            }
        }
        // 查找父级评论下面的子级评论
        for (ProductCommentsVO item : parentComments) {
            List<ProductCommentsVO> listChild = getChild(item.getCommentId(), allComments);
            item.setChildren(listChild);
        }
        // return ApiResult.success(parentComments);

        // 整合成只有两层
        List<ProductCommentVO> vos = mergeComments(parentComments);
        return ApiResult.success(vos);

    }

    private List<ProductCommentVO> mergeComments(List<ProductCommentsVO> parentComments) {
        // 整合成只有两层
        List<ProductCommentVO> vos = new ArrayList<>();
        for (ProductCommentsVO item : parentComments) {
            List<ChildrenCommentVO> childVos = new ArrayList<>();
            childVos = mergeChild(item, childVos);
            // 创建评论输出
            ProductCommentVO productCommentVO = new ProductCommentVO();
            ParentCommentVO entity = new ParentCommentVO();
            BeanUtils.copyProperties(item, entity);
            // 添加父级评论
            productCommentVO.setParentComments(entity);
            // 添加对应的子级评论列表
            // 排序逆序
            // Collections.reverse(childVos);
            // 日期倒序
            Collections.sort(childVos, new Comparator<ChildrenCommentVO>() {
                @Override
                public int compare(ChildrenCommentVO o1, ChildrenCommentVO o2) {
                    Date date1 = o1.getCreateTime();
                    Date date2 = o2.getCreateTime();
                    // 倒序after 升序before
                    if (date1.after(date2)) {
                        return 1;
                    }
                    return -1;
                }
            });
            productCommentVO.setChildrenComments(childVos);

            vos.add(productCommentVO);
        }
        return vos;
    }

    private List<ChildrenCommentVO> mergeChild(ProductCommentsVO vo, List<ChildrenCommentVO> childVos) {
        // List<ChildrenCommentVO> vos =  new ArrayList<>();
        if (vo.getChildren().size() > 0) {
            for (ProductCommentsVO item : vo.getChildren()) {
                ChildrenCommentVO entity = new ChildrenCommentVO();
                BeanUtils.copyProperties(item, entity);
                childVos.add(entity);
                // 空指针异常
                if (CollectionUtils.isNotEmpty(item.getChildren())) {
                    mergeChild(item, childVos);
                    // List<ChildrenCommentVO> list = mergeChild(item);
                    // for (ChildrenCommentVO item1 : list){
                    //     vos.add(item1);
                    // }
                }
            }
        }
        // Collections.reverse(childVos);
        // return vos;
        return childVos;
    }

    // 递归
    private List<ProductCommentsVO> getChild(Long commentId, List<ProductCommentsVO> allComments) {
        // 存在子评论
        List<ProductCommentsVO> listChild = new ArrayList<>();
        for (ProductCommentsVO item : allComments) {
            if (item.getCommentPid() == commentId) {
                listChild.add(item);
            }

        }
        // 递归
        for (ProductCommentsVO item : listChild) {
            item.setChildren(getChild(item.getCommentId(), allComments));
        }
        if (listChild.size() == 0) {
            return null;
        }
        return listChild;
    }


}
