package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by HUANG on 2017/8/3.
 */
public class CommonSuperBottomBean implements Serializable{
    private String bt_sign_second;
    private String bt_sign_first;

    public String getBt_sign_first() {
        return bt_sign_first;
    }

    public void setBt_sign_first(String bt_sign_first) {
        this.bt_sign_first = bt_sign_first;
    }

    public String getBt_sign_second() {
        return bt_sign_second;
    }
    public void setBt_sign_second(String bt_sign_second) {
        this.bt_sign_second = bt_sign_second;
    }
}
