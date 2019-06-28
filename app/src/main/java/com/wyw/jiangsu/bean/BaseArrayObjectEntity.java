package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by wyw on 2016/10/31.
 */

public class BaseArrayObjectEntity<T> extends BaseMsg {
    private List<T> dataList;

    public List<T> getData() {
        return dataList;
    }

    public void setData(List<T> data) {
        this.dataList = data;
    }
}
