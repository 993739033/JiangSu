package com.wyw.jiangsu.activity.chaxun;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.KanYanbean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IVerificationLocaPresenter;
import com.wyw.jiangsu.presenter.VerificationLocaDealPressenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 现场核查查询列表详情
 * Created by Administrator on 2017/3/28.
 */
public class VerificationLocaDealActivity extends MVPActivity<VerificationLocaDealPressenter> implements IVerificationLocaPresenter {

    @BindView(R.id.tv_nameming)
    EditText tvNameming;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_type)
    EditText tvType;
    @BindView(R.id.tv_number)
    EditText tvNumber;
    @BindView(R.id.tv_idCrad)
    EditText tvIdCrad;
    @BindView(R.id.tv_icCrad)
    EditText tvIcCrad;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewGroupPhoto;
    @BindView(R.id.et_die_animal_type)
    EditText etDieAnimalType;
    @BindView(R.id.et_deadth_number)
    EditText etDeadthNumber;
    @BindView(R.id.et_insured_number)
    EditText etInsuredNumber;
    @BindView(R.id.photo_view_baodan)
    ImageView photoViewBaodan;
    @BindView(R.id.et_dead_erbiao)
    EditText etDeadErbiao;
    @BindView(R.id.photo_view_xuqin1)
    ImageView photoViewXuqin1;
    @BindView(R.id.photo_view_xuqin2)
    ImageView photoViewXuqin2;
    @BindView(R.id.photo_view_xuqin3)
    ImageView photoViewXuqin3;
    @BindView(R.id.photo_view_xuqin4)
    ImageView photoViewXuqin4;
    @BindView(R.id.photo_view_xuzhu1)
    ImageView photoViewXuzhu1;
    @BindView(R.id.photo_view_xuzhu2)
    ImageView photoViewXuzhu2;
    @BindView(R.id.photo_view_xuzhu3)
    ImageView photoViewXuzhu3;
    @BindView(R.id.photo_view_xuzhu4)
    ImageView photoViewXuzhu4;
    @BindView(R.id.et_die_cause)
    EditText etDieCause;
//    @BindView(R.id.et_die_cause2)
//    TextView etDieCause2;
    @BindView(R.id.et_accord)
EditText etAccord;
    @BindView(R.id.et_process_mode)
    TextView etProcessMode;
