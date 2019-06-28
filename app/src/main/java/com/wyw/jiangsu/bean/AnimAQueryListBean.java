package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhyzh on 2017/3/30.
 */

public class AnimAQueryListBean extends BaseArrayObjectEntity<AnimAQueryListBean.DataListEntity> implements Serializable{

    /**
     * errorCode : 0
     * errorMsg : msg
     * dataList : [{"FStId":"43","FSguidId":"75b43cb1-9b70-4cc1-8e1d-e6e91c074942","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"236","FSuserName":"晏子龙","FSunitId":"150","FSunitUstrId":"01040201","FSunitName":"青龙街道","FSenterpriseId":"42","FSenterpriseName":"青龙畜牧兽医站","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"4","ANumber":"3200000000","ACargoOwner":"晏芳","APhone":"13638486279","AQuantity":"1","AShengQy":"江苏省","AShiQy":"常州市","AXianQy":"天宁区","ADiQuQy":"","AXiangQy":"青龙街道","ACunQy":"天宁区新希望养殖场","APlace":"","ATypeQy":"养殖场","AShengDd":"浙江省","AShiDd":"杭州市","AXianDd":"上城区","ADiQuDd":"","AXiangDd":"杭州街道","ACunDd":"杭州养殖场","AArr":"","ATypeDd":"养殖场","ACarrier":"历时","APhoneCyr":"13000000000","ATrademark":"00032210","ADisinfection":"","AYouXiaoRi":"1","AVeterinary":"晏子龙","AEarTag":"111111122222333","ADwPhone":"13000000000","AXuZhong":"猪","AXuZhongOne":"","AXuZhongTwo":"","ADanWei":"头","AYongTu":"种用","AYunZai":"公路","DateQF":"2017-03-27","AMemo1":"","AMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AMemo3":"浙江省杭州市上城区杭州街道乡(镇)杭州养殖场","SaveId":"0","IsPrant":"已打印","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"6","FSm5":"AH_AiryEmbryoQuarantine"},{"FStId":"42","FSguidId":"fbffec84-1b80-47b8-9b11-71d7a12488dd","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"2","ANumber":"1222222222","ACargoOwner":"晏芳","APhone":"13638486279","AQuantity":"1","AShengQy":"江苏省","AShiQy":"常州市","AXianQy":"天宁区","ADiQuQy":"","AXiangQy":"青龙街道","ACunQy":"天宁区新希望养殖场","APlace":"","ATypeQy":"养殖场","AShengDd":"浙江省","AShiDd":"杭州市","AXianDd":"上城区","ADiQuDd":"","AXiangDd":"杭州街道","ACunDd":"杭州养殖场","AArr":"","ATypeDd":"养殖场","ACarrier":"1111","APhoneCyr":"111111111111","ATrademark":"1111111111111","ADisinfection":"碘伏","AYouXiaoRi":"1","AVeterinary":"","AEarTag":"111111122222333","ADwPhone":"11414444444411","AXuZhong":"猪","AXuZhongOne":"","AXuZhongTwo":"","ADanWei":"头","AYongTu":"屠宰","AYunZai":"铁路","DateQF":"2017-03-22","AMemo1":"1111111111","AMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AMemo3":"浙江省杭州市上城区杭州街道乡(镇)杭州养殖场","SaveId":"0","IsPrant":"已报废","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"41","FSguidId":"9f266750-e86d-4177-a860-c990f81f1235","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"2","ANumber":"1222222222","ACargoOwner":"晏芳","APhone":"13638486279","AQuantity":"1","AShengQy":"江苏省","AShiQy":"常州市","AXianQy":"天宁区","ADiQuQy":"","AXiangQy":"青龙街道","ACunQy":"天宁区新希望养殖场","APlace":"","ATypeQy":"养殖场","AShengDd":"浙江省","AShiDd":"杭州市","AXianDd":"上城区","ADiQuDd":"","AXiangDd":"杭州街道","ACunDd":"杭州养殖场","AArr":"","ATypeDd":"养殖场","ACarrier":"11111","APhoneCyr":"1111111111","ATrademark":"11111111111","ADisinfection":"碘伏","AYouXiaoRi":"1","AVeterinary":"","AEarTag":"111111122222333","ADwPhone":"1111111","AXuZhong":"猪","AXuZhongOne":"","AXuZhongTwo":"","ADanWei":"头","AYongTu":"屠宰","AYunZai":"航空","DateQF":"2017-03-22","AMemo1":"1111111","AMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AMemo3":"浙江省杭州市上城区杭州街道乡(镇)杭州养殖场","SaveId":"0","IsPrant":"已报废","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"40","FSguidId":"40787b06-256c-4312-941e-607ea3d72e1f","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"2","ANumber":"1222222222","ACargoOwner":"晏芳","APhone":"13638486279","AQuantity":"1","AShengQy":"江苏省","AShiQy":"常州市","AXianQy":"天宁区","ADiQuQy":"","AXiangQy":"青龙街道","ACunQy":"天宁区新希望养殖场","APlace":"","ATypeQy":"养殖场","AShengDd":"浙江省","AShiDd":"杭州市","AXianDd":"上城区","ADiQuDd":"","AXiangDd":"杭州街道","ACunDd":"杭州养殖场","AArr":"","ATypeDd":"养殖场","ACarrier":"11111","APhoneCyr":"111111111111","ATrademark":"111111111111","ADisinfection":"过氧乙酸","AYouXiaoRi":"1","AVeterinary":"","AEarTag":"111111122222333","ADwPhone":"11111111111","AXuZhong":"猪","AXuZhongOne":"","AXuZhongTwo":"","ADanWei":"头","AYongTu":"屠宰","AYunZai":"航空","DateQF":"2017-03-22","AMemo1":"111111","AMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AMemo3":"浙江省杭州市上城区杭州街道乡(镇)杭州养殖场","SaveId":"0","IsPrant":"已报废","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"39","FSguidId":"f554cbfe-834a-4c30-882b-172c2a7abcb3","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"2","ANumber":"1222222222","ACargoOwner":"晏芳","APhone":"13638486279","AQuantity":"1","AShengQy":"江苏省","AShiQy":"常州市","AXianQy":"天宁区","ADiQuQy":"","AXiangQy":"青龙街道","ACunQy":"天宁区新希望养殖场","APlace":"","ATypeQy":"养殖场","AShengDd":"浙江省","AShiDd":"杭州市","AXianDd":"上城区","ADiQuDd":"","AXiangDd":"杭州街道","ACunDd":"杭州养殖场","AArr":"","ATypeDd":"养殖场","ACarrier":"11111","APhoneCyr":"1111111111111","ATrademark":"11111111111","ADisinfection":"过氧乙酸","AYouXiaoRi":"1","AVeterinary":"","AEarTag":"111111122222333","ADwPhone":"11111111111","AXuZhong":"猪","AXuZhongOne":"","AXuZhongTwo":"","ADanWei":"头","AYongTu":"屠宰","AYunZai":"水路","DateQF":"2017-03-22","AMemo1":"11111111111","AMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AMemo3":"浙江省杭州市上城区杭州街道乡(镇)杭州养殖场","SaveId":"0","IsPrant":"已报废","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"38","FSguidId":"a8f81477-8198-4126-92ea-697177b05eb2","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"2","ANumber":"1222222222","ACargoOwner":"晏芳","APhone":"13638486279","AQuantity":"1","AShengQy":"江苏省","AShiQy":"常州市","AXianQy":"天宁区","ADiQuQy":"","AXiangQy":"青龙街道","ACunQy":"天宁区新希望养殖场","APlace":"","ATypeQy":"养殖场","AShengDd":"浙江省","AShiDd":"杭州市","AXianDd":"上城区","ADiQuDd":"","AXiangDd":"杭州街道","ACunDd":"杭州养殖场","AArr":"","ATypeDd":"养殖场","ACarrier":"11111","APhoneCyr":"111111111111","ATrademark":"111111111111","ADisinfection":"过氧乙酸","AYouXiaoRi":"1","AVeterinary":"","AEarTag":"111111122222333","ADwPhone":"111111111111","AXuZhong":"猪","AXuZhongOne":"","AXuZhongTwo":"","ADanWei":"头","AYongTu":"屠宰","AYunZai":"铁路","DateQF":"2017-03-22","AMemo1":"111111111","AMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AMemo3":"浙江省杭州市上城区杭州街道乡(镇)杭州养殖场","SaveId":"0","IsPrant":"已报废","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""}]
     */
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
         * FStId : 43
         * FSguidId : 75b43cb1-9b70-4cc1-8e1d-e6e91c074942
         * FScTime : 2017-03-27
         * FSuTime : 2017-03-27
         * FSuserId : 236
         * FSuserName : 晏子龙
         * FSunitId : 150
         * FSunitUstrId : 01040201
         * FSunitName : 青龙街道
         * FSenterpriseId : 42
         * FSenterpriseName : 青龙畜牧兽医站
         * FSaudit : 0
         * FSreviewer :
         * FSmemo1 :
         * FSmemo2 :
         * FSmemo3 :
         * FGlid : 4
         * ANumber : 3200000000
         * ACargoOwner : 晏芳
         * APhone : 13638486279
         * AQuantity : 1
         * AShengQy : 江苏省
         * AShiQy : 常州市
         * AXianQy : 天宁区
         * ADiQuQy :
         * AXiangQy : 青龙街道
         * ACunQy : 天宁区新希望养殖场
         * APlace :
         * ATypeQy : 养殖场
         * AShengDd : 浙江省
         * AShiDd : 杭州市
         * AXianDd : 上城区
         * ADiQuDd :
         * AXiangDd : 杭州街道
         * ACunDd : 杭州养殖场
         * AArr :
         * ATypeDd : 养殖场
         * ACarrier : 历时
         * APhoneCyr : 13000000000
         * ATrademark : 00032210
         * ADisinfection :
         * AYouXiaoRi : 1
         * AVeterinary : 晏子龙
         * AEarTag : 111111122222333
         * ADwPhone : 13000000000
         * AXuZhong : 猪
         * AXuZhongOne :
         * AXuZhongTwo :
         * ADanWei : 头
         * AYongTu : 种用
         * AYunZai : 公路
         * DateQF : 2017-03-27
         * AMemo1 :
         * AMemo2 : 江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场
         * AMemo3 : 浙江省杭州市上城区杭州街道乡(镇)杭州养殖场
         * SaveId : 0
         * IsPrant : 已打印
         * UploadType : 0
         * UploadTypeSheng : 0
         * FSDel : False
         * FSm1 :
         * FSm2 :
         * FSm3 :
         * FSm4 : 6
         * FSm5 : AH_AiryEmbryoQuarantine
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
        private String ANumber;
        private String ACargoOwner;
        private String APhone;
        private String AQuantity;
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
        private String AYouXiaoRi;
        private String AVeterinary;
        private String AEarTag;
        private String ADwPhone;
        private String AXuZhong;
        private String AXuZhongOne;
        private String AXuZhongTwo;
        private String ADanWei;
        private String AYongTu;
        private String AYunZai;
        private String DateQF;
        private String AMemo1;
        private String AMemo2;
        private String AMemo3;
        private String SaveId;
        private String IsPrant;
        private String UploadType;
        private String UploadTypeSheng;
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

