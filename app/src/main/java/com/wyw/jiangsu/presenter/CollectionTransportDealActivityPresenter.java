package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;
import com.wyw.jiangsu.interfac.ICollectionTransportDealActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by song on 2017/6/9.
 */

public class CollectionTransportDealActivityPresenter extends BasePresenter<ICollectionTransportDealActivity> {
    public CollectionTransportDealActivityPresenter(ICollectionTransportDealActivity view) {
        super(view);
    }

    public void getDateList(int fstId) {
        addSubscribe(NetClient.getWuHaiHua("V_APP_SJQR", fstId)
                .map(listBean -> {
                    CollectionTransportDealActivityBean.DataBean list = new StatusException(listBean).getObjectData(CollectionTransportDealActivityBean.DataBean.class);
                    return list;
                })
                .subscribe(DataListEntity ->
                                getView().setData(DataListEntity),
                        new ExceptionImp(getView()),
                        new CompleteImp(getView())));


    }
}
