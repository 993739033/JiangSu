
package com.wyw.jiangsu.activity.chaxun;

import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.BreedingDetailshowBean;
import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.interfac.IBreedingActivity;
import com.wyw.jiangsu.interfac.IBreedingDetailActivity;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.StatusException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public class BreedingDetailActivityPresenter extends BasePresenter<IBreedingDetailActivity> {
    public BreedingDetailActivityPresenter(IBreedingDetailActivity view) {
        super(view);
    }

    public void getBreedingDetailData1(String tableName, String sfstid) {
        addSubscribe(
                NetClient.getBreedingDetilQuery1(sfstid,tableName)
                .subscribe(
                        dataList -> getView().setData(dataList),
                        new ExceptionImp(getView()),
                        new CompleteImp(getView()))
        );
    }


    public void getData(String tablename, String meifstid) {
        addSubscribe(
                NetClient.getBreedingDetail1(tablename,meifstid)
                .map(animAQueryListBean -> {
                    if (animAQueryListBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(animAQueryListBean.getErrorMsg());
                    }
                    BreedingDetailshowBean.DataListBean dataList = new StatusException(animAQueryListBean).getObjectData(BreedingDetailshowBean.DataListBean.class);
                    return dataList;
                }).subscribe(dataList -> getView().setData(dataList), new ExceptionImp(getView()), new CompleteImp(getView())));
    }

}
