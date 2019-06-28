package com.wyw.jiangsu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wyw.jiangsu.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PrintConfigDialog extends Dialog {
    @BindView(R.id.dialog_cancel)
    TextView dialog_cancel;
    @BindView(R.id.dialog_enter)
    TextView dialog_enter;
    @BindView(R.id.et_x)
    EditText et_x;
    @BindView(R.id.et_y)
    EditText et_y;
    Context mContext;
    String titleContent;//标题

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    CallBack callBack;
    public interface CallBack{
        void enter(String x,String y);
    }
    public PrintConfigDialog(Context context) {
        super(context, R.style.MyDialog1);
        mContext = context;
    }

    public void setTitle(String title){
        titleContent = title;
    }

    protected PrintConfigDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public PrintConfigDialog(Context context, @StyleRes int themeResId) {
        super(context, R.style.MyDialog1);
        mContext = context;
    }


    private void initView() {
        View parent = LayoutInflater.from(getContext()).inflate(R.layout.layout_print_config_dialog, null);
        ButterKnife.bind(this,parent);
        setContentView(parent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        if (callBack != null) {
            dialog_enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.enter(et_x.getText().toString(),et_y.getText().toString());
                }
            });
            dialog_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancel();
                }
            });

        }
    }
}

