package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.TongzhiGGBean;
import com.wyw.jiangsu.interfac.IShowSdActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Windows on 2017/9/25.
 */

public class ShowSdActivityPresenter extends BasePresenter<IShowSdActivity> {
    public ShowSdActivityPresenter(IShowSdActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getTongzhi(getUserId(), getFRID(), "Sys_Inform", fstId)
                .map(tongzhiGGBean -> {
                    if (tongzhiGGBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException();
                    }
                    List<TongzhiGGBean.DataListBean> beanList = new StatusException(tongzhiGGBean).getObjectRefreshDataList(TongzhiGGBean.DataListBean.class);
                    if (beanList != null && beanList.size() > 0) {
                        fstId = beanList.get(beanList.size() - 1).getFstid();
                    }
                    return beanList;
                }).subscribe(bean -> getView().refresh(bean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadMore() {
        addSubscribe(NetClient.getTongzhi(getUserId(), getFRID(), "Sys_Inform", fstId)
                .map(tongzhiGGBean -> {
                    List<TongzhiGGBean.DataListBean> beanList = new StatusException(tongzhiGGBean).getObjectRefreshDataList(TongzhiGGBean.DataListBean.class);
                    if (beanList != null && beanList.size() > 0) {
                        fstId = beanList.get(beanList.size() - 1).getFstid();
                    }
                    return beanList;
                }).subscribe(bean -> getView().loadMore(bean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
