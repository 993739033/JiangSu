package com.wyw.jiangsu.bean;

/**
 * Created by Administrator on 2017/3/6.
 */

public class Qua_QuarantineDeclarationCD {


    /**
     * 动物产地检疫申报单
     */
    /// 申报单编号
    private String QDWNumber;
    /// 申报人名称
        private String QDWCargoOwner;
    /// 电话
    private String QDWPhone;
    /// 动物种类
    private String QDWXuZhong;
    /// 畜种【1】
    private String QDWXuZhongOne;
    /// 畜种【2】
    private String QDWXuZhongTwo;
    /// 畜种种类总
    private String QDWXuZhongZ;
    /// 数量
    private int QDWQuantity;

    //图片字段
    private String len;

    private String QDWDanWei;
    /// 用途
    private String QDWYongTu;
    /// 来源
    private String QDWLaiYuan;
    /// 省【起运地】
    private String QDWShengQy;
    /// 市【起运地】
    private String QDWShiQy;
    /// 县【起运地】
    private String QDWXianQy;
    ///  保存 县/市/区【起运地】
    private String QDWXsqQy;
    /// 乡镇【起运地】
    private String QDWXiangQy;
    /// 村【起运地】
    private String QDWCunQy;
    /// 养殖场、交易市场、散养户【起运地】
    private String QDWTypeQy;
    /// 起运地
    private String QDAddQy;
    /// 省【到达地】
    private String QDWShengDd;
    /// 市【到达地】
    private String QDWShiDd;
    /// 县【到达地】
    private String QDWXianDd;
    /// 县/市/区【到达地】
    private String QDWXsqDd;
    /// 乡镇【到达地】
    private String QDWXiangDd;
    /// 村【到达地】
    private String QDWCunDd;
    /// 养殖场、屠宰场、交易市场、散养户【到达地】
    private String QDWTypeDd;
    /// 到达地
    private String QDWAddDd;
    /// 牲畜耳标号
    private String QDWErBiaoHao;
    /// 启运时间
    private String DateQy;
    /// 报检时间
    private String DateQF;
    /// 小时
    private String dr;
    /// 许可证号
    private String XKZH;
    /// 有效《跨省引进乳用种用动物检疫审批表》
    private String yx;
    /// 申报人签字（盖章）
    private String GZ;
    /// 申报处理结果
    private String QDWAccepted;
    /// 检疫地点
    private String QDWAddress;
    /// 不受理理由
    private String QDWLiYou;
    /// 经办人
    private String QDWAttn;
    /// 经办人联系电话
    private String QDWJBRDianHua;
    /// 检疫时间
    private String DateNpy;
    /// 拟派时间
    private String CLRQ;
    /// 申报单状态
    private String IsPrant;
    /// 申报类型
    private String FqSbType;
    /// 有效《种畜禽生产经营许可证》
    private String FqZxqscjyxkz;
    /// 动物产品种类
    private String FqProduct;

    public String getQDWNumber() {
        return QDWNumber;
    }

    public void setQDWNumber(String QDWNumber) {
        this.QDWNumber = QDWNumber;
    }

    public String getQDWCargoOwner() {
        return QDWCargoOwner;
    }

    public void setQDWCargoOwner(String QDWCargoOwner) {
        this.QDWCargoOwner = QDWCargoOwner;
    }

    public String getQDWPhone() {
        return QDWPhone;
    }

    public void setQDWPhone(String QDWPhone) {
        this.QDWPhone = QDWPhone;
    }

    public String getQDWXuZhong() {
        return QDWXuZhong;
    }

