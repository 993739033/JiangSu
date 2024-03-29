package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wyw.jiangsu.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mnkj004 on 2017/10/27.
 */

public class MySimpleAdapter extends SimpleAdapter {

    private int positionChosen = -1;

    public MySimpleAdapter(Context context, List<HashMap<String, String>> list, int id, String[] strings, int[] ints) {
        super(context,list,id,strings,ints);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        convertView.findViewById(R.id.iv).setVisibility(positionChosen ==position?View.VISIBLE:View.GONE);
        ((TextView)convertView.findViewById(R.id.tv)).setTextColor(positionChosen ==position
                ?convertView.getResources().getColor(R.color.adressdialog_list_text_color)
                :convertView.getResources().getColor(R.color.black));

        return convertView;
    }

    public void setFlag(int position){
        positionChosen = position;
        notifyDataSetChanged();
    }
}
