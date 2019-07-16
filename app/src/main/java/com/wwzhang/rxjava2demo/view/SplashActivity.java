package com.wwzhang.rxjava2demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;

/**
 * Created by wwzhang on 2019-07-16
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setExitTransition(new Fade().setDuration(500));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this,RxDemoActivity.class));
                finish();
            }
        },3000);
    }

    @Override
    public void onBackPressed() {

    }
}
