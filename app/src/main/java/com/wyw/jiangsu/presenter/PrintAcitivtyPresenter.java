package com.wyw.jiangsu.presenter;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IPrintAcitivty;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.DoOnSubscriber;
import com.wyw.jiangsu.runnable.DownlaodClient;
import com.wyw.jiangsu.runnable.DownloadProgressHandler;
import com.wyw.jiangsu.runnable.ExceptionImp;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.PrintShareDownlaodClient;
import com.wyw.jiangsu.runnable.ProgressHelper;
import com.wyw.jiangsu.utils.FileUtil;

import java.io.File;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/3/16.
 */
public class PrintAcitivtyPresenter extends BasePresenter<IPrintAcitivty>{
    public PrintAcitivtyPresenter(IPrintAcitivty view) {
        super(view);
    }


    public void uploadPrint(String type,String id,String tname) {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(NetClient.uploadPrint(type,id,tname,"1")
                .doOnSubscribe(new DoOnSubscriber(getView()))
                .map(BaseMsgBean -> {
                    if (BaseMsgBean.getErrorCode()!=0){
                        Toast.makeText(MyApplication.getContext(), "打印失败", Toast.LENGTH_SHORT).show();
                        getView().Return();
                        throw new IllegalArgumentException(BaseMsgBean.getErrorMsg());
                    }else {
                        Toast.makeText(MyApplication.getContext(), "打印成功", Toast.LENGTH_SHORT).show();
                    }
                    return BaseMsgBean.getData().getResult();
                }).subscribe(DataListBean ->getView().Toath()
                        , new ExceptionImp(getView(),getLock()), new CompleteImp(getView())));
    }

    public void upload() {
        if (getLock().isLock())return;
        getLock().lock();
        addSubscribe(getRequest().printCheck(String.valueOf(getUser().getUSERID()),
                getMachineNumber(), getSerialNumber())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(baseMsg -> {
                    Log.e("PrintAcitivtyPresenter", baseMsg.getErrorMsg());
                }, new ExceptionImp(getView(),getLock()), new CompleteImp(getView())));
    }

    /**
     * 开始下载apk
     */
    public void startDownPrintShareApk() {
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
        PrintShareDownlaodClient.getInstance().downloadPrintShare(new File(Environment.getExternalStorageDirectory(),
                                                                    "PrintShare.apk").getAbsolutePath());
    }

    public void saveMachineNumber(String machineNumber) {
        getSpUtils().saveData(Constance.DATA_MACHINE_NUMBER,machineNumber);
    }

    public void saveSerialNumber(String serialNumber) {
        getSpUtils().saveData(Constance.DATA_SERIAL_NUMBER,serialNumber);
    }
    public String getMachineNumber() {
        return getSpUtils().getData(Constance.DATA_MACHINE_NUMBER,"",String.class);
    }

    public String getSerialNumber() {
        return  getSpUtils().getData(Constance.DATA_SERIAL_NUMBER,"",String.class);
    }
}
