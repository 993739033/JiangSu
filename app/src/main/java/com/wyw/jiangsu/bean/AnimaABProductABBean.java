package com.wyw.jiangsu.bean;

import java.io.Serializable;

/**
 * 动物AB，产品AB列表详情的bean
 * Created by Administrator on 2017/4/18.
 */

public class AnimaABProductABBean extends BaseArrayObjectEntity<AnimaABProductABBean.DataListBean> {

    public static class DataListBean implements Serializable {


        ////////////////////////////////////////////////四个详情的bean
        //产品A
        private String FGlid;
        private String PNumber;
        private String PCargoOwner;
        private String PPhone;
        private String PName;
        private String PNameOne;
        private String PNameTwo;
        private String PNameThree;
        private String PQuantity;
        private String PUnitName;
        private String PProductionunit;
        private String PSheng;
        private String PShi;
        private String PXian;
        private String PCarrier;
        private String PPhoneCyr;
        private String PTrademark;
        private String PDisinfection;
        private int PYouXiaoRi;
        private String PVeterinary;
        private String PDwPhone;
        private String PDanWei;
        private String PYunZai;
        private String DateQF;
        private String PMemo1;
        private String PMemo2;
        private String PMemo3;
        private String PMemo4;
        private String IsPrant;
        private String SaveId;
        private String PDiDian;
        private String UploadType;
        private String UploadTypeSheng;
        private String PQySheng;
        private String PQyShi;
        private String PQyXian;

        //产品B
        private String PBNumber;
        private String PBCargoOwner;
        private String PBName;
        private String PBNameOne;
        private String PBNameTwo;
        private String PBNameThree;
        private String PBQuantity;
        private String PBOrigin;
        private String PBUnitName;
        private String PBProductionunit;
        private String PBDestinations;
        private String PBSign;
        private String PBVeterinary;
        private String PBDanWei;
        private int PBYouXiaoRi;
        private String PBHzNumber;
        private String PBRemarks;
        private String PBPumAdd;

        //动物A
        private String ANumber;
        private String ACargoOwner;
        private String APhone;
        private int AQuantity;
        private String AShengQy;
        private String AShiQy;
        private String AXianQy;
        private String ADiQuQy;
        private String AXiangQy;
        private String ACunQy;
        private String APlace;
        private String ATypeQy;
        private String AShengDd;
        private String AShiDd;
        private String AXianDd;
        private String ADiQuDd;
        private String AXiangDd;
        private String ACunDd;
        private String AArr;
        private String ATypeDd;
        private String ACarrier;
        private String APhoneCyr;
        private String ATrademark;
        private String ADisinfection;
        private int AYouXiaoRi;
        private String AVeterinary;
        private String AEarTag;
        private String ADwPhone;
        private String AXuZhong;
        private String AXuZhongOne;
        private String AXuZhongTwo;
        private String ADanWei;
        private String AYongTu;
        private String AYunZai;
        private String AMemo1;
        private String AMemo2;
        private String AMemo3;
        private String guid;

        //动物B
        private String AQNumber;
        private String AQCargoOwner;
        private String AQPhone;
        private int AQQuantity;
        private String AQShiQy;
        private String AQXianQy;
        private String AQDiQuQy;
        private String AQXiangQy;
        private String AQCunQy;
        private String AQPlace;
        private String AQTypeQy;
        private String AQShiDd;
        private String AQXianDd;
        private String AQDiQuDd;
        private String AQXiangDd;
        private String AQCunDd;
        private String AQArr;
        private String AQTypeDd;
        private String AQVeterinary;
        private String AQEarTag;
        private String AQXuZhong;
        private String AQXuZhongOne;
        private String AQXuZhongTwo;
        private String AQDanWei;
        private String AQYongTu;
        private String AQMemo1;
        private String AQMemo2;
        private String AQMemo3;
        private int AQYouXiaoRi;

        /////////////////////////////////////////////////////////////四个列表的bean
        private String id;
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
        // 货主姓名
        private String shippername;
        // 动物种类
        private String animalsort;
        // 启用地址详细
        private String rqydzxx;
        // 到达地址详细
        private String rdddzxxs;
        // 联系电话
        private String teltphone;
        // 动物检疫合格证明编号
        private String code;
        // 类型
        private String myuse;

