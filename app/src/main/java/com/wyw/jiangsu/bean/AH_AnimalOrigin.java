package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 * 动物产地检疫工作记录单
 */

public class AH_AnimalOrigin extends BaseArrayObjectEntity<AH_AnimalOrigin.DataListBean>{


    public static class DataListBean implements Serializable{
        /**
         * FStId : 21
         * FSguidId : f2c41315-1346-4033-b350-ad9099f28207
         * FScTime : 2017-03-09
         * FSuTime : 2017-03-09
         * FSuserId : 99
         * FSuserName : 余惠清
         * FSunitId : 2
         * FSunitUstrId : 0101
         * FSunitName : 南京市
         * FSenterpriseId : 7
         * FSenterpriseName : 南京市动物卫生监督所
         * FSaudit : 0
         * FSreviewer :
         * FSmemo1 :
         * FSmemo2 :
         * FSmemo3 :
         * FSDel : False
         * FSm1 : 票证已保存
         * FSm2 :
         * FSm3 :
         * FSm4 :
         * FSm5 :
         * id : 24
         * recordNo : 苏(320100)1703091128243410
         * supervisename : 南京市动物卫生监督所
         * inspectiontime : 2017-03-09
         * quarantinetime : 2017-03-09
         * shippername : 孙尚连
         * teltphone : 15021698745
         * idcardnum :
         * farmsnme : 玄武区养殖场养殖场
         * quarantineaddress : 玄武区养殖场
         * animalsort : 猪
         * myuse : 屠宰
         * animalsources : 家畜家禽
         * domesticatedid :
         * catchid :
         * animalnum : 72
         * startaddress : 江苏省
         * startaddress1 : 南京市
         * startaddress2 : 玄武区
         * startaddress3 : 玄武区街道
         * startaddress4 : 玄武区养殖场
         * startaddress5 : 养殖场
         * toolid : 苏A66666
         * qzmy : 是
         * recordrule : 符合
         * logorule : 符合
         * outbreak : 无
         * clinical : 合格
         * eridemicarea : 否
         * other :
         * laboratory : 不需要
         * situation :
         * code : 3201013232
         * jyclcard :
         * general : 实验室检测
         * harmless : 焚烧
         * harmlesnum : 0
         * legal :
         * otherObj :
         * legalnum : 0
         * otherNum : 0
         * vetname : 余惠清
         * quarantinerule : 是
         * inspId :
         * remark :
         * qualifiedNum : 0
         * runit : 法定检疫对象
         * rhzqz : 孙尚连
         * rhzdate : 2017-03-09
         * rqydzxx : 江苏省南京市玄武区玄武区街道乡(镇)玄武区养殖场
         * rdddzxxs : 江苏省南京市六合区大厂乡(镇)南京市大厂欣乐食品中心定点屠宰场
         * rdddzxx : 江苏省
         * rdddzxx1 : 南京市
         * rdddzxx2 : 六合区
         * rdddzxx3 : 大厂
         * rdddzxx4 : 南京市大厂欣乐食品中心定点屠宰场
         * rdddzxx5 : 屠宰场
         * Gftime : 2017-03-09
         * QDWNumber :
         * harmlesnums : 0
         * QDWDanWei : 头
         * dr : 10
         * dr1 : 11
         * dr2 : 11
         * dr3 : 11
         */

        private String FStId;
        private String FSguidId;
        private String FScTime;
        private String FSuTime;
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
        private String FSm1;
        private String FSm2;
        private String FSm3;
        private String FSm4;
        private String FSm5;
        private String id;
        private String recordNo;
        private String supervisename;
        private String inspectiontime;
        private String quarantinetime;
        private String shippername;
        private String teltphone;
        private String idcardnum;
        private String farmsnme;
        private String quarantineaddress;
        private String animalsort;
        private String myuse;
        private String animalsources;
        private String domesticatedid;
        private String catchid;
        private int animalnum;
        private String startaddress;
        private String startaddress1;
        private String startaddress2;
        private String startaddress3;
        private String startaddress4;
        private String startaddress5;
        private String toolid;
        private String qzmy;
        private String recordrule;
        private String logorule;
        private String outbreak;
        private String clinical;
        private String eridemicarea;
        private String other;
        private String laboratory;
        private String situation;
        private String code;
        private String jyclcard;
        private String general;
        private String harmless;
        private int harmlesnum;
        private String legal;
        private String otherObj;
        private int legalnum;
        private int otherNum;
        private String vetname;
        private String quarantinerule;
        private String inspId;
        private String remark;
        private String qualifiedNum;
        private String runit;
        private String rhzqz;
        private String rhzdate;
        private String rqydzxx;
        private String rdddzxxs;
        private String rdddzxx;
        private String rdddzxx1;
        private String rdddzxx2;
        private String rdddzxx3;
        private String rdddzxx4;
        private String rdddzxx5;
        private String Gftime;
        private String QDWNumber;
        private int harmlesnums;
        private String QDWDanWei;
        private String dr;
        private String dr1;
        private String dr2;
        private String dr3;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRecordNo() {
            return recordNo;
        }

