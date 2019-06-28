package com.wyw.jiangsu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.chaxun.DeclareNewsAcceptAdapter;
import com.wyw.jiangsu.bean.AgencyDeclareListBean;
import com.wyw.jiangsu.bean.DeclareNewstListBean;
import com.wyw.jiangsu.bean.StoreChukuBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class StorehouseOutQueryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View headerView;
    //    private List<DeclareNewstListBean.DataListBean> list = new ArrayList<>();
    private List<StoreChukuBean.DataListBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public StorehouseOutQueryAdapter(Context context, List<StoreChukuBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<StoreChukuBean.DataListBean> getDates() {
        return list;
    }

    public void setmDatas(List<StoreChukuBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<StoreChukuBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }


    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == com.wyw.jiangsu.activity.chaxun.DeclareNewsAcceptAdapter.ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new StorehouseOutQueryAdapter.HeadViewHolder(headerView);
        } else {
            return new StorehouseOutQueryAdapter.ListviewViewHolder(mLayoutInflater.inflate(R.layout.query_chuku, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StorehouseOutQueryAdapter.ListviewViewHolder) {
            ((StorehouseOutQueryAdapter.ListviewViewHolder) holder).tvLiushui.setText(list.get(position - 1).getFckdNumber());
            ((StorehouseOutQueryAdapter.ListviewViewHolder) holder).tvAdressName.setText(list.get(position - 1).getFZcdName());
            ((StorehouseOutQueryAdapter.ListviewViewHolder) holder).tvWhhFactory.setText(list.get(position - 1).getFWhhName());
            ((StorehouseOutQueryAdapter.ListviewViewHolder) holder).tvDateTime.setText(list.get(position - 1).getFckDate());
            ((StorehouseOutQueryAdapter.ListviewViewHolder) holder).tvDz.setText(list.get(position - 1).getFZcddz());
        }
    }

    //设置ITEM类型，可以自由发挥，这里设置item 0单数显示head item 其他显示listview item
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return com.wyw.jiangsu.activity.chaxun.DeclareNewsAcceptAdapter.ITEM_TYPE.ITEM_HEAD.ordinal();
        } else {
            return com.wyw.jiangsu.activity.chaxun.DeclareNewsAcceptAdapter.ITEM_TYPE.ITEM_LISTVIEM.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() == 0) {
            return 1;
        }
        return list.size() + 1;
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLiushui;
        private TextView tvAdressName;
        private TextView tvDz;
        private TextView tvDateTime;
        private TextView tvWhhFactory;

        private ListviewViewHolder(View view) {
            super(view);
            tvLiushui = (TextView) view.findViewById(R.id.tv_liushui);
            tvAdressName = (TextView) view.findViewById(R.id.tv_adressname);
            tvDz = (TextView) view.findViewById(R.id.tv_dz);
            tvDateTime = (TextView) view.findViewById(R.id.tv_datetime);
            tvWhhFactory = (TextView) view.findViewById(R.id.tv_whhfactory);
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }
}
