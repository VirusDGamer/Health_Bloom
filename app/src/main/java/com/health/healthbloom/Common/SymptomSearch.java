package com.health.healthbloom.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.health.healthbloom.R;

public class SymptomSearch extends AppCompatActivity {


    ListView symptomsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_search);

        String[] sympData = getResources().getStringArray(R.array.symps);
        symptomsList = (ListView) findViewById(R.id.symptoms_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sympData);
        symptomsList.setAdapter(adapter);
    }
}