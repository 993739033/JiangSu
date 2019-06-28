package com.wyw.jiangsu.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.JiZhongChuLiDetilBean;
import com.wyw.jiangsu.view.recyclerview.BaseMultiItemQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * 无害化处理厂集中处理
 */

public class JiZhongChuLiCXDetilAdapter extends BaseMultiItemQuickAdapter<JiZhongChuLiDetilBean.DataBean.DataList1Bean> {
    private static final int TYPE_NORMAL = 0; //动物种类
    private static final int TYPE_HEADER = 1; //第一行

    public JiZhongChuLiCXDetilAdapter(List<JiZhongChuLiDetilBean.DataBean.DataList1Bean> data) {
        super(data);
        JiZhongChuLiDetilBean.DataBean.DataList1Bean first = new JiZhongChuLiDetilBean.DataBean.DataList1Bean();
        first.setItemType(TYPE_HEADER);
        data.add(0, first);
        addItemType(TYPE_HEADER, R.layout.adapter_anim_type_header_total);
        addItemType(TYPE_NORMAL, R.layout.jizhong_chuli_cxadapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, JiZhongChuLiDetilBean.DataBean.DataList1Bean item) {
        switch (item.getItemType()) {
            case TYPE_NORMAL:
                helper.setText(R.id.tv_anim_type, item.getDwzl())
                        .setText(R.id.et_weight, item.getZls())
                        .setText(R.id.tv_deadth, item.getSws());
                break;

        }
    }
}