        public void setRecordNo(String recordNo) {
            this.recordNo = recordNo;
        }

        public String getSupervisename() {
            return supervisename;
        }

        public void setSupervisename(String supervisename) {
            this.supervisename = supervisename;
        }

        public String getInspectiontime() {
            return inspectiontime;
        }

        public void setInspectiontime(String inspectiontime) {
            this.inspectiontime = inspectiontime;
        }

        public String getQuarantinetime() {
            return quarantinetime;
        }

        public void setQuarantinetime(String quarantinetime) {
            this.quarantinetime = quarantinetime;
        }

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

        public String getIdcardnum() {
            return idcardnum;
        }

        public void setIdcardnum(String idcardnum) {
            this.idcardnum = idcardnum;
        }

        public String getFarmsnme() {
            return farmsnme;
        }

        public void setFarmsnme(String farmsnme) {
            this.farmsnme = farmsnme;
        }

        public String getQuarantineaddress() {
            return quarantineaddress;
        }

        public void setQuarantineaddress(String quarantineaddress) {
            this.quarantineaddress = quarantineaddress;
        }

        public String getAnimalsort() {
            return animalsort;
        }

        public void setAnimalsort(String animalsort) {
            this.animalsort = animalsort;
        }

        public String getMyuse() {
            return myuse;
        }

        public void setMyuse(String myuse) {
            this.myuse = myuse;
        }

        public String getAnimalsources() {
            return animalsources;
        }

        public void setAnimalsources(String animalsources) {
            this.animalsources = animalsources;
        }

        public String getDomesticatedid() {
            return domesticatedid;
        }

        public void setDomesticatedid(String domesticatedid) {
            this.domesticatedid = domesticatedid;
        }

        public String getCatchid() {
            return catchid;
        }

        public void setCatchid(String catchid) {
            this.catchid = catchid;
        }

        public int getAnimalnum() {
            return animalnum;
        }

        public void setAnimalnum(int animalnum) {
            this.animalnum = animalnum;
        }

        public String getStartaddress() {
            return startaddress;
        }

        public void setStartaddress(String startaddress) {
            this.startaddress = startaddress;
        }

        public String getStartaddress1() {
            return startaddress1;
        }

        public void setStartaddress1(String startaddress1) {
            this.startaddress1 = startaddress1;
        }

        public String getStartaddress2() {
            return startaddress2;
        }

        public void setStartaddress2(String startaddress2) {
            this.startaddress2 = startaddress2;
        }

        public String getStartaddress3() {
            return startaddress3;
        }

        public void setStartaddress3(String startaddress3) {
            this.startaddress3 = startaddress3;
        }

        public String getStartaddress4() {
            return startaddress4;
        }

        public void setStartaddress4(String startaddress4) {
            this.startaddress4 = startaddress4;
        }

        public String getStartaddress5() {
            return startaddress5;
        }

        public void setStartaddress5(String startaddress5) {
            this.startaddress5 = startaddress5;
        }

        public String getToolid() {
            return toolid;
        }

        public void setToolid(String toolid) {
            this.toolid = toolid;
        }

        public String getQzmy() {
            return qzmy;
        }

        public void setQzmy(String qzmy) {
            this.qzmy = qzmy;
        }

        public String getRecordrule() {
            return recordrule;
        }

        public void setRecordrule(String recordrule) {
            this.recordrule = recordrule;
        }

        public String getLogorule() {
            return logorule;
        }

        public void setLogorule(String logorule) {
            this.logorule = logorule;
        }

        public String getOutbreak() {
            return outbreak;
        }

        public void setOutbreak(String outbreak) {
            this.outbreak = outbreak;
        }

        public String getClinical() {
            return clinical;
        }

        public void setClinical(String clinical) {
            this.clinical = clinical;
        }

