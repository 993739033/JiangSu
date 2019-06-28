package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyw on 2016/11/1.
 * homeframgent 里面item的bean
 */

public class HomeBean extends BaseArrayObjectEntity<HomeBean.DataList> implements Serializable{
    public static class DataList implements Serializable{
        private String FStId;
        private String FmParent;
        private String FmName;
        private String FmOrder;
        private String img;
        private String imgs;
        private String TName;
        private String type;
        private String FSm1;
        private String FSm2;//为1时有角标,
        private String FSm3;//要查的视图
        private String number;//角标数目

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getFSm1() {
            return FSm1;
        }

        public void setFSm1(String FSm1) {
            this.FSm1 = FSm1;
        }

        public String getFSm2() {
            return FSm2;
        }

        public void setFSm2(String FSm2) {
            this.FSm2 = FSm2;
        }

        public String getFSm3() {
            return FSm3;
        }

        public void setFSm3(String FSm3) {
            this.FSm3 = FSm3;
        }

        public String getFsm1() {
            return FSm1;
        }

        public void setFsm1(String FSm1) {
            this.FSm1 = FSm1;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        /**
         * name : 编号
         * lx : ANumber
         */

        private List<LXBean> LX;
        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFmParent() {
            return FmParent;
        }

        public void setFmParent(String fmParent) {
            FmParent = fmParent;
        }

        public String getFmName() {
            return FmName;
        }

        public void setFmName(String fmName) {
            FmName = fmName;
        }

        public String getFmOrder() {
            return FmOrder;
        }

        public void setFmOrder(String fmOrder) {
            FmOrder = fmOrder;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<LXBean> getLX() {
            return LX;
        }

        public String getTName() {
            return TName;
        }

        public void setTName(String TName) {
            this.TName = TName;
        }

        public void setLX(List<LXBean> LX) {
            this.LX = LX;
        }
        public static class LXBean implements Serializable {
            private String name;//编号 货主
            private String lx;//type Anumber

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLx() {
                return lx;
            }

            public void setLx(String lx) {
                this.lx = lx;
            }
        }
    }
}