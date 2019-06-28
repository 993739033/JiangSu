package com.wyw.jiangsu.fragment;

import android.content.Context;

import com.wyw.jiangsu.interfac.IMVP;
import com.wyw.jiangsu.presenter.BasePresenter;


/**
 * Created by wyw on 2016/11/1.
 */

public abstract class MVPFragment<T extends BasePresenter> extends BaseFragment implements IMVP {
    protected T presenter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = createPresenter();
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachActivity();
        }
    }

    public void showProgress() {
        if (!getDialog().isShowing()) {
            getDialog().show();
        }
    }

    @Override
    public void hideProgress() {
        if (getDialog().isShowing()) {
            getDialog().dismiss();
        }
    }

    public T getPresenter() {
        return presenter;
    }
}
