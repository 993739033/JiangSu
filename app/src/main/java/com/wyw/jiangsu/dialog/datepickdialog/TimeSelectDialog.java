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
public class TimeSelectDialog extends Dialog implements View.OnClickListener {
    /**
     * 回调的时间类型
     */
    public static final String TIME_TYPE = "yyyy.MM.dd HH";
    /**
     * 四个滑动 选择器
     */
    private EasyPickerView mEpvYear;
    private EasyPickerView mEpvMonth;
    private EasyPickerView mEpvDay;
    private EasyPickerView mEpvHour;

    private EasyPickerView mEpvMinute;

    private TextView tv_minute;//分钟

    public void setNeedMinute(boolean needMinute) {
        this.needMinute = needMinute;
    }

    private boolean needMinute=false;//是否需要分钟


    private int minYear = 1990;//默认最小年
    private int maxYearoff = 20;//默认最大年与当前时间间隔

    private int yearIndex = -3, monthIndex = -3;//记录当前选择的时间位置

    private int count = 0;//添加条件 反正初始化年月时影响天数
    private int maxd;

    public TimeSelectDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public List<String> getmYearList() {
        return mYearList;
    }

    public void setmYearList(List<String> mYearList) {
        this.mYearList = mYearList;
    }

    public List<String> getmMonthList() {
        return mMonthList;
    }

    public void setmMonthList(List<String> mMonthList) {
        this.mMonthList = mMonthList;
    }

    public List<String> getmDayList() {
        return mDayList;
    }

    public void setmDayList(List<String> mDayList) {
        this.mDayList = mDayList;
    }

    public List<String> getmHourList() {
        return mHourList;
    }

    public void setmHourList(List<String> mHourList) {
        this.mHourList = mHourList;
    }

    /**
     * 滑动选择器对应的集合
     */
    private List<String> mYearList;
    private List<String> mMonthList;
    private List<String> mDayList;
    private List<String> mHourList;
    private List<String> mMinuteList;

    private Context mContext;

    public int getmCurrentYear() {
        return mCurrentYear;
    }

    public int getmCurrentMonth() {
        return mCurrentMonth;
    }

    public int getmCurrentDay() {
        return mCurrentDay;
    }

    public int getmCurrentHour() {
        return mCurrentHour;
    }

    public int getmCurrentMinute() {
        return mCurrentMinute;
    }

    /**
     * 当前时间 年月日 时 分
     */
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDay;
    private int mCurrentHour;
    private int mCurrentMinute;
    /**
     * dialog标题栏
     */
    private String mTitle="时间选择";

    /**
     * 构造,传入标题 和 上下文
     */
    public TimeSelectDialog(Context context, @Nullable String title) {
        super(context, R.style.MyDialog);
        this.mContext = context;
        if(title!=null)
        this.mTitle = title;
        initTime();//默认为当前日期
    }


    //获取当前时间 2009-10-21
    public String getNowTime(){
        String time = getmCurrentYear() + "-" + getmCurrentMonth() + "-" + getmCurrentDay();
        return time;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_time_picker);
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
        mEpvYear = (EasyPickerView) findViewById(R.id.epv_year);
        mEpvMonth = (EasyPickerView) findViewById(R.id.epv_month);
        mEpvDay = (EasyPickerView) findViewById(R.id.epv_day);
        mEpvHour = (EasyPickerView) findViewById(R.id.epv_hour);
        mEpvMinute = (EasyPickerView) findViewById(R.id.epv_minute);
        tv_minute = (TextView) findViewById(R.id.tv_minute);

        initEasyPickerView(mEpvYear, mYearList);
        initEasyPickerView(mEpvMonth, mMonthList);
        initEasyPickerView(mEpvDay, mDayList);
        initEasyPickerView(mEpvHour, mHourList);
        initEasyPickerView(mEpvMinute, mMinuteList);


