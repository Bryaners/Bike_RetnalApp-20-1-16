package com.example.bryan.bike_retnalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

public class MainCampusAdapter extends ArrayAdapter<ParseObject> {

    protected List<ParseObject> mBikes;
    protected Context mContext;

    public MainCampusAdapter(Context context, List<ParseObject> bike) {
        super(context, R.layout.college_bike_display, bike);
        mContext = context;
        mBikes = bike;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.college_bike_display, null);
            holder = new ViewHolder();
            holder.usernameCollegeCampus = (TextView) convertView
                    .findViewById(R.id.ccName);
            holder.LocationCollegeCampus = (TextView) convertView
                    .findViewById(R.id.LocCC);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ParseObject statusObject = mBikes.get(position);

        //title

        String username = statusObject.getString("NameOfBike");
        holder.usernameCollegeCampus.setText(username);

        //content
        String campus = statusObject.getString("Campus");
        holder.LocationCollegeCampus.setText(campus);

        return convertView;
    }

    public static class ViewHolder{
        TextView usernameCollegeCampus;
        TextView LocationCollegeCampus;
    }

}
