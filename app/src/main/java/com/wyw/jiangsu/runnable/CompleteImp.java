package com.wyw.jiangsu.runnable;

import com.wyw.jiangsu.interfac.IMVP;

import rx.functions.Action0;

/**
 * Created by wyw on 2016/12/20.
 * subscribe()回调完成监听
 */

public class CompleteImp implements Action0 {
    private IMVP view;

    public CompleteImp() {
    }

    public CompleteImp(IMVP view) {
        this.view = view;
    }

    @Override
    public void call() {
        if (view != null) {
            view.hideProgress();
        }
    }
}
