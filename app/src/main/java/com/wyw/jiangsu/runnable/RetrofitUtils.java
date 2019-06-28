package com.wyw.jiangsu.runnable;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by wyw on 2016/8/10.
 */
public class RetrofitUtils {
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String MULTIPART_FORM_IMAGE = "image/*";
    // RequestBody.create 默认的文本类型
    public static final String MULTIPART_FORM_JSON = "application/json; charset=utf-8";
    public static final String MULTIPART_DOWN = "application/octet-stream";
    public static final String MULTIPART_FROM_TEXT = "NoDealWithInfoBean/*; charset=utf-8";

    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    public static RequestBody createPartFromFile(File file) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), file);
    }

    /**
     * @param partName
     * @param filePath
     * @return
     */
    public static MultipartBody.Part prepareFilePart(String partName, String filePath) throws Exception {
       File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        // 为file建立RequestBody实例
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);

        // MultipartBody.Part借助文件名完成最终的上传
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}
