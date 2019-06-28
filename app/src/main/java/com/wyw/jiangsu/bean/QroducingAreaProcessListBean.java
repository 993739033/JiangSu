package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by wyw on 2017/4/6.
 */

public class QroducingAreaProcessListBean extends BaseArrayObjectEntity<QroducingAreaProcessListBean.DataListBean> {

    public static class DataListBean implements Serializable{
        /**
         * FStId : 102
         * FSguidId : e4c021c8-52ea-4647-96b2-4f01fb97e503
         * FScTime : 2017-04-05
         * FSuTime : 2017-04-05
         * FSuserId : 1
         * FSuserName : 超级管理员
         * FSunitId : 1
         * FSunitUstrId : 01
         * FSunitName : 江苏省
         * FSenterpriseId : 1
         * FSenterpriseName : 超级管理员单位
         * FSmemo1 :
         * FSmemo2 :
         * FSmemo3 :
         * FSaudit : 0
         * FSreviewer :
         * FSDel : False
         * FSm1 :
         * FSm2 :
         * FSm3 :
         * FSm4 :
         * FSm5 :
         * QDWNumber :
         * QDWCargoOwner : 胡言军省外
         * QDWPhone : 18756927603
         * QDWXuZhong : 牛
         * QDWXuZhongOne :
         * QDWXuZhongTwo :
         * QDWQuantity : 1000
         * QDWDanWei :
         * QDWLaiYuan : 家畜家禽
         * QDWYongTu : 屠宰
         * QDWShengQy : 江苏省
         * QDWShiQy : 南京市
         * QDWXianQy : 玄武区
         * QDWXsqQy :
         * QDWXiangQy : 玄武区
         * QDWCunQy : 养殖场
         * QDWTypeQy : 养殖场
         * QDAddQy : 江苏省南京市玄武区玄武区养殖场
         * QDWShengDd : 湖北省
         * QDWShiDd : 武汉市
         * QDWXianDd : 江岸区
         * QDWXsqDd :
         * QDWXiangDd : 江安区
         * QDWCunDd : 屠宰场
         * QDWTypeDd : 屠宰场
         * QDWAddDd : 湖北省武汉市江岸区江安区屠宰场
         * QDWErBiaoHao : 222222222222000-999
         * QDWXuZhongZ : 牛
         * dr : 17
         * XKZH :
         * XKZH1 :
         * DateQF : 2017-04-05
         * QDWAccepted : 受理
         * QDWAddress : 上海市
         * QDWLiYou :
         * QDWAttn : 胡言军省内
         * QDWJBRDianHua : 18756927603
         * DateNpy : 1900-01-01
         * DateQy : 2017-04-05
         * IsPrant :
         * yzcmc : 养殖场养殖场
         */
        //公共字段
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
        private String FSmemo1;
        private String FSmemo2;
        private String FSmemo3;
        private String FSaudit;
        private String FSreviewer;
        private String FSDel;
        private String FSm1;
        private String FSm2;
        private String FSm3;
        private String FSm4;
        private String FSm5;
        //申报单编号
        private String QDWNumber;
        //申报人名称
        private String QDWCargoOwner;
        //电话
        private String QDWPhone;
        //畜种
        private String QDWXuZhong;
        //畜种【1】
        private String QDWXuZhongOne;
        //畜种【2】
        private String QDWXuZhongTwo;
        //畜种总
        private String QDWXuZhongZ;
        //数量
        private String QDWQuantity;
        //单位
        private String QDWDanWei;
        //来源
        private String QDWLaiYuan;
        //用途
        private String QDWYongTu;
        //省【起运地】
        private String QDWShengQy;
        //市【起运地】
        private String QDWShiQy;
        //县【起运地】
        private String QDWXianQy;
        //保存 县/市/区【起运地】
        private String QDWXsqQy;
        // 乡镇【起运地】
        private String QDWXiangQy;
        //村【起运地】
        private String QDWCunQy;
        //养殖场、交易市场、散养户【起运地】
        private String QDWTypeQy;
        //起运地
        private String QDAddQy;
        //省【到达地】
        private String QDWShengDd;
        // 市【到达地】
        private String QDWShiDd;
        //县【到达地】
        private String QDWXianDd;
        // 县/市/区【到达地】
        private String QDWXsqDd;
        //乡镇【到达地】
        private String QDWXiangDd;
        //村【到达地】
        private String QDWCunDd;
        //养殖场、屠宰场、交易市场、散养户【到达地】
        private String QDWTypeDd;
        // 到达地
        private String QDWAddDd;
        //牲畜耳标号
        private String QDWErBiaoHao;
        //小时
        private String dr;
        // 繁殖材料许可证号
        private String XKZH;
        //野生动物捕捉(猎捕)许可证号
        private String XKZH1;
        // 检疫地点
        private String QDWAddress;
        //不受理理由
        private String QDWLiYou;
        //经办人
        private String QDWAttn;
        //经办人联系电话
        private String QDWJBRDianHua;
        //检疫时间
        private String DateNpy;
        //启运时间
        private String DateQy;

        // 报检时间
        private String DateQF;
        //申报单状态
        private String IsPrant;
        //养殖场名称
        private String yzcmc;
        /// 有效《跨省引进乳用种用动物检疫审批表》
        private String yx;
        //申报人签字（盖章）
        private String GZ;
        //申报处理结果
        private String QDWAccepted;
        // 有效《种畜禽生产经营许可证》
        private String FqZxqscjyxkz;
        /// 动物产品种类
        private String FqProduct;

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

        public String getFqZxqscjyxkz() {
            return FqZxqscjyxkz;
        }

        public void setFqZxqscjyxkz(String fqZxqscjyxkz) {
            FqZxqscjyxkz = fqZxqscjyxkz;
        }

        public String getFqProduct() {
            return FqProduct;
        }

        public void setFqProduct(String fqProduct) {
            FqProduct = fqProduct;
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

        public String getQDWQuantity() {
            return QDWQuantity;
        }

        public void setQDWQuantity(String QDWQuantity) {
            this.QDWQuantity = QDWQuantity;
        }

        public String getQDWDanWei() {
            return QDWDanWei;
        }

        public void setQDWDanWei(String QDWDanWei) {
            this.QDWDanWei = QDWDanWei;
        }

        public String getQDWLaiYuan() {
            return QDWLaiYuan;
        }

        public void setQDWLaiYuan(String QDWLaiYuan) {
            this.QDWLaiYuan = QDWLaiYuan;
        }

        public String getQDWYongTu() {
            return QDWYongTu;
        }

        public void setQDWYongTu(String QDWYongTu) {
            this.QDWYongTu = QDWYongTu;
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

        public String getQDWXuZhongZ() {
            return QDWXuZhongZ;
        }

        public void setQDWXuZhongZ(String QDWXuZhongZ) {
            this.QDWXuZhongZ = QDWXuZhongZ;
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

        public String getXKZH1() {
            return XKZH1;
        }

        public void setXKZH1(String XKZH1) {
            this.XKZH1 = XKZH1;
        }

        public String getDateQF() {
            return DateQF;
        }

        public void setDateQF(String DateQF) {
            this.DateQF = DateQF;
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

        public String getDateQy() {
            return DateQy;
        }

        public void setDateQy(String DateQy) {
            this.DateQy = DateQy;
        }

        public String getIsPrant() {
            return IsPrant;
        }

        public void setIsPrant(String IsPrant) {
            this.IsPrant = IsPrant;
        }

        public String getYzcmc() {
            return yzcmc;
        }

        public void setYzcmc(String yzcmc) {
            this.yzcmc = yzcmc;
        }
    }
}
