package com.wyw.jiangsu.bean;

/**
 * Created by wyw on 2017/5/19.
 */

public class DownloadBean {
    private int status;
    private long completedSize;
    private long maxSize;
    private String url;
    private String fileName;
    private String dir;
    private String id;

    public DownloadBean() {
    }

    public DownloadBean(int status, long completedSize, long maxSize,
                        String url, String fileName, String dir, String id) {
        this.status = status;
        this.completedSize = completedSize;
        this.maxSize = maxSize;
        this.url = url;
        this.fileName = fileName;
        this.dir = dir;
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCompletedSize() {
        return completedSize;
    }

    public void setCompletedSize(long completedSize) {
        this.completedSize = completedSize;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
