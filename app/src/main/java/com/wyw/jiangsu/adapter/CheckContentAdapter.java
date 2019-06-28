package com.wyw.jiangsu.adapter;


import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.model.PhotoViewModel;
import com.wyw.jiangsu.activity.supervision.CommonSupervisionDetilActivityActivity;
import com.wyw.jiangsu.bean.local.CheckContentEntity;

import java.io.File;
import java.util.List;

/**
 * Created by wyw on 2016/9/8.
 */
public class CheckContentAdapter extends BaseAdapter {
    private static final int COMMON = 0;
    private static final int LAST = 1;
    private ItemChangeListener itemChangeListener;
    private List<CheckContentEntity> mDatas;
    private LayoutInflater inflater;
    private ImgClickListener clickListener;
    private Context mContext;
    private String basePathName;
    private int type;
    private OnCheckChangeListener checkChangeListener;


    public List<CheckContentEntity> getDatas() {
        return mDatas;
    }

    public CheckContentAdapter(Context mContext, List<CheckContentEntity> mDatas,
                               ImgClickListener clickListener, OnCheckChangeListener checkChangeListener,
                               String basePathName) {
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
        this.clickListener = clickListener;
        this.checkChangeListener = checkChangeListener;
        this.mContext = mContext;
        this.basePathName = basePathName;
    }

