package com.example.abhishek.zoukloanscliententry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseObject;

public class AddFolderNameActivity extends AppCompatActivity {

    private EditText eventName;
    private Button btnAddEventName;
    private String eventNameStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventName = (EditText) findViewById(R.id.eventName);
        btnAddEventName = (Button) findViewById(R.id.addEvents);

        btnAddEventName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject events = new ParseObject("Events");
                if(checkValidation()){
                    if(eventName.getText().toString() != null){
                        events.put("eventName",eventName.getText().toString());
                    }else {
                        events.put("eventName", "Unknown");
                    }
                    events.put("isSaved",true);
                    events.pinInBackground();
                    events.saveEventually();
                    Intent folderViewActivity = new Intent(AddFolderNameActivity.this,FolderViewActivity.class);
                    startActivity(folderViewActivity);
                    finish();
                }else{
                    Toast.makeText(AddFolderNameActivity.this,"Please Enter the Event Name",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(eventName)) ret = false;
        return ret;
    }

}
