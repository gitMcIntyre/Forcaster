package com.dmcintyre.forcaster;

import android.os.RemoteException;
import android.util.Log;

import com.dmcintyre.mylib.Forecast;
import com.dmcintyre.mylib.IForecastListener;
import com.dmcintyre.mylib.IForecastService;

import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceImpl extends IForecastService.Stub {

    private void log(String message) {
        Log.v("WeatherServiceImpl", message);
    }

    private static final String API_KEY = "7ed411e46cd0aeee5a2912d6776ad447";
    private static final String API_BASE_URL = "https://api.darksky.net/forecast/" + API_KEY + "/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private volatile IDarkSky service;

    public WeatherServiceImpl() {
    }

    @Override
    public Forecast getForecast() throws RemoteException {
        int temp = new Random().nextInt(10);
        Forecast f = new Forecast(new Long(temp).toString());
        return f;
    }

    @Override
    public void getForcastAsync(IForecastListener listener) throws RemoteException {
        service = retrofit.create(IDarkSky.class);

        final IForecastListener l = listener;
        Call<Forecast> call = service.getForecast(42.4863, -83.1444);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                try {

                    //android.os.Debug.waitForDebugger();
                    ResponseBody errorBody = response.errorBody();
                    Forecast fc = response.body();
                    if (errorBody == null && fc != null) {
                        l.handleResponse(fc);
                    } else {
                        Log.e("Forecaster", "Error getting forecast!");
                    }
                } catch (RemoteException e) {
                    Log.e("Forecaster", e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                android.os.Debug.waitForDebugger();
                Log.e("PdoApplication", "Error getting forecast!");
            }
        });


    }

    @Override
    public void exit() throws RemoteException {

    }
}
