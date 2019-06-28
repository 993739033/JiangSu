package com.wyw.jiangsu.presenter;

import android.util.Log;

import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.interfac.ICommonSupervisionDetilActivity;
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

import rx.functions.Action1;


/**
 * Created by wyw on 2017/6/21.
 */

public class CommonSupervisionDetilActivityPresenter extends BasePresenter<ICommonSupervisionDetilActivity> {

    public CommonSupervisionDetilActivityPresenter(ICommonSupervisionDetilActivity view) {
        super(view);
    }

    public void getCompanyDetil(String table, String fstid) {
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
    public void downPrintImg(String url, String name) {
            addSubscribe(NetClient.downPrintImg(url)
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
                    }, new CompleteImp(getView())));

    }
}
