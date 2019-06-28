package com.wyw.jiangsu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.WuhaihuaDateBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

import static android.R.attr.data;

/**
 * 入场确认查询收集点的Adapter
 * Created by wyw on 2016/12/30.
 */

public class RuChangChaXunShoujiAdapter extends BaseQuickAdapter<WuhaihuaDateBean.DataBean.DataList1Bean> {


    public RuChangChaXunShoujiAdapter() {
        super(R.layout.adapter_harlmless_home_veterinarian, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, WuhaihuaDateBean.DataBean.DataList1Bean item) {
        helper.setText(R.id.tv_name, item.getFxzxm())//收集点
                .setText(R.id.tv_addr, item.getFxxdz());//地址

        LinearLayout linAdd = helper.getView(R.id.lin_add);
        linAdd.removeAllViews();
        if (item != null) {
            //获取死亡数
            View view = LayoutInflater.from(helper.getConvertView().getContext()).inflate(R.layout.adapter_anim_type_normal_total_no_insured, null);
            ((TextView) view.findViewById(R.id.tv_anim_type)).setText(item.getFbsdwzl());
            ((TextView) view.findViewById(R.id.tv_deadth)).setText(item.getFsws());
            ((TextView) view.findViewById(R.id.tv_weight)).setText(item.getSWZL());
            helper.addView(R.id.lin_add, view);
        }
    }
}
