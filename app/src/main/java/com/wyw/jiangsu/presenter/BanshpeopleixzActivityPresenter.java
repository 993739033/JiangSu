package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.BanshpeopleleixzActivityBean;
import com.wyw.jiangsu.interfac.IBanshpeopleixzActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Windows on 2017/6/20.
 */

public class BanshpeopleixzActivityPresenter extends BasePresenter<IBanshpeopleixzActivity> {
    public BanshpeopleixzActivityPresenter(IBanshpeopleixzActivity view) {
        super(view);
    }

    public void getFarmDeclare(String fSunitUstrId) {
        addSubscribe(NetClient.getBanshipeople(fSunitUstrId)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<BanshpeopleleixzActivityBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(BanshpeopleleixzActivityBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity ->
                        getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
