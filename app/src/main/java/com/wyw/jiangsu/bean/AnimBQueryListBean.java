package com.wyw.jiangsu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhyzh on 2017/3/30.
 */

public class AnimBQueryListBean extends BaseArrayObjectEntity<AnimBQueryListBean.DataListEntity>implements Serializable {

//    /**
//     * errorCode : 0
//     * errorMsg : msg
//     * dataList : [{"FStId":"65","FSguidId":"42b5dc18-5aa8-45f2-be4e-aeef15c2c661","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"63","AQNumber":"12356","AQCargoOwner":"杨柳鑫","AQPhone":"13638486279","AQQuantity":"100","AQShiQy":"常州市","AQXianQy":"天宁区","AQDiQuQy":"","AQXiangQy":"青龙街道","AQCunQy":"天宁区新希望养殖场","AQPlace":"","AQTypeQy":"养殖场","AQShiDd":"南京市","AQXianDd":"玄武区","AQDiQuDd":"","AQXiangDd":"","AQCunDd":"青龙屠宰场","AQArr":"","AQTypeDd":"养殖场","AQVeterinary":"晏子龙","AQEarTag":"123456712345100-199","AQXuZhong":"马","AQXuZhongOne":"","AQXuZhongTwo":"","AQDanWei":"匹","AQYongTu":"交易（销售）","DateQF":"2017-03-23","AQMemo1":"","AQMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AQMemo3":"江苏省南京市玄武区青龙屠宰场","IsPrant":"已打印","SaveId":"0","AQYouXiaoRi":"0","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"64","FSguidId":"00da05ff-afc3-4b99-8ff1-fb65814b9848","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"63","AQNumber":"12356","AQCargoOwner":"杨柳鑫","AQPhone":"13638486279","AQQuantity":"100","AQShiQy":"常州市","AQXianQy":"天宁区","AQDiQuQy":"","AQXiangQy":"青龙街道","AQCunQy":"天宁区新希望养殖场","AQPlace":"","AQTypeQy":"养殖场","AQShiDd":"南京市","AQXianDd":"玄武区","AQDiQuDd":"","AQXiangDd":"","AQCunDd":"青龙屠宰场","AQArr":"","AQTypeDd":"养殖场","AQVeterinary":"晏子龙","AQEarTag":"123456712345100-199","AQXuZhong":"马","AQXuZhongOne":"","AQXuZhongTwo":"","AQDanWei":"匹","AQYongTu":"交易（销售）","DateQF":"2017-03-23","AQMemo1":"","AQMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AQMemo3":"江苏省南京市玄武区青龙屠宰场","IsPrant":"已打印","SaveId":"0","AQYouXiaoRi":"0","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"63","FSguidId":"5d4bf35d-3816-4ea3-9bed-ddf415c2a514","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"63","AQNumber":"12356","AQCargoOwner":"杨柳鑫","AQPhone":"13638486279","AQQuantity":"100","AQShiQy":"常州市","AQXianQy":"天宁区","AQDiQuQy":"","AQXiangQy":"青龙街道","AQCunQy":"天宁区新希望养殖场","AQPlace":"","AQTypeQy":"养殖场","AQShiDd":"南京市","AQXianDd":"玄武区","AQDiQuDd":"","AQXiangDd":"","AQCunDd":"青龙屠宰场","AQArr":"","AQTypeDd":"养殖场","AQVeterinary":"晏子龙","AQEarTag":"123456712345100-199","AQXuZhong":"马","AQXuZhongOne":"","AQXuZhongTwo":"","AQDanWei":"匹","AQYongTu":"交易（销售）","DateQF":"2017-03-23","AQMemo1":"","AQMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AQMemo3":"江苏省南京市玄武区青龙屠宰场","IsPrant":"已打印","SaveId":"0","AQYouXiaoRi":"0","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"62","FSguidId":"0405438d-dde6-4e0a-ac0d-fb36b5d60cee","FScTime":"2017-03-28","FSuTime":"2017-03-28","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"63","AQNumber":"12356","AQCargoOwner":"杨柳鑫","AQPhone":"13638486279","AQQuantity":"100","AQShiQy":"常州市","AQXianQy":"天宁区","AQDiQuQy":"","AQXiangQy":"青龙街道","AQCunQy":"天宁区新希望养殖场","AQPlace":"","AQTypeQy":"养殖场","AQShiDd":"南京市","AQXianDd":"玄武区","AQDiQuDd":"","AQXiangDd":"","AQCunDd":"青龙屠宰场","AQArr":"","AQTypeDd":"养殖场","AQVeterinary":"晏子龙","AQEarTag":"123456712345100-199","AQXuZhong":"马","AQXuZhongOne":"","AQXuZhongTwo":"","AQDanWei":"匹","AQYongTu":"交易（销售）","DateQF":"2017-03-23","AQMemo1":"","AQMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AQMemo3":"江苏省南京市玄武区青龙屠宰场","IsPrant":"已打印","SaveId":"0","AQYouXiaoRi":"0","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"61","FSguidId":"d059524c-dac0-43e5-9a2b-03ed5e7c802a","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"63","AQNumber":"12356","AQCargoOwner":"杨柳鑫","AQPhone":"13638486279","AQQuantity":"100","AQShiQy":"常州市","AQXianQy":"天宁区","AQDiQuQy":"","AQXiangQy":"青龙街道","AQCunQy":"天宁区新希望养殖场","AQPlace":"","AQTypeQy":"养殖场","AQShiDd":"南京市","AQXianDd":"玄武区","AQDiQuDd":"","AQXiangDd":"","AQCunDd":"青龙屠宰场","AQArr":"","AQTypeDd":"养殖场","AQVeterinary":"晏子龙","AQEarTag":"123456712345100-199","AQXuZhong":"马","AQXuZhongOne":"","AQXuZhongTwo":"","AQDanWei":"匹","AQYongTu":"交易（销售）","DateQF":"2017-03-23","AQMemo1":"","AQMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AQMemo3":"江苏省南京市玄武区青龙屠宰场","IsPrant":"已打印","SaveId":"0","AQYouXiaoRi":"0","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""},{"FStId":"60","FSguidId":"dbf543f8-9e3a-4918-96f2-d9b81b98ad92","FScTime":"2017-03-27","FSuTime":"2017-03-27","FSuserId":"1","FSuserName":"超级管理员","FSunitId":"1","FSunitUstrId":"01","FSunitName":"江苏省","FSenterpriseId":"1","FSenterpriseName":"超级管理员单位","FSaudit":"0","FSreviewer":"","FSmemo1":"","FSmemo2":"","FSmemo3":"","FGlid":"63","AQNumber":"12356","AQCargoOwner":"杨柳鑫","AQPhone":"13638486279","AQQuantity":"100","AQShiQy":"常州市","AQXianQy":"天宁区","AQDiQuQy":"","AQXiangQy":"青龙街道","AQCunQy":"天宁区新希望养殖场","AQPlace":"","AQTypeQy":"养殖场","AQShiDd":"南京市","AQXianDd":"玄武区","AQDiQuDd":"","AQXiangDd":"","AQCunDd":"青龙屠宰场","AQArr":"","AQTypeDd":"养殖场","AQVeterinary":"晏子龙","AQEarTag":"123456712345100-199","AQXuZhong":"马","AQXuZhongOne":"","AQXuZhongTwo":"","AQDanWei":"匹","AQYongTu":"交易（销售）","DateQF":"2017-03-23","AQMemo1":"","AQMemo2":"江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场","AQMemo3":"江苏省南京市玄武区青龙屠宰场","IsPrant":"已打印","SaveId":"0","AQYouXiaoRi":"0","UploadType":"0","UploadTypeSheng":"0","FSDel":"False","FSm1":"","FSm2":"","FSm3":"","FSm4":"","FSm5":""}]
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

