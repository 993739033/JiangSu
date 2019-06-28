package com.wyw.jiangsu.activity.chaxun;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.ShoujiChaXunAdapter;
import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.ICollectionTransportDealActivity;
import com.wyw.jiangsu.presenter.CollectionTransportDealActivityPresenter;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收集运输查询列表详情
 * Created by Administrator on 2017/3/29.
 */
public class CollectionTransportDealActivity extends MVPActivity<CollectionTransportDealActivityPresenter> implements ICollectionTransportDealActivity {
    @BindView(R.id.tv_harmlsee_name)
    TextView tvHarmlseeName;
    @BindView(R.id.tv_harmlsee_addr)
    TextView tvHarmlseeAddr;
    @BindView(R.id.tv_harmlsee_dispatch_date)
    TextView tvHarmlseeDispatchDate;
    /*@BindView(R.id.tv_number)
    TextView et_number;*/
    @BindView(R.id.tv_huishou)
    TextView tvHuishou;
    @BindView(R.id.tv_zuijia)
    TextView tvZuijia;
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(R.id.photo_view_storage_confirm)
    ImageView photoViewStorageConfirm;
    @BindView(R.id.photo_view_qianming1)
    ImageView photoViewQianming1;
    @BindView(R.id.photo_view_qianming1_ll)
    LinearLayout photoViewQianming1Ll;
    @BindView(R.id.tv_view_qianming1)
    TextView tvViewQianming1;
    @BindView(R.id.tv_view_qianming1_ll)
    LinearLayout tvViewQianming1Ll;
    @BindView(R.id.photo_view_qianming2)
    ImageView photoViewQianming2;
    @BindView(R.id.photo_view_qianming2_ll)
    LinearLayout photoViewQianming2Ll;
    @BindView(R.id.tv_view_qianming2)
    TextView tvViewQianming2;
    @BindView(R.id.tv_view_qianming2_ll)
    LinearLayout tvViewQianming2Ll;
    private WuHaiHuaCXbean.DataListBean listBean;
    private CollectionTransportDealActivityBean.DataBean data;
    private ShoujiChaXunAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_transport_deal_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected CollectionTransportDealActivityPresenter createPresenter() {
        return new CollectionTransportDealActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("收集运输查询详情");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ShoujiChaXunAdapter(new ArrayList<>());
        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerAnimType.setAdapter(adapter);

        listBean = (WuHaiHuaCXbean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        String i = listBean.getFStId();
        getPresenter().getDateList(Integer.valueOf(i));
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void setData(CollectionTransportDealActivityBean.DataBean dataList) {

        if (dataList != null) {
            this.data = dataList;
        } else {
            data = null;
            this.data = dataList;
        }
        //CollectionTransportDealActivityBean.DataBean.DataList1Bean
        adapter.addData(dataList.getDataList1());
        getDate();
    }

    public void getDate() {
        tvHarmlseeName.setText(data.getFxzxm());        //养殖场名称
        tvHarmlseeDispatchDate.setText(data.getFScTime());//分派日期
        tvHarmlseeAddr.setText(data.getFxxdz());        //地址
//        et_number.setText(String.valueOf(data.getQDSL()));//确定数量
        tvHuishou.setText(data.getHSQFH());             //回收签封号
        tvZuijia.setText(data.getJSQFH());              //加施签封号

        //收集确认
        if (!TextUtils.isEmpty(data.getPictures1().getA1()))
            Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewStorageConfirm);

        // 收集点负责人签名
        if (!TextUtils.isEmpty(data.getPictures1().getA2())) {
            String yangFile = jieQu(data.getPictures1().getA2());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA2())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewQianming1);
                tvViewQianming1Ll.setVisibility(View.GONE);
                photoViewQianming1Ll.setVisibility(View.VISIBLE);
            } else {
                tvViewQianming1.setText(data.getPictures1().getA2());
                tvViewQianming1Ll.setVisibility(View.VISIBLE);
                photoViewQianming1Ll.setVisibility(View.GONE);
            }
        }

        // 无害化处理中心收集人员
        if (!TextUtils.isEmpty(data.getPictures1().getA3())) {
            String yangFile = jieQu(data.getPictures1().getA3());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA3())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewQianming2);
                tvViewQianming2Ll.setVisibility(View.GONE);
                photoViewQianming2Ll.setVisibility(View.VISIBLE);
            } else {
                tvViewQianming2.setText(data.getPictures1().getA3());
                tvViewQianming2Ll.setVisibility(View.VISIBLE);
                photoViewQianming2Ll.setVisibility(View.GONE);
            }
        } else {
            tvViewQianming2Ll.setVisibility(View.GONE);
            photoViewQianming2Ll.setVisibility(View.VISIBLE);
        }


    }


    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }


}



