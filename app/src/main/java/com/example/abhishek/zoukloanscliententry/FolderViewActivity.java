package com.example.abhishek.zoukloanscliententry;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;

public class FolderViewActivity extends AppCompatActivity {

    private Button btnAddDeals,btnAddEvents;

    private ListView folderListView;
    private String[] folderNameData;
    private ArrayAdapter<String> adapter;
    private ArrayList<ParseObject> folderList = new ArrayList<ParseObject>();
    private ImageView blankImageFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface robotLight = Typeface.createFromAsset(getAssets(),"fonts/robot_light.ttf");
        Typeface robotMedium = Typeface.createFromAsset(getAssets(),"fonts/robot_medium.ttf");
        Typeface robotRegular = Typeface.createFromAsset(getAssets(),"fonts/robot_regular.ttf");
        Typeface robotBold = Typeface.createFromAsset(getAssets(),"fonts/robot_bold.ttf");

        blankImageFolder = (ImageView) findViewById(R.id.blankImageFolder);
        folderListView = (ListView) findViewById(R.id.folderListView);
        btnAddDeals = (Button) findViewById(R.id.addDeals);
        btnAddDeals.setTypeface(robotMedium);
        btnAddEvents = (Button) findViewById(R.id.addEvents);
        btnAddEvents.setTypeface(robotMedium);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");

        final ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Events");
        query1.whereEqualTo("eventName","Unknown");
        query1.fromLocalDatastore();
        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e ==null){
                    Log.d("Unknown","= "+list);
                    if(list.size() !=0){
                        folderList.add(list.get(0));
                    }
                }else{

                }
            }
        });

        query.whereEqualTo("isSaved", true);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList,
                             ParseException e) {
                if (e == null) {
                    Log.d("Folder Data", "Retrieved " + scoreList);
                    if(scoreList.size() != 0){
                        for (int i = 0; i < scoreList.size(); i++) {
                            folderList.add(scoreList.get(i));
                        }
                        blankImageFolder.setVisibility(View.GONE);
                    }else{
                        blankImageFolder.setVisibility(View.VISIBLE);
                    }

                    FolderAdapter folderAdapter = new FolderAdapter(getApplicationContext(), folderList);
                    Log.d("Folder Data", "list data " + folderList);
                    folderListView.setAdapter(folderAdapter);
                } else {
                    Log.d("Folder Data", "Error: " + e.getMessage());
                }
            }
        });

        btnAddDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject events = new ParseObject("Events");
                events.put("eventName","Unknown");
                events.pinInBackground();
                events.saveEventually();
                Intent main = new Intent(FolderViewActivity.this, MainActivity.class);
                main.putExtra("eventName", "Unknown");
                startActivity(main);
            }
        });

        btnAddEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFolderNameIntent = new Intent(FolderViewActivity.this, AddFolderNameActivity.class);
                startActivity(addFolderNameIntent);
            }
        });

        folderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ParseObject obj = (ParseObject) parent.getItemAtPosition(position);
                String eventName = obj.getString("eventName");
                Log.d("eventName", " = " + eventName);
                Intent folderNameListViewIntent = new Intent(FolderViewActivity.this, DataViewActivity.class);
                folderNameListViewIntent.putExtra("eventName", eventName);
                startActivity(folderNameListViewIntent);
            }
        });



    }

}
