package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class AdressBean extends BaseArrayObjectEntity<AdressBean.DataListBean> {

    public static class DataListBean {
        /**
         * FStId : 1
         * FuParent : 0
         * FuStrId : 01
         * FuName : 江苏省
         * ucount : 17
         * FuOrder : 1
         * FuCode : 320000
         */

        private String FStId;       //自增ID
        private String FuParent;    //父节点
        private String FuStrId;     //行政等级
        private String FuName;      //行政名称
        private String ucount;      //每个行政下的所管的地方之和
        private String FuOrder;     //排序
        private String FuCode;      //行政编号

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFuParent() {
            return FuParent;
        }

        public void setFuParent(String FuParent) {
            this.FuParent = FuParent;
        }

        public String getFuStrId() {
            return FuStrId;
        }

        public void setFuStrId(String FuStrId) {
            this.FuStrId = FuStrId;
        }

        public String getFuName() {
            return FuName;
        }

        public void setFuName(String FuName) {
            this.FuName = FuName;
        }

        public String getUcount() {
            return ucount;
        }

        public void setUcount(String ucount) {
            this.ucount = ucount;
        }

        public String getFuOrder() {
            return FuOrder;
        }

        public void setFuOrder(String FuOrder) {
            this.FuOrder = FuOrder;
        }

        public String getFuCode() {
            return FuCode;
        }

        public void setFuCode(String FuCode) {
            this.FuCode = FuCode;
        }
    }
}