        public String getEridemicarea() {
            return eridemicarea;
        }

        public void setEridemicarea(String eridemicarea) {
            this.eridemicarea = eridemicarea;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getLaboratory() {
            return laboratory;
        }

        public void setLaboratory(String laboratory) {
            this.laboratory = laboratory;
        }

        public String getSituation() {
            return situation;
        }

        public void setSituation(String situation) {
            this.situation = situation;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getJyclcard() {
            return jyclcard;
        }

        public void setJyclcard(String jyclcard) {
            this.jyclcard = jyclcard;
        }

        public String getGeneral() {
            return general;
        }

        public void setGeneral(String general) {
            this.general = general;
        }

        public String getHarmless() {
            return harmless;
        }

        public void setHarmless(String harmless) {
            this.harmless = harmless;
        }

        public int getHarmlesnum() {
            return harmlesnum;
        }

        public void setHarmlesnum(int harmlesnum) {
            this.harmlesnum = harmlesnum;
        }

        public String getLegal() {
            return legal;
        }

        public void setLegal(String legal) {
            this.legal = legal;
        }

        public String getOtherObj() {
            return otherObj;
        }

        public void setOtherObj(String otherObj) {
            this.otherObj = otherObj;
        }

        public int getLegalnum() {
            return legalnum;
        }

        public void setLegalnum(int legalnum) {
            this.legalnum = legalnum;
        }

        public int getOtherNum() {
            return otherNum;
        }

        public void setOtherNum(int otherNum) {
            this.otherNum = otherNum;
        }

        public String getVetname() {
            return vetname;
        }

        public void setVetname(String vetname) {
            this.vetname = vetname;
        }

        public String getQuarantinerule() {
            return quarantinerule;
        }

        public void setQuarantinerule(String quarantinerule) {
            this.quarantinerule = quarantinerule;
        }

        public String getInspId() {
            return inspId;
        }

        public void setInspId(String inspId) {
            this.inspId = inspId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getQualifiedNum() {
            return qualifiedNum;
        }

        public void setQualifiedNum(String qualifiedNum) {
            this.qualifiedNum = qualifiedNum;
        }

        public String getRunit() {
            return runit;
        }

        public void setRunit(String runit) {
            this.runit = runit;
        }

        public String getRhzqz() {
            return rhzqz;
        }

        public void setRhzqz(String rhzqz) {
            this.rhzqz = rhzqz;
        }

        public String getRhzdate() {
            return rhzdate;
        }

        public void setRhzdate(String rhzdate) {
            this.rhzdate = rhzdate;
        }

        public String getRqydzxx() {
            return rqydzxx;
        }

        public void setRqydzxx(String rqydzxx) {
            this.rqydzxx = rqydzxx;
        }

        public String getRdddzxxs() {
            return rdddzxxs;
        }

        public void setRdddzxxs(String rdddzxxs) {
            this.rdddzxxs = rdddzxxs;
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

        public String getRdddzxx4() {
            return rdddzxx4;
        }

        public void setRdddzxx4(String rdddzxx4) {
            this.rdddzxx4 = rdddzxx4;
        }

        public String getRdddzxx5() {
            return rdddzxx5;
        }

        public void setRdddzxx5(String rdddzxx5) {
            this.rdddzxx5 = rdddzxx5;
        }

        public String getGftime() {
            return Gftime;
        }

        public void setGftime(String Gftime) {
            this.Gftime = Gftime;
        }

        public String getQDWNumber() {
            return QDWNumber;
        }

        public void setQDWNumber(String QDWNumber) {
            this.QDWNumber = QDWNumber;
        }

        public int getHarmlesnums() {
            return harmlesnums;
        }

        public void setHarmlesnums(int harmlesnums) {
            this.harmlesnums = harmlesnums;
        }

        public String getQDWDanWei() {
            return QDWDanWei;
        }

        public void setQDWDanWei(String QDWDanWei) {
            this.QDWDanWei = QDWDanWei;
        }

        public String getDr() {
            return dr;
        }

        public void setDr(String dr) {
            this.dr = dr;
        }

        public String getDr1() {
            return dr1;
        }

        public void setDr1(String dr1) {
            this.dr1 = dr1;
        }

        public String getDr2() {
            return dr2;
        }

        public void setDr2(String dr2) {
            this.dr2 = dr2;
        }

        public String getDr3() {
            return dr3;
        }

        public void setDr3(String dr3) {
            this.dr3 = dr3;
        }
    }
}
