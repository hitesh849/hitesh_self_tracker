package com.app.employetracker.receiver;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.app.employetracker.database.DatabaseMgr;
import com.app.employetracker.model.HomeModel;
import com.app.employetracker.utility.Util;

import java.util.List;


/**
 * Created by phoosaram on 8/13/2015.
 */
public class LocationMgr implements LocationListener {
    private static Context context;
    private static LocationManager locationManager;
    private static LocationMgr instance;

    HomeModel homeModel = new HomeModel();

    public static LocationMgr getInstance(Context ctx) {
        context = ctx;
        if (instance == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            instance = new LocationMgr();
        }
        return instance;
    }

    public void getCurrentLocation() {
        Location currentLocation = null;
        try {
            locationManager.requestLocationUpdates(getBestProvider(locationManager), 0, 0, this);
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBestProvider(LocationManager locManager) {
        Criteria locationCritera = new Criteria();
        locationCritera.setAccuracy(Criteria.ACCURACY_FINE);
        locationCritera.setAltitudeRequired(false);
        locationCritera.setBearingRequired(false);
        locationCritera.setCostAllowed(true);
        locationCritera.setPowerRequirement(Criteria.POWER_LOW);
        return locManager.getBestProvider(locationCritera, true);
    }


    @Override
    public void onLocationChanged(Location location) {
        try {
            if (Util.isDeviceOnline())
                homeModel.setOnLineTrack(location);
            else
                DatabaseMgr.getInstance(context).saveLocationToDB(context, location);
            locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public boolean isLocationServiceEnabled(Context ctx) {
        boolean locationEnable = true;
        try {
            if (locationManager == null) {
                locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            }
            boolean isGPSEnabled = false;
            boolean isNetworkEnable = false;
            List<String> providers = locationManager.getAllProviders();
            if (providers.contains(LocationManager.GPS_PROVIDER)) {
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            }
            isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnable) {
                locationEnable = false;
                return locationEnable;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationEnable;
    }


}
