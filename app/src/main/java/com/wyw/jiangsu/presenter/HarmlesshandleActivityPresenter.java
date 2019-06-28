
package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.interfac.IBreedingActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class HarmlesshandleActivityPresenter extends BasePresenter<IBreedingActivity> {
    public HarmlesshandleActivityPresenter(IBreedingActivity view) {
        super(view);
    }

    private String fstId = "-1";


    public void getAnimlQuarantineListData(String value, String sdate, String jdate) {
        addSubscribe(NetClient.getHarmlesshandle(getUserId(), "Ds_WhhjgjlS", "-1", "null", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<BreedingRecordData.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectRefreshDataList(BreedingRecordData.DataListBean.class);
                    return dataList;
                }).subscribe(dataList -> getView().setData(dataList), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    public void loadData(String value, String sdate, String jdate, String fstid) {

        addSubscribe(NetClient.getHarmlesshandle(getUser().getUSERID() + "", "Ds_WhhjgjlS", fstid, "null", value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<BreedingRecordData.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(BreedingRecordData.DataListBean.class);
                    return dataList;
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }
}
