package com.wyw.jiangsu.adapter;

import android.widget.CompoundButton;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.WHHChuKuChooseBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public class WHHCHuKuChooseAdapter extends BaseQuickAdapter<WHHChuKuChooseBean.DataListBean> {

    OnClickListener listener;

    public WHHCHuKuChooseAdapter(List<WHHChuKuChooseBean.DataListBean> data) {
        super(R.layout.adapter_whhchuku_choose, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WHHChuKuChooseBean.DataListBean item) {
        helper.setText(R.id.tv_anim_zhonglei, item.getFbsdwzl())//病死动物种类
                .setText(R.id.tv_anim_number, item.getFsws())//病死动物数量
                .setText(R.id.tv_anim_weight, item.getZSL())//病死动物重量
                .setOnCheckedChangeListener(R.id.checkbox, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        item.setCheak(isChecked);
                    }
                });
    }

    public interface OnClickListener {
        void OnClick(WHHChuKuChooseBean.DataListBean item);
    }
}
