package com.example.bryan.bike_retnalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bryan on 28/02/2016.
 */
public class GalleryActivityAdapter extends ArrayAdapter<ParseObject>{
    private Context mContext;
    private List<ParseObject> mImages;

    public GalleryActivityAdapter(Context context, List<ParseObject> images){
        super(context, R.layout.single_row, images);

        mContext = context;
        mImages = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.single_row, null);
            holder = new ViewHolder();

            holder.createdDate = (TextView) convertView
                    .findViewById(R.id.DateMadeHP);

            holder.usernameHomepage = (TextView) convertView
                    .findViewById(R.id.usernameHP);

            holder.homeImage = (ImageView) convertView
                    .findViewById(R.id.imageViewHome);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }



        ParseObject object = mImages.get(position);


        //username DateMadeHP
        String username = object.getString("user");
        holder.usernameHomepage.setText(username);

        //dateCreated

        String DateCreated = object.getString("createdAt");
        holder.createdDate.setText(DateCreated);

        //image
        Picasso.with(getContext().getApplicationContext()).load(object.getParseFile("imageContent").getUrl()).noFade().into(holder.homeImage);



        return convertView;

    }


    public static class ViewHolder {
        TextView createdDate;
        TextView usernameHomepage;
        ImageView homeImage;

    }
}
