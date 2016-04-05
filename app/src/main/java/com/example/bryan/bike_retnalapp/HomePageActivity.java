package com.example.bryan.bike_retnalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

public class HomePageActivity extends AppCompatActivity {

    protected Button mRentaBike;
    protected Button mViewMap;
    protected Button mInfomation;
    protected Button mGallery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "LiveChat has been activated", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent takeUserToLiveChat = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(takeUserToLiveChat);
            }
        });

        FloatingActionButton logout = (FloatingActionButton) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "user has logged out", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent takeUserOut = new Intent(HomePageActivity.this, LoginActivity.class);
                startActivity(takeUserOut);
                Toast.makeText(HomePageActivity.this, "User has logged out", Toast.LENGTH_LONG).show();
            }
        });


        mRentaBike=(Button)findViewById(R.id.RentBikeButton);
        mGallery=(Button)findViewById(R.id.GalleryButton);
        mViewMap =(Button)findViewById(R.id.MapButton);
        mInfomation =(Button)findViewById(R.id.infoBtn);

        mInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HomePageActivity.this, "clicked rent bike button", Toast.LENGTH_LONG).show();
                Intent takeUserToInfo = new Intent(HomePageActivity.this, Information.class);
                startActivity(takeUserToInfo);
            }
        });

        mRentaBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HomePageActivity.this, "clicked rent bike button", Toast.LENGTH_LONG).show();
                Intent takeUserToRentBike = new Intent(HomePageActivity.this, TransactionActivity.class);
                startActivity(takeUserToRentBike);
            }
        });



        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HomePageActivity.this, "clicked rent bike button", Toast.LENGTH_LONG).show();
                Intent takeUserToStatus = new Intent(HomePageActivity.this, GalleryActivity.class);
                startActivity(takeUserToStatus);
            }
        });

        mViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(HomePageActivity.this, "clicked rent bike button", Toast.LENGTH_LONG).show();
                Intent takeUserToGMaps = new Intent(HomePageActivity.this, MapsActivityTESTS.class);
                startActivity(takeUserToGMaps);
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
        switch(id){
            case R.id.updateStatus:
        //take user to update status activity
                Intent takeUserToStatus = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(takeUserToStatus);
        break;

            case R.id.logout_user:
            //log out the user
            ParseUser.logOut();
            //take the user to back to login screen
            Intent takeUserToLogin = new Intent(this, LoginActivity.class);
            startActivity(takeUserToLogin);
        break;
        }


        return super.onOptionsItemSelected(item);
    }
}
