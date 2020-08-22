package com.devinsight.taskstracker.Services;

import android.app.ProgressDialog;

import com.devinsight.taskstracker.Infrastructure.ServiceResponse;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class AccountService {


    private AccountService() {
    }


    public static class LoginRequestRequest {

        public String username;
        public String Password;
        public ProgressDialog progressDialog;

        public LoginRequestRequest(String username, String password, ProgressDialog progressDialog) {
            this.username = username;
            this.Password = password;
            this.progressDialog = progressDialog;
        }
    }


    public static class LoginRequestResponse extends ServiceResponse {

        public Response<ResponseBody> Loginresponse;
    }

    public static class RegistrationRequestRequest{

        public String name;
        public String email;
        public String password;
        public String phone;
        public String country;
        public ProgressDialog progressDialog;

        public RegistrationRequestRequest(String name, String email, String password,
                                          String phone, String country, ProgressDialog progressDialog) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.country = country;
            this.progressDialog = progressDialog;
        }
    }

    public static class RegistrationRequestResponse extends ServiceResponse {

        public Response<ResponseBody> RegistartionResponse;
    }

    public static class HomeActivityRequest{

        public ProgressDialog dialog;
        public String Token;
        public HomeActivityRequest(ProgressDialog dialog,String Token){
            this.dialog=dialog;
            this.Token=Token;
        }
    }

    public static class HomeActivityResponse extends ServiceResponse {

        public Response<ResponseBody> HomeActivityRequestResponse;
    }
}
