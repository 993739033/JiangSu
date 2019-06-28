package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.JianyiChuliTongzhidanListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class JianyiTongzhidanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    View headerView;
    private List<JianyiChuliTongzhidanListBean.DataListBean> list;
    private LayoutInflater mLayoutInflater;

    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public JianyiTongzhidanAdapter(Context context, List<JianyiChuliTongzhidanListBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }


    public void addAll(List<JianyiChuliTongzhidanListBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setmDatas(List<JianyiChuliTongzhidanListBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }


    public List<JianyiChuliTongzhidanListBean.DataListBean> getDates() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new HeadViewHolder(headerView);
        } else {
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.jianyichuli_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeType.setText(list.get(position - 1).getIsPrant());  //申报状态
            ((ListviewViewHolder) holder).tvBeichuliChangjia.setText(list.get(position - 1).getNDanWei());//被处理厂家
            ((ListviewViewHolder) holder).tvJianyiBianhao.setText(list.get(position - 1).getNNumber());  //编号
            ((ListviewViewHolder) holder).tvHouzhuName.setText(list.get(position - 1).getFSuserName());//官方兽医
            ((ListviewViewHolder) holder).tvAnimalType.setText(list.get(position - 1).getNName());//动物种类
            ((ListviewViewHolder) holder).tvQuantityDanwei.setText(list.get(position - 1).getFChuliNum() + list.get(position - 1).getFChuliDanwei());//danwei
        }
    }


    //设置ITEM类型，可以自由发挥，这里设置item 0单数显示head item 其他显示listview item
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_HEAD.ordinal();
        } else {
            return ITEM_TYPE.ITEM_LISTVIEM.ordinal();
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

        private TextView tvHarmlseeType;
        private TextView tvBeichuliChangjia;
        private TextView tvJianyiBianhao;
        private TextView tvHouzhuName;
        private TextView tvAnimalType;
        private TextView tvQuantityDanwei;

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseeaa_typee);
            tvBeichuliChangjia = (TextView) view.findViewById(R.id.tv_beichuli_changjia);
            tvJianyiBianhao = (TextView) view.findViewById(R.id.tv_chuli_bianhao);
            tvHouzhuName = (TextView) view.findViewById(R.id.tv_guangfang_name);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animalaa_typee);
            tvQuantityDanwei = (TextView) view.findViewById(R.id.tv_quantityaa_danweie);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }
}
