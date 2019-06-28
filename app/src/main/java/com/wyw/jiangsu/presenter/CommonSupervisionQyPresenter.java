package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.CommonSupervisionQyBean;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.ICommonSupervisionQy;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2018/1/25.
 */

public class CommonSupervisionQyPresenter extends BasePresenter<ICommonSupervisionQy> {
    private String text;
    private String fstId = "-1";
    private String tableName;

    public CommonSupervisionQyPresenter(ICommonSupervisionQy view) {
        super(view);
    }

    public void refresh() {
        searchUserDetil(text, tableName);
    }

    public void loadmore() {
        addSubscribe(NetClient.daySupervise(getUserId(), getUser().getFSUNITUSTRID(), tableName, text, fstId)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean -> {
                    if (userDetilListBean.getErrorCode() != 0)
                        throw new IllegalArgumentException(userDetilListBean.getErrorMsg());
                    List<CommonSupervisionQyBean.DataListBean> data = userDetilListBean.getData();
                    if (data != null) {
                        fstId = data.get(data.size() - 1).getFStId();
                    }
                    return data;
                })
                .subscribe(userDetilBean -> {
                    getView().loadMore(userDetilBean);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void searchUserDetil(String text, String tableName) {
        this.tableName = tableName;
        this.text = text;
        fstId = "-1";
        addSubscribe(NetClient.daySupervise(getUserId(), getUser().getFSUNITUSTRID(), tableName, text, fstId)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean -> {
                    List<CommonSupervisionQyBean.DataListBean> data = new StatusException(userDetilListBean).getObjectRefreshDataList(CommonSupervisionQyBean.DataListBean.class);
                    if (data != null) {
                        fstId = data.get(data.size() - 1).getFStId();
                    }
                    return data;
                })
                .subscribe(userDetilBean -> {
                    getView().searchComplete(userDetilBean);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
