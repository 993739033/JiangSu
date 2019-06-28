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

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.AnimZhongDanRecordActivity;
import com.wyw.jiangsu.adapter.AnimZhongDanRecordBeanAdapter;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAnimZhongDanRecordFragment;
import com.wyw.jiangsu.presenter.AnimZhongDanRecordFragmentPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;
import com.wyw.jiangsu.view.springview.container.RotationFooter;
import com.wyw.jiangsu.view.springview.container.RotationHeader;
import com.wyw.jiangsu.view.springview.widget.SpringView;

import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wyw on 2017/2/9.
 * 蛋、胚胎、精液检疫工作记录单
 */

public class AnimZhongDanRecordFragment extends MVPFragment<AnimZhongDanRecordFragmentPresenter> implements IAnimZhongDanRecordFragment, SpringView.OnFreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.view_empty)
    RelativeLayout viewEmpty;
    @BindView(R.id.springView)
    SpringView springView;

    AnimZhongDanRecordBeanAdapter adapter;
    protected boolean isPrepared;

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
        Log.e("AnimZhongDanRecord", "lazyload");
    }


    @Override
    protected int getResId() {
        return R.layout.layout_recycler2;
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
//            startActivityForResult(new Intent(getContext(),AnimProcessListDetilActivity.class), Activity.RESULT_FIRST_USER);
            Intent intent = new Intent(getActivity(), AnimZhongDanRecordActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constance.ACTIVITY_DATA, adapter.getItem(position));
            intent.putExtras(bundle);
//            startActivity(intent);
            startActivityForResult(intent, Constance.ACTIVITY_CODE);
        });
    }

    @Override
    protected void bindData() {
        adapter = new AnimZhongDanRecordBeanAdapter(null);
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
        getPresenter().refresh();
    }

    @Override
    protected AnimZhongDanRecordFragmentPresenter createPresenter() {
        return new AnimZhongDanRecordFragmentPresenter(this);
    }

    /**
     * 手动刷新
     */
    public void manualRefresh() {
        viewEmpty.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                springView.callFresh();
                getPresenter().refresh();
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
        presenter.loadMore();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                AnimZhongDanRedordBeangai.DataListBean bean = (AnimZhongDanRedordBeangai.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                manualRefresh();
            }
        }
    }

    @Override
    public void refresh(List<AnimZhongDanRedordBeangai.DataListBean> dataListBeen) {
        if (dataListBeen != null) {
            viewEmpty.setVisibility(View.GONE);
            adapter.setNewData(dataListBeen);
        } else {
            viewEmpty.setVisibility(View.VISIBLE);
            adapter.clear();
            Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore(List<AnimZhongDanRedordBeangai.DataListBean> dataListBeen) {
        if (dataListBeen != null) {
            adapter.addData(dataListBeen);
        } else {
            Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
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
