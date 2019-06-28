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
import com.wyw.jiangsu.bean.V_APP_WHHCLZXQueryBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 集中处理查询列表adapter
 * Created by Administrator on 2017/3/28.
 */

public class ConcentrateDisposeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View headerView;
    private Context context;
    private LayoutInflater mLayoutInflater;
    //    private List<V_APP_WHHCLZXQueryBean.DataListBean> datalistbean = new ArrayList<>();
    private List<WuHaiHuaCXbean.DataListBean> datalistbean = new ArrayList<>();

    public ConcentrateDisposeAdapter(Context context, List<WuHaiHuaCXbean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.datalistbean = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<WuHaiHuaCXbean.DataListBean> getDates() {
        return datalistbean;
    }


    public void clear() {
        datalistbean.clear();
        notifyDataSetChanged();
    }

    public void setmDatas(List<WuHaiHuaCXbean.DataListBean> list) {
        if (this.datalistbean == null) {
            this.datalistbean = list;
        } else {
            this.datalistbean.clear();
            this.datalistbean.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<WuHaiHuaCXbean.DataListBean> list) {
        if (this.datalistbean == null) {
            this.datalistbean = list;
        } else {
            this.datalistbean.addAll(list);
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
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.concentrate_dispose_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeName.setText(datalistbean.get(position - 1).getWHHCLZX());
            ((ListviewViewHolder) holder).tvHarmlseeProcessData.setText(datalistbean.get(position - 1).getCLR());
            ((ListviewViewHolder) holder).tvHarmlseePerson.setText(datalistbean.get(position - 1).getCLRQ());
            ((ListviewViewHolder) holder).tvHarmlseeDieAnimal.setText(datalistbean.get(position - 1).getSJ());
            ((ListviewViewHolder) holder).tvHarmlseeDieNumber.setText(datalistbean.get(position - 1).getCLFSS());
            ((ListviewViewHolder) holder).tvHarmlseeZhongliang.setText(datalistbean.get(position - 1).getYZ());
            ((ListviewViewHolder) holder).tvCanzha.setText(datalistbean.get(position - 1).getCZ());
            ((ListviewViewHolder) holder).tvYetiChanwu.setText(datalistbean.get(position - 1).getYTCW());
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
        if (datalistbean == null || datalistbean.size() == 0) {
            return 1;
        }
        return datalistbean.size() + 1;
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeName;
        private TextView tvHarmlseeProcessData;
        private TextView tvHarmlseePerson;
        private TextView tvHarmlseeDieAnimal;
        private TextView tvHarmlseeDieNumber;
        private TextView tvHarmlseeZhongliang;
        private TextView tvCanzha;
        private TextView tvYetiChanwu;

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeName = (TextView) view.findViewById(R.id.tv_harmlsee_name);
            tvHarmlseeProcessData = (TextView) view.findViewById(R.id.tv_harmlsee_process_date);
            tvHarmlseePerson = (TextView) view.findViewById(R.id.tv_harmlsee_person);
            tvHarmlseeDieAnimal = (TextView) view.findViewById(R.id.tv_harmlsee_dieanimal);
            tvHarmlseeDieNumber = (TextView) view.findViewById(R.id.tv_harmlsee_dienumber);
            tvHarmlseeZhongliang = (TextView) view.findViewById(R.id.tv_harmlsee_zhongliang);
            tvCanzha = (TextView) view.findViewById(R.id.tv_canzha);
            tvYetiChanwu = (TextView) view.findViewById(R.id.tv_yeti_chanwu);

        }
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }

}
