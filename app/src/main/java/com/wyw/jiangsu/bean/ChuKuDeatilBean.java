package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mnkj004 on 2017/8/29.
 */

public class ChuKuDeatilBean extends BaseObjectEntity<ChuKuDeatilBean.DataBean>  {

    /**
     * errorCode : 0
     * errorMsg : msg
     * data : {"FStId":"1","FckdNumber":"20170720001","dataList1":[{"FBsdwWeight":"2.000","FBsdwType":"鹿","FBsdwNum":"3"},{"FBsdwWeight":"2.000","FBsdwType":"骆驼","FBsdwNum":"2"}],"FZcdGlid":"6","FZcdName":"test1","FckDate":"2017-07-20","FWhhGlid":"22","FWhhName":"测试","FZcddz":"江苏省南京市玄武区玄武区街道111"}
     */

    public static class DataBean implements Serializable{
        /**
         * FStId : 1
         * FckdNumber : 20170720001
         * dataList1 : [{"FBsdwWeight":"2.000","FBsdwType":"鹿","FBsdwNum":"3"},{"FBsdwWeight":"2.000","FBsdwType":"骆驼","FBsdwNum":"2"}]
         * FZcdGlid : 6
         * FZcdName : test1
         * FckDate : 2017-07-20
         * FWhhGlid : 22
         * FWhhName : 测试
         * FZcddz : 江苏省南京市玄武区玄武区街道111
         */

        private String FStId;
        private String FckdNumber;
        private String FZcdGlid;
        private String FZcdName;
        private String FckDate;
        private String FWhhGlid;
        private String FWhhName;
        private String FZcddz;
        private List<DataList1Bean> dataList1;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFckdNumber() {
            return FckdNumber;
        }

        public void setFckdNumber(String FckdNumber) {
            this.FckdNumber = FckdNumber;
        }

        public String getFZcdGlid() {
            return FZcdGlid;
        }

        public void setFZcdGlid(String FZcdGlid) {
            this.FZcdGlid = FZcdGlid;
        }

        public String getFZcdName() {
            return FZcdName;
        }

        public void setFZcdName(String FZcdName) {
            this.FZcdName = FZcdName;
        }

        public String getFckDate() {
            return FckDate;
        }

        public void setFckDate(String FckDate) {
            this.FckDate = FckDate;
        }

        public String getFWhhGlid() {
            return FWhhGlid;
        }

        public void setFWhhGlid(String FWhhGlid) {
            this.FWhhGlid = FWhhGlid;
        }

        public String getFWhhName() {
            return FWhhName;
        }

        public void setFWhhName(String FWhhName) {
            this.FWhhName = FWhhName;
        }

        public String getFZcddz() {
            return FZcddz;
        }

        public void setFZcddz(String FZcddz) {
            this.FZcddz = FZcddz;
        }

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public static class DataList1Bean implements Serializable{
            /**
             * FBsdwWeight : 2.000
             * FBsdwType : 鹿
             * FBsdwNum : 3
             */

            private String FBsdwWeight;
            private String FBsdwType;
            private String FBsdwNum;

            public String getFBsdwWeight() {
                return FBsdwWeight;
            }

            public void setFBsdwWeight(String FBsdwWeight) {
                this.FBsdwWeight = FBsdwWeight;
            }

            public String getFBsdwType() {
                return FBsdwType;
            }

            public void setFBsdwType(String FBsdwType) {
                this.FBsdwType = FBsdwType;
            }

            public String getFBsdwNum() {
                return FBsdwNum;
            }

            public void setFBsdwNum(String FBsdwNum) {
                this.FBsdwNum = FBsdwNum;
            }
        }
    }
}


