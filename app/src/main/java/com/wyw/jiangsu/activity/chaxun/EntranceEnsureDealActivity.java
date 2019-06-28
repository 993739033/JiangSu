package com.wyw.jiangsu.activity.chaxun;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.RuChangChaXunShoujiAdapter;
import com.wyw.jiangsu.adapter.RuChangChaXunSumAdapter;
import com.wyw.jiangsu.bean.WuHaiHuaBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.bean.WuhaihuaDateBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IWuhaihuaDate;
import com.wyw.jiangsu.presenter.WuhaihuaDatePresenter;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 入场确认查询列表详情
 * Created by Administrator on 2017/3/29.
 */
public class EntranceEnsureDealActivity extends MVPActivity<WuhaihuaDatePresenter> implements IWuhaihuaDate {


    @BindView(R.id.tv_collection_car)
    TextView tvCollectionCar;
    @BindView(R.id.tv_collection_time)
    TextView tvCollectionTime;
    @BindView(R.id.tv_collection_person)
    TextView tvCollectionPerson;
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(R.id.recycler_sum)
    RecyclerView recyclerSum;
    @BindView(R.id.tv_shifou)
    TextView tvShifou;
    @BindView(R.id.img_takephoto_storage1)
    ImageView imgTakephotoStorage1;
    @BindView(R.id.img_takephoto_storage2)
    ImageView imgTakephotoStorage2;
    @BindView(R.id.photo_view_storage1)
    ImageView photoViewStorage1;
    @BindView(R.id.photo_view_storage2)
    ImageView photoViewStorage2;
    @BindView(R.id.photo_view_qianming1)
    ImageView photoViewQianming1;
    @BindView(R.id.photo_view_qianming1_ll)
    LinearLayout photoViewQianming1Ll;
    @BindView(R.id.tv_view_qianming1)
    EditText tvViewQianming1;
    @BindView(R.id.tv_view_qianming1_ll)
    LinearLayout tvViewQianming1Ll;
    @BindView(R.id.photo_view_qianming2)
    ImageView photoViewQianming2;
    @BindView(R.id.photo_view_qianming2_ll)
    LinearLayout photoViewQianming2Ll;
    @BindView(R.id.tv_view_qianming2)
    EditText tvViewQianming2;
    @BindView(R.id.tv_view_qianming2_ll)
    LinearLayout tvViewQianming2Ll;
    @BindView(R.id.photo_view_qianming3)
    ImageView photoViewQianming3;
    @BindView(R.id.photo_view_qianming3_ll)
    LinearLayout photoViewQianming3Ll;
    @BindView(R.id.tv_view_qianming3)
    EditText tvViewQianming3;
    @BindView(R.id.tv_view_qianming3_ll)
    LinearLayout tvViewQianming3Ll;
    @BindView(R.id.et_confirm_weight)
    EditText etConfirmWeight;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    private WuhaihuaDateBean.DataBean data;
    private WuHaiHuaCXbean.DataListBean listBean;

    List<WuHaiHuaBean.DataBean.DataList1Bean> list = new ArrayList<>();
    WuHaiHuaBean.DataBean bean = new WuHaiHuaBean.DataBean();
    //收集点的Adapter
    private RuChangChaXunShoujiAdapter adapter;
    //合计的Adapter
    private RuChangChaXunSumAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_ensure_deal_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected WuhaihuaDatePresenter createPresenter() {
        return new WuhaihuaDatePresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("入场确认查询详情");

//        //收集点Adapter
//        adapter = new RuChangChaXunShoujiAdapter(new ArrayList<>());
//        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));
//
//        recyclerAnimType.setAdapter(adapter);
//
//        adapter2 = new RuChangChaXunSumAdapter(new ArrayList<>());
//        recyclerSum.setLayoutManager(new FullyLinearLayoutManager(this));
//        //总计Adapter
//        recyclerSum.setAdapter(adapter2);

        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));
        adapter = new RuChangChaXunShoujiAdapter();
        recyclerAnimType.setHasFixedSize(true);
        recyclerAnimType.setNestedScrollingEnabled(false);
        recyclerAnimType.setAdapter(adapter);

        recyclerSum.setLayoutManager(new FullyLinearLayoutManager(this));
        adapter2 = new RuChangChaXunSumAdapter(new ArrayList<>());
        recyclerSum.setHasFixedSize(true);
        recyclerSum.setNestedScrollingEnabled(false);
        recyclerSum.setAdapter(adapter2);

        listBean = (WuHaiHuaCXbean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);

        getPresenter().getDateList(Integer.valueOf(listBean.getFStId()));

    }

    @Override
    public void bindListener() {

    }

    public void getDate() {

        tvCollectionCar.setText(data.getCPH());     //收集车辆
        tvCollectionTime.setText(data.getSJRQ());   //收集日期
        tvCollectionPerson.setText(data.getSJR());  //收集人
        tvShifou.setText(data.getSFCL());           //是否立即处理
        etConfirmWeight.setText(data.getQRZL());    //确认重量
        tvTotal.setText(data.getHJ());              //合计重量

        if (!TextUtils.isEmpty(data.getPictures1().getA1()))
            Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewStorage1);
        if (!TextUtils.isEmpty(data.getPictures1().getA2()))
            Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA2())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewStorage2);

        if (!TextUtils.isEmpty(data.getPictures1().getA3())) {
            //签名
            String yangFile = jieQu(data.getPictures1().getA3());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA3())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewQianming1);
                tvViewQianming1Ll.setVisibility(View.GONE);
            } else {
                tvViewQianming1.setText(data.getPictures1().getA3());
                photoViewQianming1Ll.setVisibility(View.GONE);
            }
        } else {
            tvViewQianming1Ll.setVisibility(View.VISIBLE);
            photoViewQianming1Ll.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(data.getPictures1().getA4())) {
            //签名
            String yangFile = jieQu(data.getPictures1().getA4());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA4())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewQianming2);
                tvViewQianming2Ll.setVisibility(View.GONE);
            } else {
                tvViewQianming2.setText(data.getPictures1().getA4());
                photoViewQianming2Ll.setVisibility(View.GONE);
            }
        } else {
            tvViewQianming2Ll.setVisibility(View.VISIBLE);
            photoViewQianming2Ll.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(data.getPictures1().getA5())) {
            //签名
            String yangFile = jieQu(data.getPictures1().getA5());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + data.getPictures1().getA5())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewQianming3);
                tvViewQianming3Ll.setVisibility(View.GONE);
            } else {
                tvViewQianming3.setText(data.getPictures1().getA5());
                photoViewQianming3Ll.setVisibility(View.GONE);
            }
        } else {
            tvViewQianming3Ll.setVisibility(View.VISIBLE);
            photoViewQianming3Ll.setVisibility(View.GONE);
        }
    }


    @Override
    public void setData(WuhaihuaDateBean.DataBean dataBean) {
        if (dataBean != null) {
            this.data = dataBean;
        } else {
            data = null;
            this.data = dataBean;
        }
        //收集点的Adapter
        adapter.addData(dataBean.getDataList1());

        adapter2.addData(dataBean.getDataList2());

        getDate();


    }

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }
}
