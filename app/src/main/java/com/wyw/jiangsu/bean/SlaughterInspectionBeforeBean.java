package com.wyw.jiangsu.bean;

/**
 * Created by zhyzh on 2017/4/13.
 */

/**
 * 宰前检查
 */
public class SlaughterInspectionBeforeBean {

    /// <summary>
    /// 屠宰检疫编号
    /// </summary>
    public String TZJYBH;
    /// <summary>
    /// 日期
    /// </summary>
    public String FDate;
    /// 官方兽医签名
    /// </summary>
    public String FAnimalDoctor;
    /// <summary>
    /// 屠宰企业
    /// </summary>
    public String FDieEnterprise;
    /// <summary>
    /// 货主
    /// </summary>
    public String FShipper;
    /// <summary>
    /// 动物种类
    /// </summary>
    public String FAnimalSpecies;
    /// <summary>
    /// 现存数量
    /// </summary>
    public int FNowNumber;
    /// <summary>
    /// 入场数量
    /// </summary>
    public int FComeNumber;
    /// <summary>
    /// 死亡数量
    /// </summary>
    public int FDieNumber;
    /// <summary>
    /// 疑似染疫数量
    /// </summary>
    public int FInfectionNumber;
    /// <summary>
    /// 隔离观察数量
    /// </summary>
    public int FIsolatedNumber;
    /// <summary>
    /// 隔离观察地点
    /// </summary>
    public String FIsolatedPlace;
    /// <summary>
    /// 急宰数量
    /// </summary>
    public int FUrgentNumber;
    /// <summary>
    /// 备注
    /// </summary>
    public String FRemarks;
    /// <summary>
    /// 关联企业名称id
    /// </summary>
    public int FGlid;
    /// <summary>
    /// 动物临床检查健康
    /// </summary>
    public String eHealthy;
    /// <summary>
    /// 动物临床检查健康结果
    /// </summary>
    public String eLcjcjg;
    /// <summary>
    /// 动物单位
    /// </summary>
    public String FAnimalUnit;
    /// <summary>
    /// 检疫证编号
    /// </summary>
    public String JYZNumber;
    /// <summary>
    /// 入场关联id
    /// </summary>
    public String FRCGlid;


    /// <summary>
    /// 小时
    /// </summary>
    public int xs;
    /// <summary>
    /// 申报单编号
    /// </summary>
    public String SBDBH;
    /// <summary>
    /// 检查数量
    /// </summary>
    public int JCSL;
    /// <summary>
    /// 结果处理
    /// </summary>
    public String JGCL;
    /// <summary>
    /// 准宰数量
    /// </summary>
    public int ZZSL;
    /// <summary>
    /// 圈存数量
    /// </summary>
    public int QCSL;
    /// <summary>
    /// 处理数量
    /// </summary>
    public int CLSL;
    /// <summary>
    /// 采取措施
    /// </summary>
    public String CQCS;
    /// <summary>
    /// 出具《检疫处理通知单》编号
    /// </summary>
    public String code;
    /// <summary>
    /// 检疫人员
    /// </summary>
    public String JYRY;
    /// <summary>
    /// 记录人员
    /// </summary>
    public String JLRY;
    /// <summary>
    /// 临床检查情况(正常，异常）集合以逗号隔开
    /// </summary>
    public String ZCYC;
    /// <summary>
    /// 圈号集合以逗号隔开
    /// </summary>
    public String QHS;
    /// <summary>
    /// 数量集合以逗号隔开
    /// </summary>
    public String SLS;

    //实际屠宰量
    public String FSm1;

    //剩余头数
    public String FSm2;

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

    public String getTZJYBH() {
        return TZJYBH;
    }

    public void setTZJYBH(String TZJYBH) {
        this.TZJYBH = TZJYBH;
    }

    public String getFDate() {
        return FDate;
    }

    public void setFDate(String FDate) {
        this.FDate = FDate;
    }

    public String getFAnimalSpecies() {
        return FAnimalSpecies;
    }

    public void setFAnimalSpecies(String FAnimalSpecies) {
        this.FAnimalSpecies = FAnimalSpecies;
    }

    public int getFNowNumber() {
        return FNowNumber;
    }

    public void setFNowNumber(int FNowNumber) {
        this.FNowNumber = FNowNumber;
    }

    public int getFComeNumber() {
        return FComeNumber;
    }

    public void setFComeNumber(int FComeNumber) {
        this.FComeNumber = FComeNumber;
    }

    public int getFDieNumber() {
        return FDieNumber;
    }

