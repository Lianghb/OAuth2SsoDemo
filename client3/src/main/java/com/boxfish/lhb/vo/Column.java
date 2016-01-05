package com.boxfish.lhb.vo;

import java.io.Serializable;

/**
 * Created by boxfish on 15/12/18.
 * 列属性
 */
public class Column implements Serializable{

    /**
     * 数据源,columns 绑定的数据源，由 columns.dataDT 定义
     */
    private  String data;

    /**
     *columns.nameDT 里定义的名称
     */
    private  String name;

    /**
     * 标记列是否能被搜索 为 true代表可以，否则不可以，这个是由 columns.searchableDT 控制
     */
    private boolean searchable;

    /**
     * 标记列是否能排序 为 true代表可以，否则不可以，这个是由 columns.orderableDT 控制
     */
    private boolean orderable;

    /**
     * 特定列的搜索条件
     */
    private Search search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public Column() {
    }



    public Column(String data, String name, boolean searchable, boolean orderable, Search search) {
        this.data = data;
        this.name = name;
        this.searchable = searchable;
        this.orderable = orderable;
        this.search = search;
    }

    @Override
    public String toString() {
        return "Column{" +
                "data=" + data +
                ", name='" + name + '\'' +
                ", searchable=" + searchable +
                ", orderable=" + orderable +
                ", search=" + search +
                '}';
    }
}
