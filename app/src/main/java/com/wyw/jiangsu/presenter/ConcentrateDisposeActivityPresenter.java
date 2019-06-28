package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.IConcentrateDisposeActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public class ConcentrateDisposeActivityPresenter extends BasePresenter<IConcentrateDisposeActivity> {
    public ConcentrateDisposeActivityPresenter(IConcentrateDisposeActivity view) {
        super(view);
    }

    private String fstId = "-1";


    public void getConcentrateDispose(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getWuHaiHuaCXbean(getUserId(), "APP_WHHCLZX", "-1", "CLRQ,WHHCLZX,CLR,Cbs,Sws,Zls", value, sdate, jdate)
                .map(wuHaiHuaCXbean -> {
                    if (wuHaiHuaCXbean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(wuHaiHuaCXbean.getErrorMsg());
                    }
                    List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(wuHaiHuaCXbean).getObjectRefreshDataList(WuHaiHuaCXbean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListBean -> getView().setData(DataListBean), new ExceptionImp(getView()), new CompleteImp(getView())));

        //.subscribe(DataListBean -> getView().setData(DataListBean), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getWuHaiHuaCXbean(getUser().getUSERID() + "", "APP_WHHCLZX", fstid, "CLRQ,WHHCLZX,CLR,Cbs,Sws,Zls", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(WuHaiHuaCXbean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