//    @BindView(R.id.et_process_mode2)
//    TextView etProcessMode2;
    @BindView(R.id.et_collection_location)
    TextView etCollectionLocation;
    @BindView(R.id.tv_evoke_time)
    TextView tvEvokeTime;
    @BindView(R.id.photo_view_veterinarians_hezhao)
    ImageView photoViewVeterinariansHezhao;
    @BindView(R.id.photo_view_process1)
    ImageView photoViewProcess1;
    @BindView(R.id.photo_view_process2)
    ImageView photoViewProcess2;
    @BindView(R.id.photo_view_process3)
    ImageView photoViewProcess3;
    @BindView(R.id.photo_view_process4)
    ImageView photoViewProcess4;
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
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.lin_collection_location)
    RelativeLayout lin_collection_location;
    @BindView(R.id.lin_evoke_time)
    RelativeLayout lin_evoke_time;
    private WuHaiHuaCXbean.DataListBean date;

    private KanYanbean.DataBean dataBean = new KanYanbean.DataBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_loca_deal_activity);
        ButterKnife.bind(this);

    }

    @Override
    protected VerificationLocaDealPressenter createPresenter() {
        return new VerificationLocaDealPressenter(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void bindData() {
        setTitle("现场勘验查询详情");
        date = (WuHaiHuaCXbean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
//        getIntent().getStringExtra(Constance.ACTIVITY_DATA);

        getPresenter().getDateList("V_HT_JGSYDSBC", Integer.valueOf(date.getFStId()));


    }

    public void getDate() {
        name.setText(dataBean.getXm());
        tvNameming.setText(dataBean.getFxzxm());//养殖场名称
        tvPhone.setText(dataBean.getLxdh());    //联系电话
        tvAddr.setText(dataBean.getFxxdz());    //地址
        tvType.setText(dataBean.getFyzclx());   //场所类型
        tvNumber.setText(dataBean.getFxcll());  //现存栏量
        tvIdCrad.setText(dataBean.getFsfzh());  //身份证号
        tvIcCrad.setText(dataBean.getFykth());  //一卡通号
        tvDate.setText(dataBean.getFsbrq());    //申报日期
        //少个单位
        etDieAnimalType.setText(dataBean.getFbsdwzl());//病死动物种类
        etDeadthNumber.setText(dataBean.getFsws());     //死亡数
        etInsuredNumber.setText(dataBean.getFcbs());    //参保数


        etDeadErbiao.setText(dataBean.getQDWErBiaoHao());//耳标号

        etDieCause.setText(dataBean.getFysswyy()+"  "+dataBean.getFsiwh());      //疑似死亡原因
//        etDieCause2.setText(dataBean.getFsiwh());       //非正常死亡的原因

        etProcessMode.setText(dataBean.getFclfs()+"  "+dataBean.getFclfs2());     //处理方式
//        etProcessMode2.setText(dataBean.getFclfs2());     //处理方式2
        etCollectionLocation.setText(dataBean.getFsjd());//病死畜禽移送收集点

        if (TextUtils.isEmpty(dataBean.getFsjd())){
            lin_collection_location.setVisibility(View.GONE);
            lin_evoke_time.setVisibility(View.GONE);
        }else{
            lin_collection_location.setVisibility(View.VISIBLE);
            lin_evoke_time.setVisibility(View.VISIBLE);
        }

        tvEvokeTime.setText(dataBean.getFyssj());       //移送时间
        etAccord.setText(dataBean.getSFFH());           //以上是否符合


        if (dataBean.getPictures1() != null) {
            //照片
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA1())) {
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA1())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewGroupPhoto);
            }
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA2()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA2())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewBaodan);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA3()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA3())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewXuqin1);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA4()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA4())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewXuqin2);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA5()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA5()).into(photoViewXuqin3);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA6()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA6()).into(photoViewXuqin4);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA7()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA7()).into(photoViewXuzhu1);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA8()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA8()).into(photoViewXuzhu2);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA9()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA9()).into(photoViewXuzhu3);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA10()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA10()).into(photoViewXuzhu4);

            //合照
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA11()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA11()).into(photoViewVeterinariansHezhao);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA12()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA12()).into(photoViewProcess1);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA13()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA13()).into(photoViewProcess2);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA14()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA14()).into(photoViewProcess3);
            if (!TextUtils.isEmpty(dataBean.getPictures1().getA15()))
                Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA15()).into(photoViewProcess4);


            // 养殖场负责人签名

            if (!TextUtils.isEmpty(dataBean.getPictures1().getA16())) {
                //养殖场签名
                String yangFile = jieQu(dataBean.getPictures1().getA16());
                if (yangFile.equals("jpg")) {
                    Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA16())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewQianming1);
                    tvViewQianming1Ll.setVisibility(View.GONE);
                } else {
                    tvViewQianming1.setText(dataBean.getPictures1().getA16());
                    photoViewQianming1Ll.setVisibility(View.GONE);
                }
            } else {
                tvViewQianming1Ll.setVisibility(View.VISIBLE);
                photoViewQianming1Ll.setVisibility(View.GONE);
            }

            // 官方兽医签字

            if (!TextUtils.isEmpty(dataBean.getPictures1().getA17())) {
                //养殖场签名
                String yangFile = jieQu(dataBean.getPictures1().getA17());
                if (yangFile.equals("jpg")) {
                    Glide.with(this).load(Constance.IMAGE_PATH + dataBean.getPictures1().getA17())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewQianming2);
                    tvViewQianming2Ll.setVisibility(View.GONE);
                } else {
                    tvViewQianming2.setText(dataBean.getPictures1().getA17());
                    photoViewQianming2Ll.setVisibility(View.GONE);
                }
            } else {
                tvViewQianming2Ll.setVisibility(View.VISIBLE);
                photoViewQianming2Ll.setVisibility(View.GONE);
            }


        }


    }

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void setData(KanYanbean.DataBean dataListEntity) {
        getDataBean(dataListEntity);
        getDate();
    }

    private void getDataBean(KanYanbean.DataBean dataListEntity) {
        if (dataListEntity != null) {
            this.dataBean = dataListEntity;
        } else {
            dataBean = null;
            this.dataBean = dataListEntity;
        }
    }

}
