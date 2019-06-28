package com.wyw.jiangsu.bean;

import com.wyw.jiangsu.view.recyclerview.entity.MultiItemEntity;

/**
 * Created by wyw on 2016/12/22.
 */

public class HarmlessCollectionMessageAcceptListBean extends BaseArrayObjectEntity<HarmlessCollectionMessageAcceptListBean.DataEntity> {
    public static class DataEntity extends MultiItemEntity {
        //类型 具体值在HarmlessCollectionMessageAcceptAdapter
//        private int type;
        //暂存点
        private String a1;
        private String a2;
        private String a3;
        private String a4;
        private String a5;

        //无害化处理厂
        private String b1;
        private String b2;
        private String b3;
        private String b4;
        private String b5;
        private String b6;


        public String getA1() {
            return a1;
        }

        public void setA1(String a1) {
            this.a1 = a1;
        }

        public String getA2() {
            return a2;
        }

        public void setA2(String a2) {
            this.a2 = a2;
        }

        public String getA3() {
            return a3;
        }

        public void setA3(String a3) {
            this.a3 = a3;
        }

        public String getA4() {
            return a4;
        }

        public void setA4(String a4) {
            this.a4 = a4;
        }

        public String getA5() {
            return a5;
        }

        public void setA5(String a5) {
            this.a5 = a5;
        }

        public String getB1() {
            return b1;
        }

        public void setB1(String b1) {
            this.b1 = b1;
        }

        public String getB2() {
            return b2;
        }

        public void setB2(String b2) {
            this.b2 = b2;
        }

        public String getB3() {
            return b3;
        }

        public void setB3(String b3) {
            this.b3 = b3;
        }

        public String getB4() {
            return b4;
        }

        public void setB4(String b4) {
            this.b4 = b4;
        }

        public String getB5() {
            return b5;
        }

        public void setB5(String b5) {
            this.b5 = b5;
        }

        public String getB6() {
            return b6;
        }

        public void setB6(String b6) {
            this.b6 = b6;
        }
    }
}
