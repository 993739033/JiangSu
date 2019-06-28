package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ZaiqianCheckQueryBean;
import com.wyw.jiangsu.bean.SlaughterBeforeBean;

import java.util.ArrayList;
import java.util.List;

public class QuerySlaughterBeforeItmeAdapter extends BaseAdapter {

    private List<SlaughterBeforeBean.DataList> list = new ArrayList<SlaughterBeforeBean.DataList>();

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ZaiqianCheckQueryBean.DataListBean.DataList1Bean> dataList1;
    private boolean isgreen=false;//判断坐上角图标颜色

    public QuerySlaughterBeforeItmeAdapter(Context context, List<SlaughterBeforeBean.DataList> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setmDatas(List<SlaughterBeforeBean.DataList> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addAll(List<SlaughterBeforeBean.DataList> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<SlaughterBeforeBean.DataList> getDateList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SlaughterBeforeBean.DataList getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.query_slaughter_before_itme, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((SlaughterBeforeBean.DataList) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(SlaughterBeforeBean.DataList object, ViewHolder holder, int position) {
        holder.tvSlaughterTitle.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(object.getTitle())) {
            holder.tvSlaughterTitle.setVisibility(View.GONE);
        } else {
            holder.tvSlaughterTitle.setText(object.getTitle());
            if (object.getTitle().contains("个体检查")) {
                isgreen = true;
            }else{
                isgreen = false;
            }
        }

        if (isgreen) {
            holder.tvSlaughterPosition.setBackgroundResource(R.drawable.tag1);
        }else{
            holder.tvSlaughterPosition.setBackgroundResource(R.drawable.tag2);
        }

        holder.tvSlaughterPosition.setText(object.getPosition());
        holder.tvSlaughterType.setText(object.getType());
        holder.spChooseYN.setText(object.getIsSomething());
        holder.etCircleNo.setText(object.getQuanNo());
        holder.etNumber.setText(object.getNumber());


        object.setIsSomething(holder.spChooseYN.getText().toString());
        object.setQuanNo(holder.etCircleNo.getText().toString());
        object.setNumber(holder.etNumber.getText().toString());
        notifyDataSetChanged();

    }

    public void setMData(List<ZaiqianCheckQueryBean.DataListBean.DataList1Bean> dataList1) {
        if (this.dataList1 == null) {
            this.dataList1 = dataList1;
        } else {
            this.dataList1.clear();
            this.dataList1.addAll(dataList1);
        }
        notifyDataSetChanged();
    }

    protected class ViewHolder {
        private TextView tvSlaughterTitle;
        private TextView tvSlaughterPosition;
        private TextView tvSlaughterType;
        private TextView etCircleNo;
        private TextView etNumber;
        private TextView spChooseYN;

        public ViewHolder(View view) {
            tvSlaughterTitle = (TextView) view.findViewById(R.id.tv_slaughterTitle);
            tvSlaughterPosition = (TextView) view.findViewById(R.id.tv_slaughterPosition);
            tvSlaughterType = (TextView) view.findViewById(R.id.tv_slaughterType);
            spChooseYN = (TextView) view.findViewById(R.id.sp_chooseYN);
            etCircleNo = (TextView) view.findViewById(R.id.et_circleNo);
            etNumber = (TextView) view.findViewById(R.id.et_number);
        }
    }

}
