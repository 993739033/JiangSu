package com.wyw.jiangsu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

/**
 * 入场确认收集点的Adapter
 * Created by wyw on 2016/12/30.
 */

public class HarmlessHomeVeterinarianAdapter extends BaseQuickAdapter<HarmlessListDetilBean.DataListBean0> {

    public HarmlessHomeVeterinarianAdapter() {
        super(R.layout.adapter_harlmless_home_veterinarian, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HarmlessListDetilBean.DataListBean0 item) {
        helper.setText(R.id.tv_name, item.getFxzxm())//收集点
                .setText(R.id.tv_addr, item.getFxxdz());//地址
        LinearLayout linAdd = helper.getView(R.id.lin_add);
        linAdd.removeAllViews();
        if (item.getDataList1() != null) {
            //获取死亡数
            for (int i = 0; i < item.getDataList1().size(); i++) {
                HarmlessListDetilBean.DataListBean0.DataListBean1 bean = item.getDataList1().get(i);
                View view = LayoutInflater.from(helper.getConvertView().getContext()).inflate(R.layout.adapter_anim_type_normal_total_no_insured, null);
                ((TextView) view.findViewById(R.id.tv_anim_type)).setText(bean.getFbsdwzl());
                ((TextView) view.findViewById(R.id.tv_deadth)).setText(bean.getFsws());
                ((TextView) view.findViewById(R.id.tv_weight)).setText(bean.getSWZL());
                helper.addView(R.id.lin_add, view);
            }
        }
    }
}
