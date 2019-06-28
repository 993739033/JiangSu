package com.wyw.jiangsu.activity.wuhaihua;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.local.LocalAnimTypeBean;
import com.wyw.jiangsu.dialog.DataSelectDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.wyw.jiangsu.R.id.bt_add_item;

/**
 * Created by wyw on 2016/12/23.
 * 动物种类 对应布局view_add_anim
 * <p>
 * 目前是只有一种动物种类(隐藏了添加按钮)
 * <p>
 * 不隐藏布局就可以实现多种动物种类
 */

public class AnimTypeModel {
    WeakReference<Activity> mActivity;
    EditText spinner;
    EditText etDeadthNumber, etInsuredNumber;
    TableLayout tlAnim;
    ArrayList<LocalAnimTypeBean> mDatas;
    DisplayMetrics metrics;
    TableRow tableRowAnimDetil;
    TextView tvRowAnimDetilBottom;
    private DataSelectDialog animDialog;//动物选择dialog


    public AnimTypeModel(Activity mActivity, List<String> mDatas) {
        this.mActivity = new WeakReference<>(mActivity);
        init(mActivity,mDatas);
    }

    private void init(Activity activity,List<String> mDatas) {
        this.mDatas = new ArrayList<>();
        spinner = (EditText) mActivity.get().findViewById(R.id.spinner_deadth_type);
        tvRowAnimDetilBottom = (TextView) mActivity.get().findViewById(R.id.tv_row_anim_detil_bottom);
        tableRowAnimDetil = (TableRow) mActivity.get().findViewById(R.id.table_row_anim_detil);
//        spinner.setAdapter(new ArrayAdapter<>(mActivity.get(),
//                android.R.layout.simple_dropdown_item_1line, mDatas));
        initDialog(activity, mDatas);//初始化动物选择
        etDeadthNumber = (EditText) mActivity.get().findViewById(R.id.et_deadth_number);
        etInsuredNumber = (EditText) mActivity.get().findViewById(R.id.et_insured_number);
        tlAnim = (TableLayout) mActivity.get().findViewById(R.id.tl_anim);
        mActivity.get().findViewById(bt_add_item).setOnClickListener(v -> {
            if (check()) {
                add();
            }
        });
        // TODO: 2016/12/27 注释这行就可以实现添加多种动物种类
        mActivity.get().findViewById(R.id.bt_add_item).setVisibility(View.GONE);
        metrics = new DisplayMetrics();
        mActivity.get().getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    private void  initDialog(Context activity,List<String> mDatas){
        animDialog = new DataSelectDialog(activity);
        animDialog.setTitle("选择病死动物类型");
        animDialog.setDatas(mDatas);
        animDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinner.setText(data);
            }

            @Override
            public void cancel() {

            }
        });

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animDialog.show();
            }
        });
    }

    /**
     * 从外部添加一条数据
     */
    public void add(String animType, String deadthNumber, String insuredNumber, boolean hidden) {
        if (hidden) {
            tvRowAnimDetilBottom.setVisibility(View.GONE);
            tableRowAnimDetil.setVisibility(View.GONE);
        }
        LocalAnimTypeBean bean = new LocalAnimTypeBean(animType, deadthNumber, insuredNumber);
        mDatas.add(bean);
        //添加布局
        TableRow row = new TableRow(mActivity.get());
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutParams(params);
//        row.addView(createSplitVerticalView());
        EditText editText1 = createEditText(spinner.getText().toString(), bean, -1);
        editText1.setBackgroundResource(R.drawable.input_bg);
        editText1.setFocusable(false);
//        row.addView(createTextView());
        row.addView(editText1);
//        row.addView(createSplitVerticalView());
        //加入死亡数
     EditText editText2 =  createEditText(bean.getDeadthNumber(), bean, 0);
     editText2.setBackgroundResource(R.drawable.input_bg);
        row.addView(editText2);
//        row.addView(createSplitVerticalView());
        //加入参保数
        EditText editText3 = createEditText(bean.getInsuredNumber(), bean, 1);
        editText3.setBackgroundResource(R.drawable.input_bg);
        row.addView(editText3);
//        row.addView(createSplitVerticalView());

        tlAnim.addView(row, tlAnim.getChildCount());
//        tlAnim.addView(createSplitHorizontalView(), tlAnim.getChildCount() - 2);
    }

    /**
     * 添加一条数据
     */
    private void add() {
        add(spinner.getText().toString(), etDeadthNumber.getText().toString(),
                etInsuredNumber.getText().toString(), false);
    }

    private EditText createEditText(String text, LocalAnimTypeBean bean, int type) {
        EditText et = new EditText(mActivity.get());
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.setMargins(5,5,5,5);
//        et.setBackgroundColor(Color.parseColor("#00000000"));
        et.setText(text);
        et.setMinHeight((int) (metrics.density * 50));
        et.setLayoutParams(params);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        et.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        et.setTextSize(16);
        et.setGravity(Gravity.CENTER);
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (type) {
                    case 0:
                        bean.setDeadthNumber(TextUtils.isEmpty(s.toString()) ? "0" : s.toString());
                        break;
                    case 1:
                        bean.setInsuredNumber(TextUtils.isEmpty(s.toString()) ? "0" : s.toString());
                        break;
                }
            }
        });
        return et;
    }

    private TextView createTextView() {
        TextView tv = new TextView(mActivity.get());
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        tv.setLayoutParams(params);
        params.gravity = Gravity.CENTER;
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.parseColor("#FF000000"));
        tv.setText(spinner.getText().toString());
        tv.setTextSize(14);
        return tv;
    }

    private View createSplitVerticalView() {
        View view = new View(mActivity.get());
        TableRow.LayoutParams params = new TableRow.LayoutParams(((int) (metrics.density * 2)), ViewGroup.LayoutParams.MATCH_PARENT);
        view.setBackgroundColor(Color.parseColor("#FF868686"));
        view.setLayoutParams(params);
        return view;
    }

    private View createSplitHorizontalView() {
        View view = new View(mActivity.get());
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ((int) (metrics.density * 2)));
        view.setBackgroundColor(Color.parseColor("#FF868686"));
        view.setLayoutParams(params);
        return view;
    }

    /**
     * 上传时 进行验证 死亡数 大于等于 参保数
     */
    public boolean uploadCheck() {
        String deadthNumber = etDeadthNumber.getText().toString();
        String insuredNumber = etInsuredNumber.getText().toString();
        if (TextUtils.isEmpty(deadthNumber) && TextUtils.isEmpty(insuredNumber))
            //0是横线
            //1是标题
            //2是横线
            deadthNumber = ((TextView) ((TableRow) tlAnim.getChildAt(3)).getChildAt(3)).getText().toString();
        insuredNumber = ((TextView) ((TableRow) tlAnim.getChildAt(3)).getChildAt(5)).getText().toString();
        return false;
    }

    /**
     * 判断新增的动物种类是否已经添加
     */
    private boolean check() {
        String animType = spinner.getText().toString();
        String deadthNumber = etDeadthNumber.getText().toString();
        String insuredNumber = etInsuredNumber.getText().toString();
        if (TextUtils.isEmpty(deadthNumber)) {
            Toast.makeText(mActivity.get(), "请输入死亡数", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getAnimType().equals(animType)) {
                Toast.makeText(mActivity.get(), "动物种类已经存在", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
//        for (int i = 0; i < tlAnim.getChildCount(); i++) {
//            if (tlAnim.getChildAt(i) instanceof TableRow
//                    && TextUtils.isEmpty(tlAnim.getChildAt(i).getTag().toString())) {
//                for (int j = 0; j < mDatas.size(); j++) {
//                    if (mDatas.get(j).getAnimType().equals(
//                            ((TextView) ((TableRow) tlAnim.getChildAt(i)).getChildAt(1)).getText().toString()))
//                        return false;
//                }
//            }
//        }
        return true;
    }

    public ArrayList<LocalAnimTypeBean> getDataVeterinariansConfirm() {
        mDatas.clear();
        LocalAnimTypeBean bean = new LocalAnimTypeBean();
        //0是横线
        //1是标题
        //2是横线
        String deadthNumber = ((EditText) ((TableRow) tlAnim.getChildAt(1)).getChildAt(1)).getText().toString();

        String insuredNumber = ((EditText) ((TableRow) tlAnim.getChildAt(1)).getChildAt(2)).getText().toString();
        bean.setDeadthNumber(TextUtils.isEmpty(deadthNumber) ? "0" : deadthNumber);
        bean.setInsuredNumber(TextUtils.isEmpty(insuredNumber) ? "0" : insuredNumber);
        mDatas.add(bean);
        return mDatas;
    }

    public ArrayList<LocalAnimTypeBean> getData() {
//        if (mDatas.size() == 0) {
//
//        }
        mDatas.clear();
        LocalAnimTypeBean bean = new LocalAnimTypeBean();
        bean.setAnimType(spinner.getText().toString());
        bean.setDeadthNumber(TextUtils.isEmpty(etDeadthNumber.getText().toString()) ? "0" : etDeadthNumber.getText().toString());
        bean.setInsuredNumber(TextUtils.isEmpty(etInsuredNumber.getText().toString()) ? "0" : etInsuredNumber.getText().toString());
        mDatas.add(bean);
        return mDatas;
    }

    public boolean check1() {
        try {

            if (etInsuredNumber.getText().toString() != null && etDeadthNumber.getText().toString() != null) {
                if (Integer.parseInt(etDeadthNumber.getText().toString()) < Integer.parseInt(etInsuredNumber.getText().toString())) {
                    Toast.makeText(MyApplication.getContext(), "参保数不能大于死亡数", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        } catch (Exception e) {
            Toast.makeText(MyApplication.getContext(), "死亡数或者参保数为空", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
