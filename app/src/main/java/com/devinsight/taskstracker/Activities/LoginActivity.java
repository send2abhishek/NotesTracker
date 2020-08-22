package com.devinsight.taskstracker.Activities;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends BaseActivity {


    private EditText username;
    private EditText password;
    private Button loginBtn;
    private Button registerBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.activity_login_username);
        password=findViewById(R.id.activity_login_Password);
        loginBtn=findViewById(R.id.activity_login_sign_btn);
        registerBtn=findViewById(R.id.activity_login_sign_up_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new AccountService.LoginRequestRequest(username.getText().toString().trim(),
                        password.getText().toString().trim(),dialog));
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }


    @Subscribe
    public void onLoginResponse(AccountService.LoginRequestResponse response){

        if(!response.didSucceed()){
            username.setError(response.getPropertyErrors("usernameEmpty"));
            password.setError(response.getPropertyErrors("passwordEmpty"));
        }
        else {
            if(response.Loginresponse.isSuccessful()){

                try {
                    JSONObject res = new JSONObject(response.Loginresponse.body().string());
                    Gson gson=new Gson();
                    LoginResponse loginResponse=gson.fromJson(res.toString(),LoginResponse.class);
                    Intent intent=new Intent(this,HomeActivity.class);
                    intent.putExtra("TOKEN",loginResponse.getToken());
                    startActivity(intent);
                    finish();



                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if(response.Loginresponse.raw().code()==401){
                try {
                    JSONObject res = new JSONObject(response.Loginresponse.errorBody().string());


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
