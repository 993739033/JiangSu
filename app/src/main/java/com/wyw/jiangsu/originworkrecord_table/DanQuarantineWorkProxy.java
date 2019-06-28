package com.wyw.jiangsu.originworkrecord_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;

/**
 * Created by zhyzh on 2017/3/28.
 */

public class DanQuarantineWorkProxy extends BaseProxy {
    AnimZhongDanRedordBean.DataListBean bean;
    public DanQuarantineWorkProxy(Context mContext, Canvas mCacheCanvas, AnimZhongDanRedordBean.DataListBean bean, Bitmap cacheBitmap) {
        super(mContext, mCacheCanvas, cacheBitmap);
        this.mContext = mContext;
        this.mCacheCanvas = mCacheCanvas;
        this.bean = bean;
        this.cacheBitmap = cacheBitmap;
    }

    @Override
    public Bitmap draw() {

        //基本情况
        writeText(bean.getRecordNo(), 25.5f,2.7f, 8.0f, 1.0f);//记录单编号
        writeText(bean.getSupervisename(), 11.5f, 6.2f, 5.0f, 1.0f);//监督所名称
        String[] inspectiontime=bean.getInspectiontime().replace("/","-").split("-");//报检时间
        writeText(inspectiontime[0],10.9f,7.60f, 4.7f, 0.7f);//1
        writeText(inspectiontime[1],13.1f,7.60f, 4.7f, 0.7f);
        writeText(inspectiontime[2],15.1f,7.60f, 4.7f, 0.7f);
//        writeText(bean.getDr(),13.1f,6.55f, 6.55f, 0.7f,20.0F);//小时
        String[] quarantinetime=bean.getQuarantinetime().replace("/","-").split("-");//检疫时间
        writeText(quarantinetime[0],24.8f,7.60f, 4.7f, 0.7f);
        writeText(quarantinetime[1],26.7f,7.60f, 4.7f, 0.7f);
        writeText(quarantinetime[2],28.5f,7.60f, 4.7f, 0.7f);
//        writeText(bean.getDr1(),27.80f,6.55f, 4.7f, 0.7f,20.0F);//小时
        writeText(bean.getShippername(),9.7f,9.10f, 10.0f, 1.5f);// 货主姓名
        writeText(bean.getTeltphone(), 18.5f,9.10f, 10.0f, 1.5f);// 联系电话
        writeText(bean.getIdcardnum(),26.0f, 9.10f, 10.0f, 1.5f);//身份证号

        writeText(bean.getFarmsnme(),11.1f,10.7f, 10.0f, 1.5f); // 养殖场、村、 原驻地或捕获地   养殖场名字
        writeText(bean.getQuarantineaddress(),25.0f,10.7f, 4.7f, 0.7f);//检疫地点
        writeText(bean.getAnimalsort(),12.1f,12.3f, 4.7f, 0.7f);//动物供体种类
        writeText(bean.getOnimalsortName(),25.0f,12.3f, 5.0f, 0.7f);//耳标号
        writeText(bean.getOnimalsort(),12.1f,13.9f, 5.0f, 0.7f);//动物产品种类
        writeText(bean.getAnimalnum(),27.0f,13.9f, 5.0f, 0.7f);//数量
        writeText(bean.getRdddzxxs(),10.1f,15.5f, 5.0f, 0.7f);//目的地详情
        writeText(bean.getToolid(),25.0f,15.6f, 5.0f, 0.7f);//运载工具号
        //种畜禽生产经营许可证
        if(bean.getZxqsc().contains("无")){
            writeText("√",8.5f,17.6f, 5.0f, 0.7f);
        }else if(bean.getZxqsc().contains("有")){
            writeText("√",9.9f,17.6f, 5.0f, 0.7f);//1
        }

        writeText(bean.getFzdw(),16.0f,17.6f, 5.0f, 0.7f);//发证单位
        writeText(bean.getBhid(),25.0f,17.7f, 5.0f, 0.7f);//发证单位号编号
        writeText(bean.getSpdw(),13.0f,19.7f, 5.0f, 0.7f);//审批单位
        writeText(bean.getSpbhb(),25.0f,19.8f, 5.0f, 0.7f);//审批单位编号
        //动物防疫条件合格证
        if(bean.getDwtjhgz().contains("有")){
            writeText("√",16.08f,21.7f, 5.0f, 0.7f);//3
        }else if(bean.getDwtjhgz().contains("无")){
            writeText("√",17.58f,21.7f, 5.0f, 0.7f);
        }
        //是否经强制免疫并在有效期内
        if(bean.getQzmy().contains("是")){
            writeText("√",27.5f,21.7f, 5.0f, 0.7f);
        }else if(bean.getQzmy().contains("否")){
            writeText("√",29.0f,21.7f, 5.0f, 0.7f);
        }
        //养殖场疫情(6个月内)
        if(bean.getOutbreak().contains("有")){
            writeText("√",11.6f,23.6f, 5.0f, 0.7f);//4
        }else if(bean.getOutbreak().contains("无")){
            writeText("√",13.05f,23.6f, 5.0f, 0.7f);
        }
        //养殖档案是否归档
        if(bean.getRecordrule().equals("符合")){
            writeText("√",18.55f,23.6f, 5.0f, 0.7f);
        }else if(bean.getRecordrule().equals("不符合")){
            writeText("√",20.75f,23.6f, 5.0f, 0.7f);
        }
        //禽畜标示
        if(bean.getLogorule().equals("符合")){
            writeText("√",26.5f,23.6f, 5.0f, 0.7f);
        }else if(bean.getLogorule().equals("不符合")){
            writeText("√",28.7f,23.6f, 5.0f, 0.7f);
        }
        //是否是疫区
        if(bean.getEridemicarea().contains("是")){
            writeText("√",11.3f,25.5f, 5.0f, 0.7f);//5
        }else if(bean.getEridemicarea().contains("否")){
            writeText("√",13.35f,25.5f, 5.0f, 0.7f);
        }

        writeText(bean.getOther(),21.0f,25.5f, 5.0f, 0.7f);//其他项目检查
        //临床检查
        if(bean.getClinical().equals("合格")){
            writeText("√",15.9f,27.4f, 5.0f, 0.7f);//6
        }else  if(bean.getClinical().equals("不合格")){
            writeText("√",19.7f,27.4f, 5.0f, 0.7f);
        }
        //法定检疫对象实验室检测情况
        if(bean.getSituation().equals("合格")){
            writeText("√",21.4f,29.5f, 5.0f, 0.7f);//7
        }else  if(bean.getSituation().equals("不合格")){
            writeText("√",24.05f,29.5f, 5.0f, 0.7f);
        }
        //是否符合检疫规定
        if (bean.getQuarantinerule().contains("是")) {
            //检疫结果
            writeText("√",12.95f,31.45f, 5.0f, 0.7f);//1
            writeText(bean.getCode(),25.0f,31.45f, 10.0f, 1.5f);
        } else if (bean.getQuarantinerule().contains("否")) {
            writeText("√",14.75f,31.45f, 5.0f, 0.7f);
            writeText(bean.getJyclcard(),18.0f,38.0f, 5.0f, 0.7f);//检疫处理通知单编号
            if(bean.getLegal().equals("1")){
                writeText("√",9.15f,32.80f, 5.0f, 0.7f);//2
                writeText(bean.getLegalnum()+"",17.0f,32.80f, 10.0f, 1.5f);
            }
            if(bean.getOtherObj().equals("1")){
                writeText("√",20.75f,32.80f, 5.0f, 0.7f);
                writeText(bean.getOtherNum()+"",26.5f,32.80f,10.0f, 1.5f);
            }
            if(bean.getGeneral().contains("实验室检测")){
                writeText("√",16.3f,34.65f, 5.0f, 0.7f);//3
            }else  if(bean.getGeneral().contains("隔离")){
                writeText("√",20.65f,34.65f, 5.0f, 0.7f);
            }else  if(bean.getGeneral().contains("治疗")){
                writeText("√",22.95f,34.65f, 5.0f, 0.7f);
            }else  if(bean.getGeneral().contains("其他")){
                writeText("√",25.3f,34.65f, 5.0f, 0.7f);
            }
            if(bean.getHarmless().contains("焚烧")){
                writeText("√",12.2f,36.20f, 5.0f, 0.7f);//4
            }else  if(bean.getHarmless().contains("化制")){
                writeText("√",15.1f,36.20f, 5.0f, 0.7f);
            }else  if(bean.getHarmless().contains("掩埋")){
                writeText("√",18.0f,36.20f, 5.0f, 0.7f);
            }else  if(bean.getHarmless().contains("发酵")){
                writeText("√",20.7f,36.20f, 5.0f, 0.7f);
            }else  if(bean.getHarmless().contains("其他")){
                writeText("√",23.5f,36.20f, 5.0f, 0.7f);
            }
            writeText(bean.getHarmlesnum()+"",29.5f,36.15f, 5.0f, 0.7f); //无害化处理数量
        }
        //签字
        writeText(bean.getVetname(),6.5f,40.5f, 5.0f, 0.7f);//检疫人员签字
        writeText(bean.getRhzqz(),21.5f,40.5f, 5.0f, 0.7f);//货主签字
        String[] Gftime=bean.getGftime().replace("/","-").split("-");// 检疫人员签字时间Gftime  dr2
        String[] rhzdate=bean.getRhzdate().replace("/","-").split("-");//货主签字时间rhzdate dr3
        writeText(Gftime[0],14.1f,42.55f, 5f, 0.7f);//1
        writeText(Gftime[1],16.0f,42.55f, 5f, 0.7f);
        writeText(Gftime[2],17.8f,42.55f, 5f, 0.7f);
        writeText(rhzdate[0],25.6f,42.55f, 5f, 0.7f);
        writeText(rhzdate[1],27.75f,42.55f, 5f, 0.7f);
        writeText(rhzdate[2],29.50f,42.55f, 5f, 0.7f);
        return cacheBitmap;
    }
}
