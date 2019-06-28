package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.interfac.IAnimalOriginLabelActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class AnimalOriginLabelActivityPresenter extends BasePresenter<IAnimalOriginLabelActivity> {
    public AnimalOriginLabelActivityPresenter(IAnimalOriginLabelActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void getAnimlOriginLabelListData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getAnimRuZhongYongRecordDetilQuery(getUserId(), "AH_AiryEmbryoQuarantine", "-1", "Quarantinetime,FSm1,RecordNo,Shippername,Animalsort,Animalnum,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimRuZhongYongRecordBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(AnimRuZhongYongRecordBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getAnimRuZhongYongRecordDetilQuery(getUser().getUSERID() + "", "AH_AiryEmbryoQuarantine", fstid, "Quarantinetime,FSm1,RecordNo,Shippername,Animalsort,Animalnum,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimRuZhongYongRecordBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(AnimRuZhongYongRecordBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
