package com.wyw.jiangsu.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.CommonSupervisionQyActivity;
import com.wyw.jiangsu.activity.supervision.DoubleRandomOnePublicActivity;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.bean.DoubleRandomCenterBean;
import com.wyw.jiangsu.bean.upload.UploadDoubleRandomBean;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import zxing.activity.CaptureActivity;

/**
 * Created by mnkj on 2018/1/25.
 */

public class DoubleRandomOnePublicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DoubleRandomCenterBean bean;
    LayoutInflater layoutInflater;
    private OnNextListener onNextListener;
    private HashMap<Integer, Boolean> map = new HashMap<>();//用于判断当前是否需要加载item界面
    private Context mContext;
    private CommonSupervisionBean.Data topData;

    public int getSum() {
        return sum;
    }

    private int sum = 0;//不符合数量

    public void setTopData(CommonSupervisionBean.Data topData) {
        this.topData = topData;
        notifyDataSetChanged();
    }

    //下一步
    public interface OnNextListener {
        void onNext(UploadDoubleRandomBean uploadBean);
    }

    public enum ITEM_TYPE {
        ITEM_TYPE_TOP,
        ITEM_TYPE_CENTER,
        ITEM_TYPE_BOTTOM
    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;
        if (position == 0) {
            type = ITEM_TYPE.ITEM_TYPE_TOP.ordinal();
        } else if (position == bean.getDataBeans().size() + 1) {
            type = ITEM_TYPE.ITEM_TYPE_BOTTOM.ordinal();
        } else if (0 < position && position < bean.getDataBeans().size() + 1) {
            type = ITEM_TYPE.ITEM_TYPE_CENTER.ordinal();
        }
        return type;
    }

    public DoubleRandomOnePublicAdapter(DoubleRandomCenterBean bean, OnNextListener onNextListener, Context mContext) {
        this.bean = bean;
        this.onNextListener = onNextListener;
        this.mContext = mContext;
    }

    public void notifyDatas(int position) {
            notifyItemRangeChanged(position, position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_TYPE.ITEM_TYPE_TOP.ordinal()) {
            View view = layoutInflater.inflate(R.layout.item_top_double_random_one_public, parent, false);
            return new TopViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_CENTER.ordinal()) {
            View view = layoutInflater.inflate(R.layout.item_center_double_random_one_public_1, parent, false);
            //防止viewholder 复用
            return new CenterHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_BOTTOM.ordinal()) {
            View view = layoutInflater.inflate(R.layout.item_bottom_check_double_random, parent, false);
            return new BottomHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            ((TopViewHolder) holder).BindView();
        } else if (holder instanceof CenterHolder) {
                ((CenterHolder) holder).BindView(position);
        } else if (holder instanceof BottomHolder) {
            ((BottomHolder) holder).BindView();
        }
    }

    @Override
    public int getItemCount() {
        return bean.getDataBeans().size() + 2;
    }


    public class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_qy_name)
        EditText et_qy_name;
        @BindView(R.id.et_fzr)
        EditText et_fzr;
        @BindView(R.id.et_address)
        EditText et_address;
        @BindView(R.id.et_phone)
        EditText et_phone;
        @BindView(R.id.bt_ercode)
        TextView bt_ercode;
        @BindView(R.id.bt_qy)
        Button bt_qy;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Log.e("TAG", "pos::" + getLayoutPosition());
        }

        private void BindView() {
            if (topData != null) {
                et_qy_name.setText(topData.getFFarmName());
                et_phone.setText(topData.getFPhone());
                et_fzr.setText(topData.getFLegalPerson());
                et_address.setText(topData.getFCityAdd());
            }

            bt_ercode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //开始二维码扫描 需要改变检查处理意见
                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) MyApplication.getContext(),
                                new String[]{Manifest.permission.CAMERA},
                                10000);
                    } else {
                        ((Activity) mContext).startActivityForResult(new Intent(mContext,
                                CaptureActivity.class), DoubleRandomOnePublicActivity.REQUEST_CODE_ACTIVITY_ZXING);
                    }
                }
            });

            bt_qy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CommonSupervisionQyActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("TableName", "V_AH_SlaughterBk");
                    intent.putExtras(bundle);
                    ((Activity) mContext).startActivityForResult(intent, DoubleRandomOnePublicActivity.REQUEST_CODE_ACTIVITY_QY);
                }
            });

        }

    }


    //包含title
    public class CenterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_add_1)
        TableLayout layout_add_1;//用于动态添加检查内容
        @BindView(R.id.layout_item)//父布局
                LinearLayout layout_item;
        @BindView(R.id.tv_title)
        TextView tv_title;//检查标题

        public CenterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void BindView(int position) {
            tv_title.setTag(position);
            if (!TextUtils.equals(tv_title.getTag()+"",position+"")) {
                layout_add_1.removeAllViews();
                return;
            }
            DoubleRandomCenterBean.DateBean item = bean.getDataBeans().get(position - 1);
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(item.getTitle());
            if (map.get(position) == null||!map.get(position)){
                layout_add_1.removeAllViews();
            }else{
                layout_add_1.removeAllViews();
                for (DoubleRandomCenterBean.DateBean.CheckItem checkItem : item.getCheckItems()) {
                    CheckContentHolder1 contentHolder
                            = new CheckContentHolder1(layoutInflater.inflate(R.layout.item_center_double_random_one_public_2, null));
                    contentHolder.BindView(checkItem);
                    layout_add_1.addView(contentHolder.getView());
                }
            }
            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean temp = false;
                    if (map.get(position) != null) {
                        temp = map.get(position);
                    } else {
                        map.put(position, false);
                    }
                    layout_add_1.removeAllViews();
                    if (temp) {
                        map.put(position, false);
                    } else {
                        map.put(position, true);
                        for (DoubleRandomCenterBean.DateBean.CheckItem checkItem : item.getCheckItems()) {
                            CheckContentHolder1 contentHolder
                                    = new CheckContentHolder1(layoutInflater.inflate(R.layout.item_center_double_random_one_public_2, null));
                            contentHolder.BindView(checkItem);
                            layout_add_1.addView(contentHolder.getView());
                        }
                    }
                }
            });
        }

    }


    //检查内容
    class CheckContentHolder1 {
        @BindView(R.id.tv_position)
        TextView tv_position;
        @BindView(R.id.tv_check_content)
        TextView tv_check_content;
        @BindView(R.id.tv_check_base)
        TextView tv_check_base;
        @BindView(R.id.layout_check_base)
        View layout_check_base;
        @BindView(R.id.relayout_check_content)
        View relayout_check_content;
        @BindView(R.id.layout_add_3)
        TableLayout layout_add_3;
        View view;

        public CheckContentHolder1(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
        }

        private void BindView(DoubleRandomCenterBean.DateBean.CheckItem checkItem) {
            tv_check_content.setText(checkItem.getCheckContent());
            //设置内容下标
            String position = checkItem.getCheckContent().substring(0, checkItem.getCheckContent().indexOf("."));
            tv_position.setText(position);
            layout_check_base.setVisibility(View.GONE);
            layout_add_3.removeAllViews();
            for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 checkItemData1 : checkItem.getCheckData1s()) {
                View view = layoutInflater.inflate(R.layout.item_center_double_random_one_public_2, null);
                CheckContentHolder2 viewHolder2 = new CheckContentHolder2(view);
                viewHolder2.BindView(checkItemData1);
                layout_add_3.addView(viewHolder2.getView());
            }
        }

        private View getView() {
            return view;
        }

    }


    //检查依据
    class CheckContentHolder2 {
        @BindView(R.id.tv_check_base)
        TextView tv_check_base;
        @BindView(R.id.layout_check_base)
        View layout_check_base;
        @BindView(R.id.relayout_check_content)
        View relayout_check_content;
        @BindView(R.id.layout_add_3)
        TableLayout layout_add_3;
        View view;

        public CheckContentHolder2(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
        }


        //添加检查依据
        private void BindView(DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 checkItemData1) {
            relayout_check_content.setVisibility(View.GONE);
            tv_check_base.setText(checkItemData1.getCheckBase());
            layout_add_3.removeAllViews();
            for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 checkItemData2 : checkItemData1.getCheckItemData2s()) {
                View view = layoutInflater.inflate(R.layout.item_center_check_double_random, null);
                CheckItemHolder checkItemHolder = new CheckItemHolder(view);
                checkItemHolder.BindView(checkItemData2);
                layout_add_3.addView(checkItemHolder.getView());
            }
        }

        private View getView() {
            return view;
        }
    }


    //动态添加item 检查项
    class CheckItemHolder {
        @BindView(R.id.tv_check_require)
        TextView tv_check_require;//检查要求
        @BindView(R.id.rb_yes)
        RadioButton rb_yes;//检查结果 是/符合
        @BindView(R.id.rb_no)
        RadioButton rb_no;
        @BindView(R.id.et_remark)//备注
                EditText et_remark;
        View view;

        public CheckItemHolder(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
        }

        private void BindView(DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 checkItemData2) {
            tv_check_require.setText(checkItemData2.getCheckRequire());
            et_remark.setText(checkItemData2.getCheckRemark());
            if (checkItemData2.getCheckResult().equals("符合")) {
                rb_yes.setText("符合");
                rb_no.setText("不符");
            } else {
                rb_yes.setText("是");
                rb_no.setText("否");
            }
            if (checkItemData2.getCheckResult().equals("符合") || checkItemData2.getCheckResult().equals("是")) {
                rb_yes.setChecked(true);
            } else {
                rb_no.setChecked(true);
            }

            rb_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (checkItemData2.getCheckResult().equals("符合")
                                || checkItemData2.getCheckResult().equals("不符")) {
                            checkItemData2.setCheckResult("不符");
                        } else {
                            checkItemData2.setCheckResult("否");
                        }
                    } else {
                        if (checkItemData2.getCheckResult().equals("符合")
                                || checkItemData2.getCheckResult().equals("不符")) {
                            checkItemData2.setCheckResult("符合");
                        } else {
                            checkItemData2.setCheckResult("是");
                        }
                    }
                }
            });

            et_remark.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    checkItemData2.setCheckRemark(String.valueOf(s));
                }
            });

        }

        private View getView() {
            return view;
        }
    }

    public class BottomHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_next)
        Button btn_next;

        public BottomHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void BindView() {
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CheckData() && onNextListener != null) {
                        UploadDoubleRandomBean uploadBean = new UploadDoubleRandomBean();
                        uploadBean.setBean(bean);
                        uploadBean.setNoSum(sum+"");
                        uploadBean.setTopData(topData);
                        onNextListener.onNext(uploadBean);
                    }
                }
            });
        }
    }

    //点击否时必须有备注
    private Boolean CheckData() {
        Boolean hasEmpty = false;
        sum = 0;
        for (DoubleRandomCenterBean.DateBean bean1 : bean.getDataBeans()) {
            for (DoubleRandomCenterBean.DateBean.CheckItem bean2 : bean1.getCheckItems()) {
                for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1 bean3 : bean2.getCheckData1s()) {
                    for (DoubleRandomCenterBean.DateBean.CheckItem.CheckItemData1.CheckItemData2 bean4 : bean3.getCheckItemData2s()) {
                        if (bean4.getCheckResult().equals("不符") || bean4.getCheckResult().equals("否")) {
                            sum++;
                            if (TextUtils.isEmpty(bean4.getCheckRemark())) {
                                hasEmpty = true;
                            }
                        }
                    }
                }
            }
        }
//
//        if (hasEmpty) {
//            Toast.makeText(MyApplication.getContext(), "检查结果选择为“否”时备注填写不能为空", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

}
