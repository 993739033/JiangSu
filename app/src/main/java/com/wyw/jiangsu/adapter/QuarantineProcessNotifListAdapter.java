package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.QuarantineProcessNotifListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class QuarantineProcessNotifListAdapter extends BaseQuickAdapter<QuarantineProcessNotifListBean.DataListBean> {
    public QuarantineProcessNotifListAdapter( List<QuarantineProcessNotifListBean.DataListBean> data) {
        super(R.layout.quarantine_proess_notif_list_bean, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuarantineProcessNotifListBean.DataListBean item) {
        helper.setText(R.id.tv_bianhao, item.getCode())//动物检疫合格证明编号
                .setText(R.id.tv_huozhu_name, item.getShippername())//货主姓名
                .setText(R.id.tv_huozhu_phone, item.getTeltphone())//货主联系电话
                .setText(R.id.tv_animal_sort,item.getAnimalsort());//动物种类
    }
}
