package com.wyw.jiangsu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.XianChangCheckBiLuBean;
import com.wyw.jiangsu.interfac.IXianChangCheckBiLuActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;

/**
 * Created by Windows on 2017/7/6.
 */

public class XianChangCheckBiLuActivityPresenter extends BasePresenter<IXianChangCheckBiLuActivity> {
    public XianChangCheckBiLuActivityPresenter(IXianChangCheckBiLuActivity view) {
        super(view);
    }

    public void upload(XianChangCheckBiLuBean data) {
        addSubscribe(NetClient.xianchangbilu(new Gson().toJson(data))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getErrorCode();
                }).subscribe(errorCode -> {
                            Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
                        }, new ExceptionImp(getView()),
                        () -> {
                            getView().isfinish();
                        }));
    }
}
