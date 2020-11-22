package com.health.healthbloom.User.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.health.healthbloom.R;

public class Login extends AppCompatActivity {


    TextView title;
    TextInputLayout username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        title = findViewById(R.id.title_login);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
    }

    public void CheckLogin(View view) {

        if (!isConnected(this)){
            showCustomDialog();
        }

        if (!validateFields()) {
            return;
        }

        final String _username = username.getEditText().getText().toString().trim();
        final String _password = password.getEditText().getText().toString().trim();


        Query checkuser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(_username);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);

                    String systemPassword = snapshot.child(_username).child("userPassword").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String _fullname = snapshot.child(_username).child("fullName").getValue(String.class);

                        Toast.makeText(Login.this, "Yes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "No Such Account Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private boolean isConnected(Login login) {
        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifiConn != null && wifiConn.isConnected() || mobileConn != null && mobileConn.isConnected()){
            return true;
        }
        else {
            return false;
        }

    }

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setMessage("Please connect to the Internet")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), LoginOrSignup.class));
                        finish();
                    }
                });

    }

    private boolean validateFields(){

        String _username = username.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if (_username.isEmpty()){
            username.setError("Enter your Username");
            username.requestFocus();
            return false;
        }

        else if (_password.isEmpty()){
            password.setError("Enter the Password");
            password.requestFocus();
            return false;
        }

        else {
            return true;
        }
    }

    public void back(View view) {
        super.onBackPressed();
    }

}