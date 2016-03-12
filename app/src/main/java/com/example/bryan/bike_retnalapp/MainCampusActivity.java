package com.example.bryan.bike_retnalapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainCampusActivity extends ListActivity {

    protected List<ParseObject> mBikesMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_campus);

        // in from NoteApp
        ParseQuery<ParseObject> query = new ParseQuery<>("Transaction");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> bike, ParseException e) {

                if (e == null) {
                    //success
                    mBikesMain = bike;

                    //how the adapter to populate the list view
                    MainCampusAdapter adapter = new MainCampusAdapter(getListView().getContext(), mBikesMain);
                    setListAdapter(adapter);
                } else {
                    //there was a probel,. alert user
                    Toast.makeText(MainCampusActivity.this, "There are no Bikes in the station Sorry !", Toast.LENGTH_LONG).show();
                }
            }
        });

        //in from noteApp
    }

}
