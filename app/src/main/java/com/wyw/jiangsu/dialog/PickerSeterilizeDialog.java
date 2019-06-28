package com.wyw.jiangsu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.db.CodeXD;
import com.wyw.jiangsu.db.LocalGreenDao;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wyw on 2016/11/2.
 * 消毒方式dialog
 */

public class PickerSeterilizeDialog extends Dialog {
    private Params params;

    public PickerSeterilizeDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void setParams(PickerSeterilizeDialog.Params params) {
        this.params = params;
    }

    public interface OnDateSelectedListener {
        void onDateSelected(String[] dates);
    }

    private static final class Params {
        private boolean shadow = true;
        private boolean canCancel = true;
        private String title = "请选择地区";
        private List<CodeXD> sererilizeWayData;
        private OnDateSelectedListener callback;
    }


    public static class Builder {
        private final Context context;
        private final PickerSeterilizeDialog.Params params;

        public Builder(Context context) {
            this.context = context;
            params = new PickerSeterilizeDialog.Params();
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }


        public Builder setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
            params.callback = onDateSelectedListener;
            return this;
        }

        private List<CodeXD> buildsererilizeWayData() {
            //获取数据库,读取数据
           List<CodeXD> list = LocalGreenDao.getInstance().querySterilizeWay();
            return list;
        }

        public PickerSeterilizeDialog create() {
            final PickerSeterilizeDialog dialog = new PickerSeterilizeDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_picker_first, null);
//            view.findViewById(R.id.tv_first).setVisibility(View.GONE);
//            view.findViewById(R.id.tv_second).setVisibility(View.GONE);
//            view.findViewById(R.id.tv_third).setVisibility(View.GONE);
            new Thread(){
                @Override
                public void run() {
                }
            }.start();

            ((TextView) view.findViewById(R.id.tv_title)).setText(params.title);
//            view.findViewById(R.id.tv_finish).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onSearch(View v) {
//                    dialog.dismiss();
//                    params.callback.onDateSelected(getCurrDateValues());
//                }
//            });
            Window win = dialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            win.setGravity(Gravity.BOTTOM);
            win.setWindowAnimations(R.style.Animation_Bottom_Rising);

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(params.canCancel);
            dialog.setCancelable(params.canCancel);

            dialog.setParams(params);
            return dialog;
        }

        private List<String> getList(List<CodeXD> list) {
            if (list == null) return new ArrayList<>();
            List<String> result = new ArrayList<>();
            for (CodeXD codeXD : list) {
                result.add(codeXD.getcName());
            }
            return result;
        }
    }
}
