package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wyw.jiangsu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */

public class SetefilizeAdapter extends BaseAdapter {
   private ArrayList<String> list;
    private Context context;
    public SetefilizeAdapter(Context context,ArrayList<String>list){
        this.context = context;
        this.list = list;
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
        ViewHolder holder = null;
        if (convertView == null){
            convertView = View.inflate(context,R.layout.adapter_simple,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView textView;
        public ViewHolder(View view){
            textView = (TextView) view.findViewById(R.id.tv_simple);
        }
    }
}
