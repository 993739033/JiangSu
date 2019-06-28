package com.wyw.jiangsu.interfac;

import com.wyw.jiangsu.runnable.DownPrintImg;

/**
 * Created by HUANG on 2017/7/28.
 */
public interface IDownPrintImgListener {
    int UPLOAD_ERROR_FILE_EXIST = 1;//文件已存在
    int UPLOAD_ERROR_RESPONSE_NOT_SUCCESS = 2;//下载失败


    void onStart(DownPrintImg downPrintImg);
    void onError(DownPrintImg downPrintImg,int code);
    void onCompleted(DownPrintImg downPrintImg);
}
