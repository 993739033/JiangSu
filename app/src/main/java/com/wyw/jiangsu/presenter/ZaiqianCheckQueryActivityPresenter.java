package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.ZaiqianCheckQueryBean;
import com.wyw.jiangsu.interfac.IZaiqianCheckQueryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class ZaiqianCheckQueryActivityPresenter extends BasePresenter<IZaiqianCheckQueryActivity> {

    public ZaiqianCheckQueryActivityPresenter(IZaiqianCheckQueryActivity view) {
        super(view);
    }


    public void getZaiqianCheckData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getZaiqianCheckQueryBean(getUser().getUSERID() + "", "Qua_Diebefore", "-1", "FDate,TZJYBH,SBDBH,JCSL,JYRY,JLRY", value, sdate, jdate)
                .map(productBListBean -> {
                    if (productBListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productBListBean.getErrorMsg());
                    }
                    List<ZaiqianCheckQueryBean.DataListBean> dataList = new StatusException(productBListBean).getObjectRefreshDataList(ZaiqianCheckQueryBean.DataListBean.class);
                    return dataList;
                }).subscribe(dataListEntity -> getView().setData(dataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {
        addSubscribe(NetClient.getZaiqianCheckQueryBean(getUser().getUSERID() + "", "Qua_Diebefore", fstid, "FDate,TZJYBH,SBDBH,JCSL,JYRY,JLRY", value, sdate, jdate)
                .map(productBListBean -> {
                    if (productBListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(productBListBean.getErrorMsg());
                    }
                    List<ZaiqianCheckQueryBean.DataListBean> dataList = new StatusException(productBListBean).getObjectDataList(ZaiqianCheckQueryBean.DataListBean.class);
                    return dataList;
                }).subscribe(dataListEntity -> getView().addListData(dataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

}
