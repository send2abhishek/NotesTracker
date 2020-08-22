package com.devinsight.taskstracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.devinsight.taskstracker.R;

public class MainActivity extends BaseActivity {

    private Handler handler=new Handler();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if(username==null && password==null){

                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(runnable,4000);
    }
}
