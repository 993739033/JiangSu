package com.wyw.jiangsu.runnable;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by wyw on 2017/1/6.
 * 用来下载显示进度的监听
 */

public abstract class DownLoadProgressCallback extends RetrofitCallback<ResponseBody> {
    File file;//保存的文件

    public DownLoadProgressCallback(String fileName) {
        file = new File(fileName);
    }

    public DownLoadProgressCallback(File file) {
        this.file = file;
    }

    @Override
    protected void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            InputStream is = response.body().byteStream();
            //获取文件总长度
            long totalLength = is.available();

            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int progress =0 ;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                progress += len;
                //此处进行更新操作
                //len即可理解为已下载的字节数
                onDownloading(progress, totalLength,progress == totalLength);
                Log.e("DownLoadProgressCallbac", "progress: " + progress + " totalLength: " + totalLength + "  end:" + (progress == totalLength));
            }
            fos.flush();
            fos.close();
            bis.close();
            is.close();
            //此处就代表更新结束
        } catch (IOException e) {
            e.printStackTrace();
            onFailure(call,e);
        }
    }

    protected abstract void onDownloading(int progress, long totalLength, boolean end);


}