        public String getANumber() {
            return ANumber;
        }

        public void setANumber(String ANumber) {
            this.ANumber = ANumber;
        }

        public String getACargoOwner() {
            return ACargoOwner;
        }

        public void setACargoOwner(String ACargoOwner) {
            this.ACargoOwner = ACargoOwner;
        }

        public String getAPhone() {
            return APhone;
        }

        public void setAPhone(String APhone) {
            this.APhone = APhone;
        }

        public int getAQuantity() {
            return AQuantity;
        }

        public void setAQuantity(int AQuantity) {
            this.AQuantity = AQuantity;
        }

        public String getAShengQy() {
            return AShengQy;
        }

        public void setAShengQy(String AShengQy) {
            this.AShengQy = AShengQy;
        }

        public String getAShiQy() {
            return AShiQy;
        }

        public void setAShiQy(String AShiQy) {
            this.AShiQy = AShiQy;
        }

        public String getAXianQy() {
            return AXianQy;
        }

        public void setAXianQy(String AXianQy) {
            this.AXianQy = AXianQy;
        }

        public String getADiQuQy() {
            return ADiQuQy;
        }

        public void setADiQuQy(String ADiQuQy) {
            this.ADiQuQy = ADiQuQy;
        }

        public String getAXiangQy() {
            return AXiangQy;
        }

        public void setAXiangQy(String AXiangQy) {
            this.AXiangQy = AXiangQy;
        }

        public String getACunQy() {
            return ACunQy;
        }

        public void setACunQy(String ACunQy) {
            this.ACunQy = ACunQy;
        }

        public String getAPlace() {
            return APlace;
        }

        public void setAPlace(String APlace) {
            this.APlace = APlace;
        }

        public String getATypeQy() {
            return ATypeQy;
        }

        public void setATypeQy(String ATypeQy) {
            this.ATypeQy = ATypeQy;
        }

        public String getAShengDd() {
            return AShengDd;
        }

        public void setAShengDd(String AShengDd) {
            this.AShengDd = AShengDd;
        }

        public String getAShiDd() {
            return AShiDd;
        }

        public void setAShiDd(String AShiDd) {
            this.AShiDd = AShiDd;
        }

        public String getAXianDd() {
            return AXianDd;
        }

        public void setAXianDd(String AXianDd) {
            this.AXianDd = AXianDd;
        }

        public String getADiQuDd() {
            return ADiQuDd;
        }

        public void setADiQuDd(String ADiQuDd) {
            this.ADiQuDd = ADiQuDd;
        }

        public String getAXiangDd() {
            return AXiangDd;
        }

        public void setAXiangDd(String AXiangDd) {
            this.AXiangDd = AXiangDd;
        }

        public String getACunDd() {
            return ACunDd;
        }

        public void setACunDd(String ACunDd) {
            this.ACunDd = ACunDd;
        }

        public String getAArr() {
            return AArr;
        }

        public void setAArr(String AArr) {
            this.AArr = AArr;
        }

        public String getATypeDd() {
            return ATypeDd;
        }

        public void setATypeDd(String ATypeDd) {
            this.ATypeDd = ATypeDd;
        }

        public String getACarrier() {
            return ACarrier;
        }

        public void setACarrier(String ACarrier) {
            this.ACarrier = ACarrier;
        }

        public String getAPhoneCyr() {
            return APhoneCyr;
        }

        public void setAPhoneCyr(String APhoneCyr) {
            this.APhoneCyr = APhoneCyr;
        }

        public String getATrademark() {
            return ATrademark;
        }

        public void setATrademark(String ATrademark) {
            this.ATrademark = ATrademark;
        }

        public String getADisinfection() {
            return ADisinfection;
        }

        public void setADisinfection(String ADisinfection) {
            this.ADisinfection = ADisinfection;
        }

        public int getAYouXiaoRi() {
            return AYouXiaoRi;
        }

        public void setAYouXiaoRi(int AYouXiaoRi) {
            this.AYouXiaoRi = AYouXiaoRi;
        }

        public String getAVeterinary() {
            return AVeterinary;
        }

