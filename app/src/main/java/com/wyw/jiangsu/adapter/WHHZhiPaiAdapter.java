package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.WHHZhiPaiChooseBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class WHHZhiPaiAdapter extends BaseQuickAdapter<WHHZhiPaiChooseBean.DataListBean> {
    public WHHZhiPaiAdapter(List<WHHZhiPaiChooseBean.DataListBean> data) {
        super(R.layout.adapter_zhipai_adapter, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WHHZhiPaiChooseBean.DataListBean item) {
        helper.setText(R.id.dead_anim_type, item.getFBsdwType())//病死动物种类
                .setText(R.id.dead_anim_number, item.getFBsdwNum())//病死动物数量
                .setText(R.id.dead_anim_weight, item.getFBsdwWeight());//病死动物重量
    }
}
