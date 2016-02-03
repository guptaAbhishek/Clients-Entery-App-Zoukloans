package com.example.abhishek.zoukloanscliententry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.GetCallback;
import com.parse.ParseException;

import android.widget.TextView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseUser;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class DataViewActivity extends AppCompatActivity {

    private ListView dataList;
    private String[] data;
    private ArrayAdapter<String> adapter;
    private ArrayList<ParseObject> list1 = new ArrayList<ParseObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
//        if(pref.getBoolean("activity_executed", false)){
//            Intent intent = new Intent(this, StartingActivity.class);
//            startActivity(intent);
//        } else {
//            SharedPreferences.Editor ed = pref.edit();
//            ed.putBoolean("activity_executed", true);
//            ed.commit();
//        }

        dataList = (ListView) findViewById(R.id.listView);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Clients");
        query.whereEqualTo("isSaved", true);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList,
                             ParseException e) {
                if (e == null) {
                    Log.d("Clients Data", "Retrieved " + scoreList);
                    for (int i = 0; i < scoreList.size(); i++) {
                        list1.add(scoreList.get(i));
                    }

                    ParseAdapter adapter1 = new ParseAdapter(getApplicationContext(), list1);
                    Log.d("Clients Data", "list data " + list1);
                    dataList.setAdapter(adapter1);
                } else {
                    Log.d("Clients Data", "Error: " + e.getMessage());
                }
            }
        });

        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = String.valueOf(parent.getItemAtPosition(position));

                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Clients");
                query1.whereEqualTo("name", name);
                query1.fromLocalDatastore();
                query1.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            Intent editDetailIntent = new Intent(DataViewActivity.this, MainActivity.class);
                            startActivity(editDetailIntent);
                        } else {
                            Log.d("Clients Data", "Error " + e.getMessage());
                        }
                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataViewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }


}
