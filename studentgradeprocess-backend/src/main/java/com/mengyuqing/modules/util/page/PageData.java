package com.mengyuqing.modules.util.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int total;

    private List<T> list;

    /**
     * pageing
     * @param list
     * @param total
     */
    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int)total;
    }
}