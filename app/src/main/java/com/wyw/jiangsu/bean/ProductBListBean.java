package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhyzh on 2017/3/30.
 */

public class ProductBListBean extends BaseArrayObjectEntity<ProductBListBean.DataListEntity>{

//    /**
//     * errorCode : 0
//     * errorMsg : msg
//     * dataList : [{"FStId":"15","FSguidId":"784feff7-e883-4b8d-b65a-15dc7578b460","FScTime":"2017-03-30","FSuTime":"2017-03-30","FSuserId":"67","FSuserName":"罗超","FSunitId":"4","FSunitUstrId":"010102","FSunitName":"玄武区","FSenterpriseId":"6","FSenterpriseName":"玄武区动物卫生监督所","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"11","PBNumber":"3200000015","PBCargoOwner":"2","PBName":"猪肉","PBNameOne":"","PBNameTwo":"","PBNameThree":"","PBQuantity":"10.00","PBOrigin":"南京","PBUnitName":"","PBProductionunit":"","PBDestinations":"那就","PBSign":"2015481","PBVeterinary":"罗超","PBDanWei":"千克(公斤)","DateQF":"2017-03-30","IsPrant":"已开证","SaveId":"6","PBYouXiaoRi":"0","PBHzNumber":"","UploadType":"0","UploadTypeSheng":"0","PBRemarks":"11111","PBPumAdd":"青龙屠宰场江苏省常州市天宁区青龙街道","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"14","FSguidId":"a2f89e85-6e83-4d53-b0c7-22afdc768493","FScTime":"2017-03-30","FSuTime":"2017-03-30","FSuserId":"67","FSuserName":"罗超","FSunitId":"4","FSunitUstrId":"010102","FSunitName":"玄武区","FSenterpriseId":"6","FSenterpriseName":"玄武区动物卫生监督所","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"11","PBNumber":"3200000016","PBCargoOwner":"2","PBName":"猪肉","PBNameOne":"","PBNameTwo":"","PBNameThree":"","PBQuantity":"10.00","PBOrigin":"南京","PBUnitName":"","PBProductionunit":"","PBDestinations":"那就","PBSign":"2015481","PBVeterinary":"罗超","PBDanWei":"千克(公斤)","DateQF":"2017-03-30","IsPrant":"已开证","SaveId":"6","PBYouXiaoRi":"0","PBHzNumber":"","UploadType":"0","UploadTypeSheng":"0","PBRemarks":"11111","PBPumAdd":"青龙屠宰场江苏省常州市天宁区青龙街道","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"13","FSguidId":"719dea39-b0bc-4032-9f84-de60cb6f8c29","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"0","PBNumber":"3200000007","PBCargoOwner":"哈哈哈","PBName":"猪肉","PBNameOne":"","PBNameTwo":"","PBNameThree":"","PBQuantity":"2.00","PBOrigin":"-哈哈哈哈阿Q","PBUnitName":"","PBProductionunit":"","PBDestinations":"江苏省南京市玄武区","PBSign":"111111111111","PBVeterinary":"晏子龙","PBDanWei":"千克(公斤)","DateQF":"2017-03-27","IsPrant":"","SaveId":"0","PBYouXiaoRi":"0","PBHzNumber":"","UploadType":"0","UploadTypeSheng":"0","PBRemarks":"哈哈哈哈哈哈","PBPumAdd":"青龙屠宰场江苏省常州市天宁区青龙街道","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"12","FSguidId":"c94a436d-7d0f-4852-b796-8bb1fdd2dd15","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"0","PBNumber":"3200000007","PBCargoOwner":"哈哈哈","PBName":"猪肉","PBNameOne":"","PBNameTwo":"","PBNameThree":"","PBQuantity":"2.00","PBOrigin":"-哈哈哈哈阿Q","PBUnitName":"","PBProductionunit":"","PBDestinations":"江苏省南京市玄武区","PBSign":"111111111111","PBVeterinary":"晏子龙","PBDanWei":"千克(公斤)","DateQF":"2017-03-27","IsPrant":"","SaveId":"0","PBYouXiaoRi":"0","PBHzNumber":"","UploadType":"0","UploadTypeSheng":"0","PBRemarks":"哈哈哈哈哈哈","PBPumAdd":"青龙屠宰场江苏省常州市天宁区青龙街道","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"11","FSguidId":"b276429f-57f2-4070-aeed-1369611c928d","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"0","PBNumber":"3200000007","PBCargoOwner":"哈哈哈","PBName":"猪肉","PBNameOne":"","PBNameTwo":"","PBNameThree":"","PBQuantity":"2.00","PBOrigin":"-哈哈哈哈阿Q","PBUnitName":"","PBProductionunit":"","PBDestinations":"江苏省南京市玄武区","PBSign":"111111111111","PBVeterinary":"晏子龙","PBDanWei":"千克(公斤)","DateQF":"2017-03-27","IsPrant":"","SaveId":"0","PBYouXiaoRi":"0","PBHzNumber":"","UploadType":"0","UploadTypeSheng":"0","PBRemarks":"哈哈哈哈哈哈","PBPumAdd":"青龙屠宰场江苏省常州市天宁区青龙街道","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"10","FSguidId":"30aa6f36-88cc-43a2-8f81-f111392f7f62","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"0","PBNumber":"3200000007","PBCargoOwner":"哈哈哈","PBName":"猪肉","PBNameOne":"","PBNameTwo":"","PBNameThree":"","PBQuantity":"2.00","PBOrigin":"-哈哈哈哈阿Q","PBUnitName":"","PBProductionunit":"","PBDestinations":"江苏省南京市玄武区","PBSign":"111111111111","PBVeterinary":"晏子龙","PBDanWei":"千克(公斤)","DateQF":"2017-03-27","IsPrant":"","SaveId":"0","PBYouXiaoRi":"0","PBHzNumber":"","UploadType":"0","UploadTypeSheng":"0","PBRemarks":"哈哈哈哈哈哈","PBPumAdd":"青龙屠宰场江苏省常州市天宁区青龙街道","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""}]
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

