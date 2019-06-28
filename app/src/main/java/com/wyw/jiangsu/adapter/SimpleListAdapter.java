package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.upload.SeekBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class SimpleListAdapter extends BaseAdapter {
    private List<SeekBean> list = new ArrayList<>();
    private Context context;
    private static final int ITEM_TYPE = 0;
    private static final int ITEM_TITLE = 1;

    public SimpleListAdapter(Context context, List<SeekBean> list) {
        this.list = list;
        this.context = context;
    }

    //返回  代表某一个样式的值
    @Override
    public int getItemViewType(int position) {
        return list.get(position).getItmeType();
    }

    //返回  有几个不同类型的item
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        int type = getItemViewType(position);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        if (convertView == null) {
            switch (type) {
                case ITEM_TYPE:
                    view = View.inflate(context, R.layout.adapter_search_header, null);
                    holder2 = new ViewHolder2(view);
                    holder2.button2 = (ImageView) view.findViewById(R.id.bt_search);
                    holder2.editText2 = (EditText) view.findViewById(R.id.et_seek_content);
//                    holder2.editText2.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            ////注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
//                            mTouchItemPosition =(Integer) v.getTag();
//                            return false;
//                        }
//                    });
                    view.setTag(holder2);
                    break;
                case ITEM_TITLE:
                    view = View.inflate(context, R.layout.adapter_simple, null);
                    holder1 = new ViewHolder1(view);
                    holder1.textView1 = (TextView) view.findViewById(R.id.tv_simple);
                    holder1.textView1.setText(list.get(position).getItemTitle());
                    view.setTag(holder1);
            }
        } else {
            switch (type) {
                case ITEM_TYPE:
                    view = convertView;
                    holder2 = (ViewHolder2) view.getTag();
                    break;
                case ITEM_TITLE:
                    view = convertView;
                    holder1 = (ViewHolder1) view.getTag();
                    holder1.textView1.setText(list.get(position).getItemTitle());
            }
        }
        return view;
    }


    private class ViewHolder1 {
        private TextView textView1;

        private ViewHolder1(View view) {
            textView1 = (TextView) view.findViewById(R.id.tv_simple);
        }
    }

    private class ViewHolder2 {
        private ImageView button2;
        private EditText editText2;

        private ViewHolder2(View view) {
            button2 = (ImageView) view.findViewById(R.id.bt_search);
            editText2 = (EditText) view.findViewById(R.id.et_seek_content);
        }
    }

}
