package com.secondhand.module.message.service.impl;

import com.secondhand.module.message.service.NotificationMessageService;
import com.secondhand.module.message.vo.GraphMessageVO;
import com.secondhand.module.product.service.GraphService;
import com.secondhand.module.product.service.ProductService;
import com.secondhand.module.product.vo.FollowUserVo;
import com.secondhand.module.product.vo.ProductVo;
import com.secondhand.module.sys.entity.User;
import com.secondhand.util.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-03-23 14:51
 **/
@Service
public class GraphMessage implements NotificationMessageService {
    @Autowired
    NotificationMessageService notificationMessageService;

    @Autowired
    GraphService graphService;

    @Autowired
    ProductService productService;
    /**
     * 获得关注用户的消息
     * @return
     */
    @Override
    public Object updateMessage(Long id) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(!id.equals(user.getUserId()))
            throw new ServiceException("用户信息匹配失败，请重新登录");
        List<GraphMessageVO> graphMessageVO = new ArrayList<>();
        //获得关注列表
        List<FollowUserVo> followUserVoList = graphService.followList(id);
        //每一个人关注者一个消息
        followUserVoList.forEach(followUserVo -> {
            ProductVo productVo = productService.search(followUserVo.getUid());
            if(productVo == null) {

            }else{
                GraphMessageVO messageVO = new GraphMessageVO();
                messageVO.setUid(followUserVo.getUid());
                messageVO.setIcon(followUserVo.getIcon());
                messageVO.setDate(productVo.getCreateTime());
                messageVO.setTimeDf(compTimeDiff(productVo.getCreateTime(),new Date()));
                messageVO.setName(followUserVo.getUname());
                messageVO.setImg(productVo.getProductPics().get(0).getProductPic());
                messageVO.setProductId(productVo.getId());
                graphMessageVO.add(messageVO);
            }
        });
        return graphMessageVO;
    }

    private  String compTimeDiff(Date pass,Date now){
        long diff = (now.getTime()-pass.getTime() )/1000;
        if(diff<0){
            return 0+"分";
        }
        int[] ints = new int[]{60*60*24*365,60*60*24*30,60*60*24,60 * 60,60};
        String[] strings= new String[]{"年","月","日","时","分"};
        int index =0;
        long ans =0;
        for (int i = 0; i < ints.length; i++) {
            if(diff/ints[i]>0){
                ans = diff/ints[i];
                index = i;
                break;
            }
        }
        return  ans+strings[index];
    }
}
