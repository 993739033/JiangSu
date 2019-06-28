package com.wyw.jiangsu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.WHHChuKuUploadBean;
import com.wyw.jiangsu.bean.WHHChukuBean;
import com.wyw.jiangsu.bean.WHHChukuFirstBean;
import com.wyw.jiangsu.interfac.IWHHChuKuActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public class WHHChuKuActivityPresenter extends BasePresenter<IWHHChuKuActivity> {

    public WHHChuKuActivityPresenter(IWHHChuKuActivity view) {
        super(view);

    }

    public void getChukuXinxi(String id,String fSTid) {
        addSubscribe(NetClient.getWHHChukuu(Integer.parseInt(id),Integer.parseInt(fSTid))
                .map(whhChukuBean -> {
                    WHHChukuBean.DataBean dataList = new StatusException(whhChukuBean).getObjectData(WHHChukuBean.DataBean.class);
                    return dataList;
                })
                .subscribe(dataListBean -> getView().chukuXinxi(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void getChukuXinxi1(String id,String fSTid) {
        addSubscribe(NetClient.getWHHChukuu1(Integer.parseInt(id),Integer.parseInt(fSTid))
                .map(whhChukuBean -> {
                    List<WHHChukuFirstBean.DataListBean> dataList = new StatusException(whhChukuBean).getObjectRefreshDataList(WHHChukuFirstBean.DataListBean.class);
                    return dataList;
                })
                .subscribe(dataListBean -> getView().chukuXinxi1(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void upload(WHHChuKuUploadBean data, String uuid) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(data), "APP_ZCDCKGL", getUserId(), uuid)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getErrorCode();
                }).subscribe(errorCode -> {
                    Log.e("HarmlessApplyActivityPr", "item success" + errorCode);
                }, new ExceptionImp(getView(),getLock()), () -> {

                    Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                    getView().upLoadSucceed();
                }));

    }
}
