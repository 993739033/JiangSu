package com.wyw.jiangsu.activity.supervision;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.InputTextActivity;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.PrintAcitivty;
import com.wyw.jiangsu.activity.model.PhotoViewModel;
import com.wyw.jiangsu.adapter.CheckContentAdapter;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.bean.local.CheckContentEntity;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.DialogListener;
import com.wyw.jiangsu.interfac.ICommonSupervisionDetilActivity;
import com.wyw.jiangsu.presenter.CommonSupervisionDetilActivityPresenter;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.view.NoScrollListView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import zxing.activity.CaptureActivity;

import static com.wyw.jiangsu.activity.model.PhotoViewModel.SUFFIX;
import static com.wyw.jiangsu.activity.model.PhotoViewModel.getCommonSupervisionDir;
import static com.wyw.jiangsu.utils.PhotoUtils.decodeSampleBitmapFromFile;

public class CommonSupervisionDetilActivityActivity extends MVPActivity<CommonSupervisionDetilActivityPresenter> implements ICommonSupervisionDetilActivity, DatePickerDialog.OnDateSetListener {
    public static final int TABLE_1 = 1;
    public static final int TABLE_2 = 2;
    public static final int TABLE_3 = 3;
    public static final int TABLE_4 = 4;
    public static final int TABLE_5 = 5;
    public static final int TABLE_6 = 6;
    public static final int TABLE_7 = 7;
    public static final int TABLE_8 = 8;
    public static final int TABLE_9 = 9;
    private static final int REQUEST_CODE_ACTIVITY_ZXING = 1000;
    private static final int REQUEST_CODE_GROUP_PHOTO = 1001;
    //    @BindView(R.id.title_name)
//    TextView titleName;
    @BindView(R.id.tv_modify_name)
    TextView tvModifyName;
    @BindView(R.id.et_feed_name)
    EditText etFeedName;
    @BindView(R.id.bt_ercode)
    Button btErcode;
    @BindView(R.id.tv_kind)
    TextView tvKind;
    @BindView(R.id.et_kinds)
    EditText etKinds;
    @BindView(R.id.tv_modify_number)
    TextView tvModifyNumber;
    @BindView(R.id.et_anim_number)
    EditText etAnimNumber;
    @BindView(R.id.et_principal_name)
    EditText etPrincipalName;
    @BindView(R.id.et_principal_phone)
    EditText etPrincipalPhone;
    @BindView(R.id.lv)
    NoScrollListView lv;
    @BindView(R.id.img_group_photo)
    ImageView imgGroupPhoto;
    @BindView(R.id.photo_view_group_photo)
    ImageView photoViewGroupPhoto;
    @BindView(R.id.et_check_advise)
    EditText etCheckAdvise;
    @BindView(R.id.et_name1)
    EditText etName1;
    @BindView(R.id.et_name2)
    EditText etName2;
    @BindView(R.id.et_person_no1)
    EditText etPersonNo1;
    @BindView(R.id.et_person_no2)
    EditText etPersonNo2;
    @BindView(R.id.bt_sign_first)
    Button btSignFirst;
    @BindView(R.id.img_sign_first)
    ImageView imgSignFirst;
    @BindView(R.id.img_sign_second)
    ImageView imgSignSecond;
    @BindView(R.id.bt_date)
    Button btDate;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.upload)
    Button upload;
    @BindView(R.id.rootView)
    ScrollView rootView;
    @BindView(R.id.lin_address)
    LinearLayout linAddress;
    @BindView(R.id.lin_kinds)
    LinearLayout linKinds;
    @BindView(R.id.lin_anim_number)
    LinearLayout linAnimNumber;
    @BindView(R.id.et_telephone_name)
    EditText etTelephoneName;
    @BindView(R.id.et_telephone_phone)
    EditText etTelephonePhone;
    @BindView(R.id.lin_telephone)
    LinearLayout linTelephone;
    @BindView(R.id.tv_modify_address)
    TextView tvModifyAddress;
    @BindView(R.id.tv_modify_principal)
    TextView tvModifyPrincipal;
    @BindView(R.id.lin_principal_phone)
    LinearLayout linPrincipalPhone;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.bt_sign_check1)
    Button btSignCheck1;
    @BindView(R.id.img_sign_check1)
    ImageView imgSignCheck1;
    @BindView(R.id.bt_sign_check2)
    Button btSignCheck2;
    @BindView(R.id.img_sign_check2)
    ImageView imgSignCheck2;
    @BindView(R.id.tv_title_picture)
    TextView tvTitlePicture;
    //    @BindView(R.id.bt_back)
