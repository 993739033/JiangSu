package com.wyw.jiangsu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/26.
 * 无害化 动物种类 没有参保数
 */
public class ShoujiChaXunAdapter extends BaseQuickAdapter<CollectionTransportDealActivityBean.DataBean.DataList1Bean> {

    private List<CollectionTransportDealActivityBean.DataBean.DataList1Bean> data;

    public ShoujiChaXunAdapter(List<CollectionTransportDealActivityBean.DataBean.DataList1Bean> data) {
        super(R.layout.adapter_harmless_animal_veterinarian, data);
        this.data = data;
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    protected void convert(BaseViewHolder helper, CollectionTransportDealActivityBean.DataBean.DataList1Bean item) {
        LinearLayout linAdd = helper.getView(R.id.lin_add);
        linAdd.removeAllViews();
        if (item != null) {
            for (int i = 0; i < data.size(); i++) {
                View view = LayoutInflater.from(helper.getConvertView().getContext()).inflate(R.layout.adapter_anim_type_normal_total_no_insured, null);
                ((TextView) view.findViewById(R.id.tv_anim_type)).setText(data.get(i).getDwzl());
                ((TextView) view.findViewById(R.id.tv_deadth)).setText(data.get(i).getSws());
                ((TextView) view.findViewById(R.id.tv_weight)).setText(data.get(i).getZls());
                helper.addView(R.id.lin_add, view);
            }
        }
    }
}
