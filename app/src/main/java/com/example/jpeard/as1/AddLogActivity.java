package com.example.jpeard.as1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public class AddLogActivity extends AppCompatActivity {
    private String message;
    private static final String FILENAME = "logs.sav";
    private EditText dateText, stationText, odometerText, gradeText, amountText, unitText;
    private TextView fuelCost;

    private LogList logs = new LogList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateText = (EditText) findViewById(R.id.Date);
        stationText = (EditText) findViewById(R.id.Station);
        odometerText = (EditText) findViewById(R.id.Odometer);
        gradeText = (EditText) findViewById(R.id.fuelGrade);
        amountText = (EditText) findViewById(R.id.fuelAmount);
        unitText = (EditText) findViewById(R.id.unitCost);
        fuelCost = (TextView) findViewById(R.id.cost_value);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        message = intent.getStringExtra(LogViewingActivity.EXTRA_MESSAGE);

        if (message.equals("new")){
            loadFromFile();
            fuelCost.setText(String.format("  %f cents",0.0));
        }
        else{
	    // Fill the edit text blocks with the previous values
            int index = Integer.parseInt(message) - 1;
            loadFromFile();
            dateText.setText(logs.getIndex(index).getDate());
            stationText.setText(logs.getIndex(index).getStation());
            odometerText.setText(logs.getIndex(index).getOdometer().toString());
            gradeText.setText(logs.getIndex(index).getFuel_grade());
            amountText.setText(logs.getIndex(index).getFuel_amount().toString());
            unitText.setText(logs.getIndex(index).getUnit_cost().toString());
            fuelCost.setText(String.format("  %f cents", logs.getIndex(index).getFuel_cost()));
        }
    }

    private void loadFromFile() {
	// Load the LogList from the logs.sav file
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
// Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<LogList>() {}.getType();
            logs = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            logs = new LogList();
        } catch (IOException e ) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
	// Save the Loglist to the logs.sav file
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            Gson gson = new Gson();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            gson.toJson(logs,out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw  new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public void SaveEdit(View v){
	// Creates a new DataLog with the values from
	// the EditText and either appends to the LogList 
	// or replaces a log in the LogList based on the message 
	// value passed in onCreate
        DataLog newLog = new DataLog(dateText.getText().toString(), stationText.getText().toString(), Double.valueOf(odometerText.getText().toString()), gradeText.getText().toString(), Double.valueOf(amountText.getText().toString()), Double.valueOf(unitText.getText().toString()));
        if (message.equals("new")){
            logs.addLog(newLog);
        }
        else{
            int index = Integer.parseInt(message) -1 ;
            logs.replaceLog(newLog,index);
        }

        saveInFile();
        Intent intent = new Intent(this, LogViewingActivity.class);
        startActivity(intent);
    }

    public void CancelAdd(View v){
	// Return to the LogViewingActivity
        Intent intent = new Intent(this, LogViewingActivity.class);
        startActivity(intent);
    }

}