//    ImageView btBack;
    private DatePickerDialog dateDialog;
    private CheckContentAdapter adapter;
    private int currentPosition;
    private ImageView currentImageView;
    private String uuid, takePhotoFilePath;
    private float density;
    //拍照
    PhotoViewModel photoViewModel;
    private String[] dirname={"yzc","tz","dwzljg","whh","gl","sl","sysc","syjy","sxr"};
    // 保存文件的名字
    String LIST_BASE_NAME, GROUP_PHOTO_NAME, SIGN_FIRST, SIGN_SECOND, SIGN_COMPANY1;

    private UploadCommonSupervisionBean uploadBean;
    private String glid;
    private List<CheckContentEntity> list3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_supervision_detil);
        Log.e("czy","oncreate==");
        ButterKnife.bind(this);
    }

    @Override
    protected CommonSupervisionDetilActivityPresenter createPresenter() {
        return new CommonSupervisionDetilActivityPresenter(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString("paths",takePhotoFilePath);
        outState.putSerializable("bean",uploadBean);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        takePhotoFilePath= (String) savedInstanceState.get("paths");
        uploadBean= (UploadCommonSupervisionBean) savedInstanceState.getSerializable("bean");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("czy","==onResume==");
    }

    @Override
    public void bindData() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        density = metrics.density;
        initFileName();
        uploadBean = new UploadCommonSupervisionBean();
        //设置拍照
        photoViewModel = new PhotoViewModel(this);
        photoViewModel.bindData(photoViewGroupPhoto, REQUEST_CODE_GROUP_PHOTO, GROUP_PHOTO_NAME, getCommonSupervisionDir(), path -> takePhotoFilePath = path);
        //设置时间
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        dateDialog = new DatePickerDialog(this, this, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        etDate.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date()));

        //设置adapter
        adapter = new CheckContentAdapter(this, getAdapterData(), (img, position) -> {
            currentPosition = position + 1;
            currentImageView = img;
            //注意reqeustCode
            takePhotoFilePath = PhotoViewModel.takePicture(CommonSupervisionDetilActivityActivity.this, position, LIST_BASE_NAME + currentPosition + SUFFIX, PhotoViewModel.getCommonSupervisionDir());
        }, new CheckContentAdapter.OnCheckChangeListener() {
            @Override
            public void deleteFlie(int position) {
                File file = new File(PhotoViewModel.getCommonSupervisionDir(), LIST_BASE_NAME + currentPosition + SUFFIX);
                if (file.exists()) {
                    file.delete();
                }
            }

            @Override
            public void inputText(int position, String etString) {
                Intent intent = new Intent(CommonSupervisionDetilActivityActivity.this, InputTextActivity.class);
                intent.putExtra("input", etString);
                startActivityForResult(intent, position);
            }
        }, getCurrentType(), new CheckContentAdapter.ItemChangeListener() {
            @Override
            public void HuiZhi() {
                adapter.notifyDataSetChanged();
                setPullLvHeight();
            }

        });
        lv.setAdapter(adapter);
