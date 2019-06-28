package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/16.
 */

public class WHHZhiPaiChooseBean extends BaseArrayObjectEntity<WHHZhiPaiChooseBean.DataListBean> implements Serializable {

    public static class DataListBean implements Serializable {
        /**
         * FStId : 6
         * FWhhGlid : 22
         * FWhhName : 测试
         * FBSGlid : 0
         * FZcdGlid : 6
         * YZCCZMC : test1
         * LXDZ : 江苏省南京市玄武区玄武区街道111
         * LXDH :
         * FBsdwType : 猪
         * FBsdwNum : 2
         * FBsdwWeight : 0.000
         */

        private String FStId;           //入库单关联id
        private String FWhhGlid;
        private String FWhhName;
        private String FBSGlid;
        private String FZcdGlid;
        private String YZCCZMC;         //收集点名称
        private String LXDZ;            //地址
        private String LXDH;
        private String FBsdwType;       //病死动物种类
        private String FBsdwNum;        //病死动物数量
        private String FBsdwWeight;     //病死动物重量(KG)
        private boolean Cheak;

        public boolean isCheak() {
            return Cheak;
        }

        public void setCheak(boolean cheak) {
            Cheak = cheak;
        }

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
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

        public String getFBSGlid() {
            return FBSGlid;
        }

        public void setFBSGlid(String FBSGlid) {
            this.FBSGlid = FBSGlid;
        }

        public String getFZcdGlid() {
            return FZcdGlid;
        }

        public void setFZcdGlid(String FZcdGlid) {
            this.FZcdGlid = FZcdGlid;
        }

        public String getYZCCZMC() {
            return YZCCZMC;
        }

        public void setYZCCZMC(String YZCCZMC) {
            this.YZCCZMC = YZCCZMC;
        }

        public String getLXDZ() {
            return LXDZ;
        }

        public void setLXDZ(String LXDZ) {
            this.LXDZ = LXDZ;
        }

        public String getLXDH() {
            return LXDH;
        }

        public void setLXDH(String LXDH) {
            this.LXDH = LXDH;
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

        public String getFBsdwWeight() {
            return FBsdwWeight;
        }

        public void setFBsdwWeight(String FBsdwWeight) {
            this.FBsdwWeight = FBsdwWeight;
        }
    }
}