    public void setFDieNumber(int FDieNumber) {
        this.FDieNumber = FDieNumber;
    }

    public int getFIsolatedNumber() {
        return FIsolatedNumber;
    }

    public void setFIsolatedNumber(int FIsolatedNumber) {
        this.FIsolatedNumber = FIsolatedNumber;
    }

    public int getFInfectionNumber() {
        return FInfectionNumber;
    }

    public void setFInfectionNumber(int FInfectionNumber) {
        this.FInfectionNumber = FInfectionNumber;
    }

    public String getFIsolatedPlace() {
        return FIsolatedPlace;
    }

    public void setFIsolatedPlace(String FIsolatedPlace) {
        this.FIsolatedPlace = FIsolatedPlace;
    }

    public int getFUrgentNumber() {
        return FUrgentNumber;
    }

    public void setFUrgentNumber(int FUrgentNumber) {
        this.FUrgentNumber = FUrgentNumber;
    }

    public int getFGlid() {
        return FGlid;
    }

    public void setFGlid(int FGlid) {
        this.FGlid = FGlid;
    }

    public String getFRemarks() {
        return FRemarks;
    }

    public void setFRemarks(String FRemarks) {
        this.FRemarks = FRemarks;
    }

    public String geteHealthy() {
        return eHealthy;
    }

    public void seteHealthy(String eHealthy) {
        this.eHealthy = eHealthy;
    }

    public String geteLcjcjg() {
        return eLcjcjg;
    }

    public void seteLcjcjg(String eLcjcjg) {
        this.eLcjcjg = eLcjcjg;
    }

    public String getFAnimalUnit() {
        return FAnimalUnit;
    }

    public void setFAnimalUnit(String FAnimalUnit) {
        this.FAnimalUnit = FAnimalUnit;
    }

    public String getJYZNumber() {
        return JYZNumber;
    }

    public void setJYZNumber(String JYZNumber) {
        this.JYZNumber = JYZNumber;
    }

    public String getFRCGlid() {
        return FRCGlid;
    }

    public void setFRCGlid(String FRCGlid) {
        this.FRCGlid = FRCGlid;
    }

    public int getXs() {
        return xs;
    }

    public void setXs(int xs) {
        this.xs = xs;
    }

    public String getSBDBH() {
        return SBDBH;
    }

    public void setSBDBH(String SBDBH) {
        this.SBDBH = SBDBH;
    }

    public int getJCSL() {
        return JCSL;
    }

    public void setJCSL(int JCSL) {
        this.JCSL = JCSL;
    }

    public String getJGCL() {
        return JGCL;
    }

    public void setJGCL(String JGCL) {
        this.JGCL = JGCL;
    }

    public int getZZSL() {
        return ZZSL;
    }

    public void setZZSL(int ZZSL) {
        this.ZZSL = ZZSL;
    }

    public int getQCSL() {
        return QCSL;
    }

    public void setQCSL(int QCSL) {
        this.QCSL = QCSL;
    }

    public int getCLSL() {
        return CLSL;
    }

    public void setCLSL(int CLSL) {
        this.CLSL = CLSL;
    }

    public String getCQCS() {
        return CQCS;
    }

    public void setCQCS(String CQCS) {
        this.CQCS = CQCS;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJYRY() {
        return JYRY;
    }

    public void setJYRY(String JYRY) {
        this.JYRY = JYRY;
    }

    public String getJLRY() {
        return JLRY;
    }

    public void setJLRY(String JLRY) {
        this.JLRY = JLRY;
    }

    public String getFAnimalDoctor() {
        return FAnimalDoctor;
    }

    public void setFAnimalDoctor(String FAnimalDoctor) {
        this.FAnimalDoctor = FAnimalDoctor;
    }

    public String getFDieEnterprise() {
        return FDieEnterprise;
    }

    public void setFDieEnterprise(String FDieEnterprise) {
        this.FDieEnterprise = FDieEnterprise;
    }

    public String getFShipper() {
        return FShipper;
    }

    public void setFShipper(String FShipper) {
        this.FShipper = FShipper;
    }

    public String getZCYC() {
        return ZCYC;
    }

    public void setZCYC(String ZCYC) {
        this.ZCYC = ZCYC;
    }

    public String getQHS() {
        return QHS;
    }

    public void setQHS(String QHS) {
        this.QHS = QHS;
    }

    public String getSLS() {
        return SLS;
    }

    public void setSLS(String SLS) {
        this.SLS = SLS;
    }
}
