package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.dialog.ErBiaoEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wyw on 2016/10/17.
 */
public class ErBiaoAdapter extends BaseAdapter {
    private List<ErBiaoEntity> mDatas;
    private LayoutInflater mInflater;

    public ErBiaoAdapter(Context mContext, List<ErBiaoEntity> mDatas) {
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public List<ErBiaoEntity> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<ErBiaoEntity> mDatas) {
        if (this.mDatas == null) {
            this.mDatas = mDatas;
        } else {
            this.mDatas.clear();
            this.mDatas.addAll(mDatas);
        }
        notifyDataSetChanged();
    }
    public void add(List<ErBiaoEntity> mDatas) {
        if (this.mDatas == null) {
            this.mDatas = mDatas;
        } else {
            this.mDatas.addAll(mDatas);
        }
        notifyDataSetChanged();
    }
    public void add(ErBiaoEntity mDatas) {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<>();
        }
        this.mDatas.add(mDatas);
        notifyDataSetChanged();
    }
    public void add(String preSeven, String middleFive, String start) {
        ErBiaoEntity entity = new ErBiaoEntity(false, preSeven, middleFive, start);
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<>();
        }
        this.mDatas.add(entity);
        notifyDataSetChanged();
    }
    public void add(String preSeven, String middleFive, String start,String end) {
        ErBiaoEntity entity = new ErBiaoEntity(false, preSeven, middleFive, start,end);
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<>();
        }
        this.mDatas.add(entity);
        notifyDataSetChanged();
    }

    public boolean delete() {
        boolean flag = false;
        Iterator<ErBiaoEntity> iterator = mDatas.iterator();
        while (iterator.hasNext()) {
            ErBiaoEntity bean = iterator.next();
            if (bean.isSelect()) {
                iterator.remove();
//                mDatas.remove(bean);
                flag = true;
            }
        }
        notifyDataSetChanged();
        return flag;
    }

    /**
     * 检测耳标号是否存在 单号录入
     * 单号 全连号
     * @param preSeven
     * @param middleFive
     *  @return true 存在
     *          false 不存在
     */
    public boolean checkExistSingle(String preSeven, String middleFive, String start) {
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getPreSeven().equals(preSeven) && 
                    mDatas.get(i).getMiddleFive().equals(middleFive) &&
                    mDatas.get(i).getStart().equals(start)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 检测耳标号是否存在 全号录入
     * 单号 全连号
     * @param preSeven
     * @param middleFive
     *  @return true 存在
     *          false 不存在
     */
    //// TODO: 2016/10/17  
    public boolean checkExistQuan(String preSeven, String middleFive, String start,int count) {
        for (int i = 0; i < mDatas.size(); i++) {
            for (int j = 0; j < count; j++) {
            }
            if (mDatas.get(i).getPreSeven().equals(preSeven) && 
                    mDatas.get(i).getMiddleFive().equals(middleFive) &&
                    mDatas.get(i).getStart().equals(start)) {
                return true;
            }
        }
        return false;
    }

    //获取当前数量
    public int getCurrentCount() {
        Iterator<ErBiaoEntity> iterator = mDatas.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            ErBiaoEntity bean = iterator.next();
            count+= Integer.valueOf(bean.getEnd()) - Integer.valueOf(bean.getStart()) + 1;
        }
        return count;
    }
    @Override
    public int getCount() {
        return mDatas == null? 0:mDatas.size();
    }

    @Override
    public ErBiaoEntity getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_erbiaolist_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getItem(position).setSelect(isChecked);
            }
        });
        viewHolder.checkbox.setChecked(getItem(position).isSelect());
        if (TextUtils.isEmpty(getItem(position).getEnd())) {
            String s = getItem(position).getPreSeven() + getItem(position).getMiddleFive() + getItem(position).getStart();
            viewHolder.tv.setText(s);
        } else {
            String s = getItem(position).getPreSeven() + getItem(position).getMiddleFive() 
                    + getItem(position).getStart() + "-" + getItem(position).getEnd();
            viewHolder.tv.setText(s);
        }
        return convertView;
    }

    private static class ViewHolder {
        CheckBox checkbox;
        TextView tv;

        public ViewHolder(View view) {
            checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
