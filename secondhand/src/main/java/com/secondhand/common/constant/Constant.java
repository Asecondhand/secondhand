package com.secondhand.common.constant;

import com.secondhand.util.shiro.ShiroUtils;

/**
 * @author Erica
 * @since 2020/2/3
 */
public class Constant {
    public static final long SUPER_ADMIN = 1L;
    public static final  Long USERID = ShiroUtils.getUserId();
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
