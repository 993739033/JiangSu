package com.wyw.jiangsu.bean;

import com.wyw.jiangsu.bean.local.CheckContentEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mnkj004 on 2017/9/7.
 */

public class TransforBean implements Serializable{
    private List<CheckContentEntity> data;

    public TransforBean(List<CheckContentEntity> data) {
        this.data = data;
    }

    public List<CheckContentEntity> getData() {
        return data;
    }

    public void setData(List<CheckContentEntity> data) {
        this.data = data;
    }
}
