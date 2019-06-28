package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.StoreChukuBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.IStorehouseOutQueryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by mnkj004 on 2017/8/28.
 */

public class StorehouseOutQuerypresenter extends BasePresenter<IStorehouseOutQueryActivity>{
    public StorehouseOutQuerypresenter(IStorehouseOutQueryActivity view) {
        super(view);
    }
    private String fstId = "-1";
    public void getDeclareNewsAccept(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getStoreChukuInfo(getUserId(), "V_APP_ZCDCKGL", "-1", "FckDate,FckdNumber,FZcdName,FZcddz,FWhhName", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<StoreChukuBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(StoreChukuBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getStoreChukuload(getUser().getUSERID() + "", "V_APP_ZCDCKGL", fstid, "FckDate,FckdNumber,FZcdName,FZcddz,FWhhName", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<StoreChukuBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(StoreChukuBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

}
