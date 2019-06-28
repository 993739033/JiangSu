package com.wyw.jiangsu;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.wyw.jiangsu.db.LocalGreenDao;
import com.wyw.jiangsu.utils.CrashHandler;

//import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

//import io.realm.Realm;
//import io.realm.RealmConfiguration;

/**
 * Created by wyw on 2016/12/20.
 */

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        LeakCanary.install(this);
        CrashHandler.getInstance().init(context);
        if (BuildConfig.DEBUG) {

        }
        Stetho.initializeWithDefaults(context);
        //默认路径 Context.getFilesDir()
//        Realm.init(this);
        Stetho.initialize(//Stetho初始化
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
//        RealmConfiguration configuration = new RealmConfiguration.Builder()
//                .schemaVersion(0)
//                .name("localRealm.realm")
//                .migration(new LocalVersionMigration())
//                .build();
//        Realm.setDefaultConfiguration(configuration);
//        LocalRealmInstance.getInstance().insertOrUpdateUnit();
//        LocalRealmInstance.getInstance().insertOrUpdateCodeXD();
//        new Thread(() -> {
//
//        }).start();
        LocalGreenDao.getInstance().insertOrUpdateUnit();
        LocalGreenDao.getInstance().insertOrUpdateCodeXD();
        //复制so文件
//        CommonUtils.initAssetsFile();
//        Toast.makeText(context, "complete", Toast.LENGTH_SHORT).show();
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
