package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.WuhaihuaDateBean;
import com.wyw.jiangsu.view.recyclerview.BaseMultiItemQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * 查询入场确认合计的Adapter
 */

public class RuChangChaXunSumAdapter extends BaseMultiItemQuickAdapter<WuhaihuaDateBean.DataBean.DataList2Bean> {
    private static final int TYPE_NORMAL = 0; //动物种类
    private static final int TYPE_HEADER = 1; //第一行
    public static final int TYPE_FOOTER = 2;//合计

    public RuChangChaXunSumAdapter(List<WuhaihuaDateBean.DataBean.DataList2Bean> data) {
        super(data);
        WuhaihuaDateBean.DataBean.DataList2Bean first = new WuhaihuaDateBean.DataBean.DataList2Bean();
        first.setItemType(TYPE_HEADER);
        data.add(0, first);
        addItemType(TYPE_HEADER, R.layout.adapter_anim_type_header_total);
        addItemType(TYPE_NORMAL, R.layout.adapter_anim_type_normal_total);
        addItemType(TYPE_FOOTER, R.layout.adapter_anim_type_footer_total);
    }

    @Override
    protected void convert(BaseViewHolder helper, WuhaihuaDateBean.DataBean.DataList2Bean item) {
        switch (item.getItemType()) {
            case TYPE_NORMAL:
                helper.setText(R.id.tv_anim_type, item.getDwzl())
                        .setFocusable(R.id.et_weight, false)
                        .setText(R.id.et_weight, item.getZls())
                        .setText(R.id.tv_deadth, item.getSws());
                break;
        }
    }
}
