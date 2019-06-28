package com.wyw.jiangsu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.SpinerPopWindow;
import com.wyw.jiangsu.adapter.ErBiaoAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wyw on 2016/10/17.
 * 显示耳标号的dialog
 */
public class ErBiaoDialog extends Dialog implements View.OnClickListener {
    private final int QuanLianHao = 0; //全连号输入
    private final int SingleNumber = 1;//单号输入
    private final int SectionNumber = 2;//阶段连号输入

    private String[] spinnerAdapterString = {"单号输入", "阶段连号输入", "全连号输入"};
    private EditText et_spinner;
    //tvSingleInput  包含了单号输入和全连号输入
    //tvSection 阶段连号输入
    private TextView tvNumberInput, tvNumberTotal, tvSingleInput, tvSection;
    private EditText etPreSeven, etMiddleFive, etSingle, etSection;
    private Button btDelete, btAdd, btCancel, btConfirm;
    private LinearLayout linSection;
    private ListView listview;
    //current 当前类型 currentCount 当前录入的数量
    private int currentType;

    public int getCurrentCount() {
        return currentCount;
    }

    private int currentCount;
    private int maxCount;
    private ErBiaoAdapter adapter;
    private OnDataSelectListener listener;
    private InputFilter[] threeInputFilter, LongInputFilter;//限制长度3个字符 83个字符
    private boolean limitNumber = true;//默认限制

    private SpinerPopWindow<String> spw_method;

    public ErBiaoDialog(Context context, String numberTotal, OnDataSelectListener listener) {
        super(context);
        this.listener = listener;
        init(numberTotal);
    }

    /**
     * 不限制死数量
     *
     * @param context
     * @param numberTotal
     * @param listener
     */
    public ErBiaoDialog(Context context, String numberTotal, OnDataSelectListener listener, boolean limitNumber) {
        super(context);
        this.listener = listener;
        this.limitNumber = limitNumber;
        init(numberTotal);
    }

    public void show(String numberTotal) {
        super.show();
        tvNumberTotal.setText(numberTotal.replace(" ", ""));
        maxCount = Integer.valueOf(numberTotal.replace(" ", ""));
    }

