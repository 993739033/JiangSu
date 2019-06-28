package com.wyw.jiangsu.bean;

/**
 * Created by wyw on 2016/10/31.
 */

public class BaseObjectEntity<T> extends BaseMsg {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString()+"    BaseObjectEntity{" +
                "data=" + data +
                '}';
    }
}
