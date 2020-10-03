package com.health.healthbloom.User.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.health.healthbloom.R;

public class LoginOrSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_signup);
    }

    public void openLogin(View view) {

        Intent intent = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.login_btn), "tran_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginOrSignup.this, pairs);
            startActivity(intent, options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    public void openSignup1(View view) {

        Intent intentSignup = new Intent(getApplicationContext(), Signup1.class);
        Pair[] pairsSignup = new Pair[1];
        pairsSignup[0] = new Pair<View, String>(findViewById(R.id.login_btn), "tran_signup");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginOrSignup.this, pairsSignup);
            startActivity(intentSignup, options.toBundle());
        } else {
            startActivity(intentSignup);
        }
    }
}