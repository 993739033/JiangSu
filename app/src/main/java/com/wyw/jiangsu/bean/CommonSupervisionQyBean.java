package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by wyw on 2018/1/25.
 */

public class CommonSupervisionQyBean extends BaseArrayObjectEntity<CommonSupervisionQyBean.DataListBean> {

    public static class DataListBean implements Serializable {
        /**
         * FStId : 3
         * FFarmName : 高淳区华晨养殖场
         * FCityAdd : 江苏省南京市高淳区东坝镇沛桥村
         * FLegalPerson : 江贤稳
         * FPhone : 13912911058
         * FCategory : 生猪
         * clsl : 7500
         * FLongitude : 119.025539
         * FLatitude : 31.32265
         */

        private String FStId;
        private String FFarmName;
        private String FCityAdd;
        private String FLegalPerson;
        private String FPhone;
        private String FCategory;
        private String clsl;
        private String FLongitude;
        private String FLatitude;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFFarmName() {
            return FFarmName;
        }

        public void setFFarmName(String FFarmName) {
            this.FFarmName = FFarmName;
        }

        public String getFCityAdd() {
            return FCityAdd;
        }

        public void setFCityAdd(String FCityAdd) {
            this.FCityAdd = FCityAdd;
        }

        public String getFLegalPerson() {
            return FLegalPerson;
        }

        public void setFLegalPerson(String FLegalPerson) {
            this.FLegalPerson = FLegalPerson;
        }

        public String getFPhone() {
            return FPhone;
        }

        public void setFPhone(String FPhone) {
            this.FPhone = FPhone;
        }

        public String getFCategory() {
            return FCategory;
        }

        public void setFCategory(String FCategory) {
            this.FCategory = FCategory;
        }

        public String getClsl() {
            return clsl;
        }

        public void setClsl(String clsl) {
            this.clsl = clsl;
        }

        public String getFLongitude() {
            return FLongitude;
        }

        public void setFLongitude(String FLongitude) {
            this.FLongitude = FLongitude;
        }

        public String getFLatitude() {
            return FLatitude;
        }

        public void setFLatitude(String FLatitude) {
            this.FLatitude = FLatitude;
        }
    }
}
