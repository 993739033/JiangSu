package com.wyw.jiangsu.activity.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.AnimAQueryActivity;
import com.wyw.jiangsu.activity.AnimBQueryActivity;
import com.wyw.jiangsu.activity.JianyiTongzhidanQueryActivity;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.ProductionAQueryActivity;
import com.wyw.jiangsu.activity.ProductionBQquryActivity;
import com.wyw.jiangsu.activity.chaxun.AnimalOriginActivity;
import com.wyw.jiangsu.activity.chaxun.AnimalOriginLabelActivity;
import com.wyw.jiangsu.activity.chaxun.AnimalQuarantineActivity;
import com.wyw.jiangsu.activity.chaxun.AnimalQuarantineDisposeActivity;
import com.wyw.jiangsu.activity.chaxun.EmbryoOriginLabelActivity;
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
 * 产地检疫的query浏览
 */
public class QueryQuarantineFlowActivity extends MVPActivity<HarmlessFlowActivityPresenter> implements IHarmlessFlowActivity, SwipeRefreshLayout.OnRefreshListener {
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
     * parent 44
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

        setTitle("产地检疫");
        tvYonghuMingzi.setVisibility(View.VISIBLE);
        tvYonghuMingzi.setText(getPresenter().getUser().getFUNAME());
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("44");
    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "18":
                    //产地检疫申报单
                    startActivity(new Intent(getContext(), AnimalQuarantineActivity.class));
                    break;
                case "19":
                    //动物检疫申报单处理
                    startActivity(new Intent(getContext(), AnimalQuarantineDisposeActivity.class));
                    break;
                case "20":
                    //动物产地工作记录单
                    startActivity(new Intent(getContext(), AnimalOriginActivity.class));
                    break;
                case "21":
                    //乳用、种用动物工作记录单
                    startActivity(new Intent(getContext(), AnimalOriginLabelActivity.class));
                    break;
                case "22":
                    //种蛋、胚胎、精液工作记录单
                    startActivity(new Intent(getContext(), EmbryoOriginLabelActivity.class));
                    break;
                case "23":
                    //动物A检疫证明
                    startActivity(new Intent(getContext(), AnimAQueryActivity.class));
                    break;
                case "24":
                    //动物B检疫证明
                    startActivity(new Intent(getContext(), AnimBQueryActivity.class));
                    break;
                case "25":
                    //产品A检疫证明
                    startActivity(new Intent(getContext(), ProductionAQueryActivity.class));
                    break;
                case "26":
                    //产品B检疫证明
                    startActivity(new Intent(getContext(), ProductionBQquryActivity.class));
                    break;
                case "27":
                    //检疫处理通知单
                    startActivity(new Intent(getContext(), JianyiTongzhidanQueryActivity.class));
                    break;
            }
        });
    }

    @Override
    public void onRefresh() {
        loadPermissionList("44");
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
    }

    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {

    }

    @Override
    public void onActivityResult(RefreshBus bean) {

    }
}