        public void setANumber(String ANumber) {
            this.ANumber = ANumber;
        }

        public void setACargoOwner(String ACargoOwner) {
            this.ACargoOwner = ACargoOwner;
        }

        public void setAPhone(String APhone) {
            this.APhone = APhone;
        }

        public void setAQuantity(String AQuantity) {
            this.AQuantity = AQuantity;
        }

        public void setAShengQy(String AShengQy) {
            this.AShengQy = AShengQy;
        }

        public void setAShiQy(String AShiQy) {
            this.AShiQy = AShiQy;
        }

        public void setAXianQy(String AXianQy) {
            this.AXianQy = AXianQy;
        }

        public void setADiQuQy(String ADiQuQy) {
            this.ADiQuQy = ADiQuQy;
        }

        public void setAXiangQy(String AXiangQy) {
            this.AXiangQy = AXiangQy;
        }

        public void setACunQy(String ACunQy) {
            this.ACunQy = ACunQy;
        }

        public void setAPlace(String APlace) {
            this.APlace = APlace;
        }

        public void setATypeQy(String ATypeQy) {
            this.ATypeQy = ATypeQy;
        }

        public void setAShengDd(String AShengDd) {
            this.AShengDd = AShengDd;
        }

        public void setAShiDd(String AShiDd) {
            this.AShiDd = AShiDd;
        }

