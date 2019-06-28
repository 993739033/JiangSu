package com.wyw.jiangsu.activity.zhifa;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AddDangchangjdshuActivityBean;
import com.wyw.jiangsu.bean.AnjiandengjiActivityBean;
import com.wyw.jiangsu.dialog.AddressDialog;
import com.wyw.jiangsu.dialog.datepickdialog.TimeSelectDialog;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.interfac.IAddPunishmentBookActivity;
import com.wyw.jiangsu.interfac.ITime;
import com.wyw.jiangsu.presenter.AddPunishmentBookActivityPresenter;
import com.wyw.jiangsu.presenter.TimePresenter;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加出具当场处罚决定书
 */
public class AddPunishmentBookActivity extends MVPActivity<AddPunishmentBookActivityPresenter> implements IAddPunishmentBookActivity, ITime {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yonghu_mingzi)
    TextView tvYonghuMingzi;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.jf)
    EditText jf;
    @BindView(R.id.ajsj)
    EditText ajsj;
    @BindView(R.id.chose)
    Button chose;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rb_apply_anim)
    RadioButton rbApplyAnim;
    @BindView(R.id.rb_apply_material)
    RadioButton rbApplyMaterial;
    @BindView(R.id.rg_apply_type)
    RadioGroup rgApplyType;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.tv_zz_select)
    TextView tv_zz_select;
    @BindView(R.id.et_xx_address)
    EditText et_xx_address;
    @BindView(R.id.shenfennum)
    EditText shenfennum;
    @BindView(R.id.gsname)
    EditText gsname;
    @BindView(R.id.daibiaopeople)
    EditText daibiaopeople;
    @BindView(R.id.tv_gs_select)
    TextView tv_gs_select;
    @BindView(R.id.et_gs_address)
    EditText et_gs_address;
    @BindView(R.id.gsphone)
    EditText gsphone;
    @BindView(R.id.wefashi)
    EditText wefashi;
    @BindView(R.id.chufayj)
    EditText chufayj;
    @BindView(R.id.jiri)
    EditText jiri;
    @BindView(R.id.jnfakuan)
    EditText jnfakuan;
    @BindView(R.id.renminzf)
    EditText renminzf;
    @BindView(R.id.huo)
    EditText huo;
    @BindView(R.id.rmfy)
    EditText rmfy;
    @BindView(R.id.zfryxm1)
    TextView zfryxm1;
    @BindView(R.id.zfzjh1)
    TextView zfzjh1;
    @BindView(R.id.zfryxm2)
    TextView zfryxm2;
    @BindView(R.id.zhzh2)
    TextView zhzh2;
    @BindView(R.id.rb_shi_anim)
    RadioButton rbShiAnim;
    @BindView(R.id.rb_fou_material)
    RadioButton rbFouMaterial;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.tv_zfsj)
    TextView tvZfsj;
    @BindView(R.id.dsrqh)
    EditText dsrqh;
    @BindView(R.id.bt_baocun)
    Button btBaocun;
    @BindView(R.id.SV)
    ScrollView SV;
    @BindView(R.id.view_container)
    FrameLayout viewContainer;
    @BindView(R.id.et_hao)
    EditText etHao;
    TimePresenter timePresenter;
    private AddDangchangjdshuActivityBean bean;
    private DatePickerDialog dateDialogStart;
    private List<AnjiandengjiActivityBean.DataListBean> anjianbean;
    private AnjiandengjiActivityBean.DataListBean databean;
    private String Glid;
    private String fanbh;

    private AddressDialog addressDialog;//住址选择
    private AddressDialog gsAddressDialog;//公司住址选择
    private TimeSelectDialog timeSelectDialog;//时间选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dangchangjdshu);
        ButterKnife.bind(this);
    }

    @Override
    protected AddPunishmentBookActivityPresenter createPresenter() {
        return new AddPunishmentBookActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("出具当场决定处罚书");
        timePresenter = new TimePresenter(this);
        timePresenter.getTime();
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        addressDialog = new AddressDialog(this);
        addressDialog.setOnDecideListener(new AddressDialog.IOnDecideListener() {
            @Override
            public void onCancel(View v) {
                addressDialog.dismiss();
            }

            @Override
            public void onSure(View v, String province, String city, String district) {
                tv_zz_select.setText(province+city+district);
                addressDialog.dismiss();
            }
        });

        gsAddressDialog= new AddressDialog(this);
        gsAddressDialog.setOnDecideListener(new AddressDialog.IOnDecideListener() {
            @Override
            public void onCancel(View v) {
                gsAddressDialog.dismiss();
            }

            @Override
            public void onSure(View v, String province, String city, String district) {
                tv_gs_select.setText(province+city+district);
                gsAddressDialog.dismiss();
            }
        });
        timeSelectDialog = new TimeSelectDialog(this, "选择报检时间");
        timeSelectDialog.setmTimePickListener1(new TimeSelectDialog.TimePickListener1() {
            @Override
            public void getTime(String year, String month, String day, String hour) {
                tvZfsj.setText(year+"-"+month+"-"+day);
            }
        });

    }

    @Override
    public void bindListener() {
        btBaocun.setOnClickListener(view -> {
            if (check()) {
                showNormalDialog();
            }
        });
        chose.setOnClickListener(view -> {
            startActivityForResult(new Intent(this, AnjiandengjiActivity.class), Constance.ACTIVITY_CODE);

        });
        tv_zz_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressDialog.show();
            }
        });

        tv_gs_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsAddressDialog.show();
            }
        });

        tvZfsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelectDialog.show();
            }
        });
    }
    private void showNormalDialog(){
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(AddPunishmentBookActivity.this);
        dialog.setTitle("您是否确定保存");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().upload(getdate());
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.ACTIVITY_CODE) {
                databean = (AnjiandengjiActivityBean.DataListBean) data.getSerializableExtra(Constance.ACTIVITY_DATA);
                Glid = databean.getFStId();
                ajsj.setText(databean.getFdjrq());
                String str = databean.getFname();
                File file = new File(str);
                String filename = file.getName();
                String fil = filename.substring(filename.lastIndexOf(",") + 1);
                String fill = filename.substring(0, filename.lastIndexOf(","));
                zfryxm1.setText(fill);
                zfryxm2.setText(fil);
                String zfh = databean.getFzfzjh();
                File file1 = new File(zfh);
                String filename1 = file1.getName();
                String zfh2 = filename1.substring(filename1.lastIndexOf(",") + 1);
                String zhf1 = filename1.substring(0, filename1.lastIndexOf(","));
                zfzjh1.setText(zhf1);
                zhzh2.setText(zfh2);
                //案件编号
                fanbh = databean.getFanbh();
            }

        }
    }


    @Override
    public void uploadSucceed() {
        Toast.makeText(this, "上传完成", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean check() {
        if (TextUtils.isEmpty(Glid)) {
            showToast("请选择案件");
            return false;
        }
        if (TextUtils.isEmpty(jf.getText().toString())) {
            showToast("请填写简罚");
            return false;
        }
        if (TextUtils.isEmpty(etHao.getText().toString())) {
            showToast("请填写案件号");
            return false;
        }
        if (TextUtils.isEmpty(etName.getText().toString())) {
            showToast("请填写姓名");
            return false;
        }
        if (TextUtils.isEmpty(age.getText().toString())) {
            showToast("请填写年龄");
            return false;
        }
        if (TextUtils.isEmpty(phone.getText().toString())) {
            showToast("请填写电话");
            return false;
        }
        if (tv_zz_select.getText().toString().equals("请选择")){
            showToast("请选择住址");
            return false;
        }
        if (TextUtils.isEmpty(et_xx_address.getText().toString())) {
            showToast("请填写详细住址");
            return false;
        }
        if (TextUtils.isEmpty(shenfennum.getText().toString())) {
            showToast("请填写身份证号");
            return false;
        }
        if (TextUtils.isEmpty(gsname.getText().toString())) {
            showToast("请填写公司名称");
            return false;
        }
        if (TextUtils.isEmpty(daibiaopeople.getText().toString())) {
            showToast("请填写公司代表人");
            return false;
        }
        if (tv_gs_select.getText().toString().equals("请选择")){
            showToast("请选择公司住址");
            return false;
        }
        if (TextUtils.isEmpty(et_gs_address.getText().toString())) {
            showToast("请填写公司详细住址");
            return false;
        }

        if (TextUtils.isEmpty(gsphone.getText().toString())) {
            showToast("请填写公司电话");
            return false;
        }
        if (TextUtils.isEmpty(wefashi.getText().toString())) {
            showToast("请填写违法事实");
            return false;
        }
        if (TextUtils.isEmpty(chufayj.getText().toString())) {
            showToast("请填写处罚依据及内容");
            return false;
        }
        if (TextUtils.isEmpty(jiri.getText().toString())) {
            showToast("请填写告知事项第一项");
            return false;
        }
        if (TextUtils.isEmpty(jnfakuan.getText().toString())) {
            showToast("请填写告知事项第二项");
            return false;
        }
        if (TextUtils.isEmpty(renminzf.getText().toString())
                || TextUtils.isEmpty(huo.getText().toString())
                || TextUtils.isEmpty(rmfy.getText().toString())) {
            showToast("请填写告知事项第三项");
            return false;
        }
        if (TextUtils.isEmpty(dsrqh.getText().toString())) {
            showToast("请填写当事人签收");
            return false;
        }
        return true;
    }

    /**
     * 上传的数据
     */
    private AddDangchangjdshuActivityBean getdate() {
        bean = new AddDangchangjdshuActivityBean();
        bean.setFSunitUstrId(getPresenter().getUser().getFSUNITUSTRID());
        bean.setGlid(Integer.parseInt(Glid));
        bean.setJf(jf.getText().toString());
        bean.setSdate(ajsj.getText().toString());
        bean.setXm(etName.getText().toString());
        bean.setXb(rbApplyAnim.getText().toString());
        if (rbApplyAnim.isChecked()) {
            bean.setXb("男");
        } else if (rbApplyMaterial.isChecked()) {
            bean.setXb("女");
        }
        bean.setNn(age.getText().toString());
        bean.setDh(phone.getText().toString());
        bean.setSfzh(shenfennum.getText().toString());
        bean.setZhuzhi(tv_zz_select.getText().toString()+et_xx_address.getText().toString());
        bean.setDwmc(gsname.getText().toString());
        bean.setFddbr(daibiaopeople.getText().toString());
        bean.setDizhi(tv_gs_select.getText().toString()+et_gs_address.getText().toString());
        bean.setDwdh(gsphone.getText().toString());
        bean.setWfss(wefashi.getText().toString());
        bean.setCfyj(chufayj.getText().toString());
        bean.setJiri(jiri.getText().toString());
        bean.setJnfk(jnfakuan.getText().toString());
        bean.setHuo(huo.getText().toString());
        bean.setRmfy(rmfy.getText().toString());
        bean.setZfryxmyi(zfryxm1.getText().toString());
        bean.setZfzjhyi(zfzjh1.getText().toString());
        bean.setZfryxmer(zfryxm2.getText().toString());
        bean.setZfzjher(zhzh2.getText().toString());
        bean.setZfdate(tvZfsj.getText().toString());
        bean.setRmzf(renminzf.getText().toString());            //人民政府
        if (rbShiAnim.isChecked()) {
            bean.setSfdczx("是");
        } else if (rbFouMaterial.isChecked()) {
            bean.setSfdczx("否");
        }
        bean.setDsrqs(dsrqh.getText().toString());
        bean.setBhao(etHao.getText().toString());
        bean.setFajbh(fanbh);                           //案件编号
        bean.setFSuserId(getPresenter().getUserId());   //用户ID
        return bean;
    }



    @Override
    public void setTime(String time, long longTime) {
        String riqi = time.substring(0, 11);
        tvZfsj.setText(riqi);//报检时间
    }
}
