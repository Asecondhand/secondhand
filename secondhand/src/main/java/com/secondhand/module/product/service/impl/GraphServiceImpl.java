package com.secondhand.module.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.DTO.GraphStatusDTO;
import com.secondhand.module.product.entity.Graph;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.mapper.GraphMapper;
import com.secondhand.module.product.service.GraphService;
import com.secondhand.module.product.vo.FollowUserVo;
import com.secondhand.module.sys.entity.User;
import com.secondhand.module.sys.service.UserAttrService;
import com.secondhand.redis.RedisTool;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph> implements GraphService {

    @Autowired
    UserAttrService userAttrService;
    @Autowired
    GraphMapper graphMapper;

    @Autowired
    RedisTool redisTool;

    @Override
    public boolean follow(Graph graph) {
        //需要用户验证
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (graph.getFollowid().equals(user.getUserId())) {
            return this.saveOrUpdate(graph);
        }
        return false;
    }

    /**
     * 通过缓存获得的
     *
     * @param followId
     * @return
     */
    @Override
    public List<FollowUserVo> followList(Long followId) {
        String key = "followList:"+followId;
        //如何存储到redis中
        //从redis中取出来
        if(redisTool.hasKeys(key)){
            //设置key过期时间
            redisTool.expire(key,10, TimeUnit.MINUTES);
            BoundListOperations listOperation = redisTool.getRedisList(key);
            return (List<FollowUserVo>) listOperation.range(0,-1);
        }
       return  getById(followId,key);
    }

    @Override
    public List<FollowUserVo> uList(Long uid) {
        String key = "uidList:"+uid;
        if(redisTool.hasKeys(key)){
            redisTool.expire(key,10, TimeUnit.MINUTES);
            BoundListOperations listOperation = redisTool.getRedisList(key);
            return (List<FollowUserVo>) listOperation.range(0,-1);
        }
        return getByUid(uid,key);
    }

    @Override
    public boolean updateStatus(Long id, GraphStatusDTO graphStatusDTO) {
        Graph graph = this.getById(id);
        if(graph != null){
            graph.setStatus(graphStatusDTO.getStatus());
            return this.save(graph);
        }
        return false;
    }

    private   List<FollowUserVo> getById(Long followId,String key){
        BoundListOperations listOperation = redisTool.getRedisList(key);
        List<Graph> graphList = graphMapper.getFollowListById(followId);
        List<Long> idList = graphList.stream().map(Graph::getUid).collect(Collectors.toList());
        if(idList.size() == 0)
            return new ArrayList<>();
        List<UserAttr> userAttrs = userAttrService.list(new LambdaQueryWrapper<UserAttr>().in(UserAttr::getUid,idList));
        List<FollowUserVo> followUserVos = new ArrayList<>();
        userAttrs.forEach(userAttr -> {
            FollowUserVo followUserVo = new FollowUserVo();
            BeanUtils.copyProperties(userAttr,followUserVo);
            followUserVo.setId(userAttr.getId());
            followUserVos.add(followUserVo);
            listOperation.leftPush(followUserVo);
        });
        redisTool.expire(key,10, TimeUnit.MINUTES);
        return followUserVos;
    }
    //有空处理一下空数据，没有插入到redis的情况
    private List<FollowUserVo> getByUid(Long uid ,String key){
        BoundListOperations listOperation = redisTool.getRedisList(key);
        List<Graph> graphList = graphMapper.getUserList(uid);
        List<Long> idList = graphList.stream().map(Graph::getFollowid).collect(Collectors.toList());
        if(idList.size() == 0 ){
            return new ArrayList<>();
        }
        List<UserAttr> userAttrs =  userAttrService.list(new LambdaQueryWrapper<UserAttr>().in(UserAttr::getUid,idList));
        List<FollowUserVo> followUserVos = new ArrayList<>();
        userAttrs.forEach(userAttr -> {
            FollowUserVo followUserVo = new FollowUserVo();
            BeanUtils.copyProperties(userAttr,followUserVo);
            followUserVo.setId(userAttr.getId());
            followUserVos.add(followUserVo);
            listOperation.leftPush(followUserVo);
        });
        redisTool.expire(key,10, TimeUnit.MINUTES);
        return followUserVos;
    }
}
