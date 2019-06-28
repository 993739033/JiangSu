package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

public class GuarantineDeclareListDetilAdapter extends BaseQuickAdapter<GuarantineDeclareListDetilBean.DataListBean> {


    public GuarantineDeclareListDetilAdapter(List<GuarantineDeclareListDetilBean.DataListBean> data) {
        super(R.layout.adapter_guarantine_declare_list_detil, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, GuarantineDeclareListDetilBean.DataListBean item) {
        helper.setText(R.id.tv_harmlsee_type, item.getFqSbType())//申报类型
                .setText(R.id.tv_harmlsee_zhuangtai, item.getIsPrant())//状态
                .setText(R.id.tv_harmlsee_result, item.getQDWAccepted())//申报受理结果
                .setText(R.id.tv_shouli_renming, null == item.getQDWAccepted() ? "" : item.getQDWAttn())//经办人
                .setText(R.id.tv_harmlsee_bianhao, item.getQDWNumber())//编号
                .setText(R.id.tv_harmlsee_baojiantime, item.getDateQF())//报检时间
                .setText(R.id.tv_harmlsee_huozhu, item.getQDWCargoOwner())//货主
                .setText(R.id.tv_harmlsee_lianxiphone, item.getQDWPhone())//联系电话
                .setText(R.id.tv_harmlsee_animaltype, item.getQDWXuZhongZ())//动物种类
                .setText(R.id.tv_harmlsee_numberdanwei, item.getQDWQuantity() + "" + item.getQDWDanWei());//数量单位
    }


}