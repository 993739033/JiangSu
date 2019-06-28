package com.wyw.jiangsu.activity.zhifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.CaseRegisterBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mnkj004 on 2017/8/11.
 */

public class CaseRegisterDetailActivity extends MVPActivity {

    @BindView(R.id.et_signdate)
    TextView etSigndate;
    @BindView(R.id.et_anyou)
    EditText etAnyou;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.tv_type)
    TextView tvType;
    private CaseRegisterBean.DataListBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dengji_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("案件登记详情");
        Intent intent = getIntent();
        data = (CaseRegisterBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
        String tableName = intent.getStringExtra("table");
//        etAnyou.setText();
        //登记日期  etSigndate
        etSigndate.setText(data.getFScTime());
        //anyou   etAnyou
        etAnyou.setText(data.getFany());
        //来源         tvSource
        tvSource.setText(data.getFly());
        //案件类型    tvType
        tvType.setText(data.getFajlx());
    }

    @Override
    public void bindListener() {

    }
}



