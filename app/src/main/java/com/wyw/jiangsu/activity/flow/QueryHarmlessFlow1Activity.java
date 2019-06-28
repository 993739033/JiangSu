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
import com.wyw.jiangsu.activity.chaxun.AgencyDeclareActivity;
import com.wyw.jiangsu.activity.chaxun.CollectionPointActivity;
import com.wyw.jiangsu.activity.chaxun.DeclareNewsAcceptActivity;
import com.wyw.jiangsu.activity.chaxun.StorehouseOutQueryActivity;
import com.wyw.jiangsu.activity.chaxun.VerificationLocaActivity;
import com.wyw.jiangsu.activity.wuhaihua.ChaxunJinduActivity;
import com.wyw.jiangsu.adapter.HomeBeanAdapeter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.fragment.FarmDeclareActivity;
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
 * 无害化处理流程总览
 */
public class QueryHarmlessFlow1Activity extends MVPActivity<HarmlessFlowActivityPresenter> implements IHarmlessFlowActivity, SwipeRefreshLayout.OnRefreshListener {
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

    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_apply_yangzhichang:
//                startActivity(new Intent(this, HarmlessApplyActivity.class));
//                break;
//            case R.id.bt_apply_replace:
//                startActivity(new Intent(this, HarmlessApplyReplaceActivity.class));
//                break;
//            case R.id.bt_apply_message_accept_list:
//                startActivity(new Intent(this, HarmlessApplyMessageAcceptListActivity.class));
//                break;
//            case R.id.bt_assign_list:
//                startActivity(new Intent(this, HarmlessSupervisionVeterinatiansListActivity.class));
//                break;
//            case R.id.bt_zancundian_jiehsou:
//                startActivity(new Intent(this, HarmlessMessageAcceptCollectionListActivity.class));
//                break;
//            case R.id.bt_chulichang:
//                startActivity(new Intent(this, HarmlessFlow2Activity.class));
//                break;
//        }
    }

    /**
     * parent 46
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
        setTitle("无害化处理");
        tvYonghuMingzi.setVisibility(View.VISIBLE);
        tvYonghuMingzi.setText(getPresenter().getUser().getFUNAME());
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        loadPermissionList("46");
    }

    @Override
    public void bindListener() {
        swipeLayout.setOnRefreshListener(this);

        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            HomeBean.DataList data = adapter.getData().get(position);
            switch (data.getFsm1()) {
                case "29":
                    //养殖场申报
                    startActivity(new Intent(this, FarmDeclareActivity.class));
                    break;
                case "30":
                    //代申报
                    startActivity(new Intent(this, AgencyDeclareActivity.class));
                    break;
                case "31":
                    //官方兽医确认
                    startActivity(new Intent(this, DeclareNewsAcceptActivity.class));
                    break;
                case "32":
                    //现场勘验
                    startActivity(new Intent(this, VerificationLocaActivity.class));
                    break;
                case "33":
                    //收集点
                    startActivity(new Intent(this, CollectionPointActivity.class));
                    break;
                case "37":
                    //无害化处理厂
                    startActivity(new Intent(this, QueryHarmlessFlow2Activity.class));
                    break;
                case "48":
                    //查询进度
                    startActivity(new Intent(this, ChaxunJinduActivity.class));
                    break;
                case "71":
                    //出库管理
                    startActivity(new Intent(this, StorehouseOutQueryActivity.class));

                    break;

/*              //TODO  case "49":
                //兽医人员代审报
                    startActivity(new Intent(this, VeterinarianDeclareActivity.class));
*/
            }
        });
    }

    @Override
    public void onRefresh() {
        loadPermissionList("46");
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
    }

    @Override
    public void refreshNoDeal(List<NoDealWithBean.DataListBean> dataLists) {

    }

    @Override
    public void onActivityResult(RefreshBus bean) {

    }
}
