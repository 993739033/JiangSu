package com.wyw.jiangsu.activity.supervision;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.InputTextActivity;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.activity.PrintAcitivty;
import com.wyw.jiangsu.activity.model.PhotoViewModel;
import com.wyw.jiangsu.adapter.CommonSupervisionDetilAdapter;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.CommonSuperBottomBean;
import com.wyw.jiangsu.bean.CommonSuperTopBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.bean.CommonSupervisionQyBean;
import com.wyw.jiangsu.bean.TransforBean;
import com.wyw.jiangsu.bean.local.CheckContentEntity;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.bus.RefreshBus;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.DialogListener;
import com.wyw.jiangsu.interfac.ICommonSupervisionDetil;
import com.wyw.jiangsu.presenter.CommonSupervisionDetilPresenter;
import com.wyw.jiangsu.runnable.NetClient;
import com.wyw.jiangsu.runnable.RxBus;
import com.wyw.jiangsu.service.UploadPictureService;
import com.wyw.jiangsu.utils.CcWriteDiolog;
import com.wyw.jiangsu.utils.FileUtil;
import com.wyw.jiangsu.utils.PhotoUtils;
import com.wyw.jiangsu.utils.SPUtils;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

import static com.wyw.jiangsu.activity.model.PhotoViewModel.SUFFIX;

