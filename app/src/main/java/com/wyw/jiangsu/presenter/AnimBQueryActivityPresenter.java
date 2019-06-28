package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimBQueryListBean;
import com.wyw.jiangsu.interfac.IAnimBQueryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class AnimBQueryActivityPresenter extends BasePresenter<IAnimBQueryActivity> {
    public AnimBQueryActivityPresenter(IAnimBQueryActivity view) {
        super(view);
    }

    public void getAnimAListData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getAnimBListData(getUser().getUSERID() + "", "Qua_AnimalQuarantineB", "-1", "DateQF,IsPrant,AQNumber,AQCargoOwner,AQXuZhong,AQQuantity,AQDanWei", value, sdate, jdate)
                .map(animBQueryListBean -> {
                    if (animBQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animBQueryListBean.getErrorMsg());
                    }
                    List<AnimBQueryListBean.DataListEntity> dataList = new StatusException(animBQueryListBean).getObjectRefreshDataList(AnimBQueryListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {
        addSubscribe(NetClient.getAnimBListData(getUser().getUSERID() + "", "Qua_AnimalQuarantineB", fstid, "DateQF,IsPrant,AQNumber,AQCargoOwner,AQXuZhong,AQQuantity,AQDanWei", value, sdate, jdate)
                .map(animBQueryListBean -> {
                    if (animBQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animBQueryListBean.getErrorMsg());
                    }
                    List<AnimBQueryListBean.DataListEntity> dataList = new StatusException(animBQueryListBean).getObjectDataList(AnimBQueryListBean.DataListEntity.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
