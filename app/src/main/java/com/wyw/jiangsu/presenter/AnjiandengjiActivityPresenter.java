package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnjiandengjiActivityBean;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.IAnjiandengjiActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Windows on 2017/6/19.
 */

public class AnjiandengjiActivityPresenter extends BasePresenter<IAnjiandengjiActivity> {
    String FSunitUstrId = "01";

    public AnjiandengjiActivityPresenter(IAnjiandengjiActivity view) {
        super(view);
    }

    //修改
    public void getFarmDeclare() {

//        addSubscribe(
//                getRequest().getCaseRegisteInfo(FSunitUstrId)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnSubscribe(() -> getView().showProgress())
//                        .subscribeOn(AndroidSchedulers.mainThread())
        addSubscribe(NetClient.getCaseRegisteInfo(getUser().getFSUNITUSTRID())
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnjiandengjiActivityBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(AnjiandengjiActivityBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
//    public void loadData() {
//
//        addSubscribe(NetClient.getWuHaiHuaCXbean(getUser().getUSERID() + "", "V_HT_JGSYDSB", fstid, "Fsbrq,Fxzxm,Fxxdz,Fclfs,Fxcll,Fyzclx,QDWErBiaoHao,Fykth,Fsfzh", value, sdate, jdate)
//                .map(animAQueryListBean -> {
//                    if (animAQueryListBean.getErrorCode() != 0) {
//                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
//                    }
//                    List<AnjiandengjiActivityBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(AnjiandengjiActivityBean.class);
//                    return dataList;
//                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
//    }
}
