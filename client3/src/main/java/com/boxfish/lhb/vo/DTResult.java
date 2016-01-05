package com.boxfish.lhb.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by boxfish on 15/12/16.
 * 专为DataTables插件而设置的返回结果
 */
public class DTResult<T> implements Serializable{

    /**
     * 上面提到了，Datatables发送的draw是多少那么服务器就返回多少。
     * 这里注意，作者出于安全的考虑，强烈要求把这个转换为整数后再返回，而不是纯粹的接受然后返回，
     * 这是 为了防止跨站脚本（XSS）攻击。
     */
    int draw;

    /**
     * 即没有过滤的记录数（数据库里总共记录数）
     */
    long recordsTotal;

    /**
     * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
     */
    long recordsFiltered;

    /**
     * 表中中需要显示的数据。这是一个对象数组，也可以只是数组，
     * 区别在于 纯数组前台就不需要用 columns绑定数据，会自动按照顺序去显示 ，
     * 而对象数组则需要使用 columns绑定数据才能正常显示。
     * 注意这个 data的名称可以由 ajaxDT 的 ajax.dataSrcDT 控制
     */
    List<T> data;
    /**
     * 可选。你可以定义一个错误来描述服务器出了问题后的友好提示
     */
    String error;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DTResult(int draw, long recordsTotal, long recordsFiltered, Object o, String error) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
        this.error = error;
    }

    public DTResult(int draw) {
        this.draw = draw;
    }

    @Override
    public String toString() {
        return "DTResult{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
