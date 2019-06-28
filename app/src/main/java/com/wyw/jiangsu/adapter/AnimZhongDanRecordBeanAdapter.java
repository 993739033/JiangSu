package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public class AnimZhongDanRecordBeanAdapter extends BaseQuickAdapter<AnimZhongDanRedordBeangai.DataListBean> {

    public AnimZhongDanRecordBeanAdapter(List<AnimZhongDanRedordBeangai.DataListBean> data) {
        super(R.layout.adapter_anim_zhongdan_frament, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnimZhongDanRedordBeangai.DataListBean item) {
        helper.setText(R.id.tv_bianhao, item.getQDWNumber())//编号
                .setText(R.id.tv_baojian_time, item.getDateQF())//报检时间
                .setText(R.id.tv_huozhu_name, item.getQDWCargoOwner())//货主
                .setText(R.id.tv_huozhu_phone, item.getQDWPhone())//货主电话
                .setText(R.id.tv_yangzhichang_name, item.getQDWTypeQy());//养殖场名称
    }
}
