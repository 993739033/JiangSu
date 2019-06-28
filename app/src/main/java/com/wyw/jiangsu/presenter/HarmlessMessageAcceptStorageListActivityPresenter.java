package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.interfac.IHarmlessMessageAcceptStorageListActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2016/12/23.
 */
public class HarmlessMessageAcceptStorageListActivityPresenter extends BasePresenter<IHarmlessMessageAcceptStorageListActivity>{
    private String fstId = "-1";

    public HarmlessMessageAcceptStorageListActivityPresenter(IHarmlessMessageAcceptStorageListActivity view) {
        super(view);
    }

    public void refresh() {
        fstId = "-1";
        NetClient.getHarmlessList(getUserId(), "SB4", fstId)
                .map(harmlessListBean -> {
                    List<HarmlessListBean.DataEntity> dataList = new StatusException(harmlessListBean).getObjectRefreshDataList(HarmlessListBean.DataEntity.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataEntities -> getView().refresh(dataEntities)
                        , new ExceptionImp(getView()), new CompleteImp(getView()));
    }

    public void loadmore() {
        NetClient.getHarmlessList(getUserId(), "SB4", fstId)
                .map(harmlessListBean -> {
                    List<HarmlessListBean.DataEntity> dataList = new StatusException(harmlessListBean).getObjectDataList(HarmlessListBean.DataEntity.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataEntities -> getView().loadMore(dataEntities)
                        , new ExceptionImp(getView()), new CompleteImp(getView()));
    }
}

