package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.ChaxunJinduActuvityBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.IChaxunJinduActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Windows on 2017/6/12.
 */

public class ChaxunJinduActuvityPresenter extends BasePresenter<IChaxunJinduActivity> {
    public ChaxunJinduActuvityPresenter(IChaxunJinduActivity view) {
        super(view);
    }

    public void getFarmDeclare(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getChaxunjindu(-1 + "", Integer.valueOf(getUserId()),
                "Fsbrq,Fxzxm,Fxxdz,Fclfs,Fxcll,Fyzclx,QDWErBiaoHao,fykth,Fsfzh,swl,jl",
                value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<ChaxunJinduActuvityBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(ChaxunJinduActuvityBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadData(String value, String sdate, String jdate, String time) {

        addSubscribe(NetClient.getChaxunjindu(time, Integer.parseInt(getUserId()),
                "Fsbrq,Fxzxm,Fxxdz,Fclfs,Fxcll,Fyzclx,QDWErBiaoHao,fykth,Fsfzh,swl,jl", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<ChaxunJinduActuvityBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(ChaxunJinduActuvityBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
