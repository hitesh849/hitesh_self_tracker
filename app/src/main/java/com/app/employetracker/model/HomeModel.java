package com.app.employetracker.model;

import android.location.Location;

import com.app.employetracker.RetroInterface.RestInterface;
import com.app.employetracker.dao.CommonData;
import com.app.employetracker.dao.TrackingData;
import com.app.employetracker.sharedPreferences.Config;
import com.app.employetracker.utility.Constants;
import com.google.gson.JsonElement;

import org.byteclues.lib.model.BasicModel;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Hitesh kumawat on 19-08-2016.
 */

public class HomeModel extends BasicModel {

    RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Constants.BASE_URL).build();
    RestInterface restInterface = adapter.create(RestInterface.class);


    public void chackIMEINumber(String imei) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("imei", Config.getIMEINumber());

        restInterface.chackIMEINumber(hashMap, new Callback<CommonData>() {
            @Override
            public void success(CommonData obj, Response response) {
                notifyObservers(obj);
            }

            @Override
            public void failure(RetrofitError error) {
                notifyObservers(error);
            }
        });
    }

    public void setOnLineTrack(Location location) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("op", "gpsok");
        hashMap.put("imei", Config.getIMEINumber());
       // hashMap.put("imei", "b16a512b427aa8a");
        hashMap.put("lat", String.valueOf(location.getLatitude()));
        hashMap.put("lng", String.valueOf(location.getLongitude()));
        hashMap.put("altitude", "0");
        hashMap.put("angle", "0");
        hashMap.put("speed", "0");
        hashMap.put("params", "");

        restInterface.setOnLineTrack(hashMap, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement obj, Response response) {
                notifyObservers(obj);
            }

            @Override
            public void failure(RetrofitError error) {
                notifyObservers(error);
            }
        });
    }
}
