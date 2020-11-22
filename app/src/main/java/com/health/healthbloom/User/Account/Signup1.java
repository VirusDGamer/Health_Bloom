package com.health.healthbloom.User.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.health.healthbloom.R;

public class Signup1 extends AppCompatActivity {

    // Variable Declaration
    TextView title;
    TextInputLayout fullName, username, email, userPassword, confirmationPassword;
    ImageView backBtn;
    Button next, existingUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        // Hooks.
            title = findViewById(R.id.title_signup1);
            backBtn = findViewById(R.id.back_btn_signup1);
            next = findViewById(R.id.next_btn_signup1);
            existingUser = findViewById(R.id.existing_account_btn_signup1);
            fullName = findViewById(R.id.user_full_name);
            username = findViewById(R.id.username_signup);
            userPassword = findViewById(R.id.user_password_signup);
            email = findViewById(R.id.email_address);
            confirmationPassword = findViewById(R.id.user_password_confirm_signup);
    }

    public void nextSignupScreen(View view) {

        Intent intent = new Intent(getApplicationContext(), Signup2.class);

        if (!fullNameValidation() | !userNameValidation() | !emailValidation() | !passwordValidation() | !passwordConfirmation()) {
            return;
        }


        // Data passing to next activity
        intent.putExtra("fullName", fullName.getEditText().getText().toString().trim());
        intent.putExtra("userName", userPassword.getEditText().getText().toString().trim());
        intent.putExtra("email", email.getEditText().getText().toString().trim());
        intent.putExtra("password", userPassword.getEditText().getText().toString().trim());

        // Transition Animations
        Pair[] pairs = new Pair[6];

        pairs[0] = new Pair<View, String>(backBtn, "back_btn_transition");
        pairs[1] = new Pair<View, String>(title, "title_transition");
        pairs[2] = new Pair<View, String>(next, "proceed_btn");
        pairs[3] = new Pair<View, String>(existingUser, "swap_form");
        pairs[4] = new Pair<View, String>(fullName, "tran_input_upper");
        pairs[5] = new Pair<View, String>(userPassword, "tran_input_lower");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup1.this, pairs);
            startActivity(intent, options.toBundle());
            finish();
        } else {
            startActivity(intent);
            finish();
        }
    }

    public void switch_to_login_form_signup1(View view) {

        Intent intentLoginPage = new Intent(getApplicationContext(), Login.class);

        // Transitions
        Pair[] loginPairs = new Pair[5];

        loginPairs[0] = new Pair<View, String>(backBtn, "back_btn_transition");
        loginPairs[1] = new Pair<View, String>(title, "title_transition");
        loginPairs[2] = new Pair<View, String>(next, "proceed_btn");
        loginPairs[3] = new Pair<View, String>(existingUser, "swap_form");
        loginPairs[4] = new Pair<View, String>(username, "tran_username");
        loginPairs[3] = new Pair<View, String>(userPassword, "tran_input_lower");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup1.this, loginPairs);
            startActivity(intentLoginPage, options.toBundle());
        } else {
            startActivity(intentLoginPage);
        }
    }

    // Validation Functions

    private boolean fullNameValidation() {
        String name = fullName.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            fullName.setError("Field cannot be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean userNameValidation() {
        String uName = username.getEditText().getText().toString().trim();
        String isSpace = "\\A\\w{1,20}\\z";

        if (uName.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else if (uName.length() > 20) {
            username.setError("Username too long!");
            return false;
        } else if (!uName.matches(isSpace)) {
            username.setError("White Spaces not Allowed");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean emailValidation() {
        String eMail = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (eMail.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!eMail.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean passwordValidation() {
        String pass = userPassword.getEditText().getText().toString().trim();

        if (pass.isEmpty()) {
            userPassword.setError("Field cannot be Empty!");
            return false;
        } else if (!(pass.length() >= 6)) {
            userPassword.setError("Password too Short!");
            return false;
        } else if (pass.contains(" ")) {
            userPassword.setError("White Spaces not Allowed!");
            return false;
        } else {
            userPassword.setError(null);
            userPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean passwordConfirmation() {
        String pwd = userPassword.getEditText().getText().toString().trim();
        String confPass = confirmationPassword.getEditText().getText().toString().trim();
        if (!pwd.matches(confPass)) {
            confirmationPassword.setError("Passwords do not Match!");
            return false;
        } else {
            return true;
        }
    }


    public void backSignup1(View view) {
        super.onBackPressed();
    }
}