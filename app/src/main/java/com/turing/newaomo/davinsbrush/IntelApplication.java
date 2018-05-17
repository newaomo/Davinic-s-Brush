package com.turing.newaomo.davinsbrush;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.turing.newaomo.davinsbrush.colorUi.util.SharedPreferencesMgr;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;
import com.turing.newaomo.davinsbrush.utils.photo.listener.GlidePauseOnScrollListener;
import com.turing.newaomo.davinsbrush.utils.photo.loader.GlideImageLoader;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by newao on 2018/2/3.
 */

public class IntelApplication extends Application {
    private static IntelApplication INSTANCE;
    private static final String SHAREDPREFENCES_POST = "process_to_post";
    private SPPostUtils spPostUtils = null;
    public synchronized SPPostUtils getSpPostUtils(){
        if (spPostUtils == null){
            spPostUtils = new SPPostUtils(IntelApplication.INSTANCE(),SHAREDPREFENCES_POST);
        }
        return spPostUtils;
    }

    //语音服务
    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility. createUtility(IntelApplication.INSTANCE(), SpeechConstant. APPID + "=5a6be952" );
    }

    public static Context applicationContext;
    public static IntelApplication INSTANCE() {
        return INSTANCE;
    }

    private void setInstance(IntelApplication app) {
        setIntelApplication(app);
    }

    private static void setIntelApplication(IntelApplication a) {
        IntelApplication.INSTANCE = a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        MultiDex.install(this);
        setInstance(this);
        initSpeech();
        if (INSTANCE == null){
            INSTANCE = new IntelApplication();
        }
        SharedPreferencesMgr.init(this, "derson");
        if(SharedPreferencesMgr.getInt("theme", 0) == 1) {
            setTheme(R.style.theme_2);
        } else {
            setTheme(R.style.theme_1);
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
    }
}
