package com.devinsight.taskstracker.ApiRequests.Models;

public class HomeRouteRequestResponse {

    private String _id;
    private String name;
    private String email;
    private String country;

    public HomeRouteRequestResponse(String _id, String name, String email, String country) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.country = country;
    }



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }
}
