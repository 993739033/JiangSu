package com.wyw.jiangsu.activity.zhifa;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.model.HeadSearchView;
import com.wyw.jiangsu.adapter.AnjiandengjiActivityAdapter;
import com.wyw.jiangsu.bean.AnjiandengjiActivityBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnjiandengjiActivity;
import com.wyw.jiangsu.presenter.AnjiandengjiActivityPresenter;
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
 * 案件登记列表
 */
public class AnjiandengjiActivity extends MVPActivity<AnjiandengjiActivityPresenter> implements IAnjiandengjiActivity, SpringView.OnFreshListener {

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
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;

    AnjiandengjiActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_recycle);
        ButterKnife.bind(this);
    }

    @Override
    protected AnjiandengjiActivityPresenter createPresenter() {
        return new AnjiandengjiActivityPresenter(this);
    }

    @Override
    public void bindData() {
        tvTitle.setText("案件登记");
        rvList.setHasFixedSize(true);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
        rvList.setItemAnimator(new DefaultItemAnimator());

        getPresenter().getFarmDeclare();

        adapter = new AnjiandengjiActivityAdapter(new ArrayList<>());
        rvList.setAdapter(adapter);
    }

    @Override
    public void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(rvList).setOnItemClickListener((recyclerView, position, v) -> {
            List<AnjiandengjiActivityBean.DataListBean> list = new ArrayList<AnjiandengjiActivityBean.DataListBean>();
            for (int i = 0; i < adapter.getData().size(); i++) {
                list.add(adapter.getData().get(i));
            }

            Intent intent = getIntent();
            AnjiandengjiActivityBean.DataListBean data = list.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constance.ACTIVITY_DATA, data);
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);
            finish();

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
                getPresenter().getFarmDeclare();
                springView.callFresh();
            }
        }, 500);
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

    @Override
    public void onRefresh() {
        getPresenter().getFarmDeclare();
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {
        springView.onFinishFreshAndLoad();

    }

    @Override
    public void setData(List<AnjiandengjiActivityBean.DataListBean> dataListEntity) {

        if (dataListEntity.size() == 0) {
            viewEmpty.setVisibility(View.VISIBLE);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListEntity);
        }
    }

}
