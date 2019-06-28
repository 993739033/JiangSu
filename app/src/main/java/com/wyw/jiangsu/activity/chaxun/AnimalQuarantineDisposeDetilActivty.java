package com.wyw.jiangsu.activity.chaxun;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.PrintAcitivty;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.db.User;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;
import com.wyw.jiangsu.utils.AppUtils;
import com.wyw.jiangsu.utils.BorisPrint;
import com.wyw.jiangsu.utils.DrawUtil;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.PrintView;
import com.wyw.jiangsu.utils.SPUtils;
import com.wyw.jiangsu.view.FullPhotoView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动物检疫申报单处理查询详情
 * Created by Administrator on 2017/3/27.
 */
public class AnimalQuarantineDisposeDetilActivty extends MVPActivity {


    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_shenpibiao)
    EditText etShenpibiao;
    @BindView(R.id.et_xukezheng)
    EditText etXukezheng;
    @BindView(R.id.ll_livestock_permission)
    RelativeLayout llLivestockPermission;
    @BindView(R.id.et_apply_number)
    EditText etApplyNumber;
    @BindView(R.id.et_apply_type)
    EditText etApplyType;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_anim_type)
    EditText etAnimType;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.et_donator_anim_kind)
    EditText etDonatorAnimKind;
    @BindView(R.id.ll_donator_anim)
    LinearLayout llDonatorAnim;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.et_anim_product_kind)
    EditText etAnimProductKind;
    @BindView(R.id.ll_anim_product)
    LinearLayout llAnimProduct;
    @BindView(R.id.ll_anim_kand)
    LinearLayout llAnimKand;
    @BindView(R.id.et_anim_count)
    EditText etAnimCount;
    @BindView(R.id.et_purpose)
    EditText etPurpose;
    @BindView(R.id.ll_use)
    RelativeLayout llUse;
    @BindView(R.id.et_source)
    EditText etSource;
    @BindView(R.id.ll_laiyuan)
    RelativeLayout llLaiyuan;
    @BindView(R.id.tv_source_title)
    TextView tvSourceTitle;
    @BindView(R.id.et_source_extra)
    EditText etSourceExtra;
    @BindView(R.id.lin_source_extra)
    RelativeLayout linSourceExtra;
    @BindView(R.id.et_start_type)
    TextView etStartType;
    @BindView(R.id.et_start_place)
    EditText etStartPlace;
    @BindView(R.id.et_start_village)
    EditText etStartVillage;
    @BindView(R.id.et_start_other)
    EditText etStartOther;
    @BindView(R.id.et_end_type)
    EditText etEndType;
    @BindView(R.id.et_end_place)
    EditText etEndPlace;
    @BindView(R.id.et_end_village)
    EditText etEndVillage;
    @BindView(R.id.et_end_other)
    EditText etEndOther;
    @BindView(R.id.tv_inspection_time)
    TextView tvInspectionTime;
    @BindView(R.id.tv_shipment_time)
    TextView tvShipmentTime;
    @BindView(R.id.tv_erbiao)
    TextView tvErbiao;
    @BindView(R.id.et_shenbao_result)
    EditText etShenbaoResult;
    @BindView(R.id.et_start)
    TextView etStart;
    @BindView(R.id.et_place)
    EditText etPlace;
    @BindView(R.id.ll_accept)
    LinearLayout llAccept;
    @BindView(R.id.et_reason)
    EditText etReason;
    @BindView(R.id.ll_unaccept)
    LinearLayout llUnaccept;
    @BindView(R.id.et_operator_name)
    EditText etOperatorName;
    @BindView(R.id.et_operator_phone)
    EditText etOperatorPhone;
    @BindView(R.id.et_deal_with_time)
    TextView etDealWithTime;
    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.img_takephoto_approve)
    ImageView imgTakephotoApprove;
    @BindView(R.id.photo_view_group_photo)
    FullPhotoView photoViewIcId;
    @BindView(R.id.lin_take_photo)
    LinearLayout linTakePhoto;
    @BindView(R.id.SV)
    ScrollView SV;
    private AnimProcessListBean.DataListBean dataListBean;
    private Bitmap cachebitmap;
    private Handler phandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101://打印完成
                    progressDialog.dismiss();
                    break;
                case 102://打印机查找失败
                    progressDialog.dismiss();
                    break;
                case 103://打印机查找成功
                    progressDialog.dismiss();
                    progressDialog.setMessage("打印中。。。");
                    progressDialog.show();
                    break;
                case 104://绘制完成，查找打印机
                    progressDialog.dismiss();
                    progressDialog.setMessage("查找打印机。。。");
                    progressDialog.show();
                    break;
                case 105://关闭对话框
                    progressDialog.dismiss();
                    break;
                case 107://关闭对话框
                    Toast.makeText(AnimalQuarantineDisposeDetilActivty.this, "未检测到指定的printshare软件，请先下载", Toast.LENGTH_SHORT).show();
                    break;
                case 108:
                    Toast.makeText(AnimalQuarantineDisposeDetilActivty.this,"请选择图片,找到"+Constance.dirName+"文件夹打印对应的文件",Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };
    private ProgressDialog progressDialog;
    private boolean hasErTag;
    private String earTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_quarantine_dispose_detil_activity);
        ButterKnife.bind(this);
        SV.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("动物检疫申报单处理详情");
        Intent intent = getIntent();
        dataListBean = (AnimProcessListBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);

        if (dataListBean.getYx().equals("有")) {
            linTakePhoto.setVisibility(View.VISIBLE);
        } else {
            linTakePhoto.setVisibility(View.GONE);
        }

        if (dataListBean.getImg() != null && !TextUtils.isEmpty(dataListBean.getImg())) {
            Glide.with(this).load(Constance.IMAGE_PATHH + dataListBean.getImg()).into(photoViewIcId);
        }

        llLivestockPermission.setVisibility(View.VISIBLE);
        llDonatorAnim.setVisibility(View.VISIBLE);
        llAnimProduct.setVisibility(View.VISIBLE);
        linSourceExtra.setVisibility(View.VISIBLE);
        llUse.setVisibility(View.VISIBLE);
        llLaiyuan.setVisibility(View.VISIBLE);
        etPurpose.setText(dataListBean.getQDWYongTu());//用途
        etSource.setText(dataListBean.getQDWLaiYuan());//来源
        etXukezheng.setText(dataListBean.getFqZxqscjyxkz());//有效《种畜禽生产经营许可证》
        etAnimProductKind.setText(dataListBean.getFqProduct());//动物产品种类

        if (dataListBean.getQDWAccepted().contains("不")) {
            llAccept.setVisibility(View.GONE);
            llUnaccept.setVisibility(View.VISIBLE);
            etReason.setText(dataListBean.getQDWLiYou()); //不受理理由
        } else {
            llAccept.setVisibility(View.VISIBLE);
            llUnaccept.setVisibility(View.GONE);
            etStart.setText(dataListBean.getCLRQ());//拟派时间
            etPlace.setText(dataListBean.getQDWAddress());//到检疫地点实施检疫

        }

        etShenpibiao.setText(dataListBean.getYx());//有效《跨省引进乳用种用动物检疫审批表》
        etApplyNumber.setText(dataListBean.getQDWNumber());//申报单编号
        etApplyType.setText(dataListBean.getFqSbType());//申报类型
        etUserName.setText(dataListBean.getQDWCargoOwner());//申报人姓名
        etUserPhone.setText(dataListBean.getQDWPhone());//申报人电话
        etAnimType.setText(dataListBean.getQDWXuZhongZ());//动物种类
        etAnimCount.setText(dataListBean.getQDWQuantity() + dataListBean.getQDWDanWei());//动物数量
        etPurpose.setText(dataListBean.getQDWYongTu());//用途
        etSource.setText(dataListBean.getQDWLaiYuan());//来源
        etStartType.setText(dataListBean.getQDWTypeQy());//起运地类别
        etStartPlace.setText(dataListBean.getQDWShengQy() + dataListBean.getQDWShiQy());//起运地址省、市
        etStartVillage.setText(dataListBean.getQDWXianQy());//起运地址乡镇
        etStartOther.setText(dataListBean.getQDWXiangQy() + dataListBean.getQDWCunQy());//起运地址乡村
        etEndType.setText(dataListBean.getQDWTypeDd());//到达地址类别
        etEndPlace.setText(dataListBean.getQDWShengDd() + dataListBean.getQDWShiDd());//到达地址省市
        etEndVillage.setText(dataListBean.getQDWXianDd());//到达地址县镇
        etEndOther.setText(dataListBean.getQDWXiangDd() + dataListBean.getQDWCunDd());//到达地址乡村
        tvInspectionTime.setText(dataListBean.getFScTime());//报检时间
        tvShipmentTime.setText(dataListBean.getDateQy());//启运时间
        tvErbiao.setText(dataListBean.getQDWErBiaoHao());//耳标号
        etShenbaoResult.setText(dataListBean.getQDWAccepted()); //申报处理结果
        etStartType.setText(dataListBean.getDateNpy());   //派去时间
        etOperatorName.setText(dataListBean.getQDWAttn());//经办人姓名
        etOperatorPhone.setText(dataListBean.getQDWJBRDianHua());//经办人电话
        etDealWithTime.setText(dataListBean.getDateNpy());//处理时间

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    @Override
    public void bindListener() {
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("绘制视图。。。");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();

                        init();

                        Looper.loop();
                    }
                }).start();
            }
        });
    }

    private void init() {
        //BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null);
        File file4 = new File(FileUtil.getInstance().getCacheDirPath(), "shenbaodanchuli.jpg");
        if (file4.exists()) file4.delete();
        PrintView<AnimProcessListBean.DataListBean> printView8 = new PrintView(this, dataListBean);
        cachebitmap = printView8.getcacheBitmap();
        String s2 = FileUtil.getInstance().save2Local(cachebitmap, file4.getAbsolutePath());
        int whichPrinter = ((User) SPUtils.getInstance().getObjectData(Constance.USER_OBJECT)).getZt();
        if (whichPrinter == 1) {
            if (dataListBean.getQDWErBiaoHao()!=null&&dataListBean.getQDWErBiaoHao().getBytes().length>146){
                hasErTag =true;
                earTag = dataListBean.getQDWErBiaoHao();
            }
            saveAndStartPrintShare(FileUtil.getInstance().getCacheDirPath(), "shenbaodanchuli.jpg");
            return;
        }
        phandler.sendEmptyMessage(104);
        while (true){
            BorisPrint bPrint = new BorisPrint(MyApplication.getContext(), null,phandler);
            int code = bPrint.print(s2, 2);
            if (code==0){
                break;
            }
        }

    }

    private void saveAndStartPrintShare(String filePath, String fileName) {
        phandler.sendEmptyMessage(104);
        long time1 = System.currentTimeMillis();
        phandler.sendEmptyMessage(108);
        long time2 = 0;
        do {
            time2 = System.currentTimeMillis();
        }while(time2-time1<5000);
        phandler.sendEmptyMessage(105);
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = initDir();
                Bitmap bitmap = BitmapFactory.decodeFile(filePath + "/" + fileName);
                FileUtil.getInstance().save2Local(bitmap, file.getAbsolutePath() + "/" + fileName);
                if (hasErTag){
                    DrawUtil drawUtil = new DrawUtil(AnimalQuarantineDisposeDetilActivty.this);
                    Bitmap bitmapEb=drawUtil.drawEbInBackSecond(earTag);
                    FileUtil.getInstance().save2Local(bitmapEb,
                            Environment.getExternalStorageDirectory() + Constance.dirName+"/ErBiao.jpg");
                }
                if(AppUtils.isAvilible(AnimalQuarantineDisposeDetilActivty.this,"com.dynamixsoftware.printershare")){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    ComponentName cn = new ComponentName("com.dynamixsoftware.printershare", "com.dynamixsoftware.printershare.ActivityMain");
                    intent.setComponent(cn);
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("startprintshare", e.getMessage());
                    }
                    phandler.sendEmptyMessage(108);
                }else {
                    phandler.sendEmptyMessage(107);
                }

            }
        }).start();
    }

    @NonNull
    private File initDir() {
        File file = new File(Environment.getExternalStorageDirectory() + Constance.dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
        return file;
    }
}
