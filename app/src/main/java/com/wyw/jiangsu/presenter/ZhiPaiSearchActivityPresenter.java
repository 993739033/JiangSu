package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.bean.WHHZhiPaiChooseBean;
import com.wyw.jiangsu.interfac.IZhiPaiSearchActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public class ZhiPaiSearchActivityPresenter extends BasePresenter<IZhiPaiSearchActivity> {
    private String text;
    private String fstId = "-1";

    public ZhiPaiSearchActivityPresenter(IZhiPaiSearchActivity view) {
        super(view);
    }

    public void refresh() {
        searchUserDetil(text);
    }

    public void searchUserDetil(String text) {
        this.text = text;
        fstId = "-1";
        addSubscribe(NetClient.getZhiPaiChoose(getUserId(), text, fstId)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean -> {
                    List<WHHZhiPaiChooseBean.DataListBean> data = new StatusException(userDetilListBean).getObjectRefreshDataList(WHHZhiPaiChooseBean.DataListBean.class);
                    if (data != null) {
                        fstId = data.get(data.size() - 1).getFStId();
                    }
                    return data;
                })
                .subscribe(userDetilBean -> {
                    getView().searchComplete(userDetilBean);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadmore() {

        addSubscribe(NetClient.getZhiPaiChoose(getUserId(), text, fstId)
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean -> {
                    if (userDetilListBean.getErrorCode() != 0)
                        throw new IllegalArgumentException(userDetilListBean.getErrorMsg());
                    List<WHHZhiPaiChooseBean.DataListBean> data = new StatusException(userDetilListBean).getObjectDataList(WHHZhiPaiChooseBean.DataListBean.class);
                    if (data != null) {
                        fstId = data.get(data.size() - 1).getFStId();
                    }
                    return data;
                })
                .subscribe(userDetilBean -> {
                    getView().loadMore(userDetilBean);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
