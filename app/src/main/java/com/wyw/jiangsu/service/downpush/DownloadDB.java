package com.wyw.jiangsu.service.downpush;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wyw.jiangsu.bean.DownloadBean;
import com.wyw.jiangsu.service.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyw on 2017/5/19.
 * 推送下载的文件
 */

public class DownloadDB {
    private static volatile DownloadDB instance;
    private DBManager manager;

    private DownloadDB(Context mContext) {
        manager = DBManager.getInstance(mContext);
    }

    public static DownloadDB getInstance(Context mContext) {
        if (instance == null) {
            synchronized (DownloadDB.class) {
                if (instance == null) {
                    instance = new DownloadDB(mContext);
                }
            }
        }
        return instance;
    }
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DownloadInfoColumns.TABLE_NAME);
        onCreate(db);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                DownloadInfoColumns.TABLE_NAME + " (" +
                DownloadInfoColumns.DOWNLOAD_ID + " TEXT NOT NULL PRIMARY KEY," +
                DownloadInfoColumns.DOWNLOAD_FILE_NAME + " CHAR NOT NULL," +
                DownloadInfoColumns.DOWNLOAD_SAVE_DIR + " CHAR NOT NULL," +
                DownloadInfoColumns.DOWNLOAD_URL + " CHAR NOT NULL," +
                DownloadInfoColumns.DOWNLOAD_COMPLETED_SIZE + " LONG," +
                DownloadInfoColumns.DOWNLOAD_MAX_SIZE + " LONG," +
                DownloadInfoColumns.DOWNLOAD_STATUS + " INT);");
    }

    public long insert(DownloadBean bean) {
        return insert(bean.getId(),bean.getFileName(),bean.getDir(),bean.getUrl(),
                bean.getCompletedSize(),bean.getMaxSize(),bean.getStatus());
    }

    public long insert(String id,String fileName,String saveDir,String url,long completedSize,long maxSize ,int status) {
        SQLiteDatabase database = manager.getWritableDatabase();
        database.beginTransaction();
        long returnId = 0l;
        try {
            ContentValues values = new ContentValues();
            values.put(DownloadInfoColumns.DOWNLOAD_ID,id);
            values.put(DownloadInfoColumns.DOWNLOAD_FILE_NAME,fileName);
            values.put(DownloadInfoColumns.DOWNLOAD_SAVE_DIR,saveDir);
            values.put(DownloadInfoColumns.DOWNLOAD_URL,url);
            values.put(DownloadInfoColumns.DOWNLOAD_COMPLETED_SIZE,completedSize);
            values.put(DownloadInfoColumns.DOWNLOAD_MAX_SIZE,maxSize);
            values.put(DownloadInfoColumns.DOWNLOAD_STATUS,status);
            returnId = database.replace(DownloadInfoColumns.TABLE_NAME, null, values);
            database.setTransactionSuccessful();
        }finally {
            database.endTransaction();
        }
        return returnId;
    }
    public synchronized void update(DownloadBean bean) {

        final SQLiteDatabase database = manager.getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues(6);
            values.put(DownloadInfoColumns.DOWNLOAD_FILE_NAME,bean.getFileName());
            values.put(DownloadInfoColumns.DOWNLOAD_SAVE_DIR,bean.getDir());
            values.put(DownloadInfoColumns.DOWNLOAD_URL,bean.getUrl());
            values.put(DownloadInfoColumns.DOWNLOAD_COMPLETED_SIZE,bean.getCompletedSize());
            values.put(DownloadInfoColumns.DOWNLOAD_MAX_SIZE,bean.getMaxSize());
            values.put(DownloadInfoColumns.DOWNLOAD_STATUS,bean.getStatus());
            database.update(DownloadInfoColumns.TABLE_NAME, values, DownloadInfoColumns.DOWNLOAD_ID + " = ?",
                    new String[]{bean.getId()});
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }
    public DownloadBean getTaskById(String id) {
        SQLiteDatabase db = manager.getReadableDatabase();
        DownloadBean bean = new DownloadBean();
        Cursor cursor = null;
        try{
            cursor = db.query(DownloadInfoColumns.TABLE_NAME, null, DownloadInfoColumns.DOWNLOAD_ID+" = ? ", new String[]{id}, null, null, null, null);
            if (cursor.moveToFirst()) {
                bean.setId(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_ID)));
                bean.setFileName(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_FILE_NAME)));
                bean.setDir(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_SAVE_DIR)));
                bean.setUrl(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_URL)));
                bean.setCompletedSize(cursor.getLong(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_COMPLETED_SIZE)));
                bean.setMaxSize(cursor.getLong(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_MAX_SIZE)));
                bean.setStatus(cursor.getInt(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_STATUS)));
            } else return null;
        }catch (Exception e){
            return null;
        }finally {
            if (cursor != null) cursor.close();
        }
        return bean;
    }

    public List<DownloadBean> query() {
        List<DownloadBean> list = new ArrayList<>();
        SQLiteDatabase db = manager.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(DownloadInfoColumns.TABLE_NAME, null, null, null, null, null, null, null);
            if (cursor.getCount() == 0) return null;
            if (cursor.moveToFirst()) {
                do {
                    DownloadBean bean = new DownloadBean();
                    bean.setId(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_ID)));
                    bean.setFileName(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_FILE_NAME)));
                    bean.setDir(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_SAVE_DIR)));
                    bean.setUrl(cursor.getString(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_URL)));
                    bean.setCompletedSize(cursor.getLong(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_COMPLETED_SIZE)));
                    bean.setMaxSize(cursor.getLong(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_MAX_SIZE)));
                    bean.setStatus(cursor.getInt(cursor.getColumnIndex(DownloadInfoColumns.DOWNLOAD_STATUS)));
                    list.add(bean);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            return null;
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public int delete(String printId) {
        String whereClause = DownloadInfoColumns.DOWNLOAD_ID + " = ? ";
        String[] whereArgs = new String[]{printId};
        return manager.getWritableDatabase().delete(DownloadInfoColumns.TABLE_NAME, whereClause, whereArgs);
    }

    public interface DownloadInfoColumns {
        /* Table name */
        String TABLE_NAME = "download_info";

        String DOWNLOAD_ID = "download_id";
        String DOWNLOAD_STATUS = "download_status";
        String DOWNLOAD_MAX_SIZE = "download_max_size";
        String DOWNLOAD_COMPLETED_SIZE = "download_complete_size";
        String DOWNLOAD_FILE_NAME = "downlaod_file_name";
        String DOWNLOAD_SAVE_DIR = "download_save_dir";
        String DOWNLOAD_URL = "download_url";
    }
}
