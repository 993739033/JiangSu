package com.wyw.jiangsu.activity.flow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.AnimApplyListActivity;
import com.wyw.jiangsu.activity.AnimBlistActivity;
import com.wyw.jiangsu.activity.AnimProcessListActivity;
import com.wyw.jiangsu.activity.AnimWorkRecordActivity;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.ProductAListActivity;
import com.wyw.jiangsu.activity.ProductBListActivity;
import com.wyw.jiangsu.activity.QuarantineProcessNotifListActivity;
import com.wyw.jiangsu.activity.wuhaihua.AnimAListActivity;
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
 * 产地检疫的浏览
 */
public class QuarantineFlowActivity extends MVPActivity<HarmlessFlowActivityPresenter> implements IHarmlessFlowActivity, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
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

    /**
     * parent 39
     */
    public void loadPermissionList(String parent) {
         list = new ArrayList<>();
        List<HomeBean.DataList> list1 = (List<HomeBean.DataList>) SPUtils.getInstance().getObjectData(Constance.DATA_PERMISSION);
        List<UploadNoDealWithBean.DataListBean>upload = new ArrayList<>();
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
        setTitle("产地检疫");
        tvYonghuMingzi.setVisibility(View.VISIBLE);
        tvYonghuMingzi.setText(getPresenter().getUser().getFUNAME());
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("39");


    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "1":
                    //产地检疫申报单
                    startActivity(new Intent(getContext(), AnimApplyListActivity.class));
                    break;
                case "2":
                    //动物检疫申报单处理
                    startActivity(new Intent(getContext(), AnimProcessListActivity.class));
                    break;
                case "3":
                    //工作记录单  AnimWorkRecordActivity
                    startActivity(new Intent(getContext(), AnimWorkRecordActivity.class));
                    break;
                case "4":
                    //动物A检疫证明
                    startActivity(new Intent(getContext(), AnimAListActivity.class));
                    break;
                case "5":
                    //动物B检疫证明
                    startActivity(new Intent(getContext(), AnimBlistActivity.class));
                    break;
                case "6":
                    //产品A检疫证明
                    startActivity(new Intent(getContext(), ProductAListActivity.class));
                    break;
                case "7":
                    //产品B检疫证明
                    startActivity(new Intent(getContext(), ProductBListActivity.class));
                    break;
                case "8":
                    //检疫处理通知单
                    startActivity(new Intent(getContext(), QuarantineProcessNotifListActivity.class));

                    break;
            }
        });
    }

    @Override
    public void onRefresh() {
        loadPermissionList("39");
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
    }

    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {
        for (int i=0;i<dataLists.size();i++){
            for (int j=0;j<list.size();j++) {
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
        if (bean.getNumber()==4) {
            loadPermissionList("39");
        }
    }
}
