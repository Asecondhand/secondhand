package com.secondhand.common.basemethod;

import lombok.Data;

import java.util.List;

/**
 * 返回的分页结果
 *
 * @param <T>
 * @author Erica
 */
@Data
public class PageList<T> {
    private long total;
    private List<T> list;

    public PageList(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}
