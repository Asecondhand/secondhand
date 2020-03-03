package com.secondhand.module.sys.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.sys.entity.Graph;
import com.secondhand.module.sys.mapper.GraphMapper;
import com.secondhand.module.sys.service.GraphService;
@Service
public class GraphServiceImpl extends ServiceImpl<GraphMapper, Graph> implements GraphService{

}
