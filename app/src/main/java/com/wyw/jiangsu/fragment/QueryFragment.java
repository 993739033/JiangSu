package com.wyw.jiangsu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.chaxun.QuerySuperviseActivity;
import com.wyw.jiangsu.activity.flow.QueryHarmlessFlow1Activity;
import com.wyw.jiangsu.activity.flow.QueryQuarantineFlowActivity;
import com.wyw.jiangsu.activity.flow.QuerySlaughterQuarantineFlowActivity;
import com.wyw.jiangsu.activity.zhifa.QueryLawActivity;
import com.wyw.jiangsu.adapter.HomeBeanAdapeter;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.interfac.IQueryFragment;
import com.wyw.jiangsu.presenter.QueryFragmentPresenter;
import com.wyw.jiangsu.utils.RecyclerItemClickSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/20.
 */

public class QueryFragment extends MVPFragment<QueryFragmentPresenter> implements IQueryFragment, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private HomeBeanAdapeter adapter;
    protected boolean isPrepared;

    @Override
    protected QueryFragmentPresenter createPresenter() {
        return new QueryFragmentPresenter(this);
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
    }


    @Override
    protected int getResId() {
        return R.layout.fragment_query;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void bindListener() {
        swipeLayout.setOnRefreshListener(this);
        RecyclerItemClickSupport.addTo(recycler).setOnItemClickListener((recyclerView, position, v) -> {
                    HomeBean.DataList data = adapter.getData().get(position);
//                    Intent intent = new Intent(getActivity(), AnimAQueryActivity.class);
//                    startActivity(intent);
                    switch (data.getFsm1()) {
                        case "44":
                            //产地检疫
                            adapter.notifyDataSetChanged();
                            startActivity(new Intent(getActivity(), QueryQuarantineFlowActivity.class));
                            break;
                        case "45":
                            //屠宰检疫
                            adapter.notifyDataSetChanged();
                            startActivity(new Intent(getActivity(), QuerySlaughterQuarantineFlowActivity.class));
                            break;
                        case "46":
                            //无害化处理
                            adapter.notifyDataSetChanged();
                            startActivity(new Intent(getActivity(), QueryHarmlessFlow1Activity.class));
                            break;
                       case "69":
                            //日常监管
                           adapter.notifyDataSetChanged();
                            startActivity(new Intent(getActivity(), QuerySuperviseActivity.class));
                            break;
                         case "70":
                            //行政执法
                             adapter.notifyDataSetChanged();
                            startActivity(new Intent(getActivity(), QueryLawActivity.class));
                            break;
                    }
                }
        );
    }

    @Override
    protected void bindData() {
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new HomeBeanAdapeter(null);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        swipeLayout.post(() -> swipeLayout.setRefreshing(true));
        presenter.refresh();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void refreshComplete(List<HomeBean.DataList> dataLists) {
        swipeLayout.post(() -> swipeLayout.setRefreshing(false));
        List<HomeBean.DataList> list = new ArrayList<>();
        for (int i = 0; i < dataLists.size(); i++) {
//            if ("0".equals(dataLists.get(i).getFmParent()) && "1".equals(dataLists.get(i).getType())) {
//                list.add(dataLists.get(i));
//            }
            if ("47".equals(dataLists.get(i).getFmParent()) && "1".equals(dataLists.get(i).getType())) {
                list.add(dataLists.get(i));
            }
        }
        adapter.setNewData(list);
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }
}
