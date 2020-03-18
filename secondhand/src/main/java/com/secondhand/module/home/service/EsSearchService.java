package com.secondhand.module.home.service;

import com.secondhand.common.basemethod.ApiResult;
import com.secondhand.module.home.vo.SearchVo;

/**
 * es 查找
 */
public interface EsSearchService {

    SearchVo SearchUserOrProductByKeyword(String keyword);
}
