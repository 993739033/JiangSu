package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wyw.jiangsu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj on 2017/12/4.
 */

public class LimitTimeDialogAdapter extends RecyclerView.Adapter {
    public List<String> getDay() {
        return days;
    }

    private int index = -1;//被点击的日期的下标

    public void setDay(List<String> day) {
        this.days = day;
        notifyDataSetChanged();
    }

    List<String> days = new ArrayList<>();//日期集合

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_limit_time_select_item, parent, false);
        return new holder(view);
    }

    public String getSelectedTime(Context context) {
        if (index < 0) {
            Toast.makeText(context, "日期选择不能为空!", Toast.LENGTH_SHORT).show();
            return "";
        }
       String time= days.get(index);
        return time;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String item = days.get(position);
        ((holder) holder).btn.setTag(position);
        if (((int) ((LimitTimeDialogAdapter.holder) holder).btn.getTag()) != position) return;
        ((holder) holder).btn.setText(item);
        ((holder) holder).btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();
            }
        });
        if (position == index) {
            ((holder) holder).btn.setBackgroundResource(R.drawable.dialog_limit_time_beselected);
            ((holder) holder).btn.setTextColor(Color.WHITE);

        } else {
            ((holder) holder).btn.setBackgroundResource(R.drawable.dialog_limit_time_normal);
            ((holder) holder).btn.setTextColor(Color.BLACK);

        }
    }

    @Override
    public int getItemCount() {
        return days == null ? 0 : days.size();
    }

    private class holder extends RecyclerView.ViewHolder {
        public Button btn;

        public holder(View itemView) {
            super(itemView);
            btn = (Button) itemView.findViewById(R.id.btn_day);
        }
    }
}
