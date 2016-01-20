package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class HomePageActivity extends AppCompatActivity {

    protected Button mRentaBike;
    protected Button mViewMap;
    protected Button mInfomation;
    protected Button mCounter;
    protected Button mGallery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mRentaBike=(Button)findViewById(R.id.RentBikeButton);

        mRentaBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HomePageActivity.this, "clicked rent bike button", Toast.LENGTH_LONG).show();
                Intent takeUserToRentBike = new Intent(HomePageActivity.this, TransactionActivity.class);
                startActivity(takeUserToRentBike);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
    }
                    //Commit test
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
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
        switch(id){
            case R.id.updateStatus:
        //take user to update status activity
      //  intent
        break;

            case R.id.logout_user:
            //log out the user
            ParseUser.logOut();
            //take the user to back to login screen
            Intent takeUserToLogin = new Intent(HomePageActivity.this, RegisterActivity.class);
            startActivity(takeUserToLogin);
        break;
        }


        return super.onOptionsItemSelected(item);
    }
}
