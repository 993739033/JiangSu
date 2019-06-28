package com.wyw.jiangsu.runnable;


import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.wyw.jiangsu.BuildConfig;
import com.wyw.jiangsu.bean.AH_AnimalOrigin;
import com.wyw.jiangsu.bean.AdressBean;
import com.wyw.jiangsu.bean.AgencyDeclareDetilActivityBean;
import com.wyw.jiangsu.bean.AgencyDeclareListBean;
import com.wyw.jiangsu.bean.AnimAProcessListBean;
import com.wyw.jiangsu.bean.AnimAProcessUserDetilBean;
import com.wyw.jiangsu.bean.AnimAQueryListBean;
import com.wyw.jiangsu.bean.AnimAlistBean;
import com.wyw.jiangsu.bean.AnimBQueryListBean;
import com.wyw.jiangsu.bean.AnimBlistBean;
import com.wyw.jiangsu.bean.AnimProcessListBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordBean;
import com.wyw.jiangsu.bean.AnimRuZhongYongRecordListBean;
import com.wyw.jiangsu.bean.AnimTypeListBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBean;
import com.wyw.jiangsu.bean.AnimZhongDanRedordBeangai;
import com.wyw.jiangsu.bean.AnimaABProductABBean;
import com.wyw.jiangsu.bean.AnimalQuarantineActivityBean;
import com.wyw.jiangsu.bean.AnjiandengjiActivityBean;
import com.wyw.jiangsu.bean.BanshpeopleleixzActivityBean;
import com.wyw.jiangsu.bean.BaseMsg;
import com.wyw.jiangsu.bean.BaseMsgBean;
import com.wyw.jiangsu.bean.BreedingDetailshowBean;
import com.wyw.jiangsu.bean.BreedingRecordData;
import com.wyw.jiangsu.bean.CaseRegisterBean;
import com.wyw.jiangsu.bean.ChaxunJinduActuvityBean;
import com.wyw.jiangsu.bean.ChuKuDeatilBean;
import com.wyw.jiangsu.bean.ChufaListBean;
import com.wyw.jiangsu.bean.CollectionListBean;
import com.wyw.jiangsu.bean.CollectionPointDealActivityBean;
import com.wyw.jiangsu.bean.CollectionTransportDealActivityBean;
import com.wyw.jiangsu.bean.CommonSupervisionBean;
import com.wyw.jiangsu.bean.CommonSupervisionQyBean;
import com.wyw.jiangsu.bean.DeclareNewstListBean;
import com.wyw.jiangsu.bean.FadingFaguiBean;
import com.wyw.jiangsu.bean.FaramDeclareListBean;
import com.wyw.jiangsu.bean.GuarantineDeclareListDetilBean;
import com.wyw.jiangsu.bean.HarmlessListBean;
import com.wyw.jiangsu.bean.HarmlessListDetilBean;
import com.wyw.jiangsu.bean.HarmlessShujiDetilBean;
import com.wyw.jiangsu.bean.HomeBean;
import com.wyw.jiangsu.bean.JiZhongChuLiDetilBean;
import com.wyw.jiangsu.bean.JianyiChuliTongzhidanListBean;
import com.wyw.jiangsu.bean.JianyiChuliTongzhihaobean;
import com.wyw.jiangsu.bean.KanYanbean;
import com.wyw.jiangsu.bean.NoDealWithBean;
import com.wyw.jiangsu.bean.ProductA_BBeanListGai;
import com.wyw.jiangsu.bean.ProductBListBean;
import com.wyw.jiangsu.bean.ProductionAListBean;
import com.wyw.jiangsu.bean.QroducingAreaProcessListBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsAListBean;
import com.wyw.jiangsu.bean.Qua_AnimalProductsBListBean;
import com.wyw.jiangsu.bean.QuarantineDealListQueryBean;
import com.wyw.jiangsu.bean.QuarantineProcessNotifListBean;
import com.wyw.jiangsu.bean.QuarantineProcessNotifListBeanDetil;
import com.wyw.jiangsu.bean.RegisteMsgBean;
import com.wyw.jiangsu.bean.RuChangChaXunBean;
import com.wyw.jiangsu.bean.RuChangChaYanQueryBean;
import com.wyw.jiangsu.bean.ShenbaoJiluBianhaoBean;
import com.wyw.jiangsu.bean.ShouyiJiandusuoBean;
import com.wyw.jiangsu.bean.SlaghterNumberBean;
import com.wyw.jiangsu.bean.SlaughterAnimTypeBean;
import com.wyw.jiangsu.bean.SlaughterChooseBean;
import com.wyw.jiangsu.bean.SlaughterSpHouseBean;
import com.wyw.jiangsu.bean.StoreChukuBean;
import com.wyw.jiangsu.bean.SupervisionVeterinarianListBean;
import com.wyw.jiangsu.bean.TimeBean;
import com.wyw.jiangsu.bean.TongzhiGGBean;
import com.wyw.jiangsu.bean.UserBean;
import com.wyw.jiangsu.bean.UserDetilBean;
import com.wyw.jiangsu.bean.UserDetilListBean;
import com.wyw.jiangsu.bean.UserRegisteAddressBean;
import com.wyw.jiangsu.bean.V_APP_SJDRYXXQueryBean;
import com.wyw.jiangsu.bean.V_APP_WHHCLZXQueryBean;
import com.wyw.jiangsu.bean.V_APP_WHHSJRWSSQueryBean;
import com.wyw.jiangsu.bean.V_APP_ZCSYXXQueryBean;
import com.wyw.jiangsu.bean.VerficationListBean;
import com.wyw.jiangsu.bean.WHHChuKuChooseBean;
import com.wyw.jiangsu.bean.WHHChukuBean;
import com.wyw.jiangsu.bean.WHHChukuFirstBean;
import com.wyw.jiangsu.bean.WHHZhiPaiBean;
import com.wyw.jiangsu.bean.WHHZhiPaiChooseBean;
import com.wyw.jiangsu.bean.WeizhiBean;
import com.wyw.jiangsu.bean.WuHaiHuaCXbean;
import com.wyw.jiangsu.bean.WuhaihuaDateBean;
import com.wyw.jiangsu.bean.XianchangCheckBean;
import com.wyw.jiangsu.bean.XunWenListBean;
import com.wyw.jiangsu.bean.YangzhihuDetailActivityBean;
import com.wyw.jiangsu.bean.ZaiqianCheckQueryBean;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wyw on 2016/6/25.
 */
public class NetClient {
//        public static String BASE_DOMAIN = "http://36.111.192.50:8888/";                      //服务器路径
//        public static String BASE_DOMAIN = "http://192.168.0.221:8886/";                      //本地路径
    public static String BASE_DOMAIN = "http://www.miliotech.com:8888/";                    //119测试路径
    public static final String DOMAIN = BASE_DOMAIN + "JiangSuAPP/HtmlAshx/";                    //发布版本的路径
//    public static final String CESHI_TEST = "http://www.miliotech.com:8888/JiangSuAPP/HtmlAshx/";//给测试人员测试的路径
//        public static String IMG_PRE = "http://36.111.192.50:8888/JiangSuAPP/";//前缀
//        public static String IMG_PRE = "http://192.168.0.221:8886/JiangSuAPP/";//前缀
    public static String IMG_PRE = "http://www.miliotech.com:8888/JiangSuAPP/";//前缀
    public static OkHttpClient okHttpClient;
    public static final String URL = DOMAIN;
//    public static final String URL = Test;

    private static final String SECRET_KEY = "sdf";
    private static final String TAG = NetClient.class.getSimpleName();
    private static NetClient mInstance;
    private Retrofit mRetrofit;
    private File httpCacheDirectory; //缓存文件夹
    private static int cacheSize = 10 * 1024 * 1024;//10M

    private static RequestService requestService;


    /**
     * 通过这个方法来调用网络请求的接口
     */
    public static RequestService getRequest() {
        return getInstance().requestService;
    }

    private synchronized static NetClient getInstance() {
        if (mInstance == null) {
            mInstance = new NetClient();
        }
        return mInstance;
    }

