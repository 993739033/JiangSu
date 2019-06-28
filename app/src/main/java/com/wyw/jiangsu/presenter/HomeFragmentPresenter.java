package com.wyw.jiangsu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.RegistryRecordBean;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHomeFragment;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DownlaodClient;
import com.wyw.jiangsu.runnable.DownloadProgressHandler;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.ProgressHelper;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.AppUtils;
import com.wyw.jiangsu.utils.FileUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by wyw on 2016/12/20.
 */
public class HomeFragmentPresenter extends BasePresenter<IHomeFragment> {
    public HomeFragmentPresenter(IHomeFragment view) {
        super(view);
    }

    @Override
    public void attachActivity(IHomeFragment view) {
        super.attachActivity(view);
        addSubscribe(RxBus.getInstance().toObserverable(RefreshBus.class)
                .subscribe(refreshBus ->
                        getView().onActivityResult(refreshBus)));
    }

    /**
     * 是否更新apk
     */
    public void updateApk() {
        addSubscribe(getRequest().checkApkVersion(AppUtils.getAppVersionName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<BaseMsgBean>() {
                    @Override
                    public void call(BaseMsgBean baseMsgBean) {
                        if (baseMsgBean.getErrorCode() == 0) {
                            //弹出apk下载对话框
                            getView().showApkDialog();
                        } else {
                            getView().showToast("已是最新版本");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showToast(throwable.getMessage());
                    }
                }));
    }

    /**
     * 开始下载apk
     */
    public void startDownApk() {
        //getView().showApkDialog();
        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long progress, long total, boolean done) {
                //在这里dismiss
                getView().setDownDialogProgress(progress, total, done);
                if (done) {
                    getView().onDownloadDone(Constance.TYPE_APK_DONE);
                }
            }
        });
        DownlaodClient.getInstance().downloadApk(FileUtil.getInstance().getApkFile().getAbsolutePath()
        );
    }


    /**
     * 请求数据库版本
     */
    public void upDateDb() {

        //先获取当前数据库的版本
        String dbVersion = getSpUtils().getData(Constance.SP_DB_VERSION, "0.8", String.class);
        //获取服务器传来的服务器版本
        addSubscribe(getRequest().checkDbVersion(dbVersion)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<BaseMsgBean>() {
                               @Override
                               public void call(BaseMsgBean baseMsgBean) {
                                   if (baseMsgBean.getErrorCode() == 0) {
                                       //直接下载数据库  参数：服务器传来的版本号
                                       //   startDownDb(baseMsgBean.setData().getResult());
                                       String version = baseMsgBean.getData().getResult();
                                       //显示是否下载对话框
                                       getView().showDbDialog(version);
                                   } else {
                                       Toast.makeText(MyApplication.getContext(), "已是最新版本", Toast.LENGTH_LONG).show();
                                   }
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                getView().showToast(throwable.getMessage());
                            }
                        }));

    }

    /**
     * 开始下载数据库
     *
     * @param version
     */
    public void startDownDb(String version) {
        //删除之前数据库
        FileUtil.getInstance().getDbFile().delete();

        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long progress, long total, boolean done) {
                getView().setDownDialogProgress(progress, total, done);
                if (done) {
                    //更换DB版本号
                    getSpUtils().saveData(Constance.SP_DB_VERSION, version);
                    getView().onDownloadDone(Constance.TYPE_DB_DONE);
                }
            }
        });
        DownlaodClient.getInstance().downloadDb(FileUtil.getInstance().getDbFile().getAbsolutePath());
    }

    public void refresh() {
        addSubscribe(NetClient.getPermissionList(getUserId(), getUser().getFRID())
                .map(homeBean -> {
                    if (homeBean.getErrorCode() != 0) {
                        throw new NullPointerException(homeBean.getErrorMsg());
                    }
                    return homeBean.getData();
                })
                .subscribe(dataLists -> {
                    getSpUtils().saveObjectData(Constance.DATA_PERMISSION, dataLists);
                    getView().refreshComplete(dataLists);
                }, throwable -> {
                    getView().showToast(throwable.getMessage());
                }));
    }

    public void withoutDealMsg(UploadNoDealWithBean bean) {
        addSubscribe(NetClient.withoutDealMsg(Integer.parseInt(getUserId()), new Gson().toJson(bean))
//                .map(homeBean -> {
//                    if (homeBean.getErrorCode() != 0) {
//                        throw new NullPointerException(homeBean.getErrorMsg());
//                    }
//                    return homeBean.getData();
//                })
                .subscribe(homeBean -> {
                    if (homeBean.getErrorCode() == 0) {
                        getView().refreshNoDeal(homeBean.getData());
                    }
                }, throwable -> {
                    getView().showToast(throwable.getMessage());
                }));
    }

    /**
     * 设置保存权限
     */
    public void loadPermission() {

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