        public void setAVeterinary(String AVeterinary) {
            this.AVeterinary = AVeterinary;
        }

        public String getAEarTag() {
            return AEarTag;
        }

        public void setAEarTag(String AEarTag) {
            this.AEarTag = AEarTag;
        }

        public String getADwPhone() {
            return ADwPhone;
        }

        public void setADwPhone(String ADwPhone) {
            this.ADwPhone = ADwPhone;
        }

        public String getAXuZhong() {
            return AXuZhong;
        }

        public void setAXuZhong(String AXuZhong) {
            this.AXuZhong = AXuZhong;
        }

        public String getAXuZhongOne() {
            return AXuZhongOne;
        }

        public void setAXuZhongOne(String AXuZhongOne) {
            this.AXuZhongOne = AXuZhongOne;
        }

        public String getAXuZhongTwo() {
            return AXuZhongTwo;
        }

        public void setAXuZhongTwo(String AXuZhongTwo) {
            this.AXuZhongTwo = AXuZhongTwo;
        }

        public String getADanWei() {
            return ADanWei;
        }

        public void setADanWei(String ADanWei) {
            this.ADanWei = ADanWei;
        }

        public String getAYongTu() {
            return AYongTu;
        }

        public void setAYongTu(String AYongTu) {
            this.AYongTu = AYongTu;
        }

        public String getAYunZai() {
            return AYunZai;
        }

        public void setAYunZai(String AYunZai) {
            this.AYunZai = AYunZai;
        }

        public String getAMemo1() {
            return AMemo1;
        }

        public void setAMemo1(String AMemo1) {
            this.AMemo1 = AMemo1;
        }

        public String getAMemo2() {
            return AMemo2;
        }

        public void setAMemo2(String AMemo2) {
            this.AMemo2 = AMemo2;
        }

        public String getAMemo3() {
            return AMemo3;
        }

