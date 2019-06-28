package com.wyw.jiangsu.activity.zhifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.ChufaListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mnkj004 on 2017/8/11.
 */

public class ChufaDetailActivity extends MVPActivity {

    @BindView(R.id.jf)
    EditText jf;
    @BindView(R.id.ajsj)
    EditText ajsj;
    @BindView(R.id.et_hao)
    EditText etHao;
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
    @BindView(R.id.zhuzhi)
    TextView zhuzhi;
    @BindView(R.id.shenfennum)
    EditText shenfennum;
    @BindView(R.id.gsname)
    EditText gsname;
    @BindView(R.id.daibiaopeople)
    TextView daibiaopeople;
    @BindView(R.id.gsadress)
    TextView gsadress;
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
    @BindView(R.id.SV)
    ScrollView SV;
    @BindView(R.id.view_container)
    FrameLayout viewContainer;
    private AnimalQuarantineActivityBean.DataListBean dataListBean;
    private ChufaListBean.DataListBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jueding_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("出具当场决定处罚书详情");
        Intent intent = getIntent();
        data = (ChufaListBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
        String tableName = intent.getStringExtra("table");
        //简罚  jf
        jf.setText(data.getJf());
        // 右   ajsj
        ajsj.setText(data.getSdate());
        //号   et_hao  Bhao
        etHao.setText(data.getBhao());
//        etHao.setText();
        //姓名   et_name
        etName.setText(data.getXm());
        //性别    nan rb_apply_anim nv rb_apply_material
        if ((data.getXb().equals("男"))) {
            rbApplyAnim.setChecked(true);
            rbApplyMaterial.setChecked(false);
        } else {
            rbApplyAnim.setChecked(false);
            rbApplyMaterial.setChecked(true);
        }
        //年龄   age
        age.setText(data.getNn());
        //电话  phone
        phone.setText(data.getDh());
        //住址  zhuzhi
        zhuzhi.setText(data.getZhuzhi());
        //身份证号  shenfennum
        shenfennum.setText(data.getSfzh());
        //公司名称 gsname
        gsname.setText(data.getDwmc());
        //公司代表人  StyleEdit
        daibiaopeople.setText(data.getFddbr());
        //公司地址 gsadress
        gsadress.setText(data.getDizhi());
        //公司电话 gsphone
        gsphone.setText(data.getDwdh());
        //违法事实  wefashi
        wefashi.setText(data.getWfss());
        //处罚依据及内容  chufayj
        chufayj.setText(data.getCfyj());
        //告知事项  几日 jiri  到 jnfakuan
        jiri.setText(data.getJiri());
        jnfakuan.setText(data.getJnfk());
        //人名政府           renminzf
        renminzf.setText(data.getRmzf());
        //或    huo
        huo.setText(data.getHuo());
        //   三个月内       rmfy
        rmfy.setText(data.getRmfy());
        //姓名    zfryxm1
        zfryxm1.setText(data.getZfryxmyi());
        //执法证件号  zfzjh1
        zfzjh1.setText(data.getZfzjhyi());
        //姓名   zfryxm2
        zfryxm2.setText(data.getZfryxmer());
        //执法证件号   zhzh2
        zhzh2.setText(data.getZfzjher());
        //是否立即执行   rb_shi_anim 是 rb_fou_material否if (rbShiAnim.isChecked()) {
        if (data.getSfdczx().equals("是")){
            rbShiAnim.setChecked(true);
            rbFouMaterial.setChecked(false);
        }else {
            rbShiAnim.setChecked(false);
            rbFouMaterial.setChecked(true);
        }
        //执法时间  tv_zfsj
        tvZfsj.setText(data.getZfdate());
        //当事人签收  dsrqh  tDsrqs
        dsrqh.setText(data.getDsrqs());
    }

    @Override
    public void bindListener() {

    }
}
