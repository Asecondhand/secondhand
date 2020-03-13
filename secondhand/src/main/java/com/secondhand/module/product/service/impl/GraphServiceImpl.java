package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.mapper.GraphMapper;
import com.secondhand.module.product.service.GraphService;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.UserAttrService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph> implements GraphService {

    @Autowired
    UserAttrService userAttrService;
    @Autowired
    GraphMapper graphMapper;
    @Override
    public boolean follow(Graph graph) {
        //需要用户验证
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (graph.getFollowid().equals(user.getUserId())){
            return this.save(graph);
        }
        return  false;
    }


    /**
     * 通过缓存过的
     * @param followId
     * @return
     */
    @Override
    public List<UserAttr> followList(Long followId) {
        List<Graph> graphList = graphMapper.getFollowListById(followId);
        List<Long> idList = graphList.stream().map(Graph::getUid).collect(Collectors.toList());
        return (List<UserAttr>) userAttrService.listByIds(idList);
    }

    @Override
    public List<UserAttr> uList(Long uid) {
        List<Graph> graphList = graphMapper.getFollowListById(uid);
        List<Long> idList = graphList.stream().map(Graph::getFollowid).collect(Collectors.toList());
        return (List<UserAttr>) userAttrService.listByIds(idList);
    }
}
