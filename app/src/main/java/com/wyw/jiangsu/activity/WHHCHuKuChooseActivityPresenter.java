package com.wyw.jiangsu.activity;

import com.wyw.jiangsu.bean.WHHChuKuChooseBean;
import com.wyw.jiangsu.interfac.IWHHCHuKuChooseActivity;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public class WHHCHuKuChooseActivityPresenter extends BasePresenter<IWHHCHuKuChooseActivity> {
    public WHHCHuKuChooseActivityPresenter(IWHHCHuKuChooseActivity view) {
        super(view);
    }

    public void refresh(String id, String fstid) {
        addSubscribe(NetClient.getWHHChukuChoose(Integer.parseInt(id), fstid)
                .map(whhChoose -> {
                    List<WHHChuKuChooseBean.DataListBean> dataList = new StatusException(whhChoose).getObjectRefreshDataList(WHHChuKuChooseBean.DataListBean.class);
                    if (whhChoose.getErrorCode() != 0) {
                        throw new IllegalArgumentException(whhChoose.getErrorMsg());
                    }
                    return dataList;
                }).subscribe(dataListEntities -> {
                    getView().setData(dataListEntities);
                }, new ExceptionImp(getView()), new CompleteImp(getView()))
        );
    }
}
