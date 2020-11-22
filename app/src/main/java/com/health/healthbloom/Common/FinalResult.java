package com.health.healthbloom.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.health.healthbloom.R;

public class FinalResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        String toPrint = getIntent().getStringExtra("FinalString");
        ((TextView)findViewById(R.id.finalText)).setText(toPrint);
    }
}