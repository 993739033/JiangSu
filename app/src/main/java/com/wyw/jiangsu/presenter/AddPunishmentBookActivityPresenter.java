package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AddDangchangjdshuActivityBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.IAddPunishmentBookActivity;
import com.wyw.jiangsu.runnable.RxBus;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Windows on 2017/6/22.
 */

public class AddPunishmentBookActivityPresenter extends BasePresenter<IAddPunishmentBookActivity> {
    public AddPunishmentBookActivityPresenter(IAddPunishmentBookActivity view) {
        super(view);
    }

    public void upload(AddDangchangjdshuActivityBean data) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(
                getRequest().uploadPunishmentBook(new Gson().toJson(data))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(() -> getView().showProgress())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .map(addAnjiandengjiActivityBean -> {
                            if (addAnjiandengjiActivityBean.getErrorCode() != 0) {
                                throw new IllegalArgumentException(addAnjiandengjiActivityBean.getErrorMsg());
                            }
//                            BaseMsgBean.Data dataBean = new StatusException(addAnjiandengjiActivityBean).getObjectData(BaseMsgBean.Data.class);
                            return addAnjiandengjiActivityBean.getErrorCode();
                        })
                        .subscribe(baseMsg ->
                                        getView().uploadSucceed(),
                                throwable -> {
                                    getLock().unLock();
                                    RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                                    Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                                    getView().hideProgress();
                                }, () -> getView().hideProgress()));


    }
}