    public ErBiaoDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ErBiaoDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init(String numberTotal) {
        // 使用自己的布局文件
        View parent = LayoutInflater.from(getContext()).inflate(R.layout.dialog_erbiaohao, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(parent);
//        setTitle("耳标号录入");
        findViews(parent);
        bindData(numberTotal);
        bindListener();
    }

    private void bindData(String numberTotal) {
        tvNumberInput.setText("0");
        tvNumberTotal.setText(numberTotal);
        maxCount = Integer.valueOf(numberTotal);
        currentCount = 0;
        adapter = new ErBiaoAdapter(getContext(), new ArrayList<ErBiaoEntity>());
        listview.setAdapter(adapter);
        threeInputFilter = new InputFilter[]{new NameLengthFilter(3)};
        LongInputFilter = new InputFilter[]{new NameLengthFilter(83)};
        spw_method = new SpinerPopWindow<String>(getContext(), Arrays.asList(spinnerAdapterString), new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spw_method.dismiss();
                String text = spinnerAdapterString[position];
                et_spinner.setText(text);
                // 判断录入的方式
                if (text.equals("阶段连号输入")) {
                    currentType = SectionNumber;
                    linSection.setVisibility(View.VISIBLE);
                    tvSingleInput.setText("后3位开始号:");
                    etSingle.setFilters(threeInputFilter);
                    tvSection.setText("后3位终止号:");
                } else if (text.equals("全连号输入")) {
                    currentType = QuanLianHao;
                    linSection.setVisibility(View.GONE);
                    tvSingleInput.setText("后3位开始号:");
                    etSingle.setFilters(threeInputFilter);
                } else {
                    //单号输入
                    currentType = SingleNumber;
                    linSection.setVisibility(View.GONE);
                    tvSingleInput.setText("耳标号后3位:");
                    etSingle.setFilters(LongInputFilter);
                }
                etSingle.setText("");
                etSection.setText("");
            }
        });
        spw_method.setWidth(400);
    }

    private void bindListener() {
        btAdd.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btConfirm.setOnClickListener(this);
        et_spinner.setOnClickListener(this);

    }

    private void findViews(View parent) {
        et_spinner = (EditText) parent.findViewById(R.id.ErSpinner);
        // 初始化
        listview = (ListView) parent.findViewById(R.id.listview);
        tvNumberInput = (TextView) parent.findViewById(R.id.tv_number_input);
        tvNumberTotal = (TextView) parent.findViewById(R.id.tv_totalnumber);

        tvSingleInput = (TextView) parent.findViewById(R.id.tv_single);
        tvSection = (TextView) parent.findViewById(R.id.tv_section);
        linSection = (LinearLayout) parent.findViewById(R.id.lin_section);
        etPreSeven = (EditText) parent.findViewById(R.id.et_pre_seven);
        etMiddleFive = (EditText) parent.findViewById(R.id.et_middle_five);
        etSingle = (EditText) parent.findViewById(R.id.et_single);
        etSection = (EditText) parent.findViewById(R.id.et_section);
        btAdd = (Button) parent.findViewById(R.id.bt_add);
        btDelete = (Button) parent.findViewById(R.id.bt_delete);
        btCancel = (Button) parent.findViewById(R.id.bt_cancel);
        btConfirm = (Button) parent.findViewById(R.id.bt_confirm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                if (check()) {
                    add();
                    //移动至底部
                    listview.smoothScrollToPosition(adapter.getmDatas().size() - 1);
                }
                break;
            case R.id.bt_delete:
                if (adapter.delete()) {
                    currentCount = adapter.getCurrentCount();
                    tvNumberInput.setText(String.valueOf(currentCount));
                } else {
                    Toast.makeText(getContext(), "请先选择条目,然后删除", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_cancel:
                dismiss();
                break;
            case R.id.bt_confirm:
                if (limitNumber && currentCount < maxCount) {
                    Toast.makeText(getContext(), "输入的耳标号数量不够", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (listener != null) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < adapter.getmDatas().size(); i++) {
                        ErBiaoEntity entity = adapter.getmDatas().get(i);
                        builder.append(entity.getPreSeven());
                        builder.append(entity.getMiddleFive());
                        if (TextUtils.isEmpty(entity.getEnd())) {
                            builder.append(entity.getStart());
                        } else {
                            builder.append(entity.getStart());
                            builder.append("-");
                            builder.append(entity.getEnd());
                        }
                        builder.append(",");
                    }
                    //去除最后一个逗号
                    if (!TextUtils.isEmpty(builder.toString())) {
                        builder.delete(builder.lastIndexOf(","), builder.length());
                        listener.dataSelect(builder.toString());
                    }
                    dismiss();
                }
                break;
            case R.id.ErSpinner:
                spw_method.showAsDropDown(et_spinner);
                break;
        }
    }


    /**
     * 耳标号合法验证  长度
     */
    public boolean check() {
        String preSeven = etPreSeven.getText().toString().trim(); // 前7位
        String middleFive = etMiddleFive.getText().toString().trim(); // 中间5位
        String start = etSingle.getText().toString().trim(); // 开始3位
        String end = etSection.getText().toString().trim(); // 终止3位
        int startInt = 0; // 开始3位
        int endInt = 0; // 终止3位
        if (preSeven.length() != 7) {
            Toast.makeText(getContext(), "耳标号前7位：请输入7位数字!",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (middleFive.length() != 5) {
            Toast.makeText(getContext(), "耳标号中间5位：请输入5位数字!",
                    Toast.LENGTH_LONG).show();
            return false;
        } else if (adapter.getmDatas().size() >= maxCount || currentCount >= maxCount) {
            Toast.makeText(getContext(), "输入数量不能超过最大数量", Toast.LENGTH_SHORT).show();
            return false;
        } else if (currentType == SectionNumber) {
            if (end.length() != 3) {
                Toast.makeText(getContext(), "后3位的终止号：请输入3位数字!",
                        Toast.LENGTH_LONG).show();
                return false;
            } else if (start.length() != 3) {
                Toast.makeText(getContext(), "后3位的开始号：请输入3位数字!",
                        Toast.LENGTH_LONG).show();
                return false;
            } else if (start.contains(",")) {
                Toast.makeText(getContext(), "后3位的开始号必须是全数字",
                        Toast.LENGTH_LONG).show();
                return false;
            }
            startInt = Integer.valueOf(start);
            endInt = Integer.valueOf(end);
            //阶段号
            int count = endInt - startInt + 1;//当前输入的数量
            int currentCount = this.currentCount + count;//
            if (currentCount > maxCount) {
                Toast.makeText(getContext(), "输入数量不能超过最大数量",
                        Toast.LENGTH_LONG).show();
                return false;
            } else {
                if (startInt == endInt) {
                    Toast.makeText(getContext(), "请使用单号输入", Toast.LENGTH_SHORT).show();
                    return false;
                }
                for (int i = 0; i < adapter.getCount(); i++) {
                    ErBiaoEntity bean = adapter.getmDatas().get(i);
                    if (bean.getPreSeven().equals(preSeven) &&
                            bean.getMiddleFive().equals(middleFive)) {
                        if (Integer.valueOf(start) > Integer.valueOf(end)) {
                            Toast.makeText(getContext(), "开始号不能大于结束号！",
                                    Toast.LENGTH_LONG).show();
                            return false;
                        } else if (start.equals(end)) {
                            Toast.makeText(getContext(), "开始号等于结束号！请使用单号录入",
                                    Toast.LENGTH_LONG).show();
                            return false;
                        } else if (TextUtils.isEmpty(bean.getEnd())) {
                            //如果前七位 和 中 五位在adapter中有匹配的 判断是否存在
                            //如果adapter中的该条目是单耳标
                            if (startInt <= bean.getIntStart() && bean.getIntStart() <= endInt) {
                                Toast.makeText(getContext(), "输入耳标号已经存在", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        } else {
                            //全连号或者阶段连号
                            if ((bean.getIntStart() <= startInt && startInt <= bean.getIntEnd()) ||
                                    (bean.getIntStart() <= endInt && endInt <= bean.getIntEnd())) {
                                Toast.makeText(getContext(), "输入耳标号已经存在", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                    }
                }
            }
        } else if (currentType == SingleNumber) {
            if (start.endsWith(",")) start = start.substring(0, start.length() - 1);
            String[] startArr = start.split(",");
            for (int i = 0; i < startArr.length; i++) {
                if (startArr[i].length() != 3) {
                    Toast.makeText(getContext(), "请输入333,444,555这种格式,最多输入21个耳标号", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            //数量判断
            if (startArr.length + currentCount > maxCount) {
                Toast.makeText(getContext(), "超过最大耳标数量", Toast.LENGTH_SHORT).show();
                return false;
            }
            //重复判断
            for (int i = 0; i < startArr.length - 1; i++) {
                for (int j = i + 1; j < startArr.length; j++) {
                    if (startArr[i].equals(startArr[j])) {
                        Toast.makeText(getContext(), "输入的耳标号有重复" + startArr[i], Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            //单号输入
            for (int j = 0; j < startArr.length; j++) {
                startInt = Integer.valueOf(startArr[j]);
                for (int i = 0; i < adapter.getCount(); i++) {
                    ErBiaoEntity bean = adapter.getmDatas().get(i);
                    if (bean.getPreSeven().equals(preSeven) &&
                            bean.getMiddleFive().equals(middleFive)) {
                        if (TextUtils.isEmpty(bean.getEnd())) {
                            //如果前七位 和 中 五位在adapter中有匹配的 判断是否存在
                            //如果adapter中的该条目是单耳标
                            if (startInt == bean.getIntStart()) {
                                Toast.makeText(getContext(), "输入耳标号已经存在", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        } else {
                            //全连号或者阶段连号
                            if (bean.getIntStart() <= startInt && startInt <= bean.getIntEnd()) {
                                Toast.makeText(getContext(), "输入耳标号已经存在", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                    }
                }
            }

        } else if (currentType == QuanLianHao) {
            if (TextUtils.isEmpty(start)) {
                Toast.makeText(getContext(), "数量为空", Toast.LENGTH_LONG).show();
            } else {
                startInt = Integer.valueOf(start);
            }
            //全连号
            if (maxCount == 1) {
                Toast.makeText(getContext(), "请使用单号输入", Toast.LENGTH_SHORT).show();
                return false;
            } else if (start.length() != 3) {
                Toast.makeText(getContext(), "耳标号开始3位：请输入3位数字!",
                        Toast.LENGTH_LONG).show();
                return false;
            }
            if (String.valueOf(startInt + maxCount - 1).length() >= 4) {
                Toast.makeText(getContext(), "耳标号后三位大于1000,请选择阶段号录入", Toast.LENGTH_SHORT).show();
                return false;
            }
//            endInt = startInt + maxCount - 1 ;
//            for (int i = 0; i < adapter.getCount(); i++) {
//                ErBiaoEntity bean = adapter.getmDatas().get(i);
//                if (bean.getPreSeven().equals(preSeven) &&
//                        bean.getMiddleFive().equals(middleFive)) {
//                    if (TextUtils.isEmpty(bean.getEnd())){
//                        //如果前七位 和 中 五位在adapter中有匹配的 判断是否存在
//                        //如果adapter中的该条目是单耳标
//                        if (startInt <= bean.getIntStart() && bean.getIntStart() <= endInt) {
//                            Toast.makeText(getContext(), "全连号有重复的", Toast.LENGTH_SHORT).show();
//                            return false;
//                        }
//                    }else{
//                        //全连号或者阶段连号
//                        if ((bean.getIntStart() <= startInt && startInt  <= bean.getIntEnd()) ||
//                                (bean.getIntStart() <= endInt && endInt <= bean.getIntEnd())) {
//                            Toast.makeText(getContext(), "全连号有重复的", Toast.LENGTH_SHORT).show();
//                            return false;
//                        }
//                    }
//                }
//            }
//            if (String.valueOf(startInt + maxCount - 1).length() >= 4) {
////                Toast.makeText(getContext(), "耳标号后三位大于1000,请选择阶段号录入", Toast.LENGTH_SHORT).show();
////                return false;
//                //判断重复 包括两部分 1.startInt-999 第二个是mid+1 000 -  如果mid + 1 进位
//            } else {
//
//            }
        }

        return true;
    }

    /**
     * 耳标号唯一验证 与listview中进行对比
     */
    public void add() {
        // 将输入框中的值
        String preSeven = etPreSeven.getText().toString().trim(); // 前7位
        String middleFive = etMiddleFive.getText().toString().trim(); // 中间5位
        String start = etSingle.getText().toString().trim(); // 开始3位
        String end = etSection.getText().toString().trim(); // 终止3位
        if (currentType == SingleNumber) {
            //单号
            for (String s : start.split(",")) {
                adapter.add(preSeven, middleFive, s);
                currentCount++;
            }
//            if (!adapter.checkExistSingle(preSeven, middleFive, start)) {
//                adapter.add(preSeven, middleFive, start);
//                currentCount++;
//            } else {
//                Toast.makeText(getContext(), "输入耳标号已经存在", Toast.LENGTH_SHORT).show();
//            }
        } else if (currentType == QuanLianHao) {
            //全连号
            //清空数据源,然后设置进去
            adapter.getmDatas().clear();
            String s = String.valueOf(Integer.valueOf(start) + maxCount - 1);
            if (s.length() == 1) {
                s = "00" + s;
            } else if (s.length() == 2) {
                s = "0" + s;
            }
            adapter.add(preSeven, middleFive, start, s);
            currentCount = maxCount;
        } else {
            //阶段输入
            int count = Integer.valueOf(end) - Integer.valueOf(start) + 1;
            currentCount += count;

            adapter.add(preSeven, middleFive, start, end);
        }
        tvNumberInput.setText(String.valueOf(currentCount));
        etSingle.setText("");
        etSection.setText("");
    }

    public interface OnDataSelectListener {
        void dataSelect(String text);
    }


    private class NameLengthFilter implements InputFilter {
        int MAX_EN;// 最大英文/数字长度 一个汉字算两个字母
        String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字

        public NameLengthFilter(int mAX_EN) {
            super();
            MAX_EN = mAX_EN;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (dend >= MAX_EN && dstart != MAX_EN - 1) {
                Toast.makeText(getContext(), "超过了" + MAX_EN + "个字符",
                        Toast.LENGTH_SHORT).show();
                return "";
            }
            return source;
//            int destCount = dest.toString().length()
//                    + getChineseCount(dest.toString());
//            int sourceCount = source.toString().length()
//                    + getChineseCount(source.toString());
//            if (destCount + sourceCount > MAX_EN) {
//                Toast.makeText(getContext(), "超过了"+MAX_EN+"个字符",
//                        Toast.LENGTH_SHORT).show();
//                return "";
//
//            } else {
//                return source;
//            }
        }

        private int getChineseCount(String str) {
            int count = 0;
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }
            return count;
        }
    }


}
