package com.secondhand.module.sys.vo;

import lombok.Data;

/**
 * @author Erica
 * @since 2020/4/19
 */
@Data
public class DataVo {
    private Integer viewNum;
    private Integer noticeNum;
    private Integer productNum;
    public DataVo(){

    }
    public DataVo(Integer viewNum, Integer noticeNum, Integer productNum) {
        this.viewNum = viewNum;
        this.noticeNum = noticeNum;
        this.productNum = productNum;
    }
}
