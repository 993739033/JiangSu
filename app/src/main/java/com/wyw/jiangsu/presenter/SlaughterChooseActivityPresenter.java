package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.SlaughterAnimTypeBean;
import com.wyw.jiangsu.bean.SlaughterChooseBean;
import com.wyw.jiangsu.bean.SlaughterSpHouseBean;
import com.wyw.jiangsu.interfac.ISlaughterChooseActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhyzh on 2017/4/14.
 */

public class SlaughterChooseActivityPresenter extends BasePresenter<ISlaughterChooseActivity> {
    List<SlaughterSpHouseBean.DataListEntity> spHouseList = new ArrayList<>();
    List<SlaughterAnimTypeBean.DataListEntity> spAnimTypeList = new ArrayList<>();
    private String animType;
    private String tid;
    private String value;
    private String fstId = "-1";

    public SlaughterChooseActivityPresenter(ISlaughterChooseActivity view) {
        super(view);
    }

    public void getSpSlaughterData() {
        addSubscribe(NetClient.getSlaughterSpData(getUserId(), "", "", "", "", "")
                .map(slaughterSpHouseBean -> {
                    List<SlaughterSpHouseBean.DataListEntity> dataList = new StatusException(slaughterSpHouseBean).getObjectRefreshDataList(SlaughterSpHouseBean.DataListEntity.class);
                    if (slaughterSpHouseBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(slaughterSpHouseBean.getErrorMsg());
                    }
                    spHouseList.addAll(dataList);
                    return dataList;
                }).subscribe(dataListEntities -> {
                    getView().setSpSlaughterData(dataListEntities);
                }, new ExceptionImp(getView()), new CompleteImp(getView()))
        );
    }

    public List<SlaughterSpHouseBean.DataListEntity> getSpHouseList() {

        return spHouseList;
    }

    public List<SlaughterAnimTypeBean.DataListEntity> getSpAnimTypeList() {
        return spAnimTypeList;
    }

    public void getAnimaType(String tid) {
        addSubscribe(NetClient.getAnimaTypeData(getUserId(), tid, "", "", "", "")
                .map(slaughterAnimTypeBean -> {
                    List<SlaughterAnimTypeBean.DataListEntity> dataList = new StatusException(slaughterAnimTypeBean).getObjectRefreshDataList(SlaughterAnimTypeBean.DataListEntity.class);
                    if (slaughterAnimTypeBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(slaughterAnimTypeBean.getErrorMsg());
                    }
                    spAnimTypeList.addAll(dataList);
                    return dataList;
                }).subscribe(dataListEntities -> getView().setSpAnimTypeData(dataListEntities), new ExceptionImp(getView()), new CompleteImp(getView()))
        );
    }

    public void getDataList(String tid, String animType, String value) {
        fstId = "-1";
        this.tid = tid;
        this.animType = animType;
        this.value = value;
        addSubscribe(NetClient.getChooseDataList(getUserId(), tid, animType, "QCPNumber,DateQy,SBRMC,QCPCargoOwner,QCProduct,SL", value, fstId)
                .map(slaughterChooseBean -> {
                    List<SlaughterChooseBean.DataListEntity> dataList = new StatusException(slaughterChooseBean).getObjectRefreshDataList(SlaughterChooseBean.DataListEntity.class);
                    if (dataList != null) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                }).subscribe(dataListEntities -> getView().setDataList(dataListEntities), new ExceptionImp(getView()), new CompleteImp(getView()))
        );
    }

    public void refresh() {
        getDataList(tid, animType, value);
    }

    public void loadMore() {
        addSubscribe(NetClient.getChooseDataList(getUserId(), tid, animType, "QCPNumber,DateQy,SBRMC,QCPCargoOwner,QCProduct,SL", value, fstId)
                .map(slaughterChooseBean -> {
                    List<SlaughterChooseBean.DataListEntity> dataList = new StatusException(slaughterChooseBean).getObjectRefreshDataList(SlaughterChooseBean.DataListEntity.class);
                    if (dataList != null) {
                        fstId = dataList.get(dataList.size() - 1).getFStId();
                    }
                    return dataList;
                }).subscribe(dataListEntities -> getView().loadMore(dataListEntities), new ExceptionImp(getView()), new CompleteImp(getView()))
        );
    }
}
