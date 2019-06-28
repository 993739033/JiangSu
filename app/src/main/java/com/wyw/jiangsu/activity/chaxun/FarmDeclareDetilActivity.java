package com.wyw.jiangsu.activity.chaxun;

import android.os.Bundle;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.presenter.BasePresenter;

/**
 * 养殖场（户）申报列表详情
 * Created by Administrator on 2017/3/27.
 */
public class FarmDeclareDetilActivity extends MVPActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_declare_detil_activity);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("养殖场（户）申报详情");
    }

    @Override
    public void bindListener() {

    }
}
