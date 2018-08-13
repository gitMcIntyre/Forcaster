// IForecastListener.aidl
package com.dmcintyre.mylib;

import com.dmcintyre.mylib.Forecast;

oneway interface IForecastListener {

    void handleResponse(in Forecast forecast);
}
