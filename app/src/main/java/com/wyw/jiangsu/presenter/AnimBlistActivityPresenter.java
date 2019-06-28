package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.interfac.IAnimBlistActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class AnimBlistActivityPresenter extends BasePresenter<IAnimBlistActivity> {
    public AnimBlistActivityPresenter(IAnimBlistActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getAnimAProcessListBean(getUserId(), "V_AH_XZJLDS", fstId)
                .map(animAlistBean -> {
                    List<AnimAProcessListBean.DataListBean> dataList = new StatusException(animAlistBean).getObjectRefreshDataList(AnimAProcessListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFScTime();
                    }
                    return dataList;
                })
                .subscribe(DataListBean -> getView().refresh(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }

    public void loadmore() {
        addSubscribe(NetClient.getAnimAProcessListBean(getUserId(), "V_AH_XZJLDS", fstId)
                .map(animAlistBean -> {
                    List<AnimAProcessListBean.DataListBean> dataList = new StatusException(animAlistBean).getObjectDataList(AnimAProcessListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().loadMore(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}

