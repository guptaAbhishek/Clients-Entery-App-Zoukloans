package com.example.abhishek.zoukloanscliententry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.net.Uri;
import org.w3c.dom.Text;

import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private String objectId,eventName,phoneTxt;
    private TextView userName,phoneNumber,email,companyName,loanAmount,
    industryType;
    private Button callButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        objectId = intent.getExtras().getString("objectId");
        eventName = intent.getExtras().getString("eventName");
        Log.d("objectId", "=" + objectId);
        Log.d("eventName","= "+eventName);

        phoneNumber =(TextView) findViewById(R.id.tvNumber1);
        callButton = (Button) findViewById(R.id.callButton);
        userName  = (TextView) findViewById(R.id.userName);
        email = (TextView) findViewById(R.id.tvNumber3);
        companyName = (TextView) findViewById(R.id.company_name);
        loanAmount = (TextView) findViewById(R.id.loanAmount);
        industryType = (TextView) findViewById(R.id.industryName);



        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Clients");
        query.whereEqualTo("objectId", objectId);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList,
                             ParseException e) {
                if (e == null) {
                    Log.d("Clients Data", "Retrieved " + scoreList);
                    if (scoreList.get(0) != null) {

                        if (scoreList.get(0).getString("name") != null) {
                            userName.setText(scoreList.get(0).getString("name"));
                        } else {
                            userName.setText("");
                        }
                        if (scoreList.get(0).getString("phone") != null) {
                            phoneNumber.setText(scoreList.get(0).getString("phone"));
                        }else{
                            phoneNumber.setText("");
                        }
                        if (scoreList.get(0).getString("email") != null) {
                            email.setText(scoreList.get(0).getString("email"));
                        }else{
                            email.setText("");
                        }
                        if (scoreList.get(0).getString("company_name") != null) {
                            companyName.setText(scoreList.get(0).getString("company_name"));
                        }else{
                            companyName.setText("");
                        }
                        if (scoreList.get(0).getString("business_type") != null) {
                            industryType.setText(scoreList.get(0).getString("business_type"));
                        }else{
                            industryType.setText("");
                        }

                        if (scoreList.get(0).getString("loan_amount") != null) {
                            loanAmount.setText(scoreList.get(0).getString("loan_amount"));
                        }else{
                            loanAmount.setText("");
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneTxt = phoneNumber.getText().toString();
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:"+phoneTxt));
            }
        });


    }

}
