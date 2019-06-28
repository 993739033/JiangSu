package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2017/6/13.
 */

public class WuHaiHua1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<CollectionTransportDealActivityBean.DataBean > list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;
    View headerView;

    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }
    public WuHaiHua1Adapter(Context context, List<CollectionTransportDealActivityBean.DataBean > list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void setmDatas(List<CollectionTransportDealActivityBean.DataBean > list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == WuHaiHua1Adapter.ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new WuHaiHua1Adapter.HeadViewHolder(headerView);
        } else {
            return new WuHaiHua1Adapter.ListviewViewHolder(mLayoutInflater.inflate(R.layout.wuhaihua1_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDongWu;
        private TextView tvSiWangShu;
        private TextView tvZongLiang;

        private ListviewViewHolder(View view) {
            super(view);
            tvDongWu = (TextView) view.findViewById(R.id.tv_dongwu);
            tvSiWangShu = (TextView) view.findViewById(R.id.tv_siwangshu);
            tvZongLiang = (TextView) view.findViewById(R.id.tv_zhongliang);
        }
    }
    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }
}