        //是否需要分钟
        if(needMinute){
            mEpvMinute.setVisibility(View.VISIBLE);
            tv_minute.setVisibility(View.VISIBLE);
        }else{
            mEpvMinute.setVisibility(View.GONE);
            tv_minute.setVisibility(View.GONE);
        }

        String year, month, day, hour,minute;
        year = mCurrentYear + "";
        month = (mCurrentMonth <= 9 ? "0" + mCurrentMonth : mCurrentMonth + "");
        day = (mCurrentDay <= 9 ? "0" + mCurrentDay : mCurrentDay + "");
        hour = (mCurrentHour <= 9 ? "0" + mCurrentHour : mCurrentHour + "");
        minute = (mCurrentMinute <= 9 ? "0" + mCurrentMinute : mCurrentMinute + "");

        //设置当年
        if (mYearList.contains(year)) {
            if (mYearList.contains(year)) {
                int index = mYearList.indexOf(year);
                mEpvYear.moveTo(index);
            }
        }

        //设置当月
        if (mMonthList.contains(month)) {
            if (mMonthList.contains(month)) {
                int index = mMonthList.indexOf(month);
                mEpvMonth.moveTo(index);
            }
        }
        //设置当日
        if (mDayList.contains(day)) {
            if (mDayList.contains(day)) {
                int index = mDayList.indexOf(day);
                mEpvDay.moveTo(index);
            }
        }
        //设置当小时
        if (mHourList.contains(hour)) {
            if (mHourList.contains(hour)) {
                int index = mHourList.indexOf(hour);
                mEpvHour.moveTo(index);
            }
        }

        //设置当分钟
        if (mMinuteList.contains(minute)) {
            if (mMinuteList.contains(minute)) {
                int index = mHourList.indexOf(minute);
                mEpvMinute.moveTo(index);
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

        mEpvYear.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {

            }

            @Override
            public void onScrollFinished(int curIndex) {
                yearIndex = curIndex;
                changeDayList();
            }
        });

        mEpvMonth.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {

            }

