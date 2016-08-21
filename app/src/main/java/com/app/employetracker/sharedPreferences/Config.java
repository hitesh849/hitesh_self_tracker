package com.app.employetracker.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by phoosaram on 8/9/2016.
 */

public class Config {
    private static SharedPreferences preferences = null;
    private static SharedPreferences.Editor editor;
    public static String PREFERENCES_NAME = "dwaPreferences";
    public static String KEY_DURATION_TIME = "duration_time";
    public static String KEY_TRACKING_INTERVAL = "tracking_interval";
    public static String KEY_chat_INTERVAL = "chat_interval";
    public static String KEY_IMEI = "imei_number";
    public static String KEY_LOGIN_STAUS = "login_staus";
    public static String KEY_STATUS = "status";

    public static void init(Context mContext) {
        Config.preferences = mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        Config.editor = preferences.edit();
    }

    public static void clearPreferences() {
        editor.clear();
        savePreferences();
    }

    public static void savePreferences() {
        editor.commit();
    }

    public static void setLatLngCommiteTime(String time)
    {
        editor.putString(KEY_DURATION_TIME,time);
    }

    public static String getLatLngCommiteTime()
    {
        return  preferences.getString(KEY_DURATION_TIME,null);
    }


    public static int getInterval(String key)
    {
        return  preferences.getInt(key,10);
    }

    public static void setInterval(String key,int time)
    {
        editor.putInt(key,time);
    }


    public static String getIMEINumber()
    {
        return preferences.getString(KEY_IMEI,null);
    }

    public static void setIMEINumber(String imei)
    {
        editor.putString(KEY_IMEI,imei);

    }

    public static boolean getLoginStatus()
    {
        return preferences.getBoolean(KEY_LOGIN_STAUS,false);
    }

    public static void setLoginStatus(boolean status)
    {
        editor.putBoolean(KEY_LOGIN_STAUS,status);
    }




    public static boolean isTrackStatus()
    {
        return preferences.getBoolean(KEY_STATUS,false);
    }

    public static void setTrackStatus(boolean status)
    {
        editor.putBoolean(KEY_STATUS,status);
    }
}

