package com.wyw.jiangsu.bean;

/**
 * 入场查验登记上传的bean
 * Created by Administrator on 2017/4/14.
 */

public class RuChangQuerenUploadBean {
    /// 日期
    private String eDate;
    /// 屠宰场名称
    private String eOwner;
    /// 来源地
    private String eSeedbed;
    /// 数量
    private int eNum;
    /// 单位
    private String eUnit;
    /// 动物种类
    private String eAnimal;
    /// 是否附有检疫证明
    private String eCertificate;
    /// 是否经指定通道调入
    private String eConform;
    ///是否佩戴耳标
    private String eIdentifica;
    /// 动物健康状况
    private String eHealthy;
    /// 查验结果
    private String eExamine;
    /// 检查人姓名
    private String eRummager;
    /// 货主（经纪人）
    private String eCOwner;
    /// 联系电话
    private String ePhone;
    /// 市
    private String eShi;
    /// 县
    private String eXian;
    /// 镇
    private String eZhen;
    /// 村
    private String eCun;
    /// 检疫证号
    private String eNo;
    /// 运载工具号
    private String eVNo;
    /// 备注
    private String eRemark;
    /// 待宰圈
    private String eDZQ;

    private int tIdBc;

    /// 证物检查结果
    private String eZwjcjg;
    /// 动物临床健康检查结果
    private String eLcjcjg;
    /// 产地
    private String eChandi;
    /// 入场数量
    private int RCSL;
    /// 处理数量
    private int CLSL;
    /// 一般处理/生物安全处理
    private String CLFS;
    /// 出具《检疫处理通知单》编号
    private String code;
    /// 消毒
    private String xd;
    /// 记录人员
    private String JLRY;
    /// 结果处理
    private String JGCL;
    /// 小时
    private int xs;
    /// 处理总数
    private int eSum;
    /// 抽检数量
    private int CJSL;
    /// 莱克多巴胺-阴性
    private int LYIN;
    /// 莱克多巴胺-阳性
    private int LYANG;
    /// 克伦特罗-阴性
    private int KYIN;
    /// 克伦特罗-阳性
    private int KYANG;
    /// 沙丁胺醇-阴性
    private int SYIN;
    /// 沙丁胺醇-阳性
    private int SYANG;
    /// 检测人
    private String JCR;
    /// 备注
    private String BZ;

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String geteOwner() {
        return eOwner;
    }

    public void seteOwner(String eOwner) {
        this.eOwner = eOwner;
    }

    public String geteSeedbed() {
        return eSeedbed;
    }

    public void seteSeedbed(String eSeedbed) {
        this.eSeedbed = eSeedbed;
    }

    public int geteNum() {
        return eNum;
    }

    public void seteNum(int eNum) {
        this.eNum = eNum;
    }

    public String geteUnit() {
        return eUnit;
    }

    public void seteUnit(String eUnit) {
        this.eUnit = eUnit;
    }

    public String geteAnimal() {
        return eAnimal;
    }

    public void seteAnimal(String eAnimal) {
        this.eAnimal = eAnimal;
    }

    public String geteCertificate() {
        return eCertificate;
    }

    public void seteCertificate(String eCertificate) {
        this.eCertificate = eCertificate;
    }

    public String geteConform() {
        return eConform;
    }

    public void seteConform(String eConform) {
        this.eConform = eConform;
    }

    public String geteIdentifica() {
        return eIdentifica;
    }

    public void seteIdentifica(String eIdentifica) {
        this.eIdentifica = eIdentifica;
    }

    public String geteHealthy() {
        return eHealthy;
    }

    public void seteHealthy(String eHealthy) {
        this.eHealthy = eHealthy;
    }

    public String geteExamine() {
        return eExamine;
    }

    public void seteExamine(String eExamine) {
        this.eExamine = eExamine;
    }

    public String geteRummager() {
        return eRummager;
    }

    public void seteRummager(String eRummager) {
        this.eRummager = eRummager;
    }

