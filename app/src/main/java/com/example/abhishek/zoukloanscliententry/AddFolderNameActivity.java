package com.example.abhishek.zoukloanscliententry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.parse.ParseObject;

public class AddFolderNameActivity extends AppCompatActivity {

    private TextView eventName;
    private Button btnAddEventName;
    private String eventNameStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventName = (TextView) findViewById(R.id.eventName);
        btnAddEventName = (Button) findViewById(R.id.addEvents);

        btnAddEventName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject events = new ParseObject("Events");
                if(eventName.getText().toString() != null){
                    events.put("eventName",eventName.getText().toString());
                }else {
                    events.put("eventName", "Unknown");
                }
                events.put("isSaved",true);
                events.saveInBackground();
                events.saveEventually();
                Intent folderViewActivity = new Intent(AddFolderNameActivity.this,FolderViewActivity.class);
                startActivity(folderViewActivity);
                finish();
            }
        });



    }

}
