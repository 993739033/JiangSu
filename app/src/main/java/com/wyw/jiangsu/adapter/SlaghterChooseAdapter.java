package com.wyw.jiangsu.adapter;

import android.widget.CompoundButton;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.SlaughterChooseBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by zhyzh on 2017/4/14.
 */

public class SlaghterChooseAdapter extends BaseQuickAdapter<SlaughterChooseBean.DataListEntity> {
    OnClickListener listener;
    public SlaghterChooseAdapter(List<SlaughterChooseBean.DataListEntity> data) {
        super(R.layout.adapter_slaughter_search, data);
//        this.listener=listener;
    }


//    @Override
//    protected void convert(BaseViewHolder helper, SlaughterChooseBean.DataListBean item) {
//        helper.setText(R.id.tv_serialNumber, item.getText())//编号
//                .setText(R.id.tv_timeShipment, item.getText1())//启运时间
//                .setText(R.id.tv_userName, item.getText2())//申报人名称
//                .setText(R.id.tv_idmName, item.getText3())//报检员姓名
//                .setText(R.id.tv_animType, item.getText4())//动物种类
//                .setText(R.id.tv_numberUnit, item.getText5())//数量及单位
//                .setOnClickListener(R.id.bt_select, v -> {
//                    if (listener != null) {
//                        listener.OnClick(item);
//                    }
//                });
//    }

    @Override
    protected void convert(BaseViewHolder helper, SlaughterChooseBean.DataListEntity item) {
        helper.setText(R.id.tv_serialNumber, item.getQCPNumber())//编号
                .setText(R.id.tv_timeShipment, item.getDateQy())//启运时间
                .setText(R.id.tv_userName, item.getSBRMC())//申报人名称
                .setText(R.id.tv_idmName, item.getQCPCargoOwner())//报检员姓名
                .setText(R.id.tv_animType, item.getQCProduct())//动物种类
                .setText(R.id.tv_numberUnit, item.getSL())//数量及单位
                .setOnCheckedChangeListener(R.id.checkbox, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      item.setCheak(isChecked);
                    }
                });
    }

    public interface OnClickListener{
        void OnClick(SlaughterChooseBean.DataListEntity item);
    }
}
