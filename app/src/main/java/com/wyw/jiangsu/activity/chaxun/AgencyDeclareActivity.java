package com.wyw.jiangsu.activity.chaxun;

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
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.model.HeadSearchView;
import com.wyw.jiangsu.adapter.AgencyDeclareListAdapter;
import com.wyw.jiangsu.bean.AgencyDeclareListBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAgencyDeclareActivity;
import com.wyw.jiangsu.presenter.AgencyDeclareActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * 监管兽医代申报  代理申报列表
 * Created by Administrator on 2017/3/27.
 */
public class AgencyDeclareActivity extends MVPActivity<AgencyDeclareActivityPresenter> implements IAgencyDeclareActivity, SpringView.OnFreshListener {

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

    String value = "";
    String startStr = "";
    String endStr = "";
    String fstid = "";
    private HeadSearchView headerView;
    private AgencyDeclareListAdapter adapter;
    List<AgencyDeclareListBean.DataListBean> listBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_recycle);
        ButterKnife.bind(this);

    }

    @Override
    protected AgencyDeclareActivityPresenter createPresenter() {
        return new AgencyDeclareActivityPresenter(this);
    }

    @Override
    public void bindData() {
        tvTitle.setText("代理申报列表");
//        getPresenter().refresh();
        listBeen = new ArrayList<>();
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
//        adapter = new QueryRecylerAdapter(this, getDataa());
        headerView = new HeadSearchView(this, mRecyclerView, new HeadSearchView.OnSearchListener() {
            @Override
            public void onSearch(String strat, String end, String data) {
                value = data;
                startStr = strat;
                endStr = end;
                getPresenter().getAgencyDeclare(data, strat, end);
            }
        });
        adapter = new AgencyDeclareListAdapter(this, new ArrayList<>(), headerView.getHeaderView());
        mRecyclerView.setAdapter(adapter);

        //进来时，先刷新
//        getPresenter().refresh();

    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(mRecyclerView).setOnItemClickListener((recyclerView, position, v) -> {
            if (position != 0) {
                Intent intent = new Intent(this, AgencyDeclareDetilActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getDates().get(position - 1));
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().getAgencyDeclare(value, startStr, endStr);
    }

    /**
     * 手动刷新
     */
    private void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                getPresenter().refresh();
                springView.callFresh();
            }
        }, 500);
    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onRefresh() {
        springView.onFinishFreshAndLoad();
        getPresenter().getAgencyDeclare(value, startStr, endStr);
//        getPresenter().refresh();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
//        getPresenter().loadmore();
        if (adapter.getDates().size() > 0) {
            getPresenter().loadData(value, startStr, endStr, adapter.getDates().get(adapter.getDates().size() - 1).getFStId());
        }
    }

    @Override
    public void addListData(List<WuHaiHuaCXbean.DataListBean> list) {
        adapter.addAll(list);
    }

    @Override
    public void setData(List<WuHaiHuaCXbean.DataListBean> list) {
        adapter.setmDatas(list);
    }
}
