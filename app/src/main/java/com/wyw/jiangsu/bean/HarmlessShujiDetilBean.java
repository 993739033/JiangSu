package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 */

public class HarmlessShujiDetilBean extends BaseObjectEntity<HarmlessShujiDetilBean.DataBean> {

    public static class DataBean {
        /**
         * FStId : 1
         * FSuserId : 101
         * dataList1 : [{"BSDWZL":"牛","BSDWSL":"23","CBS":"3.000"},{"BSDWZL":"猪","BSDWSL":"33","CBS":"123.000"}]
         * FSuserName : 玄武无害化处理厂
         * FSunitId : 4
         * FSunitUstrId : 010102
         * FSunitName : 玄武区
         * FSenterpriseId : 17
         * FSenterpriseName : 玄武无害化处理厂
         * FSaudit : 0
         * FSreviewer :
         * FSmemo1 :
         * FSmemo2 :
         * FSmemo3 :
         * FSDel : False
         * FSm2 :
         * FSm3 :
         * FSm4 :
         * FSm5 :
         * SJRID : 113
         * Fxzxm : test1
         * Fxxdz : 江苏省南京市玄武区玄武区街道111
         * FGlid : 1,2,3
         * ZLSL : 牛:23;猪:33
         * SWZL : 牛:3.000;猪:123.000
         */

        private String FStId;
        private String FSuserId;
        private String FSuserName;
        private String FSunitId;
        private String FSunitUstrId;
        private String FSunitName;
        private String FSenterpriseId;
        private String FSenterpriseName;
        private String FSaudit;
        private String FSreviewer;
        private String FSmemo1;
        private String FSmemo2;
        private String FSmemo3;
        private String FSDel;
        private String FSm2;
        private String FSm3;
        private String FSm4;
        private String FSm5;
        private String SJRID;
        private String Fxzxm;
        private String Fxxdz;
        private String FGlid;
        private String ZLSL;
        private String SWZL;
        private String CPH;
        private List<DataList1Bean> dataList1;

        public String getCPH() {
            return CPH;
        }

        public void setCPH(String CPH) {
            this.CPH = CPH;
        }

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFSuserId() {
            return FSuserId;
        }

        public void setFSuserId(String FSuserId) {
            this.FSuserId = FSuserId;
        }

        public String getFSuserName() {
            return FSuserName;
        }

        public void setFSuserName(String FSuserName) {
            this.FSuserName = FSuserName;
        }

        public String getFSunitId() {
            return FSunitId;
        }

        public void setFSunitId(String FSunitId) {
            this.FSunitId = FSunitId;
        }

        public String getFSunitUstrId() {
            return FSunitUstrId;
        }

        public void setFSunitUstrId(String FSunitUstrId) {
            this.FSunitUstrId = FSunitUstrId;
        }

        public String getFSunitName() {
            return FSunitName;
        }

        public void setFSunitName(String FSunitName) {
            this.FSunitName = FSunitName;
        }

        public String getFSenterpriseId() {
            return FSenterpriseId;
        }

        public void setFSenterpriseId(String FSenterpriseId) {
            this.FSenterpriseId = FSenterpriseId;
        }

        public String getFSenterpriseName() {
            return FSenterpriseName;
        }

        public void setFSenterpriseName(String FSenterpriseName) {
            this.FSenterpriseName = FSenterpriseName;
        }

        public String getFSaudit() {
            return FSaudit;
        }

        public void setFSaudit(String FSaudit) {
            this.FSaudit = FSaudit;
        }

        public String getFSreviewer() {
            return FSreviewer;
        }

        public void setFSreviewer(String FSreviewer) {
            this.FSreviewer = FSreviewer;
        }

        public String getFSmemo1() {
            return FSmemo1;
        }

        public void setFSmemo1(String FSmemo1) {
            this.FSmemo1 = FSmemo1;
        }

        public String getFSmemo2() {
            return FSmemo2;
        }

        public void setFSmemo2(String FSmemo2) {
            this.FSmemo2 = FSmemo2;
        }

        public String getFSmemo3() {
            return FSmemo3;
        }

        public void setFSmemo3(String FSmemo3) {
            this.FSmemo3 = FSmemo3;
        }

        public String getFSDel() {
            return FSDel;
        }

        public void setFSDel(String FSDel) {
            this.FSDel = FSDel;
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

        public String getFSm4() {
            return FSm4;
        }

        public void setFSm4(String FSm4) {
            this.FSm4 = FSm4;
        }

        public String getFSm5() {
            return FSm5;
        }

        public void setFSm5(String FSm5) {
            this.FSm5 = FSm5;
        }

        public String getSJRID() {
            return SJRID;
        }

        public void setSJRID(String SJRID) {
            this.SJRID = SJRID;
        }

        public String getFxzxm() {
            return Fxzxm;
        }

        public void setFxzxm(String Fxzxm) {
            this.Fxzxm = Fxzxm;
        }

        public String getFxxdz() {
            return Fxxdz;
        }

        public void setFxxdz(String Fxxdz) {
            this.Fxxdz = Fxxdz;
        }

        public String getFGlid() {
            return FGlid;
        }

        public void setFGlid(String FGlid) {
            this.FGlid = FGlid;
        }

        public String getZLSL() {
            return ZLSL;
        }

        public void setZLSL(String ZLSL) {
            this.ZLSL = ZLSL;
        }

        public String getSWZL() {
            return SWZL;
        }

        public void setSWZL(String SWZL) {
            this.SWZL = SWZL;
        }

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public static class DataList1Bean {
            /**
             * BSDWZL : 牛
             * BSDWSL : 23
             * CBS : 3.000
             */

            private String BSDWZL;
            private String BSDWSL;
            private String CBS;

            public String getBSDWZL() {
                return BSDWZL;
            }

            public void setBSDWZL(String BSDWZL) {
                this.BSDWZL = BSDWZL;
            }

            public String getBSDWSL() {
                return BSDWSL;
            }

            public void setBSDWSL(String BSDWSL) {
                this.BSDWSL = BSDWSL;
            }

            public String getCBS() {
                return CBS;
            }

            public void setCBS(String CBS) {
                this.CBS = CBS;
            }
        }
    }
}
