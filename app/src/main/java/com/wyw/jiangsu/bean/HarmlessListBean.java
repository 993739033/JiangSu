package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 * 无害化所有消息列表的集合
 */

public class HarmlessListBean extends BaseArrayObjectEntity<HarmlessListBean.DataEntity> {
    public static class DataEntity {
        /**
         * FStId : 13
         * Fxzxm : ret
         * Fsbrq : 2016-12-29
         * Fxxdz : 江苏省南京市玄武区二胎
         * ZLSL : 仔猪:0
         * Fclfs : 自行处理
         */
        //
        private String FSuserId;
        private String FSuserName;
        private String FSunitId;
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
        private String FGlid;
        //

        private String FStId;
        private String Fxzxm;
        private String Fsbrq;
        private String Fxxdz;
        private String ZLSL;
        private String Fclfs;
        private String Fyssj;//移送时间
        private String FScTime;//分派收集时间
        private String SWZL;//病死动物种类重量
        private String SJRID;

        //驻场兽医列表
        private String FscTime;//收集时间
        private String CPH;//收集车辆
        private String SJR; //收集人
        private String FSunitUstrId;//没用

        //无害化处理场集中处理
        private String WHHCLZX;//无害化处理中心
        private String WHHCLZXID;//无害化处理中心id
        private String CLR;//处理人
        private String CLRQ;//处理日期
        private String sws;//死亡数
        private String cbs;//参保数
        private String zls;//重量
        private List<DataList1Bean> dataList1;
        //官方兽医确认时间
        private String GFDATE;
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getGFDATE() {
            return GFDATE;
        }

        public void setGFDATE(String GFDATE) {
            this.GFDATE = GFDATE;
        }

        public List<DataList1Bean> getDataList1() {
            return dataList1;
        }

        public void setDataList1(List<DataList1Bean> dataList1) {
            this.dataList1 = dataList1;
        }

        public String getFSunitUstrId() {
            return FSunitUstrId;
        }

        public void setFSunitUstrId(String FSunitUstrId) {
            this.FSunitUstrId = FSunitUstrId;
        }

        public String getSJR() {
            return SJR;
        }

        public void setSJR(String SJR) {
            this.SJR = SJR;
        }

        public String getCPH() {
            return CPH;
        }

        public void setCPH(String CPH) {
            this.CPH = CPH;
        }

        public String getFscTime() {
            return FscTime;
        }

        public void setFscTime(String fscTime) {
            FscTime = fscTime;
        }

        public String getWHHCLZX() {
            return WHHCLZX;
        }

        public void setWHHCLZX(String WHHCLZX) {
            this.WHHCLZX = WHHCLZX;
        }

        public String getWHHCLZXID() {
            return WHHCLZXID;
        }

        public void setWHHCLZXID(String WHHCLZXID) {
            this.WHHCLZXID = WHHCLZXID;
        }

        public String getCLR() {
            return CLR;
        }

        public void setCLR(String CLR) {
            this.CLR = CLR;
        }

        public String getCLRQ() {
            return CLRQ;
        }

        public void setCLRQ(String CLRQ) {
            this.CLRQ = CLRQ;
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

        public String getSWZL() {
            return SWZL;
        }

        public void setSWZL(String SWZL) {
            this.SWZL = SWZL;
        }

        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public String getSJRID() {
            return SJRID;
        }

        public void setSJRID(String SJRID) {
            this.SJRID = SJRID;
        }

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFxzxm() {
            return Fxzxm;
        }

        public void setFxzxm(String Fxzxm) {
            this.Fxzxm = Fxzxm;
        }

        public String getFsbrq() {
            return Fsbrq;
        }

        public void setFsbrq(String Fsbrq) {
            this.Fsbrq = Fsbrq;
        }

        public String getFxxdz() {
            return Fxxdz;
        }

        public void setFxxdz(String Fxxdz) {
            this.Fxxdz = Fxxdz;
        }

        public String getZLSL() {
            return ZLSL;
        }

        public void setZLSL(String ZLSL) {
            this.ZLSL = ZLSL;
        }

        public String getFclfs() {
            return Fclfs;
        }

        public void setFclfs(String fclfs) {
            Fclfs = fclfs;
        }

        public String getFyssj() {
            return Fyssj;
        }

        public void setFyssj(String fyssj) {
            Fyssj = fyssj;
        }


        //

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

        public String getFGlid() {
            return FGlid;
        }

        public void setFGlid(String FGlid) {
            this.FGlid = FGlid;
        }

        public static class DataList1Bean {
            private String dwzl;
            private String sws;
            private String cbs;
            private String zls;

            public String getDwzl() {
                return dwzl;
            }

            public void setDwzl(String dwzl) {
                this.dwzl = dwzl;
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
        }

    }

}
