package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.QuarantineProcessNotifListBean;
import com.wyw.jiangsu.interfac.IQuarantineProcessNotifListActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2017/2/10.
 */
public class QuarantineProcessNotifListActivityPresenter extends BasePresenter<IQuarantineProcessNotifListActivity> {
    public QuarantineProcessNotifListActivityPresenter(IQuarantineProcessNotifListActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getQuarantineProcessNotifListBeanDetil(getUserId(), "V_AH_CDCLD", fstId)
                .map(auarantineProcessNotifListBean -> {
                    List<QuarantineProcessNotifListBean.DataListBean> dataList = new StatusException(auarantineProcessNotifListBean).getObjectRefreshDataList(QuarantineProcessNotifListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFScTime();
                    }
                    return dataList;
                })
                .subscribe(DataListBean -> getView().refresh(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }

    public void loadmore() {
        addSubscribe(NetClient.getQuarantineProcessNotifListBeanDetil(getUserId(), "V_AH_CDCLD", fstId)
                .map(auarantineProcessNotifListBean -> {
                    List<QuarantineProcessNotifListBean.DataListBean> dataList = new StatusException(auarantineProcessNotifListBean).getObjectDataList(QuarantineProcessNotifListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFScTime();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().loadMore(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
