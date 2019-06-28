package com.wyw.jiangsu.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wyw.jiangsu.service.downpush.DownloadDB;

/**
 * Created by wyw on 2017/5/11.
 */

public class DBManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "picture.db";
    private static final int  VERSION =1;
    private final Context mContext;
    private volatile static DBManager sInstance = null;
    public DBManager(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.mContext = context;
    }
    public static final synchronized DBManager getInstance(final Context context) {
        if (sInstance == null) {
            Log.e(DownPrintPicService.class.getSimpleName(),"DBManager");
            sInstance = new DBManager(context.getApplicationContext());
        }
        return sInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(DownPrintPicService.class.getSimpleName(),"DBManager onCreate");
        PictureDB.getInstance(mContext).onCreate(db);
        DownloadDB.getInstance(mContext).onCreate(db);
        DownPrintPicDB.getInstance(mContext).onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(DownPrintPicService.class.getSimpleName(),"DBManager onUpgrade");
        PictureDB.getInstance(mContext).onUpgrade(db,oldVersion,newVersion);
        DownloadDB.getInstance(mContext).onUpgrade(db,oldVersion,newVersion);
        DownPrintPicDB.getInstance(mContext).onUpgrade(db,oldVersion,newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(DownPrintPicService.class.getSimpleName(),"DBManager onDowngrade");
        PictureDB.getInstance(mContext).onDowngrade(db,oldVersion,newVersion);
        DownloadDB.getInstance(mContext).onDowngrade(db,oldVersion,newVersion);
        DownPrintPicDB.getInstance(mContext).onDowngrade(db,oldVersion,newVersion);
    }

    public interface DBOperatorListener{
        void onFailure(Exception e);
    }
}
