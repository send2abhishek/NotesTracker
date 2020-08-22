package com.devinsight.taskstracker.ServiceRequests;

import android.widget.Toast;

import com.devinsight.taskstracker.ApiRequests.APIClient;
import com.devinsight.taskstracker.ApiRequests.APILoginInterface;
import com.devinsight.taskstracker.ApiRequests.Models.LoginRequest;
import com.devinsight.taskstracker.ApiRequests.Models.RegisterRequest;
import com.devinsight.taskstracker.Infrastructure.TaskTrackerApp;
import com.devinsight.taskstracker.Services.AccountService;
import com.squareup.otto.Subscribe;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveAccountService extends BaseServiceRequest {

    private APILoginInterface apiLoginInterface;
    public LiveAccountService(TaskTrackerApp trackerApp) {
        super(trackerApp);
    }

    @Subscribe
    public void onLoginRequest(final AccountService.LoginRequestRequest request){
        final AccountService.LoginRequestResponse response=new AccountService.LoginRequestResponse();

        if(request.username.isEmpty()){
            response.setPropertyErrors("usernameEmpty","Username can't be empty");
        }

        else if(request.Password.isEmpty()){
            response.setPropertyErrors("passwordEmpty","Password can't be empty");
        }
        else {

            if(response.didSucceed()){


                request.progressDialog.setTitle("please wait...");
                request.progressDialog.setMessage("Attempting To Login");
                request.progressDialog.show();
                apiLoginInterface = APIClient.getClient().create(APILoginInterface.class);
                Call<ResponseBody> serviceCall=apiLoginInterface.
                        loginRequest(new LoginRequest(request.username,request.Password));

                serviceCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> ApiResponse) {
                        response.Loginresponse=ApiResponse;
                        request.progressDialog.dismiss();
                        bus.post(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        request.progressDialog.dismiss();
                        Toast.makeText(trackerApp.getApplicationContext(),"Something Went Wrong", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }

        if(response.getpropertySize() > 0){
            bus.post(response);
        }
    }

    @Subscribe
    public void onRegistrationRequest(AccountService.RegistrationRequestRequest request){

        AccountService.RegistrationRequestResponse response=new AccountService.RegistrationRequestResponse();

        if(request.name.isEmpty()){

            response.setPropertyErrors("nameEmpty","Name can't empty");

        }

        else if(request.email.isEmpty()){
            response.setPropertyErrors("EmailEmpty","Email can't empty");
        }

        else if(request.password.isEmpty()){
            response.setPropertyErrors("passwordEmpty","Password can't empty");
        }
        else if(request.country.isEmpty()){
            response.setPropertyErrors("countryEmpty","Country can't empty");
        }

        else if(request.phone.isEmpty()){
            response.setPropertyErrors("phoneEmpty","Phone can't empty");
        }
        else {
            if(response.didSucceed()){


                request.progressDialog.setTitle("please wait...");
                request.progressDialog.setMessage("Attempting To Register Your Account");
                request.progressDialog.show();
                apiLoginInterface = APIClient.getClient().create(APILoginInterface.class);
                Call<ResponseBody> serviceCall=apiLoginInterface.registerRequest(new RegisterRequest(request.name,
                        request.email,request.password,request.country,Double.parseDouble(request.phone)));

                serviceCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> ApiResponse) {
                        response.RegistartionResponse=ApiResponse;
                        request.progressDialog.dismiss();
                        bus.post(response);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        request.progressDialog.dismiss();
                        Toast.makeText(trackerApp.getApplicationContext(),"Something Went Wrong", Toast.LENGTH_LONG).show();

                    }
                });

            }
        }

        if(response.getpropertySize() > 0){
            bus.post(response);
        }
    }

    @Subscribe
    public void onHomePageRequest(AccountService.HomeActivityRequest request){

        AccountService.HomeActivityResponse response=new AccountService.HomeActivityResponse();

        request.dialog.setTitle("please wait...");
        request.dialog.setMessage("Attempting To Fetch Account Details");
        request.dialog.show();
        apiLoginInterface = APIClient.getClient().create(APILoginInterface.class);
        Call<ResponseBody> serviceCall=apiLoginInterface.getDetails("Bearer "+request.Token);

        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> ApiResponse) {
                response.HomeActivityRequestResponse=ApiResponse;
                request.dialog.dismiss();
                bus.post(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                request.dialog.dismiss();
                Toast.makeText(trackerApp.getApplicationContext(),"Something Went Wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
}
