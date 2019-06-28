package com.wyw.jiangsu.activity.supervision;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.CommonSupervisionAdapter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ICommonSupervisionListActivity;
import com.wyw.jiangsu.presenter.CommonSupervisionListActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.utils.SPUtils;
import com.wyw.jiangsu.view.CustomSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 各种上传
 */
public class CommonSupervisionListActivity extends MVPActivity<CommonSupervisionListActivityPresenter> implements ICommonSupervisionListActivity, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.commonSwipeLayout)
    CustomSwipeRefreshLayout commonSwipeLayout;
    @BindView(R.id.commonRecycler)
    RecyclerView commonRecycler;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;


    private CommonSupervisionAdapter adapter;

    private List<HomeBean.DataList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_supervision);
        ButterKnife.bind(this);
    }

    @Override
    protected CommonSupervisionListActivityPresenter createPresenter() {
        return new CommonSupervisionListActivityPresenter(this);
    }


    @Override
    public void bindData() {
        setTitle("日常监管");
        tvYonghuMingzi.setVisibility(View.VISIBLE);
        tvYonghuMingzi.setText(getPresenter().getUser().getFUNAME());
        adapter = new CommonSupervisionAdapter(null);
        commonRecycler.setHasFixedSize(true);
        commonRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        commonRecycler.setAdapter(adapter);
        loadPermissionList("54");
    }

    @Override
    public void onRefresh() {
        loadPermissionList("54");
        commonSwipeLayout.post(() -> commonSwipeLayout.setRefreshing(false));
    }

    public void loadPermissionList(String parent) {
        list = new ArrayList<>();
        List<UploadNoDealWithBean.DataListBean> upload = new ArrayList<>();
        List<HomeBean.DataList> list1 = (List<HomeBean.DataList>) SPUtils.getInstance().getObjectData(Constance.DATA_PERMISSION);

        for (int i = 0; i < list1.size(); i++) {
            if (parent.equals(list1.get(i).getFmParent())) {
                list.add(list1.get(i));
            }
            if (list1.get(i).getFSm1().equals("58")) {
                UploadNoDealWithBean.DataListBean bean = new UploadNoDealWithBean.DataListBean();
                bean.setQxid(Integer.parseInt(list1.get(i).getFSm1()));
                bean.setTableName(list1.get(i).getFSm3());
                upload.add(bean);
            }
        }

        adapter.setNewData(list);
        UploadNoDealWithBean uploadNoDealWithBean = new UploadNoDealWithBean();
        uploadNoDealWithBean.setDataList(upload);
        getPresenter().withoutDealMsg(uploadNoDealWithBean);
    }


    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {
        for (int i = 0; i < dataLists.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getFSm1().equals(dataLists.get(i).getQxid())) {
//                    list.get(j).setNumber(dataLists.get(i).getSum());
                    adapter.getData().get(j).setNumber(dataLists.get(i).getSum());
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(RefreshBus bean) {
        if (bean.getNumber() == 4) {
            loadPermissionList("54");
        }
    }

    @Override
    public void bindListener() {
        commonSwipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(commonRecycler).setOnItemClickListener((recyclerView, position, v) -> {
            Intent intent = new Intent(this, CommonSupervisionDetilActivity.class);
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "58":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_1);
                    break;
                case "59":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_2);
                    break;
                case "60":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_3);
                    break;
                case "61":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_4);
                    break;
                case "62":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_5);
                    break;
                case "63":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_6);
                    break;
                case "64":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_7);
                    break;
                case "65":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_8);
                    break;
                case "66":
                    intent.putExtra("type", CommonSupervisionDetilActivity.TABLE_9);
//                    intent = new Intent(this, DoubleRandomOnePublicActivity.class);
                    break;
                case "73":
                    intent = new Intent(this, DoubleRandomOnePublicActivity.class);
                    break;
            }
            startActivity(intent);
        });
    }
}
