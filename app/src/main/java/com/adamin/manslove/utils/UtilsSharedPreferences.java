package com.adamin.manslove.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



/**
 * @version V1.0
 * @Title: FQYSharedPreferencesUtils.java
 * @Package com.fqy.fqylibs.utils
 * @Description: TODO SharedPreferences 的工具类(抽象类)，get、set在子类中实现。
 * @author: Fang Qingyou
 * @date 2015年5月27日上午10:06:06
 */
public class UtilsSharedPreferences {

    private SharedPreferences preferences;
    private Editor edit;

    /**
     * 构造方法 默认sp 文件名为FQYData
     *
     * @param context
     */
    public UtilsSharedPreferences(Context context,String name) {
        preferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        edit = preferences.edit();
    }

    public void setChannel(String info){
        edit.putString("channel",info).commit();
    }
    public String getChannel(){
        return  preferences.getString("channel","");
    }
    public void setIsfirst(boolean first){
        edit.putBoolean("first",first).commit();

    }
    public boolean getIsFirst(){
        return preferences.getBoolean("first",true);
    }

}
