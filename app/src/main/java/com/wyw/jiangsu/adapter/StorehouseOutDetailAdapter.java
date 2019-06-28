package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ChuKuDeatilBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj004 on 2017/8/29.
 */

public class StorehouseOutDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<ChuKuDeatilBean.DataBean.DataList1Bean> mData = new ArrayList<>();
    Context context;

    public StorehouseOutDetailAdapter( Context c) {
        context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.query_chuku_list_view, parent, false);
        return new StorehouseOutDetailAdapter.ListviewViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StorehouseOutDetailAdapter.ListviewViewHolder) {
            ((StorehouseOutDetailAdapter.ListviewViewHolder) holder).tvKind.setText(mData.get(position).getFBsdwType());
            ((StorehouseOutDetailAdapter.ListviewViewHolder) holder).tvNum.setText(mData.get(position).getFBsdwNum());
            ((StorehouseOutDetailAdapter.ListviewViewHolder) holder).tvWeight.setText(mData.get(position).getFBsdwWeight());
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<ChuKuDeatilBean.DataBean.DataList1Bean> dataList1) {
        if (dataList1==null){
            return;
        }
        mData = dataList1;
        notifyDataSetChanged();
    }


    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private EditText tvWeight;
        private EditText tvNum;
        private EditText tvKind;

        public ListviewViewHolder(View view) {
            super(view);
            tvKind = (EditText) view.findViewById(R.id.vet_name);
            tvNum = (EditText) view.findViewById(R.id.vet_chuzhengshu);
            tvWeight = (EditText) view.findViewById(R.id.vet_wuhaihua);
        }
    }
}
