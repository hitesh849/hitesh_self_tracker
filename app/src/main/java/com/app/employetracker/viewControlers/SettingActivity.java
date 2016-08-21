package com.app.employetracker.viewControlers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.employetracker.R;
import com.app.employetracker.sharedPreferences.Config;

import org.byteclues.lib.model.BasicModel;
import org.byteclues.lib.view.AbstractFragmentActivity;

import java.util.Observable;

/**
 * Created by Hitesh kumawat on 20-08-2016.
 */

public class SettingActivity extends AbstractFragmentActivity implements View.OnClickListener {

    private LinearLayout llDeviceIMEI;
    private LinearLayout llTrackingInterval;
    private LinearLayout llChatInterval;
    private TextView txtDeviceImeiSetting;
    private TextView txtTrackingInterval;
    private TextView txtChatInterval;

    @Override
    protected void onCreatePost(Bundle savedInstanceState) {
        setContentView(R.layout.settings_activity);
        init();
    }

    private void init() {
        llDeviceIMEI = (LinearLayout) findViewById(R.id.llDeviceIMEI);
        llTrackingInterval = (LinearLayout) findViewById(R.id.llTrackingInterval);
        llChatInterval = (LinearLayout) findViewById(R.id.llChatInterval);
        txtDeviceImeiSetting = (TextView) findViewById(R.id.txtDeviceImeiSetting);
        txtTrackingInterval = (TextView) findViewById(R.id.txtTrackingInterval);
        txtChatInterval = (TextView) findViewById(R.id.txtChatInterval);
        llDeviceIMEI.setOnClickListener(this);
        llTrackingInterval.setOnClickListener(this);
        llChatInterval.setOnClickListener(this);
        txtTrackingInterval.setText(""+Config.getInterval(Config.KEY_TRACKING_INTERVAL)+" Sec");
        txtChatInterval.setText(""+Config.getInterval(Config.KEY_chat_INTERVAL)+" Sec");
        txtDeviceImeiSetting.setText(Config.getIMEINumber());

    }

    @Override
    protected BasicModel getModel() {
        return null;
    }

    @Override
    public void update(Observable observable, Object data) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.llDeviceIMEI) {

        } else if (id == R.id.llTrackingInterval) {
            inputDialog(getResources().getString(R.string.tracking_interval), "Tracking interval in seconds", Config.KEY_TRACKING_INTERVAL,txtTrackingInterval);
        } else if (id == R.id.llChatInterval) {
            inputDialog(getResources().getString(R.string.chat_interval), "Chat interval in seconds",Config.KEY_chat_INTERVAL,txtChatInterval);
        } else if (id == R.id.llBackHeader) {
            onBackPressed();
        }

    }

    private void inputDialog(String title, String msg, final String key,final TextView textView) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText input = new EditText(this);
        input.setPadding(10, 30, 10, 20);
        input.setHint(Html.fromHtml("<small>" + msg + "</small>"));
        input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String interval = input.getText().toString();
                textView.setText(interval+" "+"Sec");
                Config.setInterval(key,Integer.valueOf(interval));
                Config.savePreferences();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