        public void setAXianDd(String AXianDd) {
            this.AXianDd = AXianDd;
        }

        public void setADiQuDd(String ADiQuDd) {
            this.ADiQuDd = ADiQuDd;
        }

        public void setAXiangDd(String AXiangDd) {
            this.AXiangDd = AXiangDd;
        }

        public void setACunDd(String ACunDd) {
            this.ACunDd = ACunDd;
        }

        public void setAArr(String AArr) {
            this.AArr = AArr;
        }

        public void setATypeDd(String ATypeDd) {
            this.ATypeDd = ATypeDd;
        }

        public void setACarrier(String ACarrier) {
            this.ACarrier = ACarrier;
        }

        public void setAPhoneCyr(String APhoneCyr) {
            this.APhoneCyr = APhoneCyr;
        }

        public void setATrademark(String ATrademark) {
            this.ATrademark = ATrademark;
        }

        public void setADisinfection(String ADisinfection) {
            this.ADisinfection = ADisinfection;
        }

        public void setAYouXiaoRi(String AYouXiaoRi) {
            this.AYouXiaoRi = AYouXiaoRi;
        }

        public void setAVeterinary(String AVeterinary) {
            this.AVeterinary = AVeterinary;
        }

        public void setAEarTag(String AEarTag) {
            this.AEarTag = AEarTag;
        }

