package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/6.
 */

public class AnimRuZhongYongRecordListBean extends BaseArrayObjectEntity<AnimRuZhongYongRecordListBean.DataListBean> {

    public static class DataListBean implements Serializable {

        /// 自增ID
        public String FStId;
        /// Guid
        public String FSguidId;
        /// 系统默认记录日期
        public String FScTime;
        /// 系统默认记录日期—修改时更新
        public String FSuTime;
        /// 用户id
        public int FSuserId;
        /// 用户名
        public String FSuserName;
        /// 行政id
        public int FSunitId;
        /// 行政等级id如01、001、0001
        public String FSunitUstrId;
        /// 行政名称
        public String FSunitName;
        /// 企业/单位id
        public int FSenterpriseId;
        /// 企业/单位名称
        public String FSenterpriseName;
        /// 审核（0未审核，1审核）
        public int FSaudit;
        /// 审核人
        public String FSreviewer;
        /// 预留备注1
        public String FSmemo1;
        /// 预留备注2
        public String FSmemo2;
        /// 预留备注3
        public String FSmemo3;
        /// 备注1
        public String FSm1;
        /// 备注2
        public String FSm2;
        /// 备注3
        public String FSm3;
        /// 备注4
        public String FSm4;
        /// 备注5
        public String FSm5;
        /// 备注5
        public String FSDel;

        /// 申报单编号
        public String QDWNumber;
        /// 申报人名称
        public String QDWCargoOwner;
        /// 电话
        public String QDWPhone;
        /// 畜种
        public String QDWXuZhong;
        /// 畜种【1】
        public String QDWXuZhongOne;
        /// 畜种【2】
        public String QDWXuZhongTwo;
        /// 畜种总
        public String QDWXuZhongZ;
        /// 数量
        public int QDWQuantity;

        public String QDWDanWei;
        /// 用途
        public String QDWYongTu;
        /// 来源
        public String QDWLaiYuan;
        /// 省【起运地】
        public String QDWShengQy;
        /// 市【起运地】
        public String QDWShiQy;
        /// 县【起运地】
        public String QDWXianQy;
        ///  保存 县/市/区【起运地】
        public String QDWXsqQy;
        /// 乡镇【起运地】
        public String QDWXiangQy;
        /// 村【起运地】
        public String QDWCunQy;
        /// 养殖场、交易市场、散养户【起运地】
        public String QDWTypeQy;
        /// 起运地
        public String QDAddQy;
        /// 省【到达地】
        public String QDWShengDd;
        /// 市【到达地】
        public String QDWShiDd;
        /// 县【到达地】
        public String QDWXianDd;
        /// 县/市/区【到达地】
        public String QDWXsqDd;
        /// 乡镇【到达地】
        public String QDWXiangDd;
        /// 村【到达地】
        public String QDWCunDd;
        /// 养殖场、屠宰场、交易市场、散养户【到达地】
        public String QDWTypeDd;
        /// 到达地
        public String QDWAddDd;
        /// 牲畜耳标号
        public String QDWErBiaoHao;
        /// 启运时间
        public String DateQy;
        /// 报检时间
        public String DateQF;
        /// 小时
        public String dr;
        /// 许可证号
        public String XKZH;
        /// 有效《跨省引进乳用种用动物检疫审批表》
        public String yx;
        /// 申报人签字（盖章）
        public String GZ;
        /// 申报处理结果
        public String QDWAccepted;
        /// 检疫地点
        public String QDWAddress;
        /// 不受理理由
        public String QDWLiYou;
        /// 经办人
        public String QDWAttn;
        /// 经办人联系电话
        public String QDWJBRDianHua;
        /// 检疫时间
        public String DateNpy;
        /// 拟派时间
        public String CLRQ;
        /// 申报单状态
        public String IsPrant;
        /// 申报类型
        public String FqSbType;
        /// 有效《种畜禽生产经营许可证》
        public String FqZxqscjyxkz;
        /// 动物产品种类
        public String FqProduct;
        /// 动物A自增id
        public int AFStId;
        /// 动物A与申报单关联id
        public int AFGlid;
        /// 检疫证编号
        public String ANumber;
        /// 检疫证与处理单区分
        public String DwType;
        /// 工作记录单关联id
        public String AH_FGlid;
        /// 养殖场名称
        public String yzcmc;
        /// 野生动物捕捉(猎捕)许可证号
        public String XKZH1;
        /// 签发日期
        public String ABNDateQF;

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

        public int getFSunitId() {
            return FSunitId;
        }

        public void setFSunitId(int FSunitId) {
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

        public int getFSenterpriseId() {
            return FSenterpriseId;
        }

        public void setFSenterpriseId(int FSenterpriseId) {
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

        public void setDateQy(String dateQy) {
            DateQy = dateQy;
        }

        public String getDateQF() {
            return DateQF;
        }

        public void setDateQF(String dateQF) {
            DateQF = dateQF;
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
            DateNpy = dateNpy;
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
            IsPrant = isPrant;
        }

        public String getFqSbType() {
            return FqSbType;
        }

        public void setFqSbType(String fqSbType) {
            FqSbType = fqSbType;
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

        public int getAFStId() {
            return AFStId;
        }

        public void setAFStId(int AFStId) {
            this.AFStId = AFStId;
        }

        public int getAFGlid() {
            return AFGlid;
        }

        public void setAFGlid(int AFGlid) {
            this.AFGlid = AFGlid;
        }

        public String getANumber() {
            return ANumber;
        }

        public void setANumber(String ANumber) {
            this.ANumber = ANumber;
        }

        public String getDwType() {
            return DwType;
        }

        public void setDwType(String dwType) {
            DwType = dwType;
        }

        public String getAH_FGlid() {
            return AH_FGlid;
        }

        public void setAH_FGlid(String AH_FGlid) {
            this.AH_FGlid = AH_FGlid;
        }

        public String getYzcmc() {
            return yzcmc;
        }

        public void setYzcmc(String yzcmc) {
            this.yzcmc = yzcmc;
        }

        public String getXKZH1() {
            return XKZH1;
        }

        public void setXKZH1(String XKZH1) {
            this.XKZH1 = XKZH1;
        }

        public String getABNDateQF() {
            return ABNDateQF;
        }

        public void setABNDateQF(String ABNDateQF) {
            this.ABNDateQF = ABNDateQF;
        }
    }

}
