package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.interfac.IDoubleRandomOnePublicActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

/**
 * Created by mnkj on 2018/1/25.
 */

public class DoubleRandomOnePublicPresenter extends BasePresenter<IDoubleRandomOnePublicActivity> {

    public DoubleRandomOnePublicPresenter(IDoubleRandomOnePublicActivity view) {
        super(view);
    }

    public void getTopDetil(String table, String fstid) {
        addSubscribe(NetClient.getCompanyDetil(table, fstid)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(data -> new StatusException(data).getObjectData(CommonSupervisionBean.Data.class))
                .subscribe(data -> {
                    getView().setCompanyDetil(data);
                    getView().hideProgress();
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
