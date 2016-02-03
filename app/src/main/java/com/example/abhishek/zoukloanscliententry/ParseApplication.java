package com.example.abhishek.zoukloanscliententry;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by abhishek on 01/02/16.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "iT7ReC62s1A427l6olxJD7NgTpZhZQg1WWIuTODE", "wmuYJohnOqLzTdvRyvqjqNtMECrDEOg2Sixk7tcp");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

    }

}
