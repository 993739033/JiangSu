package com.wyw.jiangsu.activity.chaxun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimProcessListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 申报单处理的adapter
 * Created by Administrator on 2017/3/27.
 */

public class DeclareDealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View headerView;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<AnimProcessListBean.DataListBean> dataListBean = new ArrayList<>();

    public DeclareDealAdapter(Context context, List<AnimProcessListBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.dataListBean = list;
        mLayoutInflater = LayoutInflater.from(context);
    }


    public void clear() {
        dataListBean.clear();
        notifyDataSetChanged();
    }


    public void setNewData(List<AnimProcessListBean.DataListBean> dataListBean) {
        this.dataListBean = dataListBean;
        notifyDataSetChanged();
    }

  /*  public void clear(List<AnimProcessListBean.DataListBean> dataListBean) {
        this.dataListBean.removeAll(dataListBean);
        notifyDataSetChanged();
    }*/


    public AnimProcessListBean.DataListBean getItem(int position) {
        return dataListBean.get(position - 1);
    }

    public void setmDatas(List<AnimProcessListBean.DataListBean> list) {
        if (this.dataListBean == null) {
            this.dataListBean = list;
        } else {
            this.dataListBean.clear();
            this.dataListBean.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<AnimProcessListBean.DataListBean> list) {
        if (this.dataListBean == null) {
            this.dataListBean = list;
        } else {
            this.dataListBean.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<AnimProcessListBean.DataListBean> getDates() {
        return dataListBean;
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
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.declare_deal_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeType.setText(dataListBean.get(position - 1).getFqSbType());
            ((ListviewViewHolder) holder).tvAnimalType.setText(dataListBean.get(position - 1).getQDWXuZhong());
            ((ListviewViewHolder) holder).tvBaojianTime.setText(dataListBean.get(position - 1).getDateQF());
            ((ListviewViewHolder) holder).tvHarmlseeBianhao.setText(dataListBean.get(position - 1).getQDWNumber());
            ((ListviewViewHolder) holder).tvHarmlseeResult.setText(dataListBean.get(position - 1).getQDWAccepted());
            ((ListviewViewHolder) holder).tvHouzhuName.setText(dataListBean.get(position - 1).getGZ());
            ((ListviewViewHolder) holder).tvQuantityDanwei.setText(dataListBean.get(position - 1).getQDWQuantity() + dataListBean.get(position - 1).getQDWDanWei());
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
        if (dataListBean == null || dataListBean.size() == 0) {
            return 1;
        }
        return dataListBean.size() + 1;
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeType;
        private TextView tvHarmlseeResult;
        private TextView tvHarmlseeBianhao;
        private TextView tvBaojianTime;
        private TextView tvHouzhuName;
        private TextView tvAnimalType;
        private TextView tvQuantityDanwei;

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseedd_type);
            tvHarmlseeResult = (TextView) view.findViewById(R.id.tv_harmlseedd_result);
            tvHarmlseeBianhao = (TextView) view.findViewById(R.id.tv_harmlseedd_bianhao);
            tvBaojianTime = (TextView) view.findViewById(R.id.tv_baojiandd_time);
            tvHouzhuName = (TextView) view.findViewById(R.id.tv_huozhudd_name);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animaldd_type);
            tvQuantityDanwei = (TextView) view.findViewById(R.id.tv_quantitydd_danwei);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }

}
