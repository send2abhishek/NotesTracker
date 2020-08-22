package com.devinsight.taskstracker.ApiRequests.Models;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String country;
    private double phone;

    public RegisterRequest(String name, String email, String password, String country, double phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.phone = phone;
    }
}
