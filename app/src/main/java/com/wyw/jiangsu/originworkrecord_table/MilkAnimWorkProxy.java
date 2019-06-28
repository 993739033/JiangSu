package com.wyw.jiangsu.originworkrecord_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;

/**
 * Created by zhyzh on 2017/3/28.
 */

public class MilkAnimWorkProxy extends BaseProxy {
    AnimRuZhongYongRecordBean.DataListBean bean;

    public MilkAnimWorkProxy(Context mContext, Canvas mCacheCanvas, AnimRuZhongYongRecordBean.DataListBean bean, Bitmap cacheBitmap) {
        super(mContext, mCacheCanvas, cacheBitmap);
        this.mContext = mContext;
        this.mCacheCanvas = mCacheCanvas;
        this.bean = bean;
        this.cacheBitmap = cacheBitmap;
    }

    @Override
    public Bitmap draw() {
        //基本情况
        writeText(bean.getRecordNo(), 27.7f,2.4f, 6.0f, 1.0f);//记录单编号
        writeText(bean.getSupervisename(), 11.8f, 5.4f, 5.0f, 1.0f);//监督所名称
        String[] inspectiontime=bean.getInspectiontime().replace("/","-").split("-");//报检时间
        writeText(inspectiontime[0],9.2f,6.60f, 4.7f, 0.7f);//1
        writeText(inspectiontime[1],11.2f,6.60f, 4.7f, 0.7f);
        writeText(inspectiontime[2],13.1f,6.60f, 4.7f, 0.7f);
//        writeText(bean.getDr(),13.1f,6.55f, 6.55f, 0.7f,20.0F);//小时
        String[] quarantinetime=bean.getQuarantinetime().replace("/","-").split("-");//检疫时间
        writeText(quarantinetime[0],23.50f,6.60f, 4.7f, 0.7f);
        writeText(quarantinetime[1],26.15f,6.60f, 4.7f, 0.7f);
        writeText(quarantinetime[2],27.65f,6.60f, 4.7f, 0.7f);
//        writeText(bean.getDr1(),27.80f,6.55f, 4.7f, 0.7f,20.0F);//小时
        writeText(bean.getShippername(),8.7f,8.10f, 10.0f, 1.5f);// 货主姓名
        writeText(bean.getTeltphone(), 17.0f,8.10f, 10.0f, 1.5f);// 联系电话
        writeText(bean.getIdcardnum(),25.0f, 8.10f, 10.0f, 1.5f);//身份证号

        writeText(bean.getFarmsnme(),12.5f,9.7f, 10.0f, 1.5f); // 养殖场、村、 原驻地或捕获地   养殖场名字
        writeText(bean.getAnimalsort(),28.2f,9.7f, 10.0f, 1.5f);//动物种类
        writeText(bean.getAnimalnum()+"",11.4f,11.6f,10.0f, 1.5f);//数量
        writeText(bean.getQuarantineaddress(),23.f, 11.5f, 10.0f, 1.5f);//检疫地点
        writeText(bean.getRdddzxxs(),8.5f,13.2f, 10.0f, 1.5f);     //到达地点
        writeText(bean.getToolid(),24.5f,13.3f,10.0f, 1.5f);//运载工具牌号
        //有效《种畜禽生产经营许可证》
        if (bean.getZxqsc().contains("无")) {
            writeText("√", 7.8f, 15.80f, 5.0f, 0.7f);//1
        } else if (bean.getZxqsc().contains("有")) {
            writeText("√", 9.0f, 15.80f, 5.0f, 0.7f);
        }
        writeText(bean.getFzdw(), 15f, 15.65f, 5.0f, 0.7f);//发证单位
        writeText(bean.getBhid(), 24.5f, 15.65f, 5.0f, 0.7f);//发证单位号编号
        writeText(bean.getSpdw(), 13.0f, 18.80f, 5.0f, 0.7f);//审批单位
        writeText(bean.getSpbhb(), 25.0f, 18.80f, 5.0f, 0.7f);//审批单位
        //《动物防疫条件合格证》
        if (bean.getDwtjhgz().contains("有")) {
            writeText("√", 14.65f, 21.7f, 5.0f, 0.7f);//3
        } else if (bean.getDwtjhgz().contains("无")) {
            writeText("√", 16.55f, 21.7f, 5.0f, 0.7f);
        }
        //是否经强制免疫并在有效期内
        if (bean.getQzmy().contains("是")) {
            writeText("√", 26.1f, 21.7f, 5.0f, 0.7f);
        } else if (bean.getQzmy().contains("否")) {
            writeText("√", 28.0f, 21.7f, 5.0f, 0.7f);
        }
        //养殖场疫情(6个月内)
        if (bean.getOutbreak().equals("有")) {
            writeText("√", 10.60f, 23.65f, 5.0f, 0.7f);//4
        } else if (bean.getOutbreak().equals("无")) {
            writeText("√", 12.50f, 23.65f, 5.0f, 0.7f);
        }
        //养殖档案是否归档
        if (bean.getRecordrule().equals("符合")) {
            writeText("√", 16.85f, 23.65f, 5.0f, 0.7f);
        } else if (bean.getRecordrule().equals("不符合")) {
            writeText("√", 18.85f, 23.65f, 5.0f, 0.7f);
        }
        //耳标佩戴是否符合规定
        if (bean.getLogorule().contains("是")) {
            writeText("√", 27.25f, 23.7f, 5.0f, 0.7f);
        } else if (bean.getLogorule().contains("否")) {
            writeText("√", 29.42f, 23.7f, 5.0f, 0.7f);
        }
        //是否是疫区
        if (bean.getEridemicarea().contains("是")) {
            writeText("√", 11.70f, 25.9f, 5.0f, 0.7f);//5
        } else if (bean.getEridemicarea().contains("否")) {
            writeText("√", 13.60f, 25.9f, 5.0f, 0.7f);
        }
        writeText(bean.getOther(), 20.0f, 25.9f, 5.0f, 0.7f);//其他项目检查
        //临床检查
        if (bean.getClinical().equals("合格")) {
            writeText("√", 14.40f, 27.8f, 5.0f, 0.7f);//6
        } else if (bean.getClinical().equals("不合格")) {
            writeText("√", 18.40f, 27.8f, 5.0f, 0.7f);
        }
        //法定检疫对象实验室检测情况
        if (bean.getSituation().equals("合格")) {
            writeText("√", 20.2f, 29.8f, 5.0f, 0.7f);//7
        } else if (bean.getSituation().equals("不合格")) {
            writeText("√", 23.5f, 29.8f, 5.0f, 0.7f);
        }
        //是否符合检疫规定
        if (bean.getQuarantinerule().contains("是")) {
            //检疫结果
            writeText("√",11.85f,31.80f, 5.0f, 0.7f);//1
            writeText(bean.getCode(),23.0f,31.80f, 10.0f, 1.5f);
        } else if (bean.getQuarantinerule().contains("否")) {
            writeText("√",13.4f,31.80f, 5.0f, 0.7f);
            writeText(bean.getJyclcard(),18.0f,39.3f, 5.0f, 0.7f);//检疫处理通知单编号
            if(bean.getLegal().equals("1")){
                writeText("√",8.42f,33.65f, 5.0f, 0.7f);//2
                writeText(bean.getLegalnum()+"",18.0f,33.65f, 10.0f, 1.5f);
            }
            if(bean.getOtherObj().equals("1")){
                writeText("√",22.27f,33.65f, 5.0f, 0.7f);
                writeText(bean.getOtherNum()+"",28.5f,33.65f,10.0f, 1.5f);
            }
            if(bean.getGeneral().contains("实验室检测")){
                writeText("√",14.9f,35.4f, 5.0f, 0.7f);//3
            }else  if(bean.getGeneral().contains("隔离")){
                writeText("√",18.7f,35.4f, 5.0f, 0.7f);
            }else  if(bean.getGeneral().contains("治疗")){
                writeText("√",20.9f,35.4f, 5.0f, 0.7f);
            }else  if(bean.getGeneral().contains("其他")){
                writeText("√",23.1f,35.4f, 5.0f, 0.7f);
            }
            if(bean.getHarmless().contains("焚烧")){
                writeText("√",11.2f,37.10f, 5.0f, 0.7f);//4
            }else  if(bean.getHarmless().contains("化制")){
                writeText("√",14.0f,37.15f, 5.0f, 0.7f);
            }else  if(bean.getHarmless().contains("掩埋")){
                writeText("√",16.7f,37.15f, 5.0f, 0.7f);
            }else  if(bean.getHarmless().contains("发酵")){
                writeText("√",19.4f,37.15f, 5.0f, 0.7f);
            }else  if(bean.getHarmless().contains("其他")){
                writeText("√",22.22f,37.15f, 5.0f, 0.7f);
            }
            writeText(bean.getHarmlesnum()+"",28.5f,37.15f, 5.0f, 0.7f); //无害化处理数量
        }
        //签字
        writeText(bean.getVetname(),6.5f,42.0f, 5.0f, 0.7f);//检疫人员签字
        writeText(bean.getRhzqz(),21.5f,42.0f, 5.0f, 0.7f);//货主签字
        String[] Gftime=bean.getGftime().replace("/","-").split("-");// 检疫人员签字时间Gftime  dr2
        String[] rhzdate=bean.getRhzdate().replace("/","-").split("-");//货主签字时间rhzdate dr3
        writeText(Gftime[0],12.2f,43.3f, 4.7f, 0.7f);//1
        writeText(Gftime[1],14.4f,43.3f, 4.7f, 0.7f);
        writeText(Gftime[2],15.8f,43.3f, 4.7f, 0.7f);
        writeText(rhzdate[0],25.3f,43.3f, 4.7f, 0.7f);
        writeText(rhzdate[1],27.45f,43.3f, 4.7f, 0.7f);
        writeText(rhzdate[2],28.9f,43.3f, 4.7f, 0.7f);
        return cacheBitmap;
    }
}
