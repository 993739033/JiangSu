package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.AnimBListDetilAdapter;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimBlistActivity;
import com.wyw.jiangsu.presenter.AnimBlistActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动物B检疫证明列表
 * 2017-3-15
 * huyanjun
 */

public class AnimBlistActivity extends MVPActivity<AnimBlistActivityPresenter> implements IAnimBlistActivity, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    private AnimBListDetilAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
        ButterKnife.bind(this);

    }

    @Override
    protected AnimBlistActivityPresenter createPresenter() {
        return new AnimBlistActivityPresenter(this);
    }

    @Override
    public void bindData() {
//        getAdd().setVisibility(View.VISIBLE);
        setTitle("动物B检疫证明列表");
        adapter = new AnimBListDetilAdapter(new ArrayList<>());
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(this));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(this));
        springView.setListener(this);
        getPresenter().refresh();
    }

    @Override
    public void bindListener() {
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(AnimBlistActivity.this, AnimBActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getItem(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, Constance.ACTIVITY_CODE);
            }
        });
        viewEmpty.setOnClickListener(v -> manualRefresh());
    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().refresh();
                springView.callFresh();
            }
        }, 500);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                AnimAProcessListBean.DataListBean bean = (AnimAProcessListBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                manualRefresh();
            }
        }
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
        getPresenter().refresh();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().loadmore();
    }

    @Override
    public void refresh(List<AnimAProcessListBean.DataListBean> dataListBean) {
        if (dataListBean != null) {
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListBean);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.VISIBLE);
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<AnimAProcessListBean.DataListBean> dataListBean) {
        if (dataListBean != null) {
            adapter.addData(dataListBean);
        } else {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }
}
