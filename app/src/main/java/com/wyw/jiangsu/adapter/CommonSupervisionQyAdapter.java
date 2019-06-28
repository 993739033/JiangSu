package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.CommonSupervisionQyBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2018/1/25.
 */

public class CommonSupervisionQyAdapter extends BaseQuickAdapter<CommonSupervisionQyBean.DataListBean> {


    CommonSupervisionQyAdapter.OnClickListener listener;

    public CommonSupervisionQyAdapter(List<CommonSupervisionQyBean.DataListBean> data, CommonSupervisionQyAdapter.OnClickListener listener) {
        super(R.layout.richang_jianguanqy_adapter, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonSupervisionQyBean.DataListBean item) {
        helper.setText(R.id.tv_qiye_name, item.getFFarmName())
                .setText(R.id.tv_adress, item.getFCityAdd())
                .setText(R.id.tv_person, item.getFLegalPerson())
                .setText(R.id.tv_phone, item.getFPhone())
                .setOnClickListener(R.id.layout_select, v -> {
                    if (listener != null) {
                        listener.OnClick(item);
                    }
                });
    }

    public interface OnClickListener {
        void OnClick(CommonSupervisionQyBean.DataListBean item);
    }
}
