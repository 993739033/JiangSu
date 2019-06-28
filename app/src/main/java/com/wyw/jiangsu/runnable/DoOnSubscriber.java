package com.wyw.jiangsu.runnable;

import com.wyw.jiangsu.interfac.IMVP;

import rx.functions.Action0;

/**
 * Created by wyw on 2016/12/22.
 */

public class DoOnSubscriber implements Action0{
    private IMVP view;

    public DoOnSubscriber() {

    }

    public DoOnSubscriber(IMVP view) {
        this.view = view;
    }

    @Override
    public void call() {
        if (view != null) {
            view.showProgress();
        }
    }
}
