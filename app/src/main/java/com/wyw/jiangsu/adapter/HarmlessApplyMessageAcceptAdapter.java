package com.wyw.jiangsu.adapter;

import android.text.TextUtils;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 * 病死动物死亡申报消息接收的adapter
 */

public class HarmlessApplyMessageAcceptAdapter extends BaseQuickAdapter<HarmlessListBean.DataEntity> {
    private OnClickListener listener;
    public HarmlessApplyMessageAcceptAdapter(List<HarmlessListBean.DataEntity> data, OnClickListener listener) {
        super(R.layout.adapter_harlmless_apply_message_accept, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, HarmlessListBean.DataEntity item) {
        helper.setText(R.id.tv_harmlsee_date, item.getFsbrq())//申报日期
                .setText(R.id.tv_harmlsee_name, item.getFxzxm())//养殖场户姓名
                .setText(R.id.tv_harmlsee_addr, item.getFxxdz())//地址
                .setText(R.id.tv_harmlsee_number, item.getZLSL())//病死动物及数量
                .setText(R.id.tv_harmlsee_method, item.getFclfs())//处理方式
                .setText(R.id.tv_harmlsee_time,item.getGFDATE())
                .setOnClickListener(R.id.layout_confim, v -> {
                    if (listener != null) {
                        listener.onClick(helper.getAdapterPosition(),item.getFStId(),item.getFclfs());
                    }
                });
        if (TextUtils.isEmpty(item.getGFDATE())){
            helper.setVisible(R.id.rel_gfqe,false);
        }

    }

    public interface OnClickListener{
        /**
         * @param position
         * @param fstId 详情页面的id
         * @param fclfs 集中处理还是自行处理
         */
        void onClick(int position,String fstId,String fclfs);
    }
}
