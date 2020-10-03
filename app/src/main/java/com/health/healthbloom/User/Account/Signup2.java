package com.health.healthbloom.User.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.health.healthbloom.R;

public class Signup2 extends AppCompatActivity {

    // Variables Declaration
    TextView title, genderText, ageText;
    ImageView backBtn;
    Button next, existingUser;
    RadioGroup radioGrp;
    RadioButton selectedGender;
    DatePicker selectedAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        // Hooks
        title = findViewById(R.id.title_signup2);
        backBtn = findViewById(R.id.back_btn_signup2);
        next = findViewById(R.id.next_btn_signup2);
        existingUser = findViewById(R.id.existing_account_btn_signup2);
        genderText = findViewById(R.id.select_gender_text);
        ageText = findViewById(R.id.select_age_text);
        radioGrp = findViewById(R.id.radio_grp);

    }

    public void nextSignupScreen(View view) {

        if(!genderValidation()){
            return;
        }

        // Get data from previous activity
        String fullName = getIntent().getStringExtra("fullName");
        String userName = getIntent().getStringExtra("userName");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        selectedAge = findViewById(R.id.age);
        selectedGender = findViewById(radioGrp.getCheckedRadioButtonId());

        String gender = selectedGender.getText().toString();

        int day = selectedAge.getDayOfMonth();
        int month = selectedAge.getMonth();
        int year = selectedAge.getYear();

        String dob = day + "/" + month + "/" + year;

        Intent intent = new Intent(getApplicationContext(), Signup3.class);

        // Passing data to the next activity
        intent.putExtra("fullName", fullName);
        intent.putExtra("userName", userName);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("gender", gender);
        intent.putExtra("dob", dob);

        // Transition Animations
        Pair[] pairs = new Pair[6];

        pairs[0] = new Pair<View, String>(backBtn, "back_btn_transition");
        pairs[1] = new Pair<View, String>(title, "title_transition");
        pairs[2] = new Pair<View, String>(next, "proceed_btn");
        pairs[3] = new Pair<View, String>(existingUser, "swap_form");
        pairs[4] = new Pair<View, String>(ageText, "tran_input_lower");
        pairs[5] = new Pair<View, String>(genderText, "tran_input_upper");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup2.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void switch_to_login_form_signup2(View view) {

        Intent intentLoginPage = new Intent(getApplicationContext(), Login.class);

        // Transitions
        Pair[] loginPairs = new Pair[5];

        loginPairs[0] = new Pair<View, String>(backBtn, "back_btn_transition");
        loginPairs[1] = new Pair<View, String>(title, "title_transition");
        loginPairs[2] = new Pair<View, String>(next, "proceed_btn");
        loginPairs[3] = new Pair<View, String>(existingUser, "swap_form");
        loginPairs[4] = new Pair<View, String>(ageText, "tran_input_lower");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup2.this, loginPairs);
            startActivity(intentLoginPage, options.toBundle());
        } else {
            startActivity(intentLoginPage);
        }
    }

    private boolean genderValidation() {
        if (radioGrp.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void backSignup2(View view) {
        super.onBackPressed();
    }
}