    private NetClient() {
        //初始化缓存
        //设置cookie  compile 'com.squareup.okhttp3:okhttp-urlconnection:3.2.0'
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder()
                //设置超时
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        //将ResponseBody转换成我们需要的FileResponseBody
                        return response.newBuilder().body(new FileResponseBody<ResponseBody>(response.body(), new RetrofitCallback<ResponseBody>() {
                            @Override
                            protected void onSuccess(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                                Log.e("NetClient", "success");
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("NetClient", "failure");
                            }

                            @Override
                            public void onLoading(long total, long progress) {
                                Log.e("NetClient", "total: " + total + " progress: " + progress);
                            }
                        })).build();
                    }
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //设置错误重连
                .retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            //日志拦截
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("network", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }
//        okHttpBuilder.interceptors().add(new ParamterInterceptor());
        //配置retrofit
        okHttpClient = okHttpBuilder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //注意顺序
//               .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        requestService = mRetrofit.create(RequestService.class);
    }


    //用注解的方法做一个数据请求的内部接口
    public interface RequestService {
        @Streaming
        @GET
        Observable<ResponseBody> downPrintImg(@Url String url);

        @FormUrlEncoded
        @POST("GetVersion.ashx")
        Observable<BaseMsgBean> checkApkVersion(@Field("version") String apkVersion);

        @FormUrlEncoded
        @POST("GetVersions.ashx")
        Observable<BaseMsgBean> checkApkVersion(@Field("userType") String usertype, @Field("version") String apkVersion);

        @FormUrlEncoded
        @POST("GetDBVersion.ashx")
        Observable<BaseMsgBean> checkDbVersion(@Field("version") String apkVersion);


        @FormUrlEncoded
        @POST("LoginPage.ashx")
        Observable<UserBean> login(@Field("uAccount") String userName,
                                   @Field("pwd") String pwd);

        //用户登陆信息记录
        @FormUrlEncoded
        @POST("UploadFF.ashx")
        Observable<BaseMsgBean> uploadLoginInfo(@Field("uid") String uid, @Field("json") String json, @Field("type") String type);

        @FormUrlEncoded
        @POST("GetAPP_Menu_Pop.ashx")
        Observable<HomeBean> getPermissionList(@Field("uid") String uid,
                                               @Field("rid") String rid);

        @FormUrlEncoded
        @POST("")
        Observable<Object> uploadModify();

        //推送上传位置信息
        @FormUrlEncoded
        @POST("UploadAdress.ashx")
        Observable<WeizhiBean> upWeizhi(@Field("UserID") String uid,
                                        @Field("Longitude") String longitude,
                                        @Field("Latitude") String latitude,
                                        @Field("ErrorMsg") String errorMsg);


        //日常监管企业信息
        @FormUrlEncoded
        @POST("GetDailyRegulation.ashx")
        Observable<CommonSupervisionQyBean> daySupervise(@Field("Fsuserid") String fstid,
                                                         @Field("FSunitUstrId") String fsuid,
                                                         @Field("TableName") String tableName,
                                                         @Field("SeachTxt") String text,
                                                         @Field("TId") String tid);


        //法律法規
        @FormUrlEncoded
        @POST("GEtLawsRegulations.ashx")
        Observable<FadingFaguiBean> law(@Field("SeachTxt") String text);

        /**
         * 注册的地址
         *
         * @return
         */
//        @FormUrlEncoded
        @POST("GetDZ.ashx")
        Observable<AdressBean> getAdress();

        /**
         * 无害化申报 获取用户的个人信息 畜主姓名等
         *
         * @param userId 用户id
         * @param type   类型
         * @return
         */
        @FormUrlEncoded
        @POST("WHHCLSB.ashx")
        Observable<UserDetilBean> getUserDetil(@Field("id") String userId,
                                               @Field("type") String type);

        @FormUrlEncoded
        @POST("UploadFF.ashx")
        Observable<RegisteMsgBean> UploadFF(@Field("json") String userId,
                                            @Field("uid") int uid, @Field("type") String type,
                                            @Field("myguid") String myguid);


        /**
         * 无害化出库管理
         *
         * @param
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetWHHCKGL.ashx")
        Observable<WHHChukuBean> getWHHChuku(@Field("uid") int uid,
                                             @Field("FStId") int fStId);

        /**
         * 通知公告
         *
         * @param fid
         * @param fsRoleId
         * @param tableName
         * @param tid
         * @return
         */
        @FormUrlEncoded
        @POST("GetNotice.ashx")
        Observable<TongzhiGGBean> getTongzhi(@Field("Fsuserid") String fid,
                                             @Field("FsRoleId") String fsRoleId,
                                             @Field("TableName") String tableName,
                                             @Field("TId") String tid);

        @FormUrlEncoded
        @POST("GetWHHCKGL.ashx")
        Observable<WHHChukuFirstBean> getWHHChuku1(@Field("uid") int uid,
                                                   @Field("FStId") int fStId);

        /**
         * 无害化指派基础信息
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetWHHZP.ashx")
        Observable<WHHZhiPaiBean> getWHHZhiPai(@Field("uid") int uid);

        /**
         * 无害化指派收集选择
         *
         * @param uid
         * @param value
         * @param fstId
         * @return
         */
        @FormUrlEncoded
        @POST("GetWHHXZ.ashx")
        Observable<WHHZhiPaiChooseBean> getZhiPaiChoose(@Field("uid") String uid,
                                                        @Field("value") String value,
                                                        @Field("FStId") String fstId);


        /**
         * 无害化出库选择
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetWHHCKXZ.ashx")
        Observable<WHHChuKuChooseBean> getWHHChukuChoose(@Field("uid") int uid,
                                                         @Field("FStId") String fstid);


        /**
         * 获取无害化处理的各种列表 以及 点进去的详情
         * 根据type 来区分是详情 还是 列表
         *
         * @param userId
         * @param type
         * @param fStId
         * @return
         */
        @FormUrlEncoded
        @POST("WHHCLSB.ashx")
        Observable<HarmlessListBean> getHarmlessList(@Field("id") String userId,
                                                     @Field("type") String type,
                                                     @Field("FStId") String fStId);

        /**
         * 获取无害化处理的各种列表 以及 点进去的详情
         * 根据type 来区分是详情 还是 列表
         *
         * @param userId
         * @param type
         * @param fStId
         * @return
         */
        @FormUrlEncoded
        @POST("WHHCLSB.ashx")
        Observable<HarmlessListDetilBean> getHarmlessListDetil(@Field("id") String userId,
                                                               @Field("type") String type,
                                                               @Field("FStId") String fStId);


        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<BreedingRecordData> getBreedingListDetilQuery(@Field("id") String id,
                                                                 @Field("TableName") String tableName,
                                                                 @Field("FStId") String fStId,
                                                                 @Field("name") String name,
                                                                 @Field("value") String value,
                                                                 @Field("sdate") String sdate,
                                                                 @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetZFCX.ashx")
        Observable<CaseRegisterBean> getCaseRegistDetilQuery(@Field("uid") String id,
                                                             @Field("TableName") String tableName,
                                                             @Field("FStId") String fStId,
                                                             @Field("name") String name,
                                                             @Field("value") String value,
                                                             @Field("sdate") String sdate,
                                                             @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetZFCX.ashx")
        Observable<XianchangCheckBean> getXianChangDetilQuery(@Field("uid") String id,
                                                              @Field("TableName") String tableName,
                                                              @Field("FStId") String fStId,
                                                              @Field("name") String name,
                                                              @Field("value") String value,
                                                              @Field("sdate") String sdate,
                                                              @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetZFCX.ashx")
        Observable<XunWenListBean> getXunWenListBean(@Field("uid") String id,
                                                     @Field("TableName") String tableName,
                                                     @Field("FStId") String fStId,
                                                     @Field("name") String name,
                                                     @Field("value") String value,
                                                     @Field("sdate") String sdate,
                                                     @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetZFCX.ashx")
        Observable<ChufaListBean> getChufaListData(@Field("uid") String id,
                                                   @Field("TableName") String tableName,
                                                   @Field("FStId") String fStId,
                                                   @Field("name") String name,
                                                   @Field("value") String value,
                                                   @Field("sdate") String sdate,
                                                   @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetJDJCXQ.ashx")
        Observable<BreedingDetailshowBean> getBreedingDetail22(@Field("TableName") String tableName,
                                                               @Field("FStId") String fStId);

        @FormUrlEncoded
        @POST("GetJDJCXQ.ashx")
        Observable<BreedingDetailshowBean.DataListBean> getBreedingDetilQuery2(
                @Field("FStId") String fStId,
                @Field("TableName") String tableName);


        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<BreedingRecordData> getMedicineProduce(@Field("id") String id,
                                                          @Field("TableName") String tableName,
                                                          @Field("FStId") String fStId,
                                                          @Field("name") String name,
                                                          @Field("value") String value,
                                                          @Field("sdate") String sdate,
                                                          @Field("jdate") String jdate);


        @POST("GetDZ.ashx")
        Observable<UserRegisteAddressBean> getRegisteAddress();

        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<BreedingRecordData> getSiliaoProduce(@Field("id") String id,
                                                        @Field("TableName") String tableName,
                                                        @Field("FStId") String fStId,
                                                        @Field("name") String name,
                                                        @Field("value") String value,
                                                        @Field("sdate") String sdate,
                                                        @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<BreedingRecordData> getBreedingListDetilQuery2(@Field("id") String id,
                                                                  @Field("TableName") String tableName,
                                                                  @Field("FStId") String fStId,
                                                                  @Field("name") String name,
                                                                  @Field("value") String value,
                                                                  @Field("sdate") String sdate,
                                                                  @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<BreedingRecordData> getAninalTreateData(@Field("id") String id,
                                                           @Field("TableName") String tableName,
                                                           @Field("FStId") String fStId,
                                                           @Field("name") String name,
                                                           @Field("value") String value,
                                                           @Field("sdate") String sdate,
                                                           @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<BreedingRecordData> getHarmlesshandle(@Field("id") String id,
                                                         @Field("TableName") String tableName,
                                                         @Field("FStId") String fStId,
                                                         @Field("name") String name,
                                                         @Field("value") String value,
                                                         @Field("sdate") String sdate,
                                                         @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<BreedingRecordData> getPartPlace(@Field("id") String id,
                                                    @Field("TableName") String tableName,
                                                    @Field("FStId") String fStId,
                                                    @Field("name") String name,
                                                    @Field("value") String value,
                                                    @Field("sdate") String sdate,
                                                    @Field("jdate") String jdate);


        /**
         * 获取无害化处理的各种列表 以及 点进去的详情
         * 根据type 来区分是详情 还是 列表
         *
         * @param userId
         * @param type
         * @param fStId
         * @return
         */
        @FormUrlEncoded
        @POST("WHHCLSB.ashx")
        Observable<HarmlessShujiDetilBean> getCeshiDetil(@Field("id") String userId,
                                                         @Field("type") String type,
                                                         @Field("FStId") String fStId);


        @FormUrlEncoded
        @POST("UploadFF.ashx")
        Observable<BaseMsgBean> uploadData(@Field("json") String json,
                                           @Field("type") String type,
                                           @Field("uid") String uid,
                                           @Field("myguid") String myguid);

        @FormUrlEncoded
        @POST("UploadFF.ashx")
        Observable<BaseMsgBean> uploadZhuce(@Field("json") String json,
                                            @Field("uid") String uid,
                                            @Field("type") String type,
                                            @Field("myguid") String myguid);


        /**
         * @param json
         * @param type
         * @param uid
         * @param zt   状态区分  打印——1
         *             报废——2
         * @return
         */
        @FormUrlEncoded
        @POST("UploadZT.ashx")
        Observable<BaseMsgBean> uploadPrint(@Field("type") String json,
                                            @Field("id") String type,
                                            @Field("tname") String uid,
                                            @Field("zt") String zt);

        @Streaming
        @POST("UplaodSBPicture.aspx")
        Observable<BaseMsgBean> uploadNew(@Body RequestBody body);


        @POST("UplaodJYFiles.ashx")
        Observable<BaseMsgBean> uploadFile(@Body RequestBody body);


        /**
         * 检疫申报单列表
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<GuarantineDeclareListDetilBean> getGuarantineDeclareListDetil(@Field("id") String id,
                                                                                 @Field("TableName") String tableName,
                                                                                 @Field("FStId") String fStId);

        /**
         * 检疫申报单列表
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<AnimAProcessUserDetilBean> getAnimAProcessDetil(@Field("id") String id,
                                                                   @Field("TableName") String tableName,
                                                                   @Field("FStId") String fStId);

        /**
         * 查看查询检疫申报单列表
         */
        @FormUrlEncoded
        @POST("GetCKCX.ashx")
        Observable<AnimAProcessUserDetilBean> getAnimAProcessDetil2(
                @Field("TableName") String tableName,
                @Field("FStId") String fStId);

        /**
         * 动物检疫申报单查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AnimalQuarantineActivityBean> getGuarantineDeclareListDetilQuery(@Field("id") String id,
                                                                                    @Field("TableName") String tableName,
                                                                                    @Field("FStId") String fStId,
                                                                                    @Field("name") String name,
                                                                                    @Field("value") String value,
                                                                                    @Field("sdate") String sdate,
                                                                                    @Field("jdate") String jdate);

        /**
         * 入场查验登记查询接口
         */
        @FormUrlEncoded
        @POST("GetNoInfo.ashx")
        Observable<RuChangChaXunBean> getRuchangQuery(@Field("eNo") String eNo);


        /**
         * 入场查验登记选择,查询界面列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<RuChangChaYanQueryBean> getRuChangChaYanQueryBean(@Field("id") String id,
                                                                     @Field("TableName") String tableName,
                                                                     @Field("FStId") String fStId,
                                                                     @Field("name") String name,
                                                                     @Field("value") String value,
                                                                     @Field("sdate") String sdate,
                                                                     @Field("jdate") String jdate);

        /**
         * 养殖场（户）申报列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<FaramDeclareListBean> getFaramDeclareListBean(@Field("id") String id,
                                                                 @Field("TableName") String tableName,
                                                                 @Field("FStId") String fStId,
                                                                 @Field("name") String name,
                                                                 @Field("value") String value,
                                                                 @Field("sdate") String sdate,
                                                                 @Field("jdate") String jdate);

        /**
         * 养殖场（户）申报列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AgencyDeclareListBean> getagencyDeclareListBean(@Field("id") String id,
                                                                   @Field("TableName") String tableName,
                                                                   @Field("FStId") String fStId,
                                                                   @Field("name") String name,
                                                                   @Field("value") String value,
                                                                   @Field("sdate") String sdate,
                                                                   @Field("jdate") String jdate);

        /**
         * 代理申报列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<DeclareNewstListBean> getDeclareNewstListBean(@Field("id") String id,
                                                                 @Field("TableName") String tableName,
                                                                 @Field("FStId") String fStId,
                                                                 @Field("name") String name,
                                                                 @Field("value") String value,
                                                                 @Field("sdate") String sdate,
                                                                 @Field("jdate") String jdate);

        /**
         * 现场核查列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<VerficationListBean> getVeficationLocaBean(@Field("id") String id,
                                                              @Field("TableName") String tableName,
                                                              @Field("FStId") String fstid,
                                                              @Field("name") String name,
                                                              @Field("value") String value,
                                                              @Field("sdate") String sdate,
                                                              @Field("jdate") String jdate);

        /**
         * 检疫处理单列表
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<AnimProcessListBean> getAnimProcessListDetil(@Field("id") String id,
                                                                @Field("TableName") String tableName,
                                                                @Field("FStId") String fStId);


        /**
         * 检疫处理单列表查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AnimProcessListBean> getAnimProcessListDetilQuery(@Field("id") String id,
                                                                     @Field("TableName") String tableName,
                                                                     @Field("FStId") String fStId,
                                                                     @Field("name") String name,
                                                                     @Field("value") String value,
                                                                     @Field("sdate") String sdate,
                                                                     @Field("jdate") String jdate);


        /**
         * 产地检疫工作记录单  动物AB,产品AB列表详情 需要进行处理
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<ResponseBody> getQroducingAreaProcessList(@Field("id") String id,
                                                             @Field("TableName") String tableName,
                                                             @Field("FStId") String fStId);

        /**
         * 动物产地工作记录单查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AH_AnimalOrigin> getAH_AnimalOriginDetilQuery(@Field("id") String id,
                                                                 @Field("TableName") String tableName,
                                                                 @Field("FStId") String fStId,
                                                                 @Field("name") String name,
                                                                 @Field("value") String value,
                                                                 @Field("sdate") String sdate,
                                                                 @Field("jdate") String jdate);

        /**
         * 乳用、种用产地检疫申报单
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<AnimRuZhongYongRecordBean> getAnimRuZhongYongRecordDetil(@Field("id") String id,
                                                                            @Field("TableName") String tableName,
                                                                            @Field("FStId") String fStId);

        /**
         * 乳用、种用产地检疫申报单查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AnimRuZhongYongRecordBean> getAnimRuZhongYongRecordDetilQuery(@Field("id") String id,
                                                                                 @Field("TableName") String tableName,
                                                                                 @Field("FStId") String fStId,
                                                                                 @Field("name") String name,
                                                                                 @Field("value") String value,
                                                                                 @Field("sdate") String sdate,
                                                                                 @Field("jdate") String jdate);


        /**
         * 蛋、胚胎、精液检疫工作记录单
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<AnimZhongDanRedordBeangai> getAnimZhongDanRecordDetil(@Field("id") String id,
                                                                         @Field("TableName") String tableName,
                                                                         @Field("FStId") String fStId);

        /**
         * 蛋、胚胎、精液检疫工作记录单查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AnimZhongDanRedordBean> getAnimZhongDanRecordDetilQuery(@Field("id") String id,
                                                                           @Field("TableName") String tableName,
                                                                           @Field("FStId") String fStId,
                                                                           @Field("name") String name,
                                                                           @Field("value") String value,
                                                                           @Field("sdate") String sdate,
                                                                           @Field("jdate") String jdate);

        /**
         * 检疫处理通知单列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<QuarantineDealListQueryBean> getQuarantineDealListQuery(@Field("id") String id,
                                                                           @Field("TableName") String tableName,
                                                                           @Field("FStId") String fStId,
                                                                           @Field("name") String name,
                                                                           @Field("value") String value,
                                                                           @Field("sdate") String sdate,
                                                                           @Field("jdate") String jdate);

        /**
         * 申报单、记录单的编号赋值
         */
        @FormUrlEncoded
        @POST("GetSBDBH.ashx")
        Observable<ShenbaoJiluBianhaoBean> getShenbaoJiluBianhaoBean(@Field("uid") String uid,
                                                                     @Field("lx") String type);


        /**
         * 记录单的检疫处理通知单编号
         */
        @FormUrlEncoded
        @POST("GetJYDBH.ashx")
        Observable<JianyiChuliTongzhihaobean> getJianyiChuliTongzhihaobean(@Field("uid") String uid);


        /**
         * 官方兽医签字和监督所电话
         */
        @FormUrlEncoded
        @POST("GetDHSY.ashx")
        Observable<ShouyiJiandusuoBean> getShouyiJiandusuoBean(@Field("uid") String uid);


        /**
         * 无害化收集点查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<V_APP_SJDRYXXQueryBean> getV_APP_SJDRYXXQueryBeanQuery(@Field("id") String id,
                                                                          @Field("TableName") String tableName,
                                                                          @Field("FStId") String fStId,
                                                                          @Field("name") String name,
                                                                          @Field("value") String value,
                                                                          @Field("sdate") String sdate,
                                                                          @Field("jdate") String jdate);

        /**
         * 无害化收集运输查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<V_APP_WHHSJRWSSQueryBean> getV_APP_WHHSJRWSSQueryBeanQuery(@Field("id") String id,
                                                                              @Field("TableName") String tableName,
                                                                              @Field("FStId") String fStId,
                                                                              @Field("name") String name,
                                                                              @Field("value") String value,
                                                                              @Field("sdate") String sdate,
                                                                              @Field("jdate") String jdate);

        /**
         * 无害化入场确认查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<V_APP_ZCSYXXQueryBean> getV_APP_ZCSYXXQueryBeanQuery(@Field("id") String id,
                                                                        @Field("TableName") String tableName,
                                                                        @Field("FStId") String fStId,
                                                                        @Field("name") String name,
                                                                        @Field("value") String value,
                                                                        @Field("sdate") String sdate,
                                                                        @Field("jdate") String jdate);

        /**
         * 无害化集中处理查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<V_APP_WHHCLZXQueryBean> getV_APP_WHHCLZXQueryBeanQuery(@Field("id") String id,
                                                                          @Field("TableName") String tableName,
                                                                          @Field("FStId") String fStId,
                                                                          @Field("name") String name,
                                                                          @Field("value") String value,
                                                                          @Field("sdate") String sdate,
                                                                          @Field("jdate") String jdate);

        /**
         * 无害化查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<WuHaiHuaCXbean> getWuHaiHuaCXbean(@Field("id") String id,
                                                     @Field("TableName") String tableName,
                                                     @Field("FStId") String fStId,
                                                     @Field("name") String name,
                                                     @Field("value") String value,
                                                     @Field("sdate") String sdate,
                                                     @Field("jdate") String jdate);

        /**
         * 出库管理列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<StoreChukuBean> getStoreChukuInfo(@Field("id") String id,
                                                     @Field("TableName") String tableName,
                                                     @Field("FStId") String fStId,
                                                     @Field("name") String name,
                                                     @Field("value") String value,
                                                     @Field("sdate") String sdate,
                                                     @Field("jdate") String jdate);

        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<StoreChukuBean> getStoreChukuLoad(@Field("id") String id,
                                                     @Field("TableName") String tableName,
                                                     @Field("FStId") String fStId,
                                                     @Field("name") String name,
                                                     @Field("value") String value,
                                                     @Field("sdate") String sdate,
                                                     @Field("jdate") String jdate);

        /**
         * 养殖户申报
         */
        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<YangzhihuDetailActivityBean> getyangzhiDetil(@Field("TableName") String TableName,
                                                                @Field("FStId") int FStId);

        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<ChuKuDeatilBean> getChuKuDetil(@Field("TableName") String TableName,
                                                  @Field("FStId") int FStId);
        /**
         * 乡镇站确认
         */
//        @FormUrlEncoded
//        @POST("GetWHHCX.ashx")
//        Observable<YangzhihuDetailActivityBean> getyangzhiDetil(@Field("TableName") String TableName,
//                                                                @Field("FStId") int FStId);

        /**
         * 带申报
         */
        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<AgencyDeclareDetilActivityBean> getdaishenbaoDetil(@Field("TableName") String TableName,
                                                                      @Field("FStId") int FStId);

        /**
         * 无害化进度查询
         */
        @FormUrlEncoded
        @POST("GetWHHJDCX.ashx")
        Observable<ChaxunJinduActuvityBean> getChaxunjindu(@Field("sj") String sj,
                                                           @Field("uid") int uid,
                                                           @Field("name") String name,
                                                           @Field("value") String value,
                                                           @Field("sdate") String sdate,
                                                           @Field("jdate") String jdate);

        /**
         * 动物A查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AnimAQueryListBean> getAnimAData(@Field("id") String id,
                                                    @Field("TableName") String tableName,
                                                    @Field("FStId") String fStId,
                                                    @Field("name") String name,
                                                    @Field("value") String value,
                                                    @Field("sdate") String sdate,
                                                    @Field("jdate") String jdate);

        /**
         * 动物B查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<AnimBQueryListBean> getAnimBData(@Field("id") String id,
                                                    @Field("TableName") String tableName,
                                                    @Field("FStId") String fStId,
                                                    @Field("name") String name,
                                                    @Field("value") String value,
                                                    @Field("sdate") String sdate,
                                                    @Field("jdate") String jdate);

        /**
         * 产品A查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<ProductionAListBean> getProductAData(@Field("id") String id,
                                                        @Field("TableName") String tableName,
                                                        @Field("FStId") String fStId,
                                                        @Field("name") String name,
                                                        @Field("value") String value,
                                                        @Field("sdate") String sdate,
                                                        @Field("jdate") String jdate);

        /**
         * 产品B查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<ProductBListBean> getProductBData(@Field("id") String id,
                                                     @Field("TableName") String tableName,
                                                     @Field("FStId") String fStId,
                                                     @Field("name") String name,
                                                     @Field("value") String value,
                                                     @Field("sdate") String sdate,
                                                     @Field("jdate") String jdate);

        /**
         * 检疫处理通知单查询列表
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<JianyiChuliTongzhidanListBean> getJianyiChuliQueryListBean(@Field("id") String id,
                                                                              @Field("TableName") String tableName,
                                                                              @Field("FStId") String fStId,
                                                                              @Field("name") String name,
                                                                              @Field("value") String value,
                                                                              @Field("sdate") String sdate,
                                                                              @Field("jdate") String jdate);

        /**
         * 动物检疫证明--产品A
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<Qua_AnimalProductsAListBean> getQua_AnimalProductsADetil(@Field("id") String id,
                                                                            @Field("TableName") String tableName,
                                                                            @Field("FStId") String fStId);

        /**
         * 动物检疫证明--产品B
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<Qua_AnimalProductsBListBean> getQua_AnimalProductsBDetil(@Field("id") String id,
                                                                            @Field("TableName") String tableName,
                                                                            @Field("FStId") String fStId);

        /**
         * 动物A检疫证明列表
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<AnimAlistBean> getAnimAlistBeanDetil(@Field("id") String id,
                                                        @Field("TableName") String tableName,
                                                        @Field("FStId") String fStId);

        /**
         * 动物B检疫证明列表
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<AnimBlistBean> getAnimBlistBeanDetil(@Field("id") String id,
                                                        @Field("TableName") String tableName,
                                                        @Field("FStId") String fStId);

        /**
         * 动物A检疫证明列表
         */
        @FormUrlEncoded
        @POST("GetCDJY.ashx")
        Observable<QuarantineProcessNotifListBean> getQuarantineProcessNotifListBeanDetil(@Field("id") String id,
                                                                                          @Field("TableName") String tableName,
                                                                                          @Field("FStId") String fStId);

        /**
         * 动物A检疫证明列表详情页
         */
        @FormUrlEncoded
        @POST("GetCDJYCLTZD.ashx")
        Observable<QuarantineProcessNotifListBeanDetil> getQuarantineProcessNotifBeanDetil2(@Field("TableName") String tableName,
                                                                                            @Field("FStId") String fStId);

        /**
         * 无害化 获取养殖场(户)的信息列表
         *
         * @param fstId 加载更多的标识
         * @param text  搜索内容
         * @param uid   用户id
         * @return
         */
        @FormUrlEncoded
        @POST("GetJGSYDSB.ashx")
        Observable<UserDetilListBean> searchUserDetil(@Field("FStId") String fstId,
                                                      @Field("txt") String text,
                                                      @Field("uid") String uid);

        /**
         * 获取收集点的列表
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetSJD.ashx")
        Observable<CollectionListBean> getCollectionLocationList(@Field("uid") String uid,
                                                                 @Field("FStId") String FsgId);

        /**
         * 上传图片
         *
         * @return
         */
        @FormUrlEncoded
        @POST("UplaodSBPicture.ashx")
        Observable<BaseMsgBean> uploadPic(@Field("imagePath") String imgName,
                                          @Field("pictureBase") String pictureContent,
                                          @Field("newid") String uuid,
                                          @Field("len") int length,
                                          @Field("bool") boolean isLast);

        /**
         * 上传图片
         *
         * @return
         */
        @Multipart
        @POST("UplaodSBPicture.ashx")
        Observable<BaseMsgBean> upload(@Part("imagePath") String imgName,
                                       @Part("pictureBase") RequestBody pictureContent,
                                       @Part("newid") String uuid,
                                       @Part("len") int length,
                                       @Part("bool") boolean isLast);

        /**
         * 监管兽医指派的列表 可指派人
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetZPJGSY.ashx")
        Observable<SupervisionVeterinarianListBean> getSupervisionVeterianList(@Field("uid") String uid);


        /**
         * 获取服务器时间
         *
         * @return
         */
        @GET("GetDate.ashx")
        Observable<TimeBean> getDate();

        /**
         * 产品B查询
         */
        @FormUrlEncoded
        @POST("GetCDJYCX.ashx")
        Observable<ZaiqianCheckQueryBean> getZaiqianCheckQueryBean(@Field("id") String id,
                                                                   @Field("TableName") String tableName,
                                                                   @Field("FStId") String fStId,
                                                                   @Field("name") String name,
                                                                   @Field("value") String value,
                                                                   @Field("sdate") String sdate,
                                                                   @Field("jdate") String jdate);


        /**
         * 宰前检查选择(屠宰场)
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetTZCNAME.ashx")
        Observable<SlaughterSpHouseBean> chooseSlaughter(@Field("uid") String uid,
                                                         @Field("tid") String tid,
                                                         @Field("zl") String zl,
                                                         @Field("name") String name,
                                                         @Field("value") String value,
                                                         @Field("FStId") String FStId);

        /**
         * 宰前检查选择(动物种类)
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetTZCNAME.ashx")
        Observable<SlaughterAnimTypeBean> getAnimaType(@Field("uid") String uid,
                                                       @Field("tid") String tid,
                                                       @Field("zl") String zl,
                                                       @Field("name") String name,
                                                       @Field("value") String value,
                                                       @Field("FStId") String FStId);

        /**
         * 宰前检查选择(查询列表内容)
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetTZCNAME.ashx")
        Observable<SlaughterChooseBean> getChooseListData(@Field("uid") String uid,
                                                          @Field("tid") String tid,
                                                          @Field("zl") String zl,
                                                          @Field("name") String name,
                                                          @Field("value") String value,
                                                          @Field("FStId") String FStId);

        /**
         * 宰前检查(屠宰检疫编号)
         *
         * @param uid
         * @return
         */
        @FormUrlEncoded
        @POST("GetTZBH.ashx")
        Observable<SlaghterNumberBean> getSlaughterNumberBean(@Field("uid") String uid);

        /**
         * 无害化获取动物种类
         */
        @GET("GetZL.ashx")
        Observable<AnimTypeListBean> getAnimType();

        @GET("")
        Observable<ResponseBody> loadPic(@Url String url);

        /**
         * 文件下载 通过resoponsebody 进行进度监听
         *
         * @param url
         * @return
         */
        @GET("")
        Call<ResponseBody> download(@Url String url);

//        /**
//         * 处理 动物AB  产品AB
//         * Contance 里面 PDA PDB ...
//         *
//         * @param Json_Image 湖南的是null
//         * @return
//         */
//        @FormUrlEncoded
//        @POST("HandlerUpLoad.ashx")
//        Observable<BaseMsg> processAnimAndProduct(@Field("Json_TabName") String Json_TabName,
//                                                  @Field("Json_Data") String Json_Data,
//                                                  @Field("Json_Image") String Json_Image,
//                                                  @Field("Json_UserID") String UserID);


        /**
         * 验证打印机的机器号
         *
         * @param MachineNumber 用户对话框输入的id  序列号
         * @param SerialNumber  查找的机器码id
         * @return
         */
        @FormUrlEncoded
        @POST("GetMachineNumber.ashx")
        Observable<BaseMsg> printCheck(@Field("Json_UserID") String UserID,
                                       @Field("MachineNumber") String MachineNumber,
                                       @Field("SerialNumber") String SerialNumber);


        @Multipart
        @POST("WebForm1.aspx")
        Observable<ResponseBody> uploadPic(
                @PartMap Map<String, RequestBody> maps,
                @Part("picture1") RequestBody body
//                @Part("pic\"; filename=\"A1.jpg") RequestBody body
        );

        @POST("WebForm1.aspx")
        Observable<ResponseBody> uploadPic(
//                @Part("name") String name,
                @Body RequestBody Body);

        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<KanYanbean> getKanYan(@Field("TableName") String TableName,
                                         @Field("FStId") int fstid);

        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<CollectionPointDealActivityBean> getJianChaDian(@Field("TableName") String tableName,
                                                                   @Field("FStId") int fstid);

        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<CollectionTransportDealActivityBean> getWuHaiHua(@Field("TableName") String tableName,
                                                                    @Field("FStId") int fstid);

        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<JiZhongChuLiDetilBean> getWuHaiHua1(@Field("TableName") String tableName,
                                                       @Field("FStId") int fstid);

        @FormUrlEncoded
        @POST("GetWHHCX.ashx")
        Observable<WuhaihuaDateBean> getWuHaiHua2(@Field("TableName") String tableName,
                                                  @Field("FStId") int fstid);


        /**
         * 未处理条数接口
         */
        @FormUrlEncoded
        @POST("GetWCLTS.ashx")
        Observable<NoDealWithBean> withoutDealMsg(@Field("uid") int uid,
                                                  @Field("json") String json);

//        /**
//         * 执法
//         */
//        //添加案件人员
//        @FormUrlEncoded
//        @POST("GetRYInfo.ashx")
//        Observable<BanshpeopleleixzActivityBean> getBanshipeople(
//                @Url String url, @Field("FSunitUstrId") String FSunitUstrId);


        /**
         * 执法
         */
        //添加案件人员
        @FormUrlEncoded
        @POST("GetRYInfo.ashx")
        Observable<BanshpeopleleixzActivityBean> getBanshipeople(@Field("FSunitUstrId") String FSunitUstrId);


        @FormUrlEncoded
        @POST("InsertAJInfo.ashx")
        Observable<BaseMsgBean> uploadCaseRegister(@Field("data") String date);

        /**
         * 现场检查笔录
         *
         * @param date
         * @return
         */
        @FormUrlEncoded
        @POST("InsertJCInfo.ashx")
        Observable<BaseMsgBean> xianchangbilu(@Field("data") String date);

        /**
         * 询问笔录
         *
         * @param date
         * @return
         */
        @FormUrlEncoded
        @POST("InsertXWInfo.ashx")
        Observable<BaseMsgBean> xunwenbilu(@Field("data") String date);

        //获取案件信息接口

        @FormUrlEncoded
        @POST("GetAJInfo.ashx")
        Observable<AnjiandengjiActivityBean> getCaseRegisteInfo(@Field("FSunitUstrId") String FSunitUstrId);

        //当场决定书
        @FormUrlEncoded
        @POST("InsertCFInfo.ashx")
        Observable<BaseMsgBean> uploadPunishmentBook(
                @Field("data") String data);


        @FormUrlEncoded
        @POST("GetQYXX.ashx")
        Observable<CommonSupervisionBean> getCompanyDetil(@Field("TableName") String tableName,
                                                          @Field("FStId") String fStId);


        @FormUrlEncoded
        @POST("UploadJDJCFF.ashx")
        Observable<BaseMsgBean> uploadCommonSupervision(@Field("uid") String uid,
                                                        @Field("type") String type,
                                                        @Field("json") String json,
                                                        @Field("myguid") String uuid);

        @FormUrlEncoded
        @POST("UploadTzcJDJC.ashx")
        Observable<BaseMsgBean> uploadDoubleRandom(
                @Field("uid") String uid,
                @Field("data") String data);


        @FormUrlEncoded
        @POST("Delete.ashx")
        Observable<BaseMsgBean> deleteYangzzhiChangSB(@Field("TableName") String tableName,
                                                      @Field("FStId") String fStId,
                                                      @Field("guid") String guid);
    }


    public static Observable<CaseRegisterBean> getCaseRegistDetilQuery(String id, String type, String fStId, String name, String value,
                                                                       String sdate, String jdate) {
        return getRequest().getCaseRegistDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<XianchangCheckBean> getXianChangDataQuery(String id, String type, String fStId, String name, String value,
                                                                       String sdate, String jdate) {
        return getRequest().getXianChangDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<XunWenListBean> getXunwenDataQuery(String id, String type, String fStId, String name, String value,
                                                                String sdate, String jdate) {
        return getRequest().getXunWenListBean(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ChufaListBean> getChufaDataQuery(String id, String type, String fStId, String name, String value,
                                                              String sdate, String jdate) {
        return getRequest().getChufaListData(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<BreedingRecordData> getBreedingListDetilQuery(String id, String type, String fStId, String name, String value,
                                                                           String sdate, String jdate) {
        return getRequest().getBreedingListDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //BreedingDetailshowBean
    public static Observable<BreedingDetailshowBean.DataListBean> getBreedingDetilQuery1(String sfstid, String tableName) {
        return
                getRequest().getBreedingDetilQuery2(sfstid, tableName)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BreedingRecordData> getMedicineHandle(String id, String type, String fStId, String name, String value,
                                                                   String sdate, String jdate) {
        return getRequest().getBreedingListDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BreedingDetailshowBean> getBreedingDetail1(String tablename, String id) {
        return getRequest().getBreedingDetail22(tablename, id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BreedingRecordData> getMedicineProduce(String id, String type, String fStId, String name, String value,
                                                                    String sdate, String jdate) {
        return getRequest().getMedicineProduce(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UserRegisteAddressBean> getRegisteAddress() {
        return getRequest().getRegisteAddress()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BreedingRecordData> getSiliaoProduce(String id, String type, String fStId, String name, String value,
                                                                  String sdate, String jdate) {
        return getRequest().getSiliaoProduce(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BreedingRecordData> getPartPlace(String id, String type, String fStId, String name, String value,
                                                              String sdate, String jdate) {
        return getRequest().getPartPlace(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<BreedingRecordData> getSlaughterActivityPresenter(String id, String type, String fStId, String name, String value,
                                                                               String sdate, String jdate) {
        return getRequest().getBreedingListDetilQuery2(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BreedingRecordData> getAninalTreateData(String id, String type, String fStId, String name, String value,
                                                                     String sdate, String jdate) {
        return getRequest().getAninalTreateData(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BreedingRecordData> getHarmlesshandle(String id, String type, String fStId, String name, String value,
                                                                   String sdate, String jdate) {
        return getRequest().getHarmlesshandle(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uploadAnJian(String data) {
        return getRequest().uploadCaseRegister(data)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> xianchangbilu(String data) {
        return getRequest().xianchangbilu(data)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> xunwenbilu(String data) {
        return getRequest().xunwenbilu(data)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ResponseBody> downPrintImg(String url) {
        return getRequest().downPrintImg(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uploadCommonSupervision(String uid, String type, String json, String uuid) {
        return getRequest().uploadCommonSupervision(uid, type, json, uuid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uploadDoubleRandom(String uid, String json) {
//        String URL1 = URL.substring(0, URL.indexOf("HtmlAshx"));
        return getRequest()
                .uploadDoubleRandom(uid, json)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }


    //    public static Observable<NoDealWithBean> noDealwithInfos(int uid, String json) {
//        return getRequest().noDealwithInfos(uid, json)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
    public static Observable<CommonSupervisionBean> getCompanyDetil(String tableName, String fStId) {
        return getRequest().getCompanyDetil(tableName, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<NoDealWithBean> withoutDealMsg(int uid, String json) {
        return getRequest().withoutDealMsg(uid, json)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<BanshpeopleleixzActivityBean> getBanshipeople(String fSunitUstrId) {
        return getRequest().getBanshipeople(fSunitUstrId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnjiandengjiActivityBean> getCaseRegisteInfo(String fSunitUstrId) {
        return getRequest().getCaseRegisteInfo(fSunitUstrId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<HomeBean> getPermissionList(String uid, String rid) {
        return getRequest().getPermissionList(uid, rid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uploadNew(RequestBody body) {
        return getRequest().uploadNew(body).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uploadFile(RequestBody body) {
        return getRequest().uploadFile(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ResponseBody> uploadPic(Map<String, RequestBody> maps,
                                                     RequestBody body) {
        return getRequest().uploadPic(maps, body).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ResponseBody> uploadPic(
            RequestBody body) {
        return getRequest().uploadPic(body).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<CollectionTransportDealActivityBean> getWuHaiHua(String TableName, int FStId) {
        return getRequest().getWuHaiHua(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<WuhaihuaDateBean> getWuHaiHua2(String TableName, int FStId) {
        return getRequest().getWuHaiHua2(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<JiZhongChuLiDetilBean> getWuHaiHua1(String TableName, int FStId) {
        return getRequest().getWuHaiHua1(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<CollectionPointDealActivityBean> getJianChaDian(String TableName, int FStId) {
        return getRequest().getJianChaDian(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static void download(String url, RetrofitCallback callback) {
        Call<ResponseBody> call = getRequest().download(url);
        call.enqueue(callback);
//        getRequest().download(url).enqueue(callback);
    }

    public static Observable<BaseMsgBean> checkApkVersion(String version) {
        return getRequest().checkApkVersion(version)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> checkApkVersion(String userType, String version) {
        return getRequest().checkApkVersion(userType, version)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> checkDbVersion(String version) {
        return getRequest().checkDbVersion(version)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UserBean> login(String userName, String pwd) {
        return getRequest().login(userName, pwd)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uploadLoginInfo(String uid, String json, String type) {
        return getRequest().uploadLoginInfo(uid, json, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<TimeBean> getDate() {
        return getRequest().getDate()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UserDetilBean> getUserDetil(String id, String type) {
        return getRequest().getUserDetil(id, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<WHHChukuBean> getWHHChukuu(int i, int fSTid) {
        return getRequest().getWHHChuku(i, fSTid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<TongzhiGGBean> getTongzhi(String fid, String fsRoleId, String tableName,
                                                       String tid) {
        return getRequest().getTongzhi(fid, fsRoleId, tableName, tid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<WHHChukuFirstBean> getWHHChukuu1(int i, int fSTid) {
        return getRequest().getWHHChuku1(i, fSTid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<WHHZhiPaiBean> getWHHZhiPai(int uid) {
        return getRequest().getWHHZhiPai(uid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<WHHZhiPaiChooseBean> getZhiPaiChoose(String uid, String value, String fstId) {
        return getRequest().getZhiPaiChoose(uid, value, fstId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<WHHChuKuChooseBean> getWHHChukuChoose(int uid, String fstId) {
        return getRequest().getWHHChukuChoose(uid, fstId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<GuarantineDeclareListDetilBean> getGuarantineDeclareListDetil(String id, String type, String fStId) {
        return getRequest().getGuarantineDeclareListDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimAProcessUserDetilBean> getAnimAProcessDetil1(String id, String type, String fStId) {
        return getRequest().getAnimAProcessDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimAProcessUserDetilBean> getAnimAProcessDetil12(String type, String fStId) {
        return getRequest().getAnimAProcessDetil2(type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<DeclareNewstListBean> getDeclareNewstListBean(String id, String type, String sftid, String name, String value,
                                                                           String sdate, String jdate) {
        return getRequest().getDeclareNewstListBean(id, type, sftid, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<VerficationListBean> getVeficationLocaBean(String id, String type, String fstid, String name, String value,
                                                                        String sdate, String jdate) {
        return getRequest().getVeficationLocaBean(id, type, fstid, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AgencyDeclareListBean> getagencyDeclareListBean(String id, String type, String fstid, String name, String value, String
            sdate, String jdate) {
        return getRequest().getagencyDeclareListBean(id, type, fstid, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimalQuarantineActivityBean> getGuarantineDeclareListDetilQuery(String id, String type, String fStId, String name, String value,
                                                                                              String sdate, String jdate) {
        return getRequest().getGuarantineDeclareListDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<FaramDeclareListBean> getFaramDeclareListBean(String id, String type, String fStId, String name, String value, String sdate, String jdate) {
        return getRequest().getFaramDeclareListBean(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<RuChangChaYanQueryBean> getRuChangChaYanQueryBean(String id, String type, String fStId, String name, String value, String sdate, String jdate) {
        return getRequest().getRuChangChaYanQueryBean(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<RuChangChaXunBean> getRuchangQuery(String eNo) {
        return getRequest().getRuchangQuery(eNo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<AnimProcessListBean> getAnimProcessListDetil(String id, String type, String fStId) {
        return getRequest().getAnimProcessListDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimProcessListBean> getAnimProcessListDetilQuery(String id, String type, String fStId, String name, String value,
                                                                               String sdate, String jdate) {
        return getRequest().getAnimProcessListDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<QroducingAreaProcessListBean> getQroducingAreaProcessList(String id, String type, String fStId) {
        return baseGetHomeList(id, type, fStId, QroducingAreaProcessListBean.class);
    }


    public static Observable<ProductA_BBeanListGai> getProductA_BBeanListGaiList(String id, String type, String fStId) {
        return baseGetHomeList(id, type, fStId, ProductA_BBeanListGai.class);
    }

    public static Observable<AnimZhongDanRedordBeangai> getAnimZhongDanRedordList(String id, String type, String fStId) {
        return baseGetHomeList(id, type, fStId, AnimZhongDanRedordBeangai.class);
    }


    private static <T extends BaseMsg> Observable<T> baseGetHomeList(String id, String type, String fStId, Class<T> clazz) {
        return getRequest().getQroducingAreaProcessList(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<ResponseBody, Observable<T>>() {
                    @Override
                    public Observable<T> call(ResponseBody responseBody) {
                        String result = "";
                        try {
                            result = responseBody.string();
                        } catch (IOException e) {
                            throw new NullPointerException("获取数据失败");
                        }
                        return Observable.just(new Gson().fromJson(result, clazz));
//                        return Observable.just(new Gson().fromJson(result, new TypeToken<T>() {}.getType()));
                    }
                });
    }

    public static Observable<AH_AnimalOrigin> getAH_AnimalOriginDetilQuery(String id, String type, String fStId, String name, String value,
                                                                           String sdate, String jdate) {
        return getRequest().getAH_AnimalOriginDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimRuZhongYongRecordBean> getAnimRuZhongYongRecordDetil(String id, String type, String fStId) {
        return getRequest().getAnimRuZhongYongRecordDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimRuZhongYongRecordBean> getAnimRuZhongYongRecordDetilQuery(String id, String type, String fStId, String name, String value,
                                                                                           String sdate, String jdate) {
        return getRequest().getAnimRuZhongYongRecordDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimZhongDanRedordBeangai> getAnimZhongDanRecordDetil(String id, String type, String fStId) {
        return getRequest().getAnimZhongDanRecordDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimZhongDanRedordBean> getAnimZhongDanRecordDetilQuery(String id, String type, String fStId, String name, String value,
                                                                                     String sdate, String jdate) {
        return getRequest().getAnimZhongDanRecordDetilQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<QuarantineDealListQueryBean> getQuarantineDealListQuery(String id, String type, String fStId, String name, String value,
                                                                                     String sdate, String jdate) {
        return getRequest().getQuarantineDealListQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<V_APP_SJDRYXXQueryBean> getV_APP_SJDRYXXQueryBeanQuery(String id, String type, String fStId, String name, String value,
                                                                                    String sdate, String jdate) {
        return getRequest().getV_APP_SJDRYXXQueryBeanQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ShenbaoJiluBianhaoBean> getShenbaoJiluBianhaoBean(String id, String type) {
        return getRequest().getShenbaoJiluBianhaoBean(id, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<JianyiChuliTongzhihaobean> getJianyiChuliTongzhihaobean(String id) {
        return getRequest().getJianyiChuliTongzhihaobean(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ShouyiJiandusuoBean> getShouyiJiandusuoBean(String id) {
        return getRequest().getShouyiJiandusuoBean(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<V_APP_WHHSJRWSSQueryBean> getV_APP_WHHSJRWSSQueryBeanQuery(String id, String type, String fStId, String name, String value,
                                                                                        String sdate, String jdate) {
        return getRequest().getV_APP_WHHSJRWSSQueryBeanQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<V_APP_ZCSYXXQueryBean> getV_APP_ZCSYXXQueryBeanQuery(String id, String type, String fStId, String name, String value,
                                                                                  String sdate, String jdate) {
        return getRequest().getV_APP_ZCSYXXQueryBeanQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<V_APP_WHHCLZXQueryBean> getV_APP_WHHCLZXQueryBeanQuery(String id, String type, String fStId, String name, String value,
                                                                                    String sdate, String jdate) {
        return getRequest().getV_APP_WHHCLZXQueryBeanQuery(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<WuHaiHuaCXbean> getWuHaiHuaCXbean(String id, String type, String fStId, String name, String value,
                                                               String sdate, String jdate) {
        return getRequest().getWuHaiHuaCXbean(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<StoreChukuBean> getStoreChukuInfo(String id, String type, String fStId, String name, String value,
                                                               String sdate, String jdate) {
        return getRequest().getStoreChukuInfo(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<StoreChukuBean> getStoreChukuload(String id, String type, String fStId, String name, String value,
                                                               String sdate, String jdate) {
        return getRequest().getStoreChukuLoad(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //养殖场申报
    public static Observable<YangzhihuDetailActivityBean> getyangzhiDetil(String TableName, int FStId) {
        return getRequest().getyangzhiDetil(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<ChuKuDeatilBean> getChuKuDetil(String TableName, int FStId) {
        return getRequest().getChuKuDetil(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    // 养殖场申报数据删除
    public static Observable<BaseMsgBean> deleteYangzhiChangSB(String TableName, String FStId, String guid) {
        return getRequest().deleteYangzzhiChangSB(TableName, FStId, guid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //代申报
    public static Observable<AgencyDeclareDetilActivityBean> getdaishenbaoDetil(String TableName, int FStId) {
        return getRequest().getdaishenbaoDetil(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //现场勘查
    public static Observable<KanYanbean> getKanYan(String TableName, int FStId) {
        return getRequest().getKanYan(TableName, FStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //无害化进度
    public static Observable<ChaxunJinduActuvityBean> getChaxunjindu(String sj, int uid, String name, String value,
                                                                     String sdate, String jdate) {
        return getRequest().getChaxunjindu(sj, uid, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Qua_AnimalProductsAListBean> getQua_AnimalProductsADetil(String id, String type, String fStId) {
        return getRequest().getQua_AnimalProductsADetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Qua_AnimalProductsBListBean> getQua_AnimalProductsBDetil(String id, String type, String fStId) {
        return getRequest().getQua_AnimalProductsBDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<HarmlessListBean> getHarmlessList(String id, String type, String fStId) {
        return getRequest().getHarmlessList(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<HarmlessListDetilBean> getHarmlessListDetil(String id, String type, String fStId) {
        return getRequest().getHarmlessListDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<HarmlessShujiDetilBean> getCeshiDetil(String id, String type, String fStId) {
        return getRequest().getCeshiDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimAProcessListBean> getAnimAProcessListBean(String id, String type, String fStId) {
        return baseGetHomeList(id, type, fStId, AnimAProcessListBean.class);
    }

    public static Observable<AnimaABProductABBean> getAnimaABProductABBean(String id, String type, String fStId) {
        return baseGetHomeList(id, type, fStId, AnimaABProductABBean.class);
    }

    public static Observable<AnimAlistBean> getAnimAlistBeanDetil(String id, String type, String fStId) {
        return getRequest().getAnimAlistBeanDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<QuarantineProcessNotifListBean> getQuarantineProcessNotifListBeanDetil(String id, String type, String fStId) {
        return getRequest().getQuarantineProcessNotifListBeanDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<QuarantineProcessNotifListBeanDetil> getQuarantineProcessNotifListBeanDetil2(String type, String fStId) {
        return getRequest().getQuarantineProcessNotifBeanDetil2(type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimBlistBean> getAnimBlistBeanDetil(String id, String type, String fStId) {
        return getRequest().getAnimBlistBeanDetil(id, type, fStId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uplaod(String json, String type, String uid, String uuid) {
        return getRequest().uploadData(json, type, uid, uuid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uplaodZhuce(String json, String uid, String type) {
        return getRequest().uploadZhuce(json, uid, type, null)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<BaseMsgBean> uploadPrint(String type, String id, String tname, String zt) {
        return getRequest().uploadPrint(type, id, tname, zt)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<SupervisionVeterinarianListBean> getSupervisionVeterianList(String uid) {
        return getRequest().getSupervisionVeterianList(uid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UserDetilListBean> searchUserDetil(String fstId, String text, String uid) {
        return getRequest().searchUserDetil(fstId, text, uid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<CollectionListBean> getCollectionLocationList(String uid, String fstid) {
        return getRequest().getCollectionLocationList(uid, fstid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimTypeListBean> getAnimType() {
        return getRequest().getAnimType()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<BaseMsgBean> uploadPic(String imgName, String pictureContent,
                                                    String uuid, int length, boolean isLast) {
        return getRequest().uploadPic(imgName, pictureContent, uuid, length, isLast)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ResponseBody> loadPic(String imgName) {
        return getRequest().loadPic(imgName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AnimRuZhongYongRecordListBean> getAnimRuZhongYongRecordList(String id, String type, String fStId) {
        return baseGetHomeList(id, type, fStId, AnimRuZhongYongRecordListBean.class);
    }

    //    public static Observable<BaseMsgBean> uploadPic(String imgName,RequestBody pictureContent,
//                                                           String uuid,int length,boolean isLast) {
//        return getRequest().upload(imgName,pictureContent,uuid,length,isLast)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
    public static Observable<AnimAQueryListBean> getAnimAListData(String id, String type, String fStId, String name, String value, String sdate, String jdate) {
        return getRequest().getAnimAData(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Object> uploadModify() {
        return getRequest().uploadModify()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public static Observable<AnimBQueryListBean> getAnimBListData(String id, String type, String fStId, String name, String value,
                                                                  String sdate, String jdate) {
        return getRequest().getAnimBData(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ProductionAListBean> getProductAListData(String id, String type, String fStId, String name, String value,
                                                                      String sdate, String jdate) {
        return getRequest().getProductAData(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ProductBListBean> getProductBListData(String id, String type, String fStId, String name, String value,
                                                                   String sdate, String jdate) {
        return getRequest().getProductBData(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ZaiqianCheckQueryBean> getZaiqianCheckQueryBean(String id, String type, String fStId, String name, String value,
                                                                             String sdate, String jdate) {
        return getRequest().getZaiqianCheckQueryBean(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<JianyiChuliTongzhidanListBean> getJianyiChuliQueryListBean(String id, String type, String fStId, String name, String value,
                                                                                        String sdate, String jdate) {
        return getRequest().getJianyiChuliQueryListBean(id, type, fStId, name, value, sdate, jdate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<SlaughterSpHouseBean> getSlaughterSpData(String uid, String tid, String zl, String name, String value, String fstid
    ) {
        return getRequest().chooseSlaughter(uid, tid, zl, name, value, fstid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<SlaughterAnimTypeBean> getAnimaTypeData(String uid, String tid, String zl, String name, String value, String fstid) {
        return getRequest().getAnimaType(uid, tid, zl, name, value, fstid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<SlaughterChooseBean> getChooseDataList(String uid, String tid, String zl, String name, String value, String fstid) {
        return getRequest().getChooseListData(uid, tid, zl, name, value, fstid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<SlaghterNumberBean> getSlaughterNumberData(String uid) {
        return getRequest().getSlaughterNumberBean(uid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //注册的地址
    public static Observable<AdressBean> getAdress() {
        return getRequest().getAdress()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //兽药经营注册上传
    public static Observable<RegisteMsgBean> UploadFF(String json, int uid, String type, String myguid) {
        return getRequest().UploadFF(json, uid, type, myguid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //
    public static Observable<WeizhiBean> upWeizhi(String uid, String longitude, String latitude, String errorMsg) {
        return getRequest().upWeizhi(uid, longitude, latitude, errorMsg)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<CommonSupervisionQyBean> daySupervise(String fstid, String fsuid, String tableName, String text, String tid) {
        return getRequest().daySupervise(fstid, fsuid, tableName, text, tid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<FadingFaguiBean> getLaw(String text) {
        return getRequest().law(text)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


/**
 * 执法案将登记
 */
    //获取办案人员
//    public static Observable<BanshpeopleleixzActivityBean> getBanshipeople(String url,String FSunitUstrId) {
//        return getRequest().getBanshipeople(url,FSunitUstrId)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
    //上传
//    public static Observable<BaseMsgBean> uploadCaseRegister(String url,String date) {
//        return getRequest().uploadCaseRegister(url, date)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

}
