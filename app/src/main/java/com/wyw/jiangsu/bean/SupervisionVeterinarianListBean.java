package com.wyw.jiangsu.bean;

/**
 * Created by wyw on 2016/12/29.
 */

public class SupervisionVeterinarianListBean extends BaseArrayObjectEntity<SupervisionVeterinarianListBean.DataListBean>{

    public static class DataListBean {
        private String FStId;
        private String FuName;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFuName() {
            return FuName;
        }

        public void setFuName(String FuName) {
            this.FuName = FuName;
        }
    }
}
