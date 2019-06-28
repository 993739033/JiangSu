package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordListBean;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */

public class AnimRuZhongYongRecordBeanAdapter extends BaseQuickAdapter<AnimRuZhongYongRecordListBean.DataListBean> {
    public AnimRuZhongYongRecordBeanAdapter(List<AnimRuZhongYongRecordListBean.DataListBean> data) {
        super(R.layout.adpter_anim_ruzhongyong_record_fragmen, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnimRuZhongYongRecordListBean.DataListBean item) {
        helper.setText(R.id.tv_ruyongbianhao, item.getQDWNumber())//编号
                .setText(R.id.tv_jianyishijian, item.getDateQF())//检疫时间
                .setText(R.id.tv_huozhu_mingzi, item.getQDWCargoOwner())//货主
                .setText(R.id.tv_ruyong_phone, item.getQDWPhone())//电话
                .setText(R.id.tv_ruyongfarm_name, item.getYzcmc());//养殖场名字
    }

}
