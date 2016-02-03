package com.example.abhishek.zoukloanscliententry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class StartingActivity extends AppCompatActivity {

    private Spinner admin_users;
    private Button btnEnter;
    public String assined_by;
    private static final String PREFRENCES_NAME = "myprefrences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        admin_users = (Spinner) findViewById(R.id.admin_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addAdminUsers();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assined_by = admin_users.getSelectedItem().toString();
                SharedPreferences assined_by_user = PreferenceManager.getDefaultSharedPreferences(StartingActivity.this);
                assined_by_user.edit().putString("assined_by",assined_by).commit();
                Intent intent = new Intent(StartingActivity.this, DataViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Log.d("Finish", "Could not finish activity");
            }
        });


    }

    public void addAdminUsers(){

        admin_users = (Spinner) findViewById(R.id.admin_users);
        List<String> list = new ArrayList<String>();
        String[] admin_users1 = new String[]{"Select Your Name","Ash Narain","Akash Bansal","Rahul Gautam","Prerna Gera","Dr. U.C. Bansal",
                "Tribhuvan Sharma","Harminder Kaur","Abhishek Dubey","Srinjay Velidanda","Vipul Beniwal","Ahmad Khalil",
                "Bodhayan Prasad","Sagar Sharma","Himani Rastogi","Kumar Pratyush","Rupesh Jhabak","Pooja Chopra","Aditya Walia",
                "Puneet Bhushan","Asli Singhaar","Kabir Narain","Saurabh Goel","Mohit Gawande","Ishita Maurya",
                "Selina Rua","Kunal Sinha","Shashwat Raj","Kapil Soni","Adnan","Mohamed Amouri","Vinod Sharma","Sarvansh",
                "Diana Elizabeth","Devanand Jha","Guest","Faizan Ali",
                "Mohit Bhatt","Himanshu Gupta","Vikas Garg","Sachin Raizada","Abhishek Gupta"};

        for(int i=0;i<admin_users1.length;i++){
            list.add(admin_users1[i]);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        admin_users.setAdapter(dataAdapter);
    }




}
