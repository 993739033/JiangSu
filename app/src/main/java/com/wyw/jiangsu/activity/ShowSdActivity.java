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
import com.wyw.jiangsu.adapter.FileShowAdapter;
import com.wyw.jiangsu.bean.TongzhiGGBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IShowSdActivity;
import com.wyw.jiangsu.presenter.ShowSdActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

public class ShowSdActivity extends MVPActivity<ShowSdActivityPresenter> implements IShowSdActivity, SpringView.OnFreshListener {
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout rlEmpty;
    private FileShowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sd);
        ButterKnife.bind(this);
    }

    @Override
    protected ShowSdActivityPresenter createPresenter() {
        return new ShowSdActivityPresenter(this);
    }

    public void bindData() {
        setTitle("通知公告");
        mRecyclerView.setHasFixedSize(true);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new FileShowAdapter(this);
        mRecyclerView.setAdapter(adapter);

        getPresenter().refresh();
    }


    public void bindListener() {
        rlEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                TongzhiGGBean.DataListBean bean = adapter.getData().get(position);
                Intent intent = new Intent(ShowSdActivity.this, HtmlActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) bean);
                intent.putExtras(bundle);
                startActivityForResult(intent, Constance.ACTIVITY_CODE);
            }
        });
    }

    @Override
    public void refresh(List<TongzhiGGBean.DataListBean> bean) {
        if (bean != null) {
            rlEmpty.setVisibility(View.GONE);
            adapter.setData(bean);
        } else {
            adapter.clear();
            rlEmpty.setVisibility(View.VISIBLE);
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<TongzhiGGBean.DataListBean> bean) {
        if (bean != null) {
            adapter.addData(bean);
        } else {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        rlEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().refresh();
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
        getPresenter().refresh();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().loadMore();
    }
}
