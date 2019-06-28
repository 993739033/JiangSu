package com.wyw.jiangsu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.TiTleAdapter;
import com.wyw.jiangsu.bean.FadingFaguiBean;
import com.wyw.jiangsu.view.DivItemDecoration;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wyw on 2018/1/29.
 */

public class TiTleFragment extends Fragment implements TiTleAdapter.OnItemClick {

    @BindView(R.id.recycle)
    RecyclerView recycle;
    private List<FadingFaguiBean.DataListBean> mData;
    private List<FadingFaguiBean.DataListBean> mData1;
    Unbinder unbinder;
    private TiTleAdapter adapter;

    public TiTleFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = (List<FadingFaguiBean.DataListBean>) getArguments().getSerializable("DataList");
        mData1 = (List<FadingFaguiBean.DataListBean>) getArguments().getSerializable("DataList1");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        getFragmentManager().beginTransaction().replace(R.id.categorylayout, new ContentFragment()).commit();
        setData();
        return view;
    }

    private void setData() {
        adapter = new TiTleAdapter(getActivity(), mData);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.addItemDecoration(new DivItemDecoration(getActivity(), DivItemDecoration.VERTICAL_LIST));
        recycle.setAdapter(adapter);
        adapter.setOnItemClick(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Intent intent = new Intent();
                    intent.setAction("com.wyw.jiangsu.contentfragment");
                    if (adapter.getItem(0).getMenuName().equals("典型案卷")) {
                        intent.putExtra("component1", (Serializable) mData1);
                    } else {
                        intent.putExtra("component", adapter.getItem(0));
                    }
                    getActivity().sendBroadcast(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(int position,int distance) {
        Intent intent = new Intent();
        intent.setAction("com.wyw.jiangsu.contentfragment");
        if (adapter.getItem(position).getMenuName().equals("典型案卷")) {
            intent.putExtra("component1", (Serializable) mData1);
        } else {
            intent.putExtra("component", adapter.getItem(position));
        }
        getActivity().sendBroadcast(intent);
        scrollToCenter(distance);
    }

    private void scrollToCenter(int dis) {
        recycle.smoothScrollBy(0, dis+320, new AccelerateDecelerateInterpolator());
    }
}
