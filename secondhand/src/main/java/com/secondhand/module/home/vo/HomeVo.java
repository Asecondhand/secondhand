package com.secondhand.module.home.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

/**
 * @program: secondhand
 * @description:
 * @author: zangjan
 * @create: 2020-04-30 13:16
 **/
@Data
public class HomeVo {
    IPage<ProductPicVO> product;
}
