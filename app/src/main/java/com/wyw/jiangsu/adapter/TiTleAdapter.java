package com.wyw.jiangsu.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.FadingFaguiBean;

import java.util.List;

/**
 * Created by wyw on 2018/1/29.
 */

public class TiTleAdapter extends RecyclerView.Adapter<TiTleAdapter.ViewHolder> {

    private List<FadingFaguiBean.DataListBean> mData;
    private Context mContext;
    private OnItemClick onItemClick;
    private int selectedPosition = 0;// 初始选中第一行

    private int Screen_Height = 0;//屏幕高度


    public interface OnItemClick {
        void onItemClick(int position,int distance);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public TiTleAdapter(Context context, List<FadingFaguiBean.DataListBean> mData) {
        this.mContext = context;
        this.mData = mData;
        WindowManager manager = ((Activity)mContext).getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        Screen_Height= outMetrics.heightPixels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.title_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getMenuName());
        holder.llText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position,getDistance(holder));
                }
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
        if (selectedPosition == position) {
            holder.llText.setBackgroundColor(mContext.getResources().getColor(R.color.background));
            holder.title.setTextColor(mContext.getResources().getColor(R.color.tab_text_color));
        } else {
            holder.llText.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
            holder.title.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }

    private int getDistance(ViewHolder holder) {
        int distance = (holder.llText.getTop() - Screen_Height / 2+holder.llText.getHeight()/2);
        return distance;
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public FadingFaguiBean.DataListBean getItem(int i) {
        return mData.get(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private LinearLayout llText;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            llText = (LinearLayout) itemView.findViewById(R.id.ll_text);
        }
    }

    public List<FadingFaguiBean.DataListBean> getData() {
        return mData;
    }
}
