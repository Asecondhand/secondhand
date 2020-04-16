package com.secondhand.module.home.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.home.vo.SearchVo;
import com.secondhand.module.home.vo.TipsVo;

/**
 * es 查找
 */
public interface EsSearchService {

    SearchVo searchUserOrProductByKeyword(String keyword);

    /**
     * 搜索提示功能
     */
    TipsVo  getTips(String keyword);
}
