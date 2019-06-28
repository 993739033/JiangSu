package com.wyw.jiangsu.activity.zhifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ScrollView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.XianchangCheckBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mnkj004 on 2017/8/11.
 */

public class JianchaDetailActivity extends MVPActivity {


    @BindView(R.id.et_check_didian)
    EditText etCheckDidian;
    @BindView(R.id.et_xunwen_jiguan)
    EditText etXunwenJiguan;
    @BindView(R.id.et_dangshiren)
    EditText etDangshiren;
    @BindView(R.id.et_check_jiguan)
    EditText etCheckJiguan;
    @BindView(R.id.et_check_renyuan1)
    EditText etCheckRenyuan1;
    @BindView(R.id.et_zhifa_zhengjianhao1)
    EditText etZhifaZhengjianhao1;
    @BindView(R.id.et_check_renyuan2)
    EditText etCheckRenyuan2;
    @BindView(R.id.et_zhifa_zhengjianhao2)
    EditText etZhifaZhengjianhao2;
    @BindView(R.id.et_jiluren)
    EditText etJiluren;
    @BindView(R.id.et_check_qingkuang)
    EditText etCheckQingkuang;
    @BindView(R.id.SV)
    ScrollView SV;
    private AnimalQuarantineActivityBean.DataListBean dataListBean;
    private XianchangCheckBean.DataListBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiancha_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("现场检查（勘验）笔录详情");
        Intent intent = getIntent();
        data = (XianchangCheckBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
        String tableName = intent.getStringExtra("table");
        //检查地点  etCheckDidian
        etCheckDidian.setText(data.getFdz());
        //询问机关  etXunwenJiguan
        etXunwenJiguan.setText(data.getFxwjg());
        //当事人  etDangshiren
        etDangshiren.setText(data.getFdsr());
        //检查机关  etCheckJiguan
        etCheckJiguan.setText(data.getFjg());
        //检查人员1  etCheckRenyuan1
        etCheckRenyuan1.setText(data.getFkcry());
        //执法证件号1  etZhifaZhengjianhao1
        etZhifaZhengjianhao1.setText(data.getFzfzjh());
        //检查人员2  etCheckRenyuan2
        etCheckRenyuan2.setText(data.getFkcry1());
        //执法证件号2  etZhifaZhengjianhao2
        etZhifaZhengjianhao2.setText(data.getFzfzjh1());
        //记录人  etJiluren
        etJiluren.setText(data.getFjlr());
        //现场检查（勘验）情况  etCheckQingkuang
        etCheckQingkuang.setText(data.getFkcjl());

    }

    @Override
    public void bindListener() {

    }
}
