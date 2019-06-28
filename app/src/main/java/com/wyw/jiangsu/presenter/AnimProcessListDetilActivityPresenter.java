package com.wyw.jiangsu.presenter;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.interfac.IAnimProcessListDetilActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by wyw on 2017/2/9.
 */
public class AnimProcessListDetilActivityPresenter extends BasePresenter<IAnimProcessListDetilActivity> {
    public AnimProcessListDetilActivityPresenter(IAnimProcessListDetilActivity view) {
        super(view);
    }

    public void upload(AnimProcessListBean.DataListBean bean) {
        if (getLock().isLock()) return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(bean), "Qua_QuarantineDeclarationCDCL", getUserId(), null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    getView().getPrintId(baseMsgBean.getData().getResult());
                    return new StatusException(baseMsgBean).getObjectData(BaseMsgBean.Data.class);

                }).subscribe(s -> {
                    getView().getToast("保存成功");
                    getView().uploadsuccess();
                }, new ExceptionImp(getView(), getLock()), new CompleteImp(getView())));
    }
}
