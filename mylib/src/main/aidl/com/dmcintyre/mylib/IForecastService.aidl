// IMyWeatherService.aidl
package com.dmcintyre.mylib;

import com.dmcintyre.mylib.Forecast;
import com.dmcintyre.mylib.IForecastListener;

// A ForcastService provides weather information
interface IForecastService {

    Forecast getForecast();

    oneway void getForcastAsync(IForecastListener listener);

    void exit();
}
