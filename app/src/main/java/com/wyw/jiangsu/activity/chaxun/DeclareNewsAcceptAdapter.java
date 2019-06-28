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
import com.wyw.jiangsu.bean.DeclareNewstListBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class DeclareNewsAcceptAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View headerView;
    //    private List<DeclareNewstListBean.DataListBean> list = new ArrayList<>();
    private List<WuHaiHuaCXbean.DataListBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public DeclareNewsAcceptAdapter(Context context, List<WuHaiHuaCXbean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

//    public void addAll(List<DeclareNewstListBean.DataListBean> list) {
//        if (this.list == null) {
//            this.list = list;
//        } else {
//            this.list.addAll(list);
//        }
//        notifyDataSetChanged();
//    }
//
//    public void setmDatas(List<DeclareNewstListBean.DataListBean> list) {
//        if (this.list == null) {
//            this.list = list;
//        } else {
//            this.list.clear();
//            this.list.addAll(list);
//        }
//        notifyDataSetChanged();
//    }

    public List<WuHaiHuaCXbean.DataListBean> getDates() {
        return list;
    }

    public void setmDatas(List<WuHaiHuaCXbean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<WuHaiHuaCXbean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }


    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new HeadViewHolder(headerView);
        } else {
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.query_listviewholder_wuhaihua, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvChuzhuName.setText(list.get(position - 1).getFxzxm());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvAdress.setText(list.get(position - 1).getFxxdz());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvApplyTime.setText(list.get(position - 1).getFsbrq());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvChuliWay.setText(list.get(position - 1).getFclfs());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvXiancunlanliang.setText(list.get(position - 1).getFxcll());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvYanghzicType.setText(list.get(position - 1).getFyzclx());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvErbiaohao.setText(list.get(position - 1).getQDWErBiaoHao());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvYikatonghao.setText(list.get(position - 1).getFykth());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvShenfenzhenghao.setText(list.get(position - 1).getFsfzh());
            ((DeclareNewsAcceptAdapter.ListviewViewHolder) holder).tvName.setText(list.get(position - 1).getXm());
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

        private TextView tvChuzhuName;
        private TextView tvAdress;
        private TextView tvYanghzicType;
        private TextView tvXiancunlanliang;
        private TextView tvApplyTime;
        private TextView tvChuliWay;
        private TextView tvErbiaohao;
        private TextView tvShenfenzhenghao;
        private TextView tvYikatonghao;
        private TextView tvName;

        private ListviewViewHolder(View view) {
            super(view);
            tvChuzhuName = (TextView) view.findViewById(R.id.tv_chuzhu_name3);
            tvAdress = (TextView) view.findViewById(R.id.tv_adress3);
            tvYanghzicType = (TextView) view.findViewById(R.id.tv_yanghzic_type3);
            tvXiancunlanliang = (TextView) view.findViewById(R.id.tv_xiancunlanliang3);
            tvApplyTime = (TextView) view.findViewById(R.id.tv_apply_time3);
            tvChuliWay = (TextView) view.findViewById(R.id.tv_chuli_way3);
            tvErbiaohao = (TextView) view.findViewById(R.id.tv_erbiaohao3);
            tvShenfenzhenghao = (TextView) view.findViewById(R.id.tv_shenfenzhenghao3);
            tvYikatonghao = (TextView) view.findViewById(R.id.tv_yikatonghao3);
            tvName = (TextView) view.findViewById(R.id.name);
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }
}
