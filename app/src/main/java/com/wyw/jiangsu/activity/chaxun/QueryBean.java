package com.wyw.jiangsu.activity.chaxun;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/24.
 */

public class QueryBean implements Serializable {

    private int itemType;
    private String itemTitle;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
