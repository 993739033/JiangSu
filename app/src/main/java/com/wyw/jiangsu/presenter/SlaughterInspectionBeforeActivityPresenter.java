package com.wyw.jiangsu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.SlaghterNumberBean;
import com.wyw.jiangsu.bean.SlaughterInspectionBeforeBean;
import com.wyw.jiangsu.interfac.ISlaughterInspectionBeforeActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by zhyzh on 2017/4/12.
 */

public class SlaughterInspectionBeforeActivityPresenter extends BasePresenter<ISlaughterInspectionBeforeActivity> {
    public SlaughterInspectionBeforeActivityPresenter(ISlaughterInspectionBeforeActivity view) {
        super(view);
    }

    public void getSlaughterNumber() {
        addSubscribe(NetClient.getSlaughterNumberData(getUserId())
                .map(slaghterNumberBean -> {
                    List<SlaghterNumberBean.DataEntity> dataList = new StatusException(slaghterNumberBean).getObjectRefreshDataList(SlaghterNumberBean.DataEntity.class);
                    return dataList;
                }).subscribe(dataListEntities -> getView().setSlaughterNumber(dataListEntities), new ExceptionImp(getView()), new CompleteImp(getView()))
        );
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


    public void upload(SlaughterInspectionBeforeBean bean) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(bean), "Qua_Diebefore", getUserId(),null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
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

}
