package com.secondhand.module.product.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.UserAttr;
import com.secondhand.module.product.mapper.UserAttrMapper;
import com.secondhand.module.sys.service.UserAttrService;

@Service
public class UserAttrServiceImpl extends ServiceImpl<UserAttrMapper, UserAttr> implements UserAttrService {

}

