package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class WHHZhiPaiUploadBean {
    private String XXFSR;   //消息发送人
    private String SJR;   //收集人
    private int SJRID;   //收集人id
    private String CPH; //车牌号
    private String CPH1; //车牌号

    private List<WHHZhiPaiUploadBean.DataList1Bean> dataList;

    public String getXXFSR() {
        return XXFSR;
    }

    public void setXXFSR(String XXFSR) {
        this.XXFSR = XXFSR;
    }

    public String getSJR() {
        return SJR;
    }

    public void setSJR(String SJR) {
        this.SJR = SJR;
    }

    public int getSJRID() {
        return SJRID;
    }

    public void setSJRID(int SJRID) {
        this.SJRID = SJRID;
    }

    public String getCPH() {
        return CPH;
    }

    public void setCPH(String CPH) {
        this.CPH = CPH;
    }

    public String getCPH1() {
        return CPH1;
    }

    public void setCPH1(String CPH1) {
        this.CPH1 = CPH1;
    }

    public List<WHHZhiPaiUploadBean.DataList1Bean> getDataList() {
        return dataList;
    }

    public void setDataList(List<WHHZhiPaiUploadBean.DataList1Bean> dataList) {
        this.dataList = dataList;
    }

    public static class DataList1Bean {

        private int FBSGlid;       //病死动物id
        private int DWMCID;        //入库单关联id
        private String BSDWZL;     //病死动物种类
        private String BSDWSL;     //病死动物数量
        private String CBS;     //病死动物重量
        private String BSCQCZDZ;     //地址
        private String BSCQCZXM;     //收集点名称

        public int getFBSGlid() {
            return FBSGlid;
        }

        public void setFBSGlid(int FBSGlid) {
            this.FBSGlid = FBSGlid;
        }

        public int getDWMCID() {
            return DWMCID;
        }

        public void setDWMCID(int DWMCID) {
            this.DWMCID = DWMCID;
        }

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

        public String getBSCQCZDZ() {
            return BSCQCZDZ;
        }

        public void setBSCQCZDZ(String BSCQCZDZ) {
            this.BSCQCZDZ = BSCQCZDZ;
        }

        public String getBSCQCZXM() {
            return BSCQCZXM;
        }

        public void setBSCQCZXM(String BSCQCZXM) {
            this.BSCQCZXM = BSCQCZXM;
        }
    }
}
