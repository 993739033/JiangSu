package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.IHarmlessFlow2Activity;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RxBus;

/**
 * Created by Administrator on 2017/6/20.
 */

public class HarmlessFlow2ActivityPresenter extends BasePresenter<IHarmlessFlow2Activity>{
    public HarmlessFlow2ActivityPresenter(IHarmlessFlow2Activity view) {
        super(view);
    }
    public void noDealWithInfo(UploadNoDealWithBean bean) {
        addSubscribe(NetClient.withoutDealMsg(Integer.parseInt(getUserId()),new Gson().toJson(bean))
//                .map(homeBean -> {
//                    if (homeBean.getErrorCode() != 0) {
//                        throw new NullPointerException(homeBean.getErrorMsg());
//                    }
//                    return homeBean.getData();
//                })
                .subscribe(homeBean -> {
                    if (homeBean.getErrorCode() == 0) {
                        getView().refreshNoDeal(homeBean.getData());

                    }
                },throwable -> {
//                    getView().showToast(throwable.getMessage());
                    Toast.makeText(MyApplication.getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                }));
    }
    @Override
    public void attachActivity(IHarmlessFlow2Activity view) {
        super.attachActivity(view);
        addSubscribe(RxBus.getInstance().toObserverable(RefreshBus.class)
                .subscribe(refreshBus ->
                        getView().onActivityResult(refreshBus)));
    }
}