            @Override
            public void onScrollFinished(int curIndex) {
                monthIndex = curIndex;
                changeDayList();
            }
        });


    }

    /**
     * 设置数据
     */
    private void initEasyPickerView(EasyPickerView epview, List<String> dateList) {
        epview.setDataList(dateList);
    }

    //根据年月 获取日期
    private void changeDayList() {
        count++;
        if (count <= 2) {
            return;
        }
        if (mEpvDay == null) return;
        if (yearIndex < 0 || monthIndex < 0) return;
        String selectYear = mYearList.get(yearIndex);
        String selectMonth = mMonthList.get(monthIndex);

        try {
            int day = getDaysByYearMonth(Integer.parseInt(selectYear), Integer.parseInt(selectMonth));
            mDayList.clear();
            for (int i = 1; i <= day; i++) {
                mDayList.add(i + "");
            }
            initEasyPickerView(mEpvDay, mDayList);//重新初始化日期

        } catch (Exception e) {
            Toast.makeText(mContext, "时间选取控件发生错误", Toast.LENGTH_SHORT).show();
        }

    }

    //获取年月对应的
    private int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_cancel) {
            this.dismiss();
        } else if (id == R.id.tv_yes) {
            this.dismiss();
            mCurrentYear = Integer.parseInt(mYearList.get(mEpvYear.getCurIndex() % mYearList.size()));
            mCurrentMonth = Integer.parseInt(mMonthList.get(mEpvMonth.getCurIndex() % mMonthList.size()));


            mCurrentDay = Integer.parseInt( mDayList.get(mEpvDay.getCurIndex() % mDayList.size()));
            mCurrentHour = Integer.parseInt( mHourList.get(mEpvHour.getCurIndex() % mHourList.size()));


            if (mTimePickListener != null) {
                mTimePickListener.choseTime(

                        mYearList.get(mEpvYear.getCurIndex() % mYearList.size()) + "/" +
                                mMonthList.get(mEpvMonth.getCurIndex() % mMonthList.size()) + "/" +
                                mDayList.get(mEpvDay.getCurIndex() % mDayList.size())
                                + " " + mHourList.get(mEpvHour.getCurIndex() % mHourList.size()) + "时"
                );
            }
            if (mTimePickListener1 != null) {
                mTimePickListener1.getTime(mYearList.get(mEpvYear.getCurIndex() % mYearList.size())
                        , mMonthList.get(mEpvMonth.getCurIndex() % mMonthList.size())
                        , mDayList.get(mEpvDay.getCurIndex() % mDayList.size()), mHourList.get(mEpvHour.getCurIndex() % mHourList.size()));
            }

            if (mTimePickListener2 != null) {
                mTimePickListener2.getTime(mYearList.get(mEpvYear.getCurIndex() % mYearList.size())
                        , mMonthList.get(mEpvMonth.getCurIndex() % mMonthList.size())
                        , mDayList.get(mEpvDay.getCurIndex() % mDayList.size()),
                        mHourList.get(mEpvHour.getCurIndex() % mHourList.size())
                        ,mMinuteList.get(mEpvMinute.getCurIndex() % mMinuteList.size()));
            }
        }
    }


    //初始化为当前时间
    public TimeSelectDialog initTime() {
        Calendar now = Calendar.getInstance();
        mCurrentYear = now.get(Calendar.YEAR);
        mCurrentMonth = (now.get(Calendar.MONTH) + 1);
        mCurrentDay = now.get(Calendar.DAY_OF_MONTH);
        mCurrentHour = now.get(Calendar.HOUR_OF_DAY);
        mCurrentMinute = now.get(Calendar.MINUTE);
        return initTime(mCurrentYear + "", mCurrentMonth + "", mCurrentDay + "", mCurrentHour + "",mCurrentMinute+"");
    }

    /**
     * 初始化时间
     */
    public TimeSelectDialog initTime(String year, String month, String day, String hour, String minute) {
        int y = 0, m = 0, d = 0, h = 0,M=0;
        try {
            y = Integer.parseInt(year);
            m = Integer.parseInt(month);
            maxd = getDaysByYearMonth(y, m);
            d = Integer.parseInt(day);
            h = Integer.parseInt(hour);
            M = Integer.parseInt(minute);
            if (m > 12||m<0) {
                throw new NumberFormatException();
            }

            if (d > maxd) {
                throw new NumberFormatException();
            }

            if (M > 59||m<0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(mContext, "时间输入有误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        mCurrentYear = y;
        mCurrentMonth = m;
        mCurrentDay = d;
        mCurrentHour = h;
        mCurrentMinute = M;

        mYearList = new ArrayList<>();
        mMonthList = new ArrayList<>();
        mDayList = new ArrayList<>();
        mHourList = new ArrayList<>();
        mMinuteList = new ArrayList<>();


        for (int i = minYear; i <= y + maxYearoff; i++) {
            mYearList.add(i + "");
        }


        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                mMonthList.add("0" + i + "");
            } else {
                mMonthList.add(i + "");
            }
        }

        for (int i = 1; i <= maxd; i++) {
            if (i < 10) {
                mDayList.add("0" + i + "");
            } else {
                mDayList.add(i + "");
            }
        }

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

    public void setmTimePickListener2(TimePickListener2 mTimePickListener2) {
        this.mTimePickListener2 = mTimePickListener2;
    }

    private TimePickListener2 mTimePickListener2;


    public interface TimePickListener {
        void choseTime(String time);
    }

    public interface TimePickListener1 {
        void getTime(String year, String month, String day, String hour);
    }

    public interface TimePickListener2 {
        void getTime(String year, String month, String day, String hour,String minute);
    }


    public void setTimePickListener(TimePickListener timePickListener) {
        mTimePickListener = timePickListener;
    }
}
