package com.wyw.jiangsu.presenter;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.AddAnjiandengjiActivityBean;
import com.wyw.jiangsu.interfac.IAddCaseRegisterActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;

/**
 * Created by Windows on 2017/6/21.
 */

public class AddCaseRegisterActivityPresenter extends BasePresenter<IAddCaseRegisterActivity> {
    public AddCaseRegisterActivityPresenter(IAddCaseRegisterActivity view) {
        super(view);
    }

    public void upload(AddAnjiandengjiActivityBean data) {
//        addSubscribe(NetClient.uploadAnJian(new Gson().toJson(data)));
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uploadAnJian(new Gson().toJson(data))
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean.getData().getResult2();
                }).subscribe(result2 -> getView().uploadsuccess(result2)
                        , new ExceptionImp(getView(),getLock()), new CompleteImp(getView())));

//        addSubscribe(
//                getRequest().uploadCaseRegister(new Gson().toJson(data))
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnSubscribe(() -> getView().showProgress())
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .map(addAnjiandengjiActivityBean -> {
//                            if (addAnjiandengjiActivityBean.getErrorCode() != 0) {
//                                throw new IllegalArgumentException(addAnjiandengjiActivityBean.getErrorMsg());
//                            }
//                            BaseMsgBean.Data dataBean = new StatusException(addAnjiandengjiActivityBean).getObjectData(BaseMsgBean.Data.class);
//                            return dataBean;
//                        })
//                        .subscribe(baseMsg ->
//                                        getView().uploadSucceed(),
//                                throwable -> {
//                                    Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
//                                    getView().hideProgress();
//                                    getView().showToast("上传成功");
//                                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
//                                }, () -> getView().hideProgress()));
//
//
    }
}
