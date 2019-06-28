package com.wyw.jiangsu.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.view.recyclerview.BaseQuickAdapter;
import com.wyw.jiangsu.view.recyclerview.BaseViewHolder;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by wyw on 2016/11/1.
 */

public class HomeBeanAdapeter extends BaseQuickAdapter<HomeBean.DataList> {

    public HomeBeanAdapeter(List<HomeBean.DataList> dataList) {
        super(R.layout.adapter_home_item, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataList item) {

        if (item.getNumber() == null || item.getNumber().equals("")) {
            helper.setVisible(R.id.tv_number, false);
        } else {
            helper.setVisible(R.id.tv_number, true);
        }
        helper.setText(R.id.tv_number, item.getNumber());
//        helper.setText(R.id.tv_number,"33");
        helper.setText(R.id.tv_title, item.getFmName());
//        Log.e(TAG, item.getFmName() + "\tposition: " + helper.getLayoutPosition() + "\t内存地址: " + helper + "\ttextview内存地址:" + helper.getView(R.id.tv_title));

//        Glide.with(mContext).load(NetClient.IMG_PRE + item.getImg()).into((ImageView) helper.getView(R.id.img));

//        ((ImageView) helper.getView(R.id.img)).setImageResource(R.drawable.cesh2);
//        Glide.with(mContext).load(NetClient.IMG_PRE + item.getImg()).into((ImageView) helper.getView(R.id.img));
        ImageView iv = ((ImageView) helper.getView(R.id.img));
        iv.setTag(item.getImg());
        if (!iv.getTag().equals(item.getImg()))return;
            setSelectDrawable(iv,item.getImg()
                    , item.getImgs());

    }

    //获取点击的drawable
    private void setSelectDrawable(ImageView iv, String img, String imgs)  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StateListDrawable bg = new StateListDrawable();
                BitmapDrawable normal = null;
                if (!TextUtils.isEmpty(img)) {
                    Bitmap bt = null;
                    try {
                        bt = Glide.with(mContext)
                                .load(NetClient.IMG_PRE + img)
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    normal  = new BitmapDrawable(bt);
                }
                BitmapDrawable pressed = null;
                if (!TextUtils.isEmpty(imgs)) {
                    Bitmap bt1 = null;
                    try {
                        bt1 = Glide.with(mContext)
                                .load(NetClient.IMG_PRE + imgs)
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    pressed = new BitmapDrawable(bt1);
                }else{
                    Bitmap bt1 = null;
                    try {
                        bt1 = Glide.with(mContext)
                                .load(NetClient.IMG_PRE + img)
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    pressed = new BitmapDrawable(bt1);
                }
                bg.addState(new int[]{android.R.attr.state_pressed}, pressed);//  状态  , 设置按下的图片
                bg.addState(new int[]{}, normal);//默认状态,默认状态下的图片
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setBackgroundDrawable(bg);
                    }
                });
            }
        }).start();
    }


    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
