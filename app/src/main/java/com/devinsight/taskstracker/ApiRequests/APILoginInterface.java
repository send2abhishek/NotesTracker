package com.devinsight.taskstracker.ApiRequests;

import com.devinsight.taskstracker.ApiRequests.Models.LoginRequest;
import com.devinsight.taskstracker.ApiRequests.Models.RegisterRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APILoginInterface {


    @POST("/login")
    Call<ResponseBody> loginRequest(@Body LoginRequest request);

    @POST("/register")
    Call<ResponseBody> registerRequest(@Body RegisterRequest request);

    @GET("/home")
    Call<ResponseBody> getDetails(@Header("Authorization")String token);
}
