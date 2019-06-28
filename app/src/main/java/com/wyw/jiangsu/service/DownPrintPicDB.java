package com.wyw.jiangsu.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wyw.jiangsu.bean.DownPrintImgBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by HUANG on 2017/7/28.
 */
public class DownPrintPicDB {
    private static volatile DownPrintPicDB instance;
    private DBManager dbManager = null;

    private DownPrintPicDB(Context mContext) {
        Log.e(DownPrintPicService.class.getSimpleName(),"DownPrintPicDB");
        dbManager = DBManager.getInstance(mContext);
    }

    public static DownPrintPicDB getInstance(Context mContext) {
        if (instance == null) {
            synchronized (DownPrintPicDB.class) {
                if (instance == null) {
                    Log.e(DownPrintPicService.class.getSimpleName()," DownPrintPicDB getInstance");
                    instance = new DownPrintPicDB(mContext);
                }
            }
        }
        return instance;
    }

    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(DownPrintPicService.class.getSimpleName(),"onDowngrade");
        db.execSQL("DROP TABLE IF EXISTS " + PrintPictureInfoColumns.TABLE_NAME);
        onCreate(db);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.e(DownPrintPicService.class.getSimpleName(),"DownPrintPicDB onCreate");
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                PrintPictureInfoColumns.TABLE_NAME + " (" +
                PrintPictureInfoColumns.PICTURE_ID + " LONG NOT NULL," +
                PrintPictureInfoColumns.PICTURE_LOCAL_NAME + " CHAR NOT NULL," +
                PrintPictureInfoColumns.PICTURE_TIME + " LONG NOT NULL," +
                PrintPictureInfoColumns.PICTURE_RETRY + " INT NOT NULL," +
                PrintPictureInfoColumns.PICTURE_UUID + " CHAR NOT NULL," +
                PrintPictureInfoColumns.PICTURE_ADDRESS + " CHAR );");
    }

    //向数据库中添加数据
    public synchronized void insert(DownPrintImgBean bean, DBManager.DBOperatorListener onFailureListener) {
        if (getTask(bean.getId()) == null)
            insert(bean.getId(), bean.getLocalName(),
                    bean.getSaveDir(), 0, bean.getUuid(), onFailureListener, bean.getTime());
    }

    public synchronized void insert(String pictureId, String localName, String pictureAddress,
                                    DBManager.DBOperatorListener onFailureListener,
                                    long time, String uuid) {
        if (getTask(pictureId) == null)
            insert(pictureId, localName, pictureAddress, 0, uuid, onFailureListener, time);
    }

    public synchronized void insert(String pictureId, String localName, String pictureAddress,
                                    int retryCount, String uuid,
                                    DBManager.DBOperatorListener onFailureListener,
                                    long time) {
        SQLiteDatabase database = dbManager.getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(PrintPictureInfoColumns.PICTURE_ID, pictureId);
            values.put(PrintPictureInfoColumns.PICTURE_LOCAL_NAME, localName);
            values.put(PrintPictureInfoColumns.PICTURE_ADDRESS, pictureAddress);
            values.put(PrintPictureInfoColumns.PICTURE_TIME, time);
            values.put(PrintPictureInfoColumns.PICTURE_UUID, uuid);
            values.put(PrintPictureInfoColumns.PICTURE_RETRY, 0);
            database.insert(PrintPictureInfoColumns.TABLE_NAME, null, values);
            database.setTransactionSuccessful();
        } catch (Exception e) {
            //重试5次
            if (retryCount == 5) {
                //插入数据失败
                onFailureListener.onFailure(e);
            } else {
                insert(pictureId, localName, pictureAddress, ++retryCount, uuid, onFailureListener, time);
            }
        } finally {
            database.endTransaction();
        }
    }

    //更新时间
    public synchronized int update(String id, Long newTime, int retry) {
        ContentValues values = new ContentValues();
        values.put(PrintPictureInfoColumns.PICTURE_TIME, newTime);
        values.put(PrintPictureInfoColumns.PICTURE_RETRY, retry);
        String whereClause = PrintPictureInfoColumns.PICTURE_ID + " = ? ";
        String[] whereArgs = new String[]{id};
        return dbManager.getWritableDatabase().update(PrintPictureInfoColumns.TABLE_NAME, values,
                whereClause, whereArgs);
    }


    /**
     * 查看是否立刻进行下载,
     *
     * @return -1 数据库中没有数据
     * 0 有需要立刻下载的数据
     * 其他 定时任务的时间
     */
    public long getTimmerTime() {
        long time = -1;
        Cursor cursor = null;
        try {
            cursor = dbManager.getReadableDatabase().query(PrintPictureInfoColumns.TABLE_NAME,
                    new String[]{PrintPictureInfoColumns.PICTURE_TIME}, null, null, null, null,
                    PrintPictureInfoColumns.PICTURE_TIME + " asc");
            if (cursor != null && cursor.moveToFirst()) {
                Long dbTime = cursor.getLong(0);
                Long currentTime = System.currentTimeMillis();
                if (dbTime > currentTime) return dbTime - currentTime;
                else return 0;
            } else
                //没有数据
                return -1;

        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
    }

    //从数据库中获取所有数据 获取下载数据列表
    public List<String> getTaskList() {
        ArrayList<String> tasks = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = dbManager.getReadableDatabase().query(PrintPictureInfoColumns.TABLE_NAME,
                    new String[]{PrintPictureInfoColumns.PICTURE_ID, PrintPictureInfoColumns.PICTURE_TIME},
                    null, null, null, null, PrintPictureInfoColumns.PICTURE_TIME + " asc");
            if (cursor != null && cursor.moveToFirst()) {
                tasks.ensureCapacity(cursor.getCount());
                long currentTime = System.currentTimeMillis();
                do {
                    long dbTime = cursor.getLong(1);
                    if (dbTime <= currentTime)
                        tasks.add(cursor.getString(0));
                    else break;
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return tasks;
    }

    //根据id值获取相应的实体类
    public DownPrintImgBean getTask(String id) {
        Cursor cursor = null;
        DownPrintImgBean bean = null;
        try {
            cursor = dbManager.getReadableDatabase().query(PrintPictureInfoColumns.TABLE_NAME, null,
                    PrintPictureInfoColumns.PICTURE_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor == null) return null;
            if (cursor.moveToFirst()) {
                do {
                    bean = new DownPrintImgBean(cursor.getString(cursor.getColumnIndex(PrintPictureInfoColumns.PICTURE_ID)),
                            cursor.getString(cursor.getColumnIndex(PrintPictureInfoColumns.PICTURE_LOCAL_NAME)),
                            cursor.getString(cursor.getColumnIndex(PrintPictureInfoColumns.PICTURE_ADDRESS)),
                            cursor.getLong(cursor.getColumnIndex(PrintPictureInfoColumns.PICTURE_TIME)),
                            cursor.getInt(cursor.getColumnIndex(PrintPictureInfoColumns.PICTURE_RETRY)),
                            cursor.getString(cursor.getColumnIndex(PrintPictureInfoColumns.PICTURE_UUID)));

                } while (cursor.moveToNext());
                return bean;
            } else return null;
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
    }

    //删除指定id的值
    public void delete(String id) {
        String whereClause = PrintPictureInfoColumns.PICTURE_ID + " = ? ";
        String[] whereArgs = new String[]{id};
        dbManager.getWritableDatabase().delete(PrintPictureInfoColumns.TABLE_NAME, whereClause, whereArgs);
    }


    /**
     * 如果没有该文件 那么删除本地的图片
     *
     * @param file
     */
    public void deleteFile(File file) {
        if (file.isDirectory()) return;
        try {
            SQLiteDatabase database = dbManager.getReadableDatabase();
//        String uuid = file.getName().substring(0, UUID.randomUUID().toString().length() > file.getName().length()
//                ? file.getName().length() : UUID.randomUUID().toString().length());
            String uuid = file.getName().substring(0, UUID.randomUUID().toString().length());
            database.beginTransaction();
            Cursor cursor = null;
            try {
                cursor = database.query(PrintPictureInfoColumns.TABLE_NAME, null, PrintPictureInfoColumns.PICTURE_UUID + " = ?",
                        new String[]{uuid}, null, null, null);
                int count = cursor.getCount();
                if (count <= 0) file.delete();
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            file.delete();
        }

    }

    public interface PrintPictureInfoColumns {
        /* Table name */
        String TABLE_NAME = "downprintpic_info";

        String PICTURE_ID = "picture_id";

        String PICTURE_LOCAL_NAME = "picture_local_name";

        String PICTURE_ADDRESS = "picture_address";
        String PICTURE_TIME = "picture_time";
        String PICTURE_RETRY = "picture_retry";
        String PICTURE_UUID = "picture_uuid";
    }
}
