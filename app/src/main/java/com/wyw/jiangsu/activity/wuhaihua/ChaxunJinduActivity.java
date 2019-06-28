package com.wyw.jiangsu.activity.wuhaihua;

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
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.model.HeadSearchView;
import com.wyw.jiangsu.adapter.ChaxunJinduActuvityAdapter;
import com.wyw.jiangsu.bean.ChaxunJinduActuvityBean;
import com.wyw.jiangsu.interfac.IChaxunJinduActivity;
import com.wyw.jiangsu.presenter.ChaxunJinduActuvityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

public class ChaxunJinduActivity extends MVPActivity<ChaxunJinduActuvityPresenter> implements IChaxunJinduActivity, SpringView.OnFreshListener {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    private HeadSearchView headerView;
    private ChaxunJinduActuvityAdapter adapter;
    String value = "";
    String startStr = "";
    String endStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_recycle);
        ButterKnife.bind(this);


    }

    @Override
    protected ChaxunJinduActuvityPresenter createPresenter() {
        return new ChaxunJinduActuvityPresenter(this);
    }

    @Override
    public void bindData() {
        tvTitle.setText("查询进度列表页");
        rvList.setHasFixedSize(true);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
        rvList.setItemAnimator(new DefaultItemAnimator());
        headerView = new HeadSearchView(this, rvList, new HeadSearchView.OnSearchListener() {
            @Override
            public void onSearch(String strat, String end, String data) {
                value = data;
                startStr = strat;
                endStr = end;
                getPresenter().getFarmDeclare(data, strat, end);
            }
        });
        adapter = new ChaxunJinduActuvityAdapter(this, new ArrayList<>(), headerView.getHeaderView());
        rvList.setAdapter(adapter);
    }

    @Override
    public void bindListener() {
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
    public void addListData(List<ChaxunJinduActuvityBean.DataListBean> dataListEntity) {
        adapter.addAll(dataListEntity);
    }

    @Override
    public void setData(List<ChaxunJinduActuvityBean.DataListBean> dataListEntity) {
        adapter.setmDatas(dataListEntity);
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
}
