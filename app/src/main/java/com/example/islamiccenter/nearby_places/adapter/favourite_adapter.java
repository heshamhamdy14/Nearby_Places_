package com.example.islamiccenter.nearby_places.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.islamiccenter.nearby_places.DatabaseHandler;
import com.example.islamiccenter.nearby_places.MapsActivity;
import com.example.islamiccenter.nearby_places.PlaceModelData.PlaceModel;
import com.example.islamiccenter.nearby_places.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by islamic center on 29/10/2017.
 */

public class favourite_adapter extends ArrayAdapter<PlaceModel> {
    DatabaseHandler db=new DatabaseHandler(getContext());


    public favourite_adapter(@NonNull Context context, @NonNull PlaceModel[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_row, parent, false);
        }

        final PlaceModel placesModel=getItem(position);
        ImageView photo =(ImageView)convertView.findViewById(R.id.imageView4photo);
        TextView title =(TextView)convertView.findViewById(R.id.textView2title);
        TextView openhourse=(TextView)convertView.findViewById(R.id.textView5openhourse);
        TextView category =(TextView)convertView.findViewById(R.id.textView6category);
        RatingBar ratingBar =(RatingBar)convertView.findViewById(R.id.ratingBar);
        ImageView location =(ImageView)convertView.findViewById(R.id.imageView2location) ;
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MapsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("placemodels", placesModel);
                getContext().startActivity(intent);

            }
        });

        Cursor data=db.getlistcontents();


        return convertView;


    }
    public class DbBitmapUtility {

        // convert from bitmap to byte array
        public byte[] getBytes(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

        // convert from byte array to bitmap
        public Bitmap getImage(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
    }

}