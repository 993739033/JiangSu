package com.wyw.jiangsu.activity.zhifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.HomeBeanAdapeter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bean.UploadNoDealWithBean;
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
 * 查询模块执法总览
 */
public class QueryLawActivity extends MVPActivity<HarmlessFlowActivityPresenter> implements IHarmlessFlowActivity, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private HomeBeanAdapeter adapter;
    List<HomeBean.DataList> list;

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

    public void loadPermissionList(String parent) {
        list = new ArrayList<>();
        List<UploadNoDealWithBean.DataListBean> upload = new ArrayList<>();
        List<HomeBean.DataList> list1 = (List<HomeBean.DataList>) SPUtils.getInstance().getObjectData(Constance.DATA_PERMISSION);
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
        getPresenter().withoutDealMsg(uploadNoDealWithBean);
    }

    @Override
    public void bindData() {
        setTitle("执法办案");
        tvYonghuMingzi.setVisibility(View.VISIBLE);
        tvYonghuMingzi.setText(getPresenter().getUser().getFUNAME());
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("49");
    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            Intent intent = new Intent(this,CaseRegisterActivity.class);
            switch (data.getFsm1()) {
                //添加案件登记
                case "50":
                    intent.putExtra(Constance.ZHIFAQUERY_TABLE,CaseRegisterActivity.table_1);
                    break;
                // 添加出具当场处罚决定书
                case "51":
                    intent.putExtra(Constance.ZHIFAQUERY_TABLE,CaseRegisterActivity.table_4);
                    break;
                //现场检查(勘验)笔录
                case "67":
                    intent.putExtra(Constance.ZHIFAQUERY_TABLE,CaseRegisterActivity.table_2);
                    break;
                //询问笔录
                case "68":
                    intent.putExtra(Constance.ZHIFAQUERY_TABLE,CaseRegisterActivity.table_3);
                    break;
            }
            startActivity(intent);
        });
    }

    //要改成和点击事件哪里数值一样
    @Override
    public void onRefresh() {
        loadPermissionList("49");
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
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
            loadPermissionList("49");
        }
    }
}
