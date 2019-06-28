package com.wyw.jiangsu.activity.chaxun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.BreedingDetailshowBean;
import com.wyw.jiangsu.interfac.Constance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj004 on 2017/8/10.
 */

class BreedingDetailRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<BreedingDetailshowBean.DataListBean.DataList1Bean> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public BreedingDetailRecycleAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new lvViewHolder(mLayoutInflater.inflate(R.layout.adapter_check_content_query, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((lvViewHolder)holder).tvHarmlseeType.setText(mData.get(position).getName());
        if (mData.get(position).getJL().equals("是")){
            sureCheck(holder,true);
        }else {
            sureCheck(holder,false);
        }
        ((lvViewHolder)holder).tvHouzhuName.setText(mData.get(position).getQK());
        ((lvViewHolder)holder).radioGroup.setClickable(false);
        if (!TextUtils.isEmpty(mData.get(position).getTp())){
            ((lvViewHolder)holder).tvAnimalType.setVisibility(View.VISIBLE);
            Glide.with(context).load(Constance.IMAGE_PATH + mData.get(position).getTp())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(((lvViewHolder)holder).tvAnimalType);
        }
        if (position == mData.size()-2) {
            if (TextUtils.isEmpty(mData.get(position+1).getName())){
                ((BreedingDetailActivity)context).setEtOther("无");
                return;
            }
            ((BreedingDetailActivity)context).setEtOther(mData.get(position+1).getName());
        }
    }

    private boolean sureCheck(RecyclerView.ViewHolder holder, boolean ifYes) {
        ((lvViewHolder)holder).tvJiludanBianhao.setChecked(ifYes);
        ((lvViewHolder)holder).tvJiludanBianhao.setClickable(false);
        ((lvViewHolder)holder).tvJianyiTime.setChecked(!ifYes);
        ((lvViewHolder)holder).tvJianyiTime.setClickable(false);
        return ifYes;
    }

    @Override
    public int getItemCount() {
        return mData.size()==0?0:mData.size()-1;

    }

    public void setData(BreedingDetailshowBean.DataListBean dataList) {
        mData = dataList.getDataList1();
        notifyDataSetChanged();
    }


    private class lvViewHolder extends RecyclerView.ViewHolder {

         TextView tvHarmlseeType;//检查内容
         RadioButton tvJiludanBianhao;//是
         RadioButton tvJianyiTime;//否
         TextView tvHouzhuName;//描述
         ImageView tvAnimalType;//照片
         RadioGroup radioGroup;

        private lvViewHolder(View view) {
            super(view);
            tvHarmlseeType = (TextView) view.findViewById(R.id.tv_check_content);
            tvJiludanBianhao = (RadioButton) view.findViewById(R.id.rb_check_result_yes);
            tvJianyiTime = (RadioButton) view.findViewById(R.id.rb_check_result_no);
            tvHouzhuName = (TextView) view.findViewById(R.id.et_description);
            tvAnimalType = (ImageView) view.findViewById(R.id.img_takephone);
            radioGroup = (RadioGroup) view.findViewById(R.id.rg_check_result);
        }
    }

}
