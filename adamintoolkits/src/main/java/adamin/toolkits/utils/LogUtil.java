package adamin.toolkits.utils;

import android.util.Log;

import adamin.toolkits.BuildConfig;


/**
 * Created by LiTao on 2015-12-11-11:12.
 * Company: QD24so
 * Email: 14846869@qq.com
 * WebSite: http://lixiaopeng.top
 */
public class LogUtil {
    private static final String LOG_TAG = "Adamin90Log";

    public static final void info(Class clazz, String info) {
        if (BuildConfig.DEBUG) {
            Log.i(LOG_TAG, ">>>>>>>>>>>" + clazz.getSimpleName() + " " + info);
        }
    }

    public static final void info(String tag, String info) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, info);
        }
    }

    public static final void error(Class clazz, String error, Throwable t) {
        if (BuildConfig.DEBUG) {
            Log.e(LOG_TAG, ">>>>>>>>>>>" + clazz.getSimpleName() + " " + error, t);
        }
    }

    public static final void error(Class clazz, String error) {
        if (BuildConfig.DEBUG) {
            Log.e(LOG_TAG, ">>>>>>>>>>>" + clazz.getSimpleName() + " " + error);
        }
    }

    public static final void debug(Class clazz, String info) {
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, ">>>>>>>>>>>" + clazz.getSimpleName() + " " + info);
        }
    }

}

