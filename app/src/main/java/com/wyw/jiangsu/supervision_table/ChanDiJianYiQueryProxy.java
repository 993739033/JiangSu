package com.wyw.jiangsu.supervision_table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.wyw.jiangsu.activity.model.PhotoViewModel;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.Qua_QuarantineDeclarationCDQuery;
import com.wyw.jiangsu.bean.local.CheckContentEntity;
import com.wyw.jiangsu.bean.upload.UploadCommonSupervisionBean;
import com.wyw.jiangsu.utils.BitmapUtils;
import com.wyw.jiangsu.utils.DrawUtil;
import com.wyw.jiangsu.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj004 on 2017/8/18.
 */

public class ChanDiJianYiQueryProxy extends BaseProxy {

    private final Qua_QuarantineDeclarationCDQuery bean;
    private List<String> paths = new ArrayList<>();

    public ChanDiJianYiQueryProxy(Context context, Canvas cachecanvas, Qua_QuarantineDeclarationCDQuery t, Bitmap cachebitmap) {
        super(context, cachecanvas, cachebitmap);
        this.mContext = context;
        this.mCacheCanvas = cachecanvas;
        this.bean = t;
        this.cacheBitmap = cachebitmap;
    }

    @Override
    public Bitmap draw(int whichPrinter) {
        return null;
    }

    private void merge(Bitmap frontBitmap) {
        cacheBitmap = BitmapUtils.mergeBitmap(cacheBitmap, frontBitmap);
        if (cacheBitmap != null) mCacheCanvas = new Canvas(cacheBitmap);
    }


    @Override
    public List<String> draws() {
        if (bean.getFqSbType().contains("动物")) {
            paths.add(draw_1());
        } else if (bean.getFqSbType().contains("繁殖材料")) {
            paths.add(draw_2());
        }

        if (!cacheBitmap.isRecycled()) {
            cacheBitmap.recycle();
            System.gc();
        }
        return FileUtil.getInstance().getPrintList("cdjy");
    }

