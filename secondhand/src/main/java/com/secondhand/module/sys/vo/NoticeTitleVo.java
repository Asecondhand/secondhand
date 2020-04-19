package com.secondhand.module.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Erica
 * @since 2020/4/18
 */
@Data
public class NoticeTitleVo {

    private Integer noticeId;
    private String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String content;
    private Integer type; // 是否发布 0 未发布 1 已发布


}
