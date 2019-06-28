package com.wyw.jiangsu.activity.wuhaihua;

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
import com.wyw.jiangsu.activity.AnimAActivity;
import com.wyw.jiangsu.activity.AnimAddActivity;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.AnimAListDetilAdapter;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimAListActivity;
import com.wyw.jiangsu.presenter.AnimAListActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * 2017-3-15
 * huyanjun
 * 动物A检疫证明列表
 */


public class AnimAListActivity extends MVPActivity<AnimAListActivityPresenter> implements IAnimAListActivity, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;

    private AnimAListDetilAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
    }

    @Override
    protected AnimAListActivityPresenter createPresenter() {
        return new AnimAListActivityPresenter(this);
    }

    @Override
    public void bindData() {
//        getAdd().setVisibility(View.VISIBLE);
        setTitle("动物A检疫证明列表");
        adapter = new AnimAListDetilAdapter(null);
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
                Intent intent = new Intent(AnimAListActivity.this, AnimAActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getItem(position));
                intent.putExtras(bundle);
//                startActivity(intent);
                startActivityForResult(intent, Constance.ACTIVITY_CODE);
            }
        });
        viewEmpty.setOnClickListener(v -> manualRefresh());
        getAdd().setOnClickListener(v -> startActivityForResult(new Intent(this, AnimAddActivity.class), Constance.ACTIVITY_REQUEST_CODE));
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
    public void onError() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
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
