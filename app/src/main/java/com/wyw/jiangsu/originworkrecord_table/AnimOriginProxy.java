package com.wyw.jiangsu.originworkrecord_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.wyw.jiangsu.bean.AH_AnimalOrigin;

/**
 * Created by zhyzh on 2017/3/28.
 * 动物产地检疫工作记录单打印
 */

public class AnimOriginProxy extends BaseProxy {
    AH_AnimalOrigin.DataListBean bean;
    public AnimOriginProxy(Context mContext, Canvas mCacheCanvas, AH_AnimalOrigin.DataListBean bean, Bitmap cacheBitmap) {
        super(mContext, mCacheCanvas, cacheBitmap);
        this.mContext = mContext;
        this.mCacheCanvas = mCacheCanvas;
        this.bean = bean;
        this.cacheBitmap = cacheBitmap;
    }

    @Override
    public Bitmap draw() {
        //基本情况
        writeText(bean.getRecordNo(), 25.5f, 1.9f, 10.0f, 1.5f,20.0F);//记录单编号
        writeText(bean.getSupervisename(), 11.2f, 5.2f, 5.0f, 1.0f,20.0F);//监督所名称
        String[] inspectiontime=bean.getInspectiontime().replace("/","-").split("-");//报检时间
        writeText(inspectiontime[0],8.7f,6.55f, 4.7f, 0.7f,20.0F);//1
        writeText(inspectiontime[1],10.45f,6.55f, 4.7f, 0.7f,20.0F);
        writeText(inspectiontime[2],11.8f,6.55f, 4.7f, 0.7f,20.0F);
        writeText(bean.getDr(),13.1f,6.55f, 6.55f, 0.7f,20.0F);//小时
        String[] quarantinetime=bean.getQuarantinetime().replace("/","-").split("-");//检疫时间
        writeText(quarantinetime[0],23.25f,6.55f, 4.7f, 0.7f,20.0F);
        writeText(quarantinetime[1],24.90f,6.55f, 4.7f, 0.7f,20.0F);
        writeText(quarantinetime[2],26.35f,6.55f, 4.7f, 0.7f,20.0F);
        writeText(bean.getDr1(),27.80f,6.55f, 4.7f, 0.7f,20.0F);//小时
        writeText(bean.getShippername(),8.5f,8.25f, 10.0f, 1.5f,20.0F);// 货主姓名
        writeText(bean.getTeltphone(), 15.5f,8.25f, 10.0f, 1.5f,20.0F);// 联系电话
        writeText(bean.getIdcardnum(),23.5f, 8.25f, 10.0f, 1.5f,20.0F);//身份证号
        writeText(bean.getFarmsnme(),10.5f,10.5f, 10.0f, 1.5f,20.0F); // 养殖场、村、 原驻地或捕获地   养殖场名字
//        writeText(str,11f,6.5f, 4.7f, 0.7f);
        writeText(bean.getQuarantineaddress(),26.5f, 10.5f, 10.0f, 1.5f,20.0F);//检疫地点
        writeText(bean.getAnimalsort(),9.4f,12.5f, 10.0f, 1.5f,20.0F);//动物种类
        //用途
        if(bean.getMyuse().contains("饲养")){
            writeText("√",15.8f,12.5f, 5.0f, 0.7f,20.0F);
        }else  if(bean.getMyuse().contains("屠宰")){
            writeText("√",18.2f,12.5f, 5.0f, 0.7f,20.0F);
        }else  if(bean.getMyuse().contains("展览")){
            writeText("√",20.6f,12.5f, 5.0f, 0.7f,20.0F);
        }else  if(bean.getMyuse().contains("演出")){
            writeText("√",23.0f,12.5f, 5.0f, 0.7f,20.0F);
        }else  if(bean.getMyuse().contains("比赛")){
            writeText("√",25.4f,12.5f, 5.0f, 0.7f,20.0F);
        }else  if(bean.getMyuse().contains("其他")){
            writeText("√",27.8f,12.5f, 5.0f, 0.7f,20.0F);
        }
        //动物来源
        if(bean.getAnimalsources().contains("家畜家禽")){
            writeText("√",13.4f,14.5f, 5.0f, 0.7f,20.0F);//5
        }else if(bean.getAnimalsources().contains("人工饲养")){
            writeText("√",18.5f,14.5f, 5.0f, 0.7f,20.0F);
        }else if(bean.getAnimalsources().contains("合法捕获")){
            writeText("√",23.4f,14.5f, 5.0f, 0.7f,20.0F);
        }

        writeText(bean.getDomesticatedid(),8.5f,16.5f, 10.0f, 1.5f,20.0F);//繁殖许可证号
        writeText(bean.getCatchid(),23.5f,16.8f, 10.0f, 1.5f,20.0F);//捕捉许可证号
        writeText(bean.getAnimalnum()+"",9.4f,18.5f,10.0f, 1.5f,20.0F);//数量
        writeText(bean.getRqydzxx(),20.5f,18.4f, 10.0f, 1.5f,20.0F);  //起运地
        writeText(bean.getRdddzxxs(),9.5f,20.05f, 10.0f, 1.5f,20.0F);     //到达地点
        writeText(bean.getToolid(),27.2f,20.05f,10.0f, 1.5f,20.0F);//运载工具牌号
        //是否强制免疫
        if(bean.getQzmy().contains("是")){
            writeText("√",8.30f,22.6f, 5.0f, 0.7f,20.0F);//1
        }else if(bean.getQzmy().contains("否")){
            writeText("√",10.0f,22.6f, 5.0f, 0.7f,20.0F);
        }
        //养殖档案是否归档
        if(bean.getRecordrule().equals("符合")){
            writeText("√",15.0f,22.6f, 5.0f, 0.7f,20.0F);
        }else if(bean.getRecordrule().equals("不符合")){
            writeText("√",17.4f,22.6f, 5.0f, 0.7f,20.0F);
        }
        // 畜禽标识是否符合规定   动物防疫条件合格证
        if(bean.getLogorule().equals("符合")){
            writeText("√",26.6f,22.6f, 5.0f, 0.7f,20.0F);
        }else if(bean.getLogorule().equals("不符合")){
            writeText("√",29.0f,22.6f, 5.0f, 0.7f,20.0F);
        }
        //养殖场疫情
        if(bean.getOutbreak().contains("有")){
            writeText("√",8.30f,25.35f, 5.0f, 0.7f,20.0F);//2
        }else if(bean.getOutbreak().contains("无")){
            writeText("√",10.0f,25.35f, 5.0f, 0.7f,20.0F);
        }
        //是否疫区
        if(bean.getEridemicarea().contains("是")){
            writeText("√",15.70f,25.35f, 5.0f, 0.7f,20.0F);
        }else if(bean.getEridemicarea().contains("否")){
            writeText("√",17.4f,25.35f, 5.0f, 0.7f,20.0F);
        }
        writeText(bean.getOther(),28.0f,25.35f, 5.0f, 0.7f,20.0F);//其他
        //临床检查 合格不显示  不合格显示对象  一般处理 无害化处理   和数量
        if(bean.getClinical().equals("合格")){
            writeText("√",14.40f,27.6f, 5.0f, 0.7f,20.0F);//3
        }else if(bean.getClinical().equals("不合格")){
            writeText("√",18.8f,27.6f, 5.0f, 0.7f,20.0F);
        }
        //实验室检查
        if(bean.getLaboratory().equals("需要")){
            writeText("√",6.73f,29.75f, 5.0f, 0.7f,20.0F);//4
        }else if(bean.getLaboratory().equals("不需要")){
            writeText("√",9.4f,29.88f, 5.0f, 0.7f);
        }
        //检测情况
        if(bean.getSituation().equals("合格")){
            writeText("√",24.85f,29.75f, 5.0f, 0.7f,20.0F);
        }else if(bean.getSituation().equals("不合格")){
            writeText("√",27.4f,29.75f, 5.0f, 0.7f,20.0F);
        }
        //是否符合检疫规定  在这里做判断
        if(bean.getQuarantinerule().contains("是")){
            //检疫结果
            writeText("√",11.2f,31.80f, 5.0f, 0.7f,20.0F);//1
            writeText(bean.getCode(),23.0f,31.75f, 10.0f, 1.5f,20.0F);
        }else if(bean.getQuarantinerule().contains("否")){
            writeText("√",13.0f,31.80f, 5.0f, 0.7f,20.0F);
            writeText(bean.getJyclcard(),18.0f,39.3f, 5.0f, 0.7f,20.0F);//检疫处理通知单编号
            if(bean.getLegal().equals("1")){
                writeText("√",7.6f,33.85f, 5.0f, 0.7f,20.0F);//2
                writeText(bean.getLegalnum()+"",16.7f,33.75f, 10.0f, 1.5f,20.0F);
            }
            if(bean.getOtherObj().equals("1")){
                writeText("√",22.6f,33.85f, 5.0f, 0.7f,20.0F);
                writeText(bean.getLegalnum()+"",29.5f,33.85f,10.0f, 1.5f,20.0F);
            }
            if(bean.getGeneral().contains("实验室检测")){
                writeText("√",15.7f,35.7f, 5.0f, 0.7f,20.0F);//3
            }else  if(bean.getGeneral().contains("隔离")){
                writeText("√",20.0f,35.7f, 5.0f, 0.7f,20.0F);
            }else  if(bean.getGeneral().contains("治疗")){
                writeText("√",22.4f,35.7f, 5.0f, 0.7f,20.0F);
            }else  if(bean.getGeneral().contains("其他")){
                writeText("√",24.8f,35.7f, 5.0f, 0.7f,20.0F);
            }
            if(bean.getHarmless().contains("焚烧")){
                writeText("√",11.6f,37.5f, 5.0f, 0.7f,20.0F);//4
                writeText("√",23.8f,37.5f, 5.0f, 0.7f,20.0F);
            }else  if(bean.getHarmless().contains("化制")){
                writeText("√",15.0f,37.5f, 5.0f, 0.7f,20.0F);
            }else  if(bean.getHarmless().contains("掩埋")){
                writeText("√",18.0f,37.5f, 5.0f, 0.7f,20.0F);
            }else  if(bean.getHarmless().contains("发酵")){
                writeText("√",20.8f,37.5f, 5.0f, 0.7f,20.0F);
            }else  if(bean.getHarmless().contains("其他")){
                writeText("√",23.8f,37.5f, 5.0f, 0.7f,20.0F);
            }
            writeText(bean.getHarmlesnum()+"",29.5f,37.5f, 5.0f, 0.7f,20.0F); //无害化处理数量
        }

        writeText(bean.getVetname(),5.5f,42.55f, 5.0f, 0.7f,20.0F);//检疫人员签字
        writeText(bean.getRhzqz(),21.5f,42.55f, 5.0f, 0.7f,20.0F);//货主签字
        String[] Gftime=bean.getGftime().replace("/","-").split("-");// 检疫人员签字时间Gftime  dr2
        String[] rhzdate=bean.getRhzdate().replace("/","-").split("-");//货主签字时间rhzdate dr3
        writeText(Gftime[0],9.8f,44.4f, 4.7f, 0.7f,20.0F);//1
        writeText(Gftime[1],12.1f,44.4f, 4.7f, 0.7f,20.0F);
        writeText(Gftime[2],13.8f,44.4f, 4.7f, 0.7f,20.0F);
        writeText(rhzdate[0],25.0f,44.4f, 4.7f, 0.7f,20.0F);
        writeText(rhzdate[1],27.45f,44.4f, 4.7f, 0.7f,20.0F);
        writeText(rhzdate[2],29.10f,44.4f, 4.7f, 0.7f,20.0F);
        return cacheBitmap;
    }
}
