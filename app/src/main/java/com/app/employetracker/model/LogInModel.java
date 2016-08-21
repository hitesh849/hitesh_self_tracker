package com.app.employetracker.model;

import com.app.employetracker.RetroInterface.RestInterface;
import com.app.employetracker.dao.CommonData;
import com.app.employetracker.utility.Constants;
import com.google.gson.JsonElement;

import org.byteclues.lib.model.BasicModel;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by admin on 07-08-2016.
 */

public class LogInModel extends BasicModel {

    public void doLogin(String userName, String password) {
        try {
            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Constants.BASE_URL).build();
            RestInterface restInterface = adapter.create(RestInterface.class);
            HashMap<String,String> jsonObj=new HashMap<String,String>();
            jsonObj.put("username",userName);
            jsonObj.put("password",password);
            restInterface.loginRequest(jsonObj, new Callback<CommonData>() {
                @Override
                public void success(CommonData obj, Response response) {
                    notifyObservers(obj);
                }

                @Override
                public void failure(RetrofitError error) {
                    notifyObservers(error);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
