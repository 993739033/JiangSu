package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/28.
 */

public class HarmlessUserDetilAdapter extends BaseQuickAdapter<UserDetilBean.Data> {
    OnClickListener listener;
    public HarmlessUserDetilAdapter(List<UserDetilBean.Data> data,OnClickListener listener) {
        super(R.layout.adapter_harlmless_user_detil, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserDetilBean.Data item) {
        helper.setText(R.id.tv_name, item.getFFarmName())//畜主姓名
                .setText(R.id.tv_addr, item.getFCityAdd())//地址
                .setText(R.id.tv_type, item.getFyzcType())//养殖场类型
                .setText(R.id.et_number, item.getFherdsScale() + " " + item.getFSmemo1())//现存栏量
                .setText(R.id.tv_idCrad, item.getFPLegalCardId())//身份证号
                .setText(R.id.name, item.getFLegalPerson())//姓名
                .setText(R.id.tv_icCrad, item.getYKTH())//一卡通号
                .setOnClickListener(R.id.bt_select, v -> {
                    if (listener != null) {
                        listener.OnClick(item);
                    }
                });
    }

    public interface OnClickListener{
         void OnClick(UserDetilBean.Data item);
    }
}