    public static class DataListEntity implements Serializable {
        /**
         * FStId : 15
         * FSguidId : 784feff7-e883-4b8d-b65a-15dc7578b460
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
         * FGlid : 11
         * PBNumber : 3200000015
         * PBCargoOwner : 2
         * PBName : 猪肉
         * PBNameOne :
         * PBNameTwo :
         * PBNameThree :
         * PBQuantity : 10.00
         * PBOrigin : 南京
         * PBUnitName :
         * PBProductionunit :
         * PBDestinations : 那就
         * PBSign : 2015481
         * PBVeterinary : 罗超
         * PBDanWei : 千克(公斤)
         * DateQF : 2017-03-30
         * IsPrant : 已开证
         * SaveId : 6
         * PBYouXiaoRi : 0
         * PBHzNumber :
         * UploadType : 0
         * UploadTypeSheng : 0
         * PBRemarks : 11111
         * PBPumAdd : 青龙屠宰场江苏省常州市天宁区青龙街道
         * FSDel : False
         * FSm1 :
         * FSm2 :
         * FSm3 :
         * FSm4 :
         * FSm5 :
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
        private String FGlid;
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
        private String DateQF;
        private String IsPrant;
        private String SaveId;
        private String PBYouXiaoRi;
        private String PBHzNumber;
        private String UploadType;
        private String UploadTypeSheng;
        private String PBRemarks;
        private String PBPumAdd;
        private String FSDel;
        private String FSm1;
        private String FSm2;
        private String FSm3;
        private String FSm4;
        private String FSm5;

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

        public void setFGlid(String FGlid) {
            this.FGlid = FGlid;
        }

        public void setPBNumber(String PBNumber) {
            this.PBNumber = PBNumber;
        }

        public void setPBCargoOwner(String PBCargoOwner) {
            this.PBCargoOwner = PBCargoOwner;
        }

        public void setPBName(String PBName) {
            this.PBName = PBName;
        }

        public void setPBNameOne(String PBNameOne) {
            this.PBNameOne = PBNameOne;
        }

        public void setPBNameTwo(String PBNameTwo) {
            this.PBNameTwo = PBNameTwo;
        }

        public void setPBNameThree(String PBNameThree) {
            this.PBNameThree = PBNameThree;
        }

        public void setPBQuantity(String PBQuantity) {
            this.PBQuantity = PBQuantity;
        }

        public void setPBOrigin(String PBOrigin) {
            this.PBOrigin = PBOrigin;
        }

        public void setPBUnitName(String PBUnitName) {
            this.PBUnitName = PBUnitName;
        }

        public void setPBProductionunit(String PBProductionunit) {
            this.PBProductionunit = PBProductionunit;
        }

        public void setPBDestinations(String PBDestinations) {
            this.PBDestinations = PBDestinations;
        }

        public void setPBSign(String PBSign) {
            this.PBSign = PBSign;
        }

        public void setPBVeterinary(String PBVeterinary) {
            this.PBVeterinary = PBVeterinary;
        }

        public void setPBDanWei(String PBDanWei) {
            this.PBDanWei = PBDanWei;
        }

        public void setDateQF(String DateQF) {
            this.DateQF = DateQF;
        }

        public void setIsPrant(String IsPrant) {
            this.IsPrant = IsPrant;
        }

        public void setSaveId(String SaveId) {
            this.SaveId = SaveId;
        }

        public void setPBYouXiaoRi(String PBYouXiaoRi) {
            this.PBYouXiaoRi = PBYouXiaoRi;
        }

        public void setPBHzNumber(String PBHzNumber) {
            this.PBHzNumber = PBHzNumber;
        }

        public void setUploadType(String UploadType) {
            this.UploadType = UploadType;
        }

        public void setUploadTypeSheng(String UploadTypeSheng) {
            this.UploadTypeSheng = UploadTypeSheng;
        }

        public void setPBRemarks(String PBRemarks) {
            this.PBRemarks = PBRemarks;
        }

        public void setPBPumAdd(String PBPumAdd) {
            this.PBPumAdd = PBPumAdd;
        }

        public void setFSDel(String FSDel) {
            this.FSDel = FSDel;
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

        public String getFGlid() {
            return FGlid;
        }

        public String getPBNumber() {
            return PBNumber;
        }

        public String getPBCargoOwner() {
            return PBCargoOwner;
        }

        public String getPBName() {
            return PBName;
        }

        public String getPBNameOne() {
            return PBNameOne;
        }

        public String getPBNameTwo() {
            return PBNameTwo;
        }

        public String getPBNameThree() {
            return PBNameThree;
        }

        public String getPBQuantity() {
            return PBQuantity;
        }

        public String getPBOrigin() {
            return PBOrigin;
        }

        public String getPBUnitName() {
            return PBUnitName;
        }

        public String getPBProductionunit() {
            return PBProductionunit;
        }

        public String getPBDestinations() {
            return PBDestinations;
        }

        public String getPBSign() {
            return PBSign;
        }

        public String getPBVeterinary() {
            return PBVeterinary;
        }

        public String getPBDanWei() {
            return PBDanWei;
        }

        public String getDateQF() {
            return DateQF;
        }

        public String getIsPrant() {
            return IsPrant;
        }

        public String getSaveId() {
            return SaveId;
        }

        public String getPBYouXiaoRi() {
            return PBYouXiaoRi;
        }

        public String getPBHzNumber() {
            return PBHzNumber;
        }

        public String getUploadType() {
            return UploadType;
        }

        public String getUploadTypeSheng() {
            return UploadTypeSheng;
        }

        public String getPBRemarks() {
            return PBRemarks;
        }

        public String getPBPumAdd() {
            return PBPumAdd;
        }

        public String getFSDel() {
            return FSDel;
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
    }
}
