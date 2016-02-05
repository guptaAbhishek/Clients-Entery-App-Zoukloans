package com.example.abhishek.zoukloanscliententry;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        folderListView = (ListView) findViewById(R.id.folderListView);
        btnAddDeals = (Button) findViewById(R.id.addDeals);
        btnAddEvents = (Button) findViewById(R.id.addEvents);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
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
                    }else{

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
                Log.d("eventName"," = "+eventName);
                Intent folderNameListViewIntent = new Intent(FolderViewActivity.this, DataViewActivity.class);
                folderNameListViewIntent.putExtra("eventName", eventName);
                startActivity(folderNameListViewIntent);
            }
        });



    }

}
