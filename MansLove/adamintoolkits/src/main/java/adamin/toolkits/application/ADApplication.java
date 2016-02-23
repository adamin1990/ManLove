package adamin.toolkits.application;

import android.app.Application;
import android.content.Context;

import adamin.toolkits.exception.ABCrashHandler;
import adamin.toolkits.log.Logger;
import adamin.toolkits.thread.ThreadPool;
import adamin.toolkits.utils.ABPrefsUtil;

public class ADApplication extends Application {

    private static ADApplication instance;

    public static ADApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger(); // 初始化日志工具
        initThreadPool(); // 初始化线程池
        initNetWorkSSLScheme(); // 初始化AINetWork中SSL Scheme
        initImageLoader(); // 初始化图片加载器
        initCrashHandler(); // 初始化程序崩溃捕捉处理
        initPrefs(); // 初始化SharedPreference
        initABActionbar(); // 初始化Actionbar配置
        // 初始化Http连接
//        initHttpConfig();
    }

    /**
     * 初始化线程池
     */
    protected void initThreadPool() {
        ThreadPool.initThreadPool(-1);
    }

    /**
     * 初始化AINetWork中SSL Scheme
     */
    protected void initNetWorkSSLScheme() {
    }

    /**
     * 初始化日志
     */
    protected void initLogger() {
        Logger.initLogger(null);
    }

    /**
     * 初始化图片加载器（子类需要重写）
     */
    protected void initImageLoader() {

    }

    /**
     * 初始化程序崩溃捕捉处理
     */
    protected void initCrashHandler() {
        ABCrashHandler.init(getApplicationContext());
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        ABPrefsUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    /**
     * 初始化Actionbar配置（如果要使用Actionbar，则需要子类重写）
     */
    protected void initABActionbar() {

    }

//    protected void initHttpConfig() {
//        ABHttpUtil.initHttpConfig(null, null);
//    }



}
