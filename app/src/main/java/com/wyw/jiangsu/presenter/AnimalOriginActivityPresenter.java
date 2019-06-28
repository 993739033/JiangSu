package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.interfac.IAnimalOriginActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class AnimalOriginActivityPresenter extends BasePresenter<IAnimalOriginActivity> {
    public AnimalOriginActivityPresenter(IAnimalOriginActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void getAnimlOriginListData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getAH_AnimalOriginDetilQuery(getUserId(), "AH_AnimalOrigin", "-1", "Quarantinetime,FSm1,RecordNo,Shippername,Animalsort,Animalnum,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AH_AnimalOrigin.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(AH_AnimalOrigin.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity ->
                        getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getAH_AnimalOriginDetilQuery(getUser().getUSERID() + "", "AH_AnimalOrigin", fstid, "Quarantinetime,FSm1,RecordNo,Shippername,Animalsort,Animalnum,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AH_AnimalOrigin.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(AH_AnimalOrigin.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
