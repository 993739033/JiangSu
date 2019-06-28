package com.wyw.jiangsu.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.SlaughterBeforeBean;

import java.util.ArrayList;
import java.util.List;

public class SlaughterBeforeItmeAdapter extends BaseAdapter {

    private Handler mHandler;
    private List<SlaughterBeforeBean.DataList> list = new ArrayList<SlaughterBeforeBean.DataList>();
    private Boolean isgreen=false;//判断图标的颜色

    private Context context;
    private LayoutInflater layoutInflater;

    public SlaughterBeforeItmeAdapter(Context context, List<SlaughterBeforeBean.DataList> list, Handler mHandler) {
        this.context = context;
        this.list = list;
        this.mHandler = mHandler;
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
            convertView = layoutInflater.inflate(R.layout.slaughter_before_itme, null);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            holder.etNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    getItem(position).setNumber(s.toString());

                    int p = 0;
                    for (int i = 0; i < list.size(); i++) {
                        int number = TextUtils.isEmpty(list.get(i).getNumber()) ? 0 : Integer.parseInt(list.get(i).getNumber());
                        p = p + number;
                    }
                    Message message = new Message();
                    message.obj = p + "";
                    mHandler.sendMessage(message);
                }
            });
            holder.etCircleNo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    getItem(position).setQuanNo(s.toString());
                }
            });
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
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

        holder.spChooseYN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (context.getResources().getStringArray(R.array.sp_slaughterValue)[position].equals("异常")) {
                    holder.etCircleNo.setInputType(InputType.TYPE_CLASS_TEXT);
                    holder.etCircleNo.setFocusable(true);
                    holder.etCircleNo.setFocusableInTouchMode(true);
                    holder.etCircleNo.requestFocus();
                    holder.etNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                    object.setIsSomething("异常");
                } else {
                    holder.etCircleNo.setInputType(InputType.TYPE_NULL);
                    holder.etNumber.setInputType(InputType.TYPE_NULL);
                    holder.etCircleNo.setText("");
                    holder.etNumber.setText("");
                    object.setIsSomething("正常");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected class ViewHolder {
        private TextView tvSlaughterTitle;
        private TextView tvSlaughterPosition;
        private TextView tvSlaughterType;
        private Spinner spChooseYN;
        private EditText etCircleNo;
        private EditText etNumber;

        public ViewHolder(View view) {
            tvSlaughterTitle = (TextView) view.findViewById(R.id.tv_slaughterTitle);
            tvSlaughterPosition = (TextView) view.findViewById(R.id.tv_slaughterPosition);
            tvSlaughterType = (TextView) view.findViewById(R.id.tv_slaughterType);
            spChooseYN = (Spinner) view.findViewById(R.id.sp_chooseYN);
            etCircleNo = (EditText) view.findViewById(R.id.et_circleNo);
            etNumber = (EditText) view.findViewById(R.id.et_number);
        }
    }

}
