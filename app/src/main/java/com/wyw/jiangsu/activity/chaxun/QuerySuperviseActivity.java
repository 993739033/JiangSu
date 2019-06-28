package com.wyw.jiangsu.activity.chaxun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.chaxun.AnimalTreatActivity;
import com.wyw.jiangsu.activity.chaxun.BreedingActivity;
import com.wyw.jiangsu.activity.chaxun.HarmlesshandleActivity;
import com.wyw.jiangsu.activity.chaxun.MedicineHandleActivity;
import com.wyw.jiangsu.activity.chaxun.MedicineProduceActivity;
import com.wyw.jiangsu.activity.chaxun.MilkStationActivity;
import com.wyw.jiangsu.activity.chaxun.PartPlaceActivity;
import com.wyw.jiangsu.activity.chaxun.SiliaoProduceActivity;
import com.wyw.jiangsu.activity.chaxun.SlaughterActivity;
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

import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * 各种上传
 */
public class QuerySuperviseActivity extends MVPActivity<CommonSupervisionListActivityPresenter> implements ICommonSupervisionListActivity, SwipeRefreshLayout.OnRefreshListener {
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
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "58":
                    //養殖所
                    startActivity(new Intent(getContext(), BreedingActivity.class));
                    break;
                case "59":
                    //屠宰场
                    startActivity(new Intent(getContext(), SlaughterActivity.class));
                    break;
                case "60":
                    //动物诊疗机构
                    startActivity(new Intent(getContext(), AnimalTreatActivity.class));
                    break;
                case "61":
                    //无害化处理场所
                    startActivity(new Intent(getContext(), HarmlesshandleActivity.class));
                    break;
                case "62":
                    //隔离场所
                    startActivity(new Intent(getContext(), PartPlaceActivity.class));
                    break;
                case "63":
                    //饲料生产企业
                    startActivity(new Intent(getContext(), SiliaoProduceActivity.class));
                    break;
                case "64":
                    //兽药生产企业
                    startActivity(new Intent(getContext(), MedicineProduceActivity.class));
                    break;
                case "65":
                    //兽药经营企业
                    startActivity(new Intent(getContext(), MedicineHandleActivity.class));
                    break;
                case "66":
                    //生鲜乳收购站
                    startActivity(new Intent(getContext(), MilkStationActivity.class));
                    break;
            }
        });
    }
}