    public void setQDWXuZhong(String QDWXuZhong) {
        this.QDWXuZhong = QDWXuZhong;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getQDWXuZhongOne() {
        return QDWXuZhongOne;
    }

    public void setQDWXuZhongOne(String QDWXuZhongOne) {
        this.QDWXuZhongOne = QDWXuZhongOne;
    }

    public String getQDWXuZhongTwo() {
        return QDWXuZhongTwo;
    }

    public void setQDWXuZhongTwo(String QDWXuZhongTwo) {
        this.QDWXuZhongTwo = QDWXuZhongTwo;
    }

    public String getQDWXuZhongZ() {
        return QDWXuZhongZ;
    }

    public void setQDWXuZhongZ(String QDWXuZhongZ) {
        this.QDWXuZhongZ = QDWXuZhongZ;
    }

    public int getQDWQuantity() {
        return QDWQuantity;
    }

    public void setQDWQuantity(int QDWQuantity) {
        this.QDWQuantity = QDWQuantity;
    }

    public String getQDWDanWei() {
        return QDWDanWei;
    }

    public void setQDWDanWei(String QDWDanWei) {
        this.QDWDanWei = QDWDanWei;
    }

    public String getQDWYongTu() {
        return QDWYongTu;
    }

    public void setQDWYongTu(String QDWYongTu) {
        this.QDWYongTu = QDWYongTu;
    }

    public String getQDWLaiYuan() {
        return QDWLaiYuan;
    }

    public void setQDWLaiYuan(String QDWLaiYuan) {
        this.QDWLaiYuan = QDWLaiYuan;
    }

    public String getQDWShengQy() {
        return QDWShengQy;
    }

    public void setQDWShengQy(String QDWShengQy) {
        this.QDWShengQy = QDWShengQy;
    }

    public String getQDWShiQy() {
        return QDWShiQy;
    }

    public void setQDWShiQy(String QDWShiQy) {
        this.QDWShiQy = QDWShiQy;
    }

    public String getQDWXianQy() {
        return QDWXianQy;
    }

    public void setQDWXianQy(String QDWXianQy) {
        this.QDWXianQy = QDWXianQy;
    }

    public String getQDWXsqQy() {
        return QDWXsqQy;
    }

    public void setQDWXsqQy(String QDWXsqQy) {
        this.QDWXsqQy = QDWXsqQy;
    }

    public String getQDWXiangQy() {
        return QDWXiangQy;
    }

    public void setQDWXiangQy(String QDWXiangQy) {
        this.QDWXiangQy = QDWXiangQy;
    }

    public String getQDWCunQy() {
        return QDWCunQy;
    }

    public void setQDWCunQy(String QDWCunQy) {
        this.QDWCunQy = QDWCunQy;
    }

    public String getQDWTypeQy() {
        return QDWTypeQy;
    }

    public void setQDWTypeQy(String QDWTypeQy) {
        this.QDWTypeQy = QDWTypeQy;
    }

    public String getQDAddQy() {
        return QDAddQy;
    }

    public void setQDAddQy(String QDAddQy) {
        this.QDAddQy = QDAddQy;
    }

    public String getQDWShengDd() {
        return QDWShengDd;
    }

    public void setQDWShengDd(String QDWShengDd) {
        this.QDWShengDd = QDWShengDd;
    }

    public String getQDWShiDd() {
        return QDWShiDd;
    }

    public void setQDWShiDd(String QDWShiDd) {
        this.QDWShiDd = QDWShiDd;
    }

    public String getQDWXianDd() {
        return QDWXianDd;
    }

    public void setQDWXianDd(String QDWXianDd) {
        this.QDWXianDd = QDWXianDd;
    }

    public String getQDWXsqDd() {
        return QDWXsqDd;
    }

    public void setQDWXsqDd(String QDWXsqDd) {
        this.QDWXsqDd = QDWXsqDd;
    }

    public String getQDWXiangDd() {
        return QDWXiangDd;
    }

    public void setQDWXiangDd(String QDWXiangDd) {
        this.QDWXiangDd = QDWXiangDd;
    }

    public String getQDWCunDd() {
        return QDWCunDd;
    }

    public void setQDWCunDd(String QDWCunDd) {
        this.QDWCunDd = QDWCunDd;
    }

    public String getQDWTypeDd() {
        return QDWTypeDd;
    }

    public void setQDWTypeDd(String QDWTypeDd) {
        this.QDWTypeDd = QDWTypeDd;
    }

    public String getQDWAddDd() {
        return QDWAddDd;
    }

    public void setQDWAddDd(String QDWAddDd) {
        this.QDWAddDd = QDWAddDd;
    }

    public String getQDWErBiaoHao() {
        return QDWErBiaoHao;
    }

    public void setQDWErBiaoHao(String QDWErBiaoHao) {
        this.QDWErBiaoHao = QDWErBiaoHao;
    }

    public String getDateQy() {
        return DateQy;
    }

    public void setDateQy(String dateQy) {
        this.DateQy = dateQy;
    }

    public String getDateQF() {
        return DateQF;
    }

    public void setDateQF(String dateQF) {
        this.DateQF = dateQF;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getXKZH() {
        return XKZH;
    }

    public void setXKZH(String XKZH) {
        this.XKZH = XKZH;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getGZ() {
        return GZ;
    }

    public void setGZ(String GZ) {
        this.GZ = GZ;
    }

    public String getQDWAccepted() {
        return QDWAccepted;
    }

    public void setQDWAccepted(String QDWAccepted) {
        this.QDWAccepted = QDWAccepted;
    }

    public String getQDWAddress() {
        return QDWAddress;
    }

    public void setQDWAddress(String QDWAddress) {
        this.QDWAddress = QDWAddress;
    }

    public String getQDWLiYou() {
        return QDWLiYou;
    }

    public void setQDWLiYou(String QDWLiYou) {
        this.QDWLiYou = QDWLiYou;
    }

    public String getQDWAttn() {
        return QDWAttn;
    }

    public void setQDWAttn(String QDWAttn) {
        this.QDWAttn = QDWAttn;
    }

    public String getQDWJBRDianHua() {
        return QDWJBRDianHua;
    }

    public void setQDWJBRDianHua(String QDWJBRDianHua) {
        this.QDWJBRDianHua = QDWJBRDianHua;
    }

    public String getDateNpy() {
        return DateNpy;
    }

    public void setDateNpy(String dateNpy) {
        this.DateNpy = dateNpy;
    }

    public String getCLRQ() {
        return CLRQ;
    }

    public void setCLRQ(String CLRQ) {
        this.CLRQ = CLRQ;
    }

    public String getIsPrant() {
        return IsPrant;
    }

    public void setIsPrant(String isPrant) {
        this.IsPrant = isPrant;
    }

    public String getFqSbType() {
        return FqSbType;
    }

    public void setFqSbType(String fqSbType) {
        this.FqSbType = fqSbType;
    }

    public String getFqZxqscjyxkz() {
        return FqZxqscjyxkz;
    }

    public void setFqZxqscjyxkz(String fqZxqscjyxkz) {
        this.FqZxqscjyxkz = fqZxqscjyxkz;
    }

    public String getFqProduct() {
        return FqProduct;
    }

    public void setFqProduct(String fqProduct) {
        this.FqProduct = fqProduct;
    }


}
