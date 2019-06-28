package com.wyw.jiangsu.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.WHHZhiPaiChooseBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public class WHHZhiPaiChooseAdapter extends BaseQuickAdapter<WHHZhiPaiChooseBean.DataListBean> {

    OnClickListener listener;

    public WHHZhiPaiChooseAdapter(List<WHHZhiPaiChooseBean.DataListBean> data) {
        super(R.layout.adapter_zhipai_choose, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WHHZhiPaiChooseBean.DataListBean item) {
        helper.setText(R.id.tv_shoujidian_name, item.getYZCCZMC())//收集点名称
                .setText(R.id.tv_shoujidian_adress, item.getLXDZ())//地址
                .setText(R.id.tv_anim_type, item.getFBsdwType())//病死动物种类
                .setText(R.id.tv_anim_number, item.getFBsdwNum())//病死动物数量
                .setText(R.id.tv_anim_weight, item.getFBsdwWeight())//病死动物重量
                .setChecked(R.id.checkbox,item.isCheak())
                .setOnCheckedChangeListener(R.id.checkbox, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        item.setCheak(isChecked);
                    }
                });
    }

    public interface OnClickListener {
        void OnClick(WHHZhiPaiChooseBean.DataListBean item);
    }
}
