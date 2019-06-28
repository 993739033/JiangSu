package com.wyw.jiangsu.activity.wuhaihua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.HarmlessApplyMessageAcceptAdapter;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessApplyMessageAcceptListActivity;
import com.wyw.jiangsu.presenter.HarmlessApplyMessageAcceptListActivityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 病死动物死亡申报消息接收列表
 * 上个页面 申报  {@link HarmlessApplyActivity}
 * 下一个页面 监管兽医指派  {@link HarmlessAssignActivity}
 */
public class HarmlessApplyMessageAcceptListActivity extends MVPActivity<HarmlessApplyMessageAcceptListActivityPresenter>
        implements IHarmlessApplyMessageAcceptListActivity, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    private HarmlessApplyMessageAcceptAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
    }

    @Override
    protected HarmlessApplyMessageAcceptListActivityPresenter createPresenter() {
        return new HarmlessApplyMessageAcceptListActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("官方兽医确认");
        adapter = new HarmlessApplyMessageAcceptAdapter(new ArrayList<>(), (position, fstId, fclfs) -> {
            Intent intent = new Intent(this, HarmlessAssignActivity.class);
            intent.putExtra(Constance.ACTIVITY_DATA, fstId);
            startActivityForResult(intent, Constance.ACTIVITY_REQUEST_CODE);
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());

        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(this));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(this));
        manualRefresh();
    }

    @Override
    public void bindListener() {
        springView.setListener(this);
        viewEmpty.setOnClickListener(v -> manualRefresh());

//        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener(
//                (recyclerView, position, v) -> {
//                    Intent intent = new Intent(this, HarmlessAssignActivity.class);
//                    intent
//                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            manualRefresh();
        }
    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        getPresenter().refresh();
    }

    @Override
    public void onRefresh() {
//        manualRefresh();
        springView.onFinishFreshAndLoad();
        getPresenter().refresh();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().loadmore();
//        getPresenter().loadmore();
    }

    @Override
    public void refresh(List<HarmlessListBean.DataEntity> entity) {
        if (entity != null) {
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(entity);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.VISIBLE);
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<HarmlessListBean.DataEntity> entity) {
        if (entity != null) {
            adapter.addData(entity);
        } else {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onError() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
    }
}
