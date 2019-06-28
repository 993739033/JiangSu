package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.FaramDeclareListBean;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.IFarmDeclareActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public class FarmDeclareActivityPresenter extends BasePresenter<IFarmDeclareActivity> {
    public FarmDeclareActivityPresenter(IFarmDeclareActivity view) {
        super(view);
    }

    private String fstId = "-1";

    public void getFarmDeclare(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getWuHaiHuaCXbean(getUserId(), "V_HT_BSDWSWSB", fstId, "Fsbrq,Fxzxm,Fxxdz,Fclfs,Fxcll,Fyzclx,QDWErBiaoHao,Fykth,Fsfzh",
                value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(WuHaiHuaCXbean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getWuHaiHuaCXbean(getUser().getUSERID() + "", "V_HT_BSDWSWSB", fstid, "Fsbrq,Fxzxm,Fxxdz,Fclfs,Fxcll,Fyzclx,QDWErBiaoHao,Fykth,Fsfzh",
                value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<WuHaiHuaCXbean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(WuHaiHuaCXbean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
