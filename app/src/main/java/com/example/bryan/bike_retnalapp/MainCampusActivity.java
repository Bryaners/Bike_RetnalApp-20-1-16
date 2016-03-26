package com.example.bryan.bike_retnalapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
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

        //
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ParseObject statusObject = mBikesMain.get(position);
        String objectId = statusObject.getObjectId();

        //can delete a single item in the parse table
        //statusObject.remove("Campus");
        //statusObject.saveInBackground();

        Toast.makeText(MainCampusActivity.this, objectId, Toast.LENGTH_SHORT).show();

        Intent goToDetailedView2 = new Intent(MainCampusActivity.this, RentalDetailViewMain.class);
        goToDetailedView2.putExtra("objectID", objectId);
        startActivity(goToDetailedView2);
    }
}
