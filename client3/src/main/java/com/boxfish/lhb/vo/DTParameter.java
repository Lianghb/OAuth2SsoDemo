package com.boxfish.lhb.vo;

import java.io.Serializable;
import java.util.List;

/**
 * datatables传递给后台的参数
 * Created by boxfish on 15/12/18.
 */

public class DTParameter implements Serializable{

    /**
     * 请求次数计数器，每次发送给服务器后又原封返回，
     * 因为请求是异步的为了确保每次请求能对应到服务器返回的数据
     */
    int draw;

    /**
     * 第一条数据的起始位置，比如0代表第一条数据
     */
    int start;

    /**
     * 告诉服务器每页显示的条数，这个数字会等于返回的记录数，
     * 可能会大于因为服务器可能没有那么多数据。
     * 这个也可能是-1，代表需要返回全部数据
     */
    int length;

    /**
     * 全局的搜索条件
     */
    Search search;

    /**
     * 排序属性
     */
    List<Order> order;

    /**
     * 列属性
     */
    List<Column> columns;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public DTParameter() {
    }

    public DTParameter(int draw, int start, int length, Search search, List<Order> order, List<Column> columns) {
        this.draw = draw;
        this.start = start;
        this.length = length;
        this.search = search;
        this.order = order;
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "DTParameter{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", search=" + search +
                ", order=" + order +
                ", columns=" + columns +
                '}';
    }
}
