package com.app.employetracker.viewControlers;

import android.support.multidex.MultiDexApplication;

import com.app.employetracker.database.DatabaseMgr;
import com.app.employetracker.sharedPreferences.Config;

import org.byteclues.lib.init.Env;

/**
 * Created by Hitesh kumawat on 07-08-2016.
 */

public class EmployeTracker extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Env.appContext = this;
        Config.init(this);
        DatabaseMgr.getInstance(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_BACKGROUND || level == TRIM_MEMORY_UI_HIDDEN) {
            Env.APP_STATE = Env.applicationState.BACKGROUND;

        }
    }
}
