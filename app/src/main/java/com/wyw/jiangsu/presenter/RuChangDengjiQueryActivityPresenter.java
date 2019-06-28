package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.ProductBListBean;
import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.interfac.IRuChangDengjiQueryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public class RuChangDengjiQueryActivityPresenter extends BasePresenter<IRuChangDengjiQueryActivity> {
    public RuChangDengjiQueryActivityPresenter(IRuChangDengjiQueryActivity view) {
        super(view);
    }


    public void getRuChangDengjiData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getRuChangChaYanQueryBean(getUser().getUSERID() + "", "Qua_Entrance", "-1", "eDate,eOwner,eNo,eCOwner,eAnimal,eNum,eUnit", value, sdate, jdate)
                .map(productBListBean -> {
                    if (productBListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productBListBean.getErrorMsg());
                    }
                    List<RuChangChaYanQueryBean.DataListBean> dataList = new StatusException(productBListBean).getObjectRefreshDataList(RuChangChaYanQueryBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().setData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {
        addSubscribe(NetClient.getRuChangChaYanQueryBean(getUser().getUSERID() + "", "Qua_Entrance", fstid, "eDate,eOwner,eNo,eCOwner,eAnimal,eNum,eUnit", value, sdate, jdate)
                .map(productBListBean -> {
                    if (productBListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productBListBean.getErrorMsg());
                    }
                    List<RuChangChaYanQueryBean.DataListBean> dataList = new StatusException(productBListBean).getObjectDataList(RuChangChaYanQueryBean.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
