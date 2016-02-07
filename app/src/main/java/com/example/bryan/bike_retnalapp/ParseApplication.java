package com.example.bryan.bike_retnalapp;

import android.app.Application;

import com.parse.Parse;



public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // ParseCrashReporting.enable(this);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "cRisvlAkyzGI9gpJSRklDLNflxZ1bDoM8iHdQTOj", "BQlKHhqkSnraH9WHGoS6y41AmH0QxnYCDMOWJCVE");

    }
}

