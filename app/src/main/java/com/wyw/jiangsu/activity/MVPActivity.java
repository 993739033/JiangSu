package com.wyw.jiangsu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.wyw.jiangsu.interfac.IMVP;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.utils.KeyBoardUtils;

import java.util.Date;

/**
 * Created by wyw on 2016/10/31.
 */

public abstract class MVPActivity<T extends BasePresenter> extends BaseActivity implements IMVP {

    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    protected abstract T createPresenter();

    public T getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachActivity();
        }
    }

    /**
     * 点击空白处关闭软键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            //获取当前有焦点的view
//            View currentFocus = getCurrentFocus();
//            if (currentFocus instanceof EditText) {  //加这两行，Edittext文本过长会导致下方控件没有焦点
//                currentFocus.clearFocus();
//            }
//            if (KeyBoardUtils.isHideInput(currentFocus, ev)) {//判定确实需要隐藏
//                KeyBoardUtils.hideSoftInput(this, currentFocus.getWindowToken());
//            }
//        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //获取当前有焦点的view
            View currentFocus = getCurrentFocus();
            if (KeyBoardUtils.isHideInput(currentFocus, ev)) {
                KeyBoardUtils.hideSoftInput(this, currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置持续时间 至少800ms  否则一闪而过 效果不好
     */
    private long time;
    //最小持续时间
    private static final long LEAST_TIME = 1000;

    @Override
    public void showProgress() {
        if (!getDialog().isShowing()) {
            getDialog().show();
            time = new Date().getTime();
        }
    }

    @Override
    public void hideProgress() {
        if (getDialog() != null && getDialog().isShowing()) {
            long persistTime = new Date().getTime() - time;//持续时间
            if (persistTime > LEAST_TIME) {
                getDialog().dismiss();
            } else {
                int remainTime = (int) (LEAST_TIME - persistTime);
                new Handler().postDelayed(() -> {
                    hideProgress();
                }, remainTime);
            }
        }
    }

}
