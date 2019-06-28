package com.wyw.jiangsu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.dialog.datepickdialog.EasyPickerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mnkj on 2017/12/4.
 */

//数据选择Dialog
public class DataSelectDialog extends Dialog {

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    @BindView(R.id.tv_enter)
    TextView tv_enter;

    @BindView(R.id.method_pick_view)
    EasyPickerView pick_view;

    @BindView(R.id.tv_title)
    TextView tv_title;


    public void setDatas(String[] datas) {
        if(datas!=null){
            this.datas.clear();
            this.datas.addAll(Arrays.asList(datas));
            if (pick_view!=null) {
                pick_view.setDataList(this.datas);
            }
        }
    }
    public void setDatas(List<String> datas) {
        if(datas!=null) {
            this.datas.clear();
            this.datas.addAll(datas);
            if (pick_view!=null) {
                pick_view.setDataList(this.datas);
            }
        }
    }

    public List<String> getDatas() {
        return datas;
    }

    List<String> datas=new ArrayList<>();

    public int getNowIndex() {
        return nowIndex;
    }

    private int nowIndex = -1;

    public void setTitle(String title) {
        this.title = title;
    }

    //标题
    private String title = "默认标题";

    private Context context;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    CallBack callBack;

    public interface CallBack {
        void enter(String data);

        void cancel();

    }


    public DataSelectDialog(@NonNull Context context) {
        super(context,R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.dialog_disinfect_method_select);
        ButterKnife.bind(this);
        tv_title.setText(title);
        hideVirtualKey();
        //按空白处隐藏对话框
        setCanceledOnTouchOutside(true);
        //初始化屏幕
        initWindow();
        //初始化数据
        initView();

    }

    private void initView() {
        pick_view.setDataList(datas);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callBack==null) return;
                callBack.cancel();
            }
        });
        tv_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callBack==null) return;
                if (datas==null||datas.size()==0) {
                    nowIndex = -1;
                    if (callBack!=null)
                    callBack.enter("请选择");
                }else {
                    nowIndex = pick_view.getCurIndex() % datas.size();
                    String select = datas.get(Math.abs(pick_view.getCurIndex() % datas.size()));
                    if (callBack!=null)
                    callBack.enter(select);
                }
            }
        });
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

}
