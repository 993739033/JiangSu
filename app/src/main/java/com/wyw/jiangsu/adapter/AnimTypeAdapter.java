package com.wyw.jiangsu.adapter;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.NetworkAnimType;
import com.wyw.jiangsu.view.recyclerview.BaseMultiItemQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by wyw on 2016/12/26.
 * 无害化 动物种类 从网络访问 不带合计的
 */

public class AnimTypeAdapter extends BaseMultiItemQuickAdapter<NetworkAnimType>{
    private static final int TYPE_NORMAL = 0 ; //动物种类
    private static final int TYPE_HEADER = 1 ; //第一行
    //    private static final int TYPE_FOOTER = 2;//合计
    public AnimTypeAdapter(List<NetworkAnimType> data) {
        super(data);
        NetworkAnimType first = new NetworkAnimType();
        first.setItemType(TYPE_HEADER);
        data.add(0,first);
        addItemType(TYPE_HEADER, R.layout.adapter_anim_type_header);
        addItemType(TYPE_NORMAL, R.layout.adapter_anim_type_normal);
    }

    @Override
    protected void convert(BaseViewHolder helper, NetworkAnimType item) {
        if (item instanceof NetworkAnimType && helper.getLayoutPosition()!=0) {
            switch (((NetworkAnimType) item).getItemType()) {
                case TYPE_NORMAL:
                    helper.setText(R.id.tv_type, ((NetworkAnimType) item).getFbsdwzl())
                            .setText(R.id.tv_deadth, ((NetworkAnimType) item).getFsws())
                            .setText(R.id.tv_insured, ((NetworkAnimType) item).getFcbs());
                    break;
            }

        }
    }
}
