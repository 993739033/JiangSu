package com.wyw.jiangsu.adapter;


import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/9/8.
 */
public class CommonSupervisionAdapter extends BaseQuickAdapter<HomeBean.DataList> {

    public CommonSupervisionAdapter(List<HomeBean.DataList> dataList) {
        super(R.layout.adapter_home_item, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataList item) {
        if (item.getNumber() == null || item.getNumber().equals("")) {
            helper.setVisible(R.id.tv_number, false);
        } else {
            helper.setVisible(R.id.tv_number, true);
        }
        helper.setText(R.id.tv_number, item.getNumber());
//        helper.setText(R.id.tv_number,"33");
        helper.setText(R.id.tv_title, item.getFmName());
//        Log.e(TAG, item.getFmName() + "\tposition: " + helper.getLayoutPosition() + "\t内存地址: " + helper + "\ttextview内存地址:" + helper.getView(R.id.tv_title));
        Glide.with(mContext).load(NetClient.IMG_PRE + item.getImg()).into((ImageView) helper.getView(R.id.img));
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }
}




