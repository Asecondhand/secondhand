package com.secondhand.module.sys.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.product.DTO.UserAttrDTO;
import com.secondhand.module.sys.entity.UserAttr;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserAttrService extends IService<UserAttr> {

    UserAttrDTO getCurrentUserInfo();

    boolean changeIcon(String icon);

    ApiResult getUserInfoByUserId(Long uid);
}

