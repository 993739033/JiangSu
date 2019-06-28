package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by wyw on 2017/6/21.
 */

public class CommonSupervisionBean extends BaseObjectEntity<CommonSupervisionBean.Data> {
    public static class Data implements Serializable{
        //养殖场名称
        private String FFarmName;
        //自增ID
        private String FStId;
        //地址
        private String FCityAdd;
        //法定代表人/负责人
        private String FLegalPerson;
        //电话
        private String FPhone;
        //养殖动物种类
        private String FCategory;
        //存栏动物数量
        private String clsl;
        //经度
        private String FLongitude;
        //纬度
        private String FLatitude;

        public String getFFarmName() {
            return FFarmName;
        }

        public void setFFarmName(String FFarmName) {
            this.FFarmName = FFarmName;
        }

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
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
