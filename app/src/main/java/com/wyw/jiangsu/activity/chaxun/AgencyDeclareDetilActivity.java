package com.wyw.jiangsu.activity.chaxun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.wuhaihua.UpdateAgencyActivity;
import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAgencyDeclareDetilActivity;
import com.wyw.jiangsu.presenter.AgencyDeclareDetilActivityPresenter;
import com.wyw.jiangsu.service.UploadPictureService;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 代理申报详情
 * Created by Administrator on 2017/3/27.
 */
public class AgencyDeclareDetilActivity extends MVPActivity<AgencyDeclareDetilActivityPresenter> implements IAgencyDeclareDetilActivity {

    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.yangzhiname)
    EditText yangzhiname;
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
    ImageView photoViewIcId;
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
    @BindView(R.id.et_process_mode)
    TextView etProcessMode;
    @BindView(R.id.et_collection_location)
    TextView etCollectionLocation;
    @BindView(R.id.tv_evoke_time)
    TextView tvEvokeTime;
    @BindView(R.id.photo_view_keep_file1)
    ImageView photoViewKeepFile1;
    @BindView(R.id.photo_view_keep_file2)
    ImageView photoViewKeepFile2;
    @BindView(R.id.photo_view_keep_file3)
    ImageView photoViewKeepFile3;
    @BindView(R.id.photo_view_keep_file4)
    ImageView photoViewKeepFile4;
    @BindView(R.id.lin_keep_file)
    LinearLayout linKeepFile;
    @BindView(R.id.photo_fuzerenqm)
    ImageView photoFuzerenqm;
    @BindView(R.id.lin_yangzhiqianming)
    LinearLayout linYangzhiqianming;
    @BindView(R.id.tv_yangzhi)
    TextView tvYangzhi;
    @BindView(R.id.lin_yangzhi)
    RelativeLayout linYangzhi;
    @BindView(R.id.photo_guanfangqm)
    ImageView photoGuanfangqm;
    @BindView(R.id.lin_guanfang)
    LinearLayout linGuanfang;
    @BindView(R.id.tv_shouyiqian)
    TextView tvShouyiqian;
    @BindView(R.id.lin_shouyi)
    RelativeLayout linShouyi;
    @BindView(R.id.et_process_mode2)
    TextView etProcessMode2;
    //    @BindView(R.id.et_die_cause2)
