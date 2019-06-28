package com.wyw.jiangsu.bean.upload;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/29.
 */

public class UploadQuarantineProcessNotBeanDetil implements Serializable {
    /// 类型
    private String  FType;
    /// 关联申报单id
    private int FGlid;
    /// 编号
    private String NNumber;
    /// 处理单位
    private String NDanWei;
    /// 不合格动物及动物产品名称
    private String NName;
    /// 处理依据
    private String NTiaoLi;
    /// 处理意见
    private String NChuLi;
    /// 处理意见1
    private String NChuLi1;
    /// 处理意见2
    private String NChuLi2;
    /// 处理意见3
    private String NChuLi3;
    /// 处理意见4
    private String NChuLi4;
    /// 兽医签字
    private String NVeterinary;
    /// 当事人签字
    private String NParties;
    /// 联系电话
    private String NDwPhone;
    /// 动物卫生监督所电话
    private String NDsrPhone;
    /// 是否打印,不需要给他string?类型因为默认需要为已保存,已打印,已报废
    private String IsPrant;
    //区分表
    public int SaveId;
    /// 处理（动物/产品）种类
    private String FChuliType;
    /// 处理数量
    private float FChuliNum;
    /// 处理单位
    private String FChuliDanwei;
    /// 处理意见1---存第一处理意见1的第二行的打印时使用文本--新加字段
    private String NChuLi1txt;
    /// 处理意见2---存第二处理意见2的打印时使用文本--新加字段
    private String NChuLi2txt;
    //物品类别
    private String FSm1;
    /// 处理建立日期
    private String FScTime;
    /// 处理修改日期

    public String getFSuTime() {
        return FSuTime;
    }

    public void setFSuTime(String FSuTime) {
        this.FSuTime = FSuTime;
    }

    public String getFScTime() {
        return FScTime;
    }

    public void setFScTime(String FScTime) {
        this.FScTime = FScTime;
    }

    private String FSuTime;

    public int getSaveId() {
        return SaveId;
    }

    public void setSaveId(int saveId) {
        SaveId = saveId;
    }

    public String getFType() {
        return FType;
    }

    public void setFType(String FType) {
        this.FType = FType;
    }

    public int getFGlid() {
        return FGlid;
    }

    public void setFGlid(int FGlid) {
        this.FGlid = FGlid;
    }

    public String getNNumber() {
        return NNumber;
    }

    public void setNNumber(String NNumber) {
        this.NNumber = NNumber;
    }

    public String getNDanWei() {
        return NDanWei;
    }

    public void setNDanWei(String NDanWei) {
        this.NDanWei = NDanWei;
    }

    public String getNName() {
        return NName;
    }

    public void setNName(String NName) {
        this.NName = NName;
    }

    public String getNTiaoLi() {
        return NTiaoLi;
    }

    public void setNTiaoLi(String NTiaoLi) {
        this.NTiaoLi = NTiaoLi;
    }

    public String getNChuLi() {
        return NChuLi;
    }

    public void setNChuLi(String NChuLi) {
        this.NChuLi = NChuLi;
    }

    public String getNChuLi1() {
        return NChuLi1;
    }

    public void setNChuLi1(String NChuLi1) {
        this.NChuLi1 = NChuLi1;
    }

    public String getNChuLi2() {
        return NChuLi2;
    }

    public void setNChuLi2(String NChuLi2) {
        this.NChuLi2 = NChuLi2;
    }

    public String getNChuLi3() {
        return NChuLi3;
    }

    public void setNChuLi3(String NChuLi3) {
        this.NChuLi3 = NChuLi3;
    }

    public String getNChuLi4() {
        return NChuLi4;
    }

    public void setNChuLi4(String NChuLi4) {
        this.NChuLi4 = NChuLi4;
    }

    public String getNVeterinary() {
        return NVeterinary;
    }

    public void setNVeterinary(String NVeterinary) {
        this.NVeterinary = NVeterinary;
    }

    public String getNParties() {
        return NParties;
    }

    public void setNParties(String NParties) {
        this.NParties = NParties;
    }

    public String getNDwPhone() {
        return NDwPhone;
    }

    public void setNDwPhone(String NDwPhone) {
        this.NDwPhone = NDwPhone;
    }

    public String getNDsrPhone() {
        return NDsrPhone;
    }

    public void setNDsrPhone(String NDsrPhone) {
        this.NDsrPhone = NDsrPhone;
    }

    public String getIsPrant() {
        return IsPrant;
    }

    public void setIsPrant(String isPrant) {
        IsPrant = isPrant;
    }

    public String getFChuliType() {
        return FChuliType;
    }

    public void setFChuliType(String FChuliType) {
        this.FChuliType = FChuliType;
    }

    public float getFChuliNum() {
        return FChuliNum;
    }

    public void setFChuliNum(float FChuliNum) {
        this.FChuliNum = FChuliNum;
    }

    public String getFChuliDanwei() {
        return FChuliDanwei;
    }

    public void setFChuliDanwei(String FChuliDanwei) {
        this.FChuliDanwei = FChuliDanwei;
    }

    public String getNChuLi1txt() {
        return NChuLi1txt;
    }

    public void setNChuLi1txt(String NChuLi1txt) {
        this.NChuLi1txt = NChuLi1txt;
    }

    public String getNChuLi2txt() {
        return NChuLi2txt;
    }

    public void setNChuLi2txt(String NChuLi2txt) {
        this.NChuLi2txt = NChuLi2txt;
    }

    public String getFSm1() {
        return FSm1;
    }

    public void setFSm1(String FSm1) {
        this.FSm1 = FSm1;
    }


}
