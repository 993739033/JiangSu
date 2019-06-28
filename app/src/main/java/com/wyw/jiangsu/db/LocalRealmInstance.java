package com.wyw.jiangsu.db;

/**
 * Created by wyw on 2016/12/22.
 */

public class LocalRealmInstance {
//    private static Realm localRealm;//主线程的realm
//    private static LocalRealmInstance instance;
//
//    private LocalRealmInstance() {
//        Realm.setDefaultConfiguration(getConfig());
//        localRealm = Realm.getDefaultInstance();
//        localRealm.addChangeListener(new RealmChangeListener<Realm>() {
//            @Override
//            public void onChange(Realm element) {
//                Log.e("LocalRealmInstance", "element:" + element);
//            }
//        });
//    }
//
//    public static LocalRealmInstance getInstance() {
//        if (instance == null) {
//            instance = new LocalRealmInstance();
//        }
//        return instance;
//    }
//
//    private static RealmConfiguration getConfig() {
//        return new RealmConfiguration.Builder()
//                .schemaVersion(0)
//                .name("localRealm.realm")
//                .migration(new LocalVersionMigration())
//                .build();
//    }
//    private static RealmConfiguration getAsynConfig() {
//        return new RealmConfiguration.Builder()
//                .name("localRealm.realm")
//                .build();
//    }
//
//
//
//    /**
//     * 保存省市县信息
//     */
//    public void insertOrUpdateUnit() {
//        Realm realm = localRealm;
//        realm.executeTransactionAsync(realm1 -> {
////            Realm asynRealm = Realm.getInstance(getAsynConfig());
//            try {
//                InputStream open = MyApplication.getContext().getAssets().open("unit.txt");
////                realm.beginTransaction();
//                realm1.createOrUpdateAllFromJson(Unit.class, open);
////                realm.commitTransaction();
//            } catch (IOException e) {
//                e.printStackTrace();
////                realm1.cancelTransaction();
//            }
////            realm1.close();
//        }, () -> {
////            realm.close();
//        }, error -> {
//            error.printStackTrace();
//        });
////        Realm realm = localRealm;
////        try {
////            InputStream open = MyApplication.getContext().getAssets().open("unit.txt");
//////            StringBuilder builder = new StringBuilder();
//////            int length;
//////            byte[] buf = new byte[1024];
//////            while ((length = (open.read(buf))) != -1) {
//////                builder.append(new String(buf, 0, length));
//////            }
////            realm.beginTransaction();
////            realm.createOrUpdateAllFromJson(Unit.class, open);
////            realm.commitTransaction();
////        } catch (IOException e) {
////            e.printStackTrace();
////            realm.cancelTransaction();
////        }
//    }
//
//    /**
//     * 保存消毒方式信息
//     */
//    public void insertOrUpdateCodeXD() {
//        Realm realm = localRealm;
//        realm.executeTransactionAsync(realm1 -> {
//            try {
//                InputStream open = MyApplication.getContext().getAssets().open("codexd.txt");
////                realm.beginTransaction();
//                realm1.createOrUpdateAllFromJson(CodeXD.class, open);
////                realm.commitTransaction();
//            } catch (IOException e) {
//                e.printStackTrace();
////                realm.cancelTransaction();
//            }
//        }, () -> {
////            realm.close();
//        }, error -> {
//            error.printStackTrace();
//        });
//    }
//
//    /**
//     * 查询所得的消毒方式
//     */
//
//    public List<CodeXD> querySterilizeWay() {
//        return localRealm.where(CodeXD.class).findAll();
//    }
//
//    public void saveUser(User user) {
//        Realm realm = localRealm;
//        try {
//            realm.beginTransaction();
//            realm.copyToRealmOrUpdate(user);
//            realm.commitTransaction();
//        } catch (Exception e) {
//            e.printStackTrace();
//            realm.cancelTransaction();
//        }
//    }
//
//    /**
//     * 查询所有的省
//     */
//    public List<Unit> queryProvinces() {
//        return localRealm.where(Unit.class).equalTo("uParent", "1").findAll();
//    }
//
//    /**
//     * 查询所有的省 查找指定省
//     * 江苏省 tid 1435
//     */
//    public Unit queryProvince(int tId) {
//        return localRealm.where(Unit.class).equalTo("uParent", "1").equalTo("tId", String.valueOf(tId)).findAll().get(0);
//    }
//
//    /**
//     * 查询省下面的市
//     *
//     * @param uParent 省的tId值
//     */
//    public List<Unit> queryCitys(int uParent) {
//        return localRealm.where(Unit.class).equalTo("uParent", String.valueOf(uParent)).findAll();
//    }
//
//    /**
//     * 查询省下面的市
//     *
//     * @param uParent 省的tId值
//     */
//    public List<Unit> queryCitys(String uParent) {
//        return localRealm.where(Unit.class).equalTo("uParent", uParent).findAll();
//    }
//
//    /**
//     * 查询市市下面的县
//     */
//    public List<Unit> queryCountys(String uParent) {
//        return localRealm.where(Unit.class).equalTo("uParent", uParent).findAll();
//    }
}
