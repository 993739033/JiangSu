package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.JianyiChuliTongzhidanListBean;
import com.wyw.jiangsu.interfac.IJianyiTongzhidanQueryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public class JianyiTongzhidanQueryActivityPresenter extends BasePresenter<IJianyiTongzhidanQueryActivity> {
    public JianyiTongzhidanQueryActivityPresenter(IJianyiTongzhidanQueryActivity view) {
        super(view);
    }

    public void getJianyiTongzhidan(String value, String sdate, String jdate) {

        addSubscribe(NetClient.getJianyiChuliQueryListBean(getUser().getUSERID() + "", "Qua_Notice", "-1", "FScTime,IsPrant,NDanWei,FSuserName,NName,FChuliNum,FChuliDanwei,NNumber", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<JianyiChuliTongzhidanListBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(JianyiChuliTongzhidanListBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));


    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getJianyiChuliQueryListBean(getUser().getUSERID() + "", "Qua_Notice", fstid, "FScTime,IsPrant,NDanWei,FSuserName,NName,FChuliNum,FChuliDanwei,NNumber", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<JianyiChuliTongzhidanListBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(JianyiChuliTongzhidanListBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
