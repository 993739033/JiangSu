package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.bean.PartPlaceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 三个处理单的adapter
 * Created by Administrator on 2017/3/27.
 */

public class PartPlaceBeanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View headerView;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<BreedingRecordData.DataListBean> listBean = new ArrayList<>();

    public PartPlaceBeanAdapter(Context context, List<BreedingRecordData.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.listBean = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        listBean.clear();
        notifyDataSetChanged();
    }


    public void setNewData(List<BreedingRecordData.DataListBean> dataListBean) {
        this.listBean = dataListBean;
        notifyDataSetChanged();
    }

/*
    public void clear(List<AH_AnimalOrigin.DataListBean> dataListBean) {
        this.listBean.removeAll(dataListBean);
        notifyDataSetChanged();
    }
*/

    public BreedingRecordData.DataListBean getItem(int position) {
        return listBean.get(position - 1);
    }

    public List<BreedingRecordData.DataListBean> getDates() {
        return listBean;
    }


    public void setmDatas(List<BreedingRecordData.DataListBean> list) {
        if (this.listBean == null) {
            this.listBean = list;
        } else {
            this.listBean.clear();
            this.listBean.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<BreedingRecordData.DataListBean> list) {
        if (this.listBean == null) {
            this.listBean = list;
        } else {
            this.listBean.addAll(list);
        }
        notifyDataSetChanged();
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
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.partplace_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeType.setText(listBean.get(position - 1).getFName()); //状态
            ((ListviewViewHolder) holder).tvJiludanBianhao.setText(listBean.get(position - 1).getFCityAdd());
            ((ListviewViewHolder) holder).tvJianyiTime.setText(listBean.get(position - 1).getFCategory());
            ((ListviewViewHolder) holder).tvHouzhuName.setText(listBean.get(position - 1).getClsl());
            ((ListviewViewHolder) holder).tvAnimalType.setText(listBean.get(position - 1).getFLegalPerson());
            ((ListviewViewHolder) holder).tvQuantityDanwei.setText(listBean.get(position - 1).getFPhone());
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

        private TextView tvHarmlseeType;//养殖名称
        private TextView tvJiludanBianhao;//地址
        private TextView tvJianyiTime;//养殖动物种类
        private TextView tvHouzhuName;//存栏动物数量
        private TextView tvAnimalType;//法定代表人
        private TextView tvQuantityDanwei;//电话

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseea_type);
            tvJiludanBianhao = (TextView) view.findViewById(R.id.tv_jiludana_bianhao);
            tvJianyiTime = (TextView) view.findViewById(R.id.tv_jianyia_time);
            tvHouzhuName = (TextView) view.findViewById(R.id.tv_huozhua_name);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animala_type);
            tvQuantityDanwei = (TextView) view.findViewById(R.id.tv_quantitya_danwei);
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }


}
