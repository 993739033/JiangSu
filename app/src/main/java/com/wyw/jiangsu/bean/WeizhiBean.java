package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Windows on 2017/11/28.
 */

public class WeizhiBean extends BaseObjectEntity<WeizhiBean.DataBean> {

    public static class DataBean implements Serializable {

        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