    public CheckContentAdapter(Context mContext, List<CheckContentEntity> mDatas,
                               ImgClickListener clickListener, OnCheckChangeListener checkChangeListener,
                               int type, ItemChangeListener itemChangeListener) {
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(mContext);
        this.clickListener = clickListener;
        this.checkChangeListener = checkChangeListener;
        this.mContext = mContext;
        this.type = type;
        this.itemChangeListener = itemChangeListener;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * @return true 合格
     * false  不合格
     */
    public boolean check() {
        //最后一条不判断
        for (int i = 0; i < mDatas.size() - 1; i++) {
            CheckContentEntity entity = mDatas.get(i);
//            entity.isJL() && TextUtils.isEmpty(entity.getTp()) 情况一 打对勾 后面没有图片
//            !entity.isJL() && !TextUtils.isEmpty(entity.getTp()) 情况二 没有对勾 后面有图片
//            if (entity.getRbCheckYes().equals(entity.getJL()) && !TextUtils.isEmpty(entity.getTp())) {
//                Toast.makeText(mContext, "当选择符合时,不可以选择图片", Toast.LENGTH_SHORT).show();
//                return true;
//            }
            if (entity.getRbCheckNo().equals(entity.getJL()) && TextUtils.isEmpty(entity.getTp())) {
                Toast.makeText(mContext, "当选择不符合时,必须上传对应照片", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        //如果其他的内容没有填写数据
//        if (TextUtils.isEmpty(mDatas.get(mDatas.size() - 1).getName())) {
//            Toast.makeText(mContext, "请填写其他的内容,没有填写 无", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return false;
    }

    @Override
    public CheckContentEntity getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        RadioGroup rgCheckResult;
        final TextView etDescription;
        final ImageView imgTakePhone;
        LinearLayout linAdd = null;

        if (getItemViewType(position) == COMMON) {
            view = inflater.inflate(R.layout.adapter_check_content, parent, false);
            TextView tvCheckContent = (TextView) view.findViewById(R.id.tv_check_content);
            etDescription = (TextView) view.findViewById(R.id.et_description);
            imgTakePhone = (ImageView) view.findViewById(R.id.img_takephone);
            tvCheckContent.setText(getItem(position).getName());
            rgCheckResult = (RadioGroup) view.findViewById(R.id.rg_check_result);
            linAdd = (LinearLayout) view.findViewById(R.id.lin_add);
            linAdd.removeAllViews();
            etDescription.setVisibility(View.VISIBLE);
            linAdd.setVisibility(View.GONE);
            //判断表名
            if (type == CommonSupervisionDetilActivityActivity.TABLE_1) {
                if (position == 3 || position == 8) {
                    int size = mDatas.get(position).getList().size();
                    for (int i = 0; i < size; i++) {
                        LinearLayout view3 = (LinearLayout) LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.adapter_richang_item_layout, null);
                        CheckBox cb1 = new CheckBox(parent.getContext());
                        cb1.setId(i);
                        view3.addView(cb1);
                        cb1.setText(mDatas.get(position).getList().get(i).getXZ());
                        if (mDatas.get(position).getList().get(i).isCheek()) {
                            cb1.setChecked(true);
                        }
                        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                int id = cb1.getId();
                                mDatas.get(position).getList().get(id).setXZ((String) cb1.getText());
                                mDatas.get(position).getList().get(id).setCheek(b);
                                StringBuilder builder = new StringBuilder();
                                if (mDatas.get(position).getList() != null) {
                                    for (int n = 0; n < mDatas.get(position).getList().size(); n++) {
                                        if (mDatas.get(position).getList().get(n).isCheek()) {
                                            builder.append(mDatas.get(position).getList().get(n).getXZ() + ",");
                                        }
                                    }
                                    mDatas.get(position).setCs(builder.toString());
                                }
                            }
                        });
                        linAdd.addView(view3);
                    }
                }
            } else if (type == CommonSupervisionDetilActivityActivity.TABLE_3) {
                if (position == 4 || position == 5 || position == 7) {
                    int size = mDatas.get(position).getList().size();
                    for (int i = 0; i < size; i++) {
                        LinearLayout view3 = (LinearLayout) LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.adapter_richang_item_layout, null);
                        CheckBox cb1 = new CheckBox(parent.getContext());
                        cb1.setId(i);
                        view3.addView(cb1);
                        if (mDatas.get(position).getList().get(i).isCheek()) {
                            cb1.setChecked(true);
                        }
                        cb1.setText(mDatas.get(position).getList().get(i).getXZ());
                        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                int id = cb1.getId();
                                mDatas.get(position).getList().get(id).setXZ((String) cb1.getText());
                                mDatas.get(position).getList().get(id).setCheek(b);
                                StringBuilder builder = new StringBuilder();
                                if (mDatas.get(position).getList() != null) {
                                    for (int n = 0; n < mDatas.get(position).getList().size(); n++) {
                                        if (mDatas.get(position).getList().get(n).isCheek()) {
                                            builder.append(mDatas.get(position).getList().get(n).getXZ() + ",");
                                        }
                                    }
                                    mDatas.get(position).setCs(builder.toString());
                                }
                            }
                        });
                        linAdd.addView(view3);
                    }
                }
            } else if (type == CommonSupervisionDetilActivityActivity.TABLE_4) {
                if (position == 2 || position == 9) {
                    int size = mDatas.get(position).getList().size();
                    for (int i = 0; i < size; i++) {
                        LinearLayout view3 = (LinearLayout) LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.adapter_richang_item_layout, null);
                        CheckBox cb1 = new CheckBox(parent.getContext());
                        cb1.setId(i);
                        view3.addView(cb1);
                        if (mDatas.get(position).getList().get(i).isCheek()) {
                            cb1.setChecked(true);
                        }
                        cb1.setText(mDatas.get(position).getList().get(i).getXZ());
                        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                int id = cb1.getId();
                                mDatas.get(position).getList().get(id).setXZ((String) cb1.getText());
                                mDatas.get(position).getList().get(id).setCheek(b);
                                StringBuilder builder = new StringBuilder();
                                if (mDatas.get(position).getList() != null) {
                                    for (int n = 0; n < mDatas.get(position).getList().size(); n++) {
                                        if (mDatas.get(position).getList().get(n).isCheek()) {
                                            builder.append(mDatas.get(position).getList().get(n).getXZ() + ",");
                                        }
                                    }
                                    mDatas.get(position).setCs(builder.toString());
                                }
                            }
                        });
                        linAdd.addView(view3);
                    }
                }
            } else if (type == CommonSupervisionDetilActivityActivity.TABLE_5) {
                if (position == 2 || position == 5) {
                    int size = mDatas.get(position).getList().size();
                    for (int i = 0; i < size; i++) {
                        LinearLayout view3 = (LinearLayout) LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.adapter_richang_item_layout, null);
                        CheckBox cb1 = new CheckBox(parent.getContext());
                        cb1.setId(i);
                        view3.addView(cb1);
                        if (mDatas.get(position).getList().get(i).isCheek()) {
                            cb1.setChecked(true);
                        }
                        cb1.setText(mDatas.get(position).getList().get(i).getXZ());
                        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                int id = cb1.getId();
                                mDatas.get(position).getList().get(id).setXZ((String) cb1.getText());
                                mDatas.get(position).getList().get(id).setCheek(b);
                                StringBuilder builder = new StringBuilder();
                                if (mDatas.get(position).getList() != null) {
                                    for (int n = 0; n < mDatas.get(position).getList().size(); n++) {
                                        if (mDatas.get(position).getList().get(n).isCheek()) {
                                            builder.append(mDatas.get(position).getList().get(n).getXZ() + ",");
                                        }
                                    }
                                    mDatas.get(position).setCs(builder.toString());
                                }
                            }
                        });
                        linAdd.addView(view3);
                    }
                }
            }

            if (type == CommonSupervisionDetilActivityActivity.TABLE_2 ||
                    type == CommonSupervisionDetilActivityActivity.TABLE_6) {
                //隐藏图片拍照
                view.findViewById(R.id.rl_detil_picture).setVisibility(View.GONE);
                view.findViewById(R.id.split_detil_picture).setVisibility(View.GONE);
            }
        } else {
            view = inflater.inflate(R.layout.adapter_item_last, parent, false);
            EditText etCheckContent = (EditText) view.findViewById(R.id.et_check_content);
            imgTakePhone = null;
            etDescription = null;
            rgCheckResult = null;
            etCheckContent.setText(mDatas.get(position).getName());
            etCheckContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mDatas.get(position).setName(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        if (imgTakePhone != null) {
            if (!TextUtils.isEmpty(getItem(position).getTp()))
                Glide.with(mContext).load(new File(PhotoViewModel.getCommonSupervisionDir(), getItem(position).getTp())).into(imgTakePhone);
                //Picasso.with(mContext).load(new File(PhotoViewModel.getCommonSupervisionDir(), getItem(position).getTp())).into(imgTakePhone);
            if (clickListener != null) {
                imgTakePhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick((ImageView) v, position);
                    }
                });
            }
        }
        if (rgCheckResult != null) {
            LinearLayout finalLinAdd = linAdd;
            ((RadioButton) rgCheckResult.findViewById(R.id.rb_check_result_yes)).setText(getItem(position).getRbCheckYes());
            ((RadioButton) rgCheckResult.findViewById(R.id.rb_check_result_no)).setText(getItem(position).getRbCheckNo());
            if (getItem(position).getRbCheckNo().equals(getItem(position).getJL())) {
                rgCheckResult.check(R.id.rb_check_result_no);
                imgTakePhone.setVisibility(View.VISIBLE);
                if (type == CommonSupervisionDetilActivityActivity.TABLE_1) {
                    if (position == 3 || position == 8) {
                        finalLinAdd.setVisibility(View.VISIBLE);
                        etDescription.setVisibility(View.GONE);
                    }
                } else if (type == CommonSupervisionDetilActivityActivity.TABLE_3) {
                    if (position == 4 || position == 5 || position == 7) {
                        finalLinAdd.setVisibility(View.VISIBLE);
                        etDescription.setVisibility(View.GONE);
                    }
                } else if (type == CommonSupervisionDetilActivityActivity.TABLE_4) {
                    if (position == 2 || position == 9) {
                        finalLinAdd.setVisibility(View.VISIBLE);
                        etDescription.setVisibility(View.GONE);
                    }
                } else if (type == CommonSupervisionDetilActivityActivity.TABLE_5) {
                    if (position == 2 || position == 5) {
                        finalLinAdd.setVisibility(View.VISIBLE);
                        etDescription.setVisibility(View.GONE);
                    }
                }
            } else {
                rgCheckResult.check(R.id.rb_check_result_yes);
                imgTakePhone.setVisibility(View.INVISIBLE);
//                etDescription.setVisibility(View.VISIBLE);
//                finalLinAdd.setVisibility(View.GONE);

            }
            rgCheckResult.setOnCheckedChangeListener((group, checkedId) -> {
                mDatas.get(position).setJL(((RadioButton) rgCheckResult.findViewById(checkedId)).getText().toString());
                switch (checkedId) {
                    case R.id.rb_check_result_yes:
                        if (itemChangeListener != null) {
                            itemChangeListener.HuiZhi();
                        }
                        imgTakePhone.setVisibility(View.INVISIBLE);
                        imgTakePhone.setClickable(false);
                        imgTakePhone.setImageBitmap(null);
                        etDescription.setText("");
                        etDescription.setInputType(InputType.TYPE_NULL);
                        finalLinAdd.setVisibility(View.GONE);
                        etDescription.setVisibility(View.VISIBLE);
                        mDatas.get(position).setCs(null);
                        mDatas.get(position).setTp(null);
                        mDatas.get(position).setQK(null);
                        getItem(position).setJL(getItem(position).getRbCheckYes());
                        if (checkChangeListener != null) {
                            checkChangeListener.deleteFlie(position);
                        }
                        break;
                    case R.id.rb_check_result_no:
                        if (itemChangeListener != null) {
                            itemChangeListener.HuiZhi();
                        }
                        imgTakePhone.setClickable(true);
                        imgTakePhone.setVisibility(View.VISIBLE);
                        imgTakePhone.setImageResource(R.drawable.take_phone);
                        etDescription.setInputType(InputType.TYPE_CLASS_TEXT);
                        getItem(position).setJL(getItem(position).getRbCheckNo());
                        if (type == CommonSupervisionDetilActivityActivity.TABLE_1) {
                            if (position == 3 || position == 8) {
                                finalLinAdd.setVisibility(View.VISIBLE);
                                etDescription.setVisibility(View.GONE);
                            }
                        } else if (type == CommonSupervisionDetilActivityActivity.TABLE_3) {
                            if (position == 4 || position == 5 || position == 7) {
                                finalLinAdd.setVisibility(View.VISIBLE);
                                etDescription.setVisibility(View.GONE);
                            }
                        } else if (type == CommonSupervisionDetilActivityActivity.TABLE_4) {
                            if (position == 2 || position == 9) {
                                finalLinAdd.setVisibility(View.VISIBLE);
                                etDescription.setVisibility(View.GONE);
                            }
                        } else if (type == CommonSupervisionDetilActivityActivity.TABLE_5) {
                            if (position == 2 || position == 5) {
                                finalLinAdd.setVisibility(View.VISIBLE);
                                etDescription.setVisibility(View.GONE);
                            }
                        }
                        break;
                }
            });
        }
        if (etDescription != null) {
            etDescription.setText(mDatas.get(position).getQK());
            etDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rgCheckResult.getCheckedRadioButtonId() == R.id.rb_check_result_yes) {
                        return;
                    }
                    if (checkChangeListener != null) {
                        checkChangeListener.inputText(position, etDescription.getText().toString());
                    }
                }
            });
        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return LAST;
        }
        return COMMON;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public interface ImgClickListener {
        void onClick(ImageView img, int position);
    }

    public interface OnCheckChangeListener {
        void deleteFlie(int position);//取消选择 ,删除图片

        void inputText(int position, String etString);
    }

    public interface ItemChangeListener {
        void HuiZhi();
    }

}
