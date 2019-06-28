package com.wyw.jiangsu.activity.chaxun;

import android.content.Intent;
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
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IYangzhihuDetailActivity;
import com.wyw.jiangsu.presenter.YangzhihuDetailActivityPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wyw.jiangsu.R.id.tv_process_method;

/**
 * 申报消息接收查询列表详情
 * Created by Administrator on 2017/3/28.
 */
public class DeclareNewsAcceptDealActivity extends MVPActivity<YangzhihuDetailActivityPresenter> implements IYangzhihuDetailActivity {

    @BindView(R.id.tv_name)
    EditText tName;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_type)
    EditText tvType;
    @BindView(R.id.et_number)
    EditText et_number;
    @BindView(R.id.tv_idCrad)
    EditText tvIdCrad;
    @BindView(R.id.tv_icCrad)
    EditText tvIcCrad;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.img_takephoto_ic_id)
    ImageView imgTakephotoIcId;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewIcId;
    @BindView(R.id.et_die_animal_type)
    EditText etDieAnimalType;
    @BindView(R.id.et_deadth_number)
    EditText etDeadthNumber;
    @BindView(R.id.et_insured_number)
    EditText etInsuredNumber;
    //    @BindView(R.id.recycler_anim_type)
//    RecyclerView recyclerAnimType;
    @BindView(R.id.et_dead_erbiao)
    EditText etDeadErbiao;
    @BindView(tv_process_method)
    TextView tvProcessMethod;
    @BindView(R.id.tv_assign)
    TextView tvAssign;
    @BindView(R.id.xiangzhan)
    EditText xiangzhan;
    @BindView(R.id.lin_zhanzhang)
    RelativeLayout linZhanzhang;
    @BindView(R.id.photo_guanfangqm)
    ImageView photoGuanfangqm;
    @BindView(R.id.lin_zhanzhangqianming)
    LinearLayout linZhanzhangqianming;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tv_q_2)
    TextView tvQ2;
    @BindView(R.id.tv_kanyan_method)
    TextView tvKanyanMethod;
    @BindView(R.id.tv_sw)
    TextView tvSw;
    @BindView(R.id.tv_time_method)
    TextView tvTimeMethod;
    @BindView(R.id.lay_time)
    RelativeLayout lay_time;
    //
    private WuHaiHuaCXbean.DataListBean dataListBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.declare_news_accept_deal_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected YangzhihuDetailActivityPresenter createPresenter() {
        return new YangzhihuDetailActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("申报消息接收查询详情");
        Intent intent = getIntent();

        dataListBeen = (WuHaiHuaCXbean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        int FISid = Integer.valueOf(dataListBeen.getFStId());
        getPresenter().getFarmDeclare("V_APP_GFSYQR", FISid);

    }

    @Override
    public void bindListener() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {

    }

    @Override
    public void setData(YangzhihuDetailActivityBean.DataBean dataListEntity) {
        name.setText(dataListEntity.getXm());        //姓名
        tName.setText(dataListEntity.getFxzxm());       //养殖场名称
        tvAddr.setText(dataListEntity.getFxxdz());      //地址
        tvType.setText(dataListEntity.getFyzclx());     //养殖场类型
        et_number.setText(dataListEntity.getFxcll());    //现存栏量
        tvIdCrad.setText(dataListEntity.getFsfzh());    //身份证号
        tvIcCrad.setText(dataListEntity.getFykth());    //一卡通号
        tvDate.setText(dataListEntity.getFsbrq());      //申报日期
        etDieAnimalType.setText(dataListEntity.getFbsdwzl());//病死动物种类
        etDeadthNumber.setText(dataListEntity.getFsws());  //死亡数
        etInsuredNumber.setText(dataListEntity.getFcbs());//参保数

        etDeadErbiao.setText(dataListEntity.getQDWErBiaoHao());//耳标号
        tvProcessMethod.setText(dataListEntity.getFclfs());//处理方式
        tvAssign.setText(dataListEntity.getZPR());          //指派人

        tvKanyanMethod.setText(dataListEntity.getKYFS());
        if (dataListEntity.getKYFS().equals("上门勘验")){
            ((TextView) findViewById(R.id.tv_7)).setText("监管兽医指派");
            lay_time.setVisibility(View.GONE);
        }else{
            ((TextView) findViewById(R.id.tv_7)).setText("病死畜禽移送收集点");
            lay_time.setVisibility(View.VISIBLE);
            tvTimeMethod.setText(dataListEntity.getYSSJ());
        }
        /////修改
        //图片
        if (dataListEntity.getPictures1()!=null)
        if (!TextUtils.isEmpty(dataListEntity.getPictures1().getA1())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataListEntity.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoViewIcId);
        }
        if (dataListEntity.getPictures1()!=null)
        if (!TextUtils.isEmpty(dataListEntity.getPictures1().getA2())) {
            //签名
            String file = jieQu(dataListEntity.getPictures1().getA2());
            if (file.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + dataListEntity.getPictures1().getA2())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoGuanfangqm);
                linZhanzhang.setVisibility(View.GONE);
            } else {
                xiangzhan.setText(dataListEntity.getPictures1().getA2());
                linZhanzhangqianming.setVisibility(View.GONE);
            }

        } else {
            linZhanzhang.setVisibility(View.GONE);
//            linZhanzhangqianming.setVisibility(View.GONE);
        }



    }

    @Override
    public void deletesucess(BaseMsgBean msg) {

    }

    private String jieQu(String str) {
        File file = new File(str);
        String filename = file.getName();
        String fil = filename.substring(filename.lastIndexOf(".") + 1);
        return fil;
    }
}
