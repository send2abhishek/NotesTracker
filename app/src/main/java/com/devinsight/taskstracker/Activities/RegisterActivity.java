package com.devinsight.taskstracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.devinsight.taskstracker.ApiRequests.Models.ErrorHandler;
import com.devinsight.taskstracker.ApiRequests.Models.LoginResponse;
import com.devinsight.taskstracker.R;
import com.devinsight.taskstracker.Services.AccountService;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends BaseActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText country;
    private EditText phone;
    private Button regBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);

        name=findViewById(R.id.activity_reg_name);
        email=findViewById(R.id.activity_reg_username);
        password=findViewById(R.id.activity_reg_Password);
        country=findViewById(R.id.activity_reg_country);
        phone=findViewById(R.id.activity_reg_phone);
        regBtn=findViewById(R.id.activity_reg_sign_btn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new AccountService.RegistrationRequestRequest(name.getText().toString().trim(),
                        email.getText().toString().trim(),password.getText().toString().trim(),
                        phone.getText().toString().trim(),
                        country.getText().toString().trim(),
                        dialog
                        ));
            }
        });
    }

    @Subscribe
    public void onRegistrationRequest(AccountService.RegistrationRequestResponse response){

        if(!response.didSucceed()){

            name.setError(response.getPropertyErrors("nameEmpty"));
            email.setError(response.getPropertyErrors("EmailEmpty"));
            password.setError(response.getPropertyErrors("passwordEmpty"));
            phone.setError(response.getPropertyErrors("phoneEmpty"));
            country.setError(response.getPropertyErrors("countryEmpty"));

        }

        else {

            if(response.RegistartionResponse.raw().code()==201){

                try {
                    JSONObject res = new JSONObject(response.RegistartionResponse.body().string());
                    Gson gson=new Gson();
                    ErrorHandler registerResponse=gson.fromJson(res.toString(),ErrorHandler.class);
                    Toast.makeText(this,registerResponse.getMessage()+" Kindly Login",Toast.LENGTH_LONG).show();

//                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if(response.RegistartionResponse.raw().code()==409){
                try {
                    JSONObject res = new JSONObject(response.RegistartionResponse.errorBody().string());


                    Gson gson=new Gson();
                    ErrorHandler errorHandler=gson.fromJson(res.toString(),ErrorHandler.class);

                    Toast.makeText(this,errorHandler.getMessage(), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
