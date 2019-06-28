package com.wyw.jiangsu.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.NetworkAnimType;
import com.wyw.jiangsu.view.recyclerview.BaseMultiItemQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/26.
 * 无害化 动物种类 从网络访问 合计 监管兽医
 */

public class AnimTypeTotalAdapter extends BaseMultiItemQuickAdapter<NetworkAnimType> {
    private static final int TYPE_NORMAL = 0; //动物种类
    private static final int TYPE_HEADER = 1; //第一行
    private static final int TYPE_FOOTER = 2;//合计

    public AnimTypeTotalAdapter(List<NetworkAnimType> data) {
        super(data);
        NetworkAnimType first = new NetworkAnimType();
        first.setItemType(TYPE_HEADER);
        data.add(0, first);
        addItemType(TYPE_HEADER, R.layout.adapter_anim_type_header_total);
        addItemType(TYPE_NORMAL, R.layout.adapter_anim_type_normal_total);
//        for (int i = 0; i < data.size(); i++) {
//            NetworkAnimType networkAnimType = data.get(i);
//            if (TextUtils.isEmpty(networkAnimType.getFbsdwzl())) {
//                networkAnimType.setItemType(TYPE_FOOTER);
//            }
//        }
//        addItemType(TYPE_FOOTER, R.layout.adapter_anim_type_footer_total);
    }

    @Override
    protected void convert(BaseViewHolder helper, NetworkAnimType item) {
        if (helper.getLayoutPosition() != 0) {
            switch (item.getItemType()) {
                case TYPE_NORMAL:
                    Log.e("TYPE_NORMAL","item.getFbsdwzl = "+item.getFbsdwzl()+"  item.getFsws() = "+item.getFsws()+"");
                    helper.setText(R.id.tv_anim_type,  item.getFbsdwzl())
                            .setText(R.id.tv_deadth,  item.getFsws())
//                            .setText(R.id.tv_insured,  item.getFcbs())
                            .setText(R.id.et_weight, TextUtils.isEmpty(item.getSWZL())?"0":item.getSWZL())
                            .addTextChangedListener(R.id.et_weight, new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    ((NetworkAnimType) getData().get(1)).setSWZL(s.toString());
                                }
                            });
                    break;
                case TYPE_FOOTER:
                    helper.setText(R.id.tv_total, item.getSWZL());
                    break;
            }

        }
    }
}
