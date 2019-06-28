package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.interfac.IAnimalQuarantineDisposeActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class AnimalQuarantineDisposeActivityPresenter extends BasePresenter<IAnimalQuarantineDisposeActivity> {
    public AnimalQuarantineDisposeActivityPresenter(IAnimalQuarantineDisposeActivity view) {
        super(view);
    }

    public void getAnimlQuarantineDisposeListData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getAnimProcessListDetilQuery(getUserId(), "V_Qua_QuarantineDeclarationCD", "-1", "DateQF,QDWXuZhong,FqSbType,QDWNumber,QDWAccepted,GZ,QDWQuantity,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimProcessListBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(AnimProcessListBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getAnimProcessListDetilQuery(getUser().getUSERID() + "", "V_Qua_QuarantineDeclarationCD", fstid, "DateQF,QDWXuZhong,FqSbType,QDWNumber,QDWAccepted,GZ,QDWQuantity,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimProcessListBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(AnimProcessListBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
