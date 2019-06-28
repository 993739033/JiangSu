package com.wyw.jiangsu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.LimitTimeDialogAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mnkj on 2017/12/4.
 */

//限制日期选择界面
public class LimitTimeSelectDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.recy_limit_time)
    RecyclerView recy_limit_time;

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    @BindView(R.id.tv_enter)
    TextView tv_enter;
    //标题
    private String title = "选择天数";
    //能选择时间的最大值
    private int dayCount = 5;

    private Context context;

    private int columnCount = 3;//列数

    LimitTimeDialogAdapter adapter;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private CallBack callBack;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_enter:
                String time = adapter.getSelectedTime(context);
                if (TextUtils.isEmpty(time)) return;
                callBack.enter(time);
                dismiss();
                break;
            case R.id.tv_cancel:
                callBack.cancel();
                dismiss();
                break;
        }
    }

    public interface CallBack {
        void enter(String beSelectDay);

        void cancel();

    }

    public LimitTimeSelectDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.dialog_limit_time_select);
        ButterKnife.bind(this);
        this.setTitle(title);

        hideVirtualKey();
        //按空白处隐藏对话框
        setCanceledOnTouchOutside(true);
        //初始化时间
        initRecy();
        //初始化屏幕
        initWindow();

        tv_cancel.setOnClickListener(this);
        tv_enter.setOnClickListener(this);


    }

    private void initWindow() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
        window.setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.SelecteDialogAnim);
        WindowManager manager = ((Activity) context).getWindowManager();
        Display display = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        attributes.width = (int) (WindowManager.LayoutParams.MATCH_PARENT);
        window.setAttributes(attributes);
    }


    private void initRecy() {
        adapter = new LimitTimeDialogAdapter();
        recy_limit_time.setAdapter(adapter);
        recy_limit_time.setLayoutManager(new GridLayoutManager(context, columnCount));
        ((LimitTimeDialogAdapter) recy_limit_time.getAdapter()).setDay(getDays());
    }

    //隐藏虚拟导航栏
    private void hideVirtualKey() {
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                        全屏
//                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions |= 0x00001000;
                } else {
                    uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
    }

    private List<String> getDays() {
        List<String> days = new ArrayList<>();
        for (int i = 1; i < dayCount + 1; i++) {
            days.add(i + "");
        }
        return days;
    }
}
