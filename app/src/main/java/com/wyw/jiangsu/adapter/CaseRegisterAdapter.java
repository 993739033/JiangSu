package com.wyw.jiangsu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.zhifa.CaseRegisterActivity;
import com.wyw.jiangsu.bean.CaseRegisterBean;
import com.wyw.jiangsu.bean.ChufaListBean;
import com.wyw.jiangsu.bean.XianchangCheckBean;
import com.wyw.jiangsu.bean.XunWenListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 三个处理单的adapter
 * Created by Administrator on 2017/3/27.
 */

public class CaseRegisterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String current_table;
    private View headerView;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<?> listBean = new ArrayList<>();

    public CaseRegisterAdapter(Context context, List<?> list, View headerView, String current_table) {
        this.current_table = current_table;
        this.context = context;
        this.headerView = headerView;
        this.listBean = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void clear() {
        listBean.clear();
        notifyDataSetChanged();
    }


    public void setNewData(List<?> dataListBean) {
        this.listBean = dataListBean;
        notifyDataSetChanged();
    }

    public void setmDatas(List<?> list) {
        if (this.listBean == null) {
            this.listBean = list;
        } else {
            this.listBean.clear();
            this.listBean= list;
        }
        notifyDataSetChanged();
    }

    public void addAll(List<?> list) {
        if (this.listBean == null) {
            this.listBean = list;
        } else {
            this.listBean= list;
        }
        notifyDataSetChanged();
    }

    public List<?> getDates() {
        return listBean;
    }

    //建立枚举，2个item类型
    public enum ITEM_TYPE {
        ITEM_HEAD,
        ITEM_LISTVIEM,
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_HEAD.ordinal()) {
            return new HeadViewHolder(headerView);
        } else {
            if (current_table.equals(CaseRegisterActivity.table_1)){
                return new ListviewViewHolder1(mLayoutInflater.inflate(R.layout.caseregist_adapter, parent, false));
            }else if (current_table.equals(CaseRegisterActivity.table_2)){
                return new ListviewViewHolder2(mLayoutInflater.inflate(R.layout.jiancha_adapter, parent, false));
            }else if (current_table.equals(CaseRegisterActivity.table_3)){
                return new ListviewViewHolder3(mLayoutInflater.inflate(R.layout.xunwen_adapter, parent, false));
            }else if (current_table.equals(CaseRegisterActivity.table_4)){
                return new ListviewViewHolder4(mLayoutInflater.inflate(R.layout.chufa_adapter, parent, false));
            }else {
                return new ListviewViewHolder1(mLayoutInflater.inflate(R.layout.jiancha_adapter, parent, false));
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {
            return;

        }else if (holder instanceof ListviewViewHolder1) {
            ((ListviewViewHolder1)holder).tvHarmlseeType.setText(((List<CaseRegisterBean.DataListBean>) listBean).get(position - 1).getFScTime());
            ((ListviewViewHolder1)holder).tvJiludanBianhao.setText(((List<CaseRegisterBean.DataListBean>) listBean).get(position - 1).getFany());
            ((ListviewViewHolder1)holder).tvJianyiTime.setText(((List<CaseRegisterBean.DataListBean>) listBean).get(position - 1).getFly());//办案人员
        }else if (holder instanceof ListviewViewHolder2){
            ((ListviewViewHolder2)holder).tvHarmlseeType.setText(((List<XianchangCheckBean.DataListBean>) listBean).get(position - 1).getFdz());
            ((ListviewViewHolder2)holder).tvJiludanBianhao.setText(((List<XianchangCheckBean.DataListBean>) listBean).get(position - 1).getFdsr());
            ((ListviewViewHolder2)holder).tvJianyiTime.setText(((List<XianchangCheckBean.DataListBean>) listBean).get(position - 1).getFkcry());
            /*private TextView tvHarmlseeType;//检查地点
        private TextView tvJiludanBianhao;//当事人
        private TextView tvJianyiTime;//检查人员*/
        }else if (holder instanceof ListviewViewHolder3){
            ((ListviewViewHolder3)holder).tvHarmlseeType.setText(((List<XunWenListBean.DataListBean>) listBean).get(position - 1).getFdz());
            ((ListviewViewHolder3)holder).tvJiludanBianhao.setText(((List<XunWenListBean.DataListBean>) listBean).get(position - 1).getFjg());
            ((ListviewViewHolder3)holder).tvJianyiTime.setText(((List<XunWenListBean.DataListBean>) listBean).get(position - 1).getFxwr());
/* private TextView tvHarmlseeType;//询问地点
        private TextView tvJiludanBianhao;//询问机关
        private TextView tvJianyiTime;//询问人*/
        }else if (holder instanceof ListviewViewHolder4){
            ChufaListBean.DataListBean bean = ((List<ChufaListBean.DataListBean>) listBean).get(position - 1);
            ((ListviewViewHolder4)holder).tvHarmlseeType.setText(bean.getXm());
            ((ListviewViewHolder4)holder).tvJiludanBianhao.setText(bean.getDwmc());
            ((ListviewViewHolder4)holder).tvJianyiTime.setText(bean.getSfdczx());
            /*private TextView tvHarmlseeType;//个人
        private TextView tvJiludanBianhao;//公司
        private TextView tvJianyiTime;//是否立即执行*/


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
        if (listBean == null || listBean.size() == 0) {
            return 1;
        }
        return listBean.size()+1;
    }


    private class ListviewViewHolder1 extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeType;//登记日期
        private TextView tvJiludanBianhao;//案由
        private TextView tvJianyiTime;//办案人员

        private ListviewViewHolder1(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseea_type);
            tvJiludanBianhao = (TextView) view.findViewById(R.id.tv_jiludana_bianhao);
            tvJianyiTime = (TextView) view.findViewById(R.id.tv_jianyia_time);
        }
    }
    private class ListviewViewHolder2 extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeType;//检查地点
        private TextView tvJiludanBianhao;//当事人
        private TextView tvJianyiTime;//检查人员

        private ListviewViewHolder2(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseea_type);
            tvJiludanBianhao = (TextView) view.findViewById(R.id.tv_jiludana_bianhao);
            tvJianyiTime = (TextView) view.findViewById(R.id.tv_jianyia_time);
        }
    }
    private class ListviewViewHolder3 extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeType;//询问地点
        private TextView tvJiludanBianhao;//询问机关
        private TextView tvJianyiTime;//询问人

        private ListviewViewHolder3(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseea_type);
            tvJiludanBianhao = (TextView) view.findViewById(R.id.tv_jiludana_bianhao);
            tvJianyiTime = (TextView) view.findViewById(R.id.tv_jianyia_time);
        }
    }
    private class ListviewViewHolder4 extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeType;//个人
        private TextView tvJiludanBianhao;//公司
        private TextView tvJianyiTime;//是否立即执行

        private ListviewViewHolder4(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseea_type);
            tvJiludanBianhao = (TextView) view.findViewById(R.id.tv_jiludana_bianhao);
            tvJianyiTime = (TextView) view.findViewById(R.id.tv_jianyia_time);
        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }


}