    private String draw_1() {
        mCacheCanvas.drawColor(Color.WHITE);
        /*File file=new File(FileUtil.getInstance().getDirPrint(),"cdjy.jpg");
        Bitmap b4 = BitmapFactory.decodeFile(file.getAbsolutePath());
        merge(b4);
        if (!b4.isRecycled())b4.recycle();*/
        //报检时间FScTime
        if (!TextUtils.isEmpty(bean.getDateQF())) {
            String DateQF = bean.getDateQF();
            String[] split = DateQF.split("-");
            writeText(split[0], 20.6f, 5.8f, 6f, 1.1f);
            writeText(split[1], 23.0f, 5.8f, 6f, 1.1f);
            writeText(split[2], 24.7f, 5.8f, 6f, 1.1f);
        }
        //申报单编号
        writeText(bean.getQDWNumber(), 24.2f, 1.3f, 10f, 1.8f);
        //申报人姓名
        writeText(bean.getQDWCargoOwner(), 13f, 7.2f, 6f, 1.2f);
        //联系电话
        writeText(bean.getQDWPhone(), 26.5f, 7.2f, 6f, 1.2f);
        //养殖户地址
        writeText(bean.getQDAddQy(), 13f, 8.5f, 18f, 1.6f);
        //动物种类
        writeText(bean.getQDWXuZhongZ() + bean.getQDWDanWei(), 13f, 10.4f, 4f, 1.1f);
        //动物数量
        writeText(bean.getQDWQuantity() + "", 26.5f, 10.4f, 4f, 1.1f);
        //动物来源
        if (bean.getQDWLaiYuan().equals("家畜家禽")) {
            writeText("√", 16.0F, 11.9F, 1F, 1F);
        } else if (bean.getQDWLaiYuan().equals("人工饲养")) {
            writeText("√", 21.6F, 11.9F, 1F, 1F);
        } else if (bean.getQDWLaiYuan().equals("合法捕捞")) {
            writeText("√", 27.1f, 11.9F, 1F, 1F);
        }
        //用途
        if (bean.getQDWYongTu().equals("饲养")) {
            writeText("√", 12.8F, 13.6F, 1F, 1F);
        } else if (bean.getQDWYongTu().equals("屠宰")) {
            writeText("√", 15.3F, 13.6F, 1F, 1F);
        } else if (bean.getQDWYongTu().equals("种用、乳用")) {
            writeText("√", 17.8F, 13.6F, 1F, 1F);
        } else if (bean.getQDWYongTu().equals("展览")) {
            writeText("√", 22.3F, 13.6F, 1F, 1F);
        } else if (bean.getQDWYongTu().equals("演出")) {
            writeText("√", 24.7F, 13.6F, 1F, 1F);
        } else if (bean.getQDWYongTu().equals("比赛")) {
            writeText("√", 27.1F, 13.6F, 1F, 1F);
        } else if (bean.getQDWYongTu().equals("其他")) {
            writeText("√", 29.6F, 13.6F, 1F, 1F);
        }
        //畜生耳标号 QDWErBiaoHao
        if (!TextUtils.isEmpty(bean.getQDWErBiaoHao())) {
            if (bean.getQDWErBiaoHao().getBytes().length <= 126) {
                writeText(bean.getQDWErBiaoHao() + "", 12f, 15.75f, 6f, 1.1f);
            } else  {
                writeText(bean.getQDWErBiaoHao().substring(0, 126) + "", 12f, 15.3f, 20f, 1.1f,15F);
                writeText(bean.getQDWErBiaoHao().substring(126) + "", 12f, 15.9f, 20f, 1.1f,15F);
            }
        }
        // 野生动物驯养繁殖许可证号
        writeText(bean.getXKZH(), 12f, 17.84f, 6f, 1.1f);
        //有效...审批表
        if (bean.getYx().equals("有")) {
            writeText("√", 19f, 20.46f, 1f, 1.1f);
        } else if (bean.getYx().equals("无")) {
            writeText("√", 27.4f, 20.4f, 1f, 1.1f);
        }
        //到达地址
        writeText(bean.getQDWAddDd(), 13f, 22.2f, 18f, 1.2f);
//        启动时间DateQy
        if (!TextUtils.isEmpty(bean.getDateQy())) {
            String dateQy = bean.getDateQy();
            String[] split = dateQy.split("-");
            writeText(split[0], 20.25f, 23.7f, 6f, 1.1f);
            writeText(split[1], 22.65f, 23.7f, 6f, 1.1f);
            writeText(split[2], 24.35f, 23.7f, 6f, 1.1f);
        }
        //受理还是不受理QDWAccepted
        if (bean.getQDWAccepted().equals("受理")) {
            if (!TextUtils.isEmpty(bean.getCLRQ())) {
                String dateQy = bean.getCLRQ();
                String[] split = dateQy.split("-");
                writeText("√", 7.8f, 29.7f, 1f, 1.1f);
                writeText(split[0], 15.8f, 29.7f, 6f, 1.1f);
                writeText(split[1], 18.1f, 29.7f, 6f, 1.1f);
                writeText(split[2], 19.9f, 29.7f, 6f, 1.1f);
                writeText("现场", 22.1f, 29.8f, 6f, 1.1f);//bean.getQDWAddress()
                //处理意见
                writeText("现场", 18.15f, 39.5f, 6f, 1.1f);//bean.getQDWAddress()
                writeText("√", 4.3f, 39.4f, 1f, 1.1f);
                writeText(split[0], 10.2f, 39.6f, 6f, 1.1f);
                writeText(split[1], 12.6f, 39.6f, 6f, 1.1f);
                writeText(split[2], 14.3f, 39.6f, 6f, 1.1f);
            }
        } else {
            writeText("√", 6.7f, 30.85f, 1f, 1.1f);
            writeText("√", 3.6f, 41.3f, 1f, 1.1f);
            //理由：Location_Apply.getQDWLiYou()
            if (!TextUtils.isEmpty(bean.getQDWLiYou())) {
                if (bean.getQDWLiYou().length() <= 35) {
                    writeText(bean.getQDWLiYou(), 13.5f, 30.85f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou(), 9f, 41.3f, 40f, 1.1f);
                } else if (bean.getQDWLiYou().length() <= 40 && bean.getQDWLiYou().length() > 35) {
                    writeText(bean.getQDWLiYou(), 9f, 41.3f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(0, 35), 13.5f, 30.45f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(35), 13.5f, 31.25f, 40f, 1.1f);
                } else if (bean.getQDWLiYou().length() > 40) {
                    writeText(bean.getQDWLiYou().substring(0, 40), 9f, 41.3f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(40), 9f, 42.2f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(0, 35), 13.5f, 30.45f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(35), 13.5f, 31.25f, 40f, 1.1f);
                }
            }
        }
        //经办人及时间
        writeText(bean.getQDWAttn() + "", 9.9f, 33.4f, 6f, 1.1f);
        if (!TextUtils.isEmpty(bean.getCLRQ())) {
            String dateQy = bean.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 15.5f, 33.3f, 6f, 1.1f);
            writeText(split[1], 17.8f, 33.3f, 6f, 1.1f);
            writeText(split[2], 19.4f, 33.3f, 6f, 1.1f);
        }

