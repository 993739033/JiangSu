package com.wyw.jiangsu.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.wyw.jiangsu.bean.RegistryRecordBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IMainActivity;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.utils.AppUtils;

import rx.functions.Action1;

import static com.wyw.jiangsu.activity.MainActivity.TASK_APK;
import static com.wyw.jiangsu.activity.MainActivity.TASK_DB;

/**
 * Created by wyw on 2016/12/20.
 */
public class MainActivityPresenter extends BasePresenter<IMainActivity> {

    public MainActivityPresenter(IMainActivity view) {
        super(view);
    }

    /**
     * 设置保存权限
     */
    public void loadPermission() {
        NetClient.getPermissionList(getUserId(), getUser().getFRID())
                .map(homeBean -> {
                    if (homeBean.getErrorCode() != 0) {
                        throw new NullPointerException(homeBean.getErrorMsg());
                    }
                    return homeBean.getData();
                })
                .subscribe(dataLists -> {
                    getSpUtils().saveObjectData(Constance.DATA_PERMISSION, dataLists);
                    getView().loadSuccess();
                }, throwable -> {
                    getView().loadFail(throwable.getMessage());
                });
    }

    public void checkApkVersion() {
        String versionName = AppUtils.getAppVersionName();
        NetClient.checkApkVersion(versionName)
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new NullPointerException("不需要更新");
                    }
                    return baseMsgBean.getErrorMsg();
                })
                .subscribe(s -> {
                    //需要更新
                    getView().addTask(TASK_APK);
                }, throwable -> {
//                    loadPermission();
                    //检查数据库版本
//                    checkDbVersion();
                });
    }

    public void checkDbVersion() {
        String dbVersion = getSpUtils().getData(Constance.SP_DB_VERSION, "0", String.class);
        NetClient.checkDbVersion(dbVersion)
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new NullPointerException("不需要更新");
                    }
                    return baseMsgBean.getErrorMsg();
                })
                .subscribe(s -> {
                    //需要更新
                    getView().addTask(TASK_DB);
                }, throwable -> {
                    //检查补丁包
                });
        ;
    }

    //登出记录
    public void uploadLoginInfo(RegistryRecordBean bean) {
        addSubscribe(NetClient.uploadLoginInfo(getUserId(), new Gson().toJson(bean), "App_UserLogRecord")
                .map(baseMsgBean -> {
                    if (baseMsgBean.getErrorCode() != 0) {
                        throw new IllegalArgumentException(baseMsgBean.getErrorMsg());
                    }
                    return baseMsgBean;
                })
                .subscribe(baseMsgBean1 -> {
                    Log.e(LoginActivityPresenter.class.getSimpleName(), baseMsgBean1.getErrorMsg());
                    getView().recordSuccessful();
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().recordSuccessful();
                    }
                }, new CompleteImp(getView()))
        );
    }

}
