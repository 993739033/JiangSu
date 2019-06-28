package com.wyw.jiangsu.bean.local;

import java.io.File;

/**
 * Created by wyw on 2016/12/29.
 */

public class UploadPictureBean {
    private File file;
    private int length;
    private String picName;
    private String uuid;
    private boolean last;
    private String picContent;

    public String getPicContent() {
        return picContent;
    }

    public void setPicContent(String picContent) {
        this.picContent = picContent;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
