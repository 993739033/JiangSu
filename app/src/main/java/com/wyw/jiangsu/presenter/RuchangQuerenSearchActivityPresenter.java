package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.interfac.IRuchangQuerenSearchActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

import static jp.co.canon.android.print.ij.a.a.n;

/**
 * Created by Administrator on 2017/4/13.
 */
public class RuchangQuerenSearchActivityPresenter extends BasePresenter<IRuchangQuerenSearchActivity> {
    private String text;
    private String fstId = "-1";

    public RuchangQuerenSearchActivityPresenter(IRuchangQuerenSearchActivity view) {
        super(view);
    }

    public void searchUserDetil(String text) {
        this.text = text;
        fstId = "-1";
        addSubscribe(NetClient.getRuChangChaYanQueryBean(getUser().getUSERID() + "", "V_AH_SlaughterBk", fstId, "FhName,FhLegal,FhPhone,FhAdress,FhKind", text, "", "")
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean -> {
                    List<RuChangChaYanQueryBean.DataListBean> data = new StatusException(userDetilListBean).getObjectRefreshDataList(RuChangChaYanQueryBean.DataListBean.class);
                    if (data != null) {
                        fstId = data.get(data.size() - 1).getFStId();
                    }
                    return data;
                })
                .subscribe(userDetilBean -> {
                    getView().searchComplete(userDetilBean);
                }, new ExceptionImp(getView()), new CompleteImp(getView())));


    }

    public void refresh() {
        searchUserDetil(text);
    }


    public void loadmore() {

        addSubscribe(NetClient.getRuChangChaYanQueryBean(getUser().getUSERID() + "", "V_AH_SlaughterBk", fstId, "FhName,FhLegal,FhPhone,FhAdress,FhKind", text, "", "")
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(userDetilListBean -> {
                    if (userDetilListBean.getErrorCode() != 0)
                        throw new IllegalArgumentException(userDetilListBean.getErrorMsg());
                    List<RuChangChaYanQueryBean.DataListBean> data = userDetilListBean.getData();
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
