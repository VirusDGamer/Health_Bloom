package com.health.healthbloom.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.health.healthbloom.R;

public class Dashboard extends AppCompatActivity {

    String fullName;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        name = findViewById(R.id.name);

        fullName = getIntent().getStringExtra("fullName");

        name.setText(fullName);

    }

    public void callUnfitActivity(View view){
        startActivity(new Intent(getApplicationContext(), SymptomSearch.class));
    }
}