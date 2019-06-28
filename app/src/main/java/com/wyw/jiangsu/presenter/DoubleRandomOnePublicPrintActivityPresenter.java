package com.wyw.jiangsu.presenter;

import android.util.Log;

import com.wyw.jiangsu.interfac.IDoubleRandomOnePublicPrintActivity;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.functions.Action0;

/**
 * Created by mnkj on 2018/1/26.
 */

public class DoubleRandomOnePublicPrintActivityPresenter extends BasePresenter<IDoubleRandomOnePublicPrintActivity>  {
    public DoubleRandomOnePublicPrintActivityPresenter(IDoubleRandomOnePublicPrintActivity view) {
        super(view);
    }

    public void uploadDoubleRandom(String json) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uploadDoubleRandom(getUserId(),json)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getErrorCode();
                }).subscribe(errorCode -> {
                    if (errorCode == 0) {
                        getView().uploadSuccess();
                    }
                    getView().hideProgress();
                }, new ExceptionImp(getView(), getLock()), new Action0() {
                    @Override
                    public void call() {

                    }
                }));
    }

    public void downPrintImg(String url, String name) {
        addSubscribe(NetClient.downPrintImg(url)
                .subscribe(responseBody -> {
                    if (responseBody.contentLength() > 0) {
                        InputStream is = null;
                        byte[] buf = new byte[2048];
                        int len = 0;
                        FileOutputStream fos = null;
                        try {
                            Log.e("ssj", "size=====" + responseBody.contentLength());
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
                },new ExceptionImp(getView())
                , new Action0() {
                    @Override
                    public void call() {

                    }
                }));

    }

}
