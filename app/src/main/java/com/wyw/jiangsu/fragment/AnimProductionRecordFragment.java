package com.wyw.jiangsu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.AnimProducingAreaRecordActivity;
import com.wyw.jiangsu.adapter.AH_AnimalOriginListBeanAdapter;
import com.wyw.jiangsu.bean.QroducingAreaProcessListBean;
import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimProductionRecordFragment;
import com.wyw.jiangsu.presenter.AnimProductionRecordFragmentPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

import static android.R.attr.id;
import static android.app.Activity.RESULT_OK;
import static com.wyw.jiangsu.interfac.Constance.ACTIVITY_CODE;

/**
 * Created by wyw on 2017/2/9.
 * 动物产地工作记录单
 */

public class AnimProductionRecordFragment extends MVPFragment<AnimProductionRecordFragmentPresenter> implements IAnimProductionRecordFragment, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.springView)
    SpringView springView;

    AH_AnimalOriginListBeanAdapter adapter;
    protected boolean isPrepared;

    @Override
    protected int getResId() {
        return R.layout.layout_recycler2;
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || adapter != null) {
            return;
        }

        bindData();
        bindListener();
        Log.e("AnimProductionRecordFra", "lazyload");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void bindListener() {
        viewEmpty.setOnClickListener(v -> manualRefresh());
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
            Intent intent = new Intent(getActivity(), AnimProducingAreaRecordActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getItem(position));
            intent.putExtras(bundle);
            startActivityForResult(intent, Constance.ACTIVITY_CODE);
        });
    }

    @Override
    protected void bindData() {
        adapter = new AH_AnimalOriginListBeanAdapter(null);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        recycler.setItemAnimator(new DefaultItemAnimator());
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        //进来时，先刷新
        getPresenter().refresh();
    }

    @Override
    protected AnimProductionRecordFragmentPresenter createPresenter() {
        return new AnimProductionRecordFragmentPresenter(this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                QroducingAreaProcessListBean.DataListBean bean = (QroducingAreaProcessListBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                manualRefresh();
            }
        }
    }

    @Override
    public void refresh(List<QroducingAreaProcessListBean.DataListBean> dataListBean) {
        if (dataListBean != null) {
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListBean);
        } else {
            adapter.clear();
            viewEmpty.setVisibility(View.INVISIBLE);
            Toast.makeText(MyApplication.getContext(), "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<QroducingAreaProcessListBean.DataListBean> dataListBean) {
        if (dataListBean == null) {
            Toast.makeText(MyApplication.getContext(), "没有数1据", Toast.LENGTH_SHORT).show();
        } else {
            adapter.addData(dataListBean);
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
