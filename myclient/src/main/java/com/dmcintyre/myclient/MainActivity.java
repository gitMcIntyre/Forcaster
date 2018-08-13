package com.dmcintyre.myclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dmcintyre.mylib.CurrentForecast;
import com.dmcintyre.mylib.Forecast;
import com.dmcintyre.mylib.IForecastListener;
import com.dmcintyre.mylib.IForecastService;


import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent("com.dmcintyre.forcaster.WeatherService").setPackage("com.dmcintyre.forcaster")
                , this
                , BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void getForecast(){
        final TextView t2 = (TextView) findViewById(R.id.text2);
        final TextView t3 = findViewById(R.id.text3);
        try {
            IForecastListener l = new IForecastListener.Stub() {
                @Override
                public void handleResponse(final Forecast forecast) throws RemoteException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Date date = new Date(forecast.getCurrently().getTime());
                            Locale locale = Locale.getDefault();
                            DateFormat weekdayNameFormat = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                weekdayNameFormat = new SimpleDateFormat("EEE", locale);
                                String weekday = weekdayNameFormat.format(date);
                                String abb =  weekday.substring(0,2)+"";
                            }
                            CurrentForecast cf = forecast.getCurrently();
                            t2.setText(Float.valueOf(cf.getTemperature()).toString());
                            t3.setText(cf.getSummary());
                        }
                    });

                }
            };
            if(service != null) {
                service.getForcastAsync(l);
            }

        } catch (RemoteException e) {
            Log.v("Forecaster", e.getMessage(), e);
        }

    }
    private IForecastService service;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = IForecastService.Stub.asInterface(iBinder);
        getForecast();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }


}
