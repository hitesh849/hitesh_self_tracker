package com.app.employetracker.RetroInterface;

import com.app.employetracker.dao.CommonData;
import com.app.employetracker.dao.TrackingData;
import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by u on 5/12/2016.
 */
public interface RestInterface
{

    /*@Headers({"Content-Type:application/json"})
    @PUT("/user")
    public void SignRest(@Body HashMap<String, String> jsonObject, Callback<UserData> cb);*/

    @FormUrlEncoded
    @POST("/server/androidiemi.php")
    public void chackIMEINumber(@FieldMap HashMap<String, String> body, Callback<CommonData> cb);

    @FormUrlEncoded
    @POST("/server/http/androidgps.php")
    public void setOnLineTrack(@FieldMap HashMap<String, String> body, Callback<JsonElement> cb);

    @FormUrlEncoded
    @POST("/server/androiduser.php")
    void loginRequest(@FieldMap HashMap<String, String> body, Callback<CommonData> callback);



}
