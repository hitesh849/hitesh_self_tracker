package com.app.employetracker.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.employetracker.R;


/**
 * Created by Hitesh kumawat on 05-03-2016.
 */
public class AppHeaderView extends RelativeLayout {


    public LinearLayout llBackHeader;
    public RelativeLayout rlHeaderNameLeft;
    public RelativeLayout rlHeaderNamecenter;
    public RelativeLayout rlHeaderNotification;
    public RelativeLayout rlHeaderLogout;
    public RelativeLayout rlHeaderReport;
    public TextView txtHeaderNamecenter;
    public ImageView imgToolbarNameLeft;
    public TextView txtToolbarNameLeft;
    public View leftHederSpaceLeftSide;

    public AppHeaderView(Context context) {
        super(context);
        init(context);
    }

    public AppHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        super.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        LayoutInflater.from(context).inflate(R.layout.app_header, this, true);
        this.llBackHeader = (LinearLayout) findViewById(R.id.llBackHeader);
        this.rlHeaderNameLeft = (RelativeLayout) findViewById(R.id.rlHeaderNameLeft);
        this.rlHeaderNamecenter = (RelativeLayout) findViewById(R.id.rlHeaderNamecenter);
        this.rlHeaderNotification = (RelativeLayout) findViewById(R.id.rlHeaderNotification);
        this.rlHeaderReport = (RelativeLayout) findViewById(R.id.rlHeaderReport);
        this.rlHeaderLogout = (RelativeLayout) findViewById(R.id.rlHeaderLogout);
        this.imgToolbarNameLeft = (ImageView) findViewById(R.id.imgToolbarNameLeft);
        this.txtToolbarNameLeft = (TextView) findViewById(R.id.txtToolbarNameLeft);
        this.txtHeaderNamecenter = (TextView) findViewById(R.id.txtHeaderNamecenter);
        this.leftHederSpaceLeftSide = (View) findViewById(R.id.LeftHederSpaceLeftSide);
        this.llBackHeader.setOnClickListener((OnClickListener) context);
        this.rlHeaderLogout.setOnClickListener((OnClickListener) context);
        this.rlHeaderNotification.setOnClickListener((OnClickListener) context);
        this.rlHeaderReport.setOnClickListener((OnClickListener) context);
    }

}