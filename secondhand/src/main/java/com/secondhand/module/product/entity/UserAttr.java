package com.secondhand.module.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.secondhand.module.sys.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "user_attr")
@NoArgsConstructor
public class UserAttr {

    public UserAttr(User user){
        this.uid = Math.toIntExact(user.getUserId());
        this.icon = user.getHeaderPicture();
        this.uname = user.getUserName();
    }
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "uid")
    private Integer uid;

    /**
     * 点赞数量
     */
    @TableField(value = "start_num")
    private Integer startNum;

    /**
     * 关注数量
     */
    @TableField(value = "notice_num")
    private Integer noticeNum;

    /**
     * 粉丝数量 
     */
    @TableField(value = "follow_num")
    private Integer followNum;

    /**
     * 我发布的
     */
    @TableField(value = "issuse_num")
    private Integer issuseNum;

    /**
     * 我卖出的 
     */
    @TableField(value = "sell_num")
    private Integer sellNum;

    /**
     * 我买到的
     */
    @TableField(value = "buy_num")
    private Integer buyNum;

    /**
     * 我收藏的
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 用户头像//默认头像
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 用户名称
     */
    @TableField(value = "uname")
    private String uname;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_START_NUM = "start_num";

    public static final String COL_NOTICE_NUM = "notice_num";

    public static final String COL_FOLLOW_NUM = "follow_num";

    public static final String COL_ISSUSE_NUM = "issuse_num";

    public static final String COL_SELL_NUM = "sell_num";

    public static final String COL_BUY_NUM = "buy_num";

    public static final String COL_LIKE_NUM = "like_num";

    public static final String COL_ICON = "icon";

    public static final String COL_UNAME = "uname";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}