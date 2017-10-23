package com.example.islamiccenter.nearby_places;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.islamiccenter.nearby_places.PlaceModelData.PlaceModel;
import com.squareup.picasso.Picasso;

public class row_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_details);

        Intent intent=getIntent();
        PlaceModel placeModel=(PlaceModel) getIntent().getSerializableExtra("placemodels");

        ImageView imageView=(ImageView) findViewById(R.id.imageView3);
        TextView textView=(TextView) findViewById(R.id.textView2);
        TextView textView2=(TextView) findViewById(R.id.textView5);
        TextView textView3=(TextView) findViewById(R.id.textView6);
        RatingBar ratingBar=(RatingBar) findViewById(R.id.ratingBar2);


        Picasso.with(getBaseContext()).load("http://image.tmdb.org/t/p/w500/"+placeModel.getPhotos()).into(imageView);
        textView.setText(placeModel.getName());
        textView2.setText( placeModel.getTypes().get(0));
       // textView3.setText((CharSequence) placeModel.getOpeningHours().);
        ratingBar.getRating();

    }
}
