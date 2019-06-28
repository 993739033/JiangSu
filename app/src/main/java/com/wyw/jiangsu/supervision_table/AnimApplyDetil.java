package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.Toast;

import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public
class AnimApplyDetil extends BaseProxy {
    private AnimProcessListBean.DataListBean dataListBean;
    public AnimApplyDetil(Context mContext, Canvas mCacheCanvas, AnimProcessListBean.DataListBean dataListBean, Bitmap cacheBitmap) {
        super(mContext, mCacheCanvas, cacheBitmap);
        this.dataListBean = dataListBean;
        this.mContext = mContext;
        this.cacheBitmap = cacheBitmap;
        this.mCacheCanvas = mCacheCanvas;
    }

    @Override
    public Bitmap draw(int whichPrinter) {
       writeText(dataListBean.getQDWNumber(),26.05F,1.8F,6.5F, 2.2F,15.0F);//申报单编号
       writeText(dataListBean.getFScTime(),21.0F,6.7F,10.0F, 5.0F,20.0F);//报检时间
       writeText(dataListBean.getQDWCargoOwner(),15.5F,8.3F,10.0F, 5.0F,20.0F);//申报人姓名
       writeText(dataListBean.getQDWPhone(),26.5F,8.3F,5.0F, 2.5F,20.0F);//申报人电话
       writeText(dataListBean.getQDWShengQy() + dataListBean.getQDWShiQy()+dataListBean.getQDWXiangQy(),20.0F,9.9F,10.0F, 5.0F,20.0F);//养殖场地址
       writeText(dataListBean.getQDWXuZhongZ(),16.6F,11.5F,10.0F, 5.0F,20.0F);//动物种类
       writeText(dataListBean.getQDWQuantity()+"",28.0F,11.5F,10.0F, 5.5F,20.0F);//动物数量
       writeText(dataListBean.getQDWLaiYuan(),21.0F,13.1F,5.0F, 2.5F,20.0F);//来源
       writeText(dataListBean.getQDWYongTu(),21.5F,14.7F,10.0F, 5.5F,20.0F);//用途
        if (whichPrinter==1&&dataListBean.getQDWErBiaoHao()!=null&&dataListBean.getQDWErBiaoHao().getBytes().length>146){
            writeText("耳标号详情请见背面", 19.0F,16.3F,10.0F, 5.5F,20.0F);
        }else {
            writeText(dataListBean.getQDWErBiaoHao(),19.0F,16.3F,10.0F, 5.5F,20.0F);//耳标号
        }
        if (dataListBean.getQDWLaiYuan().contains("人工饲养")) {//来源
            writeText(dataListBean.getXKZH(),14.0F,18.0F,10.0F, 5.5F,20.0F);//野生动物驯养繁殖许可证号
        } else if (dataListBean.getQDWLaiYuan().contains("合法捕获")) {
            writeText(dataListBean.getXKZH(),26.5F,18.0F,5.0F, 5.5F,20.0F);//野生动物捕捉(猎捕)许可证号
        }
       writeText(dataListBean.getYx(),21.5F,19.8F,10.0F, 5.5F,20.0F);//有效《跨省引进乳用种用动物检疫审批表》
       writeText(dataListBean.getQDWShengDd() + dataListBean.getQDWShiDd()+dataListBean.getQDWXianDd()+dataListBean.getQDWXiangDd() + dataListBean.getQDWCunDd(),17.5F,21.3F,25.0F, 5.5F,20.0F);//
       writeText(dataListBean.getDateQy(),21.0F,23.0F,10.0F, 5.5F,20.0F);//启运时间
       writeText(dataListBean.getGZ(),12.5F,27.6F,10.0F, 5.5F,20.0F);//申报人签字

        writeText(dataListBean.getQDWAccepted(),8.1F,29.1F,10.0F, 5.5F,20.0F);//申报处理结果
        if (dataListBean.getQDWAccepted().contains("受理")){
            String[] nian = dataListBean.getCLRQ().trim().split(" ");//拟派时间
            String[] split = nian[0].split("-|/");
            String YearLi = split[0];
            String MonthLi = split[1];
            String DateLi = split[2];
            writeText("拟派员于 "+YearLi+" 年 "+MonthLi+" 月 "+DateLi+" 日到 "+dataListBean.getQDWAddress()+" 实施检疫。",14.5F,29.1F,15.0F, 5.5F,20.0F);
        }else if (dataListBean.getQDWAccepted().contains("不受理")){
            writeText("理由："+dataListBean.getQDWLiYou(),14.5F,21F,10.0F, 5.5F,20.0F);//不受理理由
        }
       writeText(dataListBean.getQDWAttn(),9.5F,32.6F,10.0F, 5.5F,20.0F);//经办人
        String[] jianyiTiem = dataListBean.getDateNpy().split("-|/");//检疫时间
       writeText(jianyiTiem[0],11.60F,32.6F,5.0F, 2.5F,20.0F);//nian
       writeText(jianyiTiem[1],13.42F,32.6F,5.0F, 2.5F,20.0F);//yue
       writeText(jianyiTiem[2],14.64F,32.6F,5.0F, 2.5F,20.0F);//ri

        writeText(dataListBean.getQDWAccepted()+"。",5.0F,38.3F,10.0F, 5.5F,20.0F);//处理意见
        if (dataListBean.getQDWAccepted().contains("受理")){
            writeText("本所拟于 "+jianyiTiem[0]+" 年 "+jianyiTiem[1]+" 月 "+jianyiTiem[2]+" 日派员到 "+dataListBean.getQDWAddress()+" 实施检疫。请准备好相关防疫资料，以方便检疫人员核查。",8.3F,38.3F,15.0F, 5.5F,20.0F);
        }else if (dataListBean.getQDWAccepted().contains("不受理")){
            writeText("。 理由："+dataListBean.getQDWLiYou(),5.3F,38.3F,15.0F, 2.5F,20.0F);
        }
       writeText(dataListBean.getQDWAttn(),7.4F,42.6F,5.0F, 5.5F,20.0F);//经办人
       writeText(dataListBean.getQDWJBRDianHua(),13.2F,42.6F,10.0F, 5.5F,20.0F);//经办人联系电话
        if (dataListBean!=null){
            //Toast.makeText(mContext,"打印成功",Toast.LENGTH_LONG).show();
        }

        return cacheBitmap;
    }

    @Override
    public List<String> draws() {
        return null;
    }
}
