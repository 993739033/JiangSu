package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wyw.jiangsu.R;
import com.wyw.jiangsu.bean.ModifyBean;
import com.wyw.jiangsu.interfac.IModifyPwd;
import com.wyw.jiangsu.presenter.ModifyPwdActivityPresenter;
import com.wyw.jiangsu.utils.OtherUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HUANG on 2017/9/13.
 */
public class ModifyPwdActivity extends MVPActivity<ModifyPwdActivityPresenter> implements IModifyPwd {
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
    @BindView(R.id.et_user_id)
    EditText etUserId;
    @BindView(R.id.et_oldpwd)
    EditText etOldpwd;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd_new)
    EditText etPwdNew;
    @BindView(R.id.et_pwd_ensure)
    EditText etPwdEnsure;
    @BindView(R.id.btn_ensure)
    Button btnEnsure;
    private String name;
    private String pwd;
    public static int RESULT_CODE=1001;
    private int uid;
    private String username;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modifypassword);
        ButterKnife.bind(this);
    }

    @Override
    protected ModifyPwdActivityPresenter createPresenter() {
        return new ModifyPwdActivityPresenter(this);
    }

    @Override
    public void bindData() {
        setTitle("密码修改");
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        pwd = intent.getStringExtra("pwd");
        phone = intent.getStringExtra("phone");
        uid = intent.getIntExtra("uid",-1);
        //username
        username = intent.getStringExtra("username");
        etUserId.setText(name);
        etOldpwd.setText(pwd);
        etName.setText(username);
        etPhone.setText(phone);
    }

    @Override
    public void bindListener() {
        btnEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()){
                    getPresenter().upload(new Gson().toJson(getData()),uid,"Sys_UsersNew",null);
                }
            }
        });
    }
    public ModifyBean getData(){
        ModifyBean modifyBean = new ModifyBean();
        String userId = this.etUserId.getText().toString();
        String oldpwd = etOldpwd.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String pwdnew = etPwdNew.getText().toString();
        String ensurepwd = etPwdEnsure.getText().toString();

        modifyBean.setFuAccount(userId);
        modifyBean.setFuPassWord(oldpwd);
        modifyBean.setFuName(name);
        modifyBean.setFuPhone(phone);
        modifyBean.setFuPassWordX(pwdnew);
        modifyBean.setFuPassWordQ(ensurepwd);
        modifyBean.setIp(OtherUtil.getPhoneIP());
        return modifyBean;

    }
    public boolean check(){
        if (TextUtils.isEmpty(etUserId.getText().toString())){
            showToast("账号不能为空");
            etUserId.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etOldpwd.getText().toString())){
            showToast("原密码不能为空");
            etOldpwd.requestFocus();
            return false;
        }
        if (!OtherUtil.isPwdType(etOldpwd.getText().toString())){
            showToast("原密码不符合格式");
            etOldpwd.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etName.getText().toString())){
            showToast("姓名不能为空");
            etName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPhone.getText().toString())){
            showToast("手机号不能为空");
            etPhone.requestFocus();
            return false;
        }
        if (!OtherUtil.isPhoneNumber(etPhone.getText().toString())){
            showToast("手机号不符合");
            etPhone.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPwdNew.getText().toString())){
            showToast("新密码不能为空");
            etUserId.requestFocus();
            return false;
        }
        if (!OtherUtil.isPwdType(etOldpwd.getText().toString())){
            showToast("新密码不符合格式");
            etOldpwd.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etPwdEnsure.getText().toString())){
            showToast("确认密码不能为空");
            etPwdEnsure.requestFocus();
            return false;
        }
        if (etPwdNew.getText().toString().equals("123456")){
            showToast("新密码不可以为123456");
            etPwdEnsure.requestFocus();
            return false;
        }
        if (!etPwdNew.getText().toString().equals(etPwdEnsure.getText().toString())){
            showToast("两次密码不一致");
            etPwdNew.requestFocus();
            return false;
        }


        return true;
    }

    @Override
    public void uploadSuccess() {
        Toast.makeText(this,"修改成功",Toast.LENGTH_LONG).show();
        setResult(RESULT_CODE);
        finish();
    }
}
