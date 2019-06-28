package com.wyw.jiangsu.activity.chaxun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;

import java.util.ArrayList;
import java.util.List;

/**
 * 三个处理单的adapter
 * Created by Administrator on 2017/3/27.
 */

public class LabelCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View headerView;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<AnimZhongDanRedordBean.DataListBean> listBean = new ArrayList<>();

    public LabelCAdapter(Context context, List<AnimZhongDanRedordBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.listBean = list;
        mLayoutInflater = LayoutInflater.from(context);
    }


    public void clear() {
        listBean.clear();
        notifyDataSetChanged();
    }

    public AnimZhongDanRedordBean.DataListBean getItem(int position) {
        return listBean.get(position - 1);
    }

    public void setmDatas(List<AnimZhongDanRedordBean.DataListBean> list) {
        if (this.listBean == null) {
            this.listBean = list;
        } else {
            this.listBean.clear();
            this.listBean.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<AnimZhongDanRedordBean.DataListBean> list) {
        if (this.listBean == null) {
            this.listBean = list;
        } else {
            this.listBean.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<AnimZhongDanRedordBean.DataListBean> getDates() {
        return listBean;
    }

    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new HeadViewHolder(headerView);
        } else {
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.labelc_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeType.setText(listBean.get(position - 1).getFSm1()); //状态
            ((ListviewViewHolder) holder).tvJiludanBianhao.setText(listBean.get(position - 1).getRecordNo());
            ((ListviewViewHolder) holder).tvJianyiTime.setText(listBean.get(position - 1).getQuarantinetime());
            ((ListviewViewHolder) holder).tvHouzhuName.setText(listBean.get(position - 1).getShippername());
            ((ListviewViewHolder) holder).tvAnimalType.setText(listBean.get(position - 1).getAnimalsort());
            ((ListviewViewHolder) holder).tvQuantityDanwei.setText(listBean.get(position - 1).getAnimalnum() + listBean.get(position - 1).getQCPDanWei());
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
        if (listBean == null || listBean.size() == 0) {
            return 1;
        }
        return listBean.size() + 1;
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeType;
        private TextView tvJiludanBianhao;
        private TextView tvJianyiTime;
        private TextView tvHouzhuName;
        private TextView tvAnimalType;
        private TextView tvQuantityDanwei;

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseec_type);
            tvJiludanBianhao = (TextView) view.findViewById(R.id.tv_jiludanc_bianhao);
            tvJianyiTime = (TextView) view.findViewById(R.id.tv_jianyic_time);
            tvHouzhuName = (TextView) view.findViewById(R.id.tv_huozhuc_name);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animalc_type);
            tvQuantityDanwei = (TextView) view.findViewById(R.id.tv_quantityc_danwei);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }

}
