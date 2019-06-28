package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnjiandengjiActivityBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 2017/6/19.
 */

public class AnjiandengjiActivityAdapter extends BaseQuickAdapter<AnjiandengjiActivityBean.DataListBean> {


    public AnjiandengjiActivityAdapter(List<AnjiandengjiActivityBean.DataListBean> data) {
        super(R.layout.activity_anjiandengji, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnjiandengjiActivityBean.DataListBean item) {
        helper.setText(R.id.tv_chuzhu_name, item.getFany())//案由
                .setText(R.id.tv_adress, item.getFly())//案件来源
                .setText(R.id.tv_yanghzic_type, item.getFajlx())//案件类型
                .setText(R.id.tv_xiancunlanliang, item.getFname())//办案人员
                .setText(R.id.tv_apply_time, item.getFdjrq());//办案时间
    }


}
