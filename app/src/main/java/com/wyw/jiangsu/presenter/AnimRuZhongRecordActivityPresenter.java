package com.wyw.jiangsu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.interfac.IAnimRuZhongRecordActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by wyw on 2017/2/9.
 */
public class AnimRuZhongRecordActivityPresenter extends BasePresenter<IAnimRuZhongRecordActivity> {
    public AnimRuZhongRecordActivityPresenter(IAnimRuZhongRecordActivity view) {
        super(view);
    }


    public void getJiludanhao() {
        addSubscribe(NetClient.getJianyiChuliTongzhihaobean(getUserId())
                .map(guarantineDeclareListDetilBean -> {
                    JianyiChuliTongzhihaobean.DataBean dataList = new StatusException(guarantineDeclareListDetilBean).getObjectData(JianyiChuliTongzhihaobean.DataBean.class);
                    return dataList;
                })
                .subscribe(DataListBean -> getView().jiludan(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void getBianhao() {

        addSubscribe(NetClient.getShenbaoJiluBianhaoBean(getUserId(),"")
                .map(guarantineDeclareListDetilBean -> {
                    ShenbaoJiluBianhaoBean.DataBean dataList = new StatusException(guarantineDeclareListDetilBean).getObjectData(ShenbaoJiluBianhaoBean.DataBean.class);
                    return dataList;
                })
                .subscribe(DataListBean -> getView().bianhao(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void upload(AnimRuZhongYongRecordBean.DataListBean bean) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(bean), "AH_AiryEmbryoQuarantine", getUserId(),null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    getView().getPrintid(baseMsgBean.getData().getResult());
                    return baseMsgBean.getData().getResult();
                }).subscribe(errorCode -> {
                    Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
                }, new ExceptionImp(getView(),getLock()), () -> {
                    Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                    Log.e("HarmlessApplyActivityPr", "complete");
                    getView().uploadsuccess();
                }));
    }
}
