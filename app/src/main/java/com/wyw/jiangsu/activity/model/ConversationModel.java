package com.wyw.jiangsu.activity.model;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.XunWenBiLuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 2017/8/8.
 */

public class ConversationModel {


    private Context mContext;
    private LinearLayout mContainer;
    private ArrayList<XunWenBiLuBean.RecordOfQuestWDBean> mDatas;
    private int position;

    public List<XunWenBiLuBean.RecordOfQuestWDBean> getData() {
        return mDatas;
    }

    public ConversationModel(Context mContext, LinearLayout mContainer) {
        this.mContext = mContext;
        this.mContainer = mContainer;
        mDatas = new ArrayList<>();
        addParticularItem();
        position++;
    }

    private void addParticularItem() {
        View viewControl = LayoutInflater.from(mContext).inflate(R.layout.model_conversation_particular_item_control, mContainer, false);
        //新增 删除
        viewControl.findViewById(R.id.bt_add_new).setOnClickListener(v -> addItem(this.position++));
        viewControl.findViewById(R.id.bt_delete).setOnClickListener(v -> {
            //默认删除最后一条
            if (mContainer.getChildCount() != 1) {
                mContainer.removeViewAt(mContainer.getChildCount() - 2);
                //删除最后一条数据
                mDatas.remove(mDatas.size() - 1);
                this.position--;
            }
        });
        mContainer.addView(viewControl);
    }

    /**
     * 添加普通的问答对话
     *
     * @param position
     */
    private void addItem(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.model_conversation_item, mContainer, false);
        XunWenBiLuBean.RecordOfQuestWDBean bean = new XunWenBiLuBean.RecordOfQuestWDBean();
        bean.setFSask("");
        bean.setFSanswer("");
        //问
        EditText etQuery = (EditText) view.findViewById(R.id.et_query);
        //答
        EditText etResponse = (EditText) view.findViewById(R.id.et_response);
        etQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bean.setFSask(s.toString());
            }
        });
        etResponse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bean.setFSanswer(s.toString());
            }
        });
        bean.setFSserialNum(position);
        mDatas.add(bean);
        //添加进去
        mContainer.addView(view, mContainer.getChildCount() - 1);
    }

    public boolean check() {
        if (mDatas != null) {
            for (XunWenBiLuBean.RecordOfQuestWDBean bean : mDatas) {
                if (TextUtils.isEmpty(bean.getFSask())) {
                    Toast.makeText(mContext, "问题不能为空", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (TextUtils.isEmpty(bean.getFSanswer())) {
                    Toast.makeText(mContext, "答案不能为空,有问必有答", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        return false;
    }
}
