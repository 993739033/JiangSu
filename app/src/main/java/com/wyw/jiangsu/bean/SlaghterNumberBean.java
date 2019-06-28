package com.wyw.jiangsu.bean;

/**
 * Created by zhyzh on 2017/4/17.
 */

public class SlaghterNumberBean extends BaseArrayObjectEntity<SlaghterNumberBean.DataEntity>{

    /**
     * errorCode : 0
     * errorMsg : msg
     * data : {"result":"3200001704170001"}
     */
    public static class DataEntity {
        /**
         * result : 3200001704170001
         */

        private String result;

        public void setResult(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }
    }
}
