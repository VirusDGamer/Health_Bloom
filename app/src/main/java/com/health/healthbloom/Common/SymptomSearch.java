package com.health.healthbloom.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.health.healthbloom.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomSearch extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView symptomsList, selectedSymps;
    String choice = "", toSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_search);

        String[] sympData = getResources().getStringArray(R.array.symps);
        symptomsList = (ListView) findViewById(R.id.symptoms_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sympData);
        symptomsList.setAdapter(adapter);

        symptomsList.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        List<String> selectedIllness = new ArrayList<String>();

        if (position == 0) {
            selectedIllness.add("Fever");
            choice = choice + "F";
        }
        if (position == 1) {
            selectedIllness.add("Head Ache");
            choice = choice + "H";
        }
        if (position == 2) {
            selectedIllness.add("Cough");
            choice = choice + "C";
        }

        selectedSymps = (ListView) findViewById(R.id.illness);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selectedIllness);
        selectedSymps.setAdapter(adapter);

        switch (choice) {
            case "F":
                toSend = "You Have Fever";
                break;

            case "H":
                toSend = "You Have Head Ache";
                break;

            case "C":
                toSend = "You Have Cough";
                break;

            case "FH":
                toSend = "You Have Viral Infection";
                break;

            case "FC":
                toSend = "You Have Throat Infection";
                break;

            case "HC":
                toSend = "You Have Excessive Exertion";
                break;

            case "FHC":
                toSend = "You Might Have Corona so please go and give Corona test. Best of Luck.";
                break;
        }
    }

    public void goToFinalScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), FinalResult.class);
        intent.putExtra("FinalString", toSend);
        startActivity(intent);
    }
}