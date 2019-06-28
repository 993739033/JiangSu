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
import com.wyw.jiangsu.adapter.HarmlessStorageMessageAcceptAdapter;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessMessageAcceptStorageListActivity;
import com.wyw.jiangsu.presenter.HarmlessMessageAcceptStorageListActivityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 无害化处理中心 收集人员消息接收
 * 上个页面 暂存点收集人员确认 {@link HarmlessMessageConfirmCollectionActivity}
 * 下个页面 暂存点处收集确认 {@link HarmlessMessageConfirmStorageActivity}
 */
public class HarmlessMessageAcceptStorageListActivity extends MVPActivity<HarmlessMessageAcceptStorageListActivityPresenter>
        implements IHarmlessMessageAcceptStorageListActivity, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    HarmlessStorageMessageAcceptAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
    }

    @Override
    protected HarmlessMessageAcceptStorageListActivityPresenter createPresenter() {
        return new HarmlessMessageAcceptStorageListActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("收集运输");
        adapter = new HarmlessStorageMessageAcceptAdapter(new ArrayList<>(), (position, fstId, fclfs, fglid,num) -> {
            Intent intent = new Intent(this, HarmlessMessageConfirmStorageActivity.class);
            intent.putExtra(Constance.ACTIVITY_DATA, fstId);
            intent.putExtra(Constance.ACTIVITY_TYPE, fglid);
            intent.putExtra(Constance.ACTIVITY_NUM, num);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            manualRefresh();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindListener() {
        springView.setListener(this);
        viewEmpty.setOnClickListener(v -> manualRefresh());

//        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener(
//                (recyclerView, position, v) -> {});
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
        springView.onFinishFreshAndLoad();
        getPresenter().refresh();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();
        getPresenter().loadmore();
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
        if (springView==null) return;
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
    }
}
