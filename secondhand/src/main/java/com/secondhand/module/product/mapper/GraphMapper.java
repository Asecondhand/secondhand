package com.secondhand.module.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secondhand.module.product.entity.Graph;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface GraphMapper extends BaseMapper<Graph> {
    List<Graph> getFollowListById(@Param("id") Long id);

    List<Graph> getUserList(@Param("id") Long id);
}