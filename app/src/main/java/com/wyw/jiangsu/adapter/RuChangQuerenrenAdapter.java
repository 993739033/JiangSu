package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/28.
 */

public class RuChangQuerenrenAdapter extends BaseQuickAdapter<RuChangChaYanQueryBean.DataListBean> {
    OnClickListener listener;

    public RuChangQuerenrenAdapter(List<RuChangChaYanQueryBean.DataListBean> data, OnClickListener listener) {
        super(R.layout.ruchang_querren_adapter, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, RuChangChaYanQueryBean.DataListBean item) {
        helper.setText(R.id.tv_tuzai_qiye, item.getFhName())
                .setText(R.id.tv_faren_daibiao, item.getFhLegal())
                .setText(R.id.tv_lianxi_phone, item.getFhPhone())
                .setText(R.id.tv_adress, item.getFhAdress())
                .setText(R.id.tv_tuzai_zhonglei, item.getFhKind())
                .setOnClickListener(R.id.layout_select, v -> {
                    if (listener != null) {
                        listener.OnClick(item);
                    }
                });
    }

    public interface OnClickListener {
        void OnClick(RuChangChaYanQueryBean.DataListBean item);
    }
}
