package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.bean.AnimAlistBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.interfac.IAnimAActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by wyw on 2017/2/10.
 */
public class AnimAActivityPresenter extends BasePresenter<IAnimAActivity> {
    public AnimAActivityPresenter(IAnimAActivity view) {
        super(view);
    }

    public void getUserDetil(AnimAProcessListBean.DataListBean bean) {
        addSubscribe(NetClient.getAnimAProcessDetil1(getUserId(), "myuse" + bean.getMyuse() + "A", bean.getFStId())
                .subscribe(guarantineDeclareListDetilBean -> {
                    //再次请求
                    getView().getUserDetil1(guarantineDeclareListDetilBean.getData().get(0));
                }, throwable -> {
                }));
    }

    public void upload(AnimAlistBean.DataListBean data) {
//        String  json = "";
//        BaseMsg msg = new Gson().fromJson(json,BaseMsg.class);
        if (getLock().isLock()) return;
        getLock().lock();
        addSubscribe(NetClient.uplaod(new Gson().toJson(data), "Qua_AnimalQuarantineA", getUserId(), null)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(msgBean -> {
                    if (msgBean.getErrorCode() != 0) {
                        Toast.makeText(MyApplication.getContext(), "保存失败", Toast.LENGTH_SHORT).show();
                        throw new IllegalArgumentException(msgBean.getErrorMsg());
                    } else {
                        Toast.makeText(MyApplication.getContext(), "保存完成", Toast.LENGTH_SHORT).show();
                    }
                    getView().getPrintId(msgBean.getData().getResult(), msgBean.getData().getGuid());
                    return msgBean.getData().getGuid();
                }).subscribe(guid -> getView().uploadSuccess(guid)
                        , new ExceptionImp(getView(), getLock()), new CompleteImp(getView())));
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


//public void upload(AnimAlistBean.DataListBean bean) {
//    addSubscribe(getRequest().processAnimAndProduct(Constance.TYPE_PRINT_ANIM_A, new Gson().toJson(bean)
//            , "null", String.valueOf(getUser().getUSERID()))
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe(() -> getView().showProgress())
//            .map(baseMsg -> {
//                if (baseMsg.getErrorCode() != 0)
//                    throw new IllegalArgumentException(baseMsg.getErrorMsg());
//                return baseMsg;
//            })
//            .subscribe(baseMsg -> getView().getUserDetil1(),
//                    throwable -> {
//                        getView().showToast(throwable.getMessage());
//                        getView().hideProgress();
//                    }, () -> getView().hideProgress()));
//}
//
//    public String getMachineNumber() {
//        return getSpUtils().setData(Constance.DATA_MACHINE_NUMBER,"",String.class);
//    }
//
//    public String getSerialNumber() {
//        return  getSpUtils().setData(Constance.DATA_SERIAL_NUMBER,"",String.class);
//    }
}