package com.health.healthbloom.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.health.healthbloom.R;
import com.health.healthbloom.User.Dashboard;
import com.health.healthbloom.User.Login;
import com.health.healthbloom.User.LoginOrSignup;

import pl.droidsonroids.gif.GifImageView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME = 2400;
    Animation dropdown;
    GifImageView startup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        dropdown = AnimationUtils.loadAnimation(this, R.anim.drop_down_anim);
        startup = findViewById(R.id.startup);

        startup.setAnimation(dropdown);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginOrSignup.class));
                finish();
            }
        },SPLASH_TIME);
    }
}