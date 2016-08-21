package com.app.employetracker.dao;

import java.io.Serializable;

/**
 * Created by Hitesh kumawat on 20-08-2016.
 */

public class TrackingData extends CommonData implements Serializable
{
    public String op;
    public String imei;
    public String lat;
    public String lng;
    public String altitude;
    public String angle;
    public String speed;
    public String params;
    public String timeStamp;
}

