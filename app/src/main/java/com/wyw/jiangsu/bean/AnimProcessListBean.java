package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/9.
 * 检疫处理单列表实体类
 */

public class AnimProcessListBean extends BaseArrayObjectEntity<AnimProcessListBean.DataListBean> {


    public static class DataListBean implements Serializable {
        /**
         * FStId : 22
         * FSguidId : d54aee6a-529f-4b15-867d-fda83826b035
         * FScTime : 2017-03-08
         * FSuTime : 2017-03-08
         * FSuserId : 100
         * FSuserName : 孙尚连
         * FSunitId : 138
         * FSunitUstrId : 01010201
         * FSunitName : 玄武区街道
         * FSenterpriseId : 9
         * FSenterpriseName : 玄武区养殖场
         * FSaudit : 0
         * FSreviewer :
         * FSmemo1 :
         * FSmemo2 :
         * FSmemo3 :
         * FSDel : False
         * FSm1 :
         * FSm2 :
         * FSm3 :
         * FSm4 :
         * FSm5 :
         * QDWNumber : 苏(320102)1703081038393320
         * QDWCargoOwner : 孙尚连
         * QDWPhone : 15021698745
         * QDWXuZhong : 猪
         * QDWXuZhongOne :
         * QDWXuZhongTwo :
         * QDWXuZhongZ : 猪
         * QDWQuantity : 23
         * QDWDanWei : 头
         * QDWYongTu : 屠宰
         * QDWLaiYuan : 家畜家禽
         * QDWShengQy : 江苏省
         * QDWShiQy : 南京市
         * QDWXianQy : 玄武区
         * QDWXsqQy :
         * QDWXiangQy : 玄武区街道
         * QDWCunQy : 玄武区养殖场
         * QDWTypeQy : 养殖场
         * QDAddQy : 江苏省南京市玄武区玄武区街道乡(镇)玄武区养殖场
         * QDWShengDd : 江苏省
         * QDWShiDd : 南京市
         * QDWXianDd : 六合区
         * QDWXsqDd :
         * QDWXiangDd : 六合区
         * QDWCunDd : 南京市大厂欣乐食品中心定点屠宰场
         * QDWTypeDd : 屠宰场
         * QDWAddDd : 江苏省南京市六合区六合区乡(镇)南京市大厂欣乐食品中心定点屠宰场
         * QDWErBiaoHao : 879678665467001-023
         * DateQy : 2017-03-08
         * DateQF : 2017-03-08
         * dr : 10
         * XKZH :
         * yx : 无
         * GZ : 孙尚连
         * IsPrant : 票证已保存
         * QDWAccepted : 受理
         * QDWAddress : 南京市大厂欣乐食品中心定点屠宰场
         * QDWLiYou :
         * QDWAttn : 余惠清
         * QDWJBRDianHua : 15021695874
         * DateNpy : 2017-03-08
         * CLRQ : 2017-03-08
         * FqSbType : 动物
         * FqZxqscjyxkz :
         * FqProduct :
         */
        private String img;

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
        private String QDWNumber;
        private String QDWCargoOwner;
        private String QDWPhone;
        private String QDWXuZhong;
        private String QDWXuZhongOne;
        private String QDWXuZhongTwo;
        private String QDWXuZhongZ;
        private int QDWQuantity;
        private String QDWDanWei;
        private String QDWYongTu;
        private String QDWLaiYuan;
        private String QDWShengQy;
        private String QDWShiQy;
        private String QDWXianQy;
        private String QDWXsqQy;
        private String QDWXiangQy;
        private String QDWCunQy;
        private String QDWTypeQy;
        private String QDAddQy;
        private String QDWShengDd;
        private String QDWShiDd;
        private String QDWXianDd;
        private String QDWXsqDd;
        private String QDWXiangDd;
        private String QDWCunDd;
        private String QDWTypeDd;
        private String QDWAddDd;
        private String QDWErBiaoHao;
        private String DateQy;
        private String DateQF;
        private String dr;
        private String XKZH;
        private String yx;
        private String GZ;
        private String IsPrant;
        private String QDWAccepted;
        private String QDWAddress;
        private String QDWLiYou;
        private String QDWAttn;
        private String QDWJBRDianHua;
        private String DateNpy;
        private String CLRQ;
        private String FqSbType;
        private String FqZxqscjyxkz;
        private String FqProduct;

        //新增
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public void setDateQy(String DateQy) {
            this.DateQy = DateQy;
        }

        public String getDateQF() {
            return DateQF;
        }

        public void setDateQF(String DateQF) {
            this.DateQF = DateQF;
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

        public String getIsPrant() {
            return IsPrant;
        }

        public void setIsPrant(String IsPrant) {
            this.IsPrant = IsPrant;
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

        public void setDateNpy(String DateNpy) {
            this.DateNpy = DateNpy;
        }

        public String getCLRQ() {
            return CLRQ;
        }

        public void setCLRQ(String CLRQ) {
            this.CLRQ = CLRQ;
        }

        public String getFqSbType() {
            return FqSbType;
        }

        public void setFqSbType(String FqSbType) {
            this.FqSbType = FqSbType;
        }

        public String getFqZxqscjyxkz() {
            return FqZxqscjyxkz;
        }

        public void setFqZxqscjyxkz(String FqZxqscjyxkz) {
            this.FqZxqscjyxkz = FqZxqscjyxkz;
        }

        public String getFqProduct() {
            return FqProduct;
        }

        public void setFqProduct(String FqProduct) {
            this.FqProduct = FqProduct;
        }
    }
}
