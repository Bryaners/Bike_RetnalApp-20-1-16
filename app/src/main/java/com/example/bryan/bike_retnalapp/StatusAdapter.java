package com.example.bryan.bike_retnalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Bryan on 02/02/2016.
 */
public class StatusAdapter extends ArrayAdapter<ParseObject> {

    protected Context mContext;
    protected List<ParseObject> mStatus;
                                                            //this status getters and setters
    public StatusAdapter(Context context, List<ParseObject> status) {
        super(context, R.layout.home_page_custom_layout, status);
        mContext = context;
        mStatus = status;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.home_page_custom_layout, null);
            holder = new ViewHolder();
            holder.usernameHomepage = (TextView) convertView
                    .findViewById(R.id.usernameHP);
            holder.statusHomepage = (TextView) convertView
                    .findViewById(R.id.statusHP);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ParseObject statusObject = mStatus.get(position);

        //title

        String username = statusObject.getString("objectId");
        holder.usernameHomepage.setText(username);

        //content
        String status = statusObject.getString("newStatus");
        holder.statusHomepage.setText(status);

        return convertView;
    }

    public static class ViewHolder{
        TextView usernameHomepage;
        TextView statusHomepage;
    }

}