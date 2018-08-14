package com.dmcintyre.forcaster;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Remote weather service implementation.
 */
public class WeatherService extends Service {

    private void log(String message){
        Log.v("WeatherService", message);
    }

    public WeatherService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("Start command called");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("Binding");
        return new WeatherServiceImpl();
    }

}
