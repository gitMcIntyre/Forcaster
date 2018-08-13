package com.dmcintyre.mylib;

import android.os.Parcel;
import android.os.Parcelable;
import com.dmcintyre.mylib.CurrentForecast;

public class Forecast implements Parcelable {

    private String mTemp;
    float latitude;
    float longitude;
    String timezone;

    CurrentForecast currently;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public Forecast(Parcel source){
        readFromParcel(source);
    }

    public Forecast(String temp){
        this.mTemp = temp;
    }

    public float getmTemp(){
        return currently.getTemperature();
    }

    public CurrentForecast getCurrently() {
        return currently;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel in){
        latitude = in.readFloat();
        currently = in.readParcelable(CurrentForecast.class.getClassLoader());
        }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeFloat(latitude);
        out.writeParcelable(currently, i);
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel parcel) {
            return new Forecast(parcel);
        }

        @Override
        public Forecast[] newArray(int i) {
            return new Forecast[i];
        }
    };
}
