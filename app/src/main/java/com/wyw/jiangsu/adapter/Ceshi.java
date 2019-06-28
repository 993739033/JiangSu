package com.wyw.jiangsu.adapter;

import com.google.gson.annotations.SerializedName;
import com.wyw.jiangsu.bean.BaseObjectEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/7.
 */

public class Ceshi extends BaseObjectEntity<Ceshi> implements Serializable {


    /**
     * TableName : HT_BSDWSWSB
     * title : 无害化申报
     * content : 您有待处理的无害化申报信息
     */
    @SerializedName("TableName")
    private String TableName;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
