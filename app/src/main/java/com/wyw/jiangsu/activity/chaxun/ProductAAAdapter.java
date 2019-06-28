package com.wyw.jiangsu.activity.chaxun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ProductionAListBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 产品A的adapter
 * Created by Administrator on 2017/3/27.
 */

public class ProductAAAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductionAListBean.DataListEntity> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;
    View headerView;

    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    public ProductAAAdapter(Context context, List<ProductionAListBean.DataListEntity> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<ProductionAListBean.DataListEntity> getDates() {
        return list;
    }

    public void setmDatas(List<ProductionAListBean.DataListEntity> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<ProductionAListBean.DataListEntity> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AnimalAAAdapter.ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new HeadViewHolder(headerView);
        } else {
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.productaaa_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeType.setText(list.get(position - 1).getIsPrant());
            ((ListviewViewHolder) holder).tvQianfaDate.setText(list.get(position - 1).getDateQF());
            ((ListviewViewHolder) holder).tvJianyiBianhao.setText(list.get(position - 1).getPNumber());
            ((ListviewViewHolder) holder).tvHouzhuName.setText(list.get(position - 1).getPCargoOwner());
            ((ListviewViewHolder) holder).tvAnimalType.setText(list.get(position - 1).getPName());
            ((ListviewViewHolder) holder).tvQuantityDanwei.setText(list.get(position - 1).getPQuantity() + list.get(position - 1).getPDanWei());
        }
    }

    //设置ITEM类型，可以自由发挥，这里设置item 0单数显示head item 其他显示listview item
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return AnimalAAAdapter.ITEM_TYPE.ITEM_HEAD.ordinal();
        } else {
            return AnimalAAAdapter.ITEM_TYPE.ITEM_LISTVIEM.ordinal();
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
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseepa_type);
            tvQianfaDate = (TextView) view.findViewById(R.id.tv_qianfapa_date);
            tvJianyiBianhao = (TextView) view.findViewById(R.id.tv_jianyipa_bianhao);
            tvHouzhuName = (TextView) view.findViewById(R.id.tv_huozhupa_name);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animalpa_type);
            tvQuantityDanwei = (TextView) view.findViewById(R.id.tv_quantitypa_danwei);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);

        }
    }

}
