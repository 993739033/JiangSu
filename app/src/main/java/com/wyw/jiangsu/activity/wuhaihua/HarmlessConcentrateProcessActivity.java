package com.wyw.jiangsu.activity.wuhaihua;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.HarmlessConcentrateProcessDetilAdapter;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.upload.UploadHarmlessConcentrateProcess;
import com.wyw.jiangsu.bean.upload.UploadHomeConfimBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.DataSelectDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessConcentrateProcessActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.HarmlessConcentrateProcessActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;
import com.wyw.jiangsu.view.photoview.PhotoView;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;


/**
 * 无害化处理中心集中处理任务
 * 上个页面 无害化处理厂集中处理列表 {@link HarmlessConcentrateProcessListActivity}
 */
public class HarmlessConcentrateProcessActivity extends MVPActivity<HarmlessConcentrateProcessActivityPresenter>
        implements IHarmlessConcentrateProcessActivity, ITime, PhotoViewModel.OnClickListener {
    UploadHarmlessConcentrateProcess bean;
    HarmlessConcentrateProcessDetilAdapter adapter;
    @BindView(R.id.tv_harmlsee_name)
    TextView tvHarmlseeName;
    @BindView(R.id.tv_harmlsee_person)
    TextView tvHarmlseePerson;
    @BindView(R.id.tv_harmlsee_process_date_start)
    TextView tvHarmlseeProcessDateStart;
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(R.id.img_takephoto_confirm1)
    ImageView imgTakephotoConfirm1;
    @BindView(R.id.img_takephoto_confirm2)
    ImageView imgTakephotoConfirm2;
    @BindView(R.id.photo_view_confirm1)
    ImageView photoViewConfirm1;
    @BindView(R.id.photo_view_confirm2)
    ImageView photoViewConfirm2;
    @BindView(R.id.spinner_process_method)
    TextView spinnerProcessMethod;
    @BindView(R.id.et_grease)
    EditText etGrease;
    @BindView(R.id.et_residue)
    EditText etResidue;
    @BindView(R.id.et_liquid_products)
    EditText etLiquidProducts;
    //    @BindView(R.id.spinner_year)
//    Spinner spinnerYear;
//    @BindView(R.id.spinner_month)
//    Spinner spinnerMonth;
//    @BindView(R.id.spinner_day)
//    Spinner spinnerDay;
//    @BindView(R.id.spinner_hour)
//    Spinner spinnerHour;
//    @BindView(R.id.spinner_minites)
//    Spinner spinnerMinites;
    @BindView(R.id.img_process_person_signature)
    ImageView imgProcessPersonSignature;
    @BindView(R.id.img_veterinarians_signature)
    ImageView imgVeterinariansSignature;
    @BindView(R.id.harmless_btn_submit)
    Button harmlessBtnSubmit;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.parent_photo_view)
    PhotoView parentPhotoView;
    @BindView(R.id.parent)
    FrameLayout parent;
    @BindView(R.id.iv_none_1)
    ImageView iv_none_1;
    @BindView(R.id.iv_none_2)
    ImageView iv_none_2;
    @BindView(R.id.tv_date)
    TextView tv_date;

    TimePresenter timePresenter;
    private String fGlid;
    private String uuid = UUID.randomUUID().toString();
    private PhotoViewModel ImageViewModel;
    private String currentPhotoFile;

    private DataSelectDialog clfsSelectDialog;//处理方式
    private TimeSelectDialog jsTimeSelectDialog;//结束时间选择
    private String time[] = new String[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmelss_concentrate_process);
    }

    @Override
    protected HarmlessConcentrateProcessActivityPresenter createPresenter() {
        return new HarmlessConcentrateProcessActivityPresenter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
            if (file.getName().contains(uuid + "_A8")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgProcessPersonSignature);
            } else if (file.getName().contains(uuid + "_A9")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgVeterinariansSignature);
            }
        }
        String nameManager1 = uuid + "_A6";
        String nameManager2 = uuid + "_A7";
        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewConfirm1, REQUEST_CODE_START, nameManager1, getWuHaiHuaDir(), this);
        ImageViewModel.bindData(photoViewConfirm2, REQUEST_CODE_START + 1, nameManager2, getWuHaiHuaDir(), this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uuid = savedInstanceState.getString("uuid");
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("uuid", uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindData() {
        setTitle("无害化处理中心集中处理任务");
        bean = new UploadHarmlessConcentrateProcess();
//        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
//        photoViewUtils.initPhotoView(photoViewConfirm1, () -> currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A6",uuid+"_"));
//        photoViewUtils.initPhotoView(photoViewConfirm2, () -> currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 1, "A7",uuid+"_"));

//        spinnerProcessMethod.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv,
//                getResources().getStringArray(R.array.harmless_concentrate)));
        tvHarmlseeName.setText(getPresenter().getUser().getFUSEENAME());
        tvHarmlseePerson.setText(getPresenter().getUser().getFUNAME());
        UploadHomeConfimBean data = null;
        try {
            data = (UploadHomeConfimBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data != null) {
            ArrayList<HarmlessListDetilBean.DataListBean2> list = new ArrayList<>();
            String dwzl = data.getDwzl();//动物种类
            String sws = data.getSws();//死亡数
            String cbs = data.getCbs();//参保数
            String swzl = data.getZls();//总重量
            for (int i = 0; i < dwzl.split(",").length; i++) {
                HarmlessListDetilBean.DataListBean2 dataListBean2 = new HarmlessListDetilBean.DataListBean2();
                dataListBean2.setFbsdwzl(dwzl.split(",")[i]);
//                dataListBean2.setCBSS(cbs.split(",")[i]);
                dataListBean2.setFsws(sws.split(",")[i]);
                dataListBean2.setSWZL(swzl.split(",")[i]);
                list.add(dataListBean2);
            }
            bean.setWHHCLZX(getPresenter().getUser().getFUSEENAME());//无害化处理中心
            bean.setCLR(getPresenter().getUser().getFUNAME());//处理人
            HarmlessListDetilBean.DataListBean2 dataListBean2 = new HarmlessListDetilBean.DataListBean2();
            dataListBean2.setSWZL(data.getHJ());
            dataListBean2.setItemType(HarmlessConcentrateProcessDetilAdapter.TYPE_FOOTER);
            list.add(dataListBean2);
            adapter = new HarmlessConcentrateProcessDetilAdapter(list);
            fGlid = data.getFGlid();
            bean.setTGlid(data.getGlid());

            bean.setFGlid(data.getId());

            bean.setCbs(data.getCbs());
            bean.setSws(data.getSws());
            bean.setZls(data.getZls());
            bean.setDwzl(data.getDwzl());
            timePresenter = new TimePresenter(this);
            timePresenter.getTime();
        } else {
            String fstId = getIntent().getStringExtra(Constance.ACTIVITY_DATA);
            bean.setGlid(fstId);
            adapter = new HarmlessConcentrateProcessDetilAdapter(new ArrayList<>());
            getPresenter().getPageDetil(fstId);
        }
        recyclerAnimType.setAdapter(adapter);
        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));

        initDialog();
    }


    private void initDialog() {
        clfsSelectDialog = new DataSelectDialog(this);
        clfsSelectDialog.setTitle("选择处理方式");
        clfsSelectDialog.setDatas(getResources().getStringArray(R.array.harmless_concentrate));
        clfsSelectDialog.setCallBack(new DataSelectDialog.CallBack() {
            @Override
            public void enter(String data) {
                spinnerProcessMethod.setText(data);
            }

            @Override
            public void cancel() {

            }
        });

        spinnerProcessMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clfsSelectDialog.show();
            }
        });

        jsTimeSelectDialog = new TimeSelectDialog(this, "处理结束时间选择");
        jsTimeSelectDialog.setNeedMinute(true);
        time[0] = jsTimeSelectDialog.getmCurrentYear() + "";
        time[1] = jsTimeSelectDialog.getmCurrentMonth() + "";
        time[2] = jsTimeSelectDialog.getmCurrentDay() + "";
        time[3] = jsTimeSelectDialog.getmCurrentHour() + "";
        time[4] = jsTimeSelectDialog.getmCurrentMinute() + "";
        tv_date.setText(time[0] + "-" +
                time[1] + "-" +
                time[2] + " " +
                time[3] + ":" +
                time[4]);
        jsTimeSelectDialog.setmTimePickListener2(new TimeSelectDialog.TimePickListener2() {
            @Override
            public void getTime(String year, String month, String day, String hour, String minute) {
                time[0] = year;
                time[1] = month;
                time[2] = day;
                time[3] = hour;
                time[4] = minute;
                tv_date.setText(time[0] + "-" +
                        time[1] + "-" +
                        time[2] + " " +
                        time[3] + ":" +
                        time[4]);
            }
        });

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsTimeSelectDialog.show();
            }
        });
    }

    @Override
    public void bindListener() {
        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                Log.e("getbean", getBean().toString());
                showNormalDialog(uuid);
            }
        });
    }

    private void showNormalDialog(String uuid) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessConcentrateProcessActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getBean(), uuid);
                        dialog.dismiss();
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    public boolean check() {

        /*if (photoViewConfirm1.getDrawable() == null || !new File(PhotoUtils.getWuHaiHuaDir(), uuid+"_"+"A6.jpg").exists()) {
            Toast.makeText(this, "请重新拍摄处理确认1", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (photoViewConfirm2.getDrawable() == null || !new File(PhotoUtils.getWuHaiHuaDir(),uuid+"_" +"A7.jpg").exists()) {
            Toast.makeText(this, "请重新拍摄处理确认2", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        if (TextUtils.isEmpty(spinnerProcessMethod.getText().toString()) || spinnerProcessMethod.getText().toString().equals("请选择")) {
            Toast.makeText(this, "请选择处理方式", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etGrease.getText() == null || etResidue.getText() == null || etLiquidProducts.getText() == null ||
                ("0".equals(etGrease.getText()) && "0".equals(etResidue.getText()) && "0".equals(etLiquidProducts.getText()))) {
            Toast.makeText(this, "请录入处理产物重量", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!new File(PhotoUtils.getWuHaiHuaDir(), uuid + "_" + "A8.jpg").exists() || imgProcessPersonSignature.getDrawable() == null) {
            Toast.makeText(this, "处理人签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!new File(PhotoUtils.getWuHaiHuaDir(), uuid + "_" + "A9.jpg").exists() || imgVeterinariansSignature.getDrawable() == null) {
            Toast.makeText(this, "驻场兽医签名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            if (!checkTime()) {
                Toast.makeText(this, "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    //判断时间 true 报错了
    private boolean checkTime() throws ParseException {
        String[] split1 = tvHarmlseeProcessDateStart.getText().toString().split(" ");
        String[] pre = split1[0].split("-");
        String[] end = split1[1].split(":");
        int startYear = Integer.valueOf(pre[0]);
        int startMonth = Integer.valueOf(pre[1]);
        int startDay = Integer.valueOf(pre[2]);
        int startHour = Integer.valueOf(end[0]);
        int startMinute = Integer.valueOf(end[1]);

//        int endYear = Integer.valueOf(spinnerYear.getSelectedItem().toString());
//        int endMonth = Integer.valueOf(spinnerMonth.getSelectedItem().toString());
//        int endDay = Integer.valueOf(spinnerDay.getSelectedItem().toString());
//        int endHour = Integer.valueOf(spinnerHour.getSelectedItem().toString());
//        int endMinute = Integer.valueOf(spinnerMinites.getSelectedItem().toString());


        int endYear = Integer.valueOf(time[0]);
        int endMonth = Integer.valueOf(time[1]);
        int endDay = Integer.valueOf(time[2]);
        int endHour = Integer.valueOf(time[3]);
        int endMinute = Integer.valueOf(time[4]);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date startdate = df.parse(tvHarmlseeProcessDateStart.getText().toString());//开始时间
        java.util.Date enddate = df.parse(endYear + "-" + endMonth + "-" + endDay + " " + endHour + ":" + endMinute + ":" + "00");//结束时间
        if (startdate.getTime() - enddate.getTime() > 1) {
            return false;
        }
        //原先的判断存在问题
//        if (startYear > endYear) {
//            return true;
//        } else if (startMonth > endMonth) {
//            return true;
//        } else if (startDay > endDay) {
//            return true;
//        } else if (startHour > endHour) {
//            return true;
//        } else if (startMinute > endMinute) {
//            return true;
//        }
        return true;
    }


    public UploadHarmlessConcentrateProcess getBean() {
        String dwzl = "", sws = "", cbs = "", zls = "", hj = "";
        for (int i = 1; i < adapter.getData().size(); i++) {
            HarmlessListDetilBean.DataListBean2 dataListBean2 = (HarmlessListDetilBean.DataListBean2) adapter.getData().get(i);
            if (TextUtils.isEmpty(dataListBean2.getFbsdwzl())) {
                hj += dataListBean2.getSWZL();//最后一个总heji
                break;
            }
            dwzl += dataListBean2.getFbsdwzl() + ",";
            sws += dataListBean2.getFsws() + ",";
//            cbs += dataListBean2.getCBSS() + ",";
            zls += dataListBean2.getSWZL() + ",";
        }
        //去除,
        dwzl = dwzl.substring(0, dwzl.length() - 1);
        sws = sws.substring(0, sws.length() - 1);
//        cbs = cbs.substring(0, dwzl.length() - 1);
        zls = zls.substring(0, zls.length() - 1);
        //动物种类数组
        bean.setDwzl(dwzl);
        //死亡数
        bean.setSws(sws);
        //参保数
//        bean.setCbs(cbs);
        //重量
        bean.setZls(zls);
        //合计
        bean.setHJ(hj);

        //处理方式
//        bean.setCLFSS(spinnerProcessMethod.getSelectedItem().toString());
        bean.setCLFSS(spinnerProcessMethod.getText().toString());
        //时间
//        bean.setSJ(spinnerYear.getSelectedItem().toString() + "-" +
//                spinnerMonth.getSelectedItem().toString() + "-" +
//                spinnerDay.getSelectedItem().toString() + " " +
//                spinnerHour.getSelectedItem().toString() + ":" +
//                spinnerMinites.getSelectedItem().toString());
        bean.setSJ(tv_date.getText().toString());
        //油脂
        bean.setYZ(etGrease.getText().toString());
        //残渣
        bean.setCZ(etResidue.getText().toString());
        //液体产物
        bean.setYTCW(etLiquidProducts.getText().toString());
//        bean.setFGlid(fGlid);
        bean.setLen("A6.jpg,A7.jpg,A8.jpg,A9.jpg");
        return bean;
    }


    @OnClick({R.id.img_takephoto_confirm1, R.id.img_takephoto_confirm2,
            R.id.bt_process_person_signature, R.id.bt_veterinarians_signature})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_confirm1:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A6", uuid + "_");
                break;
            case R.id.img_takephoto_confirm2:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START + 1, "A7", uuid + "_");
                break;
            case R.id.bt_process_person_signature:
                //处理人签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A8", uuid + "_");//名字约定的
                    imgProcessPersonSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_veterinarians_signature:
                //驻场兽医签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A9", uuid + "_");//名字约定的
                    imgVeterinariansSignature.setImageBitmap((Bitmap) object);
                }).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Uri data1 = data.setData();
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
            PhotoUtils.saveBitmap(currentPhotoFile, bitmap);
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                    photoViewConfirm1.setImageBitmap(bitmap);
                    iv_none_1.setVisibility(View.GONE);
                    break;
                case PhotoUtils.REQUEST_CODE_START + 1:
                    photoViewConfirm2.setImageBitmap(bitmap);
                    iv_none_2.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
//        if (photoViewUtils.onBackPress()) super.onBackPressed();
        finish();
    }

    @Override
    public void setTime(String time, long longTime) {
        //年月日 时分秒
        tvHarmlseeProcessDateStart.setText(time);
        bean.setCLRQ(time);
    }

    @Override
    public void getPageDetil(HarmlessListDetilBean.DataBean dataBean) {
        //合计
        double total = 0;
        List<HarmlessListDetilBean.DataListBean2> listBean2s = new ArrayList<>();
        for (int i = 0; i < dataBean.getDataList1().size(); i++) {
            HarmlessListDetilBean.DataListBean0 dataListBean0 = dataBean.getDataList1().get(i);
            HarmlessListDetilBean.DataListBean2 data = new HarmlessListDetilBean.DataListBean2();
            data.setFbsdwzl(dataListBean0.getDwzl());
            data.setSWZL(dataListBean0.getZls());
            data.setFsws(dataListBean0.getSws());
            listBean2s.add(data);
            total += Double.valueOf(dataListBean0.getZls());
        }
        HarmlessListDetilBean.DataListBean2 last = new HarmlessListDetilBean.DataListBean2();
        last.setSWZL(String.valueOf(total));
        last.setItemType(HarmlessConcentrateProcessDetilAdapter.TYPE_FOOTER);
        listBean2s.add(last);
        //拼装数据
        adapter.addData(listBean2s);

        tvHarmlseeName.setText(dataBean.getWHHCLZX());
        bean.setWHHCLZX(dataBean.getWHHCLZX());//无害化处理中心

        tvHarmlseePerson.setText(dataBean.getCLR());//处理人
        bean.setCLR(dataBean.getCLR());//处理人

        tvHarmlseeProcessDateStart.setText(dataBean.getCLRQ());//处理日期
        bean.setCLRQ(dataBean.getCLRQ());
    }

    @Override
    public void uploadSuccessful() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        Toast.makeText(this, "上传完成", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
