package edu.uga.countryquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {
    private String[] data;

    public Country(String[] data) {
        this.data = data;
    }

    protected Country(Parcel in) {
        data = in.createStringArray();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(data);
    }
}

