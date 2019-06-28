package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.Qua_AnimalProductsBListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Qua_AnimalProductsBBeanAdapter extends BaseQuickAdapter<Qua_AnimalProductsBListBean.DataListBean> {
    public Qua_AnimalProductsBBeanAdapter(List<Qua_AnimalProductsBListBean.DataListBean> data) {
        super(R.layout.qua_animal_productsb_list_bean, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Qua_AnimalProductsBListBean.DataListBean item) {
        helper.setText(R.id.tv_harmlsee_date, item.getFScTime())//申报日期
                .setText(R.id.tv_harmlsee_name, item.getFSenterpriseName())//养殖场户姓名
                .setText(R.id.tv_harmlsee_addr, item.getFSunitName());//地址
    }
}
