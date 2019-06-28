package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;
import com.wyw.jiangsu.bean.WuhaihuaDateBean;
import com.wyw.jiangsu.interfac.IWuhaihuaDate;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by song on 2017/6/16.
 */

public class WuhaihuaDatePresenter extends BasePresenter<IWuhaihuaDate> {
    public WuhaihuaDatePresenter(IWuhaihuaDate view) {
        super(view);
    }

    public void getDateList(int fstId) {
        addSubscribe(NetClient.getWuHaiHua2("APP_ZCSYQR", fstId)
                .map(listBean -> {
                    if (listBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(listBean.getErrorMsg());
                    }
                    WuhaihuaDateBean.DataBean list = new StatusException(listBean).getObjectData(WuhaihuaDateBean.DataBean.class);
                    return list;
                })
                .subscribe(dataListEntity ->
                                getView().setData(dataListEntity),
                        new ExceptionImp(getView()),
                        new CompleteImp(getView())));


    }
}
