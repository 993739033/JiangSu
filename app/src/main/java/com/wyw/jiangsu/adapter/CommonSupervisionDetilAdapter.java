package com.wyw.jiangsu.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.CommonSupervisionQyActivity;
import com.wyw.jiangsu.activity.InputTextActivity;
import com.wyw.jiangsu.activity.model.PhotoViewModel;
import com.wyw.jiangsu.activity.supervision.CommonSupervisionDetilActivity;
import com.wyw.jiangsu.bean.CommonSuperBottomBean;
import com.wyw.jiangsu.bean.CommonSuperTopBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.bean.local.CheckContentEntity;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.utils.SPUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import zxing.activity.CaptureActivity;

import static com.wyw.jiangsu.utils.PhotoUtils.getCommonSupervision;

/**
 * Created by HUANG on 2017/8/2.
 */
public class CommonSupervisionDetilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnBottomImgListener onBottomImgListener;
    private TopViewHolder topViewHolder;
    private BottomViewHolder bottomViewHolder;
    private ItemsViewHolder itemsViewHolder;
    private UploadCommonSupervisionBean uploadBean;
    private ItemsViewOtherHolder itemsViewOtherHolder;

    public enum ITEM_TYPE {
        ITEM_TYPE_TOP,
        ITEM_TYPE_COMMON,
        ITEM_TYPE_BOTTOM,
        ITEM_TYPE_COMMON_OTHER
    }

    private String[] FFarmNames = {"养殖场名称", "屠宰厂(场)名称", "场所名称", "场所名称", "隔离场所名称",
            "企业名称", "企业名称", "企业名称", "收购站名称"};
    private String[] FCategorys = {"养殖动物种类", "", "诊疗动物种类", "无害化动物种类", "隔离动物种类", "",
            "", "", "收购生鲜乳种类"};
    private String[] clsls = {"存栏动物数量", "", "每日可诊疗动物数量", "日处理能力", "每日可隔离动物数量",
            "企业代码", "", "", "日收奶量"};
    private String[] bt_sign_firsts = {"规模场负责人员签字", "厂方负责人员(签字)", "诊疗机构负责人员签字",
            "无害化处理场负责人员签字", "隔离场负责人员签字", "企业负责人签章", "企业负责人签章", "企业负责人签章", "企业签字"};
    private String[] Item1_4s = {Constance.Item1_4_XZ1, Constance.Item1_4_XZ2, Constance.Item1_4_XZ3,
            Constance.Item1_4_XZ4, Constance.Item1_4_XZ5, Constance.Item1_4_XZ6, Constance.Item1_4_XZ7,
            Constance.Item1_4_XZ8, Constance.Item1_4_XZ9};
    private String[] Item1_9s = {Constance.Item1_9_XZ1, Constance.Item1_9_XZ2, Constance.Item1_9_XZ3,
            Constance.Item1_9_XZ4, Constance.Item1_9_XZ5, Constance.Item1_9_XZ6, Constance.Item1_9_XZ7};
    private String[] Item3_5s = {Constance.Item3_5_XZ1, Constance.Item3_5_XZ2, Constance.Item3_5_XZ3,
            Constance.Item3_5_XZ4, Constance.Item3_5_XZ5};
    private String[] Item3_6s = {Constance.Item3_6_XZ1, Constance.Item3_6_XZ2, Constance.Item3_6_XZ3};
    private String[] Item3_8s = {Constance.Item3_8_XZ1, Constance.Item3_8_XZ2, Constance.Item3_8_XZ3};
    private String[] Item4_3s = {Constance.Item4_3_XZ1, Constance.Item4_3_XZ2, Constance.Item4_3_XZ3,
            Constance.Item4_3_XZ4, Constance.Item4_3_XZ5, Constance.Item4_3_XZ6, Constance.Item4_3_XZ7, Constance.Item4_3_XZ8};
    private String[] Item4_10s = {Constance.Item4_10_XZ1, Constance.Item4_10_XZ2, Constance.Item4_10_XZ3,
            Constance.Item4_10_XZ4, Constance.Item4_10_XZ5};
    private String[] Item5_3s = {Constance.Item5_3_XZ1, Constance.Item5_3_XZ2, Constance.Item5_3_XZ3,
            Constance.Item5_3_XZ4, Constance.Item5_3_XZ5, Constance.Item5_3_XZ6, Constance.Item5_3_XZ7,
            Constance.Item5_3_XZ8, Constance.Item5_3_XZ9};
    private String[] Item5_6s = {Constance.Item5_6_XZ1, Constance.Item5_6_XZ2, Constance.Item5_6_XZ3,
            Constance.Item5_6_XZ4, Constance.Item5_6_XZ5, Constance.Item5_6_XZ6};

    private Context mContext;
    private int currentType;

    private List<String> checkno;
    private CommonSupervisionBean.Data mTopData;
    private OnCheckChangeListener onCheckChangeListener;
    private List<CheckContentEntity> mItemData;
    private ImgClickListener imgClickListener;
    private CommonSuperTopBean topBean;
    private CommonSuperBottomBean bottomBean;
    private List<String> smList;
    private UploadListener uploadListener;
    private String uuid;
    private String tableName;

    public CommonSupervisionDetilAdapter(Context context, int currentType, OnCheckChangeListener onCheckChangeListener,
                                         List<CheckContentEntity> mItemData, ImgClickListener clickListener,
                                         OnBottomImgListener onBottomImgListener, UploadListener uploadListener) {
        mContext = context;
        this.currentType = currentType;
        this.onCheckChangeListener = onCheckChangeListener;
        this.mItemData = mItemData;
        this.imgClickListener = clickListener;
        this.onBottomImgListener = onBottomImgListener;
        this.uploadListener = uploadListener;
        init();
    }

    public BottomViewHolder getBottomViewHolder() {
        return bottomViewHolder;
    }

    public void setBottomViewHolder(BottomViewHolder bottomViewHolder) {
        this.bottomViewHolder = bottomViewHolder;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCurrentType() {
        return currentType;
    }

    public void setCurrentType(int currentType) {
        this.currentType = currentType;
    }

    public List<String> getSmList() {
        return smList;
    }

    public void setSmList(List<String> smList) {
        this.smList = smList;
    }

    public List<String> getCheckno() {
        return checkno;
    }

    public void setCheckno(List<String> checkno) {
        this.checkno = checkno;
    }


    public CommonSuperTopBean getTopBean() {
        return topBean;
    }

    public void setTopBean(CommonSuperTopBean topBean) {
        this.topBean = topBean;
        notifyDataSetChanged();
    }

    public void setBottomBean(CommonSuperBottomBean bottomBean) {
        this.bottomBean = bottomBean;
        notifyDataSetChanged();
    }

    public CommonSuperBottomBean getBottomBean() {
        return bottomBean;
    }

    public CommonSupervisionBean.Data getmTopData() {
        return mTopData;
    }


    public void setTopData(CommonSupervisionBean.Data data) {
        if (data == null) {
            mTopData = new CommonSupervisionBean.Data();
        }
        mTopData = data;
        notifyDataSetChanged();
    }

    public void setItemData(int position) {
        notifyItemChanged(position);
    }

    public void setItemDataAll(List<CheckContentEntity> data) {
        mItemData = data;
        notifyDataSetChanged();
    }

    public List<CheckContentEntity> getmItemData() {
        return mItemData;
    }

    private void init() {
        checkno = new ArrayList<>();
        uploadBean = new UploadCommonSupervisionBean();
        mTopData = new CommonSupervisionBean.Data();
        setDefalutTop();
        setDefalutBottom();
        setDefalutItem();
        setDefalutUploadData();

        setType(currentType);
    }

    private void setType(int type) {
        switch (type) {
            case 1:
                tableName = "V_AH_FarmsRecord";
                break;
            case 2:
                tableName = "V_AH_SlaughterBk";
                break;
            case 3:
                tableName = "V_AH_MedicalInstitutions";
                break;
            case 4:
                tableName = "V_AH_Basic";
                break;
            case 5:
                tableName = "V_AH_GLCXX";
                break;
            case 6:
                tableName = "V_SL_FProdBasicInfor";
                break;
            case 7:
                tableName = "V_AH_QYJBQK";
                break;
            case 8:
                tableName = "V_AH_FirmRun";
                break;
            case 9:
                tableName = "V_SXR_Sgzhan";
                break;
            default:
                break;
        }
    }

    private void setDefalutUploadData() {
        uploadBean.setCheckItems(mItemData);
        List<UploadCommonSupervisionBean.SignPicNames> signPicNames1 = new ArrayList<>();
        uploadBean.setSignPicNames(signPicNames1);
    }

    private void setDefalutItem() {
        getItemContent();
        for (int i = 0; i < smList.size(); i++) {
            CheckContentEntity entity = new CheckContentEntity();
            entity.setName(smList.get(i));
            entity.setRbCheckYes("是").setRbCheckNo("否");
            if (currentType == 2) {
                if (i == 0 || i == 1)
                    entity.setRbCheckYes("能");
            }
            entity.setJL(entity.getRbCheckYes());
            entity.setTp(null);
            entity.setCs(null);
            setBuFuHe(entity, i);
            mItemData.add(entity);
        }
        CheckContentEntity entity = new CheckContentEntity();
        entity.setName("");
        mItemData.add(entity);
    }

    private void setBuFuHe(CheckContentEntity entity, int position) {
        List<CheckContentEntity.DataEntity> list = new ArrayList<>();
        switch (currentType) {
            case CommonSupervisionDetilActivity.TABLE_1:
                if (position == 3) {
                    for (int i = 0; i < 9; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item1_4s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }
                if (position == 8) {

                    for (int i = 0; i < 7; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item1_9s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }

                break;
            case CommonSupervisionDetilActivity.TABLE_3:
                if (position == 4) {

                    for (int i = 0; i < 5; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item3_5s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }
                if (position == 5) {

                    for (int i = 0; i < 3; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item3_6s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }
                if (position == 7) {

                    for (int i = 0; i < 3; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item3_8s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }

                break;
            case CommonSupervisionDetilActivity.TABLE_4:
                if (position == 2) {

                    for (int i = 0; i < 8; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item4_3s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }
                if (position == 9) {

                    for (int i = 0; i < 5; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item4_10s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }

                break;
            case CommonSupervisionDetilActivity.TABLE_5:
                if (position == 2) {
                    for (int i = 0; i < 8; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item5_3s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }
                if (position == 5) {
                    for (int i = 0; i < 5; i++) {
                        CheckContentEntity.DataEntity dataEntity = new CheckContentEntity.DataEntity();
                        dataEntity.setXZ(Item5_6s[i]);
                        list.add(dataEntity);
                    }
                    entity.setList(list);
                }
                break;
        }

    }

    private void setDefalutBottom() {
        bottomBean = new CommonSuperBottomBean();
        switch (currentType) {
            case CommonSupervisionDetilActivity.TABLE_1:
                bottomBean.setBt_sign_first(bt_sign_firsts[0]);
                break;
            case CommonSupervisionDetilActivity.TABLE_2:
                bottomBean.setBt_sign_first(bt_sign_firsts[1]);
                break;
            case CommonSupervisionDetilActivity.TABLE_3:
                bottomBean.setBt_sign_first(bt_sign_firsts[2]);
                break;
            case CommonSupervisionDetilActivity.TABLE_4:
                bottomBean.setBt_sign_first(bt_sign_firsts[3]);
                break;
            case CommonSupervisionDetilActivity.TABLE_5:
                bottomBean.setBt_sign_first(bt_sign_firsts[4]);
                break;
            case CommonSupervisionDetilActivity.TABLE_6:
                bottomBean.setBt_sign_first(bt_sign_firsts[5]);
                break;
            case CommonSupervisionDetilActivity.TABLE_7:
                bottomBean.setBt_sign_first(bt_sign_firsts[6]);
                break;
            case CommonSupervisionDetilActivity.TABLE_8:
                bottomBean.setBt_sign_first(bt_sign_firsts[7]);
                break;
            case CommonSupervisionDetilActivity.TABLE_9:
                bottomBean.setBt_sign_first(bt_sign_firsts[8]);
                break;
        }

    }

    public void setSign(List<UploadCommonSupervisionBean.SignPicNames> list) {
        uploadBean.setSignPicNames(list);
    }

    private void setDefalutTop() {
        topBean = new CommonSuperTopBean();
        switch (currentType) {
            case CommonSupervisionDetilActivity.TABLE_1:
                topBean.setTv_FFarmName(FFarmNames[0]);
                topBean.setTv_FCategory(FCategorys[0]);
                topBean.setTv_clsl(clsls[0]);
                break;
            case CommonSupervisionDetilActivity.TABLE_2:
                topBean.setTv_FFarmName(FFarmNames[1]);
                topBean.setTv_FCategory(FCategorys[1]);
                topBean.setTv_clsl(clsls[1]);
                break;
            case CommonSupervisionDetilActivity.TABLE_3:
                topBean.setTv_FFarmName(FFarmNames[2]);
                topBean.setTv_FCategory(FCategorys[2]);
                topBean.setTv_clsl(clsls[2]);
                break;
            case CommonSupervisionDetilActivity.TABLE_4:
                topBean.setTv_FFarmName(FFarmNames[3]);
                topBean.setTv_FCategory(FCategorys[3]);
                topBean.setTv_clsl(clsls[3]);
                break;
            case CommonSupervisionDetilActivity.TABLE_5:
                topBean.setTv_FFarmName(FFarmNames[4]);
                topBean.setTv_FCategory(FCategorys[4]);
                topBean.setTv_clsl(clsls[4]);
                break;
            case CommonSupervisionDetilActivity.TABLE_6:
                topBean.setTv_FFarmName(FFarmNames[5]);
                topBean.setTv_FCategory(FCategorys[5]);
                topBean.setTv_clsl(clsls[5]);
                break;
            case CommonSupervisionDetilActivity.TABLE_7:
                topBean.setTv_FFarmName(FFarmNames[6]);
                topBean.setTv_FCategory(FCategorys[6]);
                topBean.setTv_clsl(clsls[6]);

                break;
            case CommonSupervisionDetilActivity.TABLE_8:

                topBean.setTv_FFarmName(FFarmNames[7]);
                topBean.setTv_FCategory(FCategorys[7]);
                topBean.setTv_clsl(clsls[7]);
                break;
            case CommonSupervisionDetilActivity.TABLE_9:
                topBean.setTv_FFarmName(FFarmNames[8]);
                topBean.setTv_FCategory(FCategorys[8]);
                topBean.setTv_clsl(clsls[8]);
                break;
        }


    }

    private void getItemContent() {
        smList = new ArrayList<>();
        switch (currentType) {
            case CommonSupervisionDetilActivity.TABLE_1:
                smList.add(Constance.Item1_1);
                smList.add(Constance.Item1_2);
                smList.add(Constance.Item1_3);
                smList.add(Constance.Item1_4);
                smList.add(Constance.Item1_5);
                smList.add(Constance.Item1_6);
                smList.add(Constance.Item1_7);
                smList.add(Constance.Item1_8);
                smList.add(Constance.Item1_9);
                smList.add(Constance.Item1_10);
                smList.add(Constance.Item1_11);
                smList.add(Constance.Item1_12);
                smList.add(Constance.Item1_13);
                smList.add(Constance.Item1_14);
                smList.add(Constance.Item1_15);
                smList.add(Constance.Item1_16);
                smList.add(Constance.Item1_17);
                smList.add(Constance.Item1_18);
                smList.add(Constance.Item1_19);
                smList.add(Constance.Item1_20);
                smList.add(Constance.Item1_21);
                smList.add(Constance.Item1_22);
                smList.add(Constance.Item1_23);
                smList.add(Constance.Item1_24);
                smList.add(Constance.Item1_25);
                smList.add(Constance.Item1_26);
                break;
            case CommonSupervisionDetilActivity.TABLE_2:
                smList.add(Constance.Item2_1);
                smList.add(Constance.Item2_2);
                smList.add(Constance.Item2_3);
                smList.add(Constance.Item2_4);
                smList.add(Constance.Item2_5);
                smList.add(Constance.Item2_6);
                smList.add(Constance.Item2_7);
                smList.add(Constance.Item2_8);
                smList.add(Constance.Item2_9);
                smList.add(Constance.Item2_10);
                smList.add(Constance.Item2_11);
                smList.add(Constance.Item2_12);
                smList.add(Constance.Item2_13);
                smList.add(Constance.Item2_14);
                smList.add(Constance.Item2_15);
                smList.add(Constance.Item2_16);
                smList.add(Constance.Item2_17);
                smList.add(Constance.Item2_18);
                smList.add(Constance.Item2_19);
                smList.add(Constance.Item2_20);
                smList.add(Constance.Item2_21);
                smList.add(Constance.Item2_22);
                smList.add(Constance.Item2_23);
                smList.add(Constance.Item2_24);
                smList.add(Constance.Item2_25);
                smList.add(Constance.Item2_26);
                smList.add(Constance.Item2_27);
                smList.add(Constance.Item2_28);
                smList.add(Constance.Item2_29);
                break;
            case CommonSupervisionDetilActivity.TABLE_3:
                smList.add(Constance.Item3_1);
                smList.add(Constance.Item3_2);
                smList.add(Constance.Item3_3);
                smList.add(Constance.Item3_4);
                smList.add(Constance.Item3_5);
                smList.add(Constance.Item3_6);
                smList.add(Constance.Item3_7);
                smList.add(Constance.Item3_8);
                smList.add(Constance.Item3_9);
                smList.add(Constance.Item3_10);
                smList.add(Constance.Item3_11);
                smList.add(Constance.Item3_12);
                smList.add(Constance.Item3_13);

                break;
            case CommonSupervisionDetilActivity.TABLE_4:
                smList.add(Constance.Item4_1);
                smList.add(Constance.Item4_2);
                smList.add(Constance.Item4_3);
                smList.add(Constance.Item4_4);
                smList.add(Constance.Item4_5);
                smList.add(Constance.Item4_6);
                smList.add(Constance.Item4_7);
                smList.add(Constance.Item4_8);
                smList.add(Constance.Item4_9);
                smList.add(Constance.Item4_10);
                smList.add(Constance.Item4_11);
                smList.add(Constance.Item4_12);
                smList.add(Constance.Item4_13);
                smList.add(Constance.Item4_14);
                smList.add(Constance.Item4_15);
                break;
            case CommonSupervisionDetilActivity.TABLE_5:
                smList.add(Constance.Item5_1);
                smList.add(Constance.Item5_2);
                smList.add(Constance.Item5_3);
                smList.add(Constance.Item5_4);
                smList.add(Constance.Item5_5);
                smList.add(Constance.Item5_6);
                smList.add(Constance.Item5_7);
                smList.add(Constance.Item5_8);
                smList.add(Constance.Item5_9);
                smList.add(Constance.Item5_10);
                smList.add(Constance.Item5_11);
                smList.add(Constance.Item5_12);
                smList.add(Constance.Item5_13);
                smList.add(Constance.Item5_14);
                smList.add(Constance.Item5_15);
                smList.add(Constance.Item5_16);


                break;
            case CommonSupervisionDetilActivity.TABLE_6:
                smList.add(Constance.Item6_1);
                smList.add(Constance.Item6_2);
                smList.add(Constance.Item6_3);
                smList.add(Constance.Item6_4);
                smList.add(Constance.Item6_5);
                smList.add(Constance.Item6_6);
                smList.add(Constance.Item6_7);
                smList.add(Constance.Item6_8);
                smList.add(Constance.Item6_9);
                smList.add(Constance.Item6_10);
                smList.add(Constance.Item6_11);
                smList.add(Constance.Item6_12);
                smList.add(Constance.Item6_13);
                smList.add(Constance.Item6_14);
                smList.add(Constance.Item6_15);
                smList.add(Constance.Item6_16);
                smList.add(Constance.Item6_17);
                smList.add(Constance.Item6_18);
                smList.add(Constance.Item6_19);
                break;
            case CommonSupervisionDetilActivity.TABLE_7:
                smList.add(Constance.Item6_1);
                smList.add(Constance.Item6_2);
                smList.add(Constance.Item7_3);
                smList.add(Constance.Item7_4);
                smList.add(Constance.Item7_5);
                smList.add(Constance.Item7_6);
                smList.add(Constance.Item7_7);
                smList.add(Constance.Item7_8);
                smList.add(Constance.Item7_9);
                smList.add(Constance.Item7_10);
                smList.add(Constance.Item7_11);
                smList.add(Constance.Item7_12);
                smList.add(Constance.Item7_13);
                smList.add(Constance.Item7_14);

                break;
            case CommonSupervisionDetilActivity.TABLE_8:
                smList.add(Constance.Item8_1);
                smList.add(Constance.Item8_2);
                smList.add(Constance.Item8_3);
                smList.add(Constance.Item8_4);
                smList.add(Constance.Item8_5);
                smList.add(Constance.Item8_6);
                smList.add(Constance.Item8_7);
                smList.add(Constance.Item8_8);
                smList.add(Constance.Item8_9);
                smList.add(Constance.Item8_10);
                smList.add(Constance.Item8_11);
                smList.add(Constance.Item8_12);
                smList.add(Constance.Item8_13);
                smList.add(Constance.Item8_14);
                smList.add(Constance.Item8_15);
                smList.add(Constance.Item8_16);
                smList.add(Constance.Item8_17);
                smList.add(Constance.Item8_18);
                break;
            case CommonSupervisionDetilActivity.TABLE_9:
                smList.add(Constance.Item9_1);
                smList.add(Constance.Item9_2);
                smList.add(Constance.Item9_3);
                smList.add(Constance.Item9_4);
                smList.add(Constance.Item9_5);
                smList.add(Constance.Item9_6);
                smList.add(Constance.Item9_7);
                smList.add(Constance.Item9_8);
                smList.add(Constance.Item9_9);
                smList.add(Constance.Item9_10);
                smList.add(Constance.Item9_11);
                smList.add(Constance.Item9_12);
                smList.add(Constance.Item9_13);
                smList.add(Constance.Item9_14);
                smList.add(Constance.Item9_15);
                smList.add(Constance.Item9_16);
                smList.add(Constance.Item9_17);
                smList.add(Constance.Item9_18);
                smList.add(Constance.Item9_19);
                smList.add(Constance.Item9_20);
                smList.add(Constance.Item9_21);
                smList.add(Constance.Item9_22);
                smList.add(Constance.Item9_23);
                smList.add(Constance.Item9_24);
                smList.add(Constance.Item9_25);
                smList.add(Constance.Item9_26);
                break;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("uuidd", "onCreateViewHolder");
        if (viewType == ITEM_TYPE.ITEM_TYPE_TOP.ordinal()) {
            topViewHolder = new TopViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_top_commonsuper, parent, false));
            return topViewHolder;
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_BOTTOM.ordinal()) {
            bottomViewHolder = new BottomViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_bottom_commonsuper, parent, false));
            return bottomViewHolder;
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_COMMON.ordinal()) {
            itemsViewHolder = new ItemsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_check_content, parent, false));
            return itemsViewHolder;
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal()) {
            itemsViewOtherHolder = new ItemsViewOtherHolder(LayoutInflater.from(mContext).
                    inflate(R.layout.adapter_checkbox_content, parent, false));
            return itemsViewOtherHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("uuidd", "onBindViewHolder");
        if (getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_TOP.ordinal()) {
            bindTopData((TopViewHolder) holder);
        } else if (getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_BOTTOM.ordinal()) {
            bindBottomData((BottomViewHolder) holder);
        } else if (getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_COMMON.ordinal()) {
            bindCommonItem((ItemsViewHolder) holder, position);
        } else if (getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal()) {
            bindCommonOther((ItemsViewOtherHolder) holder, position);
        }
    }

    private void bindCommonOther(ItemsViewOtherHolder holder, int position) {
        holder.rb_check_result_no.setTag(String.valueOf(position));
        holder.rb_check_result_yes.setText(mItemData.get(position - 1).getRbCheckYes());
        holder.rb_check_result_no.setText(mItemData.get(position - 1).getRbCheckNo());
        if (currentType == 2 || currentType == 6) {
            holder.rl_detil_picture.setVisibility(View.GONE);
        }
        //检查内容
        holder.tv_check_content.setText(mItemData.get(position - 1).getName());
        //结论,不符合情况，图片
        Log.e("czys", checkno.toString());
        if (mItemData.get(position - 1).getJL().equals(mItemData.get(position - 1).getRbCheckNo())) {
            holder.rb_check_result_no.setChecked(true);
            if (!TextUtils.isEmpty(mItemData.get(position - 1).getTp())) {
                Glide.with(mContext).load(new File(PhotoViewModel.getCommonSupervisionDir(),
                        mItemData.get(position - 1).getTp())).into(holder.img_takephone);
                holder.img_camera.setVisibility(View.GONE);
            } else {
                holder.img_camera.setImageResource(R.drawable.take_pictures);
                holder.img_camera.setVisibility(View.VISIBLE);
            }
        } else {
            holder.rb_check_result_yes.setChecked(true);
        }
    }

    private void bindCommonItem(ItemsViewHolder holder, int position) {
        holder.rb_check_result_no.setTag(String.valueOf(position));
        holder.rb_check_result_yes.setText(mItemData.get(position - 1).getRbCheckYes());
        holder.rb_check_result_no.setText(mItemData.get(position - 1).getRbCheckNo());
        if (currentType == 2 || currentType == 6) {
            holder.rl_detil_picture.setVisibility(View.GONE);
        }
        //检查内容
        holder.tv_check_content.setText(mItemData.get(position - 1).getName());
        //结论,不符合情况，图片
        Log.e("czys", checkno.toString());
        if (mItemData.get(position - 1).getJL().equals(mItemData.get(position - 1).getRbCheckNo())) {
            holder.rb_check_result_no.setChecked(true);
            holder.et_description.setText(mItemData.get(position - 1).getQK());
            if (!TextUtils.isEmpty(mItemData.get(position - 1).getTp())) {
                Glide.with(mContext).load(new File(PhotoViewModel.getCommonSupervisionDir(),
                        mItemData.get(position - 1).getTp())).into(holder.img_takephone);
                holder.img_camera.setVisibility(View.GONE);
            } else {
                holder.img_camera.setVisibility(View.VISIBLE);
                holder.img_camera.setImageResource(R.drawable.take_pictures);
                //Glide.with(mContext).load(new File(PhotoViewModel.getCommonSupervisionDir(),
                // mItemData.get(position - 1).getTp())).into(holder.img_takephone);
            }

        } else {
            holder.rb_check_result_yes.setChecked(true);

        }

    }

    private void bindBottomData(BottomViewHolder holder) {
        holder.bt_sign_first.setText(bottomBean.getBt_sign_first());
        holder.et_date.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date()));
        holder.et_name1.setText(uploadBean.getJcryxmyi());
        holder.et_person_no1.setText(uploadBean.getZfzhyi());
        String otherText = uploadBean.getCheckItems().get(uploadBean.getCheckItems().size() - 1).getName();
        holder.et_other.setText(TextUtils.isEmpty(otherText) ? "无" : otherText);
        Log.e("asd", "otherText = " + otherText);
        holder.et_name2.setText(uploadBean.getJcryxmer());
        holder.et_person_no2.setText(uploadBean.getZfzher());
        holder.et_check_advise.setText(uploadBean.getJcclyj());
        uuid = SPUtils.getInstance().getData("uuidd", null, String.class);
        Log.e("uuidd", "uuid bindBottomData" + uuid);
        for (File file : getCommonSupervision().listFiles()) {
            if (file.getName().contains(uuid + "_A1")) {
                Picasso.with(mContext).load(Uri.fromFile(new File(getCommonSupervision(),
                        file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(getBottomViewHolder().photo_view_group_photo);
            } else if (file.getName().contains(uuid + "_A2")) {
                Picasso.with(mContext).load(Uri.fromFile(new File(getCommonSupervision(),
                        file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(getBottomViewHolder().img_sign_check1);
            } else if (file.getName().contains(uuid + "_A3")) {
                Picasso.with(mContext).load(Uri.fromFile(new File(getCommonSupervision(),
                        file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(getBottomViewHolder().img_sign_check2);
            } else if (file.getName().contains(uuid + "_A4")) {
                Picasso.with(mContext).load(Uri.fromFile(new File(getCommonSupervision(),
                        file.getName()))).memoryPolicy(MemoryPolicy.NO_CACHE).into(getBottomViewHolder().img_sign_first);
            }
        }

    }

    private void bindTopData(TopViewHolder holder) {
        if (currentType == 2 || currentType == 6) {
            holder.tv_title_picture.setVisibility(View.GONE);
        }
        holder.et_FFarmName.setText(mTopData.getFFarmName());
        holder.et_FCityAdd.setText(mTopData.getFCityAdd());
        holder.et_FCategory.setText(mTopData.getFCategory());
        holder.et_clsl.setText(mTopData.getClsl());
        holder.et_FLegalPerson.setText(mTopData.getFLegalPerson());
        holder.et_FPhone.setText(mTopData.getFPhone());

        if (!topBean.getTv_FFarmName().equals("")) {
            holder.tv_FFarmNam.setText(topBean.getTv_FFarmName());
        }
        if (!topBean.getTv_FCategory().equals("")) {
            holder.tv_FCategory.setText(topBean.getTv_FCategory());
        } else {
            holder.lin_anim_type.setVisibility(View.GONE);
        }
        if (!topBean.getTv_clsl().equals("")) {
            holder.tv_clsl.setText(topBean.getTv_clsl());
        } else {
            holder.lin_anim_number.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_TOP.ordinal();
        }
        if (position == getItemCount() - 1) {
            return ITEM_TYPE.ITEM_TYPE_BOTTOM.ordinal();
        }
        if (currentType == 1 && position == 4) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 1 && position == 9) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 3 && position == 5) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 3 && position == 6) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 3 && position == 8) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 4 && position == 3) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 4 && position == 10) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 5 && position == 3) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        }
        if (currentType == 5 && position == 6) {
            return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
        }
      /*  switch (currentType){
            case CommonSupervisionDetilActivity.TABLE_1:
                if (position==4||position==9){
                    return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
                }else {
                    return ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
                }

            case CommonSupervisionDetilActivity.TABLE_3:
                //3,6,8
                if (position==3||position==6||position==8){
                    return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
                }else {
                    return ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
                }

            case CommonSupervisionDetilActivity.TABLE_4:
                //3,10
                if (position==3||position==10){
                    return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
                }else {
                    return ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
                }

            case CommonSupervisionDetilActivity.TABLE_5:
                //3,6
                if (position==3||position==6){
                    return ITEM_TYPE.ITEM_TYPE_COMMON_OTHER.ordinal();
                }else {
                    return ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
                }
        }
        return */
    }

    @Override
    public int getItemCount() {

        return getCount();
    }

    //获取条目数
    private int getCount() {
        int num = 0;
        switch (currentType) {
            case CommonSupervisionDetilActivity.TABLE_1:
                num = 28;
                break;
            case CommonSupervisionDetilActivity.TABLE_2:
                num = 31;
                break;
            case CommonSupervisionDetilActivity.TABLE_3:
                num = 15;
                break;
            case CommonSupervisionDetilActivity.TABLE_4:
                num = 17;
                break;
            case CommonSupervisionDetilActivity.TABLE_5:
                num = 18;
                break;
            case CommonSupervisionDetilActivity.TABLE_6:
                num = 21;
                break;
            case CommonSupervisionDetilActivity.TABLE_7:
                num = 16;
                break;
            case CommonSupervisionDetilActivity.TABLE_8:
                num = 20;
                break;
            case CommonSupervisionDetilActivity.TABLE_9:
                num = 28;
                break;
        }
        return num;
    }

    //顶部Viewholder
    private class TopViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_FFarmNam, tv_FCityAdd, tv_FCategory, tv_clsl, tv_FLegalPerson, tv_FPhone,
                tv_title_picture, tv_description, rg_check_result, tv_check_content;
        private EditText et_FFarmName, et_FCityAdd, et_FCategory, et_clsl, et_FLegalPerson, et_FPhone;
        private TextView bt_ercode;
        private Button bt_qy;
        private LinearLayout lin_anim_type, lin_anim_number;

        public TopViewHolder(View view) {
            super(view);
            lin_anim_type = (LinearLayout) view.findViewById(R.id.lin_anim_type);
            lin_anim_number = (LinearLayout) view.findViewById(R.id.lin_anim_number);
            tv_FFarmNam = ((TextView) view.findViewById(R.id.tv_FFarmName));
            tv_FCityAdd = ((TextView) view.findViewById(R.id.tv_FCityAdd));
            tv_FCategory = ((TextView) view.findViewById(R.id.tv_FCategory));
            tv_clsl = ((TextView) view.findViewById(R.id.tv_clsl));
            tv_FLegalPerson = ((TextView) view.findViewById(R.id.tv_FLegalPerson));
            tv_FPhone = ((TextView) view.findViewById(R.id.tv_FPhone));
            tv_title_picture = ((TextView) view.findViewById(R.id.tv_title_picture));
            tv_description = ((TextView) view.findViewById(R.id.tv_description));
            rg_check_result = ((TextView) view.findViewById(R.id.rg_check_result));
            tv_check_content = ((TextView) view.findViewById(R.id.tv_check_content));

            et_FFarmName = ((EditText) view.findViewById(R.id.et_FFarmName));
            et_FCityAdd = ((EditText) view.findViewById(R.id.et_FCityAdd));
            et_FCategory = ((EditText) view.findViewById(R.id.et_FCategory));
            et_clsl = ((EditText) view.findViewById(R.id.et_clsl));
            et_FLegalPerson = ((EditText) view.findViewById(R.id.et_FLegalPerson));
            et_FPhone = ((EditText) view.findViewById(R.id.et_FPhone));

            bt_ercode = ((TextView) view.findViewById(R.id.bt_ercode));
            bt_qy = ((Button) view.findViewById(R.id.bt_qy));

            bt_ercode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //开始二维码扫描 需要改变检查处理意见
                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) mContext,
                                new String[]{Manifest.permission.CAMERA},
                                10000);
                    } else {
                        ((Activity) mContext).startActivityForResult(new Intent(mContext,
                                CaptureActivity.class), CommonSupervisionDetilActivity.REQUEST_CODE_ACTIVITY_ZXING);
                    }
                }
            });
            bt_qy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CommonSupervisionQyActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("TableName", tableName);
                    intent.putExtras(bundle);
                    ((Activity) mContext).startActivityForResult(intent, CommonSupervisionDetilActivity.REQUEST_CODE_ACTIVITY_QY);
                }
            });

        }
    }

    public void setJC1(String funame, String fuseename) {
        uploadBean.setJcryxmyi(funame);
        uploadBean.setZfzhyi(fuseename);
    }

    //底部Viewholder
    public class BottomViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_group_photo, photo_view_group_photo, img_sign_check1, img_sign_check2, img_sign_first, img_sign_second;
        public EditText et_other, et_check_advise, et_name1, et_person_no1, et_name2, et_person_no2, et_date;
        private TextView bt_sign_check1, bt_sign_check2, bt_sign_first, bt_sign_second, bt_date;
        private Button upload;
        private LinearLayout lin_sign_check1, lin_sign_check2, lin_sign_first, lin_sign_second;

        public BottomViewHolder(View view) {
            super(view);
            img_group_photo = ((ImageView) view.findViewById(R.id.img_group_photo));
            photo_view_group_photo = ((ImageView) view.findViewById(R.id.photo_view_group_photo));
            img_sign_check1 = ((ImageView) view.findViewById(R.id.img_sign_check1));
            img_sign_check2 = ((ImageView) view.findViewById(R.id.img_sign_check2));
            img_sign_first = ((ImageView) view.findViewById(R.id.img_sign_first));
            img_sign_second = ((ImageView) view.findViewById(R.id.img_sign_second));

            lin_sign_check1 = ((LinearLayout) view.findViewById(R.id.lin_sign_check1));
            lin_sign_check2 = ((LinearLayout) view.findViewById(R.id.lin_sign_check2));
            lin_sign_first = ((LinearLayout) view.findViewById(R.id.lin_sign_first));
            lin_sign_second = ((LinearLayout) view.findViewById(R.id.lin_sign_second));

            et_other = ((EditText) view.findViewById(R.id.et_other));
            et_check_advise = ((EditText) view.findViewById(R.id.et_check_advise));
            et_name1 = ((EditText) view.findViewById(R.id.et_name1));
            et_person_no1 = ((EditText) view.findViewById(R.id.et_person_no1));
            et_name2 = ((EditText) view.findViewById(R.id.et_name2));
            et_person_no2 = ((EditText) view.findViewById(R.id.et_person_no2));
            et_date = ((EditText) view.findViewById(R.id.et_date));

            bt_sign_check1 = ((TextView) view.findViewById(R.id.bt_sign_check1));
            bt_sign_check2 = ((TextView) view.findViewById(R.id.bt_sign_check2));
            bt_sign_first = ((TextView) view.findViewById(R.id.bt_sign_first));
            bt_sign_second = ((TextView) view.findViewById(R.id.bt_sign_second));
            bt_date = ((TextView) view.findViewById(R.id.bt_date));
            upload = ((Button) view.findViewById(R.id.upload));

            photo_view_group_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // PhotoViewModel.takePicture(this, REQUEST_CODE_GROUP_PHOTO, GROUP_PHOTO_NAME, getCommonSupervisionDir())
                    onBottomImgListener.onClick(1, photo_view_group_photo, uploadBean);
                }
            });

            lin_sign_check1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBottomImgListener.onClick(2, img_sign_check1, uploadBean);
                }
            });
            lin_sign_check2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBottomImgListener.onClick(3, img_sign_check2, uploadBean);
                }
            });

            lin_sign_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBottomImgListener.onClick(4, img_sign_first, uploadBean);
                }
            });
            et_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBottomImgListener.onClick(5, et_date, uploadBean);
                }
            });
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadBean.setFName(mTopData.getFFarmName());
                    uploadBean.setFCityAdd(mTopData.getFCityAdd());
                    uploadBean.setFCategory(mTopData.getFCategory());
                    uploadBean.setClsl(mTopData.getClsl());
                    uploadBean.setFLegalPerson(mTopData.getFLegalPerson());
                    uploadBean.setFPhone(mTopData.getFPhone());
                    uploadBean.setGlid(mTopData.getFStId());
