package com.dmcintyre.forcaster;

import com.dmcintyre.mylib.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IDarkSky {
    @GET("{latitude},{longitude}")
    Call<Forecast> getForecast(@Path("latitude") double latitude, @Path("longitude") double longitude);
}
