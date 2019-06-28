package com.wyw.jiangsu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.RuChangChaXunBean;
import com.wyw.jiangsu.bean.RuChangQuerenUploadBean;
import com.wyw.jiangsu.interfac.IRuChangQuerenActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by Administrator on 2017/4/11.
 */
public class RuChangQuerenActivityPresenter extends BasePresenter<IRuChangQuerenActivity> {

    public RuChangQuerenActivityPresenter(IRuChangQuerenActivity view) {
        super(view);
    }

    public void upLoad(RuChangQuerenUploadBean data) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(data), "Qua_Entrance", getUserId(),null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        Toast.makeText(MyApplication.getContext(), "保存失败", Toast.LENGTH_SHORT).show();
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getData().getResult();
                }).subscribe(errorCode -> {
                    Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
                }, new ExceptionImp(getView(),getLock()), () -> {
                    Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                    Log.e("HarmlessApplyActivityPr", "complete");
                    getView().uploadsuccess();
                }));
    }

    public void getQuery(String etQuery) {
        addSubscribe(NetClient.getRuchangQuery(etQuery)
                .map(ruChangChaYanQueryBean -> {
                    RuChangChaXunBean.DataBean dataList = new StatusException(ruChangChaYanQueryBean).getObjectData(RuChangChaXunBean.DataBean.class);
                    return dataList;
                })
                .subscribe(bean -> getView().chaxun(bean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void getJiludanhao() {
        addSubscribe(NetClient.getJianyiChuliTongzhihaobean(getUserId())
                .map(guarantineDeclareListDetilBean -> {
                    JianyiChuliTongzhihaobean.DataBean dataList = new StatusException(guarantineDeclareListDetilBean).getObjectData(JianyiChuliTongzhihaobean.DataBean.class);
                    return dataList;
                })
                .subscribe(dataListBean -> getView().jiludan(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
