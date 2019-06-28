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

public class ApplyDetil extends BaseProxy {
    private AnimProcessListBean.DataListBean dataListBean;
    public ApplyDetil(Context mContext, Canvas mCacheCanvas, AnimProcessListBean.DataListBean dataListBean, Bitmap cacheBitmap) {
        super(mContext, mCacheCanvas, cacheBitmap);
        this.mContext = mContext;
        this.mCacheCanvas = mCacheCanvas;
        this.dataListBean = dataListBean;
        this.cacheBitmap = cacheBitmap;
    }

    @Override
    public Bitmap draw(int whichPrinter) {
        writeText(dataListBean.getQDWNumber(),26.25F,1.4F,6.5F, 2.2F,15.0F);//申报单编号
        writeText(dataListBean.getFScTime(),20.5F,6.2F,10.0F, 5.0F,20.0F);//报检时间
        writeText(dataListBean.getQDWCargoOwner(),14.3F,7.8F,10.0F, 5.0F,20.0F);//申报人姓名
        writeText(dataListBean.getQDWPhone(),26.5F,7.8F,5.0F, 2.5F,20.0F);//申报人电话
        writeText(dataListBean.getQDWShengQy() + dataListBean.getQDWShiQy()+dataListBean.getQDWXiangQy(),20.0F,9.5F,10.0F, 5.0F,20.0F);//养殖场地址
        writeText(dataListBean.getQDWXuZhongZ(),21.5F,11.1F,10.0F, 5.0F,20.0F);//供体动物种类
        if (whichPrinter==1&&dataListBean.getQDWErBiaoHao()!=null&&dataListBean.getQDWErBiaoHao().getBytes().length>146){
            writeText("耳标号详情请见背面", 19.0F,13.1F,10.0F, 5.0F,20.0F);
        }else {
            writeText(dataListBean.getQDWErBiaoHao(),19.0F,13.1F,10.0F, 5.0F,20.0F);//耳标号
        }
        writeText(dataListBean.getFqProduct(),15.6F,14.7F,10.0F, 5.5F,20.0F);//动物产品种类
        writeText(dataListBean.getQDWQuantity()+"",28.0F,14.7F,10.0F, 5.5F,20.0F);//动物数量
        writeText(dataListBean.getFqZxqscjyxkz(),15.0F,17.0F,10.0F, 5.5F,20.0F);//有效《种畜禽生产经营许可证》
        writeText(dataListBean.getYx(),28.0F,17.0F,5.0F, 5.5F,20.0F);//有效《跨省引进乳用种用动物检疫审批表》
        writeText(dataListBean.getQDWShengDd() + dataListBean.getQDWShiDd()+dataListBean.getQDWXianDd()+dataListBean.getQDWXiangDd() + dataListBean.getQDWCunDd(),17.0F,19.4F,25.0F, 5.5F,20.0F);//到达地点
        writeText(dataListBean.getDateQy(),19.8F,21.0F,10.0F, 5.5F,20.0F);//启运时间
        writeText(dataListBean.getGZ(),12.5F,26.0F,10.0F, 5.5F,20.0F);//申报人签字
        writeText(dataListBean.getQDWAccepted(),7.2F,27.5F,10.0F, 5.5F,20.0F);//申报处理结果
        if (dataListBean.getQDWAccepted().contains("受理")){
            String[] nian = dataListBean.getCLRQ().trim().split(" ");//拟派时间
            String[] split = nian[0].split("-|/");
            String YearLi = split[0];
            String MonthLi = split[1];
            String DateLi = split[2];
            writeText("拟派员于 "+YearLi+" 年 "+MonthLi+" 月 "+DateLi+" 日到 "+dataListBean.getQDWAddress()+" 实施检疫。",13.5F,27.5F,15.0F, 5.5F,20.0F);
        }else if (dataListBean.getQDWAccepted().contains("不受理")){
            writeText("理由："+dataListBean.getQDWLiYou(),14.5F,27.5F,15.0F, 5.5F,20.0F);//不受理理由
        }

        writeText(dataListBean.getQDWAttn(),8.5F,31.6F,10.0F, 5.5F,20.0F);//经办人
        String[] jianyiTiem = dataListBean.getDateNpy().split("-|/");//检疫时间
        writeText(jianyiTiem[0]+" 年 "+jianyiTiem[1]+" 月 "+jianyiTiem[2]+" 日",11.00F,31.6F,5.0F, 2.5F,20.0F);//nian
//        writeText(jianyiTiem[1],8.42F,19.6F,5.0F, 2.5F);//yue
//        writeText(jianyiTiem[2],9.22F,19.6F,5.0F, 2.5F);//ri

        writeText(dataListBean.getQDWAccepted()+"。",5.0F,37.3F,10.0F, 5.5F,20.0F);//处理意见
        if (dataListBean.getQDWAccepted().contains("受理")){
            writeText("本所拟于 "+jianyiTiem[0]+" 年 "+jianyiTiem[1]+" 月 "+jianyiTiem[2]+" 日派员到 "+dataListBean.getQDWAddress()+" 实施检疫。请准备好相关防疫资料，以方便检疫人员核查。",8.0F,37.3F,15.0F, 5.5F,20.0F);
        }else if (dataListBean.getQDWAccepted().contains("不受理")){
            writeText("。 理由："+dataListBean.getQDWLiYou(),8.0F,37.3F,15.0F, 2.5F,20.0F);
        }
        writeText(dataListBean.getQDWAttn(),7.8F,42.80F,5.0F, 5.5F,20.0F);//经办人
        writeText(dataListBean.getQDWJBRDianHua(),13.4F,42.80F,10.0F, 5.5F,20.0F);//经办人联系电话

        writeText(jianyiTiem[0],25.5F,44.29F,10.0F, 5.5F,20.0F);//nian
        writeText(jianyiTiem[1],27.5F,44.29F,10.0F, 5.5F,20.0F);//yue
        writeText(jianyiTiem[2],28.55F,44.29F,10.0F, 5.5F,20.0F);//ri
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
