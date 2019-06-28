package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.interfac.IAnimProcesslistActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2016/12/20.
 */
public class AnimProcesslistActivityPresenter extends BasePresenter<IAnimProcesslistActivity> {
    public AnimProcesslistActivityPresenter(IAnimProcesslistActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getAnimProcessListDetil(getUserId(), "V_Qua_QuarantineDeclarationCDCL", fstId)
                .map(animProcessListBean -> {
                    List<AnimProcessListBean.DataListBean> dataList = new StatusException(animProcessListBean).getObjectRefreshDataList(AnimProcessListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().refresh(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }

    public void loadmore() {
        addSubscribe(NetClient.getAnimProcessListDetil(getUserId(), "V_Qua_QuarantineDeclarationCDCL", fstId)
                .map(animProcessListBean -> {
                    List<AnimProcessListBean.DataListBean> dataList = new StatusException(animProcessListBean).getObjectDataList(AnimProcessListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().loadMore(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
