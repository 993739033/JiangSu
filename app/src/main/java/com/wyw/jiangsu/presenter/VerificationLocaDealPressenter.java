package com.wyw.jiangsu.presenter;

import android.provider.ContactsContract;

import com.wyw.jiangsu.bean.KanYanbean;
import com.wyw.jiangsu.interfac.IVerificationLocaPresenter;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by song on 2017/6/8.
 */

public class VerificationLocaDealPressenter extends BasePresenter<IVerificationLocaPresenter> {

    public VerificationLocaDealPressenter(IVerificationLocaPresenter view) {
        super(view);
    }

    public void getDateList(String tableName, Integer fstId) {
        addSubscribe(NetClient.getKanYan(tableName, fstId)
                .map(listBean -> {
                    if (listBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(listBean.getErrorMsg());
                    }
                    KanYanbean.DataBean list = new StatusException(listBean).getObjectData(KanYanbean.DataBean.class);
                    return list;
                })
                .subscribe(DataListEntity ->
                                getView().setData(DataListEntity),
                        new ExceptionImp(getView()),
                        new CompleteImp(getView())));


    }


}
