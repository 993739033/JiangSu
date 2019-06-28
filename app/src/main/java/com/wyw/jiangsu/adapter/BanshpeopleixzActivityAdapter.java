package com.wyw.jiangsu.adapter;

import android.widget.CompoundButton;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.BanshpeopleleixzActivityBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Windows on 2017/6/20.
 */


public class BanshpeopleixzActivityAdapter extends BaseQuickAdapter<BanshpeopleleixzActivityBean.DataListBean> {

    OnClickListener listener;

    public BanshpeopleixzActivityAdapter(List<BanshpeopleleixzActivityBean.DataListBean> data) {
        super(R.layout.banshipeopleitem, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BanshpeopleleixzActivityBean.DataListBean item) {
        helper.setText(R.id.tv_name_anjian, item.getFname())
                .setText(R.id.tv_danwei_anjian, item.getFdwei())
                .setText(R.id.tv_zhengjian_hao, item.getFzfzjh())
                .setOnCheckedChangeListener(R.id.checkbox, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        item.setCheak(isChecked);
                    }
                });
    }

    public interface OnClickListener {
        void OnClick(BanshpeopleleixzActivityBean.DataListBean item);
    }

}