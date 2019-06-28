package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsAListBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.IProductAActivity;
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
public class ProductAActivityPresenter extends BasePresenter<IProductAActivity> {
    public ProductAActivityPresenter(IProductAActivity view) {
        super(view);
    }

    public void upLoad(Qua_AnimalProductsAListBean.DataListBean data) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(data), "Qua_AnimalProductsA", getUserId(),null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(new Func1<BaseMsgBean, String>() {
                    @Override
                    public String call(BaseMsgBean baseMsgBean) {
                        if (baseMsgBean.getErrorCode() != 0) {
                            throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                        }
                        Toast.makeText(MyApplication.getContext(), "上传完成", Toast.LENGTH_SHORT).show();
                        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
                        getView().getPrintId(baseMsgBean.getData().getResult(),baseMsgBean.getData().getGuid());
                        return baseMsgBean.getData().getGuid();
                    }
                })
                //toast放在view层
                .subscribe(guid -> getView().uploadComplete(guid),
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
                .subscribe(dataListBean -> getView().shouyijiandu(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }
}
