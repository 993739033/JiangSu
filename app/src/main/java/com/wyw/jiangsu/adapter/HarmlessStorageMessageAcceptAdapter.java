package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 * 暂存点收集人员消息接收的adapter
 * 包括两种类型  暂存点的收集人员
 */

public class HarmlessStorageMessageAcceptAdapter extends BaseQuickAdapter<HarmlessListBean.DataEntity> {
    private OnClickListener listener;

    public HarmlessStorageMessageAcceptAdapter(List<HarmlessListBean.DataEntity> data, OnClickListener listener) {
        super(R.layout.adapter_harlmless_storage_message_accept, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, HarmlessListBean.DataEntity item) {
//        helper.setText(R.id.tv_harmlsee_date, item.getFsbrq())//申报日期
        helper.setText(R.id.tv_harmlsee_name, item.getFxzxm())//收集点名称
                .setText(R.id.tv_harmlsee_addr, item.getFxxdz())//地址
                .setText(R.id.tv_harmlsee_weight, item.getSWZL())//病死动物重量
                .setText(R.id.tv_harmlsee_number, item.getZLSL())//病死动物及数量
//                .setText(R.id.tv_harmlsee_collection_time, item.getFScTime())//分派收集日期
                .setOnClickListener(R.id.bt_confim, v -> {
                    if (listener != null) {
                        listener.onClick(helper.getAdapterPosition(), item.getFStId(), item.getFclfs(), item.getFGlid(),item.getSws());
                    }
                });
    }

    public interface OnClickListener {
        /**
         * @param position
         * @param fstId    详情页面的id
         * @param fclfs    集中处理还是自行处理
         */
        void onClick(int position, String fstId, String fclfs, String fglid,String num);
    }
}
