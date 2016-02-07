package com.example.bryan.bike_retnalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

public class CollegeAdapter extends ArrayAdapter<ParseObject> {
    protected Context mContext;
    protected List<ParseObject> mSatus;

    public CollegeAdapter(Context context, List<ParseObject> status) {
        super(context, R.layout.list_item_layout, status);
        mContext = context;
        mSatus = status;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.usernameCollegeCampus = (TextView) convertView
                    .findViewById(R.id.usernameHP);
            holder.LocationCollegeCampus = (TextView) convertView
                    .findViewById(R.id.statusHP);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


    ParseObject statusObject = mSatus.get(position);

    //title

    String username = statusObject.getString("NameOfBike");
    holder.usernameCollegeCampus.setText(username);

    //content
    String status = statusObject.getString("Campus");
    holder.LocationCollegeCampus.setText(status);

    return convertView;
}

 public static class ViewHolder{
     TextView usernameCollegeCampus;
     TextView LocationCollegeCampus;
 }

}
