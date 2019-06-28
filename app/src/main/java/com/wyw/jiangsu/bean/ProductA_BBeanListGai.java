package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/6.
 */

public class ProductA_BBeanListGai extends BaseArrayObjectEntity<ProductA_BBeanListGai.DataListBean> {

    public static class DataListBean implements Serializable {
        //表名称：V_AH_AEmbryoQuarantine

        private String id;
        /// 动物检疫合格证明编号
        private String code;
        /// 生产单位名称地址
        private String scdz;
        /// 地址
        private String dddz;
        /// 目的地
        private String FhAdress;
        /// 生产单位名称
        private String SCDWMC;
        /// 到达【省】
        private String rdddzxx;
        /// 到达【市】;
        private String rdddzxx1;
        /// 到达【县】
        private String rdddzxx2;
        /// 到达【乡】
        private String rdddzxx3;
        /// 养殖场/产地
        private String farmsnme;
        /// 动物种类
        private String animalsort;
        /// 数量
        private String animalnum;
        /// 单位
        private String QCPDanWei;

        //产品名称
        private String onimalsort;

        /// 自增ID
        private String FStId;
        /// Guid
        private String FSguidId;
        /// 系统默认记录日期
        private String FScTime;
        /// 系统默认记录日期—修改时更新
        private String FSuTime;
        /// 用户id
        private int FSuserId;
        /// 用户名
        private String FSuserName;
        /// 行政id
        private String FSunitId;
        /// 行政等级id如01、001、0001
        private String FSunitUstrId;
        /// 行政名称
        private String FSunitName;
        /// 企业/单位id;
        private String FSenterpriseId;
        /// 企业/单位名称
        private String FSenterpriseName;
        /// 审核（0未审核，1审核）
        private int FSaudit;
        /// 审核人
        private String FSreviewer;
        /// 预留备注1
        private String FSmemo1;
        /// 预留备注2
        private String FSmemo2;
        /// 预留备注3
        private String FSmemo3;
        /// 备注1
        private String FSm1;
        /// 备注2
        private String FSm2;
        private String FSm3;
        /// 备注4
        private String FSm4;
        /// 备注5
        private String FSm5;
        /// 备注5
        private String FSDel;

        //新增货主
        private String shippername;

        //新增货主电话号码
        private String teltphone;

        public String getShippername() {
            return shippername;
        }

        public void setShippername(String shippername) {
            this.shippername = shippername;
        }

        public String getTeltphone() {
            return teltphone;
        }

        public void setTeltphone(String teltphone) {
            this.teltphone = teltphone;
        }

        public String getOnimalsort() {
            return onimalsort;
        }

        public void setOnimalsort(String onimalsort) {
            this.onimalsort = onimalsort;
        }


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScdz() {
            return scdz;
        }

        public void setScdz(String scdz) {
            this.scdz = scdz;
        }


        public String getDddz() {
            return dddz;
        }

        public void setDddz(String dddz) {
            this.dddz = dddz;
        }

        public String getFhAdress() {
            return FhAdress;
        }

        public void setFhAdress(String fhAdress) {
            FhAdress = fhAdress;
        }

        public String getSCDWMC() {
            return SCDWMC;
        }

        public void setSCDWMC(String SCDWMC) {
            this.SCDWMC = SCDWMC;
        }

        public String getRdddzxx() {
            return rdddzxx;
        }

        public void setRdddzxx(String rdddzxx) {
            this.rdddzxx = rdddzxx;
        }

        public String getRdddzxx1() {
            return rdddzxx1;
        }

        public void setRdddzxx1(String rdddzxx1) {
            this.rdddzxx1 = rdddzxx1;
        }

        public String getRdddzxx2() {
            return rdddzxx2;
        }

        public void setRdddzxx2(String rdddzxx2) {
            this.rdddzxx2 = rdddzxx2;
        }

        public String getRdddzxx3() {
            return rdddzxx3;
        }

        public void setRdddzxx3(String rdddzxx3) {
            this.rdddzxx3 = rdddzxx3;
        }

        public String getFarmsnme() {
            return farmsnme;
        }

        public void setFarmsnme(String farmsnme) {
            this.farmsnme = farmsnme;
        }

        public String getAnimalsort() {
            return animalsort;
        }

        public void setAnimalsort(String animalsort) {
            this.animalsort = animalsort;
        }

        public String getAnimalnum() {
            return animalnum;
        }

        public void setAnimalnum(String animalnum) {
            this.animalnum = animalnum;
        }

        public String getQCPDanWei() {
            return QCPDanWei;
        }

        public void setQCPDanWei(String QCPDanWei) {
            this.QCPDanWei = QCPDanWei;
        }

        public String getFStId() {
            return FStId;
        }

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public String getFSguidId() {
            return FSguidId;
        }

        public void setFSguidId(String FSguidId) {
            this.FSguidId = FSguidId;
        }

        public String getFScTime() {
            return FScTime;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public String getFSuTime() {
            return FSuTime;
        }

        public void setFSuTime(String FSuTime) {
            this.FSuTime = FSuTime;
        }

        public int getFSuserId() {
            return FSuserId;
        }

        public void setFSuserId(int FSuserId) {
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

        public int getFSaudit() {
            return FSaudit;
        }

        public void setFSaudit(int FSaudit) {
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

        public String getFSmemo3() {
            return FSmemo3;
        }

        public void setFSmemo3(String FSmemo3) {
            this.FSmemo3 = FSmemo3;
        }

        public String getFSmemo2() {
            return FSmemo2;
        }

        public void setFSmemo2(String FSmemo2) {
            this.FSmemo2 = FSmemo2;
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

        public String getFSDel() {
            return FSDel;
        }

        public void setFSDel(String FSDel) {
            this.FSDel = FSDel;
        }


    }
}
