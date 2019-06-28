package com.wyw.jiangsu.activity.wuhaihua;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.adapter.AnimTypeTotalNoInsuredAdapter;
import com.wyw.jiangsu.bean.HarmlessShujiDetilBean;
import com.wyw.jiangsu.bean.upload.UploadMessageConfirmStorageBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IHarmlessPlantCollectionConfirmActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.HarmlessPlantCollectionConfirmActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.PhotoViewModel;
import com.wyw.jiangsu.view.FullyLinearLayoutManager;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wyw.jiangsu.R.id.harmless_btn_submit;
import static com.wyw.jiangsu.R.id.photo_view_storage_confirm;
import static com.wyw.jiangsu.utils.PhotoUtils.REQUEST_CODE_START;
import static com.wyw.jiangsu.utils.PhotoUtils.getWuHaiHuaDir;


/**
 * 暂存点收集人员确认 无害化处理场
 * 上个页面 收集人员消息接收(无害化处理厂) {@link HarmlessMessageAcceptStorageListActivity}
 * 下个页面 驻场兽医列表 {@link HarmlessHomeVeterinatiansListActivity}
 */
public class HarmlessMessageConfirmStorageActivity extends MVPActivity<HarmlessPlantCollectionConfirmActivityPresenter>
        implements IHarmlessPlantCollectionConfirmActivity, ITime, PhotoViewModel.OnClickListener {

    @BindView(R.id.tv_harmlsee_name)
    TextView tvHarmlseeName;
    @BindView(R.id.tv_harmlsee_addr)
    TextView tvHarmlseeAddr;
    @BindView(R.id.tv_harmlsee_dispatch_date)
    TextView tvHarmlseeDispatchDate;
    @BindView(R.id.recycler_anim_type)
    RecyclerView recyclerAnimType;
    @BindView(photo_view_storage_confirm)
    ImageView photoViewStorageConfirm;
    @BindView(R.id.img_principal)
    ImageView imgPrincipal;
    @BindView(R.id.img_collection_person)
    ImageView imgCollectionPerson;
    @BindView(harmless_btn_submit)
    Button harmlessBtnSubmit;
    UploadMessageConfirmStorageBean bean;
    AnimTypeTotalNoInsuredAdapter typeAdapter;
    @BindView(R.id.et_check_number)
    EditText etCheckNumber;
    @BindView(R.id.et_arrive_time)
    TextView etArriveTime;
    @BindView(R.id.et_leave_time)
    TextView etleaveTime;
    @BindView(R.id.tv_carnum)
    TextView tvCarnum;
//    @BindView(R.id.et_arrive_hour)
//    Spinner etArriveHour;
//    @BindView(R.id.et_arrive_min)
//    Spinner etArriveMin;
//    @BindView(R.id.et_leave_hour)
//    Spinner etLeaveHour;
//    @BindView(R.id.et_leave_min)
//    Spinner etLeaveMin;

    TimeSelectDialog arriveTimeDialog;//到达时间
    TimeSelectDialog leaveTimeDialog;//离开时间


    @BindView(R.id.iv_none)
    ImageView iv_none;

    private TimePresenter timePresenter;
    private List<HarmlessShujiDetilBean.DataBean> list;
    int num = 0;
    private String fglid;
    private String dwzl;
    private String sws;
    private String zls;
//    private DatePickerDialog dateDialogStart1;
//    private DatePickerDialog dateDialogStart2;
    private String arriveTime = "";
    private String leaveTime = "";
    private String nowArriveTIme;
    private String nowLeaveTime;
    private PhotoViewModel ImageViewModel;
    private String currentPhotoFile;
    private String uuid;
    private int selectedArriveHour;
    private int selectedArriveMin;
    private int selectedLeaveHour;
    private int selectedLeaveMin;
    private boolean isDestoryed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmless_message_connfirm_storage);
        ButterKnife.bind(this);
        uuid = UUID.randomUUID().toString();
