package com.health.healthbloom.User.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.health.healthbloom.Common.Dashboard;
import com.health.healthbloom.Databases.UserDatabaseClass;
import com.health.healthbloom.R;

import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {

    PinView pinview;
    String sentcode, fullName, userName, email, password, gender, dob, phoneNum;
    TextView phoneNoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        pinview = findViewById(R.id.pin_view);
        phoneNoText = findViewById(R.id.phone_no_text);

        // Get data from previous activity
        fullName = getIntent().getStringExtra("fullName");
        userName = getIntent().getStringExtra("userName");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        gender = getIntent().getStringExtra("gender");
        dob = getIntent().getStringExtra("dob");
        phoneNum = getIntent().getStringExtra("phoneNum");

        phoneNoText.setText(phoneNum);

        sendOTP(phoneNum);
    }

    private void sendOTP(String phoneNum) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNum,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            sentcode = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if(code!=null){
                pinview.setText(code);

                codeVerification(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException error) {
            Toast.makeText(OtpVerification.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void codeVerification(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sentcode, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            saveUserData();
                            Toast.makeText(OtpVerification.this, "Verification Successful!", Toast.LENGTH_SHORT).show();

                            Intent dashBoardIntent = new Intent(getApplicationContext(), Dashboard.class);

                            dashBoardIntent.putExtra("fullName", fullName);
                            startActivity(dashBoardIntent);
                            finish();
                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OtpVerification.this, "Invalid Code! Verification Unsuccessful!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void saveUserData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();

        // Table
        DatabaseReference reference = rootNode.getReference("Users");

        UserDatabaseClass addNewUser = new UserDatabaseClass(fullName, userName, email, password, gender, dob, phoneNum);

        reference.setValue(addNewUser);
    }

    public void verifyOTP(View view){
        String OTP = pinview.getText().toString();
        if(!OTP.isEmpty()){
            codeVerification(OTP);
        }
    }
}