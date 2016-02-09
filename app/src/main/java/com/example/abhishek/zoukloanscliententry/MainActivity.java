package com.example.abhishek.zoukloanscliententry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseACL;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity {



    private Button btnSubmit;
    private EditText client_name,mobile_num,client_email,client_pan,company_name,loan_amount,about;
    private Spinner business_type;
    private String assinged_by;
    private String eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent eventNameIntnet = getIntent();
        eventName = eventNameIntnet.getExtras().getString("eventName");
        Log.d("Event Name", " = " + eventName);

        Typeface robotLight = Typeface.createFromAsset(getAssets(),"fonts/robot_light.ttf");
        Typeface robotMedium = Typeface.createFromAsset(getAssets(),"fonts/robot_medium.ttf");
        Typeface robotRegular = Typeface.createFromAsset(getAssets(),"fonts/robot_regular.ttf");
        Typeface robotBold = Typeface.createFromAsset(getAssets(),"fonts/robot_bold.ttf");

//        Getting the value of the admin
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(MainActivity.this);
        assinged_by=settings.getString("assined_by","");

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setTypeface(robotMedium);
        client_name = (EditText) findViewById(R.id.client_name);
        client_name.setTypeface(robotLight);
        mobile_num = (EditText) findViewById(R.id.mobile_num);
        mobile_num.setTypeface(robotLight);
        client_email = (EditText) findViewById(R.id.client_email);
        client_email.setTypeface(robotLight);
        client_pan = (EditText) findViewById(R.id.client_pan);
        client_pan.setTypeface(robotLight);
        company_name = (EditText) findViewById(R.id.company_name);
        company_name.setTypeface(robotLight);
        loan_amount = (EditText) findViewById(R.id.loan_amount);
        loan_amount.setTypeface(robotLight);
        about = (EditText) findViewById(R.id.about);
        about.setTypeface(robotLight);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addItemsOnSpinner2();

        // Validation
        //Client Name Validation

        client_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Validation.hasText(client_name);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Client Email Validation
        client_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Validation.isEmailAddress(client_email, true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        company_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Validation.hasText(company_name);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mobile_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Validation.isPhoneNumber(mobile_num,true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ParseObject clients = new ParseObject("Clients");
                if(checkValidation()){
                    if(assinged_by !=null){
                        clients.put("assined_by",assinged_by);
                    }else{
                        clients.put("assined_by","Something Broke");
                    }

                    if(client_name.getText().toString() !=null){
                        clients.put("name",client_name.getText().toString());
                    }else{
                        clients.put("name","");
                    }

                    if(mobile_num.getText().toString() !=null){
                        clients.put("phone",mobile_num.getText().toString());
                    }else{
                        clients.put("phone","");
                    }

                    if(client_pan.getText().toString() !=null){
                        clients.put("pan",client_pan.getText().toString());
                    }else{
                        clients.put("pan","");
                    }

                    if(client_email.getText().toString() !=null){
                        clients.put("email",client_email.getText().toString());
                    }else{
                        clients.put("email","");
                    }

                    if(company_name.getText().toString() !=null){
                        clients.put("company_name",company_name.getText().toString());
                    }else{
                        clients.put("company_name","");
                    }

                    if(business_type.getSelectedItem().toString() != null){
                        clients.put("business_type",business_type.getSelectedItem().toString());
                    }else{
                        clients.put("business_type","");
                    }

                    if(loan_amount.getText().toString() != null){
                        clients.put("loan_amount",loan_amount.getText().toString());
                    }else{
                        clients.put("loan_amount","");
                    }

                    if(about.getText().toString() !=null){
                        clients.put("about",about.getText().toString());
                    }else{
                        clients.put("about","");
                    }

                    clients.put("eventName",eventName);
                    clients.put("isSaved", true);
                    clients.pinInBackground();
                    clients.saveEventually();
                    Intent intent = new Intent(MainActivity.this, DataViewActivity.class);
                    intent.putExtra("eventName",eventName);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Please enter required field",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnSubmit.setEnabled(true);

    }


    // ToListActivity.java
    private void syncTodosToParse() {
       final ArrayList<ParseObject> list1 = new ArrayList<ParseObject>();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if ((ni != null) && (ni.isConnected())) {
            if (!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
                // If we have a network connection and a current
                // logged in user, sync the todos
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("Clients");
                query.whereEqualTo("isSaved", true);
                query.fromLocalDatastore();
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList,
                                     ParseException e) {
                        if (e == null) {
                            Log.d("score", "Retrieved " + scoreList);
                            for (int i = 0; i < scoreList.size(); i++) {
                                list1.add(scoreList.get(i));
                            }
                            Log.d("score", "list data " + list1);

                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });


            } else {
                // If we have a network connection but no logged in user, direct
                // the person to log in or sign up.
//                ParseLoginBuilder builder = new ParseLoginBuilder(this);
//                startActivityForResult(builder.build(),0);
            }
        } else {
            // If there is no connection, let the user know the sync didn't happen
            Toast.makeText(
                    getApplicationContext(),
                    "Your device appears to be offline. Some todos may not have been synced to Parse.",
                    Toast.LENGTH_LONG).show();
        }
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        business_type = (Spinner) findViewById(R.id.business_type);
        List<String> list = new ArrayList<String>();
        String[] business_type1 = new String[]{"Select Industry Type","Agriculture","ACs and Refrigerators","Automobiles","Books, Office Supplies Stationery",
        "Cement","Chemicals","Computers","Construction","Consumer Electronics","Containers and Packaging",
                "Durables","Electronics","Entertainment and Leisure","Financial Services","Food and Beverages","Food Processing","Food Products",
                "Glass and Glass Products","Granite / Marbles","Healthcare","Home Furniture and Furnishing","Household Products","Industrial Equipment",
                "Jewellery","Luggage and Leather Goods","Media","Metals","Paints","Paper","Petroleum Products","Photographic and Allied Products",
                "Plastics","Professional Services","Rubber","Specialty (Clock, Watches, Footwear, Toys and Games, Optical Instruments, Sports Goods)",
                "Television broadcasting media","Textiles","Transportation Logistics","Wood and Wood Products"};

        for(int i=0;i<business_type1.length;i++){
            list.add(business_type1[i]);
        }



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        business_type.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        business_type = (Spinner) findViewById(R.id.business_type);
//        business_type.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(client_name)) ret = false;
        if (!Validation.isEmailAddress(client_email, true)) ret = false;
        if (!Validation.isPhoneNumber(mobile_num, true)) ret = false;

        return ret;
    }
}

