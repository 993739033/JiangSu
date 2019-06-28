package com.wyw.jiangsu.presenter;

import android.util.Log;

import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.interfac.ICommonSupervisionDetil;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;
import com.wyw.jiangsu.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by HUANG on 2017/8/1.
 */
public class CommonSupervisionDetilPresenter extends BasePresenter<ICommonSupervisionDetil>{
    private final OkHttpClient mOkHttpClient;

    public CommonSupervisionDetilPresenter(ICommonSupervisionDetil view) {
        super(view);
        mOkHttpClient = new OkHttpClient();
    }

    public void getTopDetil(String table, String fstid) {
        addSubscribe(NetClient.getCompanyDetil(table, fstid)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(data -> new StatusException(data).getObjectData(CommonSupervisionBean.Data.class))
                .subscribe(data -> {
                    getView().setCompanyDetil(data);
                    getView().hideProgress();
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }
    public void uploadCommonSupervision(String type, String json, String uuid) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uploadCommonSupervision(getUserId(), type, json, uuid)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(data -> new StatusException(data).getObjectData(BaseMsgBean.Data.class))
                .subscribe(data -> {
                    getView().uploadSuccess(data);
                    getView().hideProgress();
                }, new ExceptionImp(getView(),getLock()), new CompleteImp(getView())));
    }
    public void downPrintImg(String url, String name, int num, android.os.Handler handler) {
       /* addSubscribe(NetClient.downPrintImg(url)
                .subscribe(responseBody -> {
                    if (responseBody.contentLength() > 0) {
                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len = 0;
                        FileOutputStream fos = null;
                        try {
                            Log.e("czy", "size=====" + responseBody.contentLength());
                            File file = new File(FileUtil.getInstance().getDirPrint(), name);
                            fos = new FileOutputStream(file);
                            is = responseBody.byteStream();

                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                            }
                            fos.flush();
                            getView().downImgSuccess();
                        } catch (IOException e) {
                            e.printStackTrace();
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
                    } else {
                        getView().downImgFail();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().downImgFail();
                    }
                }, new CompleteImp(getView())));*/


        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (responseBody.contentLength() > 0) {
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        Log.e("czy", "size=====" + responseBody.contentLength());
                        File file = new File(FileUtil.getInstance().getDirPrint(), name);
                        fos = new FileOutputStream(file);
                        is = responseBody.byteStream();

                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.flush();
                        getView().downImgSuccess(num);
                    } catch (IOException e) {
                        Log.e("czy",e.getMessage());
                        getView().downImgFail();
                        e.printStackTrace();
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
                } else {
                    getView().downImgFail();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("czy",e.getMessage());
                handler.sendEmptyMessage(1);
            }
        });


    }
}
