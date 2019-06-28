package com.wyw.jiangsu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.wyw.jiangsu.db.local.CodeXDDao;
import com.wyw.jiangsu.db.local.DaoMaster;
import com.wyw.jiangsu.db.local.UnitDao;
import com.wyw.jiangsu.db.local.UserDao;

/**
 * Created by wyw on 2017/4/11.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, UserDao.class, CodeXDDao.class, UnitDao.class);

    }
}