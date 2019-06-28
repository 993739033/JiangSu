package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsBListBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.IProductBActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.runnable.StatusException;

import rx.functions.Func1;

/**
 * Created by wyw on 2017/2/15.
 */
public class ProductBActivityPresenter extends BasePresenter<IProductBActivity> {
    public ProductBActivityPresenter(IProductBActivity view) {
        super(view);
    }

    public void upLoad(Qua_AnimalProductsBListBean.DataListBean data) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(data), "Qua_AnimalProductsB", getUserId(),null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(new Func1<BaseMsgBean, String>() {
                    @Override
                    public String call(BaseMsgBean baseMsgBean) {
                        if (baseMsgBean.getErrorCode() != 0) {
                            throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                        }
                        getView().getPrint(baseMsgBean.getData().getResult());
                        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                        return baseMsgBean.getData().getGuid();
                    }
                })
                .subscribe(guid -> getView().upLoadSucceed(guid),
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
