package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;
import com.wyw.jiangsu.interfac.IEmbryoOriginLabelActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class EmbryoOriginLabelActivityPresenter extends BasePresenter<IEmbryoOriginLabelActivity> {
    public EmbryoOriginLabelActivityPresenter(IEmbryoOriginLabelActivity view) {
        super(view);
    }

    private String fstId = "-1";


    public void getEmbryoOriginLabel(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getAnimZhongDanRecordDetilQuery(getUserId(), "AH_AEmbryoQuarantine", "-1", "Quarantinetime,FSm1,RecordNo,Shippername,Animalsort,Animalnum,QCPDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimZhongDanRedordBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(AnimZhongDanRedordBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getAnimZhongDanRecordDetilQuery(getUser().getUSERID() + "", "AH_AEmbryoQuarantine", fstid, "Quarantinetime,FSm1,RecordNo,Shippername,Animalsort,Animalnum,QCPDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimZhongDanRedordBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(AnimZhongDanRedordBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
