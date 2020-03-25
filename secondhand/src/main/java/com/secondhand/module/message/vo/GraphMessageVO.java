package com.secondhand.module.message.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: secondhand
 * @description: 关注消息返回类
 * @author: zangjan
 * @create: 2020-03-23 15:18
 **/
@Data
public class GraphMessageVO {
    //用户id
    private Long uid;
    //用户头像
    private String icon;
    //创建时间
    private Date date;
    //时间差 自动时间转换
    private String timeDf;
    //商品名称
    private String name;
    //商品图片
    private String img;
    //时间单位
    private String timeUnit;
}