public class CommonSupervisionDetilActivity extends MVPActivity<CommonSupervisionDetilPresenter> implements
        ICommonSupervisionDetil, PhotoViewModel.OnClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private CommonSupervisionDetilAdapter adapter;
    public static final int REQUEST_CODE_ACTIVITY_ZXING = 1000;
    public static final int REQUEST_CODE_ACTIVITY_QY = 900;
    public static final int TABLE_1 = 1;
    public static final int TABLE_2 = 2;
    public static final int TABLE_3 = 3;
    public static final int TABLE_4 = 4;
    public static final int TABLE_5 = 5;
    public static final int TABLE_6 = 6;
    public static final int TABLE_7 = 7;
    public static final int TABLE_8 = 8;
    public static final int TABLE_9 = 9;
    private String[] dirname = {"yzc", "tz", "dwzljg", "whh", "gl", "sl", "sysc", "syjy", "sxr"};
    private String uuid, currentPhotoFile;
    private List<CheckContentEntity> mdata = new ArrayList<>();
    private int currentPosition;
    //    private DatePickerDialog dateDialog;
    private TimeSelectDialog timeSelectDialog;
    private ImageView photo_view_group_photo;
    private EditText et_date;
    private boolean isGroupphoto, isCheckSign1, isCheckSign2, isFuzerenSign;
    private ProgressDialog dialog;
    @SuppressLint("HandlerLeak")
    private Handler mhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                downImgFail();
            }
        }
    };
    private UploadCommonSupervisionBean uploadBean;
    private CommonSupervisionBean.Data topData;
    private TransforBean transforBean;
    private boolean isDestoryed;
    private CommonSuperBottomBean bottomBean;
    private CommonSuperTopBean topBean;
    private ArrayList<String> list;
    private ArrayList<String> smList;
    private int currentType;
    private String et_dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonsupervisiondetil);
    }

    @Override
    protected CommonSupervisionDetilPresenter createPresenter() {
        return new CommonSupervisionDetilPresenter(this);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        uuid = savedInstanceState.getString("uuid");
        Log.e("uuidd", "uuid getString" + uuid);
        SPUtils.getInstance().saveData("uuidd", uuid);
        Log.e("uuidd", "uuid saveData onRestoreInstanceState " + uuid);
        currentPhotoFile = savedInstanceState.getString("currentPhotoFile");
        uploadBean = (UploadCommonSupervisionBean) savedInstanceState.getSerializable("uploadBean");
        topData = (CommonSupervisionBean.Data) savedInstanceState.getSerializable("mTopData");
        transforBean = (TransforBean) savedInstanceState.getSerializable("transforBean");
        bottomBean = (CommonSuperBottomBean) savedInstanceState.getSerializable("bottomBean");
        topBean = (CommonSuperTopBean) savedInstanceState.getSerializable("topBean");
        list = savedInstanceState.getStringArrayList("list");
        smList = savedInstanceState.getStringArrayList("smList");
        currentType = savedInstanceState.getInt("currentType");
        isGroupphoto = savedInstanceState.getBoolean("isGroupphoto");
        isCheckSign1 = savedInstanceState.getBoolean("isCheckSign1");
        isCheckSign2 = savedInstanceState.getBoolean("isCheckSign2");
        isFuzerenSign = savedInstanceState.getBoolean("isFuzerenSign");
//        et_dateString =  savedInstanceState.getString("et_date");
        isDestoryed = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        SPUtils.getInstance().removeKey("uuidd");
        Log.e("uuidd", "uuid removeKey" + uuid);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("uuid", uuid);
        Log.e("uuidd", "uuid putString" + uuid);
        SPUtils.getInstance().saveData("uuidd", uuid);
        Log.e("uuidd", "uuid saveData onSaveInstanceState" + uuid);
        outState.putString("currentPhotoFile", currentPhotoFile);
        outState.putBoolean("isDestoryed", isDestoryed);
        adapter.notifyDataSetChanged();
        UploadCommonSupervisionBean uploadBean = adapter.getUploadBean();
        CommonSupervisionBean.Data topData = adapter.getmTopData();
        List<CheckContentEntity> mItemData = adapter.getmItemData();
        CommonSuperBottomBean bottomBean = adapter.getBottomBean();
        CommonSuperTopBean topBean = adapter.getTopBean();
        List<String> list = adapter.getCheckno();
        List<String> smList = adapter.getSmList();
        TransforBean transforBean = new TransforBean(mItemData);
        outState.putSerializable("uploadBean", uploadBean);
        outState.putSerializable("mTopData", topData);
        outState.putSerializable("transforBean", transforBean);
        outState.putSerializable("topBean", topBean);
        outState.putSerializable("bottomBean", bottomBean);
        outState.putStringArrayList("list", (ArrayList<String>) list);
        outState.putStringArrayList("smList", (ArrayList<String>) smList);
        outState.putInt("currentType", adapter.getCurrentType());
        outState.putBoolean("isGroupphoto", isGroupphoto);
        outState.putBoolean("isCheckSign1", isCheckSign1);
        outState.putBoolean("isCheckSign2", isCheckSign2);
        outState.putBoolean("isFuzerenSign", isFuzerenSign);
//        outState.putString("et_date", et_date.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isDestoryed) {
            adapter.setCurrentType(currentType);
            adapter.setCheckno(list);
            adapter.setSmList(smList);
            adapter.setUploadBean(uploadBean);
            adapter.setTopData(topData);
            adapter.setItemDataAll(transforBean.getData());
            adapter.setBottomBean(bottomBean);
            adapter.setTopBean(topBean);
//            et_date.setText(et_dateString);
        }


        isDestoryed = false;
    }

    @Override
    public void bindData() {
        initTitle();
        uuid = UUID.randomUUID().toString();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
//        dateDialog = new DatePickerDialog(this, this, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));

        timeSelectDialog = new TimeSelectDialog(this, null);
        timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                et_date.setText(year + "-" + month + "-" + day);
            }
        });
