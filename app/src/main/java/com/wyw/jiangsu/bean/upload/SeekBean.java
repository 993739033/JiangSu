package com.wyw.jiangsu.bean.upload;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */

public class SeekBean implements Serializable{
    private int itmeType;

    private String itemTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItmeType() {
        return itmeType;
    }

    public void setItmeType(int itmeType) {
        this.itmeType = itmeType;
    }


}
