package com.wyw.jiangsu.presenter;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.IHarmlessFlowActivity;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RxBus;

/**
 * Created by wyw on 2016/12/22.
 */
public class HarmlessFlowActivityPresenter extends BasePresenter<IHarmlessFlowActivity>{
    public HarmlessFlowActivityPresenter(IHarmlessFlowActivity view) {
        super(view);
    }
    public void withoutDealMsg(UploadNoDealWithBean bean) {
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
//                    getSpUtils().saveObjectData(Constance.DATA_PERMISSION,dataLists);
                },throwable -> {
                    getView().showToast(throwable.getMessage());
                }));
    }
    @Override
    public void attachActivity(IHarmlessFlowActivity view) {
        super.attachActivity(view);
        addSubscribe(RxBus.getInstance().toObserverable(RefreshBus.class)
                .subscribe(refreshBus ->
                        getView().onActivityResult(refreshBus)));
    }
}
