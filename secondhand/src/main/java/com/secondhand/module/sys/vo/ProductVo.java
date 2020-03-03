package com.secondhand.module.sys.vo;

import com.secondhand.module.sys.entity.LeaveMessage;
import com.secondhand.module.sys.entity.Product;
import lombok.Data;

import java.util.List;

/**
 * @program: secondhand3
 * @description:
 * @author: zangjan
 * @create: 2020-03-03 10:13
 **/
@Data
public class ProductVo extends Product {
    private List<LeaveMessage> leaveMessages;
}