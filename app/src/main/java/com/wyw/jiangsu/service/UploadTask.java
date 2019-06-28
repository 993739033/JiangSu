package com.wyw.jiangsu.service;


import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.runnable.NetClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.wyw.jiangsu.service.UploadPictureListener.UPLOAD_ERROR_ERRORCODE_NOT_ZERO;
import static com.wyw.jiangsu.service.UploadPictureListener.UPLOAD_ERROR_ERRORCODE_OTHER;
import static com.wyw.jiangsu.service.UploadPictureListener.UPLOAD_ERROR_FILE_NOT_FOUND;
import static com.wyw.jiangsu.service.UploadPictureListener.UPLOAD_ERROR_RESPONSE_NOT_SUCCESS;

/**
 * Created by wyw on 2017/5/11.
 */

public class UploadTask implements Runnable {
    private String id;
    private String localName;
    private String address;//存储文件夹
    private Long time;
    private int retry;//已经重试的次数
    private PictureBean bean;
    private Context mContext;
    private OkHttpClient client;
    private Gson gson;
    private UploadPictureListener listener;

    public UploadTask(Builder builder) {
        this.bean = builder.getBean();
        this.mContext = builder.getmContext();
        this.id = builder.getId();
        this.localName = builder.getLocalName();
        this.address = builder.getAddress();
        this.time = builder.getTime();
        this.gson = new Gson();
    }

    public void deleteFile() {
        new File(address, localName).delete();
    }

    @Override
    public void run() {
        onStart();
        File file = new File(bean.getSaveDir(), bean.getLocalName());
        if (!file.exists()) {
//            throw new Exception("文件不存在");
            onError(UPLOAD_ERROR_FILE_NOT_FOUND);
            return;
        }
        MultipartBody body = new MultipartBody.Builder()
                .addFormDataPart(bean.getLocalName(), bean.getLocalName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .setType(MultipartBody.FORM)
                .build();
        Request request = new Request.Builder()
//                .url("http://192.168.0.221:8886/QingDaoAPP/HtmlAshx/UplaodJYFiles.ashx")
                .url(NetClient.URL+"UplaodImage.ashx")                     //本地测试
//                .url(NetClient.DOMAIN_TEST + "UplaodImage.ashx")                    //服务器测试
//                .url(NetClient.DOMAIN + "UplaodImage.ashx")                    //服务器
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.e("UploadTask", "response.code():" + response.code());
//                Log.e("UploadTask", response.body().string() + "");
                String result = response.body().string();
                BaseMsgBean msgBean = gson.fromJson(result, BaseMsgBean.class);
//                writeText(result, this);
                if (msgBean.getErrorCode() != 0) {
                    onError(UPLOAD_ERROR_ERRORCODE_NOT_ZERO);
                } else {
                    onCompleted();
                }
            } else {
                onError(UPLOAD_ERROR_RESPONSE_NOT_SUCCESS);
            }
        } catch (IOException e) {
            e.printStackTrace();
//            onError(UPLOAD_ERROR_ERRORCODE_OTHER);
            onError(UPLOAD_ERROR_ERRORCODE_NOT_ZERO);
        } catch (Exception e) {
            e.printStackTrace();
//            onError(UPLOAD_ERROR_ERRORCODE_OTHER);
            onError(UPLOAD_ERROR_ERRORCODE_NOT_ZERO);
        }
    }

    /**
     * 追加文件：使用FileWriter
     */
    public static void writeText(String result, UploadTask task) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        FileWriter writer = null;
        File file = new File(Environment.getExternalStorageDirectory(), "test.txt");
        String content = "result:" + result + "\t localName: " + task.getLocalName() +
                "\tretry: " + task.getRetry() + "\t时间" + format.format(new Date()) + "\r\n";
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(file, true);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void onCompleted() {
        if (listener == null) {
            return;
        }
        listener.onCompleted(this);
    }

    private void onStart() {
        if (listener == null) {
            return;
        }
        listener.onStart(this);
    }

    private void onError(int code) {
        if (listener == null) {
            return;
        }
        listener.onError(this, code);
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public UploadPictureListener getListener() {
        return listener;
    }

    public void setListener(UploadPictureListener listener) {
        this.listener = listener;
    }

    public PictureBean getBean() {
        return bean;
    }

    public void setBean(PictureBean bean) {
        this.bean = bean;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public static class Builder {
        private String id;
        private String localName;
        private String address;//存储文件夹
        private PictureBean bean;
        private Context mContext;
        private Long time;
        private int retry;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setPictureBean(PictureBean bean) {
            this.bean = bean;
            this.localName = bean.getLocalName();
            this.address = bean.getSaveDir();
            this.id = bean.getId();
            this.time = bean.getTime();
            this.retry = bean.getRetry();
            return this;
        }

        public Long getTime() {
            return time;
        }

        public UploadTask build() {
            return new UploadTask(this);
        }

        public String getId() {
            return id;
        }

        public String getLocalName() {
            return localName;
        }


        public String getAddress() {
            return address;
        }

        public PictureBean getBean() {
            return bean;
        }

        public Context getmContext() {
            return mContext;
        }
    }

    public static UploadTask parse(PictureBean bean, Context mContext) {
        return new Builder(mContext).setPictureBean(bean).build();
    }
}
