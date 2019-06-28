package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ChaxunJinduActuvityBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 2017/6/12.
 */

public class ChaxunJinduActuvityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String str;
    private int size;
    private View headerView;
    private List<ChaxunJinduActuvityBean.DataListBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public ChaxunJinduActuvityAdapter(Context context, List<ChaxunJinduActuvityBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setmDatas(List<ChaxunJinduActuvityBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<ChaxunJinduActuvityBean.DataListBean> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<ChaxunJinduActuvityBean.DataListBean> getDates() {
        return list;
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
        if (viewType == ChaxunJinduActuvityAdapter.ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new ChaxunJinduActuvityAdapter.HeadViewHolder(headerView);
        } else {
            return new ChaxunJinduActuvityAdapter.ListviewViewHolder(mLayoutInflater.inflate(R.layout.chaxunjindu_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ChaxunJinduActuvityAdapter.ListviewViewHolder) {
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvChuzhuName.setText(list.get(position - 1).getFxzxm());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvAdress.setText(list.get(position - 1).getFxxdz());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvApplyTime.setText(list.get(position - 1).getFsbrq());//日期
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvChuliWay.setText(list.get(position - 1).getFclfs());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvXiancunlanliang.setText(list.get(position - 1).getFxcll());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvYanghzicType.setText(list.get(position - 1).getFyzclx());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvErbiaohao.setText(list.get(position - 1).getQDWErBiaoHao());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvYikatonghao.setText(list.get(position - 1).getFykth());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvShenfenzhenghao.setText(list.get(position - 1).getFsfzh());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvSiWanglv.setText(list.get(position - 1).getSwl());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).tvXiangjuJuli.setText(list.get(position - 1).getJl());
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).name.setText(list.get(position - 1).getXm());
            size = list.get(position - 1).getDataList1().size();
            ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).lv_layout.removeAllViews();
            TextView textview;
            if (size == 0) {
                View view = LayoutInflater.from(context).inflate(R.layout.text_add, null);
                TextView textView = (TextView) view.findViewById(R.id.tv_jindu);
                textView.setText("暂无进度");
                ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).lv_layout.addView(view);
            } else {
                for (int i = 0; i < list.get(position - 1).getDataList1().size(); i++) {
                    View view = LayoutInflater.from(context).inflate(R.layout.text_add, null);
                    TextView textView = (TextView) view.findViewById(R.id.tv_jindu);
                    TextView textView2 = (TextView) view.findViewById(R.id.tv_jindu2);
                    str = list.get(position - 1).getDataList1().get(i).getZT();
                    File file = new File(str);
                    String filename = file.getName();
                    String fil = filename.substring(filename.lastIndexOf("("));
                    String fill = filename.substring(0, filename.lastIndexOf("("));
                    textView.setText("类型：" + fill);
                    textView2.setText("签名:" + fil);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.black));
                    textView2.setTextColor(ContextCompat.getColor(context, R.color.black));
                    ((ChaxunJinduActuvityAdapter.ListviewViewHolder) holder).lv_layout.addView(view);
                }
            }
        }
    }

    //设置ITEM类型，可以自由发挥，这里设置item 0单数显示head item 其他显示listview item
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ChaxunJinduActuvityAdapter.ITEM_TYPE.ITEM_HEAD.ordinal();
        } else {
            return ChaxunJinduActuvityAdapter.ITEM_TYPE.ITEM_LISTVIEM.ordinal();
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
        private TextView tvSiWanglv;
        private TextView tvXiangjuJuli;
        private LinearLayout lv_layout;
        private TextView name;

        private ListviewViewHolder(View view) {
            super(view);
            tvChuzhuName = (TextView) view.findViewById(R.id.tv_chuzhu_name);
            tvAdress = (TextView) view.findViewById(R.id.tv_adress);
            tvYanghzicType = (TextView) view.findViewById(R.id.tv_yanghzic_type);
            tvXiancunlanliang = (TextView) view.findViewById(R.id.tv_xiancunlanliang);
            tvApplyTime = (TextView) view.findViewById(R.id.tv_apply_time);
            tvChuliWay = (TextView) view.findViewById(R.id.tv_chuli_way);
            tvErbiaohao = (TextView) view.findViewById(R.id.tv_erbiaohao);
            tvShenfenzhenghao = (TextView) view.findViewById(R.id.tv_shenfenzhenghao);
            tvYikatonghao = (TextView) view.findViewById(R.id.tv_yikatonghao);
            tvSiWanglv = (TextView) view.findViewById(R.id.tv_siwanglv);
            tvXiangjuJuli = (TextView) view.findViewById(R.id.tv_xiangju_juli);
            lv_layout = (LinearLayout) view.findViewById(R.id.lv_layout);
            name = (TextView) view.findViewById(R.id.name);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }
}
