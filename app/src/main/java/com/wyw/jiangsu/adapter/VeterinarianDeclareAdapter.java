package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj004 on 2017/7/27.
 */

public class VeterinarianDeclareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WuHaiHuaCXbean.DataListBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;


    public VeterinarianDeclareAdapter(Context context, List<WuHaiHuaCXbean.DataListBean> listBeen) {
        this.context = context;
        this.list = listBeen;
        mLayoutInflater = LayoutInflater.from(context);

    }

    public List<WuHaiHuaCXbean.DataListBean> getDates() {
        return list;
    }

    public void setmDatas(List<WuHaiHuaCXbean.DataListBean> mlist) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list.clear();
            this.list.addAll(mlist);
        }

        notifyDataSetChanged();
    }

    public void addAll(List<WuHaiHuaCXbean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new VeterinarianDeclareAdapter.ListviewViewHolder(mLayoutInflater.inflate(R.layout.item_query_listviewholder_wuhaihua, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VeterinarianDeclareAdapter.ListviewViewHolder) {
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvChuzhuName.setText(list.get(position ).getFxzxm());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvAdress.setText(list.get(position ).getFxxdz());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvApplyTime.setText(list.get(position ).getFsbrq());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvChuliWay.setText(list.get(position ).getFclfs());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvXiancunlanliang.setText(list.get(position ).getFxcll());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvYanghzicType.setText(list.get(position ).getFyzclx());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvErbiaohao.setText(list.get(position ).getQDWErBiaoHao());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvYikatonghao.setText(list.get(position ).getFykth());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvShenfenzhenghao.setText(list.get(position ).getFsfzh());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvSiWanglv.setText(list.get(position ).getSwl());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).tvXiangjuJuli.setText(list.get(position).getJl());
            ((VeterinarianDeclareAdapter.ListviewViewHolder) holder).name.setText(list.get(position).getXm());
        }
    }

    @Override
    public int getItemCount() {
        Log.v("mnkj","size" + list.size());
        return list.size();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvChuzhuName;
        private TextView tvAdress;
        private TextView tvYanghzicType;
        private TextView tvXiancunlanliang;
        private TextView tvApplyTime;
        private TextView tvChuliWay;
        private TextView tvErbiaohao;
        private TextView tvShenfenzhenghao;
        private TextView tvYikatonghao;
        private TextView tvSiWanglv;
        private TextView tvXiangjuJuli;
        private TextView name;

        private ListviewViewHolder(View view) {
            super(view);
            tvChuzhuName = (TextView) view.findViewById(R.id.tv_chuzhu_name);
            tvAdress = (TextView) view.findViewById(R.id.tv_adress);
            tvYanghzicType = (TextView) view.findViewById(R.id.tv_yanghzic_type);
            tvXiancunlanliang = (TextView) view.findViewById(R.id.tv_xiancunlanliang);
            tvApplyTime = (TextView) view.findViewById(R.id.tv_apply_time);
            tvChuliWay = (TextView) view.findViewById(R.id.tv_chuli_way);
            tvErbiaohao = (TextView) view.findViewById(R.id.tv_erbiaohao);
            tvShenfenzhenghao = (TextView) view.findViewById(R.id.tv_shenfenzhenghao);
            tvYikatonghao = (TextView) view.findViewById(R.id.tv_yikatonghao);
            tvSiWanglv = (TextView) view.findViewById(R.id.tv_siwanglv);
            tvXiangjuJuli = (TextView) view.findViewById(R.id.tv_xiangju_juli);
            name = (TextView) view.findViewById(R.id.name);
        }
    }

}
