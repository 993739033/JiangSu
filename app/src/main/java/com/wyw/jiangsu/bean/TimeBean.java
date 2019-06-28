package com.wyw.jiangsu.bean;

/**
 * Created by wyw on 2016/12/27.
 */

public class TimeBean extends BaseObjectEntity<TimeBean.Data>{

    public static class Data{
        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
