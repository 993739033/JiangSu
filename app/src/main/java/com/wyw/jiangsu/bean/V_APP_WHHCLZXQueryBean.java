package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * 无害化集中处理bean
 * Created by Administrator on 2017/3/30.
 */

public class V_APP_WHHCLZXQueryBean extends BaseArrayObjectEntity<V_APP_WHHCLZXQueryBean.DataListBean> {


    public static class DataListBean implements Serializable {
        /**
         * FStId : 6
         * WHHCLZXID : 17
         * WHHCLZX : 玄武无害化处理厂
         * CLRQ : 2017-03-17
         * CLR : 玄武无害化处理厂
         * sws : 仔猪:34
         * cbs : 仔猪:0
         * zls : 仔猪:2833.000
         * FSenterpriseId : 6
         */
        private String FStId;
        private String WHHCLZXID;
        private String WHHCLZX;         //无害化处理厂名字
        private String CLRQ;            //处理日期
        private String CLR;             //处理人
        private String sws;             //死亡数
        private String cbs;             //病死动物类型
        private String zls;             //重量
        private String FSenterpriseId;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getWHHCLZXID() {
            return WHHCLZXID;
        }

        public void setWHHCLZXID(String WHHCLZXID) {
            this.WHHCLZXID = WHHCLZXID;
        }

        public String getWHHCLZX() {
            return WHHCLZX;
        }

        public void setWHHCLZX(String WHHCLZX) {
            this.WHHCLZX = WHHCLZX;
        }

        public String getCLRQ() {
            return CLRQ;
        }

        public void setCLRQ(String CLRQ) {
            this.CLRQ = CLRQ;
        }

        public String getCLR() {
            return CLR;
        }

        public void setCLR(String CLR) {
            this.CLR = CLR;
        }

        public String getSws() {
            return sws;
        }

        public void setSws(String sws) {
            this.sws = sws;
        }

        public String getCbs() {
            return cbs;
        }

        public void setCbs(String cbs) {
            this.cbs = cbs;
        }

        public String getZls() {
            return zls;
        }

        public void setZls(String zls) {
            this.zls = zls;
        }

        public String getFSenterpriseId() {
            return FSenterpriseId;
        }

        public void setFSenterpriseId(String FSenterpriseId) {
            this.FSenterpriseId = FSenterpriseId;
        }
    }
}
