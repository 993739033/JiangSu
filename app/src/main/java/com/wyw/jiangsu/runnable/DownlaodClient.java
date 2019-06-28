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
public class DownlaodClient {
    //public static final String DOMAIN = "http://220.163.128.58:8888/YNSYApp/";
    public static final String DOMAIN = "http://36.111.192.50:8888/JiangSuAPP/";            //APK更新不需要后面的HtmlAshx
    //    public static final String DOMAIN = "http://36.111.192.50:8888/JiangSuAPPCS/";            //APK更新不需要后面的HtmlAshx
    public static final String Test = "http://192.168.0.32/HuNanAPP/";
    private static final String TAG = DownlaodClient.class.getSimpleName();
    private static DownlaodClient mInstance;
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

    public synchronized static DownlaodClient getInstance() {
        if (mInstance == null) {
            mInstance = new DownlaodClient();
        }
        return mInstance;
    }

    private DownlaodClient() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DOMAIN);
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        mRetrofit = retrofitBuilder.client(builder.build()).build();
        requestService = mRetrofit.create(DownloadApi.class);
    }

    public interface DownloadApi {


        @GET("UserDatabase/JiangSu.apk")
        Call<ResponseBody> downloadApk();

        @GET("UserDatabase/UserData.dbx")
        Call<ResponseBody> downloadDb();

        @GET("UserDatabase/patch.jar")
        Call<ResponseBody> downloadPatch();

        @GET("UserDatabase/out.patch")
        Call<ResponseBody> downloadDex();


    }


    public void downloadApk(String path) {
        requestService.downloadApk().enqueue(new DownloadCallBack(path));
    }

    public void downloadDb(String path) {
        requestService.downloadDb().enqueue(new DownloadCallBack(path));
    }

    public void downloadPatch(String path) {
        requestService.downloadPatch().enqueue(new DownloadCallBack(path));
    }

    public void downloadDex(String path) {
        requestService.downloadDex().enqueue(new DownloadCallBack(path));
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
