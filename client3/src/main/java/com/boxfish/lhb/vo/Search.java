package com.boxfish.lhb.vo;

import java.io.Serializable;

/**
 * Created by boxfish on 15/12/18.
 * 搜索
 */
public class Search implements Serializable{

    /**
     * 搜索的值
     */
    private String value;

    /**
     * 是否正则表达式搜索
     */
    private boolean regex;

    public Search(String value, boolean regex) {
        this.value = value;
        this.regex = regex;
    }

    public Search() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return "Search{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
