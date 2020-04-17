package com.secondhand.util.tree;

import com.secondhand.module.sys.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Erica
 * @since 2020/4/13
 */
public class TreeUtil {
    public static List<MenuVo> getChild(Long menuId, List<MenuVo> allMenu) {
        // 子菜单
        List<MenuVo> childList = new ArrayList<>();
        for (MenuVo nav : allMenu) {
            // 遍历，找到pid = menuId，则为根节点的子节点
            if (nav.getParentId() == menuId) {
                childList.add(nav);
            }
        }
        // 递归
        for (MenuVo nav : childList) {
            nav.setChildren(getChild(nav.getMenuId(), allMenu));
        }
        // System.out.println(childList);
        // 判断集合是否为空，防止比较时出现空指针异常
        if (!CollectionUtils.isEmpty(childList)){
            Collections.sort(childList, order());
        }

        // 如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        // System.out.println(childList);
        return childList;
    }

    /*
     * 排序,根据orderNum排序
     */
    public static Comparator<MenuVo> order() {
        Comparator<MenuVo> comparator = new Comparator<MenuVo>() {

            @Override
            public int compare(MenuVo o1, MenuVo o2) {
                if (o1.getOrderNum() != o2.getOrderNum()) {
                    return o1.getOrderNum() - o2.getOrderNum();
                }
                return 0;
            }
        };
        return comparator;
    }

}
