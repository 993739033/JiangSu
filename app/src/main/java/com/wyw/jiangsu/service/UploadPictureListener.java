package com.wyw.jiangsu.service;

/**
 * Created by wyw on 2017/5/11.
 */

public interface UploadPictureListener {
    int UPLOAD_ERROR_FILE_NOT_FOUND = 1;//没有找到文件
    int UPLOAD_ERROR_RESPONSE_NOT_SUCCESS = 2;//响应失败
    int UPLOAD_ERROR_ERRORCODE_NOT_ZERO = 3;//响应码不是0
    int UPLOAD_ERROR_ERRORCODE_OTHER = 4;

    void onStart(UploadTask downloadTask);

    void onError(UploadTask downloadTask, int errorCode);

    void onCompleted(UploadTask downloadTask);
}