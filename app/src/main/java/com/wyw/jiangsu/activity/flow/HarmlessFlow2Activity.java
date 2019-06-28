package com.wyw.jiangsu.activity.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.WHHZhiPaiActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessConcentrateProcessListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessHomeVeterinatiansListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessMessageAcceptStorageListActivity;
import com.wyw.jiangsu.adapter.HomeBeanAdapeter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessFlow2Activity;
import com.wyw.jiangsu.presenter.HarmlessFlow2ActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.wyw.jiangsu.MyApplication.getContext;

public class HarmlessFlow2Activity extends MVPActivity<HarmlessFlow2ActivityPresenter> implements IHarmlessFlow2Activity, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private HomeBeanAdapeter adapter;
    List<HomeBean.DataList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_flow2);

//        findViewById(R.id.bt_back).setOnClickListener(v -> finish());
//        ((TextView) findViewById(R.id.tv_title)).setText("无害化处理厂");
    }

    @Override
    protected HarmlessFlow2ActivityPresenter createPresenter() {
        return new HarmlessFlow2ActivityPresenter(this);
    }

    /**
     * parent 28
     */
    public void loadPermissionList(String parent) {
        list = new ArrayList<>();
        List<HomeBean.DataList> list1 = (List<HomeBean.DataList>) SPUtils.getInstance().getObjectData(Constance.DATA_PERMISSION);
        List<UploadNoDealWithBean.DataListBean> upload = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (parent.equals(list1.get(i).getFmParent())) {
                list.add(list1.get(i));
            }
            if (list1.get(i).getFSm2().equals("1")) {
                UploadNoDealWithBean.DataListBean bean = new UploadNoDealWithBean.DataListBean();
                bean.setQxid(Integer.parseInt(list1.get(i).getFSm1()));
                bean.setTableName(list1.get(i).getFSm3());
                upload.add(bean);
            }
        }
        adapter.setNewData(list);
        UploadNoDealWithBean uploadNoDealWithBean = new UploadNoDealWithBean();
        uploadNoDealWithBean.setDataList(upload);
        getPresenter().noDealWithInfo(uploadNoDealWithBean);
    }

    @Override
    public void bindData() {
        setTitle("无害化处理厂");
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("28");
    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "53":
                    //收集任务指派
                    startActivity(new Intent(this, WHHZhiPaiActivity.class));
                    break;
                case "15":
                    //收集运输
                    startActivity(new Intent(this, HarmlessMessageAcceptStorageListActivity.class));
                    break;
                case "16":
                    //入场确认
                    startActivity(new Intent(this, HarmlessHomeVeterinatiansListActivity.class));
                    break;
                case "17":
                    //集中处理
                    startActivity(new Intent(this, HarmlessConcentrateProcessListActivity.class));
                    break;
            }
        });
    }

    @Override
    public void onRefresh() {
        loadPermissionList("28");
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
    }

    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {
        for (int i = 0; i < dataLists.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getFSm1().equals(dataLists.get(i).getQxid())) {
                    adapter.getData().get(j).setNumber(dataLists.get(i).getSum());
                }
            }
        }
        adapter.notifyDataSetChanged();
//        adapter.clear();
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(RefreshBus bean) {
        loadPermissionList("28");
    }
}
