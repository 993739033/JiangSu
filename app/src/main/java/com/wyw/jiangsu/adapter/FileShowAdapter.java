package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.TongzhiGGBean;

import java.util.List;

/**
 * Created by HUANG on 2017/6/21.
 */
public class FileShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<TongzhiGGBean.DataListBean> mData;

    public FileShowAdapter(Context mContext) {
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<TongzhiGGBean.DataListBean> bean) {
        if (this.mData == null) {
            this.mData = bean;
        } else {
            this.mData.clear();
            this.mData.addAll(bean);
        }

        notifyDataSetChanged();
    }

    public void addData(List<TongzhiGGBean.DataListBean> bean) {
        this.mData.addAll(bean);
        notifyDataSetChanged();
    }

    public List<TongzhiGGBean.DataListBean> getData() {
        return mData;
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.adapter_file_show, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvFileName.setText(mData.get(position).getFititle());
            ((ListviewViewHolder) holder).tvFileTime.setText(mData.get(position).getFiDate());
            ((ListviewViewHolder) holder).tvFileZihao.setText(mData.get(position).getFIFWZH());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFileName;
        private TextView tvFileTime;
        private TextView tvFileZihao;

        public ListviewViewHolder(View view) {
            super(view);
            tvFileName = (TextView) view.findViewById(R.id.tv_file_name);
            tvFileTime = (TextView) view.findViewById(R.id.tv_file_time);
            tvFileZihao = (TextView) view.findViewById(R.id.tv_file_zihao);
        }
    }
}
