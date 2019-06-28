package com.wyw.jiangsu.fragment;

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
import com.wyw.jiangsu.activity.AnimRuZhongRecordActivity;
import com.wyw.jiangsu.adapter.AnimRuZhongYongRecordBeanAdapter;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimRuZhongYongRecordFragment;
import com.wyw.jiangsu.presenter.AnimRuZhongYongRecordFragmentPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wyw on 2017/2/9.
 * 乳用、种用产地检疫申报单
 */


public class AnimRuZhongYongRecordFragment extends MVPFragment<AnimRuZhongYongRecordFragmentPresenter> implements IAnimRuZhongYongRecordFragment, SpringView.OnFreshListener {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    protected boolean isPrepared;

    AnimRuZhongYongRecordBeanAdapter adapter;

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
        Log.e("AnimRuZhongYongReco", "lazyload");
    }

    @Override
    protected AnimRuZhongYongRecordFragmentPresenter createPresenter() {
        return new AnimRuZhongYongRecordFragmentPresenter(this);
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
//           startActivityForResult(new Intent(getContext(),AnimProcessListDetilActivity.class), Activity.RESULT_FIRST_USER);
            Intent intent = new Intent(getActivity(), AnimRuZhongRecordActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constance.ACTIVITY_DATA, (Serializable) adapter.getItem(position));
            intent.putExtras(bundle);
//            startActivity(intent);
            startActivityForResult(intent, Constance.ACTIVITY_CODE);
        });
    }

    @Override
    protected void bindData() {

        adapter = new AnimRuZhongYongRecordBeanAdapter(null);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        recycler.setItemAnimator(new DefaultItemAnimator());
        springView.setType(SpringView.Type.FOLLOW);
        springView.setGive(SpringView.Give.BOTH);
//        springView.setHeader(new AliHeader(this,R.drawable.ali,true));   //参数为：logo图片资源，是否显示文字
//        springView.setFooter(new AliFooter(this,false));
        springView.setHeader(new RotationHeader(getContext()));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new RotationFooter(getContext()));
        springView.setListener(this);
        //进来时，先刷新
        getPresenter().refresh();
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

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                AnimRuZhongYongRecordListBean.DataListBean bean = (AnimRuZhongYongRecordListBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                manualRefresh();
            }
        }
    }

    @Override
    public void refresh(List<AnimRuZhongYongRecordListBean.DataListBean> dataListBean) {
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
    public void loadMore(List<AnimRuZhongYongRecordListBean.DataListBean> dataListBean) {
        if (dataListBean == null) {
            Toast.makeText(MyApplication.getContext(), "没有数据", Toast.LENGTH_SHORT).show();
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
