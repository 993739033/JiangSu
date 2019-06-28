package com.wyw.jiangsu.permisson;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by wyw on 2017/1/10.
 */

public class TContextWrap {
    private Activity activity;
    private Fragment fragment;

    public TContextWrap(Activity activity) {
        this.activity = activity;
    }

    public TContextWrap(Fragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();
    }

    public static TContextWrap of(Activity activity) {
        return new TContextWrap(activity);
    }

    public static TContextWrap of(Fragment fragment) {
        return new TContextWrap(fragment);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
