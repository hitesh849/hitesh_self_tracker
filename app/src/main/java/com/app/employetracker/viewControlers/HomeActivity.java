package com.app.employetracker.viewControlers;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.employetracker.R;
import com.app.employetracker.dao.CommonData;
import com.app.employetracker.model.HomeModel;
import com.app.employetracker.receiver.AlarmReceiver;
import com.app.employetracker.sharedPreferences.Config;
import com.app.employetracker.utility.Util;

import org.byteclues.lib.model.BasicModel;
import org.byteclues.lib.view.AbstractFragmentActivity;

import java.util.Observable;

import retrofit.RetrofitError;

/**
 * Created by Hitesh kumawat on 19-08-2016.
 */

public class HomeActivity extends AbstractFragmentActivity implements View.OnClickListener {


    private LinearLayout llStartTracking;
    private LinearLayout llStatus;
    private LinearLayout llSettingHome;
    private LinearLayout activity_main;
    private TextView txtStatusHome;
    private TextView txtStartTrackHome;
    private PendingIntent pendingIntent;
    private boolean isTrackerStart;
    String imei;
    public HomeModel homeModel = new HomeModel();

    @Override
    protected void onCreatePost(Bundle savedInstanceState) {
        setContentView(R.layout.home_activity);
        llStartTracking = (LinearLayout) findViewById(R.id.llStartTracking);
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
        llStatus = (LinearLayout) findViewById(R.id.llStatus);
        llSettingHome = (LinearLayout) findViewById(R.id.llSettingHome);
        txtStatusHome = (TextView) findViewById(R.id.txtStatusHome);
        txtStartTrackHome = (TextView) findViewById(R.id.txtStartTrackHome);
        llStartTracking.setOnClickListener(this);
        llStatus.setOnClickListener(this);
        llSettingHome.setOnClickListener(this);
        saveImeiNumber();
        if(Config.isTrackStatus())
        {
            txtStatusHome.setText("Tracking is stopped now");
            txtStartTrackHome.setText("Stop Tracking");
            trackerStart();
        }

    }

    private void saveImeiNumber() {

        if (Config.getIMEINumber() == null) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
            Config.setIMEINumber(imei);
            Config.savePreferences();
        } else {
            imei = Config.getIMEINumber();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        homeModel.chackIMEINumber(imei);
    }

    @Override
    protected BasicModel getModel() {
        return homeModel;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o != null && o instanceof CommonData) {
            CommonData commonData = (CommonData) o;
            if (commonData.replyStatus.equals("success")) {

            } else if (commonData.replyStatus.equals("error")) {
                alertDialog();
            }
        } else if (o != null && o instanceof RetrofitError) {
           // Util.showOKSnakBar(activity_main, getResources().getString(R.string.pls_try_again));
        }
    }

    public void trackerStart() {
        isTrackerStart = true;
        android.app.AlarmManager manager = (android.app.AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = Config.getInterval(Config.KEY_TRACKING_INTERVAL);
        Intent alarmIntent = new Intent(HomeActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(HomeActivity.this, 0, alarmIntent, 0);
        manager.cancel(pendingIntent);
        manager.setInexactRepeating(android.app.AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
   }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.llStartTracking) {
            if (isTrackerStart) {
                isTrackerStart = false;
                txtStatusHome.setText("Tracking is started now");
                txtStartTrackHome.setText("Start Tracking");
                Config.setTrackStatus(false);

            } else {
                txtStatusHome.setText("Tracking is stopped now");
                txtStartTrackHome.setText("Stop Tracking");
                Config.setTrackStatus(true);
                trackerStart();
            }
            Config.savePreferences();
        } else if (id == R.id.llStatus) {
            startActivity(new Intent(this, StatusActivity.class));
        } else if (id == R.id.llSettingHome) {
            startActivity(new Intent(this, SettingActivity.class));
        }
    }

    void alertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Tracker")
                .setCancelable(false)
                .setMessage("Please contact admin to add your device number\n" + imei)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        HomeActivity.this.onBackPressed();
                    }
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }
}
