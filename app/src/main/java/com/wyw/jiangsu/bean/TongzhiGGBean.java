package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by wyw on 2018/1/23.
 */

public class TongzhiGGBean extends BaseArrayObjectEntity<TongzhiGGBean.DataListBean> {

    public static class DataListBean implements Serializable {
        /**
         * fstid : 2
         * fititle : 标题
         * FiDate : 2018/1/23 16:30:05
         * FIFWZH : 发文字号
         * fijb : 普通
         */

        private String fstid;       //主键ID
        private String fititle;     //通知标题
        private String FiDate;      //发布时间
        private String FIFWZH;      //发文字号
        private String fijb;        //通知级别
        private String ficontent;   //通知正文

        public String getFstid() {
            return fstid;
        }

        public void setFstid(String fstid) {
            this.fstid = fstid;
        }

        public String getFititle() {
            return fititle;
        }

        public void setFititle(String fititle) {
            this.fititle = fititle;
        }

        public String getFiDate() {
            return FiDate;
        }

        public void setFiDate(String FiDate) {
            this.FiDate = FiDate;
        }

        public String getFIFWZH() {
            return FIFWZH;
        }

        public void setFIFWZH(String FIFWZH) {
            this.FIFWZH = FIFWZH;
        }

        public String getFijb() {
            return fijb;
        }

        public void setFijb(String fijb) {
            this.fijb = fijb;
        }

        public String getFicontent() {
            return ficontent;
        }

        public void setFicontent(String ficontent) {
            this.ficontent = ficontent;
        }
    }
}
