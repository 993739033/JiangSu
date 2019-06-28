package com.wyw.jiangsu.presenter;

import android.util.Log;

import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IPrintProductAAcitivty;
import com.wyw.jiangsu.runnable.CompleteImp;
import com.wyw.jiangsu.runnable.ExceptionImp;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/16.
 */
public class PrintProductAAcitivtyPresenter extends BasePresenter<IPrintProductAAcitivty> {

    public PrintProductAAcitivtyPresenter(IPrintProductAAcitivty view) {
        super(view);
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
                },new ExceptionImp(getView(),getLock()),new CompleteImp(getView())));
    }

    public String getMachineNumber() {
        return getSpUtils().getData(Constance.DATA_MACHINE_NUMBER, "", String.class);
    }

    public String getSerialNumber() {
        return getSpUtils().getData(Constance.DATA_SERIAL_NUMBER, "", String.class);
    }

    public void saveSerialNumber(String serialNumber) {
        getSpUtils().saveData(Constance.DATA_SERIAL_NUMBER, serialNumber);

    }

    public void saveMachineNumber(String machineNumber) {
        getSpUtils().saveData(Constance.DATA_MACHINE_NUMBER, machineNumber);

    }


}
