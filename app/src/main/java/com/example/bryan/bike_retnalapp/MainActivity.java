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
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends ListActivity {


    protected List<ParseObject> mStatus;
    protected Button mUpdateStatus;
    protected Button mBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);



        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null) {
            //show user the homepage status

            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Status");

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> status, ParseException e) {

                    if (e == null) {
                        //success
                        mStatus = status;

                        UpdateStatusAdapter adapter = new UpdateStatusAdapter(getListView().getContext(), mStatus);
                        setListAdapter(adapter);
                    } else {
                        //there was a problem alert user
                    }
                }
            });
        }else{
            //show the login screen
            Intent takeusertoLogin = new Intent(this,LoginActivity.class);
            startActivity(takeusertoLogin);
        }


        mUpdateStatus=(Button)findViewById(R.id.UpdateStatud);
        mBackToMainMenu=(Button)findViewById(R.id.BackToMainMenu);

        mUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(TransactionActivity.this, "College", Toast.LENGTH_LONG).show();
                Intent takeUserCollegeCampus = new Intent(MainActivity.this, UpdateStatusActivity.class);
                startActivity(takeUserCollegeCampus);
            }
        });

        mBackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(TransactionActivity.this, "College", Toast.LENGTH_LONG).show();
                Intent takeUserMainMenu = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(takeUserMainMenu);
            }
        });


    }

}


