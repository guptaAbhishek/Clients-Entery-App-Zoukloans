package com.example.abhishek.zoukloanscliententry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailViewActivity extends AppCompatActivity {

    private TextView eventNameTxt,clientNameTxt,phoneClient,
    emailClient,panClient,companyName,industryName,aboutClient,loanAmount;
    String objectId,eventName;
    private ArrayList<ParseObject> list1 = new ArrayList<ParseObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        objectId = intent.getExtras().getString("objectId");
        eventName = intent.getExtras().getString("eventName");
        Log.d("Object Id", " = " + objectId);


        eventNameTxt = (TextView) findViewById(R.id.eventNameTxt);
        clientNameTxt = (TextView) findViewById(R.id.clientNameTxt);
        phoneClient = (TextView) findViewById(R.id.phoneClient);
        emailClient = (TextView) findViewById(R.id.emailClient);
        panClient = (TextView) findViewById(R.id.panClient);
        companyName = (TextView) findViewById(R.id.companyName);
        industryName = (TextView) findViewById(R.id.industryName);
        loanAmount = (TextView) findViewById(R.id.loanAmount);
        aboutClient = (TextView) findViewById(R.id.aboutClient);


        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Clients");
        query.whereEqualTo("objectId", objectId);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList,
                             ParseException e) {
                if (e == null) {
                    Log.d("Clients Data", "Retrieved " + scoreList);
                    if (scoreList.get(0) != null) {
                        if (scoreList.get(0).getString("eventName") != null) {
                            eventNameTxt.setText(scoreList.get(0).getString("eventName"));
                        } else {
                            eventNameTxt.setText("");
                        }
                        if (scoreList.get(0).getString("name") != null) {
                            clientNameTxt.setText(scoreList.get(0).getString("name"));
                        } else {
                            clientNameTxt.setText("");
                        }
                        if (scoreList.get(0).getString("phone") != null) {
                            phoneClient.setText(scoreList.get(0).getString("phone"));
                        }else{
                            phoneClient.setText("");
                        }
                        if (scoreList.get(0).getString("pan") != null) {
                            emailClient.setText(scoreList.get(0).getString("pan"));
                        }else{
                            emailClient.setText("");
                        }
                        if (scoreList.get(0).getString("email") != null) {
                            panClient.setText(scoreList.get(0).getString("email"));
                        }else{
                            panClient.setText("");
                        }
                        if (scoreList.get(0).getString("company_name") != null) {
                            companyName.setText(scoreList.get(0).getString("company_name"));
                        }else{
                            companyName.setText("");
                        }
                        if (scoreList.get(0).getString("business_type") != null) {
                            industryName.setText(scoreList.get(0).getString("business_type"));
                        }else{
                            industryName.setText("");
                        }

                        if (scoreList.get(0).getString("loan_amount") != null) {
                            loanAmount.setText(scoreList.get(0).getString("loan_amount"));
                        }else{
                            loanAmount.setText("");
                        }

                        if (scoreList.get(0).getString("about") != null) {
                            aboutClient.setText(scoreList.get(0).getString("about"));
                        }else{
                            aboutClient.setText("");
                        }

                    }

                } else {
                    Log.d("Clients Data", "Error: " + e.getMessage());
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Open Edit Profile Acitivity
                Intent editDataIntent = new Intent(DetailViewActivity.this, EditProfileActivity.class);
                editDataIntent.putExtra("objectId", objectId);
                editDataIntent.putExtra("eventName",eventName);
                startActivity(editDataIntent);
            }
        });






    }

}
