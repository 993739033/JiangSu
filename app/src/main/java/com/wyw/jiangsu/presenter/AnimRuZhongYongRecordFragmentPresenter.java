package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordListBean;
import com.wyw.jiangsu.bean.QroducingAreaProcessListBean;
import com.wyw.jiangsu.interfac.IAnimProductionRecordFragment;
import com.wyw.jiangsu.interfac.IAnimRuZhongYongRecordFragment;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by wyw on 2017/2/9.
 */
public class AnimRuZhongYongRecordFragmentPresenter extends BasePresenter<IAnimRuZhongYongRecordFragment> {

    public AnimRuZhongYongRecordFragmentPresenter(IAnimRuZhongYongRecordFragment view) {
        super(view);
    }

    private String fstId = "-1";

    public void refresh() {
        //getGuarantineDeclareListDetil
        fstId = "-1";
        addSubscribe(NetClient.getAnimRuZhongYongRecordList(getUserId(), "V_AiryEmbryoQuarantine_Sbd", fstId)
                //map里面的是实体类对象
                .map(aH_AnimalOrigin -> {
                    List<AnimRuZhongYongRecordListBean.DataListBean> dataList = new StatusException(aH_AnimalOrigin).getObjectRefreshDataList(AnimRuZhongYongRecordListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(DataListBean -> getView().refresh(DataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));

    }

    public void loadmore() {
        addSubscribe(NetClient.getAnimRuZhongYongRecordList(getUserId(), "V_AiryEmbryoQuarantine_Sbd", fstId)
                .map(aH_AnimalOrigin -> {
                    List<AnimRuZhongYongRecordListBean.DataListBean> dataList = new StatusException(aH_AnimalOrigin).getObjectDataList(AnimRuZhongYongRecordListBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                })
                .subscribe(dataListBean -> getView().loadMore(dataListBean)
                        , new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
