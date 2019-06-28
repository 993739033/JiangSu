package com.wyw.jiangsu.runnable;

import android.util.Log;

import com.wyw.jiangsu.bean.DownPrintImgBean;
import com.wyw.jiangsu.interfac.IDownPrintImgListener;
import com.wyw.jiangsu.service.DownPrintPicService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HUANG on 2017/7/28.
 */
public class DownPrintImg implements Runnable {
    private IDownPrintImgListener listener;

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    private int retry;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private OkHttpClient client;
    private DownPrintImgBean bean;
    public void setListener(IDownPrintImgListener listener) {
        this.listener = listener;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }
    public DownPrintImg(DownPrintImgBean bean){
        this.bean=bean;
        setId(bean.getId());
        setRetry(bean.getRetry());
    }
    @Override
    public void run() {
        Log.e(DownPrintPicService.class.getSimpleName(),"run");
      File file = new File(bean.getSaveDir(),bean.getLocalName());
       /*  if (file.exists()) {
            onError(IDownPrintImgListener.UPLOAD_ERROR_FILE_EXIST);
            return;
        }*/
        Request request = new Request.Builder().url(bean.getUuid()).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onError(IDownPrintImgListener.UPLOAD_ERROR_RESPONSE_NOT_SUCCESS);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    onError(IDownPrintImgListener.UPLOAD_ERROR_RESPONSE_NOT_SUCCESS);
                    return;
                }
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);

                    }
                    fos.flush();
                    onCompleted();
                } catch (IOException e) {
                    onError(IDownPrintImgListener.UPLOAD_ERROR_RESPONSE_NOT_SUCCESS);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {

                    }
                }
            }

        });

    }

    public void onError(int code) {
        if (listener != null)
            listener.onError(this, code);
    }

    public void onCompleted() {
        if (listener!=null)
            listener.onCompleted(this);
    }
}