package com.wyw.jiangsu.activity.zhifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.XunWenListBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mnkj004 on 2017/8/11.
 */

public class XunwenDetailActivity extends MVPActivity {

    @BindView(R.id.et_xunwen_didian)
    EditText etXunwenDidian;
    @BindView(R.id.et_xunwen_jiguan)
    EditText etXunwenJiguan;
    @BindView(R.id.et_xunwen_renyuan1)
    EditText etXunwenRenyuan1;
    @BindView(R.id.et_zhifa_zhengjianhao1)
    EditText etZhifaZhengjianhao1;
    @BindView(R.id.et_xunwen_renyuan2)
    EditText etXunwenRenyuan2;
    @BindView(R.id.et_zhifa_zhengjianhao2)
    EditText etZhifaZhengjianhao2;
    @BindView(R.id.et_jiluren)
    EditText etJiluren;
    @BindView(R.id.et_beixunwen_name)
    EditText etBeixunwenName;
    @BindView(R.id.et_beixunwen_age)
    EditText etBeixunwenAge;
    @BindView(R.id.et_beixunwen_sex)
    EditText etBeixunwenSex;
    @BindView(R.id.et_beixunwen_phone)
    EditText etBeixunwenPhone;
    @BindView(R.id.et_beixunwen_id)
    EditText etBeixunwenId;
    @BindView(R.id.et_beixunwen_danwei)
    EditText etBeixunwenDanwei;
    @BindView(R.id.et_beixunwen_work)
    EditText etBeixunwenWork;
    @BindView(R.id.et_beixunwen_adress)
    EditText etBeixunwenAdress;
    @BindView(R.id.et_zhifa_renyuan)
    EditText etZhifaRenyuan;
    @BindView(R.id.et_xunwen_bilu)
    EditText etXunwenBilu;
    @BindView(R.id.lin_conversation)
    LinearLayout linConversation;
    private AnimalQuarantineActivityBean.DataListBean dataListBean;
    private XunWenListBean.DataListBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xunwen_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("询问笔录详情");
        Intent intent = getIntent();
        data = (XunWenListBean.DataListBean) intent.getSerializableExtra(Constance.ACTIVITY_DATA);
        String tableName = intent.getStringExtra("table");

        //询问地点  etXunwenDidian a
        etXunwenDidian.setText(data.getFdz());
        //询问机关  etXunwenJiguan b
        etXunwenJiguan.setText(data.getFjg());
        //询问人1   etXunwenRenyuan1 c
        etXunwenRenyuan1.setText(data.getFxwr());
        //执法证件号1  etZhifaZhengjianhao1 d
        etZhifaZhengjianhao1.setText(data.getFzfzh());
        //询问人2 etXunwenRenyuan2 e
        etXunwenRenyuan2.setText(data.getFxwr1());
        // 执法证件号2    etZhifaZhengjianhao2 f
        etZhifaZhengjianhao2.setText(data.getFzfzh1());
        //记录人   etJiluren h
        etJiluren.setText(data.getFjlr());
        //被询问人 etBeixunwenName i
        etBeixunwenName.setText(data.getFname());
        //年龄 etBeixunwenAge j
        etBeixunwenAge.setText(data.getFavg());
        // 性别 et_beixunwen_sex k
        etBeixunwenSex.setText(data.getFsex());
        //联系电话   et_beixunwen_phone l
        etBeixunwenPhone.setText(data.getFlxdh());
        //身份证号 et_beixunwen_id m
        etBeixunwenId.setText(data.getFsfzh());
        //工作单位 etBeixunwenDanwei n
        etBeixunwenDanwei.setText(data.getFgzdw());
        //职务   et_beixunwen_work o
        etBeixunwenWork.setText(data.getFzw());
        //住址   et_beixunwen_adress p
        etBeixunwenAdress.setText(data.getFzhuz());
        //问   etZhifaRenyuan k
        etZhifaRenyuan.setText(data.getFzfry());
                //答
        etXunwenBilu.setText(data.getA1());


        /* bean.setFGlid(Integer.parseInt(Glid));  ////案件登记关联id
        bean.setFsdate(spinnerYear.getSelectedItem().toString() + "-" + spinnerMonth.getSelectedItem().toString()
                + "-" + spinnerDay.getSelectedItem().toString());//询问时间
        bean.setFdz(OtherUtil.toString(etXunwenDidian));  //询问地点
        bean.setFjg(OtherUtil.toString(etXunwenJiguan));  //询问机关
        bean.setFxwr(OtherUtil.toString(etXunwenRenyuan1));  //询问人1
        bean.setFzfzjh(OtherUtil.toString(etZhifaZhengjianhao1));  //执法证件号1
        bean.setFxwr1(OtherUtil.toString(etXunwenRenyuan2));  //询问人2
        bean.setFzfzjh1(OtherUtil.toString(etZhifaZhengjianhao2));  //执法证件号2
        bean.setFjlr(OtherUtil.toString(etJiluren));  //记录人
        bean.setFname(OtherUtil.toString(etBeixunwenName));  //姓名
        bean.setFsex(spBeixunwenSex.getSelectedItem().toString());  //性别
        bean.setFlxdh(OtherUtil.toString(etBeixunwenPhone));  //联系电话
        bean.setFavg(OtherUtil.toString(etBeixunwenAge));  //年龄
        bean.setFsfzh(OtherUtil.toString(etBeixunwenId));  //身份证号
        bean.setFgzdw(OtherUtil.toString(etBeixunwenDanwei));  //工作单位
        bean.setFzw(OtherUtil.toString(etBeixunwenWork));  //职务
        bean.setFzhuz(OtherUtil.toString(etBeixunwenAdress));  //住址
        bean.setFzfry(OtherUtil.toString(etZhifaRenyuan));  //执法人员（执法证号）
        bean.setA1(OtherUtil.toString(etXunwenBilu));       //第一个答
        bean.setFshij(spinnerStartShi.getSelectedItem().toString());//小时1
        bean.setFfengz(spinnerStartFen.getSelectedItem().toString());//分钟1
        bean.setFshij1(spinnerEndShi.getSelectedItem().toString()); //小时2
        bean.setFfengz1(spinnerEndFen.getSelectedItem().toString());//分钟2
        bean.setFSunitUstrId(getPresenter().getUser().getFSUNITUSTRID());//行政id
        bean.setFSuserId(getPresenter().getUserId());                   //用户ID
        bean.setFajbh(fanbh);                                       //案件编号
        bean.setDatalist(conversationModel.getData());              //问答数据*/

    }

    @Override
    public void bindListener() {

    }
}