    public static class DataListEntity implements  Serializable{
        /**
         * FStId : 65
         * FSguidId : 42b5dc18-5aa8-45f2-be4e-aeef15c2c661
         * FScTime : 2017-03-28
         * FSuTime : 2017-03-28
         * FSuserId : 1
         * FSuserName : 超级管理员
         * FSunitId : 1
         * FSunitUstrId : 01
         * FSunitName : 江苏省
         * FSenterpriseId : 1
         * FSenterpriseName : 超级管理员单位
         * FSaudit : 0
         * FSreviewer :
         * FSmemo1 :
         * FSmemo2 :
         * FSmemo3 :
         * FGlid : 63
         * AQNumber : 12356
         * AQCargoOwner : 杨柳鑫
         * AQPhone : 13638486279
         * AQQuantity : 100
         * AQShiQy : 常州市
         * AQXianQy : 天宁区
         * AQDiQuQy :
         * AQXiangQy : 青龙街道
         * AQCunQy : 天宁区新希望养殖场
         * AQPlace :
         * AQTypeQy : 养殖场
         * AQShiDd : 南京市
         * AQXianDd : 玄武区
         * AQDiQuDd :
         * AQXiangDd :
         * AQCunDd : 青龙屠宰场
         * AQArr :
         * AQTypeDd : 养殖场
         * AQVeterinary : 晏子龙
         * AQEarTag : 123456712345100-199
         * AQXuZhong : 马
         * AQXuZhongOne :
         * AQXuZhongTwo :
         * AQDanWei : 匹
         * AQYongTu : 交易（销售）
         * DateQF : 2017-03-23
         * AQMemo1 :
         * AQMemo2 : 江苏省常州市天宁区青龙街道乡(镇)天宁区新希望养殖场
         * AQMemo3 : 江苏省南京市玄武区青龙屠宰场
         * IsPrant : 已打印
         * SaveId : 0
         * AQYouXiaoRi : 0
         * UploadType : 0
         * UploadTypeSheng : 0
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
        private String AQNumber;
        private String AQCargoOwner;
        private String AQPhone;
        private String AQQuantity;
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
        private String DateQF;
        private String AQMemo1;
        private String AQMemo2;
        private String AQMemo3;
        private String IsPrant;
        private String SaveId;
        private String AQYouXiaoRi;
        private String UploadType;
        private String UploadTypeSheng;
        private String FSDel;
        private String FSm1;
        private String FSm2;
        private String FSm3;
        private String FSm4;
        private String FSm5;
        private String guid;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

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

        public void setAQNumber(String AQNumber) {
            this.AQNumber = AQNumber;
        }

        public void setAQCargoOwner(String AQCargoOwner) {
            this.AQCargoOwner = AQCargoOwner;
        }

        public void setAQPhone(String AQPhone) {
            this.AQPhone = AQPhone;
        }

        public void setAQQuantity(String AQQuantity) {
            this.AQQuantity = AQQuantity;
        }

        public void setAQShiQy(String AQShiQy) {
            this.AQShiQy = AQShiQy;
        }

        public void setAQXianQy(String AQXianQy) {
            this.AQXianQy = AQXianQy;
        }

        public void setAQDiQuQy(String AQDiQuQy) {
            this.AQDiQuQy = AQDiQuQy;
        }

        public void setAQXiangQy(String AQXiangQy) {
            this.AQXiangQy = AQXiangQy;
        }

        public void setAQCunQy(String AQCunQy) {
            this.AQCunQy = AQCunQy;
        }

        public void setAQPlace(String AQPlace) {
            this.AQPlace = AQPlace;
        }

        public void setAQTypeQy(String AQTypeQy) {
            this.AQTypeQy = AQTypeQy;
        }

        public void setAQShiDd(String AQShiDd) {
            this.AQShiDd = AQShiDd;
        }

        public void setAQXianDd(String AQXianDd) {
            this.AQXianDd = AQXianDd;
        }

        public void setAQDiQuDd(String AQDiQuDd) {
            this.AQDiQuDd = AQDiQuDd;
        }

        public void setAQXiangDd(String AQXiangDd) {
            this.AQXiangDd = AQXiangDd;
        }

        public void setAQCunDd(String AQCunDd) {
            this.AQCunDd = AQCunDd;
        }

        public void setAQArr(String AQArr) {
            this.AQArr = AQArr;
        }

        public void setAQTypeDd(String AQTypeDd) {
            this.AQTypeDd = AQTypeDd;
        }

        public void setAQVeterinary(String AQVeterinary) {
            this.AQVeterinary = AQVeterinary;
        }

        public void setAQEarTag(String AQEarTag) {
            this.AQEarTag = AQEarTag;
        }

        public void setAQXuZhong(String AQXuZhong) {
            this.AQXuZhong = AQXuZhong;
        }

        public void setAQXuZhongOne(String AQXuZhongOne) {
            this.AQXuZhongOne = AQXuZhongOne;
        }

        public void setAQXuZhongTwo(String AQXuZhongTwo) {
            this.AQXuZhongTwo = AQXuZhongTwo;
        }

        public void setAQDanWei(String AQDanWei) {
            this.AQDanWei = AQDanWei;
        }

        public void setAQYongTu(String AQYongTu) {
            this.AQYongTu = AQYongTu;
        }

        public void setDateQF(String DateQF) {
            this.DateQF = DateQF;
        }

        public void setAQMemo1(String AQMemo1) {
            this.AQMemo1 = AQMemo1;
        }

        public void setAQMemo2(String AQMemo2) {
            this.AQMemo2 = AQMemo2;
        }

        public void setAQMemo3(String AQMemo3) {
            this.AQMemo3 = AQMemo3;
        }

        public void setIsPrant(String IsPrant) {
            this.IsPrant = IsPrant;
        }

        public void setSaveId(String SaveId) {
            this.SaveId = SaveId;
        }

        public void setAQYouXiaoRi(String AQYouXiaoRi) {
            this.AQYouXiaoRi = AQYouXiaoRi;
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

        public String getAQNumber() {
            return AQNumber;
        }

        public String getAQCargoOwner() {
            return AQCargoOwner;
        }

        public String getAQPhone() {
            return AQPhone;
        }

        public String getAQQuantity() {
            return AQQuantity;
        }

        public String getAQShiQy() {
            return AQShiQy;
        }

        public String getAQXianQy() {
            return AQXianQy;
        }

        public String getAQDiQuQy() {
            return AQDiQuQy;
        }

        public String getAQXiangQy() {
            return AQXiangQy;
        }

        public String getAQCunQy() {
            return AQCunQy;
        }

        public String getAQPlace() {
            return AQPlace;
        }

        public String getAQTypeQy() {
            return AQTypeQy;
        }

        public String getAQShiDd() {
            return AQShiDd;
        }

        public String getAQXianDd() {
            return AQXianDd;
        }

        public String getAQDiQuDd() {
            return AQDiQuDd;
        }

        public String getAQXiangDd() {
            return AQXiangDd;
        }

        public String getAQCunDd() {
            return AQCunDd;
        }

        public String getAQArr() {
            return AQArr;
        }

        public String getAQTypeDd() {
            return AQTypeDd;
        }

        public String getAQVeterinary() {
            return AQVeterinary;
        }

        public String getAQEarTag() {
            return AQEarTag;
        }

        public String getAQXuZhong() {
            return AQXuZhong;
        }

        public String getAQXuZhongOne() {
            return AQXuZhongOne;
        }

        public String getAQXuZhongTwo() {
            return AQXuZhongTwo;
        }

        public String getAQDanWei() {
            return AQDanWei;
        }

        public String getAQYongTu() {
            return AQYongTu;
        }

        public String getDateQF() {
            return DateQF;
        }

        public String getAQMemo1() {
            return AQMemo1;
        }

        public String getAQMemo2() {
            return AQMemo2;
        }

        public String getAQMemo3() {
            return AQMemo3;
        }

        public String getIsPrant() {
            return IsPrant;
        }

        public String getSaveId() {
            return SaveId;
        }

        public String getAQYouXiaoRi() {
            return AQYouXiaoRi;
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
