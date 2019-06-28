package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhyzh on 2017/3/30.
 */

public class ProductionAListBean extends BaseArrayObjectEntity<ProductionAListBean.DataListEntity>{

//    /**
//     * errorCode : 0
//     * errorMsg : msg
//     * dataList : [{"FStId":"102","FSguidId":"97eff79c-ca88-477a-b491-8486ba12c7ac","FScTime":"2017-03-30","FSuTime":"2017-03-30","FSuserId":"67","FSuserName":"罗超","FSunitId":"4","FSunitUstrId":"010102","FSunitName":"玄武区","FSenterpriseId":"6","FSenterpriseName":"玄武区动物卫生监督所","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FSDel":"False","FGlid":"26","PNumber":"3200000024","PCargoOwner":"2","PPhone":"","PName":"猪肉","PNameOne":"","PNameTwo":"","PNameThree":"","PQuantity":"20.00","PUnitName":"南京","PProductionunit":"青龙屠宰场江苏省常州市天宁区青龙街道","PSheng":"北京市","PShi":"市辖区","PXian":"东城区","PCarrier":"111","PPhoneCyr":"13402092715","PTrademark":"1111","PDisinfection":"","PYouXiaoRi":"1","PVeterinary":"罗超","PDwPhone":"13402092715","PDanWei":"千克(公斤)","PYunZai":"公路","DateQF":"2017-03-30","PMemo1":"","PMemo2":"北京市市辖区东城区","PMemo3":"","PMemo4":"南京青龙屠宰场江苏省常州市天宁区青龙街道","IsPrant":"已开证","SaveId":"6","PDiDian":"","UploadType":"0","UploadTypeSheng":"0","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":"","PQySheng":"江苏省","PQyShi":"南京市","PQyXian":"玄武区"},{"FStId":"101","FSguidId":"2cf2daf2-70b9-4d3f-93e3-c58d7ccbfdf5","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FSDel":"False","FGlid":"0","PNumber":"32000000","PCargoOwner":"张琳","PPhone":"15021695874","PName":"2222222222","PNameOne":"","PNameTwo":"","PNameThree":"","PQuantity":"1.00","PUnitName":"青龙屠宰场","PProductionunit":"江苏省常州市天宁区青龙街道","PSheng":"北京市","PShi":"市辖区","PXian":"东城区","PCarrier":"张琳","PPhoneCyr":"15021695874","PTrademark":"2222222222222","PDisinfection":"2222222","PYouXiaoRi":"1","PVeterinary":"","PDwPhone":"gb0519-69661020","PDanWei":"千克(公斤)","PYunZai":"水路","DateQF":"2017-03-23","PMemo1":"2222222222222222222222222222222222","PMemo2":"","PMemo3":"","PMemo4":"青龙屠宰场江苏省常州市天宁区青龙街道","IsPrant":"","SaveId":"0","PDiDian":"北京市市辖区东城区","UploadType":"0","UploadTypeSheng":"0","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":"","PQySheng":"","PQyShi":"","PQyXian":""},{"FStId":"100","FSguidId":"8683e196-4b41-49bc-9403-d91c09c47435","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FSDel":"False","FGlid":"0","PNumber":"32000000","PCargoOwner":"张琳","PPhone":"15021695874","PName":"2222222222","PNameOne":"","PNameTwo":"","PNameThree":"","PQuantity":"1.00","PUnitName":"青龙屠宰场","PProductionunit":"江苏省常州市天宁区青龙街道","PSheng":"北京市","PShi":"市辖区","PXian":"东城区","PCarrier":"张琳","PPhoneCyr":"15021695874","PTrademark":"2222222222222","PDisinfection":"2222222","PYouXiaoRi":"1","PVeterinary":"","PDwPhone":"gb0519-69661020","PDanWei":"千克(公斤)","PYunZai":"水路","DateQF":"2017-03-23","PMemo1":"2222222222222222222222222222222222","PMemo2":"","PMemo3":"","PMemo4":"青龙屠宰场江苏省常州市天宁区青龙街道","IsPrant":"","SaveId":"0","PDiDian":"北京市市辖区东城区","UploadType":"0","UploadTypeSheng":"0","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":"","PQySheng":"","PQyShi":"","PQyXian":""},{"FStId":"99","FSguidId":"a7149dce-4936-42af-a542-e810d7012b80","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FSDel":"False","FGlid":"0","PNumber":"32000000","PCargoOwner":"张琳","PPhone":"15021695874","PName":"2222222222","PNameOne":"","PNameTwo":"","PNameThree":"","PQuantity":"1.00","PUnitName":"青龙屠宰场","PProductionunit":"江苏省常州市天宁区青龙街道","PSheng":"北京市","PShi":"市辖区","PXian":"东城区","PCarrier":"张琳","PPhoneCyr":"15021695874","PTrademark":"2222222222222","PDisinfection":"2222222","PYouXiaoRi":"1","PVeterinary":"","PDwPhone":"gb0519-69661020","PDanWei":"千克(公斤)","PYunZai":"水路","DateQF":"2017-03-23","PMemo1":"2222222222222222222222222222222222","PMemo2":"","PMemo3":"","PMemo4":"青龙屠宰场江苏省常州市天宁区青龙街道","IsPrant":"","SaveId":"0","PDiDian":"北京市市辖区东城区","UploadType":"0","UploadTypeSheng":"0","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":"","PQySheng":"","PQyShi":"","PQyXian":""},{"FStId":"98","FSguidId":"9bc5876f-289e-4d6b-950e-57b17e15fe7c","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FSDel":"False","FGlid":"0","PNumber":"32000000","PCargoOwner":"张琳","PPhone":"15021695874","PName":"2222222222","PNameOne":"","PNameTwo":"","PNameThree":"","PQuantity":"1.00","PUnitName":"青龙屠宰场","PProductionunit":"江苏省常州市天宁区青龙街道","PSheng":"北京市","PShi":"市辖区","PXian":"东城区","PCarrier":"张琳","PPhoneCyr":"15021695874","PTrademark":"2222222222222","PDisinfection":"2222222","PYouXiaoRi":"1","PVeterinary":"","PDwPhone":"gb0519-69661020","PDanWei":"千克(公斤)","PYunZai":"水路","DateQF":"2017-03-23","PMemo1":"2222222222222222222222222222222222","PMemo2":"","PMemo3":"","PMemo4":"青龙屠宰场江苏省常州市天宁区青龙街道","IsPrant":"","SaveId":"0","PDiDian":"北京市市辖区东城区","UploadType":"0","UploadTypeSheng":"0","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":"","PQySheng":"","PQyShi":"","PQyXian":""},{"FStId":"97","FSguidId":"d2bac8a6-9d90-49d9-a530-016c0fed3db6","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FSDel":"False","FGlid":"0","PNumber":"32000000","PCargoOwner":"张琳","PPhone":"15021695874","PName":"2222222222","PNameOne":"","PNameTwo":"","PNameThree":"","PQuantity":"1.00","PUnitName":"青龙屠宰场","PProductionunit":"江苏省常州市天宁区青龙街道","PSheng":"北京市","PShi":"市辖区","PXian":"东城区","PCarrier":"张琳","PPhoneCyr":"15021695874","PTrademark":"2222222222222","PDisinfection":"2222222","PYouXiaoRi":"1","PVeterinary":"","PDwPhone":"gb0519-69661020","PDanWei":"千克(公斤)","PYunZai":"水路","DateQF":"2017-03-23","PMemo1":"2222222222222222222222222222222222","PMemo2":"","PMemo3":"","PMemo4":"青龙屠宰场江苏省常州市天宁区青龙街道","IsPrant":"","SaveId":"0","PDiDian":"北京市市辖区东城区","UploadType":"0","UploadTypeSheng":"0","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":"","PQySheng":"","PQyShi":"","PQyXian":""}]
//     */
//
//    private int errorCode;
//    private String errorMsg;
//    private List<DataListEntity> dataList;
//
//    public void setErrorCode(int errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public void setErrorMsg(String errorMsg) {
//        this.errorMsg = errorMsg;
//    }
//
//    public void setDataList(List<DataListEntity> dataList) {
//        this.dataList = dataList;
//    }
//
//    public int getErrorCode() {
//        return errorCode;
//    }
//
//    public String getErrorMsg() {
//        return errorMsg;
//    }
//
//    public List<DataListEntity> getDataList() {
//        return dataList;
//    }

