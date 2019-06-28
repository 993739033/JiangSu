package com.wyw.jiangsu.bean;

/**
 * Created by HUANG on 2017/7/28.
 */
public class DownPrintImgBean {
    private String id;

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    private String localName;
    private String uuid;
    private String saveDir;//存储文件夹
    private long time;
    private int retry;
    public DownPrintImgBean() {
    }

    public DownPrintImgBean(String id, String localName, String saveDir, long time, int retry, String uuid) {
        this.id = id;
        this.localName = localName;
        this.saveDir = saveDir;
        this.time = time;
        this.retry = retry;
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }
}
