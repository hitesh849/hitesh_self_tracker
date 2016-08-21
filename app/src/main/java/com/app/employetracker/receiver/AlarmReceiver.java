package com.app.employetracker.receiver;

/**
 * Created by u on 7/5/2016.
 */

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.app.employetracker.database.DatabaseMgr;
import com.app.employetracker.utility.Constants;
import com.app.employetracker.viewControlers.HomeActivity;

import org.byteclues.lib.init.Env;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {

    private static LocationManager locationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        LocationMgr locationMgr = LocationMgr.getInstance(context);
        locationMgr.getCurrentLocation();
        Log.d("phoosa kutta","tatti khale");
    }
}