//                    if (topBean.getTv_clsl().equals("")) {
//                        uploadBean.setClsl(null);
//                    }
//                    if (topBean.getTv_FCategory().equals("")) {
//                        uploadBean.setFCategory(null);
//                    }


                    uploadListener.uploadListener(uploadBean);
                }
            });
            et_other.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    mItemData.get(mItemData.size() - 1).setName(s.toString());
                }
            });
            et_date.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    uploadBean.setJcdate(s.toString());
                }
            });

            et_check_advise.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    uploadBean.setJcclyj(s.toString());
                }
            });
            et_name1.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    uploadBean.setJcryxmyi(s.toString());
                }
            });
            et_name2.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    uploadBean.setJcryxmer(s.toString());
                }
            });
            et_person_no1.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    uploadBean.setZfzhyi(s.toString());
                }
            });
            et_person_no2.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    uploadBean.setZfzher(s.toString());
                }
            });


        }
    }

    public UploadCommonSupervisionBean getUploadBean() {
        return uploadBean;
    }

    public void setUploadBean(UploadCommonSupervisionBean bean) {
        uploadBean = bean;
        notifyDataSetChanged();
    }


    //中间部分的viewHolder
    private class ItemsViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_check_content, et_description;
        private RadioButton rb_check_result_yes, rb_check_result_no;
        private RadioGroup rg_check_result;
        private LinearLayout lin_add;
        private ImageView img_takephone, img_camera;
        private RelativeLayout rl_detil_picture;

        public ItemsViewHolder(View view) {
            super(view);
            tv_check_content = (TextView) view.findViewById(R.id.tv_check_content);
            et_description = (TextView) view.findViewById(R.id.et_description);
            rb_check_result_yes = (RadioButton) view.findViewById(R.id.rb_check_result_yes);
            rb_check_result_no = (RadioButton) view.findViewById(R.id.rb_check_result_no);
            rg_check_result = (RadioGroup) view.findViewById(R.id.rg_check_result);
            lin_add = (LinearLayout) view.findViewById(R.id.lin_add);
            img_takephone = (ImageView) view.findViewById(R.id.img_takephone);
            img_camera = (ImageView) view.findViewById(R.id.img_camera);
            rl_detil_picture = (RelativeLayout) view.findViewById(R.id.rl_detil_picture);

            rg_check_result.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_check_result_yes:
                            et_description.setClickable(false);
                            et_description.setVisibility(View.GONE);
                            lin_add.setVisibility(View.GONE);
                            img_takephone.setVisibility(View.GONE);
                            img_camera.setVisibility(View.GONE);
                            et_description.setText("");
                            lin_add.removeAllViews();
                            mItemData.get(getAdapterPosition() - 1).setJL(mItemData.get(getAdapterPosition() - 1).getRbCheckYes());
                            mItemData.get(getAdapterPosition() - 1).setTp(null);
                            mItemData.get(getAdapterPosition() - 1).setQK(null);
                            if (mItemData.get(getAdapterPosition() - 1).getList() != null) {
                                for (int i = 0; i < mItemData.get(getAdapterPosition() - 1).getList().size(); i++) {
                                    if (mItemData.get(getAdapterPosition() - 1).getList().get(i) != null) {
                                        mItemData.get(getAdapterPosition() - 1).getList().get(i).setCheek(false);
                                    }
                                }
                            }
                            if (onCheckChangeListener != null) {
                                onCheckChangeListener.deleteFlie(getAdapterPosition());
                                img_takephone.setBackground(null);
                            }
                            break;
                        case R.id.rb_check_result_no:
                            mItemData.get(getAdapterPosition() - 1).setJL(mItemData.get(getAdapterPosition() - 1).getRbCheckNo());
                            et_description.setClickable(true);
                            et_description.setVisibility(View.VISIBLE);
                            img_takephone.setVisibility(View.VISIBLE);
                            if (img_takephone.getDrawable() == null) {
                                img_camera.setVisibility(View.VISIBLE);
                            } else {
                                img_camera.setVisibility(View.GONE);
                            }
                            img_camera.setImageResource(R.drawable.take_pictures);
                            break;
                    }
                }
            });

            et_description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rb_check_result_no.isChecked())
                        onCheckChangeListener.inputText(getAdapterPosition(), et_description.getText().toString());
                }
            });
            img_takephone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = imgClickListener.onClick(img_takephone, getAdapterPosition());
                    mItemData.get(getAdapterPosition() - 1).setTp(s);

                }
            });

            img_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = imgClickListener.onClick(img_takephone, getAdapterPosition());
                    mItemData.get(getAdapterPosition() - 1).setTp(s);
                }
            });

            et_description.addTextChangedListener(new TextWatchImp() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    mItemData.get(getAdapterPosition() - 1).setQK(s.toString());
                }
            });


        }
    }

    private class ItemsViewOtherHolder extends RecyclerView.ViewHolder {
        private TextView tv_check_content;
        private RadioButton rb_check_result_yes, rb_check_result_no;
        private RadioGroup rg_check_result;
        private LinearLayout lin_add;
        private ImageView img_takephone, img_camera;
        private RelativeLayout rl_detil_picture;

        public ItemsViewOtherHolder(View view) {
            super(view);
            tv_check_content = (TextView) view.findViewById(R.id.tv_check_content);
            rb_check_result_yes = (RadioButton) view.findViewById(R.id.rb_check_result_yes);
            rb_check_result_no = (RadioButton) view.findViewById(R.id.rb_check_result_no);
            rg_check_result = (RadioGroup) view.findViewById(R.id.rg_check_result);
            lin_add = (LinearLayout) view.findViewById(R.id.lin_add);
            img_takephone = (ImageView) view.findViewById(R.id.img_takephone);
            img_camera = (ImageView) view.findViewById(R.id.img_camera);


            rl_detil_picture = (RelativeLayout) view.findViewById(R.id.rl_detil_picture);
            rg_check_result.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rb_check_result_yes:
                            lin_add.setVisibility(View.GONE);
                            img_takephone.setVisibility(View.GONE);
                            img_camera.setVisibility(View.GONE);

                            lin_add.removeAllViews();
                            mItemData.get(getAdapterPosition() - 1).setJL(mItemData.get(getAdapterPosition() - 1).getRbCheckYes());
                            mItemData.get(getAdapterPosition() - 1).setTp(null);
                            mItemData.get(getAdapterPosition() - 1).setQK(null);
                            if (mItemData.get(getAdapterPosition() - 1).getList() != null) {
                                for (int i = 0; i < mItemData.get(getAdapterPosition() - 1).getList().size(); i++) {
                                    if (mItemData.get(getAdapterPosition() - 1).getList().get(i) != null) {
                                        mItemData.get(getAdapterPosition() - 1).getList().get(i).setCheek(false);
                                    }
                                }
                            }
                            if (onCheckChangeListener != null)
                                onCheckChangeListener.deleteFlie(getAdapterPosition());
                            break;
                        case R.id.rb_check_result_no:
                            mItemData.get(getAdapterPosition() - 1).setJL(mItemData.get(getAdapterPosition() - 1).getRbCheckNo());
                            lin_add.setVisibility(View.VISIBLE);
                            if (currentType == 1 && getAdapterPosition() == 4) {
                                lin_add.addView(getCheckView(3));
                            }
                            if (currentType == 1 && getAdapterPosition() == 9) {
                                lin_add.addView(getCheckView(8));
                            }
                            if (currentType == 3 && getAdapterPosition() == 5) {
                                lin_add.addView(getCheckView(4));
                            }
                            if (currentType == 3 && getAdapterPosition() == 6) {
                                lin_add.addView(getCheckView(5));
                            }
                            if (currentType == 3 && getAdapterPosition() == 8) {
                                lin_add.addView(getCheckView(7));
                            }
                            if (currentType == 4 && getAdapterPosition() == 3) {
                                lin_add.addView(getCheckView(2));
                            }
                            if (currentType == 4 && getAdapterPosition() == 10) {
                                lin_add.addView(getCheckView(9));
                            }
                            if (currentType == 5 && getAdapterPosition() == 3) {
                                lin_add.addView(getCheckView(2));
                            }
                            if (currentType == 5 && getAdapterPosition() == 6) {
                                lin_add.addView(getCheckView(5));
                            }
                            img_takephone.setVisibility(View.VISIBLE);
                            if (img_takephone.getDrawable() != null) {
                                img_camera.setVisibility(View.GONE);
                            } else {
                                img_camera.setImageResource(R.drawable.take_pictures);
                                img_camera.setVisibility(View.VISIBLE);
                            }

                            break;
                    }
                }
            });

            img_takephone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = imgClickListener.onClick(img_takephone, getAdapterPosition());
                    mItemData.get(getAdapterPosition() - 1).setTp(s);

                }
            });


        }
    }

    public View getCheckView(int position) {
        LinearLayout view3 = (LinearLayout) LayoutInflater.from(MyApplication.getContext()).
                inflate(R.layout.adapter_richang_item_layout, null);
        int size = mItemData.get(position).getList().size();
        for (int i = 0; i < size; i++) {
            CheckBox cb1 = new CheckBox(mContext);
            cb1.setPadding(10, 10, 10, 10);
            cb1.setButtonDrawable(R.drawable.check_box_select);//替换drawable
            cb1.setId(i);
            view3.addView(cb1);

            cb1.setText(mItemData.get(position).getList().get(i).getXZ());
            if (mItemData.get(position).getList().get(i).isCheek()) {
                cb1.setChecked(true);
            }
            cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int id = cb1.getId();
                    mItemData.get(position).getList().get(id).setXZ((String) cb1.getText());
                    mItemData.get(position).getList().get(id).setCheek(b);
                    StringBuilder builder = new StringBuilder();
                    if (mItemData.get(position).getList() != null) {
                        for (int n = 0; n < mItemData.get(position).getList().size(); n++) {
                            if (mItemData.get(position).getList().get(n).isCheek()) {
                                builder.append(mItemData.get(position).getList().get(n).getXZ() + ",");
                            }
                        }
                        mItemData.get(position).setCs(builder.toString());
                        mItemData.get(position).setQK(builder.toString());
                    }
                }
            });
        }
        return view3;
    }

    public interface OnCheckChangeListener {
        void deleteFlie(int position);

        void inputText(int position, String etString);
    }

    public interface OnBottomImgListener<T> {
        void onClick(int flag, T img, UploadCommonSupervisionBean bean);
    }

    /**
     * 不想每次都实现这么长
     */
    private class TextWatchImp implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public interface ImgClickListener {
        String onClick(ImageView img, int position);
    }

    public interface UploadListener {
        void uploadListener(UploadCommonSupervisionBean bean);
    }

    public boolean check() {
        if (currentType != 2 && currentType != 6) {
            for (int i = 0; i < uploadBean.getCheckItems().size() - 1; i++) {
                CheckContentEntity entity = uploadBean.getCheckItems().get(i);
                if (entity.getRbCheckNo().equals(entity.getJL()) && TextUtils.isEmpty(entity.getTp())) {
                    Toast.makeText(mContext, "当选择不符合时,必须上传对应照片", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
            return false;
        }

        if (TextUtils.isEmpty(topViewHolder.et_FFarmName.getText())) {
            Toast.makeText(mContext, "扫码信息错误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bottomViewHolder.img_group_photo.getDrawable() == null) {
            Toast.makeText(mContext, "合照必须拍摄", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bottomViewHolder.img_sign_check1.getDrawable() == null) {
            Toast.makeText(mContext, "检查人员1必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bottomViewHolder.img_sign_check2.getDrawable() == null) {
            Toast.makeText(mContext, "检查人员2必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bottomViewHolder.img_sign_first.getDrawable() == null) {
            Toast.makeText(mContext, bottomBean.getBt_sign_first() + "必须签字", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
