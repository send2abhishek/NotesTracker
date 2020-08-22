package com.devinsight.taskstracker.Activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.devinsight.taskstracker.Infrastructure.TaskTrackerApp;
import com.devinsight.taskstracker.Utils.BaseUtils;
import com.squareup.otto.Bus;


public class BaseActivity extends AppCompatActivity {

    private TaskTrackerApp trackerApp;
    protected Bus bus;
    private Boolean isRegisterWithBus=false;
    protected SharedPreferences preferences;
    protected String username=null;
    protected String password=null;
    protected String name=null;
    protected ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackerApp=(TaskTrackerApp)getApplication();
        bus=trackerApp.getBus();
        bus.register(this);
        isRegisterWithBus=true;
        preferences=getSharedPreferences(BaseUtils.SHARED_PREFERENCES, MODE_PRIVATE);
        username=preferences.getString(BaseUtils.USER_EMAIL,null);
        password=preferences.getString(BaseUtils.USER_PASSWORD,null);
        name=preferences.getString(BaseUtils.USER_NAME,"");
        dialog=new ProgressDialog(this);
        dialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(isRegisterWithBus){

            bus.unregister(this);
            isRegisterWithBus=false;
        }
    }
}
