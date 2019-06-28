package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.CollectionPointDealActivityBean;
import com.wyw.jiangsu.interfac.ICollectionPointDealActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

import static android.R.id.list;

/**
 * Created by song on 2017/6/9.
 */

public class CollectionPointDealActivityPresenter extends BasePresenter<ICollectionPointDealActivity> {
    public CollectionPointDealActivityPresenter(ICollectionPointDealActivity view) {
        super(view);
    }

    public void getDateList(String tableName, int fstId) {
        addSubscribe(NetClient.getJianChaDian(tableName, fstId)
                .map(listBean -> {
                    if (listBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(listBean.getErrorMsg());
                    }
                    CollectionPointDealActivityBean.DataBean list = new StatusException(listBean).getObjectData(CollectionPointDealActivityBean.DataBean.class);
                    return list;
                })
                .subscribe(DataListEntity ->
                                getView().setData(DataListEntity),
                        new ExceptionImp(getView()),
                        new CompleteImp(getView())));


    }

}