        //经办人及联系电话
        writeText(bean.getQDWAttn() + "", 6.9f, 43.1f, 6f, 1.1f);
        writeText(bean.getQDWJBRDianHua() + "", 16.4f, 43.1f, 6f, 1.1f);
        //最下边的时间
        if (!TextUtils.isEmpty(bean.getCLRQ())) {
            String dateQy = bean.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 26.5f, 45.5f, 6f, 1.1f);
            writeText(split[1], 28.8f, 45.5f, 6f, 1.1f);
            writeText(split[2], 30.3f, 45.5f, 6f, 1.1f);
        }
        File tupianPath = new File(FileUtil.getInstance().getCacheDirPath(), "cdjy_.jpg");
        if (tupianPath.exists()) tupianPath.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, tupianPath.getAbsolutePath());
    }

    /**
     * 繁殖材料产地检疫申报单
     */
    private String draw_2() {

        //报检时间FScTime
        if (!TextUtils.isEmpty(bean.getDateQF())) {
            String dateQy = bean.getDateQF();
            String[] split = dateQy.split("-");
            writeText(split[0], 19.3f, 5.57f, 6f, 1.1f);
            writeText(split[1], 21.7f, 5.57f, 6f, 1.1f);
            writeText(split[2], 23.4f, 5.57f, 6f, 1.1f);
        }
        //申报单编号
        writeText(bean.getQDWNumber(), 24.2f, 1.3f, 10f, 1.8f);
        //申报人姓名
        writeText(bean.getQDWCargoOwner(), 12f, 7.3f, 6f, 1.2f);
        //联系电话
        writeText(bean.getQDWPhone(), 25.1f, 7.3f, 6f, 1.2f);

        //养殖户地址
        writeText(bean.getQDAddQy(), 12f, 8.9f, 18f, 1.6f);
        //动物种类
        writeText(bean.getQDWXuZhongZ(), 12f, 10.6f, 4f, 1.1f);

        //畜生耳标号 QDWErBiaoHao
        if (!TextUtils.isEmpty(bean.getQDWErBiaoHao())) {
            if (bean.getQDWErBiaoHao().getBytes().length <= 63) {
                writeText(bean.getQDWErBiaoHao() + "", 12f, 15.75f, 6f, 1.1f);
            } else if (bean.getQDWErBiaoHao().getBytes().length > 63) {
                writeText(bean.getQDWErBiaoHao().substring(0, 62) + "", 12f, 12.6f, 40f, 1.1f);
                writeText(bean.getQDWErBiaoHao().substring(62) + "", 12f, 13.5f, 40f, 1.1f);
            }
        }
        //动物产品种类
        writeText(bean.getFqProduct() + "", 12f, 15.75f, 4f, 1.1f);
//        动物产品数量
        writeText(bean.getQDWCunDd() + "", 25.3f, 15.75f, 4f, 1.1f);
        //有效
        if (!TextUtils.isEmpty(bean.getFqZxqscjyxkz())) {
            if (bean.getFqZxqscjyxkz().equals("有")) {
                writeText("√", 13.2f, 18f, 1f, 1.1f);
            } else if (bean.getYx().equals("无")) {
                writeText("√", 16.6f, 18f, 1f, 1.1f);
            }
        }
        if (!TextUtils.isEmpty(bean.getYx())) {
            if (bean.getYx().equals("有")) {
                writeText("√", 27.2f, 18f, 1f, 1.1f);
            } else if (bean.getYx().equals("无")) {
                writeText("√", 30.1f, 18f, 1f, 1.1f);
            }
        }

        //到达地址
        writeText(bean.getQDWAddDd(), 12f, 20.4f, 40f, 1.2f);

//        启动时间DateQy
        if (!TextUtils.isEmpty(bean.getDateQy())) {
            String dateQy = bean.getDateQy();
            String[] split = dateQy.split("-");
            writeText(split[0], 19.2f, 22f, 6f, 1.1f);
            writeText(split[1], 21.6f, 22f, 6f, 1.1f);
            writeText(split[2], 23.3f, 22f, 6f, 1.1f);
        }
        ////受理还是不受理QDWAccepted
        if (!TextUtils.isEmpty(bean.getQDWAccepted())) {
            if (bean.getQDWAccepted().equals("受理")) {
                if (!TextUtils.isEmpty(bean.getDateNpy())) {
                    String dateQy = bean.getDateNpy();
                    String[] split = dateQy.split("-");

                    writeText("√", 6.7f, 28.9f, 1f, 1.1f);
                    writeText(split[0], 14.2f, 28.9f, 6f, 1.1f);
                    writeText(split[1], 16.7f, 28.9f, 6f, 1.1f);
                    writeText(split[2], 18.4f, 28.9f, 6f, 1.1f);
                    writeText(bean.getQDWCunDd(), 20.8f, 28.9f, 6f, 1.1f);

                    //处理意见
                    writeText(bean.getQDWCunDd(), 17f, 39.3f, 6f, 1.1f);
                    writeText("√", 3.2f, 39.2f, 1f, 1.1f);
                    writeText(split[0], 9f, 39.2f, 6f, 1.1f);
                    writeText(split[1], 11.3f, 39.2f, 6f, 1.1f);
                    writeText(split[2], 12.9f, 39.2f, 6f, 1.1f);
                }

            } else if (bean.getQDWAccepted().equals("不受理")) {
                writeText("√", 3.2f, 40.1f, 1f, 1.1f);
                writeText("√", 6.4f, 31f, 1f, 1.1f);
                //理由：Location_Apply.getQDWLiYou()
                if (bean.getQDWLiYou().length() <= 35) {
                    writeText(bean.getQDWLiYou(), 13f, 31f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou(), 9.2f, 41.5f, 40f, 1.1f);
                } else if (bean.getQDWLiYou().length() <= 40 && bean.getQDWLiYou().length() > 35) {
                    writeText(bean.getQDWLiYou(), 9.2f, 41.5f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(0, 35), 13f, 30.6f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(35), 13f, 31.5f, 40f, 1.1f);
                } else if (bean.getQDWLiYou().length() > 40) {
                    writeText(bean.getQDWLiYou().substring(0, 40), 9f, 41.5f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(40), 9f, 42.4f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(0, 35), 13f, 30.6f, 40f, 1.1f);
                    writeText(bean.getQDWLiYou().substring(35), 13f, 31.5f, 40f, 1.1f);
                }
            }
        }

        //经办人及时间
        writeText(bean.getQDWAttn() + "", 9.54f, 32.8f, 6f, 1.1f);
        if (!TextUtils.isEmpty(bean.getCLRQ())) {
            String dateQy = bean.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 13.4f, 32.8f, 6f, 1.1f);
            writeText(split[1], 15.8f, 32.8f, 6f, 1.1f);
            writeText(split[2], 17.4f, 32.8f, 6f, 1.1f);
        }

        //经办人及联系电话
        writeText(bean.getQDWAttn() + "", 6.2f, 43.5f, 6f, 1.1f);
        writeText(bean.getQDWJBRDianHua() + "", 14.3f, 43.5f, 6f, 1.1f);


        if (!TextUtils.isEmpty(bean.getCLRQ())) {
            String dateQy = bean.getCLRQ();
            String[] split = dateQy.split("-");
            writeText(split[0], 21.8f, 45.7f, 6f, 1.1f);
            writeText(split[1], 24.2f, 45.7f, 6f, 1.1f);
            writeText(split[2], 25.9f, 45.7f, 6f, 1.1f);
        }
        File tupianPath = new File(FileUtil.getInstance().getCacheDirPath(), "cdjy.jpg");
        if (tupianPath.exists()) tupianPath.delete();
        return FileUtil.getInstance().save2Local2(cacheBitmap, tupianPath.getAbsolutePath());
    }
}