        public void setADwPhone(String ADwPhone) {
            this.ADwPhone = ADwPhone;
        }

        public void setAXuZhong(String AXuZhong) {
            this.AXuZhong = AXuZhong;
        }

        public void setAXuZhongOne(String AXuZhongOne) {
            this.AXuZhongOne = AXuZhongOne;
        }

        public void setAXuZhongTwo(String AXuZhongTwo) {
            this.AXuZhongTwo = AXuZhongTwo;
        }

        public void setADanWei(String ADanWei) {
            this.ADanWei = ADanWei;
        }

        public void setAYongTu(String AYongTu) {
            this.AYongTu = AYongTu;
        }

        public void setAYunZai(String AYunZai) {
            this.AYunZai = AYunZai;
        }

        public void setDateQF(String DateQF) {
            this.DateQF = DateQF;
        }

        public void setAMemo1(String AMemo1) {
            this.AMemo1 = AMemo1;
        }

        public void setAMemo2(String AMemo2) {
            this.AMemo2 = AMemo2;
        }

        public void setAMemo3(String AMemo3) {
            this.AMemo3 = AMemo3;
        }

        public void setSaveId(String SaveId) {
            this.SaveId = SaveId;
        }

        public void setIsPrant(String IsPrant) {
            this.IsPrant = IsPrant;
        }

        public void setUploadType(String UploadType) {
            this.UploadType = UploadType;
        }

        public void setUploadTypeSheng(String UploadTypeSheng) {
            this.UploadTypeSheng = UploadTypeSheng;
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

        public String getANumber() {
            return ANumber;
        }

        public String getACargoOwner() {
            return ACargoOwner;
        }

        public String getAPhone() {
            return APhone;
        }

        public String getAQuantity() {
            return AQuantity;
        }

        public String getAShengQy() {
            return AShengQy;
        }

        public String getAShiQy() {
            return AShiQy;
        }

        public String getAXianQy() {
            return AXianQy;
        }

        public String getADiQuQy() {
            return ADiQuQy;
        }

        public String getAXiangQy() {
            return AXiangQy;
        }

        public String getACunQy() {
            return ACunQy;
        }

        public String getAPlace() {
            return APlace;
        }

        public String getATypeQy() {
            return ATypeQy;
        }

        public String getAShengDd() {
            return AShengDd;
        }

        public String getAShiDd() {
            return AShiDd;
        }

        public String getAXianDd() {
            return AXianDd;
        }

        public String getADiQuDd() {
            return ADiQuDd;
        }

        public String getAXiangDd() {
            return AXiangDd;
        }

        public String getACunDd() {
            return ACunDd;
        }

        public String getAArr() {
            return AArr;
        }

        public String getATypeDd() {
            return ATypeDd;
        }

        public String getACarrier() {
            return ACarrier;
        }

        public String getAPhoneCyr() {
            return APhoneCyr;
        }

        public String getATrademark() {
            return ATrademark;
        }

        public String getADisinfection() {
            return ADisinfection;
        }

        public String getAYouXiaoRi() {
            return AYouXiaoRi;
        }

        public String getAVeterinary() {
            return AVeterinary;
        }

        public String getAEarTag() {
            return AEarTag;
        }

        public String getADwPhone() {
            return ADwPhone;
        }

        public String getAXuZhong() {
            return AXuZhong;
        }

        public String getAXuZhongOne() {
            return AXuZhongOne;
        }

        public String getAXuZhongTwo() {
            return AXuZhongTwo;
        }

        public String getADanWei() {
            return ADanWei;
        }

        public String getAYongTu() {
            return AYongTu;
        }

        public String getAYunZai() {
            return AYunZai;
        }

        public String getDateQF() {
            return DateQF;
        }

        public String getAMemo1() {
            return AMemo1;
        }

        public String getAMemo2() {
            return AMemo2;
        }

        public String getAMemo3() {
            return AMemo3;
        }

        public String getSaveId() {
            return SaveId;
        }

        public String getIsPrant() {
            return IsPrant;
        }

        public String getUploadType() {
            return UploadType;
        }

        public String getUploadTypeSheng() {
            return UploadTypeSheng;
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