    public static class DataListEntity implements Serializable{
        /**
         * FStId : 102
         * FSguidId : 97eff79c-ca88-477a-b491-8486ba12c7ac
         * FScTime : 2017-03-30
         * FSuTime : 2017-03-30
         * FSuserId : 67
         * FSuserName : 罗超
         * FSunitId : 4
         * FSunitUstrId : 010102
         * FSunitName : 玄武区
         * FSenterpriseId : 6
         * FSenterpriseName : 玄武区动物卫生监督所
         * FSaudit : 0
         * FSreviewer :
         * FSmemo1 :
         * FSmemo2 :
         * FSmemo3 :
         * FSDel : False
         * FGlid : 26
         * PNumber : 3200000024
         * PCargoOwner : 2
         * PPhone :
         * PName : 猪肉
         * PNameOne :
         * PNameTwo :
         * PNameThree :
         * PQuantity : 20.00
         * PUnitName : 南京
         * PProductionunit : 青龙屠宰场江苏省常州市天宁区青龙街道
         * PSheng : 北京市
         * PShi : 市辖区
         * PXian : 东城区
         * PCarrier : 111
         * PPhoneCyr : 13402092715
         * PTrademark : 1111
         * PDisinfection :
         * PYouXiaoRi : 1
         * PVeterinary : 罗超
         * PDwPhone : 13402092715
         * PDanWei : 千克(公斤)
         * PYunZai : 公路
         * DateQF : 2017-03-30
         * PMemo1 :
         * PMemo2 : 北京市市辖区东城区
         * PMemo3 :
         * PMemo4 : 南京青龙屠宰场江苏省常州市天宁区青龙街道
         * IsPrant : 已开证
         * SaveId : 6
         * PDiDian :
         * UploadType : 0
         * UploadTypeSheng : 0
         * FSm1 :
         * FSm2 :
         * FSm3 :
         * FSm4 :
         * FSm5 :
         * PQySheng : 江苏省
         * PQyShi : 南京市
         * PQyXian : 玄武区
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
        private String PYouXiaoRi;
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
        private String FSm1;
        private String FSm2;
        private String FSm3;
        private String FSm4;
        private String FSm5;
        private String PQySheng;
        private String PQyShi;
        private String PQyXian;

        public void setFStId(String FStId) {
            this.FStId = FStId;
        }

        public void setFSguidId(String FSguidId) {
            this.FSguidId = FSguidId;
        }

        public void setFScTime(String FScTime) {
            this.FScTime = FScTime;
        }

        public void setFSuTime(String FSuTime) {
            this.FSuTime = FSuTime;
        }

        public void setFSuserId(String FSuserId) {
            this.FSuserId = FSuserId;
        }

        public void setFSuserName(String FSuserName) {
            this.FSuserName = FSuserName;
        }

        public void setFSunitId(String FSunitId) {
            this.FSunitId = FSunitId;
        }

        public void setFSunitUstrId(String FSunitUstrId) {
            this.FSunitUstrId = FSunitUstrId;
        }

        public void setFSunitName(String FSunitName) {
            this.FSunitName = FSunitName;
        }

        public void setFSenterpriseId(String FSenterpriseId) {
            this.FSenterpriseId = FSenterpriseId;
        }

        public void setFSenterpriseName(String FSenterpriseName) {
            this.FSenterpriseName = FSenterpriseName;
        }

        public void setFSaudit(String FSaudit) {
            this.FSaudit = FSaudit;
        }

        public void setFSreviewer(String FSreviewer) {
            this.FSreviewer = FSreviewer;
        }

        public void setFSmemo1(String FSmemo1) {
            this.FSmemo1 = FSmemo1;
        }

        public void setFSmemo2(String FSmemo2) {
            this.FSmemo2 = FSmemo2;
        }

        public void setFSmemo3(String FSmemo3) {
            this.FSmemo3 = FSmemo3;
        }

        public void setFSDel(String FSDel) {
            this.FSDel = FSDel;
        }

        public void setFGlid(String FGlid) {
            this.FGlid = FGlid;
        }

        public void setPNumber(String PNumber) {
            this.PNumber = PNumber;
        }

        public void setPCargoOwner(String PCargoOwner) {
            this.PCargoOwner = PCargoOwner;
        }

        public void setPPhone(String PPhone) {
            this.PPhone = PPhone;
        }

        public void setPName(String PName) {
            this.PName = PName;
        }

        public void setPNameOne(String PNameOne) {
            this.PNameOne = PNameOne;
        }

        public void setPNameTwo(String PNameTwo) {
            this.PNameTwo = PNameTwo;
        }

        public void setPNameThree(String PNameThree) {
            this.PNameThree = PNameThree;
        }

        public void setPQuantity(String PQuantity) {
            this.PQuantity = PQuantity;
        }

        public void setPUnitName(String PUnitName) {
            this.PUnitName = PUnitName;
        }

        public void setPProductionunit(String PProductionunit) {
            this.PProductionunit = PProductionunit;
        }

        public void setPSheng(String PSheng) {
            this.PSheng = PSheng;
        }

        public void setPShi(String PShi) {
            this.PShi = PShi;
        }

        public void setPXian(String PXian) {
            this.PXian = PXian;
        }

        public void setPCarrier(String PCarrier) {
            this.PCarrier = PCarrier;
        }

        public void setPPhoneCyr(String PPhoneCyr) {
            this.PPhoneCyr = PPhoneCyr;
        }

        public void setPTrademark(String PTrademark) {
            this.PTrademark = PTrademark;
        }

        public void setPDisinfection(String PDisinfection) {
            this.PDisinfection = PDisinfection;
        }

        public void setPYouXiaoRi(String PYouXiaoRi) {
            this.PYouXiaoRi = PYouXiaoRi;
        }

        public void setPVeterinary(String PVeterinary) {
            this.PVeterinary = PVeterinary;
        }

        public void setPDwPhone(String PDwPhone) {
            this.PDwPhone = PDwPhone;
        }

        public void setPDanWei(String PDanWei) {
            this.PDanWei = PDanWei;
        }

        public void setPYunZai(String PYunZai) {
            this.PYunZai = PYunZai;
        }

        public void setDateQF(String DateQF) {
            this.DateQF = DateQF;
        }

        public void setPMemo1(String PMemo1) {
            this.PMemo1 = PMemo1;
        }

        public void setPMemo2(String PMemo2) {
            this.PMemo2 = PMemo2;
        }

        public void setPMemo3(String PMemo3) {
            this.PMemo3 = PMemo3;
        }

        public void setPMemo4(String PMemo4) {
            this.PMemo4 = PMemo4;
        }

        public void setIsPrant(String IsPrant) {
            this.IsPrant = IsPrant;
        }

        public void setSaveId(String SaveId) {
            this.SaveId = SaveId;
        }

        public void setPDiDian(String PDiDian) {
            this.PDiDian = PDiDian;
        }

        public void setUploadType(String UploadType) {
            this.UploadType = UploadType;
        }

        public void setUploadTypeSheng(String UploadTypeSheng) {
            this.UploadTypeSheng = UploadTypeSheng;
        }

        public void setFSm1(String FSm1) {
            this.FSm1 = FSm1;
        }

        public void setFSm2(String FSm2) {
            this.FSm2 = FSm2;
        }

        public void setFSm3(String FSm3) {
            this.FSm3 = FSm3;
        }

        public void setFSm4(String FSm4) {
            this.FSm4 = FSm4;
        }

        public void setFSm5(String FSm5) {
            this.FSm5 = FSm5;
        }

        public void setPQySheng(String PQySheng) {
            this.PQySheng = PQySheng;
        }

        public void setPQyShi(String PQyShi) {
            this.PQyShi = PQyShi;
        }

        public void setPQyXian(String PQyXian) {
            this.PQyXian = PQyXian;
        }

        public String getFStId() {
            return FStId;
        }

        public String getFSguidId() {
            return FSguidId;
        }

        public String getFScTime() {
            return FScTime;
        }

        public String getFSuTime() {
            return FSuTime;
        }

        public String getFSuserId() {
            return FSuserId;
        }

        public String getFSuserName() {
            return FSuserName;
        }

        public String getFSunitId() {
            return FSunitId;
        }

        public String getFSunitUstrId() {
            return FSunitUstrId;
        }

        public String getFSunitName() {
            return FSunitName;
        }

        public String getFSenterpriseId() {
            return FSenterpriseId;
        }

        public String getFSenterpriseName() {
            return FSenterpriseName;
        }

        public String getFSaudit() {
            return FSaudit;
        }

        public String getFSreviewer() {
            return FSreviewer;
        }

        public String getFSmemo1() {
            return FSmemo1;
        }

        public String getFSmemo2() {
            return FSmemo2;
        }

        public String getFSmemo3() {
            return FSmemo3;
        }

        public String getFSDel() {
            return FSDel;
        }

        public String getFGlid() {
            return FGlid;
        }

        public String getPNumber() {
            return PNumber;
        }

        public String getPCargoOwner() {
            return PCargoOwner;
        }

        public String getPPhone() {
            return PPhone;
        }

        public String getPName() {
            return PName;
        }

        public String getPNameOne() {
            return PNameOne;
        }

        public String getPNameTwo() {
            return PNameTwo;
        }

        public String getPNameThree() {
            return PNameThree;
        }

        public String getPQuantity() {
            return PQuantity;
        }

        public String getPUnitName() {
            return PUnitName;
        }

        public String getPProductionunit() {
            return PProductionunit;
        }

        public String getPSheng() {
            return PSheng;
        }

        public String getPShi() {
            return PShi;
        }

        public String getPXian() {
            return PXian;
        }

        public String getPCarrier() {
            return PCarrier;
        }

        public String getPPhoneCyr() {
            return PPhoneCyr;
        }

        public String getPTrademark() {
            return PTrademark;
        }

        public String getPDisinfection() {
            return PDisinfection;
        }

        public String getPYouXiaoRi() {
            return PYouXiaoRi;
        }

        public String getPVeterinary() {
            return PVeterinary;
        }

        public String getPDwPhone() {
            return PDwPhone;
        }

        public String getPDanWei() {
            return PDanWei;
        }

        public String getPYunZai() {
            return PYunZai;
        }

        public String getDateQF() {
            return DateQF;
        }

        public String getPMemo1() {
            return PMemo1;
        }

        public String getPMemo2() {
            return PMemo2;
        }

        public String getPMemo3() {
            return PMemo3;
        }

        public String getPMemo4() {
            return PMemo4;
        }

        public String getIsPrant() {
            return IsPrant;
        }

        public String getSaveId() {
            return SaveId;
        }

        public String getPDiDian() {
            return PDiDian;
        }

        public String getUploadType() {
            return UploadType;
        }

        public String getUploadTypeSheng() {
            return UploadTypeSheng;
        }

        public String getFSm1() {
            return FSm1;
        }

        public String getFSm2() {
            return FSm2;
        }

        public String getFSm3() {
            return FSm3;
        }

        public String getFSm4() {
            return FSm4;
        }

        public String getFSm5() {
            return FSm5;
        }

        public String getPQySheng() {
            return PQySheng;
        }

        public String getPQyShi() {
            return PQyShi;
        }

        public String getPQyXian() {
            return PQyXian;
        }
    }
}
