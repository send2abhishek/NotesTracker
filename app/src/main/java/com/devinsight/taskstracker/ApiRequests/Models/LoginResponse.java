package com.devinsight.taskstracker.ApiRequests.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginResponse implements Parcelable {

    private String userId;
    private String username;
    private String email;
    private String country;
    private double phone;
    private String token;

    public LoginResponse(String userId, String username, String email,
                         String country, double phone, String token) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.country = country;
        this.phone = phone;
        this.token = token;
    }

    protected LoginResponse(Parcel in) {
        userId = in.readString();
        username = in.readString();
        email = in.readString();
        country = in.readString();
        phone = in.readInt();
        token = in.readString();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public double getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(country);
        dest.writeDouble(phone);
        dest.writeString(token);
    }


}
