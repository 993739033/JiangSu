package com.wyw.jiangsu.activity.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.BaseActivity;
import com.wyw.jiangsu.activity.chaxun.CollectionTransportActivivty;
import com.wyw.jiangsu.activity.chaxun.ConcentrateDisposeActivity;
import com.wyw.jiangsu.activity.chaxun.EntranceEnsureActivity;
import com.wyw.jiangsu.adapter.HomeBeanAdapeter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.wyw.jiangsu.MyApplication.getContext;

public class QueryHarmlessFlow2Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private HomeBeanAdapeter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_flow2);

//        findViewById(R.id.bt_back).setOnClickListener(v -> finish());
//        ((TextView) findViewById(R.id.tv_title)).setText("无害化处理厂");
    }

    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_transportation:
//                startActivity(new Intent(this, HarmlessMessageAcceptStorageListActivity.class));
//                break;
//            case R.id.bt_confirm:
//                startActivity(new Intent(this, HarmlessHomeVeterinatiansListActivity.class));
//                break;
//            case R.id.bt_concertrate_process:
//                startActivity(new Intent(this, HarmlessConcentrateProcessListActivity.class));
////                startActivity(new Intent(this, HarmlessConcentrateProcessListActivity.class));
//                break;
    }

    /**
     * parent 37
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

        setTitle("无害化处理厂");
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("37");
    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "34":
                    //收集运输
                    startActivity(new Intent(this, CollectionTransportActivivty.class));
                    break;
                case "35":
                    //入场确认
                    startActivity(new Intent(this, EntranceEnsureActivity.class));
                    break;
                case "36":
                    //集中处理
                    startActivity(new Intent(this, ConcentrateDisposeActivity.class));
                    break;
            }
        });
    }

    @Override
    public void onRefresh() {
        loadPermissionList("37");
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
    }
}
