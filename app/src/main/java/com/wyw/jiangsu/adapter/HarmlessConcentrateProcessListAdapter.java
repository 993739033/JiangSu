package com.wyw.jiangsu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 * 病死动物死亡申报消息接收的adapter
 */

public class HarmlessConcentrateProcessListAdapter extends BaseQuickAdapter<HarmlessListBean.DataEntity> {
    private OnClickListener listener;

    public HarmlessConcentrateProcessListAdapter(List<HarmlessListBean.DataEntity> data, OnClickListener listener) {
        super(R.layout.adapter_harlmless_concentrate_process_list, data);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, HarmlessListBean.DataEntity item) {
        helper.setText(R.id.tv_harmlsee_name, item.getWHHCLZX())//无害化处理厂名字
                .setText(R.id.tv_harmlsee_process_date_start, item.getCLRQ())//处理日期
                .setText(R.id.tv_harmlsee_person, item.getCLR())//处理人
                .setOnClickListener(R.id.layout_confim, v -> {
                    if (listener != null) {
                        listener.onClick(helper.getAdapterPosition(), item.getFStId(), item.getFclfs());
                    }
                });
        LinearLayout linAdd = helper.getView(R.id.lin_add);
        linAdd.removeAllViews();
        if (item.getDataList1() != null) {
            //获取死亡数
            for (int i = 0; i < item.getDataList1().size(); i++) {
                HarmlessListBean.DataEntity.DataList1Bean bean = item.getDataList1().get(i);
                View view = LayoutInflater.from(helper.getConvertView().getContext()).inflate(R.layout.adapter_anim_type_normal_total_no_insured, null);
                ((TextView) view.findViewById(R.id.tv_anim_type)).setText(bean.getDwzl());
                ((TextView) view.findViewById(R.id.tv_deadth)).setText(bean.getSws());
                ((TextView) view.findViewById(R.id.tv_weight)).setText(bean.getZls());
                helper.addView(R.id.lin_add, view);
            }
        }
    }

    public interface OnClickListener {
        /**
         * @param position
         * @param fstId    详情页面的id
         * @param fclfs    集中处理还是自行处理
         */
        void onClick(int position, String fstId, String fclfs);
    }
}
