
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
import com.wyw.jiangsu.adapter.HarmlesshandleAdapter;
import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IBreedingActivity;
import com.wyw.jiangsu.presenter.HarmlesshandleActivityPresenter;
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
 * 规模养殖场日常监督检查记录查询列表
 * Created by Administrator on 2017/3/23.
 */

public class HarmlesshandleActivity extends MVPActivity<HarmlesshandleActivityPresenter> implements IBreedingActivity, SpringView.OnFreshListener {

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
    private HarmlesshandleAdapter adapter;

    private BreedingRecordData.DataListBean dataListBean = new BreedingRecordData.DataListBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_recycle);
        ButterKnife.bind(this);

    }

    @Override
    protected HarmlesshandleActivityPresenter createPresenter() {
        return new HarmlesshandleActivityPresenter(this);
    }

    @Override
    public void bindData() {
        tvTitle.setText("无害化处理场所监管记录列表");
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
        headerView = new HeadSearchView(this, mRecyclerView, new HeadSearchView.OnSearchListener() {
            @Override
            public void onSearch(String strat, String end, String data) {
                value = data;
                startStr = strat;
                endStr = end;
                getPresenter().getAnimlQuarantineListData(data, strat, end);
            }
        });
//        adapter = new QueryRecylerAdapter(this, getDataa());
        adapter = new HarmlesshandleAdapter(this, new ArrayList<>(), headerView.getHeaderView());
        mRecyclerView.setAdapter(adapter);

        //进来时，先刷新
//        getPresenter().search();

    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(mRecyclerView).setOnItemClickListener((recyclerView, position, v) -> {
            if (position != 0) {
                Intent intent = new Intent(this, BreedingDetailActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getItem(position));
                bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getDates().get(position - 1));
                bundle.putString("table","Ds_WhhjgjlS");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 手动刷新
     */
    private void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                getPresenter().search();
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
//        getPresenter().search();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        if (adapter.getDates().size() > 0) {
//            getPresenter().loadData(value, startStr, endStr, adapter.getDates().get(adapter.getDates().size() - 1).getFStId());
        }
    }


//    @Override
//    public List<AnimalQuarantineActivityBean.DataListBean> getData(List<AnimalQuarantineActivityBean.DataListBean> list) {
//        adapter.setmDatas(list);
//        return list;
//    }

    @Override
    public void addListData(List<BreedingRecordData.DataListBean> list) {
        adapter.addAll(list);
    }

    @Override
    public void setData(List<BreedingRecordData.DataListBean> list) {
        adapter.setmDatas(list);
    }
}
