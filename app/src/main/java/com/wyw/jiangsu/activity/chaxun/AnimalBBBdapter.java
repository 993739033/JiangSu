package com.wyw.jiangsu.activity.chaxun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimBQueryListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 动物B的adapter
 * Created by Administrator on 2017/3/27.
 */

public class AnimalBBBdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AnimBQueryListBean.DataListEntity> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;
    View headerView;


    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    public AnimalBBBdapter(Context context, List<AnimBQueryListBean.DataListEntity> list,
                           View headerView) {
        this.headerView = headerView;
        this.context = context;
        this.list = list;

        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }


    public List<AnimBQueryListBean.DataListEntity> getDates() {
        return list;
    }

    public void setmDatas(List<AnimBQueryListBean.DataListEntity> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<AnimBQueryListBean.DataListEntity> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new HeadViewHolder(headerView);
        } else {
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.animalbbb_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {

        } else if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeType.setText(list.get(position - 1).getIsPrant());
            ((ListviewViewHolder) holder).tvQianfaDate.setText(list.get(position - 1).getDateQF());
            ((ListviewViewHolder) holder).tvJianyiBianhao.setText(list.get(position - 1).getAQNumber());
            ((ListviewViewHolder) holder).tvHouzhuName.setText(list.get(position - 1).getAQCargoOwner());
            ((ListviewViewHolder) holder).tvAnimalType.setText(list.get(position - 1).getAQXuZhong());
            ((ListviewViewHolder) holder).tvQuantityDanwei.setText(list.get(position - 1).getAQQuantity() + list.get(position - 1).getAQDanWei());
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
        private TextView tvQianfaDate;
        private TextView tvJianyiBianhao;
        private TextView tvHouzhuName;
        private TextView tvAnimalType;
        private TextView tvQuantityDanwei;

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseeab_type);
            tvQianfaDate = (TextView) view.findViewById(R.id.tv_qianfaab_date);
            tvJianyiBianhao = (TextView) view.findViewById(R.id.tv_jianyiab_bianhao);
            tvHouzhuName = (TextView) view.findViewById(R.id.tv_huozhuab_name);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animalab_type);
            tvQuantityDanwei = (TextView) view.findViewById(R.id.tv_quantityab_danwei);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }

}
