package com.wyw.jiangsu.bean;

/**
 * 入场查验登记  查询的bean
 * Created by Administrator on 2017/4/17.
 */

public class RuChangChaXunBean extends BaseObjectEntity<RuChangChaXunBean.DataBean> {

    public static class DataBean {

        /**
         * eNum : 20
         * eSum : 20
         * eUnit : 头
         * eAnimal : 猪
         * PSheng : 江苏省
         * AQShiQy : 常州市
         * AQXianQy : 天宁区
         * eCOwner : 晏芳
         * eZhen : 青龙街道
         * eCun : 天宁区新希望养殖场
         * ePhone : 13638486279
         */


        private String result;
        private String eNum;
        private String eSum;
        private String eUnit;
        private String eAnimal;
        private String PSheng;
        private String AQShiQy;
        private String AQXianQy;
        private String eCOwner;
        private String eZhen;
        private String eCun;
        private String ePhone;

        /**
         * errorCode : 0
         * errorMsg : msg
         * data : 未查询到此检疫证信息！
         */

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        private String data;

        public String getENum() {
            return eNum;
        }

        public void setENum(String eNum) {
            this.eNum = eNum;
        }

        public String getESum() {
            return eSum;
        }

        public void setESum(String eSum) {
            this.eSum = eSum;
        }

        public String getEUnit() {
            return eUnit;
        }

        public void setEUnit(String eUnit) {
            this.eUnit = eUnit;
        }

        public String getEAnimal() {
            return eAnimal;
        }

        public void setEAnimal(String eAnimal) {
            this.eAnimal = eAnimal;
        }

        public String getPSheng() {
            return PSheng;
        }

        public void setPSheng(String PSheng) {
            this.PSheng = PSheng;
        }

        public String getAQShiQy() {
            return AQShiQy;
        }

        public void setAQShiQy(String AQShiQy) {
            this.AQShiQy = AQShiQy;
        }

        public String getAQXianQy() {
            return AQXianQy;
        }

        public void setAQXianQy(String AQXianQy) {
            this.AQXianQy = AQXianQy;
        }

        public String getECOwner() {
            return eCOwner;
        }

        public void setECOwner(String eCOwner) {
            this.eCOwner = eCOwner;
        }

        public String getEZhen() {
            return eZhen;
        }

        public void setEZhen(String eZhen) {
            this.eZhen = eZhen;
        }

        public String getECun() {
            return eCun;
        }

        public void setECun(String eCun) {
            this.eCun = eCun;
        }

        public String getEPhone() {
            return ePhone;
        }

        public void setEPhone(String ePhone) {
            this.ePhone = ePhone;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
