package com.wyw.jiangsu.db;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wyw.jiangsu.MyApplication;
import com.wyw.jiangsu.db.local.CodeXDDao;
import com.wyw.jiangsu.db.local.DaoMaster;
import com.wyw.jiangsu.db.local.DaoSession;
import com.wyw.jiangsu.db.local.UnitDao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by wyw on 2017/4/11.
 */

public class LocalGreenDao {
    private static LocalGreenDao instance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static final String DATABASE_NAME = "userdata.db";
    private Context mContext;

    public LocalGreenDao(Context mContext) {
        getDaoSession();
        this.mContext = mContext;
    }

    public static LocalGreenDao getInstance() {
        if (instance == null) {
            instance = new LocalGreenDao(MyApplication.getContext());
        }
        return instance;
    }


    private DaoMaster obtainMaster(Context context, String dbName) {
        //改
        return new DaoMaster(new MySQLiteOpenHelper(context, dbName, null).getWritableDatabase());
//        return new DaoMaster(new MySQLiteOpenHelper(context, dbName, null).getWritableDatabase());
    }

    private DaoMaster getDaoMaster(Context context, String dbName) {
        if (dbName == null)
            return null;
        if (daoMaster == null) {
            daoMaster = obtainMaster(context, dbName);
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @return
     */
    public DaoSession getDaoSession(String dbName) {

        if (daoSession == null) {
            daoSession = getDaoMaster(MyApplication.getContext(), dbName).newSession();
        }
        return daoSession;
    }

    /**
     * 默认操作localdata数据库
     */
    public DaoSession getDaoSession() {

        if (daoSession == null) {
            daoSession = getDaoMaster(MyApplication.getContext(), DATABASE_NAME).newSession();
        }
        return daoSession;
    }


    /**
     * 保存省市县信息
     */
    public void
    insertOrUpdateUnit() {
        new Thread(() -> {
            UnitDao unitDao = daoSession.getUnitDao();
            try {
                if (!unitDao.loadAll().isEmpty()) return;
                InputStream inputStream = mContext.getAssets().open("unit.txt");
                List<Unit> units = new Gson().fromJson(new InputStreamReader(inputStream), new TypeToken<List<Unit>>() {
                }.getType());
                unitDao.insertInTx(units);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 保存消毒方式信息
     */
    public void insertOrUpdateCodeXD() {
        new Thread(() -> {
            CodeXDDao codeXDDao = daoSession.getCodeXDDao();
            try {
                if (!codeXDDao.loadAll().isEmpty()) return;
                InputStream inputStream = mContext.getAssets().open("codexd.txt");
                List<CodeXD> codeXDs = new Gson().fromJson(new InputStreamReader(inputStream), new TypeToken<List<CodeXD>>() {
                }.getType());
                codeXDDao.insertInTx(codeXDs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 查询所得的消毒方式
     */
    public List<CodeXD> querySterilizeWay() {
        return daoSession.getCodeXDDao().loadAll();
    }

    /**
     * 保存登录用户
     *
     * @param user
     */
    public void saveUser(User user) {
        daoSession.getUserDao().insertOrReplace(user);
    }

    /**
     * 查询所有的省
     */
    public List<Unit> queryProvinces() {
        return daoSession.getUnitDao().queryBuilder().where(UnitDao.Properties.UParent.eq("1")).list();
//        return localRealm.where(Unit.class).equalTo("uParent", "1").findAll();
    }

    /**
     * 查询所有的省 查找指定省
     * 江苏省 tid 1435
     */
    public Unit queryProvince(int tId) {
        return daoSession.getUnitDao().queryBuilder().where(UnitDao.Properties.UParent.eq("1"),
                UnitDao.Properties.TId.eq(String.valueOf(tId))).list().get(0);
//        return localRealm.where(Unit.class).equalTo("uParent", "1").equalTo("tId", String.valueOf(tId)).findAll().get(0);
    }

    /**
     * 查询省下面的市
     *
     * @param uParent 省的tId值
     */
    public List<Unit> queryCitys(String uParent) {
        return daoSession.getUnitDao().queryBuilder().
                where(UnitDao.Properties.UParent.eq(uParent)).list();
//        return localRealm.where(Unit.class).equalTo("uParent", String.valueOf(uParent)).findAll();
    }

    public List<Unit> queryCitys(int uParent) {
        return daoSession.getUnitDao().queryBuilder().
                where(UnitDao.Properties.UParent.eq(String.valueOf(uParent))).list();
//        return localRealm.where(Unit.class).equalTo("uParent", String.valueOf(uParent)).findAll();
    }

    /**
     * 查询省下面的市 县
     */
    public List<Unit> queryCitysOrCountries(String uParent) {
        return daoSession.getUnitDao().queryBuilder().where(UnitDao.Properties.UParent.eq(uParent)).list();
//        return localRealm.where(Unit.class).equalTo("uParent", uParent).findAll();
    }

}