    public String geteCOwner() {
        return eCOwner;
    }

    public void seteCOwner(String eCOwner) {
        this.eCOwner = eCOwner;
    }

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public String geteShi() {
        return eShi;
    }

    public void seteShi(String eShi) {
        this.eShi = eShi;
    }

    public String geteXian() {
        return eXian;
    }

    public void seteXian(String eXian) {
        this.eXian = eXian;
    }

    public String geteZhen() {
        return eZhen;
    }

    public void seteZhen(String eZhen) {
        this.eZhen = eZhen;
    }

    public String geteCun() {
        return eCun;
    }

    public void seteCun(String eCun) {
        this.eCun = eCun;
    }

    public String geteNo() {
        return eNo;
    }

    public void seteNo(String eNo) {
        this.eNo = eNo;
    }

    public String geteVNo() {
        return eVNo;
    }

    public void seteVNo(String eVNo) {
        this.eVNo = eVNo;
    }

    public String geteRemark() {
        return eRemark;
    }

    public void seteRemark(String eRemark) {
        this.eRemark = eRemark;
    }

    public String geteDZQ() {
        return eDZQ;
    }

    public void seteDZQ(String eDZQ) {
        this.eDZQ = eDZQ;
    }

    public int gettIdBc() {
        return tIdBc;
    }

    public void settIdBc(int tIdBc) {
        this.tIdBc = tIdBc;
    }

    public String geteZwjcjg() {
        return eZwjcjg;
    }

    public void seteZwjcjg(String eZwjcjg) {
        this.eZwjcjg = eZwjcjg;
    }

    public String geteLcjcjg() {
        return eLcjcjg;
    }

    public void seteLcjcjg(String eLcjcjg) {
        this.eLcjcjg = eLcjcjg;
    }

    public String geteChandi() {
        return eChandi;
    }

    public void seteChandi(String eChandi) {
        this.eChandi = eChandi;
    }

    public int getRCSL() {
        return RCSL;
    }

    public void setRCSL(int RCSL) {
        this.RCSL = RCSL;
    }

    public int getCLSL() {
        return CLSL;
    }

    public void setCLSL(int CLSL) {
        this.CLSL = CLSL;
    }

    public String getCLFS() {
        return CLFS;
    }

    public void setCLFS(String CLFS) {
        this.CLFS = CLFS;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    public String getJLRY() {
        return JLRY;
    }

    public void setJLRY(String JLRY) {
        this.JLRY = JLRY;
    }

    public String getJGCL() {
        return JGCL;
    }

    public void setJGCL(String JGCL) {
        this.JGCL = JGCL;
    }

    public int getXs() {
        return xs;
    }

    public void setXs(int xs) {
        this.xs = xs;
    }

    public int geteSum() {
        return eSum;
    }

    public void seteSum(int eSum) {
        this.eSum = eSum;
    }

    public int getCJSL() {
        return CJSL;
    }

    public void setCJSL(int CJSL) {
        this.CJSL = CJSL;
    }

    public int getLYIN() {
        return LYIN;
    }

    public void setLYIN(int LYIN) {
        this.LYIN = LYIN;
    }

    public int getLYANG() {
        return LYANG;
    }

    public void setLYANG(int LYANG) {
        this.LYANG = LYANG;
    }

    public int getKYIN() {
        return KYIN;
    }

    public void setKYIN(int KYIN) {
        this.KYIN = KYIN;
    }

    public int getKYANG() {
        return KYANG;
    }

    public void setKYANG(int KYANG) {
        this.KYANG = KYANG;
    }

    public int getSYIN() {
        return SYIN;
    }

    public void setSYIN(int SYIN) {
        this.SYIN = SYIN;
    }

    public int getSYANG() {
        return SYANG;
    }

    public void setSYANG(int SYANG) {
        this.SYANG = SYANG;
    }

    public String getJCR() {
        return JCR;
    }

    public void setJCR(String JCR) {
        this.JCR = JCR;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
