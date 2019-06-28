package com.wyw.jiangsu.activity.wuhaihua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IYangzhihuDetailActivity;
import com.wyw.jiangsu.presenter.YangzhihuDetailActivityPresenter;
import com.wyw.jiangsu.service.UploadPictureService;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 查询养殖场申报表详情
 */
public class YangzhihuDetailActivity extends MVPActivity<YangzhihuDetailActivityPresenter> implements IYangzhihuDetailActivity {


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
    @BindView(R.id.yzhname)
    EditText yzhname;
    @BindView(R.id.dz)
    TextView dz;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.yzhlx)
    EditText yzhlx;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.numleixing)
    TextView numleixing;
    @BindView(R.id.shenfennum)
    EditText shenfennum;
    @BindView(R.id.yikanum)
    EditText yikanum;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.anmilzl)
    EditText anmilzl;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewIcId;
    @BindView(R.id.swnum)
    EditText swnum;
    @BindView(R.id.cbnum)
    EditText cbnum;
    @BindView(R.id.erbiao)
    EditText erbiao;
    @BindView(R.id.bignyin)
    EditText bignyin;
    @BindView(R.id.chuzhuqianzhi)
    ImageView chuzhuqianzhi;
    @BindView(R.id.lin_qianimg)
    LinearLayout linQianimg;
    @BindView(R.id.tv_qianming)
    EditText tvQianming;
    @BindView(R.id.lin_tvqian)
    RelativeLayout linTvqian;
    @BindView(R.id.feizhengchang_death)
    EditText feizhengchangDeath;
    @BindView(R.id.ll_feizhangchang)
    RelativeLayout llFeizhangchang;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.et_cl_method)
    EditText et_cl_method;
    private WuHaiHuaCXbean.DataListBean dataListBeen;
    YangzhihuDetailActivityBean.DataBean dateben = new YangzhihuDetailActivityBean.DataBean();
    private PopupWindow popupwindow;
    private TextView tv_update;
    private TextView tv_del;
    private String uuid;
    private static int fStId;
    private static YangzhihuDetailActivityPresenter mpresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yangzhihu_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected YangzhihuDetailActivityPresenter createPresenter() {
        return new YangzhihuDetailActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("养殖场（户）申报表");
        Intent intent = getIntent();
        dataListBeen = (WuHaiHuaCXbean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
        if (dataListBeen.getZPID()!=null&&dataListBeen.getFSuserId()!=null) {
            if (dataListBeen.getFSuserId().equals(getPresenter().getUserId()) && dataListBeen.getZPID().equals("")) {
                tvYonghuMingzi.setVisibility(View.VISIBLE);
                tvYonghuMingzi.setText("更多");
            }
        }


        mpresenter = getPresenter();
        fStId = Integer.valueOf(dataListBeen.getFStId());
        getPresenter().getFarmDeclare("HT_BSDWSWSB", fStId);
        initmPopupWindowView();
    }

    @Override
    public void bindListener() {
     /*   getTitltView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //2fbfc016-8db6-4fe8-9ae1-a225835e60ce_A1.jpg
                Glide.with(YangzhihuDetailActivity.this).load(Constance.IMAGE_PATH + "2fbfc016-8db6-4fe8-9ae1-a225835e60ce_A1.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache( true )
                        .into(photoViewIcId);
                linTvqian.setVisibility(View.GONE);
            }
        });*/
        tvYonghuMingzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    public void setData(YangzhihuDetailActivityBean.DataBean dataListEntity) {
        dateben = dataListEntity;


        phone.setText(dateben.getLxdh());   //联系电话
        dz.setText(dateben.getFxxdz());     //地址
        name.setText(dateben.getXm());     //地址
        yzhlx.setText(dateben.getFyzclx()); //养殖场类型
        yzhname.setText(dataListEntity.getFxzxm());//养殖场名称
        number.setText(dateben.getFxcll()); //现存栏量
        numleixing.setText(dateben.getFdw1());//单位
        shenfennum.setText(dateben.getFsfzh()); //身份证号
        yikanum.setText(dataListEntity.getFykth()); //一卡通号
        date.setText(dateben.getFsbrq());               //申报日期
        anmilzl.setText(dateben.getFbsdwzl());          //病死动物种类
        swnum.setText(dateben.getFsws());               //死亡数
        cbnum.setText(dateben.getFcbs());               //参保数
        erbiao.setText(dateben.getQDWErBiaoHao());      //耳标号
        bignyin.setText(dateben.getFysswyy());          //疑似死亡原因
        et_cl_method.setText(dateben.getFclfs());  //新增处理方式
        if (dateben.getFysswyy().equals("正常死亡")) {
            llFeizhangchang.setVisibility(View.GONE);
        } else {
            llFeizhangchang.setVisibility(View.VISIBLE);
            feizhengchangDeath.setText(dateben.getFsiwh()); //非正常死亡原因
        }
        uuid = dataListEntity.getPictures1().getA1().split("_")[0];
//        dataListEntity.getPictures1().getA2();
        //图片赋值
        if (!TextUtils.isEmpty(dataListEntity.getPictures1().getA1())) {        //身份证和一卡通照片
            Glide.with(YangzhihuDetailActivity.this).load(Constance.IMAGE_PATH + dataListEntity.getPictures1().getA1())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(photoViewIcId);
        } else {
            Glide.with(YangzhihuDetailActivity.this).load("")
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                    .into(photoViewIcId);
        }

        if (!TextUtils.isEmpty(dataListEntity.getPictures1().getA2())) {            //养殖场负责人签名
            String file = jieQu(dataListEntity.getPictures1().getA2());
            if (file.equals("jpg")) {
                //2fbfc016-8db6-4fe8-9ae1-a225835e60ce_A1.jpg
                Glide.with(YangzhihuDetailActivity.this).load(Constance.IMAGE_PATH + dataListEntity.getPictures1().getA2())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(chuzhuqianzhi);
                linTvqian.setVisibility(View.GONE);
            } else {
                tvQianming.setText(dataListEntity.getPictures1().getA2());
                linQianimg.setVisibility(View.GONE);
            }
        } else {
            linTvqian.setVisibility(View.GONE);
//            linQianimg.setVisibility(View.GONE);
        }
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

    private String jieQu(String str) {
        File f = new File(str);
        String fiename = f.getName();
        String fie = fiename.substring(fiename.lastIndexOf(".") + 1);
        return fie;
    }

    //初始化PopWindowView
    public void initmPopupWindowView() {


        View customView = getLayoutInflater().inflate(R.layout.popview_item,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, 250, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupwindow.setOutsideTouchable(true);
        popupwindow.setBackgroundDrawable(getResources().getDrawable(R.color.white));

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
                Intent intent = new Intent(YangzhihuDetailActivity.this, UpdateYangzhihuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constance.ACTIVITY_DATA, dateben);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
                popupwindow.dismiss();

            }
        });
        if (!dataListBeen.getFSm1().equals("移动端")) {
            tv_update.setVisibility(View.GONE);
        }

    }

    private void openDeleteDialog() {

        getPresenter().deteleData(dateben.getFStId(), uuid);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {

            getPresenter().getFarmDeclare("HT_BSDWSWSB", fStId);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static class InnerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(UploadPictureService.NEW_LIFEFORM_DETECTED1))
                if (mpresenter != null)
                    mpresenter.getFarmDeclare("HT_BSDWSWSB", Integer.valueOf(fStId));
        }
    }


}
