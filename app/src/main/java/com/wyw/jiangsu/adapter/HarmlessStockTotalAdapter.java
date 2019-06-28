package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.view.recyclerview.BaseMultiItemQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * 驻场兽医 入库合计
 */

public class HarmlessStockTotalAdapter extends BaseMultiItemQuickAdapter<HarmlessListDetilBean.DataListBean2> {
    private static final int TYPE_NORMAL = 0; //动物种类
    private static final int TYPE_HEADER = 1; //第一行
    public static final int TYPE_FOOTER = 2;//合计
    private List<HarmlessListDetilBean.DataListBean2> data;

    public HarmlessStockTotalAdapter(List<HarmlessListDetilBean.DataListBean2> data) {
        super(data);
        this.data = data;
        HarmlessListDetilBean.DataListBean2 first = new HarmlessListDetilBean.DataListBean2();
        first.setItemType(TYPE_HEADER);
        data.add(0, first);
        addItemType(TYPE_HEADER, R.layout.adapter_anim_type_header_total);
        addItemType(TYPE_NORMAL, R.layout.adapter_anim_type_normal_total);
        addItemType(TYPE_FOOTER, R.layout.adapter_anim_type_footer_total);
    }

    @Override
    public int getItemCount() {
//        if (data == null || data.size() == 0) {
//            return 0;
//        } else {
//            data.get(data.size() - 1).setItemType(2);
//            return data.size();
//        }
        return data == null ? 0 : data.size();
    }

    @Override
    protected void convert(BaseViewHolder helper, HarmlessListDetilBean.DataListBean2 item) {

        switch (item.getItemType()) {
            case TYPE_NORMAL:
                helper.setText(R.id.tv_anim_type, item.getFbsdwzl())
                        .setFocusable(R.id.et_weight, false)
                        .setText(R.id.et_weight, item.getSWZL())
                        .setText(R.id.tv_deadth, item.getFsws());
//                            .setText(R.id.tv_insured, item.getCBSS());
                break;
            case TYPE_FOOTER:
                helper.setText(R.id.tv_total, item.getSWZL());
                break;
        }
    }
}
