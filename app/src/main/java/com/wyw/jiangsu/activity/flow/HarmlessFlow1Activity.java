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
import com.wyw.jiangsu.activity.WHHChuKuActivity;
import com.wyw.jiangsu.activity.chaxun.VeterinarianDeclareActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessApplyActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessApplyMessageAcceptListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessApplyReplaceActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessMessageAcceptCollectionListActivity;
import com.wyw.jiangsu.activity.wuhaihua.HarmlessSupervisionVeterinatiansListActivity;
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

import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * 无害化处理流程总览
 */
public class HarmlessFlow1Activity extends MVPActivity<HarmlessFlowActivityPresenter> implements IHarmlessFlowActivity, SwipeRefreshLayout.OnRefreshListener {
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
    }

    @Override
    protected HarmlessFlowActivityPresenter createPresenter() {
        return new HarmlessFlowActivityPresenter(this);
    }

    /**
     * parent 9
     */
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
        setTitle("无害化处理");
        tvYonghuMingzi.setVisibility(View.VISIBLE);
        tvYonghuMingzi.setText(getPresenter().getUser().getFUNAME());
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("9");

    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "10":
                    //养殖场申报
                    startActivity(new Intent(this, HarmlessApplyActivity.class));
                    break;
                case "11":
                    //代申报
                    startActivity(new Intent(this, HarmlessApplyReplaceActivity.class));
                    break;
                case "12":
                    //官方兽医确认
                    startActivity(new Intent(this, HarmlessApplyMessageAcceptListActivity.class));//1
                    break;
                case "13":
                    //现场勘验
                    startActivity(new Intent(this, HarmlessSupervisionVeterinatiansListActivity.class));
                    break;
                case "14":
                    //收集点
                    startActivity(new Intent(this, HarmlessMessageAcceptCollectionListActivity.class));//2
                    break;
                case "52":
                    //出库管理
                    startActivity(new Intent(this, WHHChuKuActivity.class));
                    break;
                case "28":
                    //无害化处理厂
                    startActivity(new Intent(this, HarmlessFlow2Activity.class));
                    break;
                case "57":
                    //乡镇站代申报确认
                    startActivity(new Intent(this, VeterinarianDeclareActivity.class));
                    break;
            }
        });
    }

    @Override
    public void onRefresh() {
        loadPermissionList("9");
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
            loadPermissionList("9");
        }
    }
}
