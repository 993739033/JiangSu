package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * 无害化入场确认bean
 * Created by Administrator on 2017/3/30.
 */

public class V_APP_ZCSYXXQueryBean extends BaseArrayObjectEntity<V_APP_ZCSYXXQueryBean.DataListBean>{

    public static class DataListBean {
        /**
         * FStId : 16
         * FScTime : 2017-03-29
         * CPH : 00002
         * SJR : 玄武无害化处理厂收集人员
         * FSunitUstrId : 010102
         */

        private String FStId;
        private String FScTime;         //收集时间
        private String CPH;             //收集车辆
        private String SJR;             //收集人
        private String FSunitUstrId;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public String getCPH() {
            return CPH;
        }

        public void setCPH(String CPH) {
            this.CPH = CPH;
        }

        public String getSJR() {
            return SJR;
        }

        public void setSJR(String SJR) {
            this.SJR = SJR;
        }

        public String getFSunitUstrId() {
            return FSunitUstrId;
        }

        public void setFSunitUstrId(String FSunitUstrId) {
            this.FSunitUstrId = FSunitUstrId;
        }
    }
}
