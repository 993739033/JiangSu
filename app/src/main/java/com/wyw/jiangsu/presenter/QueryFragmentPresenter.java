package com.wyw.jiangsu.presenter;

import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IQueryFragment;
import com.wyw.jiangsu.runnable.NetClient;

/**
 * Created by Administrator on 2017/3/20.
 */
public class QueryFragmentPresenter extends BasePresenter<IQueryFragment>{
    public QueryFragmentPresenter(IQueryFragment view) {
        super(view);
    }

    public void refresh() {
//        Observable.just((List<HomeBean.DataList>) SPUtils.getInstance().getObjectData(Constance.DATA_PERMISSION))
//                .map(dataLists -> {
//
//                })
        addSubscribe(NetClient.getPermissionList(getUserId(), getUser().getFRID())
                .map(homeBean -> {
                    if (homeBean.getErrorCode() != 0) {
                        throw new NullPointerException(homeBean.getErrorMsg());
                    }
                    return homeBean.getData();
                })
                .subscribe(dataLists -> {
                    getSpUtils().saveObjectData(Constance.DATA_PERMISSION,dataLists);
                    getView().refreshComplete(dataLists);
                },throwable -> {
                    getView().showToast(throwable.getMessage());
                }));
    }
}
