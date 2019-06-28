package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.WHHChuKuChooseBean;

import java.util.List;
//WHHChuKuChooseBean.DataListBean

/**
 * Created by Administrator on 2017/6/15.
 */

public class WHHChuKuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  Handler mHandler;
    private Context context;
    private List<WHHChuKuChooseBean.DataListBean> list;
    private LayoutInflater mLayoutInflater;

    public WHHChuKuAdapter(Context context, List<WHHChuKuChooseBean.DataListBean> list, Handler mHandler) {
        this.context = context;
        this.list = list;
        this.mHandler = mHandler;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<WHHChuKuChooseBean.DataListBean> getData() {
        return list;
    }

    public void setmDatas(List<WHHChuKuChooseBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.adapter_whhchuku_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListviewViewHolder) holder).animType.setText(list.get(position).getFbsdwzl());
        ((ListviewViewHolder) holder).animNumber.setText(list.get(position).getFsws());
        ((ListviewViewHolder) holder).animWeight.setText(list.get(position).getZSL());

        ((ListviewViewHolder) holder).animWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(position).setZSL(s.toString());
                Message message = new Message();
                message.obj = list;
                mHandler.sendMessage(message);
            }
        });

        ((ListviewViewHolder) holder).animNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(position).setFsws(s.toString());
                Message message = new Message();
                message.obj = list;
                mHandler.sendMessage(message);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private EditText animType;
        private EditText animNumber;
        private EditText animWeight;

        private ListviewViewHolder(View view) {
            super(view);
            animType = (EditText) view.findViewById(R.id.dead_anim_type);
            animNumber = (EditText) view.findViewById(R.id.dead_anim_number);
            animWeight = (EditText) view.findViewById(R.id.dead_anim_weight);
        }
    }
}
