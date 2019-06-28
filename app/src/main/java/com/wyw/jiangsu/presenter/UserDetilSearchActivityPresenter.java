package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.interfac.IUserDetilSearchActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2016/12/28.
 */
public class UserDetilSearchActivityPresenter extends BasePresenter<IUserDetilSearchActivity> {
    private String fstId = "-1";
    private String text = "";

    public UserDetilSearchActivityPresenter(IUserDetilSearchActivity view) {
        super(view);
    }

    public void searchUserDetil(String text) {
        this.text = text;
        fstId = "-1";
        NetClient.searchUserDetil(fstId, text, getUserId())
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean -> {
                    if (userDetilListBean.getErrorCode() != 0)
                        throw new IllegalArgumentException(userDetilListBean.getErrorMsg());
                    List<UserDetilBean.Data> data = new StatusException(userDetilListBean).getObjectRefreshDataList(UserDetilBean.Data.class);
                    if (data != null) {
                        fstId = data.get(data.size() - 1).getFStId();
                    }
                    return data;
                })
                .subscribe(userDetilBean -> {
                    getView().searchComplete(userDetilBean);
                }, new ExceptionImp(getView()), new CompleteImp(getView()));
    }

    public void refresh() {
        searchUserDetil(text);
    }

    public void loadmore() {
        NetClient.searchUserDetil(fstId, text, getUserId())
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean ->
                {
                    if (userDetilListBean.getErrorCode() != 0)
                        throw new IllegalArgumentException(userDetilListBean.getErrorMsg());
                    List<UserDetilBean.Data> data = userDetilListBean.getData();
                    if (data != null) {
                        fstId = data.get(data.size() - 1).getFStId();
                    }
                    return data;
                })
                .subscribe(userDetilBean -> {
                    getView().loadMore(userDetilBean);
                }, new ExceptionImp(getView()), new CompleteImp(getView()));
    }
}
