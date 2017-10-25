package com.example.islamiccenter.nearby_places.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.islamiccenter.nearby_places.PlaceModelData.Location;
import com.example.islamiccenter.nearby_places.PlaceModelData.PlaceModel;
import com.example.islamiccenter.nearby_places.R;
import com.squareup.picasso.Picasso;

/**
 * Created by islamic center on 22/10/2017.
 */

public class PlaceAdapter extends ArrayAdapter<PlaceModel> {

public PlaceAdapter(@NonNull Context context, @NonNull PlaceModel[] objects) {
        super(context, 0, objects);
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.place_row,parent,false);

        }
        final PlaceModel placesModel=getItem(position);
        ImageView photo =(ImageView)convertView.findViewById(R.id.imageView4photo);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500/"+placesModel.getPhotos()).into(photo);
        TextView title =(TextView)convertView.findViewById(R.id.textView2title);
        title.setText(placesModel.getName());
        TextView openhourse=(TextView)convertView.findViewById(R.id.textView5openhourse);
        openhourse.setText( placesModel.getOpeningHours().getOpenNow().toString());
        TextView category =(TextView)convertView.findViewById(R.id.textView6category);
        category.setText( placesModel.getTypes().get(0)+"");
        RatingBar ratingBar =(RatingBar)convertView.findViewById(R.id.ratingBar);
        ratingBar.getRating();

       final Location loc = new Location();

       ImageView location =(ImageView)convertView.findViewById(R.id.imageView2location) ;
       location.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               loc.getLat();
               loc.getLng();
           }
       });

        return convertView;
        }
        }
