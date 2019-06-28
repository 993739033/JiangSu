package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimAQueryListBean;
import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 入场查验登记查询的adapter
 * Created by Administrator on 2017/3/27.
 */

public class RuChangQueryapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RuChangChaYanQueryBean.DataListBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;
    View headerView;


    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    public RuChangQueryapter(Context context, List<RuChangChaYanQueryBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }


    public List<RuChangChaYanQueryBean.DataListBean> getDates() {
        return list;
    }

    public void setmDatas(List<RuChangChaYanQueryBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<RuChangChaYanQueryBean.DataListBean> list) {
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
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.ruchang_query_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvTuzaiQiye.setText(list.get(position - 1).geteOwner());
            ((ListviewViewHolder) holder).tvFarenDaibiao.setText(list.get(position - 1).geteDate());
            //((ListviewViewHolder) holder).tVLianxiPhone.setText(list.get(position - 1).geteNo());
            ((ListviewViewHolder) holder).tvAdress.setText(list.get(position - 1).geteCOwner());
            ((ListviewViewHolder) holder).tvTuzaiZhonglei.setText(list.get(position - 1).geteAnimal());
            ((ListviewViewHolder) holder).tvNumberDanwei.setText(list.get(position - 1).geteNum() + list.get(position - 1).geteUnit());
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

        private TextView tvTuzaiQiye;
        private TextView tvFarenDaibiao;
        private TextView tVLianxiPhone;
        private TextView tvAdress;
        private TextView tvTuzaiZhonglei;
        private TextView tvNumberDanwei;

        private ListviewViewHolder(View view) {
            super(view);
            tvTuzaiQiye = (TextView) view.findViewById(R.id.tv_tuzai_qiye);
            tvFarenDaibiao = (TextView) view.findViewById(R.id.tv_faren_daibiao);
           // tVLianxiPhone = (TextView) view.findViewById(R.id.tv_lianxi_phone);
            tvAdress = (TextView) view.findViewById(R.id.tv_adress);
            tvTuzaiZhonglei = (TextView) view.findViewById(R.id.tv_tuzai_zhonglei);
            tvNumberDanwei = (TextView) view.findViewById(R.id.tv_number_danwei);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }


}
