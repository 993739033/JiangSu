package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.interfac.IAnimBActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by wyw on 2017/2/10.
 */
public class AnimBActivityPresenter extends BasePresenter<IAnimBActivity> {

    public AnimBActivityPresenter(IAnimBActivity view) {
        super(view);
    }

    public void getUserDetil(AnimAProcessListBean.DataListBean bean) {
        addSubscribe(NetClient.getAnimAProcessDetil1(getUserId(), "myuse" + bean.getMyuse() + "B", bean.getFStId())
                .subscribe(guarantineDeclareListDetilBean -> {
                    //再次请求
                    getView().getUserDetil1(guarantineDeclareListDetilBean.getData().get(0));
                }, new ExceptionImp(getView())));
    }

    public void upload(AnimBlistBean.DataListBean data) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(data), "Qua_AnimalQuarantineB", getUserId(),null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getData().toString());
                    } else {
                        Toast.makeText(MyApplication.getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                    }
                    getView().getPrintId(baseMsgBean.getData().getResult(),baseMsgBean.getData().getGuid());
                    return baseMsgBean.getData().getResult();
                })
                .subscribe(baseMsg -> getView().uploadSucceed(),
                        throwable -> {
                            getLock().unLock();
                            Toast.makeText(MyApplication.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            getView().hideProgress();
                        }, () -> getView().hideProgress()));
    }

    public void getShouyiJiandu() {

        addSubscribe(NetClient.getShouyiJiandusuoBean(getUserId())
                .map(guarantineDeclareListDetilBean -> {
                    ShouyiJiandusuoBean.DataBean dataList = new StatusException(guarantineDeclareListDetilBean).getObjectData(ShouyiJiandusuoBean.DataBean.class);
                    return dataList;
                })
                .subscribe(DataListBean -> getView().shouyijiandu(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }

}
