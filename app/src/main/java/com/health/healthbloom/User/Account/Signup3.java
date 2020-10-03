package com.health.healthbloom.User.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.health.healthbloom.R;

public class Signup3 extends AppCompatActivity {

    ImageView backBtn;
    TextView title;
    ScrollView scrollview;
    TextInputLayout phonenumber;
    CountryCodePicker countrycodepicker;
    Button signupbtn, existingUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);


        scrollview = findViewById(R.id.scrollview_signup3);
        phonenumber = findViewById(R.id.signup_phone_number);
        countrycodepicker = findViewById(R.id.country_code_picker);
        signupbtn = findViewById(R.id.signup_btn);
        existingUser = findViewById(R.id.existing_account_btn_signup3);
    }

    public void callVerifyOTPScreen(View view) {

        if (!phoneNumberValidation()) {
            return;
        }

        // Get data from previous activity
        String fullName = getIntent().getStringExtra("fullName");
        String userName = getIntent().getStringExtra("userName");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String gender = getIntent().getStringExtra("gender");
        String dob = getIntent().getStringExtra("dob");

        String userPhnNum = phonenumber.getEditText().getText().toString().trim();
        String phoneNum = "+" + countrycodepicker.getFullNumber() + userPhnNum;

        Intent intent = new Intent(getApplicationContext(), OtpVerification.class);

        // Passing data to the next activity
        intent.putExtra("fullName", fullName);
        intent.putExtra("userName", userName);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("gender", gender);
        intent.putExtra("dob", dob);
        intent.putExtra("phoneNum", phoneNum);

        // Transition Animations
        Pair[] pairs = new Pair[2];

        pairs[0] = new Pair<View, String>(scrollview, "layout_transition");
        pairs[1] = new Pair<View, String>(signupbtn, "proceed_btn");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup3.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    public void switch_to_login_form_signup3(View view) {

        Intent intentLoginPage = new Intent(getApplicationContext(), Login.class);

        // Transitions
        Pair[] loginPairs = new Pair[5];

        loginPairs[0] = new Pair<View, String>(backBtn, "back_btn_transition");
        loginPairs[1] = new Pair<View, String>(title, "title_transition");
        loginPairs[2] = new Pair<View, String>(signupbtn, "proceed_btn");
        loginPairs[3] = new Pair<View, String>(existingUser, "swap_form");
        loginPairs[4] = new Pair<View, String>(phonenumber, "tran_input_lower");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup3.this, loginPairs);
            startActivity(intentLoginPage, options.toBundle());
        } else {
            startActivity(intentLoginPage);
        }
    }

    private boolean phoneNumberValidation() {
        String userPhnNum = phonenumber.getEditText().getText().toString().trim();

        if (userPhnNum.isEmpty()) {
            phonenumber.setError("Field cannot be empty");
            return false;
        } else if (userPhnNum.length() < 10) {
            phonenumber.setError("Invalid Phone Number");
            return false;
        } else {
            phonenumber.setError(null);
            phonenumber.setErrorEnabled(false);
            return true;
        }
    }

    public void backSignup3(View view) {
        super.onBackPressed();
    }


}