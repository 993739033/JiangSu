package com.wyw.jiangsu.presenter;

import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.interfac.IContentFragment;
import com.wyw.jiangsu.runnable.NetClient;

/**
 * Created by wyw on 2018/1/29.
 */

public class ContentFragmentPresenter extends BasePresenter<IContentFragment> {
    public ContentFragmentPresenter(IContentFragment view) {
        super(view);
    }

}
