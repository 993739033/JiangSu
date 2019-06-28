package com.wyw.jiangsu.bean;

/**
 * Created by HUANG on 2017/6/21.
 */
public class FileBean {
    private String fileName;
    private String modifyDate;
    //文件类型
    private String fileType;
    //文件路径
    private String filePath;

    public FileBean(String fileName, String modifyDate,String fileType,String filePath) {
        this.fileName = fileName;
        this.modifyDate = modifyDate;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    public FileBean() {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

}
