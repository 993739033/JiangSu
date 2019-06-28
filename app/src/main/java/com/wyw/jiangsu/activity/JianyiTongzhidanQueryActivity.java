package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.chaxun.AnimalAQueryDetilActivity;
import com.wyw.jiangsu.activity.model.HeadSearchView;
import com.wyw.jiangsu.adapter.JianyiTongzhidanAdapter;
import com.wyw.jiangsu.bean.JianyiChuliTongzhidanListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IJianyiTongzhidanQueryActivity;
import com.wyw.jiangsu.presenter.JianyiTongzhidanQueryActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;
import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * Created by Administrator on 2017/4/12.
 */

public class JianyiTongzhidanQueryActivity extends MVPActivity<JianyiTongzhidanQueryActivityPresenter> implements IJianyiTongzhidanQueryActivity, SpringView.OnFreshListener {

    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;

    private JianyiTongzhidanAdapter adapter;
    String value = "";
    String startStr = "";
    String endStr = "";
    String fstid = "";
    private HeadSearchView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_recycle);
        ButterKnife.bind(this);
    }

    @Override
    protected JianyiTongzhidanQueryActivityPresenter createPresenter() {
        return new JianyiTongzhidanQueryActivityPresenter(this);
    }

    @Override
    public void bindData() {
        tvTitle.setText("检疫处理通知单查询列表");
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
        test = new HeadSearchView(this, mRecyclerView, new HeadSearchView.OnSearchListener() {
            @Override
            public void onSearch(String strat, String end, String data) {
                value = data;
                startStr = strat;
                endStr = end;
                getPresenter().getJianyiTongzhidan(data, strat, end);
            }
        });
        adapter = new JianyiTongzhidanAdapter(this, new ArrayList<>(), test.getHeaderView());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(mRecyclerView).setOnItemClickListener((recyclerView, position, v) -> {
            if (position != 0) {
                Intent intent = new Intent(this, JianyiChuliXiangqingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getDates().get(position - 1));
                intent.putExtras(bundle);
                startActivity(intent);
            }
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
                //getPresenter().search();
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
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        if (adapter.getDates().size() > 0) {
            getPresenter().loadData(value, startStr, endStr, adapter.getDates().get(adapter.getDates().size() - 1).getFStId());
        }
    }


//    @Override
//    public List<JianyiChuliTongzhidanListBean.DataListBean> getData(List<JianyiChuliTongzhidanListBean.DataListBean> list) {
//        adapter.setmDatas(list);
//        return list;
//    }

    @Override
    public void addListData(List<JianyiChuliTongzhidanListBean.DataListBean> list) {
        adapter.addAll(list);
    }

    @Override
    public void setData(List<JianyiChuliTongzhidanListBean.DataListBean> list) {
        adapter.setmDatas(list);
    }
}