//        DatePicker datePicker = dateDialogStart1.getDatePicker();

    }

    @Override
    protected HarmlessPlantCollectionConfirmActivityPresenter createPresenter() {
        return new HarmlessPlantCollectionConfirmActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("收集运输确认");
        timePresenter = new TimePresenter(this);
        bean = new UploadMessageConfirmStorageBean();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = f.format(date);
        etArriveTime.setText(time);
        etleaveTime.setText(time);
        typeAdapter = new AnimTypeTotalNoInsuredAdapter(new ArrayList<>());
        recyclerAnimType.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerAnimType.setAdapter(typeAdapter);
        String fstId = getIntent().getStringExtra(Constance.ACTIVITY_DATA);
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
//        dateDialogStart1 = new DatePickerDialog(this, this, mCalendar.get(Calendar.YEAR),
//                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
//        dateDialogStart2 = new DatePickerDialog(this, this, mCalendar.get(Calendar.YEAR),
//                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
//        etArriveHour.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, OtherUtil.createHours()));
//        etArriveMin.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, OtherUtil.createMinutes()));
//        etLeaveHour.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, OtherUtil.createHours()));
//        etLeaveMin.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_simple, R.id.tv, OtherUtil.createMinutes()));
//         无害化任务关联ID
        fglid = getIntent().getStringExtra(Constance.ACTIVITY_TYPE);
        bean.setGlid(fstId);
        getPresenter().getPageDetil(fstId);
        timePresenter.getTime();
        
//        photoViewUtils = new PhotoViewUtils(parent, bg, parentPhotoView);
//        photoViewUtils.initPhotoView(photoViewStorageConfirm, () -> currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_"));

        initDialog();
    }

    private void initDialog() {
//        到达时间
        arriveTimeDialog = new TimeSelectDialog(this, "请选择到达时间");
        arriveTimeDialog.setNeedMinute(true);
        etArriveTime.setText(arriveTimeDialog.getmCurrentYear()+"-"+arriveTimeDialog.getmCurrentMonth()+"-"+arriveTimeDialog.getmCurrentDay()
                +" "+arriveTimeDialog.getmCurrentHour()+":"+arriveTimeDialog.getmCurrentMinute());
        arriveTimeDialog.setmTimePickListener2(new TimeSelectDialog.TimePickListener2() {
            @Override
            public void getTime(String year, String month, String day, String hour, String minute) {
//                nowArriveTIme = arriveTime + " "
//                 + etArriveHour.getSelectedItemPosition() + ":" + etArriveMin.getSelectedItemPosition();
                //    String time = i + "-" + (i1 + 1) + "-" + i2;
                etArriveTime.setText(year+"-"+month+"-"+day+" "+hour+":"+minute);
            }
        });

//        离开时间
        leaveTimeDialog = new TimeSelectDialog(this, "请选择离开时间");
        leaveTimeDialog.setNeedMinute(true);
        etleaveTime.setText(leaveTimeDialog.getmCurrentYear()+"-"+leaveTimeDialog.getmCurrentMonth()+"-"+leaveTimeDialog.getmCurrentDay()
                +" "+leaveTimeDialog.getmCurrentHour()+":"+leaveTimeDialog.getmCurrentMinute());
        leaveTimeDialog.setmTimePickListener2(new TimeSelectDialog.TimePickListener2() {
            @Override
            public void getTime(String year, String month, String day, String hour, String minute) {
                etleaveTime.setText(year+"-"+month+"-"+day+" "+hour+":"+minute);
            }
        });
    
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (File file : PhotoUtils.getWuHaiHuaDir().listFiles()) {
            if (file.getName().contains(uuid + "_A2")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgPrincipal);
            }else if (file.getName().contains(uuid + "_A3")) {
                Picasso.with(this).load(Uri.fromFile(new File(getWuHaiHuaDir(), file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(imgCollectionPerson);
            }
        }
        String nameManager1 = uuid + "_A1";
        ImageViewModel = new PhotoViewModel(this);
        ImageViewModel.bindData(photoViewStorageConfirm, REQUEST_CODE_START, nameManager1, getWuHaiHuaDir(), this);
        if (isDestoryed){
            etArriveTime.setText(arriveTime);
//            etArriveHour.setSelection(selectedArriveHour);
//            etArriveMin.setSelection(selectedArriveMin);
            etleaveTime.setText(leaveTime);
//            etLeaveHour.setSelection(selectedLeaveHour);
//            etLeaveMin.setSelection(selectedLeaveMin);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uuid = savedInstanceState.getString("uuid");
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
//        nowArriveTIme = arriveTime + " " + etArriveHour.getSelectedItemPosition() + ":" + etArriveMin.getSelectedItemPosition();
//        nowLeaveTime = leaveTime + " " + etLeaveHour.getSelectedItemPosition() + ":" + etLeaveMin.getSelectedItemPosition();

        nowArriveTIme = etArriveTime.getText().toString();
        nowLeaveTime = etleaveTime.getText().toString();
        arriveTime = savedInstanceState.getString("arriveTime");
        selectedArriveHour = savedInstanceState.getInt("etArriveHour");
        selectedArriveMin = savedInstanceState.getInt("etArriveMin");
        leaveTime = savedInstanceState.getString("leaveTime");
        selectedLeaveHour = savedInstanceState.getInt("etLeaveHour");
        selectedLeaveMin = savedInstanceState.getInt("etLeaveMin");
        isDestoryed = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("uuid", uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        outState.putString("arriveTime", arriveTime);
//        outState.putInt("etArriveHour", etArriveHour.getSelectedItemPosition());
//        outState.putInt("etArriveMin", etArriveMin.getSelectedItemPosition());
        outState.putString("leaveTime", leaveTime);
//        outState.putInt("etLeaveHour", etLeaveHour.getSelectedItemPosition());
//        outState.putInt("etLeaveMin", etLeaveMin.getSelectedItemPosition());
        super.onSaveInstanceState(outState);
    }

    @OnClick({R.id.et_arrive_time, R.id.et_leave_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_arrive_time:
//                dateDialogStart1.show();
                arriveTimeDialog.show();
                break;
            case R.id.et_leave_time:
//                dateDialogStart2.show();
                leaveTimeDialog.show();
                break;
        }
    }

    @Override
    public void bindListener() {
        harmlessBtnSubmit.setOnClickListener(v -> {
            if (check()) {
                showNormalDialog(uuid);
            }
        });
    }

    private void showNormalDialog(String uuid) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(HarmlessMessageConfirmStorageActivity.this);
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
        if (isEmpty(etCheckNumber.getText().toString())) {
            Toast.makeText(this, "确认数量不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (photoViewStorageConfirm.getDrawable() == null) {
            Toast.makeText(this, "收集确认图片不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (imgPrincipal.getDrawable() == null) {
            Toast.makeText(this, "官方兽医签字不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (imgCollectionPerson.getDrawable() == null) {
            Toast.makeText(this, "运输人员签字不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etArriveTime.getText().toString().equals("请选择")|| TextUtils.isEmpty(etArriveTime.getText().toString())){
            Toast.makeText(this, "请选择到达时间", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etleaveTime.getText().toString().equals("请选择")|| TextUtils.isEmpty(etleaveTime.getText().toString())){
            Toast.makeText(this, "请选择离开时间", Toast.LENGTH_SHORT).show();
            return false;
        }

//        nowArriveTIme = arriveTime + " " + etArriveHour.getSelectedItem() + ":" + etArriveMin.getSelectedItem();
//        nowLeaveTime = leaveTime + " " + etLeaveHour.getSelectedItem() + ":" + etLeaveMin.getSelectedItem();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date arriveDate;
        Date leaveDate;
        try {
            arriveDate = format.parse(etArriveTime.getText().toString());
            leaveDate = format.parse(etleaveTime.getText().toString());
            if (arriveDate.getTime() > leaveDate.getTime()) {
                Toast.makeText(this, "离开时间不能早于到达时间", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
            switch (requestCode) {
                case PhotoUtils.REQUEST_CODE_START:
                    photoViewStorageConfirm.setImageBitmap(bitmap);
                    iv_none.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @OnClick({R.id.img_takephoto_storage_confirm,
            R.id.bt_principal_qianming, R.id.bt_collection_person})
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.img_takephoto_storage_confirm:
                currentPhotoFile = PhotoUtils.takePictureHarmless(this, PhotoUtils.REQUEST_CODE_START, "A1", uuid + "_");
                break;
            case R.id.bt_principal_qianming:
                //暂存点负责人签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A2", uuid + "_");
                    imgPrincipal.setImageBitmap((Bitmap) object);
                }).show();
                break;
            case R.id.bt_collection_person:
                //无害化处理中心收集人员签名
                new CcWriteDiolog(this, object -> {
                    //保存
                    PhotoUtils.saveSignPicture((Bitmap) object, "A3", uuid + "_");
                    imgCollectionPerson.setImageBitmap((Bitmap) object);
                }).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        if (photoViewUtils.onBackPress()) super.onBackPressed();
        finish();
    }

    @Override
    public void uploadSuccessful() {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void getPageDetil(HarmlessShujiDetilBean.DataBean data) {
        if (data != null) {
            int size1 = data.getDataList1().size();
            list = new ArrayList();
            list.add(data);
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            StringBuffer sb3 = new StringBuffer();
            for (int i = 0; i < size1; i++) {

                String bsdwzl = data.getDataList1().get(i).getBSDWZL();//动物
                String bsdwsl = data.getDataList1().get(i).getBSDWSL();//死亡数
                num += Integer.valueOf(bsdwsl);
                String cbs = data.getDataList1().get(i).getCBS();//重量
                sb1.append(bsdwzl);
                sb2.append(bsdwsl);
                sb3.append(cbs);
                if (i < size1 - 1) {
                    sb1.append(",");
                    sb2.append(",");
                    sb3.append(",");
                }

            }
            //自动获取确定数量
            etCheckNumber.setText(num + "");
            dwzl = new String(sb1);
            sws = new String(sb2);
            zls = new String(sb3);

            //车牌号
            tvCarnum.setText(data.getCPH());
            //// TODO: 2016/12/29 将来扩展 多种动物种类
            //动物种类
            typeAdapter.addData(list);

            tvHarmlseeName.setText(data.getFxzxm());//收集点名称
            tvHarmlseeAddr.setText(data.getFxxdz());//地址

        } else {
            Toast.makeText(this, "数据出错", Toast.LENGTH_SHORT).show();
        }
        isDestoryed = false;
    }

    public UploadMessageConfirmStorageBean getBean() {

        bean.setDwzl(dwzl);
        bean.setSws(sws);
        bean.setZls(zls);
        bean.setFGlid(fglid);
        bean.setCPH(tvCarnum.getText().toString());
        bean.setHSQFH(nowArriveTIme);
        bean.setJSQFH(nowLeaveTime);
        bean.setQDSL(Integer.valueOf(etCheckNumber.getText().toString()));
        bean.setLen("A1.jpg,A2.jpg,A3.jpg");
        return bean;
    }

    @Override
    public void setTime(String time, long longTime) {
        tvHarmlseeDispatchDate.setText(time.substring(0, 11));//分派日期
    }


    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile=path;
    }
}
