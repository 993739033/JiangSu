package com.wyw.jiangsu.bean;

import com.google.gson.annotations.SerializedName;
import com.mob.tools.gui.Scrollable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/28.
 */

public class QuarantineProcessNotifListBeanDetil extends BaseObjectEntity<QuarantineProcessNotifListBeanDetil.DataBean> {

    /**
     * data : {"FStId":"33","jyclcard":"0000000001","animalsort":"猪","QDWDanWei":"头","legalnum":"2","shippername":"晏芳","farmsnme":"天宁区新希望养殖场养殖场","teltphone":"13638486279","myuse":"AH_AnimalOrigin"}
     */

    public static class DataBean implements Serializable {
        /**
         * FStId : 33
         * jyclcard : 0000000001
         * animalsort : 猪
         * QDWDanWei : 头
         * legalnum : 2
         * shippername : 晏芳
         * farmsnme : 天宁区新希望养殖场养殖场
         * teltphone : 13638486279
         * myuse : AH_AnimalOrigin
         */

        private String FStId;
        private String jyclcard;
        private String animalsort;
        private String QDWDanWei;
        private String legalnum;
        private String shippername;
        private String farmsnme;
        private String teltphone;
        private String myuse;

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getJyclcard() {
            return jyclcard;
        }

        public void setJyclcard(String jyclcard) {
            this.jyclcard = jyclcard;
        }

        public String getAnimalsort() {
            return animalsort;
        }

        public void setAnimalsort(String animalsort) {
            this.animalsort = animalsort;
        }

        public String getQDWDanWei() {
            return QDWDanWei;
        }

        public void setQDWDanWei(String QDWDanWei) {
            this.QDWDanWei = QDWDanWei;
        }

        public String getLegalnum() {
            return legalnum;
        }

        public void setLegalnum(String legalnum) {
            this.legalnum = legalnum;
        }

        public String getShippername() {
            return shippername;
        }

        public void setShippername(String shippername) {
            this.shippername = shippername;
        }

        public String getFarmsnme() {
            return farmsnme;
        }

        public void setFarmsnme(String farmsnme) {
            this.farmsnme = farmsnme;
        }

        public String getTeltphone() {
            return teltphone;
        }

        public void setTeltphone(String teltphone) {
            this.teltphone = teltphone;
        }

        public String getMyuse() {
            return myuse;
        }

        public void setMyuse(String myuse) {
            this.myuse = myuse;
        }
    }
}
