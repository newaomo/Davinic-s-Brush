/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.kaku.colorfulnews;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.kaku.colorfulnews.common.Constants;
import com.kaku.colorfulnews.di.component.ApplicationComponent;
import com.kaku.colorfulnews.di.component.DaggerApplicationComponent;
import com.kaku.colorfulnews.di.module.ApplicationModule;
import com.kaku.colorfulnews.greendao.DaoMaster;
import com.kaku.colorfulnews.greendao.DaoSession;
import com.kaku.colorfulnews.greendao.NewsChannelTableDao;
import com.kaku.colorfulnews.utils.MyUtils;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.turing.newaomo.davinsbrush.BuildConfig;
import com.turing.newaomo.davinsbrush.mydb.MyDBHelper;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;
import com.turing.newaomo.davinsbrush.utils.photo.listener.GlidePauseOnScrollListener;
import com.turing.newaomo.davinsbrush.utils.photo.loader.GlideImageLoader;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author 咖枯
 * @version 1.0 2016/05/20
 */
public class App extends Application {

    private static App INSTANCE;
    private static final String SHAREDPREFENCES_POST = "process_to_post";
    private SPPostUtils spPostUtils = null;
    public synchronized SPPostUtils getSpPostUtils(){
        if (spPostUtils == null){
            spPostUtils = new SPPostUtils(App.INSTANCE(),SHAREDPREFENCES_POST);
        }
        return spPostUtils;
    }

    //语音服务
    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility. createUtility(App.INSTANCE(), SpeechConstant. APPID + "=5a6be952" );
    }

    public static Context applicationContext;
    public static App INSTANCE() {
        return INSTANCE;
    }

    private void setInstance(App app) {
        setApplication(app);
    }

    private static void setApplication(App a) {
        App.INSTANCE = a;
    }


    private  ApplicationComponent mApplicationComponent;
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }
    private static Context sAppContext;
    private static DaoSession mDaoSession;

    public void initMyMethod(){
        //TODO 这里是添加一些默认属性的方法
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        MyDBHelper.init(getApplicationContext());
        initMyMethod();
        Fresco.initialize(this);
        MultiDex.install(this);
        setInstance(this);
        initSpeech();
        if (INSTANCE == null){
            INSTANCE = new App();
        }
        //建议在application中配置
        //设置主题
//        ThemeConfig theme = ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setCheckNornalColor(Color.parseColor("#000000"))
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, new GlideImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(new GlidePauseOnScrollListener(false, true))
                .build();
        GalleryFinal.init(coreConfig);

        initLeakCanary();
        initActivityLifecycleLogs();
        initStrictMode();
        initDayNightMode();
        KLog.init(BuildConfig.LOG_DEBUG);
        // 官方推荐将获取 DaoMaster 对象的方法放到 Application 层，这样将避免多次创建生成 Session 对象
        setupDatabase();
        initApplicationComponent();

    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }

    /**
     * release版本使用此方法
     */
    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

    private void initActivityLifecycleLogs() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                KLog.v("=========", activity + "  onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                KLog.v("=========", activity + "  onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                KLog.v("=========", activity + "  onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                KLog.v("=========", activity + "  onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                KLog.v("=========", activity + "  onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                KLog.v("=========", activity + "  onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                KLog.v("=========", activity + "  onActivityDestroyed");
            }
        });
    }

    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
//                            .penaltyDialog() // 弹出违规提示对话框
                            .penaltyLog() // 在logcat中打印违规异常信息
                            .build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }
    }

    private void initDayNightMode() {
        if (MyUtils.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
    }

    public static Context getAppContext() {
        return sAppContext;
    }

    // Fixme
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static NewsChannelTableDao getNewsChannelTableDao() {
        return mDaoSession.getNewsChannelTableDao();
    }

    public static boolean isHavePhoto() {
        return MyUtils.getSharedPreferences().getBoolean(Constants.SHOW_NEWS_PHOTO, true);
    }

}
