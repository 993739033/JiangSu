package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.QroducingAreaProcessListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */

public class AH_AnimalOriginListBeanAdapter extends BaseQuickAdapter<QroducingAreaProcessListBean.DataListBean> {

    public AH_AnimalOriginListBeanAdapter(List<QroducingAreaProcessListBean.DataListBean> data) {
        super(R.layout.adapter_anim_lorigin_list_bean,data);
    }

    List<String> list;

    @Override
    protected void convert(BaseViewHolder helper, QroducingAreaProcessListBean.DataListBean item) {
        helper.setText(R.id.tv_no, item.getQDWNumber())//申报单编号
                .setText(R.id.tv_date, item.getDateNpy())//检疫时间
                .setText(R.id.tv_owern, item.getQDWCargoOwner())//货主
                .setText(R.id.tv_phone, item.getQDWPhone())//电话
                .setText(R.id.tv_farm_name, item.getYzcmc());//养殖场名称
    }
}
