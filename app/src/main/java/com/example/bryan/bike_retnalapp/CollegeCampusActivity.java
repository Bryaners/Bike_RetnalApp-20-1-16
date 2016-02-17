package com.example.bryan.bike_retnalapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class CollegeCampusActivity extends ListActivity {

    protected List<ParseObject> mBikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_campus);

        // in from NoteApp
        ParseQuery<ParseObject> query = new ParseQuery<>("Transaction");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> status, ParseException e) {

                if (e == null) {
                    //success
                    mBikes = status;

                    StatusAdapter adapter = new StatusAdapter(getListView().getContext(), mBikes);
                    setListAdapter(adapter);
                } else {
                    //there was a probel,. alert user
                    Toast.makeText(CollegeCampusActivity.this, "There are no Bikes in the station Sorry !", Toast.LENGTH_LONG).show();
                }
            }
        });

        //in from noteApp
    }

}
