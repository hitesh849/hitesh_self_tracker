package com.app.employetracker.viewControlers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.app.employetracker.R;

import org.byteclues.lib.model.BasicModel;
import org.byteclues.lib.view.AbstractFragmentActivity;

import java.util.Observable;

/**
 * Created by Hitesh kumawat on 19-08-2016.
 */

public class StatusActivity extends AbstractFragmentActivity implements View.OnClickListener {
    @Override
    protected void onCreatePost(Bundle savedInstanceState) {
        setContentView(R.layout.status_activity);
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
        int id=v.getId();
        if(id==R.id.llBackHeader)
        {
            onBackPressed();
        }
    }
}
