package com.wyw.jiangsu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.upload.AnimListBean;

import java.util.ArrayList;

/**
 * Created by wyw on 2016/12/20.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    ArrayList<String> mDatas;

    public SimpleAdapter(ArrayList<String> mDatas) {
        this.mDatas = mDatas;
    }

    public void addList(ArrayList<String> mDatas) {
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0:mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
