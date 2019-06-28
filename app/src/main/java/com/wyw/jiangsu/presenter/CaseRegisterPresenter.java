package com.wyw.jiangsu.presenter;

import android.support.annotation.Nullable;

import com.wyw.jiangsu.bean.BaseArrayObjectEntity;
import com.wyw.jiangsu.bean.BaseMsg;
import com.wyw.jiangsu.bean.BaseObjectEntity;
import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.bean.CaseRegisterBean;
import com.wyw.jiangsu.bean.ChufaListBean;
import com.wyw.jiangsu.bean.XianchangCheckBean;
import com.wyw.jiangsu.bean.XunWenListBean;
import com.wyw.jiangsu.interfac.IACaseRegisterActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj004 on 2017/8/11.
 */

public class CaseRegisterPresenter extends BasePresenter<IACaseRegisterActivity> {
    public CaseRegisterPresenter(IACaseRegisterActivity view) {
        super(view);
    }

    public void getCaseRegisterData(int beanType, String tableName, String name, String value, String sdate, String jdate) {
        addSubscribe(NetClient.getCaseRegistDetilQuery(getUserId(), tableName, "-1", name, value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, animAQueryListBean);
                    if (dataList != null) return dataList;
                    return new ArrayList<>();
                }).subscribe(dataList -> getView().setData(dataList), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void getXianChangData(int beanType, String tableName, String name, String value, String sdate, String jdate) {
        addSubscribe(NetClient.getXianChangDataQuery(getUserId(), tableName, "-1", name, value, sdate, jdate)
                .map(aimAQueryListBean -> {
                    if (aimAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(aimAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, aimAQueryListBean);
                    if (dataList != null)
                        return dataList;
                    return new ArrayList<>();
                }).subscribe(dataList -> getView().setData(dataList), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void getXunwenData(int beanType, String tableName, String name, String value, String sdate, String jdate) {
        addSubscribe(NetClient.getXunwenDataQuery(getUserId(), tableName, "-1", name, value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, animAQueryListBean);
                    if (dataList != null) return dataList;
                    return new ArrayList<>();
                }).subscribe(dataList -> getView().setData(dataList), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void getChufaData(int beanType, String tableName, String name, String value, String sdate, String jdate) {
        addSubscribe(NetClient.getChufaDataQuery(getUserId(), tableName, "-1", name, value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, animAQueryListBean);
                    if (dataList != null) return dataList;
                    return new ArrayList<>();
                }).subscribe(dataList -> getView().setData(dataList), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


    @Nullable
    private List<? extends Object> getDataList(int beanType, BaseArrayObjectEntity animAQueryListBean) {
        if (beanType == 1) {
            List<CaseRegisterBean.DataListBean> dataList = new StatusException( animAQueryListBean).getObjectRefreshDataList(CaseRegisterBean.DataListBean.class);
            return dataList;
        } else if (beanType == 2) {
            List<XianchangCheckBean.DataListBean> dataList = new StatusException( animAQueryListBean).getObjectRefreshDataList(XianchangCheckBean.DataListBean.class);
            return dataList;
        } else if (beanType == 3) {
            List<XunWenListBean.DataListBean> dataList = new StatusException( animAQueryListBean).getObjectRefreshDataList(XunWenListBean.DataListBean.class);
            return dataList;
        } else if (beanType == 4) {
            List<ChufaListBean.DataListBean> dataList = new StatusException( animAQueryListBean).getObjectRefreshDataList(ChufaListBean.DataListBean.class);
            return dataList;
        }
        return null;
    }


    public void loadCaseData(int beanType,String value, String sdate, String jdate, String fstid, String tablename, String name) {

        addSubscribe(NetClient.getCaseRegistDetilQuery(getUser().getUSERID() + "", tablename, fstid, name, value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, animAQueryListBean);
//                    List<CaseRegisterBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(CaseRegisterBean.DataListBean.class);
                    if (dataList != null) return dataList;
                    return new ArrayList<>();
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadXianchangData(int beanType,String value, String sdate, String jdate, String fstid, String tablename, String name) {

        addSubscribe(NetClient.getXianChangDataQuery(getUser().getUSERID() + "", tablename, fstid, name, value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, animAQueryListBean);
//                    List<CaseRegisterBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(CaseRegisterBean.DataListBean.class);
                    if (dataList != null) return dataList;
                    return new ArrayList<>();
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadXunwenData(int beanType,String value, String sdate, String jdate, String fstid, String tablename, String name) {

        addSubscribe(NetClient.getXunwenDataQuery(getUser().getUSERID() + "", tablename, fstid, name, value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, animAQueryListBean);
//                    List<CaseRegisterBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(CaseRegisterBean.DataListBean.class);
                    if (dataList != null) return dataList;
                    return new ArrayList<>();
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

    public void loadChufaData(int beanType,String value, String sdate, String jdate, String fstid, String tablename, String name) {

        addSubscribe(NetClient.getChufaDataQuery(getUser().getUSERID() + "", tablename, fstid, name, value, sdate, jdate)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    List<? extends Object> dataList = getDataList(beanType, animAQueryListBean);
//                    List<CaseRegisterBean.DataListBean> dataList = new StatusException(animAQueryListBean).getObjectDataList(CaseRegisterBean.DataListBean.class);
                    if (dataList != null) return dataList;
                    return new ArrayList<>();
                }).subscribe(DataListEntity -> getView().addListData(DataListEntity), new ExceptionImp(getView()), new CompleteImp(getView())));
    }


}
