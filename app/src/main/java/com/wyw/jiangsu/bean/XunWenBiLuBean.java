package com.wyw.jiangsu.bean;

import java.util.List;

/**
 * Created by Windows on 2017/7/10.
 */

public class XunWenBiLuBean {

    private int FGlid;  //案件登记关联id
    private String Fsdate;  //询问时间
    private String Fdz;  //询问地点
    private String Fname;  //被询问人
    private String Fjg;  //询问机关
    private String Fxwr;  //询问人1
    private String Fzfzjh;  //执法证件号1
    private String Fxwr1;  //询问人2
    private String Fzfzjh1;  //执法证件号2
    private String Fjlr;  //记录人
    private String Fsex;  //性别
    private String Favg;  //年龄
    private String Fsfzh;  //身份证号码
    private String Flxdh;  //电话
    private String Fgzdw;  //工作单位
    private String Fzw;  //职务
    private String Fzhuz;  //地址
    private String Fzfry;  //执法人员(执法证号)
    private String A1;      //第一个答
    private String Fshij;  //小时1
    private String Ffengz;  //分钟1
    private String Fshij1;  //小时2
    private String Ffengz1;  //分钟2
    private String FSunitUstrId;//行政ID
    private String FSuserId;   //用户ID
    private String Fajbh;       //案件编号

    private List<RecordOfQuestWDBean> datalist;

    public String getFSuserId() {
        return FSuserId;
    }

    public void setFSuserId(String FSuserId) {
        this.FSuserId = FSuserId;
    }

    public String getFajbh() {
        return Fajbh;
    }

    public void setFajbh(String fajbh) {
        Fajbh = fajbh;
    }

    public String getA1() {
        return A1;
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public List<RecordOfQuestWDBean> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<RecordOfQuestWDBean> datalist) {
        this.datalist = datalist;
    }

    public int getFGlid() {
        return FGlid;
    }

    public void setFGlid(int FGlid) {
        this.FGlid = FGlid;
    }

    public String getFsdate() {
        return Fsdate;
    }

    public void setFsdate(String fsdate) {
        Fsdate = fsdate;
    }

    public String getFdz() {
        return Fdz;
    }

    public void setFdz(String fdz) {
        Fdz = fdz;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getFjg() {
        return Fjg;
    }

    public void setFjg(String fjg) {
        Fjg = fjg;
    }

    public String getFxwr() {
        return Fxwr;
    }

    public void setFxwr(String fxwr) {
        Fxwr = fxwr;
    }

    public String getFzfzjh() {
        return Fzfzjh;
    }

    public void setFzfzjh(String fzfzjh) {
        Fzfzjh = fzfzjh;
    }

    public String getFxwr1() {
        return Fxwr1;
    }

    public void setFxwr1(String fxwr1) {
        Fxwr1 = fxwr1;
    }

    public String getFzfzjh1() {
        return Fzfzjh1;
    }

    public void setFzfzjh1(String fzfzjh1) {
        Fzfzjh1 = fzfzjh1;
    }

    public String getFjlr() {
        return Fjlr;
    }

    public void setFjlr(String fjlr) {
        Fjlr = fjlr;
    }

    public String getFsex() {
        return Fsex;
    }

    public void setFsex(String fsex) {
        Fsex = fsex;
    }

    public String getFavg() {
        return Favg;
    }

    public void setFavg(String favg) {
        Favg = favg;
    }

    public String getFsfzh() {
        return Fsfzh;
    }

    public void setFsfzh(String fsfzh) {
        Fsfzh = fsfzh;
    }

    public String getFlxdh() {
        return Flxdh;
    }

    public void setFlxdh(String flxdh) {
        Flxdh = flxdh;
    }

    public String getFgzdw() {
        return Fgzdw;
    }

    public void setFgzdw(String fgzdw) {
        Fgzdw = fgzdw;
    }

    public String getFzw() {
        return Fzw;
    }

    public void setFzw(String fzw) {
        Fzw = fzw;
    }

    public String getFzhuz() {
        return Fzhuz;
    }

    public void setFzhuz(String fzhuz) {
        Fzhuz = fzhuz;
    }

    public String getFzfry() {
        return Fzfry;
    }

    public void setFzfry(String fzfry) {
        Fzfry = fzfry;
    }

    public String getFshij() {
        return Fshij;
    }

    public void setFshij(String fshij) {
        Fshij = fshij;
    }

    public String getFfengz() {
        return Ffengz;
    }

    public void setFfengz(String ffengz) {
        Ffengz = ffengz;
    }

    public String getFshij1() {
        return Fshij1;
    }

    public void setFshij1(String fshij1) {
        Fshij1 = fshij1;
    }

    public String getFfengz1() {
        return Ffengz1;
    }

    public void setFfengz1(String ffengz1) {
        Ffengz1 = ffengz1;
    }

    public String getFSunitUstrId() {
        return FSunitUstrId;
    }

    public void setFSunitUstrId(String FSunitUstrId) {
        this.FSunitUstrId = FSunitUstrId;
    }

    public static class RecordOfQuestWDBean {
        private int FSserialNum;            //编号
        private String FSask;               //问
        private String FSanswer;            //答

        public int getFSserialNum() {
            return FSserialNum;
        }

        public void setFSserialNum(int FSserialNum) {
            this.FSserialNum = FSserialNum;
        }

        public String getFSask() {
            return FSask;
        }

        public void setFSask(String FSask) {
            this.FSask = FSask;
        }

        public String getFSanswer() {
            return FSanswer;
        }

        public void setFSanswer(String FSanswer) {
            this.FSanswer = FSanswer;
        }
    }
}