//        initFileName();
        setUploadTable();

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);


        adapter = new CommonSupervisionDetilAdapter(this, getCurrentType(), new CommonSupervisionDetilAdapter.OnCheckChangeListener() {
            @Override
            public void inputText(int position, String etString) {
                currentPosition = position;
                Intent intent = new Intent(CommonSupervisionDetilActivity.this, InputTextActivity.class);
                intent.putExtra("input", etString);
                startActivityForResult(intent, 102);
            }

            @Override
            public void deleteFlie(int position) {
                File file = new File(PhotoViewModel.getCommonSupervisionDir(), uuid + "_P" + position + SUFFIX);
                if (file.exists()) {
                    file.delete();
                }
            }
        }, mdata, new CommonSupervisionDetilAdapter.ImgClickListener() {
            @Override
            public String onClick(ImageView img, int position) {
                currentPosition = position;
                currentPhotoFile = PhotoViewModel.takePicture(CommonSupervisionDetilActivity.this, 101, uuid + "_P" + position + SUFFIX, PhotoViewModel.getCommonSupervisionDir());
                return uuid + "_P" + position + SUFFIX;
            }
        }, new CommonSupervisionDetilAdapter.OnBottomImgListener() {

            @Override
            public void onClick(int flag, Object img, UploadCommonSupervisionBean bean) {
                List<UploadCommonSupervisionBean.SignPicNames> signPicNames = bean.getSignPicNames();
                Log.e("czy", "size==" + signPicNames.size());
                if (flag == 1) {
                    currentPhotoFile = PhotoViewModel.takePicture(CommonSupervisionDetilActivity.this, 103, uuid + "_A1" + SUFFIX, getCommonSupervisionDir());
                    photo_view_group_photo = (ImageView) img;
                    bean.getSignPicNames().get(0).setQz(uuid + "_A1" + SUFFIX);
                    isGroupphoto = true;
                }
                if (flag == 2) {
                    new CcWriteDiolog(CommonSupervisionDetilActivity.this, new DialogListener() {
                        @Override
                        public void refreshActivity(final Object object) {
                            new Thread(() -> {
                                File signCheckFile = new File(getCommonSupervisionDir(), uuid + "_A2" + SUFFIX);
                                if (signCheckFile.exists()) signCheckFile.delete();
                                PhotoViewModel.saveBitmap(signCheckFile.getAbsolutePath(), (Bitmap) object);
                            }).start();
                            ((ImageView) img).setImageBitmap((Bitmap) object);
                           /* UploadCommonSupervisionBean.SignPicNames signPicNames1 = new UploadCommonSupervisionBean.SignPicNames();
                            signPicNames1.setQz(uuid + "_A2" + SUFFIX);
                            signPicNames.add(1, signPicNames1);*/
                            bean.getSignPicNames().get(1).setQz(uuid + "_A2" + SUFFIX);
                            isCheckSign1 = true;
                        }
                    }).show();

                }
                if (flag == 3) {
                    new CcWriteDiolog(CommonSupervisionDetilActivity.this, new DialogListener() {
                        @Override
                        public void refreshActivity(final Object object) {
                            new Thread(() -> {
                                File signCheckFile = new File(getCommonSupervisionDir(), uuid + "_A3" + SUFFIX);
                                if (signCheckFile.exists()) signCheckFile.delete();
                                PhotoViewModel.saveBitmap(signCheckFile.getAbsolutePath(), (Bitmap) object);
                            }).start();
                            ((ImageView) img).setImageBitmap((Bitmap) object);
                            bean.getSignPicNames().get(2).setQz(uuid + "_A3" + SUFFIX);
                            isCheckSign2 = true;
                        }
                    }).show();
                }
                if (flag == 4) {
                    new CcWriteDiolog(CommonSupervisionDetilActivity.this, new DialogListener() {
                        @Override
                        public void refreshActivity(final Object object) {
                            new Thread(() -> {
                                File signCheckFile = new File(getCommonSupervisionDir(), uuid + "_A4" + SUFFIX);
                                if (signCheckFile.exists()) signCheckFile.delete();
                                PhotoViewModel.saveBitmap(signCheckFile.getAbsolutePath(), (Bitmap) object);
                            }).start();
                            ((ImageView) img).setImageBitmap((Bitmap) object);
                            bean.getSignPicNames().get(3).setQz(uuid + "_A4" + SUFFIX);
                            isFuzerenSign = true;
                        }
                    }).show();
                }
                if (flag == 5) {
//                    dateDialog.show();
                    timeSelectDialog.show();
                    et_date = (EditText) img;
                }
            }
        }, new CommonSupervisionDetilAdapter.UploadListener() {
            @Override
            public void uploadListener(UploadCommonSupervisionBean bean) {
                uploadBean = bean;
                upload();


            }
        });
        String funame = getPresenter().getUser().getFUNAME();
        String fuseename = getPresenter().getUser().getFUSEENAME();
        adapter.setJC1(funame, fuseename);
        adapter.setSign(getCommonSigns());
        recyclerview.setAdapter(adapter);
    }

    private void initTitle() {
        switch (getCurrentType()) {
            case TABLE_1:
                setTitle(Constance.Item1_title);
                break;
            case TABLE_2:
                setTitle(Constance.Item2_title);
                break;
            case TABLE_3:
                setTitle(Constance.Item3_title);
                break;
            case TABLE_4:
                setTitle(Constance.Item4_title);
                break;
            case TABLE_5:
                setTitle(Constance.Item5_title);
                break;
            case TABLE_6:
                setTitle(Constance.Item6_title);

                break;
            case TABLE_7:
                setTitle(Constance.Item7_title);
                break;
            case TABLE_8:
                setTitle(Constance.Item8_title);
                break;
            case TABLE_9:
                setTitle(Constance.Item9_title);
                break;
        }
    }

    private void upload() {
        Log.e("asdasdfsd", uploadBean.toString());
        if (et_date == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            et_date = new EditText(this);
            et_date.setText(sdf.format(new Date()));
        }
        uploadBean.setJcdate(et_date.getText().toString());
        if (check()) showNormalDialog();
    }

    private void showNormalDialog() {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(CommonSupervisionDetilActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().uploadCommonSupervision(uploadTable, new Gson().toJson(uploadBean), uuid);
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
        if (getCurrentType() != 2 && getCurrentType() != 6) {
            for (int i = 0; i < uploadBean.getCheckItems().size() - 1; i++) {
                CheckContentEntity entity = uploadBean.getCheckItems().get(i);
                if (entity.getRbCheckNo().equals(entity.getJL()) && TextUtils.isEmpty(entity.getTp())) {
                    Toast.makeText(this, "当选择不符合时,必须上传对应照片", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        /*if (uploadBean.getFName() == null) {
            Toast.makeText(this, "扫码信息错误", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        if (!isGroupphoto) {
            Toast.makeText(this, "合照必须拍摄", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isCheckSign1) {
            Toast.makeText(this, "检查人员1必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isCheckSign2) {
            Toast.makeText(this, "检查人员2必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isFuzerenSign) {
            Toast.makeText(this, adapter.getBottomBean().getBt_sign_first() + "必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private String uploadTable;

    public void setUploadTable() {
        switch (getCurrentType()) {
            case TABLE_1:
                uploadTable = "Ds_GmyzcjcjlS";
                break;
            case TABLE_2:
                uploadTable = "Ds_SztzcrcS";
                break;
            case TABLE_3:
                uploadTable = "Ds_ZlcsjcjlS";
                break;
            case TABLE_4:
                uploadTable = "Ds_WhhjgjlS";
                break;
            case TABLE_5:
                uploadTable = "Ds_GlcsjcjlS";
                break;
            case TABLE_6:
                uploadTable = "Ds_SlscqyjcjlS";
                break;
            case TABLE_7:
                uploadTable = "Ds_SyscqyjcjlS";
                break;
            case TABLE_8:
                uploadTable = "Ds_SyjyqyjcjlS";
                break;
            case TABLE_9:
                uploadTable = "SXR_SgjcjlS";
                break;

        }
    }

    public static File getCommonSupervisionDir() {
        Log.e("PhotoViewModel", MyApplication.getContext().getPackageName());
        File dir;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dir = MyApplication.getContext().getExternalFilesDir("commonSupervision");
        } else {
            dir = new File(Environment.getExternalStorageDirectory(), "Android" + File.separator +
                    "data" + File.separator + MyApplication.getContext().getPackageName() + File.separator + "commonSupervision");
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    @Override
    public void bindListener() {

    }

    private List<UploadCommonSupervisionBean.SignPicNames> getCommonSigns() {
        List<UploadCommonSupervisionBean.SignPicNames> signPicNames = new ArrayList<>();
        addSignItem(signPicNames, uuid + "_A1" + SUFFIX);
        addSignItem(signPicNames, uuid + "_A2" + SUFFIX);
        addSignItem(signPicNames, uuid + "_A3" + SUFFIX);
        addSignItem(signPicNames, uuid + "_A4" + SUFFIX);
        return signPicNames;
    }

    private void addSignItem(List<UploadCommonSupervisionBean.SignPicNames> signPicNames, String name) {
        UploadCommonSupervisionBean.SignPicNames data = new UploadCommonSupervisionBean.SignPicNames();
        data.setQz(name);
        signPicNames.add(data);
    }

    /*private void initFileName() {
        
        Log.e("uuidd","uuid initFileName "+uuid);
        uuid + "_P" = ;
        uuid + "_A1" + SUFFIX = ;
        uuid + "_A2" + SUFFIX = ;
        uuid + "_A3" + SUFFIX = ;
        uuid + "_A4" + SUFFIX = ;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ACTIVITY_ZXING) {
                //二维码扫描成功
                String result = data.getExtras().getString("result");
                result = new String(Base64.decode(result, Base64.DEFAULT));
                Log.e("czy", "result" + result);
                getPresenter().getTopDetil(getEnterpriseTableName(), result.split("-")[1]);
            }
            if (requestCode == 102) {
                if (data != null) {
                    String aReturn = data.getStringExtra("return");
                    adapter.getmItemData().get(currentPosition - 1).setQK(aReturn);
                    adapter.notifyDataSetChanged();
                }
            }
            if (requestCode == 101) {
                PhotoUtils.compressBmpToFile(currentPhotoFile);
                adapter.getmItemData().get(currentPosition - 1).setTp(uuid + "_P" + currentPosition + SUFFIX);
                adapter.notifyDataSetChanged();
            }
            if (requestCode == 103) {
                Bitmap bitmap = PhotoUtils.decodeSampleBitmapFromFile(PhotoUtils.compressBmpToFile(currentPhotoFile));
                photo_view_group_photo.setImageBitmap(bitmap);

            }

            if (requestCode == REQUEST_CODE_ACTIVITY_QY) {
                CommonSupervisionQyBean.DataListBean bean = (CommonSupervisionQyBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                CommonSupervisionBean.Data beanData = new CommonSupervisionBean.Data();
                beanData.setFStId(bean.getFStId());
                beanData.setClsl(bean.getClsl());
                beanData.setFCategory(bean.getFCategory());
                beanData.setFCityAdd(bean.getFCityAdd());
                beanData.setFFarmName(bean.getFFarmName());
                beanData.setFLatitude(bean.getFLatitude());
                beanData.setFLegalPerson(bean.getFLegalPerson());
                beanData.setFPhone(bean.getFPhone());
                beanData.setFLongitude(bean.getFLongitude());
                adapter.setTopData(beanData);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setCompanyDetil(CommonSupervisionBean.Data data) {
        Log.e("czy", data.toString());
        adapter.setTopData(data);
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

                        /*int currentType = getCurrentType();
                        String url= NetClient.IMG_PRE+"/PrintImgs"+"/"*/
                    downPrintImg();

                })
                .setNegativeButton("否", (dialog, which) -> {
                    finish();
                }).show();
    }

    private void downPrintImg() {
        if (isDown()) {
            List<String> urllist = getUrllist();
            List<String> fileNames = getFileNames();
            dialog = new ProgressDialog(this);
            dialog.setMessage("下载打印资源");
            dialog.show();
            for (int i = 0; i < urllist.size(); i++)
                getPresenter().downPrintImg(urllist.get(i), fileNames.get(i), i, mhandle);
        } else {
            skipActivity();
        }
    }

    public List<String> getFileNames() {
        List<String> list = new ArrayList<>();
        switch (getCurrentType()) {
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

    public void skipActivity() {
        Intent intent = new Intent(CommonSupervisionDetilActivity.this, PrintAcitivty.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constance.START_ACTIVITY_TYPE, String.valueOf(getCurrentType()));
        bundle.putSerializable(Constance.ACTIVITY_DATA, uploadBean);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private String BASE_URL = NetClient.IMG_PRE + "/PrintImgs" + "/";

    public List<String> getUrllist() {
        List<String> list = new ArrayList<>();
        switch (getCurrentType()) {
            case TABLE_1:
                //4
                list.add(BASE_URL + dirname[0] + "/yz_img1.jpg");
                list.add(BASE_URL + dirname[0] + "/yz_img2.jpg");
                list.add(BASE_URL + dirname[0] + "/yz_img3.jpg");
                list.add(BASE_URL + dirname[0] + "/yz_img4.jpg");
                break;
            case TABLE_2:
                //2
                list.add(BASE_URL + dirname[1] + "/tz_1.jpg");
                list.add(BASE_URL + dirname[1] + "/tz_2.jpg");
                break;
            case TABLE_3:
                //3
                list.add(BASE_URL + dirname[2] + "/dwzlcs_1.jpg");
                list.add(BASE_URL + dirname[2] + "/dwzlcs_2.jpg");
                list.add(BASE_URL + dirname[2] + "/dwzlcs_3.jpg");
                break;
            case TABLE_4:
                //4
                list.add(BASE_URL + dirname[3] + "/wuhaihua_1.jpg");
                list.add(BASE_URL + dirname[3] + "/wuhaihua_2.jpg");
                list.add(BASE_URL + dirname[3] + "/wuhaihua_3.jpg");
                list.add(BASE_URL + dirname[3] + "/wuhaihua_4.jpg");
                break;
            case TABLE_5:
                //4
                list.add(BASE_URL + dirname[4] + "/geli_1.jpg");
                list.add(BASE_URL + dirname[4] + "/geli_2.jpg");
                list.add(BASE_URL + dirname[4] + "/geli_3.jpg");
                list.add(BASE_URL + dirname[4] + "/geli_4.jpg");
                break;
            case TABLE_6:
                //2
                list.add(BASE_URL + dirname[5] + "/siliao_1.jpg");
                list.add(BASE_URL + dirname[5] + "/siliao_2.jpg");

                break;
            case TABLE_7:
                //2
                list.add(BASE_URL + dirname[6] + "/sysc_1.jpg");
                list.add(BASE_URL + dirname[6] + "/sysc_2.jpg");

                break;
            case TABLE_8:
                //3
                list.add(BASE_URL + dirname[7] + "/syjy_1.jpg");
                list.add(BASE_URL + dirname[7] + "/syjy_2.jpg");
                list.add(BASE_URL + dirname[7] + "/syjy_3.jpg");

                break;
            case TABLE_9:
                //2
                list.add(BASE_URL + dirname[8] + "/sxr_1.jpg");
                list.add(BASE_URL + dirname[8] + "/sxr_2.jpg");
                break;
            default:
                break;
        }
        return list;
    }

    private boolean isDown() {
        Boolean b = true;
        switch (getCurrentType()) {
            case TABLE_1:
                //4
                if (FileUtil.getInstance().getPrintBGSize("yz") == 4)
                    b = false;
                break;
            case TABLE_2:
                //2
                if (FileUtil.getInstance().getPrintBGSize("tz") == 2)
                    b = false;
                break;
            case TABLE_3:
                //3
                if (FileUtil.getInstance().getPrintBGSize("dwzlcs") == 3)
                    b = false;
                break;
            case TABLE_4:
                //4
                if (FileUtil.getInstance().getPrintBGSize("wuhaihua") == 4)
                    b = false;
                break;
            case TABLE_5:
                //4
                if (FileUtil.getInstance().getPrintBGSize("geli") == 4)
                    b = false;
                break;
            case TABLE_6:
                //2
                if (FileUtil.getInstance().getPrintBGSize("siliao") == 2)
                    b = false;
                break;
            case TABLE_7:
                //2
                if (FileUtil.getInstance().getPrintBGSize("sysc") == 2)
                    b = false;
                break;
            case TABLE_8:
                //3
                if (FileUtil.getInstance().getPrintBGSize("syjy") == 3)
                    b = false;
                break;
            case TABLE_9:
                //2
                if (FileUtil.getInstance().getPrintBGSize("sxr") == 2)
                    b = false;
                break;
            default:
                break;
        }
        return b;
    }

    private boolean isT1_1, isT1_2, isT1_3, isT1_4, isT2_1, isT2_2, isT3_1, isT3_2, isT3_3, isT4_1, isT4_2, isT4_3,
            isT4_4, isT5_1, isT5_2, isT5_3, isT5_4, isT6_1, isT6_2, isT7_1, isT7_2, isT8_1, isT8_2, isT8_3, isT9_1, isT9_2;

    @Override
    public void downImgSuccess(int num) {
        switch (getCurrentType()) {
            case TABLE_1:
                //4
                if (num == 0) {
                    isT1_1 = true;
                }
                if (num == 1) {
                    isT1_2 = true;
                }
                if (num == 2) {
                    isT1_3 = true;
                }
                if (num == 3) {
                    isT1_4 = true;
                }

                break;
            case TABLE_2:
                //2
                if (num == 0) {
                    isT2_1 = true;
                }
                if (num == 1) {
                    isT2_2 = true;
                }
                break;
            case TABLE_3:
                //3
                if (num == 0) {
                    isT3_1 = true;
                }
                if (num == 1) {
                    isT3_2 = true;
                }
                if (num == 2) {
                    isT3_3 = true;
                }
                break;
            case TABLE_4:
                //4
                if (num == 0) {
                    isT4_1 = true;
                }
                if (num == 1) {
                    isT4_2 = true;
                }
                if (num == 2) {
                    isT4_3 = true;
                }
                if (num == 3) {
                    isT4_4 = true;
                }
                break;
            case TABLE_5:
                //4
                if (num == 0) {
                    isT5_1 = true;
                }
                if (num == 1) {
                    isT5_2 = true;
                }
                if (num == 2) {
                    isT5_3 = true;
                }
                if (num == 3) {
                    isT5_4 = true;
                }
                break;
            case TABLE_6:
                //2
                if (num == 0) {
                    isT6_1 = true;
                }
                if (num == 1) {
                    isT6_2 = true;
                }

                break;
            case TABLE_7:
                //2
                if (num == 0) {
                    isT2_1 = true;
                }
                if (num == 1) {
                    isT2_2 = true;
                }
                break;
            case TABLE_8:
                //3
                if (num == 0) {
                    isT8_1 = true;
                }
                if (num == 1) {
                    isT8_2 = true;
                }
                if (num == 2) {
                    isT8_3 = true;
                }

                break;
            case TABLE_9:
                //2
                if (num == 0) {
                    isT9_1 = true;
                }
                if (num == 1) {
                    isT9_2 = true;
                }
                break;
        }

        switch (getCurrentType()) {
            case TABLE_1:
                //4
                if (isT1_1 && isT1_2 && isT1_3 && isT1_4) {
                    skipActivity();
                    dialog.dismiss();
                }

                break;
            case TABLE_2:
                //2
                if (isT2_1 && isT2_2) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            case TABLE_3:
                //3
                if (isT3_1 && isT3_2 && isT3_3) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            case TABLE_4:
                //4
                if (isT4_1 && isT4_2 && isT4_3 && isT4_4) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            case TABLE_5:
                //4
                if (isT5_1 && isT5_2 && isT5_3 && isT5_4) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            case TABLE_6:
                //2
                if (isT6_1 && isT6_2) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            case TABLE_7:
                //2
                if (isT7_1 && isT7_2) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            case TABLE_8:
                //3
                if (isT8_1 && isT8_2 && isT8_3) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            case TABLE_9:
                //2
                if (isT9_1 && isT9_2) {
                    dialog.dismiss();
                    skipActivity();
                }
                break;
            default:
                break;
        }
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
                    dialog.dismiss();
                    downPrintImg();

                })
                .show();
    }


    private String getEnterpriseTableName() {
        switch (getCurrentType()) {
            case TABLE_1:
                return "V_AH_FarmsRecord";
            case TABLE_2:
                return "V_AH_SlaughterBk";
            case TABLE_3:
                return "V_AH_MedicalInstitutions";
            case TABLE_4:
                return "V_AH_Basic";
            case TABLE_5:
                return "V_AH_GLCXX";
            case TABLE_6:
                return "V_SL_FProdBasicInfor";
            case TABLE_7:
                return "V_AH_QYJBQK";
            case TABLE_8:
                return "V_AH_FirmRun";
            case TABLE_9:
                return "V_SXR_Sgzhan";
            default:
                Toast.makeText(this, "数据加载错误", Toast.LENGTH_SHORT).show();
                return "";
        }
    }

    public int getCurrentType() {
        Intent intent = getIntent();
        if (intent == null) {
            return TABLE_1;
        }
        return getIntent().getIntExtra("type", TABLE_1);
    }


//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        String time = year + "-" + (month + 1) + "-" + dayOfMonth;
//        et_date.setText(time);
//    }

    @Override
    public void onModelTakePhoto(String path) {
        currentPhotoFile = path;
    }
}
