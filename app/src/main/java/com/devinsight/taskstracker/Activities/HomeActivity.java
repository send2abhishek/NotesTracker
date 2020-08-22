package com.devinsight.taskstracker.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devinsight.taskstracker.ApiRequests.Models.HomeRouteRequestResponse;
import com.devinsight.taskstracker.R;
import com.devinsight.taskstracker.Services.AccountService;
import com.devinsight.taskstracker.Views.HomePageAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private HomePageAdapter adapter;
    private String Token;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView=findViewById(R.id.activity_home_recylerview);
        adapter=new HomePageAdapter(this);
        Token=getIntent().getStringExtra("TOKEN");
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.post(new AccountService.HomeActivityRequest(dialog,Token));
    }

        @Subscribe
    public void onHomePageDataFetched(AccountService.HomeActivityResponse response){

        if(response.HomeActivityRequestResponse.isSuccessful()){

            try {
                JSONArray jsonarray = new JSONArray(response.HomeActivityRequestResponse.body().string());

                Gson gson=new Gson();
                List<HomeRouteRequestResponse> list= Arrays.asList(gson.fromJson(jsonarray.toString(),HomeRouteRequestResponse[].class));
//
//
                adapter.AddDataToView(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
