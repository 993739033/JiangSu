package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.interfac.IHarmlessHomeVeterinatiansListActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2016/12/29.IHarmlessConcentrateProcessListActivity
 */
public class HarmlessHomeVeterinatiansListActivityPresenter
        extends BasePresenter<IHarmlessHomeVeterinatiansListActivity> {
    private String fstId;

    public HarmlessHomeVeterinatiansListActivityPresenter(IHarmlessHomeVeterinatiansListActivity view) {
        super(view);
    }

    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getHarmlessList(getUserId(), "SB5", fstId)
                .map(harmlessListBean -> {
                    List<HarmlessListBean.DataEntity> dataList = new StatusException(harmlessListBean).getObjectRefreshDataList(HarmlessListBean.DataEntity.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataEntities ->
                                getView().refresh(dataEntities)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadmore() {
        addSubscribe(NetClient.getHarmlessList(getUserId(), "SB5", fstId)
                .map(harmlessListBean -> {
                    List<HarmlessListBean.DataEntity> dataList = new StatusException(harmlessListBean).getObjectDataList(HarmlessListBean.DataEntity.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataEntities -> getView().loadMore(dataEntities)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
