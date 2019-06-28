package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.interfac.IAnimalQuarantineActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class AnimalQuarantineActivityPresenter extends BasePresenter<IAnimalQuarantineActivity> {
    public AnimalQuarantineActivityPresenter(IAnimalQuarantineActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void getAnimlQuarantineListData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getGuarantineDeclareListDetilQuery(getUserId(), "V_Qua_QuarantineDeclarationCD", "-1", "DateQF,QDWXuZhong,FqSbType,QDWNumber,QDWAccepted,GZ,QDWQuantity,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimalQuarantineActivityBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(AnimalQuarantineActivityBean.DataListBean.class);
                    return dataList;
                }).subscribe(dataList -> getView().setData(dataList), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getGuarantineDeclareListDetilQuery(getUser().getUSERID() + "", "V_Qua_QuarantineDeclarationCD", fstid, "DateQF,QDWXuZhong,FqSbType,QDWNumber,QDWAccepted,GZ,QDWQuantity,QDWDanWei", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<AnimalQuarantineActivityBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(AnimalQuarantineActivityBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
