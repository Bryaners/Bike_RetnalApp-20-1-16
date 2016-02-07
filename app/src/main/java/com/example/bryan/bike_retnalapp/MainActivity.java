package com.example.bryan.bike_retnalapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends ListActivity {


    protected List<ParseObject> mStatus;
    protected Button mCollegeStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Status");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> status, ParseException e) {

                if(e==null){
                    //success
                    mStatus = status;

                    StatusAdapter adapter = new StatusAdapter(getListView().getContext(), mStatus);
                    setListAdapter(adapter);
                }else{
                    //there was a probel,. alert user
                }
            }
        });


        //Parse.enableLocalDatastore(this);

        //Parse.initialize(this, "cRisvlAkyzGI9gpJSRklDLNflxZ1bDoM8iHdQTOj", "BQlKHhqkSnraH9WHGoS6y41AmH0QxnYCDMOWJCVE");

        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */

        mCollegeStreet=(Button)findViewById(R.id.UpdateStatud);


        mCollegeStreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(TransactionActivity.this, "College", Toast.LENGTH_LONG).show();
                Intent takeUserCollegeCampus = new Intent(MainActivity.this, UpdateStatusActivity.class);
                startActivity(takeUserCollegeCampus);
            }
        });


    }

}


