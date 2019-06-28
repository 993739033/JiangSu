package com.wyw.jiangsu.activity.chaxun;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyw.jiangsu.R;
import com.wyw.jiangsu.activity.MVPActivity;
import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.interfac.Constance;
import com.wyw.jiangsu.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 入场查验登记查询详情
 * Created by Administrator on 2017/4/13.
 */
public class RuChangDengjiDeilActivity extends MVPActivity {

    @BindView(R.id.bt_back)
    ImageView btBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_add)
    ImageView btAdd;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.et_tuzaichang_name)
    EditText etTuzaichangName;
    @BindView(R.id.et_tuzaichang_time)
    EditText etTuzaichangTime;
    @BindView(R.id.et_tuzaichang_query)
    EditText etTuzaichangQuery;
    @BindView(R.id.et_tuzaichang_huozhuname)
    EditText etTuzaichangHuozhuname;
    @BindView(R.id.et_tuzaichang_phone)
    EditText etTuzaichangPhone;
    @BindView(R.id.et_dongwu_zhonglei)
    EditText etDongwuZhonglei;
    @BindView(R.id.et_tuzaichang_number)
    EditText etTuzaichangNumber;
    @BindView(R.id.et_tuzaichang_danwei)
    EditText etTuzaichangDanwei;
    @BindView(R.id.et_tuzaichang_laiyuandi)
    EditText etTuzaichangLaiyuandi;
//    @BindView(R.id.et_laiyuandi_xiang)
//    EditText etLaiyuandiXiang;
    @BindView(R.id.et_jianyi_zhengming)
    EditText etJianyiZhengming;
    @BindView(R.id.et_peidai_erbiao)
    EditText etPeidaiErbiao;
    @BindView(R.id.et_tongdao_diaoru)
    EditText etTongdaoDiaoru;
    @BindView(R.id.et_linchuang_check)
    EditText etLinchuangCheck;
    @BindView(R.id.et_result_deal)
    EditText etResultDeal;
    @BindView(R.id.et_ruchang_number)
    EditText etRuchangNumber;
    @BindView(R.id.et_daizai_quanhao)
    EditText etDaizaiQuanhao;
    @BindView(R.id.et_chuli_number)
    EditText etChuliNumber;
    @BindView(R.id.et_buhege_chuli)
    EditText etBuhegeChuli;
    @BindView(R.id.et_buhege_jiance)
    EditText etBuhegeJiance;
    @BindView(R.id.et_jianyichuli_bianhao)
    EditText etJianyichuliBianhao;
    @BindView(R.id.et_jianyichuli_xiaodu)
    EditText etJianyichuliXiaodu;
    @BindView(R.id.et_chayan_renyuan)
    EditText etChayanRenyuan;
    @BindView(R.id.et_jilu_renyuan)
    EditText etJiluRenyuan;
    @BindView(R.id.et_chouyan_number)
    EditText etChouyanNumber;
    @BindView(R.id.et_laike_yinxing)
    EditText etLaikeYinxing;
    @BindView(R.id.et_laike_yangxing)
    EditText etLaikeYangxing;
    @BindView(R.id.et_kelun_yinxing)
    EditText etKelunYinxing;
    @BindView(R.id.et_kelun_yangxing)
    EditText etKelunYangxing;
    @BindView(R.id.et_shading_yinxing)
    EditText etShadingYinxing;
    @BindView(R.id.et_shading_yangxing)
    EditText etShadingYangxing;
    @BindView(R.id.et_chouyan_jianceren)
    EditText etChouyanJianceren;
    @BindView(R.id.et_chouyan_beizhu)
    EditText etChouyanBeizhu;
    @BindView(R.id.ll_ruchang_chouyan)
    LinearLayout llRuchangChouyan;
    private RuChangChaYanQueryBean.DataListBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ruchang_dengji_deil);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindData() {
        setTitle("入场查验登记查询详情");
        bean = (RuChangChaYanQueryBean.DataListBean) getIntent().getSerializableExtra(Constance.ACTIVITY_DATA);

        if (bean.geteAnimal().contains("猪") || bean.geteAnimal().contains("牛")
                || bean.geteAnimal().contains("羊")) {
            llRuchangChouyan.setVisibility(View.VISIBLE);
        } else {
            llRuchangChouyan.setVisibility(View.GONE);
        }

        etTuzaichangName.setText(bean.geteOwner());//屠宰场名称
        etTuzaichangTime.setText(bean.geteDate() + " " + bean.getXs() + "时");//时间
        etTuzaichangQuery.setText(bean.geteNo());//检疫证明号码
        etTuzaichangHuozhuname.setText(bean.geteCOwner());//货主(经纪人)
        etTuzaichangPhone.setText(bean.getePhone());//联系电话
        etDongwuZhonglei.setText(bean.geteAnimal());//动物种类
        etTuzaichangNumber.setText(bean.geteNum());//数量
        etTuzaichangDanwei.setText(bean.geteUnit());//单位
        etTuzaichangLaiyuandi.setText(bean.geteSeedbed() + bean.geteShi() + bean.geteXian() + bean.geteZhen());//来源地
//        etLaiyuandiXiang.setText();
        etJianyiZhengming.setText(bean.geteCertificate());//是否附有检疫证明
        etPeidaiErbiao.setText(bean.geteConform());//是否佩戴耳标
        etTongdaoDiaoru.setText(bean.geteIdentifica());//是否经指定通道调入
        etLinchuangCheck.setText(bean.geteHealthy());//临床检查情况
        etResultDeal.setText(bean.geteSum());//结果处理(总数)
        etRuchangNumber.setText(bean.getRCSL());//入场数量
        etDaizaiQuanhao.setText(bean.geteDZQ());//待宰圈号
        etChuliNumber.setText(bean.getCLSL());//处理数量
        etBuhegeChuli.setText(bean.getCLFS());//不合格处理
        etBuhegeJiance.setText(bean.geteLcjcjg());//不合格检测
        etJianyichuliBianhao.setText(bean.getCode());//检疫处理通知单编号
        etJianyichuliXiaodu.setText(bean.getXd());//消毒
        etChayanRenyuan.setText(bean.geteRummager());//查验人员
        etJiluRenyuan.setText(bean.getJLRY());//记录人员
        etChouyanNumber.setText(bean.getCJSL());//抽验数量
        etLaikeYinxing.setText(bean.getLYIN());// 莱克多巴胺-阴性
        etLaikeYangxing.setText(bean.getLYANG());// 莱克多巴胺-阳性
        etKelunYinxing.setText(bean.getKYIN());// 克伦特罗-阴性
        etKelunYangxing.setText(bean.getKYANG());// 克伦特罗-阳性
        etShadingYinxing.setText(bean.getSYIN());// 沙丁胺醇-阳性
        etShadingYangxing.setText(bean.getSYANG());// 沙丁胺醇-阳性
        etChouyanJianceren.setText(bean.getJCR());// 检测人
        etChouyanBeizhu.setText(bean.getBZ());// 备注


    }

    @Override
    public void bindListener() {

    }
}
