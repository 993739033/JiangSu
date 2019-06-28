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
import com.wyw.jiangsu.adapter.HarmlessHomeVeterinatiansListAdapter;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessHomeVeterinatiansListActivity;
import com.wyw.jiangsu.presenter.HarmlessHomeVeterinatiansListActivityPresenter;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 驻场兽医列表
 * 上个页面 暂存点处收集确认 {@link HarmlessMessageConfirmStorageActivity}
 * 下一个页面 驻场兽医确认 {@link HarmlessHomeVeterinarianConfirmActivity}
 */
public class HarmlessHomeVeterinatiansListActivity extends MVPActivity<HarmlessHomeVeterinatiansListActivityPresenter>
        implements IHarmlessHomeVeterinatiansListActivity, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    private HarmlessHomeVeterinatiansListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
    }

    @Override
    protected HarmlessHomeVeterinatiansListActivityPresenter createPresenter() {
        return new HarmlessHomeVeterinatiansListActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("入场确认");
        adapter = new HarmlessHomeVeterinatiansListAdapter(new ArrayList<>(),(position, fstId,fclfs) -> {
            Intent intent = new Intent(this,HarmlessHomeVeterinarianConfirmActivity.class);
            intent.putExtra(Constance.ACTIVITY_DATA,fstId);
            intent.putExtra(Constance.ACTIVITY_TYPE,fclfs);
            startActivityForResult(intent,Constance.ACTIVITY_REQUEST_CODE);
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
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            manualRefresh();
        }
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
    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        getPresenter().refresh();
    }

    @Override
    public void onRefresh() {
        manualRefresh();
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
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void refreshNone() {
        adapter.clear();
        springView.onFinishFreshAndLoad();
    }
}
