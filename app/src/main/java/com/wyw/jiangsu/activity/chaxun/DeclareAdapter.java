package com.wyw.jiangsu.activity.chaxun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimAQueryListBean;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * 申报单处理的adapter
 * Created by Administrator on 2017/3/27.
 */

public class DeclareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private View headerView;
    private List<AnimalQuarantineActivityBean.DataListBean> listBeen = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public DeclareAdapter(Context context, List<AnimalQuarantineActivityBean.DataListBean> list, View headerView) {
        this.context = context;
        this.headerView = headerView;
        this.listBeen = list;
        mLayoutInflater = LayoutInflater.from(context);
    }


    public void clear() {
        listBeen.clear();
        notifyDataSetChanged();
    }

    public void setNewData(List<AnimalQuarantineActivityBean.DataListBean> listBeen) {
//        this.listBeen = listBeen;
//        notifyDataSetChanged();

        if (this.listBeen == null) {
            this.listBeen = listBeen;
        } else {
            this.listBeen.clear();
            this.listBeen.addAll(listBeen);
        }
        notifyDataSetChanged();
    }

    public void addData(List<AnimalQuarantineActivityBean.DataListBean> listBeen) {
//        this.listBeen.addAll(listBeen);
//        notifyDataSetChanged();

        if (this.listBeen == null) {
            this.listBeen = listBeen;
        } else {
            this.listBeen.addAll(listBeen);
        }
        notifyDataSetChanged();
    }

   /* public void clear(List<AnimalQuarantineActivityBean.DataListBean> listBeen) {
        this.listBeen.removeAll(listBeen);
        notifyDataSetChanged();
    }*/

    public AnimalQuarantineActivityBean.DataListBean getItem(int position) {
        return listBeen.get(position - 1);
    }

    public void setmDatas(List<AnimalQuarantineActivityBean.DataListBean> list) {
        if (this.listBeen == null) {
            this.listBeen = list;
        } else {
            this.listBeen.clear();
            this.listBeen.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<AnimalQuarantineActivityBean.DataListBean> list) {
        if (this.listBeen == null) {
            this.listBeen = list;
        } else {
            this.listBeen.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<AnimalQuarantineActivityBean.DataListBean> getDates() {
        return listBeen;
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
            return new ListviewViewHolder(mLayoutInflater.inflate(R.layout.declare_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListviewViewHolder) {
            ((ListviewViewHolder) holder).tvHarmlseeType.setText(listBeen.get(position - 1).getFqSbType());
            ((ListviewViewHolder) holder).tvAnimalType.setText(listBeen.get(position - 1).getQDWXuZhong());
            ((ListviewViewHolder) holder).tvBaojianTime.setText(listBeen.get(position - 1).getDateQF());
            ((ListviewViewHolder) holder).tvHarmlseeBianhao.setText(listBeen.get(position - 1).getQDWNumber());
            ((ListviewViewHolder) holder).tvHarmlseeResult.setText(listBeen.get(position - 1).getQDWAccepted());
            ((ListviewViewHolder) holder).tvHouzhuName.setText(listBeen.get(position - 1).getGZ());
            ((ListviewViewHolder) holder).tvQuantityDanwei.setText(listBeen.get(position - 1).getQDWQuantity() + listBeen.get(position - 1).getQDWDanWei());
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
        if (listBeen == null || listBeen.size() == 0) {
            return 1;
        }
        return listBeen.size() + 1;
    }

    private class ListviewViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHarmlseeType;
        private TextView tvHarmlseeResult;
        private TextView tvHarmlseeBianhao;
        private TextView tvBaojianTime;
        private TextView tvHouzhuName;
        private TextView tvAnimalType;
        private TextView tvQuantityDanwei;

        private ListviewViewHolder(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_harmlseede_type);
            tvHarmlseeResult = (TextView) view.findViewById(R.id.tv_harmlseede_result);
            tvHarmlseeBianhao = (TextView) view.findViewById(R.id.tv_harmlseede_bianhao);
            tvBaojianTime = (TextView) view.findViewById(R.id.tv_baojiande_time);
            tvHouzhuName = (TextView) view.findViewById(R.id.tv_huozhude_name);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animalde_type);
            tvQuantityDanwei = (TextView) view.findViewById(R.id.tv_quantityde_danwei);

        }
    }


    private class HeadViewHolder extends RecyclerView.ViewHolder {
        private HeadViewHolder(View view) {
            super(view);
        }
    }

}
