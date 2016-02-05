package com.example.abhishek.zoukloanscliententry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText client_name,mobile_num,client_email,client_pan,company_name,loan_amount,about;
    private Spinner business_type;
    private String assinged_by,eventName;
    private String objectId;
    private ParseObject parseObject = new ParseObject("Clients");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        client_name = (EditText) findViewById(R.id.editText);
        mobile_num = (EditText) findViewById(R.id.editText2);
        client_email = (EditText) findViewById(R.id.editText3);
        client_pan = (EditText) findViewById(R.id.editText4);
        company_name = (EditText) findViewById(R.id.editText5);
        loan_amount = (EditText) findViewById(R.id.editText7);
        about = (EditText) findViewById(R.id.editText8);
        business_type = (Spinner) findViewById(R.id.editText6);

        // Receiving
        Intent intent = getIntent();
        objectId = intent.getExtras().getString("objectId");
        eventName = intent.getExtras().getString("eventName");
        Log.d("Object Id", " = " + objectId);
        Log.d("enentnameid","= "+eventName);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Clients");
        query.whereEqualTo("objectId", objectId);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> scoreList,
                             ParseException e) {
                if (e == null) {
                    Log.d("Clients Data", "Retrieved " + scoreList);
                    if (scoreList.get(0).getString("name") != null) {
                        client_name.setText(scoreList.get(0).getString("name"));
                    } else {
                        client_name.setText("");
                    }

                    if (scoreList.get(0).getString("phone") != null) {
                        mobile_num.setText(scoreList.get(0).getString("phone"));
                    } else {
                        mobile_num.setText("");
                    }

                    if (scoreList.get(0).getString("pan") != null) {
                        client_pan.setText(scoreList.get(0).getString("pan"));
                    } else {
                        client_pan.setText("");
                    }

                    if (scoreList.get(0).getString("email") != null) {
                        client_email.setText(scoreList.get(0).getString("email"));
                    } else {
                        client_email.setText("");
                    }
                    if (scoreList.get(0).getString("company_name") != null) {
                        company_name.setText(scoreList.get(0).getString("company_name"));
                    } else {
                        company_name.setText("");
                    }
                    if (scoreList.get(0).getString("business_type") != null) {
//                business_type.setSelection(scoreList.getString("business_type"));
                    } else {
//                client_name.setText("");
                    }

                    if (scoreList.get(0).getString("loan_amount") != null) {
                        client_name.setText(scoreList.get(0).getString("loan_amount"));
                    } else {
                        client_name.setText("");
                    }

                    if (scoreList.get(0).getString("about") != null) {
                        client_name.setText(scoreList.get(0).getString("about"));
                    } else {
                        client_name.setText("");
                    }

                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final ParseObject clients = new ParseObject("Clients");
                            if (scoreList.get(0).getString("assined_by") != null) {
                                clients.put("assined_by", scoreList.get(0).getString("assined_by"));
                            } else {
                                clients.put("assined_by", "Something Broke");
                            }

                            if (client_name.getText().toString() != null) {
                                clients.put("name", client_name.getText().toString());
                            } else {
                                clients.put("name", "");
                            }

                            if (mobile_num.getText().toString() != null) {
                                clients.put("phone", mobile_num.getText().toString());
                            } else {
                                clients.put("phone", "");
                            }

                            if (client_pan.getText().toString() != null) {
                                clients.put("pan", client_pan.getText().toString());
                            } else {
                                clients.put("pan", "");
                            }

                            if (client_email.getText().toString() != null) {
                                clients.put("email", client_email.getText().toString());
                            } else {
                                clients.put("email", "");
                            }

                            if (company_name.getText().toString() != null) {
                                clients.put("company_name", company_name.getText().toString());
                            } else {
                                clients.put("company_name", "");
                            }

                            if (business_type.getSelectedItem().toString() != null) {
                                clients.put("business_type", business_type.getSelectedItem().toString());
                            } else {
                                clients.put("business_type", "");
                            }

                            if (loan_amount.getText().toString() != null) {
                                clients.put("loan_amount", loan_amount.getText().toString());
                            } else {
                                clients.put("loan_amount", "");
                            }

                            if (about.getText().toString() != null) {
                                clients.put("about", about.getText().toString());
                            } else {
                                clients.put("about", "");
                            }

                            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Clients");
                            query1.getInBackground(objectId, new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject parseObject, ParseException e) {
                                    if (e == null) {
                                        clients.saveInBackground();
                                        clients.saveEventually();
                                    } else {
                                        Log.d("Clients Data", "Error: " + e.getMessage());
                                    }
                                }
                            });

                            Intent intent = new Intent(EditProfileActivity.this, DataViewActivity.class);
                            intent.putExtra("eventName",eventName);
                            Log.d("Editevent"," = "+eventName);
                            startActivity(intent);
                            finish();
                        }
                    });


                } else {
                    Log.d("Clients Data", "Error: " + e.getMessage());
                }
            }
        });





    }

}
