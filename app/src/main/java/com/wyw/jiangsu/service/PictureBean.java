package com.wyw.jiangsu.service;

/**
 * Created by wyw on 2017/5/11.
 */

public class PictureBean {
    private String id;
    private String localName;
    private String uuid;
    private String saveDir;//存储文件夹
    private long time;
    private int retry;

    public PictureBean() {
    }

    public PictureBean(String id, String localName, String saveDir, long time, int retry, String uuid) {
        this.id = id;
        this.localName = localName;
        this.saveDir = saveDir;
        this.time = time;
        this.retry = retry;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
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
}
