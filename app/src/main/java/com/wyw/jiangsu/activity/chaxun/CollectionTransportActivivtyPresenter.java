package com.wyw.jiangsu.activity.chaxun;

import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.ICollectionTransportActivivty;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public class CollectionTransportActivivtyPresenter extends BasePresenter<ICollectionTransportActivivty> {
    public CollectionTransportActivivtyPresenter(ICollectionTransportActivivty view) {
        super(view);
    }

    private String fstId = "-1";

    public void getCollectionTransport(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getWuHaiHuaCXbean(getUserId(), "V_APP_SJQR", "-1", "FScTime,Fxzxm,Fxxdz,QDSL", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(WuHaiHuaCXbean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getWuHaiHuaCXbean(getUser().getUSERID() + "", "V_APP_SJQR", fstid, "FScTime,Fxzxm,Fxxdz,QDSL", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(WuHaiHuaCXbean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
