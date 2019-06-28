package com.wyw.jiangsu.runnable;

import android.util.Log;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.bean.local.Lock;
import com.wyw.jiangsu.interfac.IMVP;
import com.wyw.jiangsu.interfac.IMVPList;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

/**
 * Created by wyw on 2016/12/20.
 * 仅仅上传按钮使用
 */

public class ExceptionUploadImp implements Action1<Throwable> {
    private IMVP view;

    public ExceptionUploadImp() {
    }
    private Lock lock;
    public ExceptionUploadImp(IMVP view, Lock lock) {
        this.view = view;
        this.lock=lock;
    }
    public ExceptionUploadImp(IMVP view) {
        this.view = view;

    }

    @Override
    public void call(Throwable throwable) {
        this.lock.unLock();
        throwable.printStackTrace();
        if (view != null) {
            view.hideProgress();
        }
        if (throwable instanceof RefreshException && view instanceof IMVPList){
            ((IMVPList) view).refreshNone();
        }
        Log.e("ExceptionImp", "error: "+throwable.getMessage());
        if (throwable instanceof HttpException) {
            switch (((HttpException) throwable).code()) {
                case 404:
                    Toast.makeText(MyApplication.getContext(), "网页没有找到404", Toast.LENGTH_SHORT).show();
                case 500:
                    Toast.makeText(MyApplication.getContext(), "服务器错误500", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MyApplication.getContext(),"上传失败"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (view instanceof IMVPList) {
            ((IMVPList) view).onError();
        }
    }
}
