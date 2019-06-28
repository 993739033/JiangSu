package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by wyw on 2016/12/27.
 */

public class UserDetilBean extends BaseObjectEntity<UserDetilBean.Data> {
    /**
     * FFarmName : 上等人共同体
     * FyzcType : 种用
     * FStId : 2
     * FherdsScale : 10
     * FSmemo1 : 头
     * FCityAdd : 江苏省南京市玄武区二胎
     * FPLegalCardId : 512548966563225652
     * YKTH : 1564
     */
    public static class Data implements Serializable {
        private String FFarmName;//畜主姓名
        private String lxdh;//联系电话
        private String FyzcType;//养殖场类型
        private String FStId;//畜主id 上传用
        private String FCityAdd;//地址
        private String FPLegalCardId;//身份证
        private String YKTH;//一卡通号
        //现存栏量
        private String FherdsScale;//数量
        private String FSmemo1;//单位
        private String FPhone;//联系电话
        private String img;
        private String FLegalPerson;
        public String getFLegalPerson() {
            return FLegalPerson;
        }

        public void setFLegalPerson(String xm) {
            this.FLegalPerson = xm;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getFPhone() {
            return FPhone;
        }

        public void setFPhone(String FPhone) {
            this.FPhone = FPhone;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getFFarmName() {
            return FFarmName;
        }

        public void setFFarmName(String FFarmName) {
            this.FFarmName = FFarmName;
        }

        public String getFyzcType() {
            return FyzcType;
        }

        public void setFyzcType(String FyzcType) {
            this.FyzcType = FyzcType;
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

        public String getFPLegalCardId() {
            return FPLegalCardId;
        }

        public void setFPLegalCardId(String FPLegalCardId) {
            this.FPLegalCardId = FPLegalCardId;
        }

        public String getYKTH() {
            return YKTH;
        }

        public void setYKTH(String YKTH) {
            this.YKTH = YKTH;
        }

        public String getFherdsScale() {
            return FherdsScale;
        }

        public void setFherdsScale(String fherdsScale) {
            FherdsScale = fherdsScale;
        }

        public String getFSmemo1() {
            return FSmemo1;
        }

        public void setFSmemo1(String FSmemo1) {
            this.FSmemo1 = FSmemo1;
        }
    }
}

