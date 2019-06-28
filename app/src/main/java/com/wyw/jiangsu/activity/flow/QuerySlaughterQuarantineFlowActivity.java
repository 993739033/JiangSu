package com.wyw.jiangsu.activity.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.chaxun.RuChangDengjiQueryActivity;
import com.wyw.jiangsu.activity.chaxun.ZaiqianCheckQueryActivity;
import com.wyw.jiangsu.adapter.HomeBeanAdapeter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessFlowActivity;
import com.wyw.jiangsu.presenter.HarmlessFlowActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * Created by Administrator on 2017/4/20.
 */
public class QuerySlaughterQuarantineFlowActivity extends MVPActivity<HarmlessFlowActivityPresenter> implements IHarmlessFlowActivity, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    private HomeBeanAdapeter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_flow);
        ButterKnife.bind(this);
    }

    @Override
    protected HarmlessFlowActivityPresenter createPresenter() {
        return new HarmlessFlowActivityPresenter(this);
    }

    /**
     * parent 45
     */
    public void loadPermissionList(String parent) {
        List<HomeBean.DataList> list = new ArrayList<>();
        List<HomeBean.DataList> list1 = (List<HomeBean.DataList>) SPUtils.getInstance().getObjectData(Constance.DATA_PERMISSION);
        for (int i = 0; i < list1.size(); i++) {
            if (parent.equals(list1.get(i).getFmParent())) {
                list.add(list1.get(i));
            }
        }
        adapter.setNewData(list);
    }

    @Override
    public void bindData() {
        setTitle("屠宰检疫");
        tvYonghuMingzi.setVisibility(View.VISIBLE);
        tvYonghuMingzi.setText(getPresenter().getUser().getFUNAME());
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("45");
    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "38":
                    //入场查验登记
                    startActivity(new Intent(getContext(), RuChangDengjiQueryActivity.class));
                    break;
                case "41":
                    //宰前检查
                    startActivity(new Intent(getContext(), ZaiqianCheckQueryActivity.class));
                    break;
            }
        });
    }

    @Override
    public void onRefresh() {
        loadPermissionList("45");
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
    }

    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {

    }

    @Override
    public void onActivityResult(RefreshBus bean) {

    }
}