        public void setAMemo3(String AMemo3) {
            this.AMemo3 = AMemo3;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getAQNumber() {
            return AQNumber;
        }

        public void setAQNumber(String AQNumber) {
            this.AQNumber = AQNumber;
        }

        public String getAQCargoOwner() {
            return AQCargoOwner;
        }

        public void setAQCargoOwner(String AQCargoOwner) {
            this.AQCargoOwner = AQCargoOwner;
        }

        public String getAQPhone() {
            return AQPhone;
        }

        public void setAQPhone(String AQPhone) {
            this.AQPhone = AQPhone;
        }

        public int getAQQuantity() {
            return AQQuantity;
        }

        public void setAQQuantity(int AQQuantity) {
            this.AQQuantity = AQQuantity;
        }

        public String getAQShiQy() {
            return AQShiQy;
        }

        public void setAQShiQy(String AQShiQy) {
            this.AQShiQy = AQShiQy;
        }

        public String getAQXianQy() {
            return AQXianQy;
        }

        public void setAQXianQy(String AQXianQy) {
            this.AQXianQy = AQXianQy;
        }

        public String getAQDiQuQy() {
            return AQDiQuQy;
        }

        public void setAQDiQuQy(String AQDiQuQy) {
            this.AQDiQuQy = AQDiQuQy;
        }

        public String getAQXiangQy() {
            return AQXiangQy;
        }

        public void setAQXiangQy(String AQXiangQy) {
            this.AQXiangQy = AQXiangQy;
        }

        public String getAQCunQy() {
            return AQCunQy;
        }

        public void setAQCunQy(String AQCunQy) {
            this.AQCunQy = AQCunQy;
        }

        public String getAQPlace() {
            return AQPlace;
        }

        public void setAQPlace(String AQPlace) {
            this.AQPlace = AQPlace;
        }

        public String getAQTypeQy() {
            return AQTypeQy;
        }

        public void setAQTypeQy(String AQTypeQy) {
            this.AQTypeQy = AQTypeQy;
        }

        public String getAQShiDd() {
            return AQShiDd;
        }

        public void setAQShiDd(String AQShiDd) {
            this.AQShiDd = AQShiDd;
        }

        public String getAQXianDd() {
            return AQXianDd;
        }

        public void setAQXianDd(String AQXianDd) {
            this.AQXianDd = AQXianDd;
        }

        public String getAQDiQuDd() {
            return AQDiQuDd;
        }

        public void setAQDiQuDd(String AQDiQuDd) {
            this.AQDiQuDd = AQDiQuDd;
        }

        public String getAQXiangDd() {
            return AQXiangDd;
        }

        public void setAQXiangDd(String AQXiangDd) {
            this.AQXiangDd = AQXiangDd;
        }

        public String getAQCunDd() {
            return AQCunDd;
        }

        public void setAQCunDd(String AQCunDd) {
            this.AQCunDd = AQCunDd;
        }

        public String getAQArr() {
            return AQArr;
        }

        public void setAQArr(String AQArr) {
            this.AQArr = AQArr;
        }

        public String getAQTypeDd() {
            return AQTypeDd;
        }

        public void setAQTypeDd(String AQTypeDd) {
            this.AQTypeDd = AQTypeDd;
        }

        public String getAQVeterinary() {
            return AQVeterinary;
        }

        public void setAQVeterinary(String AQVeterinary) {
            this.AQVeterinary = AQVeterinary;
        }

        public String getAQEarTag() {
            return AQEarTag;
        }

        public void setAQEarTag(String AQEarTag) {
            this.AQEarTag = AQEarTag;
        }

        public String getAQXuZhong() {
            return AQXuZhong;
        }

        public void setAQXuZhong(String AQXuZhong) {
            this.AQXuZhong = AQXuZhong;
        }

        public String getAQXuZhongOne() {
            return AQXuZhongOne;
        }

        public void setAQXuZhongOne(String AQXuZhongOne) {
            this.AQXuZhongOne = AQXuZhongOne;
        }

        public String getAQXuZhongTwo() {
            return AQXuZhongTwo;
        }

        public void setAQXuZhongTwo(String AQXuZhongTwo) {
            this.AQXuZhongTwo = AQXuZhongTwo;
        }

        public String getAQDanWei() {
            return AQDanWei;
        }

        public void setAQDanWei(String AQDanWei) {
            this.AQDanWei = AQDanWei;
        }

        public String getAQYongTu() {
            return AQYongTu;
        }

        public void setAQYongTu(String AQYongTu) {
            this.AQYongTu = AQYongTu;
        }

        public String getAQMemo1() {
            return AQMemo1;
        }

        public void setAQMemo1(String AQMemo1) {
            this.AQMemo1 = AQMemo1;
        }

        public String getAQMemo2() {
            return AQMemo2;
        }

        public void setAQMemo2(String AQMemo2) {
            this.AQMemo2 = AQMemo2;
        }

        public String getAQMemo3() {
            return AQMemo3;
        }

        public void setAQMemo3(String AQMemo3) {
            this.AQMemo3 = AQMemo3;
        }

        public int getAQYouXiaoRi() {
            return AQYouXiaoRi;
        }

        public void setAQYouXiaoRi(int AQYouXiaoRi) {
            this.AQYouXiaoRi = AQYouXiaoRi;
        }

        public String getPBNumber() {
            return PBNumber;
        }

        public void setPBNumber(String PBNumber) {
            this.PBNumber = PBNumber;
        }

        public String getPBCargoOwner() {
            return PBCargoOwner;
        }

        public void setPBCargoOwner(String PBCargoOwner) {
            this.PBCargoOwner = PBCargoOwner;
        }

        public String getPBName() {
            return PBName;
        }

        public void setPBName(String PBName) {
            this.PBName = PBName;
        }

        public String getPBNameOne() {
            return PBNameOne;
        }

        public void setPBNameOne(String PBNameOne) {
            this.PBNameOne = PBNameOne;
        }

        public String getPBNameTwo() {
            return PBNameTwo;
        }

        public void setPBNameTwo(String PBNameTwo) {
            this.PBNameTwo = PBNameTwo;
        }

        public String getPBNameThree() {
            return PBNameThree;
        }

        public void setPBNameThree(String PBNameThree) {
            this.PBNameThree = PBNameThree;
        }

        public String getPBQuantity() {
            return PBQuantity;
        }

        public void setPBQuantity(String PBQuantity) {
            this.PBQuantity = PBQuantity;
        }

        public String getPBOrigin() {
            return PBOrigin;
        }

        public void setPBOrigin(String PBOrigin) {
            this.PBOrigin = PBOrigin;
        }

        public String getPBUnitName() {
            return PBUnitName;
        }

        public void setPBUnitName(String PBUnitName) {
            this.PBUnitName = PBUnitName;
        }

        public String getPBProductionunit() {
            return PBProductionunit;
        }

        public void setPBProductionunit(String PBProductionunit) {
            this.PBProductionunit = PBProductionunit;
        }

        public String getPBDestinations() {
            return PBDestinations;
        }

        public void setPBDestinations(String PBDestinations) {
            this.PBDestinations = PBDestinations;
        }

        public String getPBSign() {
            return PBSign;
        }

        public void setPBSign(String PBSign) {
            this.PBSign = PBSign;
        }

        public String getPBVeterinary() {
            return PBVeterinary;
        }

        public void setPBVeterinary(String PBVeterinary) {
            this.PBVeterinary = PBVeterinary;
        }

        public String getPBDanWei() {
            return PBDanWei;
        }

        public void setPBDanWei(String PBDanWei) {
            this.PBDanWei = PBDanWei;
        }

        public int getPBYouXiaoRi() {
            return PBYouXiaoRi;
        }

        public void setPBYouXiaoRi(int PBYouXiaoRi) {
            this.PBYouXiaoRi = PBYouXiaoRi;
        }

        public String getPBHzNumber() {
            return PBHzNumber;
        }

        public void setPBHzNumber(String PBHzNumber) {
            this.PBHzNumber = PBHzNumber;
        }

        public String getPBRemarks() {
            return PBRemarks;
        }

        public void setPBRemarks(String PBRemarks) {
            this.PBRemarks = PBRemarks;
        }

        public String getPBPumAdd() {
            return PBPumAdd;
        }

        public void setPBPumAdd(String PBPumAdd) {
            this.PBPumAdd = PBPumAdd;
        }

        public String getFGlid() {
            return FGlid;
        }

        public void setFGlid(String FGlid) {
            this.FGlid = FGlid;
        }

        public String getPNumber() {
            return PNumber;
        }

        public void setPNumber(String PNumber) {
            this.PNumber = PNumber;
        }

        public String getPCargoOwner() {
            return PCargoOwner;
        }

        public void setPCargoOwner(String PCargoOwner) {
            this.PCargoOwner = PCargoOwner;
        }

        public String getPPhone() {
            return PPhone;
        }

        public void setPPhone(String PPhone) {
            this.PPhone = PPhone;
        }

        public String getPName() {
            return PName;
        }

        public void setPName(String PName) {
            this.PName = PName;
        }

        public String getPNameOne() {
            return PNameOne;
        }

        public void setPNameOne(String PNameOne) {
            this.PNameOne = PNameOne;
        }

        public String getPNameTwo() {
            return PNameTwo;
        }

        public void setPNameTwo(String PNameTwo) {
            this.PNameTwo = PNameTwo;
        }

        public String getPNameThree() {
            return PNameThree;
        }

        public void setPNameThree(String PNameThree) {
            this.PNameThree = PNameThree;
        }

        public String getPQuantity() {
            return PQuantity;
        }

        public void setPQuantity(String PQuantity) {
            this.PQuantity = PQuantity;
        }

        public String getPUnitName() {
            return PUnitName;
        }

        public void setPUnitName(String PUnitName) {
            this.PUnitName = PUnitName;
        }

        public String getPProductionunit() {
            return PProductionunit;
        }

        public void setPProductionunit(String PProductionunit) {
            this.PProductionunit = PProductionunit;
        }

        public String getPSheng() {
            return PSheng;
        }

        public void setPSheng(String PSheng) {
            this.PSheng = PSheng;
        }

        public String getPShi() {
            return PShi;
        }

        public void setPShi(String PShi) {
            this.PShi = PShi;
        }

        public String getPXian() {
            return PXian;
        }

        public void setPXian(String PXian) {
            this.PXian = PXian;
        }

        public String getPCarrier() {
            return PCarrier;
        }

        public void setPCarrier(String PCarrier) {
            this.PCarrier = PCarrier;
        }

        public String getPPhoneCyr() {
            return PPhoneCyr;
        }

        public void setPPhoneCyr(String PPhoneCyr) {
            this.PPhoneCyr = PPhoneCyr;
        }

        public String getPTrademark() {
            return PTrademark;
        }

        public void setPTrademark(String PTrademark) {
            this.PTrademark = PTrademark;
        }

        public String getPDisinfection() {
            return PDisinfection;
        }

        public void setPDisinfection(String PDisinfection) {
            this.PDisinfection = PDisinfection;
        }

        public int getPYouXiaoRi() {
            return PYouXiaoRi;
        }

        public void setPYouXiaoRi(int PYouXiaoRi) {
            this.PYouXiaoRi = PYouXiaoRi;
        }

        public String getPVeterinary() {
            return PVeterinary;
        }

        public void setPVeterinary(String PVeterinary) {
            this.PVeterinary = PVeterinary;
        }

        public String getPDwPhone() {
            return PDwPhone;
        }

        public void setPDwPhone(String PDwPhone) {
            this.PDwPhone = PDwPhone;
        }

        public String getPDanWei() {
            return PDanWei;
        }

        public void setPDanWei(String PDanWei) {
            this.PDanWei = PDanWei;
        }

        public String getPYunZai() {
            return PYunZai;
        }

        public void setPYunZai(String PYunZai) {
            this.PYunZai = PYunZai;
        }

        public String getDateQF() {
            return DateQF;
        }

        public void setDateQF(String dateQF) {
            DateQF = dateQF;
        }

        public String getPMemo1() {
            return PMemo1;
        }

        public void setPMemo1(String PMemo1) {
            this.PMemo1 = PMemo1;
        }

        public String getPMemo2() {
            return PMemo2;
        }

        public void setPMemo2(String PMemo2) {
            this.PMemo2 = PMemo2;
        }

        public String getPMemo3() {
            return PMemo3;
        }

        public void setPMemo3(String PMemo3) {
            this.PMemo3 = PMemo3;
        }

        public String getPMemo4() {
            return PMemo4;
        }

        public void setPMemo4(String PMemo4) {
            this.PMemo4 = PMemo4;
        }

        public String getIsPrant() {
            return IsPrant;
        }

        public void setIsPrant(String isPrant) {
            IsPrant = isPrant;
        }

        public String getSaveId() {
            return SaveId;
        }

        public void setSaveId(String saveId) {
            SaveId = saveId;
        }

        public String getPDiDian() {
            return PDiDian;
        }

        public void setPDiDian(String PDiDian) {
            this.PDiDian = PDiDian;
        }

        public String getUploadType() {
            return UploadType;
        }

        public void setUploadType(String uploadType) {
            UploadType = uploadType;
        }

        public String getUploadTypeSheng() {
            return UploadTypeSheng;
        }

        public void setUploadTypeSheng(String uploadTypeSheng) {
            UploadTypeSheng = uploadTypeSheng;
        }

        public String getPQySheng() {
            return PQySheng;
        }

        public void setPQySheng(String PQySheng) {
            this.PQySheng = PQySheng;
        }

        public String getPQyShi() {
            return PQyShi;
        }

        public void setPQyShi(String PQyShi) {
            this.PQyShi = PQyShi;
        }

        public String getPQyXian() {
            return PQyXian;
        }

        public void setPQyXian(String PQyXian) {
            this.PQyXian = PQyXian;
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

        public String getOnimalsort() {
            return onimalsort;
        }

        public void setOnimalsort(String onimalsort) {
            this.onimalsort = onimalsort;
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

        public String getShippername() {
            return shippername;
        }

        public void setShippername(String shippername) {
            this.shippername = shippername;
        }

        public String getAnimalsort() {
            return animalsort;
        }

        public void setAnimalsort(String animalsort) {
            this.animalsort = animalsort;
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

        public String getTeltphone() {
            return teltphone;
        }

        public void setTeltphone(String teltphone) {
            this.teltphone = teltphone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMyuse() {
            return myuse;
        }

        public void setMyuse(String myuse) {
            this.myuse = myuse;
        }
    }
}
