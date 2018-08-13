package com.dmcintyre.mylib;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentForecast implements Parcelable{

    long time;
    String summary;
    String icon;
    float nearestStormDistance;
    float precipIntensity;
    float precipIntensityError;
    float precipProbability;
    String precipType;
    float temperature;
    float apparentTemperature;
    float dewPoint;
    float humidity;
    float windSpeed;
    float windBearing;
    float visibility;
    float cloudCover;
    float pressure;
    float ozone;

    public CurrentForecast(Parcel in){
        readFromParcel(in);
    }

    public long getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public float getNearestStormDistance() {
        return nearestStormDistance;
    }

    public float getPrecipIntensity() {
        return precipIntensity;
    }

    public float getPrecipIntensityError() {
        return precipIntensityError;
    }

    public float getPrecipProbability() {
        return precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getApparentTemperature() {
        return apparentTemperature;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindBearing() {
        return windBearing;
    }

    public float getVisibility() {
        return visibility;
    }

    public float getCloudCover() {
        return cloudCover;
    }

    public float getPressure() {
        return pressure;
    }

    public float getOzone() {
        return ozone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(time);
        parcel.writeFloat(temperature);
        parcel.writeString(icon);
        parcel.writeFloat(apparentTemperature);
        parcel.writeFloat(precipProbability);
        parcel.writeString(summary);
    }

    public void readFromParcel(Parcel in){
        time = in.readLong();
        temperature = in.readFloat();
        icon = in.readString();
        apparentTemperature = in.readFloat();
        precipProbability = in.readFloat();
        summary = in.readString();
    }

    public static final Creator<CurrentForecast> CREATOR = new Creator<CurrentForecast>() {
        @Override
        public CurrentForecast createFromParcel(Parcel parcel) {
            return new CurrentForecast(parcel);
        }

        @Override
        public CurrentForecast[] newArray(int i) {
            return new CurrentForecast[i];
        }
    };

}