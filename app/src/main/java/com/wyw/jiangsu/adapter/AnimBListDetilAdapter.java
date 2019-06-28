package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class AnimBListDetilAdapter extends BaseQuickAdapter<AnimAProcessListBean.DataListBean> {
    public AnimBListDetilAdapter(List<AnimAProcessListBean.DataListBean> data) {
        super(R.layout.adapter_anim_b_list_detil, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnimAProcessListBean.DataListBean item) {
        helper.setText(R.id.tv_number, item.getCode())//编号
                .setText(R.id.tv_name, item.getShippername())//货主姓名
                .setText(R.id.tv_phone, item.getTeltphone())//联系电话
                .setText(R.id.tv_anim_kinds, item.getAnimalsort())//动物种类
                .setText(R.id.tv_start_place, item.getRqydzxx())//启运地点
                .setText(R.id.tv_end_place, item.getRdddzxxs());//到达地点
    }
}
