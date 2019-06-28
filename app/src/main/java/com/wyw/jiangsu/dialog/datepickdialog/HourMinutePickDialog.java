package com.wyw.jiangsu.dialog.datepickdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Date: 2017-06-07 09:49
 */
public class HourMinutePickDialog extends Dialog implements View.OnClickListener {
    /**
     * 回调的时间类型
     */
    public static final String TIME_TYPE = "yyyy.MM.dd HH";
    /**
     * 两个滑动 选择器
     */
    private EasyPickerView mEpvHour;
    private EasyPickerView mEpvMinute;

    private int HourIndex = -3, minuteIndex = -3;//记录当前选择的时间位置

    private int count = 0;//添加条件 反正初始化年月时影响天数
    private int maxd;

    public HourMinutePickDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }


    public List<String> getmHourList() {
        return mHourList;
    }

    public void setmHourList(List<String> mHourList) {
        this.mHourList = mHourList;
    }

    public List<String> getmMinuteList() {
        return mMinuteList;
    }

    public void setmMinuteList(List<String> mMinuteList) {
        this.mMinuteList = mMinuteList;
    }

    /**
     * 滑动选择器对应的集合
     */
    private List<String> mHourList;
    private List<String> mMinuteList;

    private Context mContext;

    public int getmCurrentHour() {
        return mCurrentHour;
    }

    public int getmCurrentMinute() {
        return mCurrentMinute;
    }

    /**
     * 当前时间 年月日
     */
    private int mCurrentHour;
    private int mCurrentMinute;
    /**
     * dialog标题栏
     */
    private String mTitle="时间区间选择";

    /**
     * 构造,传入标题 和 上下文
     */
    public HourMinutePickDialog(Context context, @Nullable String title) {
        super(context, R.style.MyDialog);
        this.mContext = context;
        if(title!=null)
        this.mTitle = title;
        initTime();//默认为当前日期
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_time_hour_minute_picker);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面控件的事件
        initEvent();

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.SelecteDialogAnim);
        WindowManager manager = ((Activity) mContext).getWindowManager();
        Display display = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        attributes.width = (int) (WindowManager.LayoutParams.MATCH_PARENT); // 宽度设置为屏幕的0.65，根据实际情况调整
        window.setAttributes(attributes);
    }

    /**
     * 初始化控件
     * <p>
     * 设置dialog宽度
     */
    private void initView() {
        mEpvHour = (EasyPickerView) findViewById(R.id.epv_hour);
        mEpvMinute = (EasyPickerView) findViewById(R.id.epv_minute);

        initEasyPickerView(mEpvHour, mHourList);
        initEasyPickerView(mEpvMinute, mMinuteList);

        String hour ,minute;
        hour = (mCurrentHour <= 9 ? "0" + mCurrentHour : mCurrentHour + "");
        minute = (mCurrentMinute <= 9 ? "0" + mCurrentMinute : mCurrentMinute + "");

        //设置当分钟
        if (mMinuteList.contains(minute)) {
            if (mMinuteList.contains(minute)) {
                int index = mMinuteList.indexOf(minute);
                mEpvMinute.moveTo(index);
            }
        }

        //设置当小时
        if (mHourList.contains(hour)) {
            if (mHourList.contains(hour)) {
                int index = mHourList.indexOf(hour);
                mEpvHour.moveTo(index);
            }
        }
    }


    /**
     * 设置滑动列表数据
     * 设置标题
     * 设置点击事件
     */
    private void initEvent() {

        //设置标题
        ((TextView) findViewById(R.id.tv_title)).setText(mTitle);
        //设置下方三个按钮监听
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_yes).setOnClickListener(this);

    }

    /**
     * 设置数据
     */
    private void initEasyPickerView(EasyPickerView epview, List<String> dateList) {
        epview.setDataList(dateList);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_cancel) {
            this.dismiss();
        } else if (id == R.id.tv_yes) {
            this.dismiss();
            if (mTimePickListener != null) {
                mTimePickListener.choseTime(
                                mHourList.get(mEpvHour.getCurIndex() % mHourList.size())
                                + " " + mMinuteList.get(mEpvMinute.getCurIndex() % mMinuteList.size()) + "时"
                );
            }
            if (mTimePickListener1 != null) {
                mTimePickListener1.getTime(mHourList.get(mEpvHour.getCurIndex() % mHourList.size()), mMinuteList.get(mEpvMinute.getCurIndex() % mMinuteList.size()));
            }
        }
    }

    //初始化为当前时间
    public HourMinutePickDialog initTime() {
        Calendar now = Calendar.getInstance();
        mCurrentHour = now.get(Calendar.HOUR_OF_DAY);
        mCurrentMinute = now.get(Calendar.MINUTE);
        return initTime( mCurrentHour + "", mCurrentMinute + "");
    }

    /**
     * 初始化时间
     */
    public HourMinutePickDialog initTime( String hour, String minute) {
        int h = 0,m=0;
        try {
            h = Integer.parseInt(hour);
            if (h > 24) {
                throw new NumberFormatException();
            }
            if (m > 60) {
                throw new NumberFormatException();
            }
            m = Integer.parseInt(minute);
        } catch (NumberFormatException e) {
            Toast.makeText(mContext, "时间输入有误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        mCurrentHour = h;
        mCurrentMinute = m;

        mHourList = new ArrayList<>();
        mMinuteList = new ArrayList<>();

        for (int i = 0; i <= 23; i++) {
            if (i < 10) {
                mHourList.add("0" + i + "");
            } else {
                mHourList.add(i + "");
            }
        }
        for (int i = 0; i <= 59; i++) {
            if (i < 10) {
                mMinuteList.add("0" + i + "");
            } else {
                mMinuteList.add(i + "");
            }
        }

        return this;
    }

    /**
     * 时间选择回调监听
     */
    private TimePickListener mTimePickListener;

    public void setmTimePickListener1(TimePickListener1 mTimePickListener1) {
        this.mTimePickListener1 = mTimePickListener1;
    }

    private TimePickListener1 mTimePickListener1;


    public interface TimePickListener {
        void choseTime(String time);
    }

    public interface TimePickListener1 {
        void getTime( String hour, String minute);
    }


    public void setTimePickListener(TimePickListener timePickListener) {
        mTimePickListener = timePickListener;
    }
}