//    TextView etDieCause2;
    @BindView(R.id.lin_evoke_time)
    RelativeLayout lin_evoke_time;
    @BindView(R.id.lin_collection_location)
    RelativeLayout lin_collection_location;
    @BindView(R.id.name)
    EditText name;
    private WuHaiHuaCXbean.DataListBean dataListBean;
    private PopupWindow popupwindow;
    private TextView tv_update;
    private TextView tv_del;
    private AgencyDeclareDetilActivityBean.DataBean dateben;
    private String uuid;
    private static String fstid;
    private static AgencyDeclareDetilActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agency_declare_detil_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected AgencyDeclareDetilActivityPresenter createPresenter() {
        return new AgencyDeclareDetilActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("代理申报详情");
        presenter = getPresenter();
        Intent intent = getIntent();
        dataListBean = (WuHaiHuaCXbean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
        fstid = dataListBean.getFStId();
        if (dataListBean.getFSuserId().equals(getPresenter().getUserId()) && dataListBean.getFSm2().equals("")) {
            tvYonghuMingzi.setVisibility(View.VISIBLE);
            tvYonghuMingzi.setText("更多");
        }

        int Fstid = Integer.valueOf(dataListBean.getFStId());
        getPresenter().getFarmDeclare("HT_JGSYDSB", Fstid);

        initmPopupWindowView();
    }

    @Override
    public void bindListener() {
        tvYonghuMingzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vHomiew) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    popupwindow.showAsDropDown(appBar, appBar.getWidth() - popupwindow.getWidth(), 0);
                }
            }
        });
    }

    @Override
    public void onError() {

    }

    @Override
    public void refreshNone() {

    }

    @Override
    public void setData(AgencyDeclareDetilActivityBean.DataBean dataEntity) {
        settex(dataEntity);

    }

    @Override
    public void deletesucess(BaseMsgBean msg) {
        if (msg.getErrorCode() == 0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    //赋值
    private void settex(AgencyDeclareDetilActivityBean.DataBean dataEntity) {
        dateben = dataEntity;
        uuid = dateben.getPictures1().getA1().split("_")[0];
        yangzhiname.setText(dataEntity.getFxzxm()); //养殖场名称
        tvAddr.setText(dataEntity.getFxxdz());      //地址
        tvType.setText(dataEntity.getFyzclx());     //养殖场类型
        tvNumber.setText(dataEntity.getFxcll());    //现存栏量
        name.setText(dataEntity.getXm());    //姓名
        tvIdCrad.setText(dataEntity.getFsfzh());    //身份证号
        tvIcCrad.setText(dataEntity.getFykth());    //一卡通号
        tvDate.setText(dataEntity.getFsbrq());      //申报日期
        etDieAnimalType.setText(dataEntity.getFbsdwzl());//病死动物种类
        etDeadthNumber.setText(dataEntity.getFsws());    //死亡数
        etInsuredNumber.setText(dataEntity.getFcbs()); //参保数
        etDeadErbiao.setText(dataEntity.getQDWErBiaoHao()); //耳标号
        etProcessMode.setText(dataEntity.getFclfs());       //处理方式
        etProcessMode2.setText(dataEntity.getFclfs2());     //处理方式2
        etCollectionLocation.setText(dataEntity.getFsjd()); //病死畜禽移送收集点
        if (TextUtils.isEmpty(dataEntity.getFsjd())){
            lin_collection_location.setVisibility(View.GONE);
            lin_evoke_time.setVisibility(View.GONE);
        }else{
            lin_collection_location.setVisibility(View.VISIBLE);
            lin_evoke_time.setVisibility(View.VISIBLE);
        }
        tvEvokeTime.setText(dataEntity.getFyssj());         //移送时间
        etDieCause.setText(dataEntity.getFysswyy()+"   "+dataEntity.getFsiwh());//疑似死亡原因
//        etDieCause2.setText(dataEntity.getFsiwh()); //非正常死亡

        if(("集中处理").equals(dataEntity.getFclfs())){
            linKeepFile.setVisibility(View.GONE);
        }
        //图片
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA1())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewIcId);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA2())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA2())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewBaodan);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA3())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA3())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin1);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA4())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA4())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin2);
        }

        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA5())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA5())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin3);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA6())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA6())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuqin4);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA7())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA7())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu1);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA8())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA8())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu2);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA9())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA9())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu3);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA10())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA10())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewXuzhu4);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA11())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA11())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile1);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA12())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA12())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile2);
        }
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA13())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA13())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile3);
        }


        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA14())) {
            Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA14())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoViewKeepFile4);
        }

        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA15())) {
            //养殖场签名
            String yangFile = jieQu(dataEntity.getPictures1().getA15());
            if (yangFile.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA15())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoFuzerenqm);
                linYangzhi.setVisibility(View.GONE);
            } else {
                tvYangzhi.setText(dataEntity.getPictures1().getA15());
                linYangzhiqianming.setVisibility(View.GONE);
            }
        } else {
            linYangzhi.setVisibility(View.GONE);
//            linYangzhiqianming.setVisibility(View.GONE);
        }
        //兽医签名
        if (!TextUtils.isEmpty(dataEntity.getPictures1().getA16())) {
            String file = jieQu(dataEntity.getPictures1().getA16());
            if (file.equals("jpg")) {
                Glide.with(this).load(Constance.IMAGE_PATH + dataEntity.getPictures1().getA16())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(photoGuanfangqm);
                linShouyi.setVisibility(View.GONE);
            } else {

                tvShouyiqian.setText(dataEntity.getPictures1().getA16());
                linGuanfang.setVisibility(View.GONE);
            }
        } else {
            linGuanfang.setVisibility(View.GONE);
        }
    }

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Intent intent = getIntent();
            dataListBean = (WuHaiHuaCXbean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
            int fsttid = Integer.valueOf(dataListBean.getFStId());
            getPresenter().getFarmDeclare("HT_JGSYDSB", fsttid);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //初始化PopWindowView
    public void initmPopupWindowView() {


        View customView = getLayoutInflater().inflate(R.layout.popview_item,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, 300, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupwindow.setBackgroundDrawable(getResources().getDrawable(R.color.white));

        popupwindow.setOutsideTouchable(true);

        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();

                }

                return false;
            }
        });
        tv_update = ((TextView) customView.findViewById(R.id.tv_update));
        tv_del = ((TextView) customView.findViewById(R.id.tv_del));
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeleteDialog();
                popupwindow.dismiss();
            }
        });
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgencyDeclareDetilActivity.this, UpdateAgencyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, dateben);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
                popupwindow.dismiss();
            }
        });
        if (!dataListBean.getFSm1().equals("移动端")) {
            tv_update.setVisibility(View.GONE);
        }

    }

    private void openDeleteDialog() {

        getPresenter().deteleData(dateben.getFStId(), uuid);

    }

    public static class InnerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(UploadPictureService.NEW_LIFEFORM_DETECTED2))
                if (presenter != null)
                    presenter.getFarmDeclare("HT_JGSYDSB", Integer.valueOf(fstid));
        }
    }

}
