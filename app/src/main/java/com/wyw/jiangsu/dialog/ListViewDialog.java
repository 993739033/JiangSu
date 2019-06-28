package com.wyw.jiangsu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.adapter.SetefilizeAdapter;
import com.wyw.jiangsu.db.CodeXD;
import com.wyw.jiangsu.db.LocalGreenDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ListViewDialog extends Dialog {
    public ListViewDialog(Context context,int themResId) {
        super(context,themResId);
    }

    private static class Params{
        private String title = "哈哈";
        private boolean shadow = true;
        private boolean canCancel = true;
        private OnClickListener listener;
    }

    public static class Buidle{
        private Context context;
        private Params params;

        public Buidle(Context context){
            this.context = context;
            params = new Params();
        }

        public Buidle setTitle(String title){
            params.title = title;
            return this;
        }

        public Buidle setListener(OnClickListener listener){
            params.listener = listener;
            return  this;
        }

        public Buidle setCancel(boolean canCancel){
            params.canCancel = canCancel;
            return this;
        }

        public ListViewDialog create(){
            final ListViewDialog dialog = new ListViewDialog(context,params.shadow? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_picker_first,null);
            ListView listView = (ListView) view.findViewById(R.id.lv_setefilize);
            List<CodeXD>list = LocalGreenDao.getInstance().querySterilizeWay();
            ArrayList<String> datas = new ArrayList<>();
            for (int i=0;i<list.size();i++){
                datas.add(list.get(i).getcName());
            }
            listView.setAdapter(new SetefilizeAdapter(context,datas));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (params.listener!=null){
                        params.listener.onClick(datas.get(position));
                    }
                    dialog.dismiss();

                }
            });
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvTitle.setText(params.title);
            Window window = dialog.getWindow();
            window.getDecorView().setPadding(0,0,0,0);
            WindowManager.LayoutParams arrribures = window.getAttributes();
            arrribures.height = WindowManager.LayoutParams.WRAP_CONTENT;
            WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
            arrribures.width = (int) (manager.getDefaultDisplay().getWidth()*0.7f);
            window.setGravity(Gravity.CENTER);
            dialog.setTitle(params.title);
            dialog.setCanceledOnTouchOutside(params.canCancel);
            dialog.setCancelable(params.canCancel);
            dialog.setContentView(view);
            return dialog;
        }
    }

    public interface OnClickListener{
        void onClick(String selectSterilize);
    }
}
