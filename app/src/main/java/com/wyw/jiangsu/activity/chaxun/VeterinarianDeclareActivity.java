package com.wyw.jiangsu.activity.chaxun;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.VeterinarianDeclareAdapter;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IVeterinarianDeclareActivity;
import com.wyw.jiangsu.presenter.VeterinarianDeclareActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.MyApplication.getContext;

/**
 * 乡镇站代申报列表
 */

public class VeterinarianDeclareActivity extends MVPActivity<VeterinarianDeclareActivityPresenter> implements IVeterinarianDeclareActivity, SpringView.OnFreshListener {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.vd_rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.vd_springView)
    SpringView vdSpringView;
    @BindView(R.id.vd_view_empty)
    RelativeLayout vdViewEmpty;
    List<WuHaiHuaCXbean.DataListBean> listBeen;
    private VeterinarianDeclareAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recycle);
        ButterKnife.bind(this);
    }

    @Override
    protected VeterinarianDeclareActivityPresenter createPresenter() {
        return new VeterinarianDeclareActivityPresenter(this);
    }


    @Override
    public void addListData(List<WuHaiHuaCXbean.DataListBean> list) {
        adapter.addAll(list);
    }

    @Override
    public void setData(List<WuHaiHuaCXbean.DataListBean> list) {
        adapter.setmDatas(list);
    }

    @Override
    public void bindData() {
        tvTitle.setText("乡镇站代申报列表");
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listBeen = new ArrayList<>();
        vdSpringView.setType(SpringView.Type.FOLLOW);
        vdSpringView.setGive(SpringView.Give.BOTH);
        vdSpringView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        vdSpringView.setFooter(new RotationFooter(getContext()));
        vdSpringView.setListener(this);
        adapter = new VeterinarianDeclareAdapter(this, listBeen);
        mRecyclerView.setAdapter(adapter);
        getPresenter().getVeterinarianDeclare();

    }

    /**
     * 手动刷新
     */
    private void manualRefresh() {
        vdViewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                vdSpringView.callFresh();
            }
        }, 500);
    }

    @Override
    public void bindListener() {
        vdViewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(mRecyclerView).setOnItemClickListener((recyclerView, position, v) -> {
            Intent intent = new Intent(this, VeterinarianDeclareDetilActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getDates().get(position));
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().getVeterinarianDeclare();
    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {
        adapter.clear();
        vdSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onRefresh() {
        vdSpringView.onFinishFreshAndLoad();
        getPresenter().getVeterinarianDeclare();
    }

    @Override
    public void onLoadmore() {
        vdSpringView.onFinishFreshAndLoad();
        if (adapter.getDates().size() > 0) {
            getPresenter().loadData(adapter.getDates().get(adapter.getDates().size() - 1).getFStId());
        }
    }
}
