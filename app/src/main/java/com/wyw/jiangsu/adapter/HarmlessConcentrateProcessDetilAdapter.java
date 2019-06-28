package com.wyw.jiangsu.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.view.recyclerview.BaseMultiItemQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * 无害化处理厂集中处理
 */

public class HarmlessConcentrateProcessDetilAdapter extends BaseMultiItemQuickAdapter<HarmlessListDetilBean.DataListBean2> {
    private static final int TYPE_NORMAL = 0; //动物种类
    private static final int TYPE_HEADER = 1; //第一行
    public static final int TYPE_FOOTER = 2;//合计

    public HarmlessConcentrateProcessDetilAdapter(List<HarmlessListDetilBean.DataListBean2> data) {
        super(data);
        HarmlessListDetilBean.DataListBean2 first = new HarmlessListDetilBean.DataListBean2();
        first.setItemType(TYPE_HEADER);
        data.add(0, first);
        addItemType(TYPE_HEADER, R.layout.adapter_anim_type_header_total);
        addItemType(TYPE_NORMAL, R.layout.adapter_anim_type_normal_total);
//        for (int i = 0; i < data.size(); i++) {
//            HarmlessListDetilBean.DataListBean2 networkAnimType = data.get(i);
//            if (TextUtils.isEmpty(networkAnimType.getFbsdwzl())) {
//                networkAnimType.setItemType(TYPE_FOOTER);
//            }
//        }
        addItemType(TYPE_FOOTER, R.layout.adapter_anim_type_footer_total);
    }

    @Override
    protected void convert(BaseViewHolder helper, HarmlessListDetilBean.DataListBean2 item) {
        switch (item.getItemType()) {
            case TYPE_NORMAL:
                helper.setText(R.id.tv_anim_type, item.getFbsdwzl())
                        .setText(R.id.et_weight, item.getSWZL())
                        .setText(R.id.tv_deadth, item.getFsws())
                        .addTextChangedListener(R.id.et_weight, new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                ((HarmlessListDetilBean.DataListBean2) getData()
                                        .get(helper.getAdapterPosition()))
                                        .setSWZL(TextUtils.isEmpty(s.toString())?"0":s.toString());
                                //求和
                                double sum =0;
                                for (int i = 0; i < getData().size(); i++) {
                                    HarmlessListDetilBean.DataListBean2 bean = (HarmlessListDetilBean.DataListBean2) getData().get(i);
                                    if (!TextUtils.isEmpty(bean.getFbsdwzl())) {
                                        sum += Double.valueOf(bean.getSWZL());
                                    } else {
                                        bean.setSWZL(String.valueOf(sum));
                                    }
                                }
//                                notifyDataSetChanged();
                                notifyItemChanged(getData().size()-1);
                            }
                        });
//                            .setText(R.id.tv_insured, item.getCBSS());
                break;
            case TYPE_FOOTER:
                helper.setText(R.id.tv_total, item.getSWZL());
                break;
        }
    }
}
