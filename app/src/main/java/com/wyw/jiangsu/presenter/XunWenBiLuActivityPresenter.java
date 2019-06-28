package com.wyw.jiangsu.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.XunWenBiLuBean;
import com.wyw.jiangsu.interfac.IXunWenBiLuActivity;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;

/**
 * 询问笔录
 * Created by Windows on 2017/7/7.
 */

public class XunWenBiLuActivityPresenter extends BasePresenter<IXunWenBiLuActivity> {
    public XunWenBiLuActivityPresenter(IXunWenBiLuActivity view) {
        super(view);
    }

    public void upload(XunWenBiLuBean data) {
        if (getLock().isLock()) return;
        getLock().lock();
        addSubscribe(NetClient.xunwenbilu(new Gson().toJson(data))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getErrorCode();
                }).subscribe(errorCode -> {
                            Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
                        }, new ExceptionImp(getView(), getLock()),
                        () -> {
                            getView().isfinish();
                        }));
    }
}
