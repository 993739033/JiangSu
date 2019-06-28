package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.QuarantineDealListQueryBean;
import com.wyw.jiangsu.interfac.IQuarantineProcessNotifQuaryActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public class QuarantineProcessNotifQuaryActivityPresenter extends BasePresenter<IQuarantineProcessNotifQuaryActivity> {
    public QuarantineProcessNotifQuaryActivityPresenter(IQuarantineProcessNotifQuaryActivity view) {
        super(view);
    }

    private String fstId ="-1";
    private String value="";
    private String sdate="";
    private String jdate="";
    public void search(String value, String sdate, String jdate) {
        fstId = "-1";
        this.value = value;
        this.sdate= sdate;
        this.jdate = jdate;                                                                                 //DateQF,IsPrant,AQPhoneCyr,AQCargoOwner,AQXuZhong,AQDwPhone,AQDanWei
        addSubscribe(NetClient.getQuarantineDealListQuery(getUser().getUSERID() + "", "Qua_Notice", fstId, "", value, sdate, jdate)
                .map(QuarantineDealListQueryBean -> {
                    List<QuarantineDealListQueryBean.DataListBean> dataList =new StatusException(QuarantineDealListQueryBean).getObjectRefreshDataList(QuarantineDealListQueryBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                }).subscribe(DataListEntity -> getView().refresh(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData() {
        addSubscribe(NetClient.getQuarantineDealListQuery(getUser().getUSERID() + "", "Qua_Notice", fstId, "DateQF,IsPrant,AQPhoneCyr,AQCargoOwner,AQXuZhong,AQDwPhone,AQDanWei", value, sdate, jdate)
                .map(QuarantineDealListQueryBean -> {
                    List<QuarantineDealListQueryBean.DataListBean> dataList =new StatusException(QuarantineDealListQueryBean).getObjectRefreshDataList(QuarantineDealListQueryBean.DataListBean.class);
                    if (dataList != null && dataList.size() > 0) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                }).subscribe(DataListEntity -> getView().loadMore(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

}