//        lv.setPullLvHeight(lv);

        //设置检查人员1
        etName1.setText(getPresenter().getUser().getFUNAME());
        etPersonNo1.setText(getPresenter().getUser().getFUSEENAME());
    }
    private void setPullLvHeight() {
        ListAdapter listAdapter = lv.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(lv.getWidth(), View.MeasureSpec.AT_MOST);
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, lv);
//            listItem.measure(desiredWidth, desiredWidth); // 计算子项View 的宽高
            if (i == 3) {
                Log.e("aaaaa1", listItem.getHeight() + "");
                Log.e("aaaaa", listItem.getMeasuredHeight() + "");
            }
            listItem.measure(desiredWidth, View.MeasureSpec.makeMeasureSpec(100000, View.MeasureSpec.AT_MOST));
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        lv.setLayoutParams(params);
    }

    private void initFileName() {
        uuid = UUID.randomUUID().toString();
        LIST_BASE_NAME = uuid + "_P";
        GROUP_PHOTO_NAME = uuid + "_A1" + SUFFIX;
        SIGN_FIRST = uuid + "_A2" + SUFFIX;
        SIGN_SECOND = uuid + "_A3" + SUFFIX;
        SIGN_COMPANY1 = uuid + "_A4" + SUFFIX;
    }

    private String scan_table, uploadTable, signFirst;

    private void initBaseMsg(String name, String tvKind, String count, String signFirst, String titleName, String address, String principal) {
        setTitle(titleName);
        tvModifyName.setText(name);
        tvModifyPrincipal.setText(TextUtils.isEmpty(principal) ? "法定代表人(负责人)" : principal);
        tvModifyNumber.setText(count);
        tvModifyAddress.setText(TextUtils.isEmpty(address) ? "地址" : address);
        btSignFirst.setText(signFirst);
        this.signFirst = signFirst;
        this.tvKind.setText(tvKind);
    }


    @Override
    public void bindListener() {

        //合照
        imgGroupPhoto.setOnClickListener(v -> takePhotoFilePath = PhotoViewModel.takePicture(this, REQUEST_CODE_GROUP_PHOTO, GROUP_PHOTO_NAME, getCommonSupervisionDir()));
        btErcode.setOnClickListener(v -> startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_ACTIVITY_ZXING));
        btDate.setOnClickListener(v -> dateDialog.show());
        upload.setOnClickListener(v -> {
/*            setUploadData();
            openPrintDialog();*/
            if (check()) {
                for (int i = 0; i < adapter.getDatas().size(); i++) {
                    if (!TextUtils.isEmpty(adapter.getDatas().get(i).getCs())) {
                        String cs = adapter.getDatas().get(i).getCs();
                        adapter.getDatas().get(i).setQK(cs.substring(0, cs.length() - 1));
                    }
                }
                setUploadData();
                getPresenter().uploadCommonSupervision(uploadTable,
                        new Gson().toJson(uploadBean), uuid);
            }
        });

        btSignCheck1.setOnClickListener(v -> {
            new CcWriteDiolog(this, new DialogListener() {
                @Override
                public void refreshActivity(final Object object) {
                    new Thread(() -> {
                        File signCheckFile = new File(getCommonSupervisionDir(), SIGN_FIRST);
                        if (signCheckFile.exists()) signCheckFile.delete();
                        PhotoViewModel.saveBitmap(signCheckFile.getAbsolutePath(), (Bitmap) object);
                    }).start();
                    imgSignCheck1.setImageBitmap((Bitmap) object);
                }
            }).show();
        });
        btSignCheck2.setOnClickListener(v -> {
            new CcWriteDiolog(this, new DialogListener() {
                @Override
                public void refreshActivity(final Object object) {
                    new Thread(() -> {
                        File signCheckFile = new File(getCommonSupervisionDir(), SIGN_SECOND);
                        if (signCheckFile.exists()) signCheckFile.delete();
                        PhotoViewModel.saveBitmap(signCheckFile.getAbsolutePath(), (Bitmap) object);
                    }).start();
                    imgSignCheck2.setImageBitmap((Bitmap) object);
                }
            }).show();
        });
        btSignFirst.setOnClickListener(v -> {
            new CcWriteDiolog(this, new DialogListener() {
                @Override
                public void refreshActivity(final Object object) {
                    new Thread(() -> {
                        File signCheckFile = new File(getCommonSupervisionDir(), SIGN_COMPANY1);
                        if (signCheckFile.exists()) signCheckFile.delete();
                        PhotoViewModel.saveBitmap(signCheckFile.getAbsolutePath(), (Bitmap) object);
                    }).start();
                    imgSignFirst.setImageBitmap((Bitmap) object);
                }
            }).show();
        });
    }


    private void setUploadData() {
        uploadBean.setCheckItems(adapter.getDatas());
        uploadBean.setJcclyj(etCheckAdvise.getText().toString());
        uploadBean.setJcryxmyi(etName1.getText().toString());
        uploadBean.setZfzhyi(etPersonNo1.getText().toString());
        uploadBean.setJcryxmer(etName2.getText().toString());
        uploadBean.setZfzher(etPersonNo2.getText().toString());
        uploadBean.setJcdate(etDate.getText().toString());
    }

    private boolean check() {
        if (getCurrentType() != TABLE_2 && getCurrentType() != TABLE_6 && adapter.check()) {
            return false;
        }
        if (TextUtils.isEmpty(etFeedName.getText().toString())) {
            Toast.makeText(this, "扫码信息错误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (photoViewGroupPhoto.getDrawable() == null) {
            Toast.makeText(this, "合照必须拍摄", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (imgSignFirst.getDrawable() == null) {
            Toast.makeText(this, signFirst + "必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (imgSignCheck1.getDrawable() == null) {
            Toast.makeText(this, "检查人员1必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (imgSignCheck2.getDrawable() == null) {
            Toast.makeText(this, "检察人员2必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode < 100) {
                //listview里面的请求
                if (data != null) {
                    adapter.getDatas().get(requestCode).setQK(TextUtils.isEmpty(data.getStringExtra("return")) ? "" : data.getStringExtra("return"));
                    adapter.notifyDataSetChanged();
                    return;
                }
                PhotoViewModel.compressBmpToFile2(takePhotoFilePath,1);
                File file=new File(takePhotoFilePath);
                Bitmap bitmap = BitmapFactory.decodeFile(takePhotoFilePath);
                if (bitmap != null) {
                    //设置图片名字
                    adapter.getDatas().get(currentPosition - 1).setTp(LIST_BASE_NAME + currentPosition + SUFFIX);
                    currentImageView.setImageBitmap(bitmap);
                }
               /* Glide.with(MyApplication.getContext()).load(file).skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(currentImageView);*/
               // adapter.getDatas().get(currentPosition - 1).
                //拍照的文件按768*1024 保存 显示的按 60*density * 60*density显示
               /* Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoViewModel.compressBmpToFile(takePhotoFilePath,
                        (int) density * 60, (int) density * 60));
                if (bitmap != null) {
                    //设置图片名字
                    adapter.getDatas().get(currentPosition - 1).setTp(LIST_BASE_NAME + currentPosition + SUFFIX);
                    currentImageView.setImageBitmap(bitmap);
                }*/
            } else {
                if (data != null && data.getExtras() != null) {
                    String result = data.getExtras().getString("result");
                    result = new String(Base64.decode(result, Base64.DEFAULT));
                    Log.e("CommonSupervisionDetilA", result);
                    glid = result.split("-")[1];
                    if (requestCode == REQUEST_CODE_ACTIVITY_ZXING)
                        getPresenter().getCompanyDetil(scan_table, glid);
                } else if (requestCode == REQUEST_CODE_GROUP_PHOTO) {
                    photoViewGroupPhoto.setImageBitmap(decodeSampleBitmapFromFile(PhotoViewModel.compressBmpToFileWithSave(takePhotoFilePath)));
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private int getCurrentType() {
        Intent intent = getIntent();
        if (intent == null) {
            return TABLE_1;
        }
        return getIntent().getIntExtra("type", TABLE_1);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String time = year + "-" + (month + 1) + "-" + dayOfMonth;
        etDate.setText(time);
    }

    @Override
    public void setCompanyDetil(CommonSupervisionBean.Data data) {
        switch (getCurrentType()) {
            case TABLE_1:
            case TABLE_3:
            case TABLE_4:
            case TABLE_5:
            case TABLE_9:
                //养殖场名字
                etFeedName.setText(data.getFFarmName());
                uploadBean.setFName(data.getFFarmName());
                uploadBean.setGlid(getGlid());
                //地址
                etAddress.setText(data.getFCityAdd());
                uploadBean.setFCityAdd(data.getFCityAdd());
                //养殖动物种类
                etKinds.setText(data.getFCategory());
                uploadBean.setFCategory(data.getFCategory());
                //存栏动物数量
                etAnimNumber.setText(data.getClsl());
                uploadBean.setClsl(data.getClsl());
                // 法定代表人/负责人
                etPrincipalName.setText(data.getFLegalPerson());
                uploadBean.setFLegalPerson(data.getFLegalPerson());
                //电话
                etPrincipalPhone.setText(data.getFPhone());
                uploadBean.setFPhone(data.getFPhone());
                break;
            case TABLE_2:
                //屠宰企业
                etFeedName.setText(data.getFFarmName());
                uploadBean.setFName(data.getFFarmName());
                uploadBean.setGlid(getGlid());
                //地址
                etAddress.setText(data.getFCityAdd());
                uploadBean.setFCityAdd(data.getFCityAdd());
                //负责人
                etPrincipalName.setText(data.getFLegalPerson());
                uploadBean.setFLegalPerson(data.getFLegalPerson());
                //电话
                etPrincipalPhone.setText(data.getFPhone());
                uploadBean.setFPhone(data.getFPhone());
                break;
            case TABLE_6:
                //企业名称
                etFeedName.setText(data.getFFarmName());
                uploadBean.setFName(data.getFFarmName());
                uploadBean.setGlid(getGlid());
                //生产地址
                etAddress.setText(data.getFCityAdd());
                uploadBean.setFCityAdd(data.getFCityAdd());
                //养殖动物种类
                etKinds.setText(data.getFCategory());
                uploadBean.setFCategory(data.getFCategory());
                //存栏动物数量
                etAnimNumber.setText(data.getClsl());
                uploadBean.setClsl(data.getClsl());
                // 法定代表人/负责人
                etPrincipalName.setText(data.getFLegalPerson());
                uploadBean.setFLegalPerson(data.getFLegalPerson());
                //联系人
                etTelephoneName.setText(data.getFCategory());
                uploadBean.setFCategory(data.getFCategory());
                //电话
                etTelephonePhone.setText(data.getFPhone());
                uploadBean.setFPhone(data.getFPhone());
                break;
            case TABLE_7:
            case TABLE_8:
                //企业名称
                etFeedName.setText(data.getFFarmName());
                uploadBean.setFName(data.getFFarmName());
                uploadBean.setGlid(getGlid());
                //地址
                etAddress.setText(data.getFCityAdd());
                uploadBean.setFCityAdd(data.getFCityAdd());
                //养殖动物种类
                etKinds.setText(data.getFCategory());
                uploadBean.setFCategory(data.getFCategory());
                //存栏动物数量
                etAnimNumber.setText(data.getClsl());
                uploadBean.setClsl(data.getClsl());
                // 法定代表人/负责人
                etPrincipalName.setText(data.getFLegalPerson());
                uploadBean.setFLegalPerson(data.getFLegalPerson());
                //电话
                etPrincipalPhone.setText(data.getFPhone());
                uploadBean.setFPhone(data.getFPhone());
                //联系人
                etTelephoneName.setText(data.getFCategory());
                uploadBean.setFCategory(data.getFCategory());
                //电话
                etTelephonePhone.setText(data.getClsl());
                uploadBean.setClsl(data.getClsl());
                break;
        }
    }

    @Override
    public void uploadSuccess(BaseMsgBean.Data data) {
        RxBus.getInstance().post(new RefreshBus(RefreshBus.Permission));
        //遍历文件夹文件
        for (File file : PhotoViewModel.getCommonSupervisionDir().listFiles()) {
            if (file.getName().contains(uuid)) {
                //拷贝
                PhotoViewModel.copyToUpload(file);
                Intent intent = new Intent(this, UploadPictureService.class);
                intent.setAction(UploadPictureService.ADD_TASK);
                intent.putExtra("localName", file.getName());
                intent.putExtra("address", PhotoViewModel.getUploadDirPath());
                intent.putExtra("uuid", uuid);
                startService(intent);
            }
        }
        Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        openPrintDialog();
      /*  Intent intent=new Intent(CommonSupervisionDetilActivityActivity.this, PrintAcitivty.class);
        Bundle bundle=new Bundle();
        bundle.putString(Constance.START_ACTIVITY_TYPE,String.valueOf(getCurrentType()));
        bundle.putSerializable(Constance.ACTIVITY_DATA,uploadBean);
        intent.putExtras(bundle);
        startActivity(intent);

        //删除
       // PhotoViewModel.deleteFile(PhotoViewModel.getCommonSupervisionDir());
        finish();*/
    }

    @Override
    public void downImgSuccess() {
        switch (getCurrentType()){
            case TABLE_1:
            //4
                if (FileUtil.getInstance().getPrintBGSize("yz")==4)
                    skipActivity();
                break;
            case TABLE_2:
            //2
                if (FileUtil.getInstance().getPrintBGSize("tz")==2)
                    skipActivity();
                break;
            case TABLE_3:
            //3
                if (FileUtil.getInstance().getPrintBGSize("dwzlcs")==3)
                    skipActivity();
                break;
            case TABLE_4:
            //4
                if (FileUtil.getInstance().getPrintBGSize("wuhaihua")==4)
                    skipActivity();
                break;
            case TABLE_5:
            //4
                if (FileUtil.getInstance().getPrintBGSize("geli")==4)
                    skipActivity();
                break;
            case TABLE_6:
            //2
                if (FileUtil.getInstance().getPrintBGSize("siliao")==2)
                    skipActivity();
                break;
            case TABLE_7:
            //2
                if (FileUtil.getInstance().getPrintBGSize("sysc")==2)
                    skipActivity();
                break;
            case TABLE_8:
            //3
                if (FileUtil.getInstance().getPrintBGSize("syjy")==3)
                    skipActivity();
                break;
            case TABLE_9:
            //2
                if (FileUtil.getInstance().getPrintBGSize("sxr")==2)
                    skipActivity();
                break;
            default:
                break;
        }

    }
    public void skipActivity(){
        Intent intent=new Intent(CommonSupervisionDetilActivityActivity.this, PrintAcitivty.class);
        Bundle bundle=new Bundle();
        bundle.putString(Constance.START_ACTIVITY_TYPE,String.valueOf(getCurrentType()));
        bundle.putSerializable(Constance.ACTIVITY_DATA,uploadBean);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @Override
    public void downImgFail() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View viewAddEmployee = layoutInflater.inflate(
                R.layout.tishi_print_dialog, null);
        new AlertDialog.Builder(this)
                .setTitle("下载提示")
                .setCancelable(false)
                .setView(viewAddEmployee)
                .setPositiveButton("重新下载", (dialog, which) -> {
                    //getPresenter().downPrintImg("http://pan.baidu.com/s/1mi0vzL2");
                })
               .show();
    }

    public void openPrintDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View viewAddEmployee = layoutInflater.inflate(
                R.layout.tishi_activity, null);
        new AlertDialog.Builder(this)
                .setTitle("是否打印")
                .setCancelable(false)
                .setView(viewAddEmployee)
                .setPositiveButton("是", (dialog, which) -> {
                    if (isDown()) {
                        List<String> urllist = getUrllist();
                        List<String> fileNames = getFileNames();
                        for (int i=0;i<urllist.size();i++)
                        getPresenter().downPrintImg(urllist.get(i), fileNames.get(i));

                    }else{
                        skipActivity();
                    }
                })
                .setNegativeButton("否", (dialog, which) -> {
                    finish();
                }).show();
    }
    public List<String> getFileNames(){
        List<String> list=new ArrayList<>();
        switch (getCurrentType()){
            case TABLE_1:
                //4
                list.add("yz_img1.jpg");
                list.add("yz_img2.jpg");
                list.add("yz_img3.jpg");
                list.add("yz_img4.jpg");
                break;
            case TABLE_2:
                //2
                list.add("tz_1.jpg");
                list.add("tz_2.jpg");
                break;
            case TABLE_3:
                //3
                list.add("dwzlcs_1.jpg");
                list.add("dwzlcs_2.jpg");
                list.add("dwzlcs_3.jpg");
                break;
            case TABLE_4:
                //4
                list.add("wuhaihua_1.jpg");
                list.add("wuhaihua_2.jpg");
                list.add("wuhaihua_3.jpg");
                list.add("wuhaihua_4.jpg");
                break;
            case TABLE_5:
                //4
                list.add("geli_1.jpg");
                list.add("geli_2.jpg");
                list.add("geli_3.jpg");
                list.add("geli_4.jpg");
                break;
            case TABLE_6:
                //2
                list.add("siliao_1.jpg");
                list.add("siliao_2.jpg");

                break;
            case TABLE_7:
                //2
                list.add("sysc_1.jpg");
                list.add("sysc_2.jpg");

                break;
            case TABLE_8:
                //3
                list.add("syjy_1.jpg");
                list.add("syjy_2.jpg");
                list.add("syjy_3.jpg");

                break;
            case TABLE_9:
                //2
                list.add("sxr_1.jpg");
                list.add("sxr_2.jpg");
                break;
            default:
                break;
        }
        return list;
    }
    private String BASE_URL=NetClient.IMG_PRE+"/PrintImgs"+"/";
    public List<String> getUrllist(){
        List<String> list=new ArrayList<>();
        switch (getCurrentType()){
            case TABLE_1:
                //4
                list.add(BASE_URL+dirname[0]+"/yz_img1.jpg");
                list.add(BASE_URL+dirname[0]+"/yz_img2.jpg");
                list.add(BASE_URL+dirname[0]+"/yz_img3.jpg");
                list.add(BASE_URL+dirname[0]+"/yz_img4.jpg");
                break;
            case TABLE_2:
                //2
                list.add(BASE_URL+dirname[1]+"/tz_1.jpg");
                list.add(BASE_URL+dirname[1]+"/tz_2.jpg");
                break;
            case TABLE_3:
                //3
                list.add(BASE_URL+dirname[2]+"/dwzlcs_1.jpg");
                list.add(BASE_URL+dirname[2]+"/dwzlcs_2.jpg");
                list.add(BASE_URL+dirname[2]+"/dwzlcs_3.jpg");
                break;
            case TABLE_4:
                //4
                list.add(BASE_URL+dirname[3]+"/wuhaihua_1.jpg");
                list.add(BASE_URL+dirname[3]+"/wuhaihua_2.jpg");
                list.add(BASE_URL+dirname[3]+"/wuhaihua_3.jpg");
                list.add(BASE_URL+dirname[3]+"/wuhaihua_4.jpg");
                break;
            case TABLE_5:
                //4
                list.add(BASE_URL+dirname[4]+"/geli_1.jpg");
                list.add(BASE_URL+dirname[4]+"/geli_2.jpg");
                list.add(BASE_URL+dirname[4]+"/geli_3.jpg");
                list.add(BASE_URL+dirname[4]+"/geli_4.jpg");
                break;
            case TABLE_6:
                //2
                list.add(BASE_URL+dirname[5]+"/siliao_1.jpg");
                list.add(BASE_URL+dirname[5]+"/siliao_2.jpg");

                break;
            case TABLE_7:
                //2
                list.add(BASE_URL+dirname[6]+"/sysc_1.jpg");
                list.add(BASE_URL+dirname[6]+"/sysc_2.jpg");

                break;
            case TABLE_8:
                //3
                list.add(BASE_URL+dirname[7]+"/syjy_1.jpg");
                list.add(BASE_URL+dirname[7]+"/syjy_2.jpg");
                list.add(BASE_URL+dirname[7]+"/syjy_3.jpg");

                break;
            case TABLE_9:
                //2
                list.add(BASE_URL+dirname[8]+"/sxr_1.jpg");
                list.add(BASE_URL+dirname[8]+"/sxr_2.jpg");
                break;
            default:
                break;
        }
        return list;
    }
    private boolean isDown(){
        Boolean b=true;
        switch (getCurrentType()){
            case TABLE_1:
                //4
                if (FileUtil.getInstance().getPrintBGSize("yz")==4)
                    b=false;
                break;
            case TABLE_2:
                //2
                if (FileUtil.getInstance().getPrintBGSize("tz")==2)
                    b=false;
                break;
            case TABLE_3:
                //3
                if (FileUtil.getInstance().getPrintBGSize("dwzlcs")==3)
                    b=false;
                break;
            case TABLE_4:
                //4
                if (FileUtil.getInstance().getPrintBGSize("wuhaihua")==4)
                    b=false;
                break;
            case TABLE_5:
                //4
                if (FileUtil.getInstance().getPrintBGSize("geli")==4)
                    b=false;
                break;
            case TABLE_6:
                //2
                if (FileUtil.getInstance().getPrintBGSize("siliao")==2)
                   b=false;
                break;
            case TABLE_7:
                //2
                if (FileUtil.getInstance().getPrintBGSize("sysc")==2)
                    b=false;
                break;
            case TABLE_8:
                //3
                if (FileUtil.getInstance().getPrintBGSize("syjy")==3)
                    b=false;
                break;
            case TABLE_9:
                //2
                if (FileUtil.getInstance().getPrintBGSize("sxr")==2)
                    b=false;
                break;
            default:
                break;
        }
        return b;
    }
    /**
     * 处理adaptger 以及签名
     *
     * @return
     */
    private List<CheckContentEntity> getAdapterData() {
        addCommonSigns();
        ArrayList<CheckContentEntity> mDatas = new ArrayList<>();
        String[] checkContent = null;
        String[] inconformity = null;
        switch (getCurrentType()) {
            case TABLE_1:
                scan_table = "V_AH_FarmsRecord";
                uploadTable = "Ds_GmyzcjcjlS";
                initBaseMsg("养殖场所名称", "养殖动物种类 ", "存栏动物数量", "规模场负责人员签字", Constance.Item1_title, "", "");
                checkContent = new String[]{Constance.Item1_1, Constance.Item1_2, Constance.Item1_3, Constance.Item1_4, Constance.Item1_5,
                        Constance.Item1_6, Constance.Item1_7, Constance.Item1_8, Constance.Item1_9, Constance.Item1_10, Constance.Item1_11,
                        Constance.Item1_12, Constance.Item1_13, Constance.Item1_14, Constance.Item1_15, Constance.Item1_16, Constance.Item1_17,
                        Constance.Item1_18, Constance.Item1_19, Constance.Item1_20, Constance.Item1_21, Constance.Item1_22, Constance.Item1_23,
                        Constance.Item1_24, Constance.Item1_25, Constance.Item1_26};
                inconformity = new String[]{Constance.Item1_4_XZ1, Constance.Item1_4_XZ2, Constance.Item1_4_XZ3, Constance.Item1_4_XZ4,
                        Constance.Item1_4_XZ5, Constance.Item1_4_XZ6, Constance.Item1_4_XZ7, Constance.Item1_4_XZ8,
                        Constance.Item1_4_XZ9, Constance.Item1_9_XZ1, Constance.Item1_9_XZ2, Constance.Item1_9_XZ3,
                        Constance.Item1_9_XZ4, Constance.Item1_9_XZ5, Constance.Item1_9_XZ6, Constance.Item1_9_XZ7};
                break;
            case TABLE_2:
                scan_table = "V_AH_SlaughterBk";
                uploadTable = "Ds_SztzcrcS";
//                linAddress.setVisibility(View.GONE);
                linAnimNumber.setVisibility(View.GONE);
                linKinds.setVisibility(View.GONE);
                //隐藏图片拍照
                tvTitlePicture.setVisibility(View.GONE);
                findViewById(R.id.split_title_picture).setVisibility(View.GONE);
                initBaseMsg("屠宰厂(场)名称", " ", "", "厂方负责人员(签字)", Constance.Item2_title, "", "负责人");
                checkContent = new String[]{Constance.Item2_1, Constance.Item2_2, Constance.Item2_3, Constance.Item2_4, Constance.Item2_5,
                        Constance.Item2_6, Constance.Item2_7, Constance.Item2_8, Constance.Item2_9, Constance.Item2_10, Constance.Item2_11,
                        Constance.Item2_12, Constance.Item2_13, Constance.Item2_14, Constance.Item2_15, Constance.Item2_16, Constance.Item2_17,
                        Constance.Item2_18, Constance.Item2_19, Constance.Item2_20, Constance.Item2_21, Constance.Item2_22, Constance.Item2_23,
                        Constance.Item2_24, Constance.Item2_25, Constance.Item2_26, Constance.Item2_27, Constance.Item2_28, Constance.Item2_29};
                inconformity = new String[]{};
                break;
            case TABLE_3:
                scan_table = "V_AH_MedicalInstitutions";
                uploadTable = "Ds_ZlcsjcjlS";
                initBaseMsg("场所名称", "诊疗动物种类", "每日可诊疗动物数量", "诊疗机构负责人员签字", Constance.Item3_title, "", "");
                checkContent = new String[]{Constance.Item3_1, Constance.Item3_2, Constance.Item3_3, Constance.Item3_4, Constance.Item3_5,
                        Constance.Item3_6, Constance.Item3_7, Constance.Item3_8, Constance.Item3_9, Constance.Item3_10, Constance.Item3_11,
                        Constance.Item3_12, Constance.Item3_13};
                inconformity = new String[]{Constance.Item3_5_XZ1, Constance.Item3_5_XZ2, Constance.Item3_5_XZ3, Constance.Item3_5_XZ4,
                        Constance.Item3_5_XZ5, Constance.Item3_6_XZ1, Constance.Item3_6_XZ2, Constance.Item3_6_XZ3, Constance.Item3_8_XZ1,
                        Constance.Item3_8_XZ2, Constance.Item3_8_XZ3, Constance.Item3_8_XZ4, Constance.Item3_8_XZ5};
                break;
            case TABLE_4:
                scan_table = "V_AH_Basic";
                uploadTable = "Ds_WhhjgjlS";
                initBaseMsg("场所名称", "无害化动物种类", "日处理能力", "无害化处理场负责人员签字", Constance.Item4_title, "", "");
                checkContent = new String[]{Constance.Item4_1, Constance.Item4_2, Constance.Item4_3, Constance.Item4_4, Constance.Item4_5,
                        Constance.Item4_6, Constance.Item4_7, Constance.Item4_8, Constance.Item4_9, Constance.Item4_10, Constance.Item4_11,
                        Constance.Item4_12, Constance.Item4_13, Constance.Item4_14, Constance.Item4_15};
                inconformity = new String[]{Constance.Item4_3_XZ1, Constance.Item4_3_XZ2, Constance.Item4_3_XZ3, Constance.Item4_3_XZ4,
                        Constance.Item4_3_XZ5, Constance.Item4_3_XZ6, Constance.Item4_3_XZ7, Constance.Item4_3_XZ8, Constance.Item4_10_XZ1,
                        Constance.Item4_10_XZ2, Constance.Item4_10_XZ3, Constance.Item4_10_XZ4, Constance.Item4_10_XZ5};
                break;
            case TABLE_5:
                scan_table = "V_AH_GLCXX";
                uploadTable = "Ds_GlcsjcjlS";
                initBaseMsg("隔离场所名称", "隔离动物种类", "每日可隔离动物数量", "隔离场负责人员签字", Constance.Item5_title, "", "");
                checkContent = new String[]{Constance.Item5_1, Constance.Item5_2, Constance.Item5_3, Constance.Item5_4, Constance.Item5_5,
                        Constance.Item5_6, Constance.Item5_7, Constance.Item5_8, Constance.Item5_9, Constance.Item5_10, Constance.Item5_11,
                        Constance.Item5_12, Constance.Item5_13, Constance.Item5_14, Constance.Item5_15, Constance.Item5_16};
                inconformity = new String[]{Constance.Item5_3_XZ1, Constance.Item5_3_XZ2, Constance.Item5_3_XZ3, Constance.Item5_3_XZ4,
                        Constance.Item5_3_XZ5, Constance.Item5_3_XZ6, Constance.Item5_3_XZ7, Constance.Item5_3_XZ8, Constance.Item5_3_XZ9,
                        Constance.Item5_6_XZ1, Constance.Item5_6_XZ2, Constance.Item5_6_XZ3, Constance.Item5_6_XZ4, Constance.Item5_6_XZ5,
                        Constance.Item5_6_XZ6};
                break;
            case TABLE_6:
                scan_table = "V_SL_FProdBasicInfor";
                uploadTable = "Ds_SlscqyjcjlS";
                linPrincipalPhone.setVisibility(View.GONE);
                linAnimNumber.setVisibility(View.GONE);
                linTelephone.setVisibility(View.VISIBLE);
                //隐藏图片拍照
                tvTitlePicture.setVisibility(View.GONE);
                findViewById(R.id.split_title_picture).setVisibility(View.GONE);
                initBaseMsg("企业名称", "企业代码 ", "", "企业负责人签章", Constance.Item6_title, "生产地址", "法定代表人");
                checkContent = new String[]{Constance.Item6_1, Constance.Item6_2, Constance.Item6_3, Constance.Item6_4, Constance.Item6_5,
                        Constance.Item6_6, Constance.Item6_7, Constance.Item6_8, Constance.Item6_9, Constance.Item6_10, Constance.Item6_11,
                        Constance.Item6_12, Constance.Item6_13, Constance.Item6_14, Constance.Item6_15, Constance.Item6_16, Constance.Item6_17,
                        Constance.Item6_18, Constance.Item6_19};
                inconformity = new String[]{};
                break;
            case TABLE_7:
                scan_table = "V_AH_QYJBQK";
                uploadTable = "Ds_SyscqyjcjlS";
                linAnimNumber.setVisibility(View.GONE);
                linKinds.setVisibility(View.GONE);
                linTelephone.setVisibility(View.VISIBLE);
                findViewById(R.id.split_telephone).setVisibility(View.VISIBLE);
                initBaseMsg("企业名称", " ", "", "企业负责人签章", Constance.Item7_title, "", "法定代表人");
                checkContent = new String[]{Constance.Item7_1, Constance.Item7_2, Constance.Item7_3, Constance.Item7_4, Constance.Item7_5,
                        Constance.Item7_6, Constance.Item7_7, Constance.Item7_8, Constance.Item7_9, Constance.Item7_10, Constance.Item7_11,
                        Constance.Item7_12, Constance.Item7_13, Constance.Item7_14};
                inconformity = new String[]{};
                break;
            case TABLE_8:
                scan_table = "V_AH_FirmRun";
                uploadTable = "Ds_SyjyqyjcjlS";
                linAnimNumber.setVisibility(View.GONE);
                linKinds.setVisibility(View.GONE);
                linTelephone.setVisibility(View.VISIBLE);
                findViewById(R.id.split_telephone).setVisibility(View.VISIBLE);
                initBaseMsg("企业名称", " ", "", "企业负责人签章", Constance.Item8_title, "", "法定代表人");
                checkContent = new String[]{Constance.Item8_1, Constance.Item8_2, Constance.Item8_3, Constance.Item8_4, Constance.Item8_5,
                        Constance.Item8_6, Constance.Item8_7, Constance.Item8_8, Constance.Item8_9, Constance.Item8_10, Constance.Item8_11,
                        Constance.Item8_12, Constance.Item8_13, Constance.Item8_14, Constance.Item8_15, Constance.Item8_16, Constance.Item8_17,
                        Constance.Item8_18};
                inconformity = new String[]{};
                break;
            case TABLE_9:
                scan_table = "V_SXR_Sgzhan";
                uploadTable = "SXR_SgjcjlS";
                initBaseMsg("收购站名称", "收购生鲜乳种类 ", "日收奶量", "企业签字", Constance.Item9_title, "收购站地址", "被查单位负责人");
                checkContent = new String[]{Constance.Item9_1, Constance.Item9_2, Constance.Item9_3, Constance.Item9_4, Constance.Item9_5,
                        Constance.Item9_6, Constance.Item9_7, Constance.Item9_8, Constance.Item9_9, Constance.Item9_10, Constance.Item9_11,
                        Constance.Item9_12, Constance.Item9_13, Constance.Item9_14, Constance.Item9_15, Constance.Item9_16, Constance.Item9_17,
                        Constance.Item9_18, Constance.Item9_19, Constance.Item9_20, Constance.Item9_21, Constance.Item9_22, Constance.Item9_23,
                        Constance.Item9_24, Constance.Item9_25, Constance.Item9_26};
                inconformity = new String[]{};
                break;
        }
        for (int i = 0; i < checkContent.length; i++) {
            CheckContentEntity entity = new CheckContentEntity();
            entity.setName(checkContent[i]);
            entity.setRbCheckYes("是").setRbCheckNo("否");
            switch (getCurrentType()) {
                case TABLE_1:
                    List<CheckContentEntity.DataEntity> list = new ArrayList<>();
                    if (i == 3) {
                        for (int j = 0; j < 9; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list.add(dataEntity);
                        }
                        entity.setList(list);
                    }
                    if (i == 8) {
                        for (int j = 9; j < inconformity.length; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list.add(dataEntity);
                        }
                        entity.setList(list);
                    }
                    break;
                case TABLE_2:
                    if (i == 0 || i == 1) {
                        entity.setRbCheckYes("能");
                    }
                    break;
                case TABLE_3:
                    List<CheckContentEntity.DataEntity> list1 = new ArrayList<>();
                    if (i == 4) {
                        for (int j = 0; j < 5; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list1.add(dataEntity);
                        }
                        entity.setList(list1);
                    }
                    if (i == 5) {
                        for (int j = 5; j < 8; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list1.add(dataEntity);
                        }
                        entity.setList(list1);
                    }
                    if (i == 7) {
                        for (int j = 8; j < inconformity.length; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list1.add(dataEntity);
                        }
                        entity.setList(list1);
                    }
                    break;
                case TABLE_4:
                    List<CheckContentEntity.DataEntity> list2 = new ArrayList<>();
                    if (i == 2) {
                        for (int j = 0; j < 8; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list2.add(dataEntity);
                        }
                        entity.setList(list2);
                    }
                    if (i == 9) {
                        for (int j = 8; j < inconformity.length; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list2.add(dataEntity);
                        }
                        entity.setList(list2);
                    }
                    break;
                case TABLE_5:
                    List<CheckContentEntity.DataEntity> list3 = new ArrayList<>();
                    if (i == 2) {
                        for (int j = 0; j < 9; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list3.add(dataEntity);
                        }
                        entity.setList(list3);
                    }
                    if (i == 5) {
                        for (int j = 9; j < inconformity.length; j++) {
                            CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                            dataEntity.setXZ(inconformity[j]);
                            list3.add(dataEntity);
                        }
                        entity.setList(list3);
                    }
                    break;

            }
            entity.setJL(entity.getRbCheckYes());
            mDatas.add(entity);
        }
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getList() != null) {
                for (int n = 0; n < mDatas.get(i).getList().size(); n++) {
                    if (mDatas.get(i).getList().get(n).isCheek()) {
                        mDatas.get(i).getList().get(n).setCheek(true);
                    }
                }
            }
        }
        //mDatas  表格的全部数据
        //mDatas.get()  表格的某一条数据的全部数据
        //mDatas.get().getList 表格的某一条数据的不符合情况的集合
        //mDatas.get().getList.get() 表格的某一条数据的不符合情况的集合,集合中的某一条
        //mDatas.get().getList.get()。isCheek 表格的某一条数据的不符合情况的集合,判断集合中的某一条是否被点击
        mDatas.add(new CheckContentEntity());
        return mDatas;
    }

    private String getGlid() {
        return glid;
    }

    private void addSignItem(List<UploadCommonSupervisionBean.SignPicNames> signPicNames, String name) {
        UploadCommonSupervisionBean.SignPicNames data = new UploadCommonSupervisionBean.SignPicNames();
        data.setQz(name);
        signPicNames.add(data);
    }

    /**
     * 普通的签名有3个
     */
    private void addCommonSigns() {
        List<UploadCommonSupervisionBean.SignPicNames> signPicNames = new ArrayList<>();
        addSignItem(signPicNames, GROUP_PHOTO_NAME);
        addSignItem(signPicNames, SIGN_FIRST);
        addSignItem(signPicNames, SIGN_SECOND);
        addSignItem(signPicNames, SIGN_COMPANY1);
        uploadBean.setSignPicNames(signPicNames);
    }

    @Deprecated
    private void addTuZaiSigns() {
        List<UploadCommonSupervisionBean.SignPicNames> signPicNames = new ArrayList<>();
        addSignItem(signPicNames, GROUP_PHOTO_NAME);
        addSignItem(signPicNames, SIGN_FIRST);
        addSignItem(signPicNames, SIGN_SECOND);
        addSignItem(signPicNames, SIGN_COMPANY1);
        uploadBean.setSignPicNames(signPicNames);
    }
}
