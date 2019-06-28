package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.interfac.IHarmlessSupervisionVeterinatiansListActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2016/12/29.
 */
public class HarmlessSupervisionVeterinatiansListActivityPresenter
        extends BasePresenter<IHarmlessSupervisionVeterinatiansListActivity> {
    private String fstId;

    public HarmlessSupervisionVeterinatiansListActivityPresenter(IHarmlessSupervisionVeterinatiansListActivity view) {
        super(view);
    }

    public void refresh() {
        fstId = "-1";
        addSubscribe(NetClient.getHarmlessList(getUserId(), "SB2", fstId)
                .map(harmlessListBean -> {
                    List<HarmlessListBean.DataEntity> dataList = new StatusException(harmlessListBean).getObjectRefreshDataList(HarmlessListBean.DataEntity.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFGlid();
                    }
                    return dataList;
                })
                .subscribe(dataEntities -> getView().refresh(dataEntities)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadmore() {
        addSubscribe(NetClient.getHarmlessList(getUserId(), "SB2", fstId)
                .map(harmlessListBean -> {
                    List<HarmlessListBean.DataEntity> dataList = new StatusException(harmlessListBean).getObjectDataList(HarmlessListBean.DataEntity.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFGlid();
                    }
                    return dataList;
                })
                .subscribe(dataEntities -> getView().loadMore(dataEntities)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
