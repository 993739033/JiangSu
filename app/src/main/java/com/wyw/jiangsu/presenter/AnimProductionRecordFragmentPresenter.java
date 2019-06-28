package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.QroducingAreaProcessListBean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.interfac.IAnimProductionRecordFragment;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2017/2/9.
 */
public class AnimProductionRecordFragmentPresenter extends BasePresenter<IAnimProductionRecordFragment> {
    public AnimProductionRecordFragmentPresenter(IAnimProductionRecordFragment view) {
        super(view);
    }


    private String fstId = "-1";
    public void refresh() {
        //getGuarantineDeclareListDetil
        fstId = "-1";
        addSubscribe(NetClient.getQroducingAreaProcessList(getUserId(),"V_AH_AnimalOrigin_Sbd",fstId)
                //map里面的是实体类对象
                .map(aH_AnimalOrigin -> {
                    List<QroducingAreaProcessListBean.DataListBean> dataList = new StatusException(aH_AnimalOrigin).getObjectRefreshDataList(QroducingAreaProcessListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(DataListBean -> getView().refresh(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }

    public void loadmore() {
        addSubscribe(NetClient.getQroducingAreaProcessList(getUserId(),"V_AH_AnimalOrigin_Sbd",fstId)
                .map(aH_AnimalOrigin -> {
                    List<QroducingAreaProcessListBean.DataListBean> dataList = new StatusException(aH_AnimalOrigin).getObjectDataList(QroducingAreaProcessListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().loadMore(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
