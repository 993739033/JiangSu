package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ZaiqianCheckQueryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class ZaiqianQueryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ZaiqianCheckQueryBean.DataListBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;
    View headerView;


    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    public ZaiqianQueryAdapter(Context context, List<ZaiqianCheckQueryBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }


    public List<ZaiqianCheckQueryBean.DataListBean> getDates() {
        return list;
    }

    public void setmDatas(List<ZaiqianCheckQueryBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<ZaiqianCheckQueryBean.DataListBean> list) {
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
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.zaiqian_query_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {

            ((ListviewViewHolder) holder).tvTuZaiBianhao.setText(list.get(position - 1).getTZJYBH());
            ((ListviewViewHolder) holder).tvTuZaiShijian.setText(list.get(position - 1).getFDate());
            ((ListviewViewHolder) holder).tvShenbaoBianhao.setText(list.get(position - 1).getSBDBH());
            ((ListviewViewHolder) holder).tvCheckNumber.setText(list.get(position - 1).getJCSL());
            ((ListviewViewHolder) holder).tvJianyiPeople.setText(list.get(position - 1).getJYRY());
            ((ListviewViewHolder) holder).tvJiluPeople.setText(list.get(position - 1).getJLRY());

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

        private TextView tvTuZaiBianhao;
        private TextView tvTuZaiShijian;
        private TextView tvShenbaoBianhao;
        private TextView tvCheckNumber;
        private TextView tvJianyiPeople;
        private TextView tvJiluPeople;

        private ListviewViewHolder(View view) {
            super(view);
            tvTuZaiBianhao = (TextView) view.findViewById(R.id.tv_tuzai_bianhao);
            tvTuZaiShijian = (TextView) view.findViewById(R.id.tv_tuzai_shijian);
            tvShenbaoBianhao = (TextView) view.findViewById(R.id.tv_shenbao_bianhao);
            tvCheckNumber = (TextView) view.findViewById(R.id.tv_check_number);
            tvJianyiPeople = (TextView) view.findViewById(R.id.tv_jianyi_people);
            tvJiluPeople = (TextView) view.findViewById(R.id.tv_jilu_people);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }
}
