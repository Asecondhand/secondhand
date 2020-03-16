package com.secondhand.module.sys.service;

import com.secondhand.module.product.entity.UserAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

public interface UserAttrService extends IService<UserAttr> {

    UserAttr getCurrentUserInfo();
}


