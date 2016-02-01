package com.example.jpeard.as1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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

public class LogViewingActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.jpeard.myfirstapp.INDEX";
    private static final String FILENAME = "logs.sav";
    private EditText bodyText;
    private ListView logEntriesList;

    private LogList logs = new LogList();
    private ArrayAdapter<String> adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_viewing);

        bodyText = (EditText) findViewById(R.id.body);
        logEntriesList = (ListView) findViewById(R.id.logEntriesList);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<String>( this, R.layout.list_item, logs.getIndexes());
        logEntriesList.setAdapter(adapter);
        Intent intent = getIntent();
    }

    private void loadFromFile() {
	// Reads the data from logs.sav and loads that
	// data into the LogList
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
	// Writes the data for the current LogList to
	// the file logs.sav
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

    public void AddLog(View v){
	// Opens an AddLogActivity to append a new log 
	// entry to the log list 
        Intent intent = new Intent(this,AddLogActivity.class);
        String index = "new";
        intent.putExtra(EXTRA_MESSAGE, index);
        startActivity(intent);
    }

    public void EditLog(View v){
	// Opens an AddLogActivity and loads the data for 
	// the log at the provided index
        Intent intent = new Intent(this, AddLogActivity.class);
        String index = bodyText.getText().toString();
        if (index.equals("")){
            index = "new";
        }
        intent.putExtra(EXTRA_MESSAGE, index);
        startActivity(intent);
    }

    public void ClearLog(View v){
	// Clears the log and saves the blank log over the existing file
        logs.clear();
        adapter.notifyDataSetChanged();
        saveInFile();
    }
}
