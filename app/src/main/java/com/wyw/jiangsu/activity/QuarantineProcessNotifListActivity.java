package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.QuarantineProcessNotifListAdapter;
import com.wyw.jiangsu.bean.QuarantineProcessNotifListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IQuarantineProcessNotifListActivity;
import com.wyw.jiangsu.presenter.QuarantineProcessNotifListActivityPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.List;

import butterknife.BindView;

/**
 * 检查处理通知单列表
 */
public class QuarantineProcessNotifListActivity extends MVPActivity<QuarantineProcessNotifListActivityPresenter> implements IQuarantineProcessNotifListActivity, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.springView)
    SpringView springView;

    QuarantineProcessNotifListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
    }

    @Override
    protected QuarantineProcessNotifListActivityPresenter createPresenter() {
        return new QuarantineProcessNotifListActivityPresenter(this);
    }

    @Override
    public void bindData() {
//        getAdd().setVisibility(View.VISIBLE);
        setTitle("检疫申报通知单列表");
        adapter = new QuarantineProcessNotifListAdapter(null);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        recycler.setItemAnimator(new DefaultItemAnimator());
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
//        springView.setHeader(new AliHeader(this,R.drawable.ali,true));   //参数为：logo图片资源，是否显示文字
//        springView.setFooter(new AliFooter(this,false));
        springView.setHeader(new RotationHeader(this));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(this));
        springView.setListener(this);
        getPresenter().refresh();
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            Intent intent = new Intent(QuarantineProcessNotifListActivity.this, QuarantineProcessNotifActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constance.ACTIVITY_DATA, adapter.getItem(position));
            intent.putExtras(bundle);
//            startActivity(intent);
            startActivityForResult(intent, Constance.ACTIVITY_CODE);
        });
    }


    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().refresh();
                springView.callFresh();
            }
        }, 500);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                QuarantineProcessNotifListBean.DataListBean bean = (QuarantineProcessNotifListBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                manualRefresh();
            }
        }
    }

    @Override
    public void refresh(List<QuarantineProcessNotifListBean.DataListBean> dataListBean) {
        if (dataListBean != null) {
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListBean);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.VISIBLE);
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<QuarantineProcessNotifListBean.DataListBean> dataListBean) {
        if (dataListBean != null) {
            adapter.addData(dataListBean);
        } else {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void uploadsuccess() {

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
