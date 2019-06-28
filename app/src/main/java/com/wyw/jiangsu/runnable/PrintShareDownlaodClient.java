package com.wyw.jiangsu.runnable;


import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by wyw on 2016/8/30.
 */
public class PrintShareDownlaodClient {
    public static final String SharePrrintApk = "https://12.shouji.com.cn/wb/soft/2017/";


    private static PrintShareDownlaodClient mInstance;
    private Retrofit mRetrofit;

    private static DownloadApi requestService;

    /**
     * 通过这个方法来调用网络请求的接口
     *
     * @return
     */
    public static DownloadApi getRequest() {
        return getInstance().requestService;
    }

    public synchronized static PrintShareDownlaodClient getInstance() {
        if (mInstance == null) {
            mInstance = new PrintShareDownlaodClient();
        }
        return mInstance;
    }

    private PrintShareDownlaodClient() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SharePrrintApk);
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        mRetrofit = retrofitBuilder.client(builder.build()).build();
        requestService = mRetrofit.create(DownloadApi.class);
    }

    public interface DownloadApi {

        @GET("20170711/6658002285.apk")
        Call<ResponseBody> downloadPrintShare();


    }

    public void downloadPrintShare(String path) {
        requestService.downloadPrintShare().enqueue(new DownloadCallBack(path));
    }

    private class DownloadCallBack implements Callback<ResponseBody> {
        private String path;

        public DownloadCallBack(String path) {
            this.path = path;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                InputStream is = response.body().byteStream();
                FileOutputStream fos = new FileOutputStream(path);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                fos.close();
                bis.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
                onFailure(null, e);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            //下载失败
            Toast.makeText(MyApplication.getContext(), "请打开网络重新打开app: " + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
