package com.wyw.jiangsu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.WHHCHuKuChooseAdapter;
import com.wyw.jiangsu.bean.WHHChuKuChooseBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IWHHCHuKuChooseActivity;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/15.
 */
public class WHHCHuKuChooseActivity extends MVPActivity<WHHCHuKuChooseActivityPresenter> implements IWHHCHuKuChooseActivity, SpringView.OnFreshListener {

    @BindView(R.id.btn_xuanze)
    Button btnXuanze;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    private WHHCHuKuChooseAdapter adapter;
    private String fstId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_choose);
        ButterKnife.bind(this);
    }

    @Override
    protected WHHCHuKuChooseActivityPresenter createPresenter() {
        return new WHHCHuKuChooseActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("库存");
        fstId = getIntent().getStringExtra("FstId");
        getPresenter().refresh(getPresenter().getUserId(), fstId);
        adapter = new WHHCHuKuChooseAdapter(new ArrayList<>());

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(this));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(this));
        springView.setListener(this);
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());

        btnXuanze.setOnClickListener(v -> {
            List<WHHChuKuChooseBean.DataListBean> list = new ArrayList<WHHChuKuChooseBean.DataListBean>();

            for (int i = 0; i < adapter.getData().size(); i++) {
                if (adapter.getData().get(i).isCheak()) {
                    list.add(adapter.getData().get(i));
                }
            }

            Intent intent = new Intent();
            intent.putExtra(Constance.ACTIVITY_DATA, (Serializable) list);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().refresh(getPresenter().getUserId(), fstId);
                springView.callFresh();
            }
        }, 500);
    }

    @Override
    public void onError() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onRefresh() {
        springView.onFinishFreshAndLoad();
        getPresenter().refresh(getPresenter().getUserId(), fstId);
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().refresh(getPresenter().getUserId(), fstId);
    }

    @Override
    public void setData(List<WHHChuKuChooseBean.DataListBean> list) {
        if (list.size() == 0) {
            viewEmpty.setVisibility(View.VISIBLE);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(list);
        }
    }
}
