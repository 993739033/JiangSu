package com.wyw.jiangsu.bean;

/**
 * Created by HUANG on 2017/9/13.
 */
public class RegisteMsgBean {

    /**
     * errorCode : 0
     * errorMsg : OK
     * data : {"result2":"32010100012","guid":"1"}
     */

    private int errorCode;
    private String errorMsg;
    private DataBean data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * result2 : 32010100012
         * guid : 1
         */

        private String result2;
        private String guid;

        public String getResult2() {
            return result2;
        }

        public void setResult2(String result2) {
            this.result2 = result2;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }
    }
}
