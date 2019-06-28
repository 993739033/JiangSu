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
import com.wyw.jiangsu.bean.AgencyDeclareListBean;
import com.wyw.jiangsu.bean.V_APP_SJDRYXXQueryBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class CollectionPointAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View headerView;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<WuHaiHuaCXbean.DataListBean> dataListbean = new ArrayList<>();


    public CollectionPointAdapter(Context context, List<WuHaiHuaCXbean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.dataListbean = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        dataListbean.clear();
        notifyDataSetChanged();
    }


    public List<WuHaiHuaCXbean.DataListBean> getDates() {
        return dataListbean;
    }

    public void setmDatas(List<WuHaiHuaCXbean.DataListBean> list) {
        if (this.dataListbean == null) {
            this.dataListbean = list;
        } else {
            this.dataListbean.clear();
            this.dataListbean.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<WuHaiHuaCXbean.DataListBean> list) {
        if (this.dataListbean == null) {
            this.dataListbean = list;
        } else {
            this.dataListbean.addAll(list);
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
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.collection_point_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {

        } else if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeName.setText(dataListbean.get(position - 1).getFxzxm());
            ((ListviewViewHolder) holder).tvHarmlseeAddr.setText(dataListbean.get(position - 1).getFxxdz());
            ((ListviewViewHolder) holder).tvHarmlseeDate.setText(dataListbean.get(position - 1).getFsbrq());
            ((ListviewViewHolder) holder).tvHarmlseeNumber.setText(dataListbean.get(position - 1).getFbsdwzl() + " " + dataListbean.get(position - 1).getFsws());
            ((ListviewViewHolder) holder).tvHarmlEvokeTime.setText(dataListbean.get(position - 1).getFScTime());
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
        if (dataListbean == null || dataListbean.size() == 0) {
            return 1;
        }
        return dataListbean.size() + 1;
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeName;
        private TextView tvHarmlseeAddr;
        private TextView tvHarmlseeDate;
        private TextView tvHarmlseeNumber;
        private TextView tvHarmlEvokeTime;

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeName = (TextView) view.findViewById(R.id.tv_harmlsee_name);
            tvHarmlseeAddr = (TextView) view.findViewById(R.id.tv_harmlsee_addr);
            tvHarmlseeDate = (TextView) view.findViewById(R.id.tv_harmlsee_date);
            tvHarmlseeNumber = (TextView) view.findViewById(R.id.tv_harmlsee_number);
            tvHarmlEvokeTime = (TextView) view.findViewById(R.id.tv_harmlsee_evoke_time);
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }

}
