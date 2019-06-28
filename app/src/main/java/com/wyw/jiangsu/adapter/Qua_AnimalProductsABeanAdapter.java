package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ProductA_BBeanListGai;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Qua_AnimalProductsABeanAdapter extends BaseQuickAdapter<ProductA_BBeanListGai.DataListBean> {

    public Qua_AnimalProductsABeanAdapter(List<ProductA_BBeanListGai.DataListBean> data) {
        super(R.layout.qua_animal_productsa_list_bean, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ProductA_BBeanListGai.DataListBean item) {
        helper.setText(R.id.tv_anim_jianyi_hege_zhengming_number, item.getCode())//动物检疫合格编号
                .setText(R.id.tv_product_name, item.getOnimalsort())//产品名称
                .setText(R.id.tv_number_and_danwei, item.getAnimalnum() + item.getQCPDanWei())//数量及单位
                .setText(R.id.tv_shengchan_danwei_adress, item.getScdz())//生产单位地址
                .setText(R.id.tv_mudidi, item.getDddz());//目的地
    }
}
