package com.boxfish.lhb.vo;

import java.io.Serializable;

/**
 * 排序
 * Created by boxfish on 15/12/18.
 */

public class Order implements Serializable{

    /**
     * 列
     */
    private int column;

    /**
     * 排序方式
     */
    private String dir;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Order{" +
                "column=" + column +
                ", dir='" + dir + '\'' +
                '}';
    }

    public Order() {
    }

    public Order(int column, String dir) {
        this.column = column;
        this.dir = dir;
    }
}
