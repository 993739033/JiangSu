package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 * 病死动物死亡申报消息接收的adapter
 */

public class HarmlessHomeVeterinatiansListAdapter extends BaseQuickAdapter<HarmlessListBean.DataEntity> {
    private OnClickListener listener;
    public HarmlessHomeVeterinatiansListAdapter(List<HarmlessListBean.DataEntity> data, OnClickListener listener) {
        super(R.layout.adapter_harlmless_home_veterinatians_list, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, HarmlessListBean.DataEntity item) {
        helper.setText(R.id.tv_harmlsee_car, item.getCPH())//收集车辆
                .setText(R.id.tv_harmlsee_name, item.getSJR())//收集人
                .setText(R.id.tv_harmlsee_collection_time, item.getFScTime())//收集时间
                .setOnClickListener(R.id.layout_confim, v -> {
                    if (listener != null) {
                        listener.onClick(helper.getAdapterPosition(),item.getFStId(),item.getFclfs());
                    }
                });
    }

    public interface OnClickListener{
        /**
         * @param position
         * @param fstId 详情页面的id
         * @param fclfs 集中处理还是自行处理
         */
        void onClick(int position, String fstId, String fclfs);
    }
}
