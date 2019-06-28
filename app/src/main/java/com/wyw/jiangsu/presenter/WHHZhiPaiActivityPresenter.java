package com.wyw.jiangsu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.WHHZhiPaiBean;
import com.wyw.jiangsu.bean.WHHZhiPaiUploadBean;
import com.wyw.jiangsu.interfac.IWHHZhiPaiActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public class WHHZhiPaiActivityPresenter extends BasePresenter<IWHHZhiPaiActivity> {

    public WHHZhiPaiActivityPresenter(IWHHZhiPaiActivity view) {
        super(view);

    }

    public void getShouyiXinxi(String id) {
        addSubscribe(NetClient.getWHHZhiPai(Integer.parseInt(id))
                .map(whhZhiPaiBean -> {
                    List<WHHZhiPaiBean.DataListBean> dataListBeen = new StatusException(whhZhiPaiBean).getObjectDataList(WHHZhiPaiBean.DataListBean.class);
                    return dataListBeen;
                })
                .subscribe(dataListBean -> getView().zhipaiXinxi(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void upLoad(WHHZhiPaiUploadBean data, String uuid) {
        if (getLock().isLock())return;
      getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(data), "APP_WHHSJRW", getUserId(), uuid)
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
