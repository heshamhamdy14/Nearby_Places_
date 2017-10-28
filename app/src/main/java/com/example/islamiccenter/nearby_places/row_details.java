package com.example.islamiccenter.nearby_places;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islamiccenter.nearby_places.PlaceModelData.PlaceModel;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class row_details extends AppCompatActivity {
 DatabaseHandler db;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_details);

        db=new DatabaseHandler(this);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(row_details.this,PlaceSearch.class);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        PlaceModel placeModel=(PlaceModel) getIntent().getSerializableExtra("placemodels");

        final ImageView imageView=(ImageView) findViewById(R.id.imageView3);
        final byte[]newimg=imageViewtobyte(imageView);

        final TextView textView=(TextView) findViewById(R.id.textView2);
        final TextView textView2=(TextView) findViewById(R.id.textView5);
        final TextView textView3=(TextView) findViewById(R.id.textView6);
        final RatingBar ratingBar=(RatingBar) findViewById(R.id.ratingBar2);


        Picasso.with(getBaseContext()).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&" +
                "photoreference=" +placeModel.getPhotos().get(0).getPhotoReference()+
                "&key=AIzaSyAd7Ga7dxZ2It9hNnFzjxNoa0J8Wnk_Vps").into(imageView);
        textView.setText(placeModel.getName());
        textView2.setText( placeModel.getTypes().get(0));
        textView3.setText( placeModel.getOpeningHours().getOpenNow().toString());
        ratingBar.getRating();

        ImageView love=(ImageView)findViewById(R.id.imageViewlove);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result =db.savedata(newimg,textView.getText().toString(),textView2.getText().toString(),textView3.getText().toString(),ratingBar.getRating());
                if (result) {
                    Toast.makeText(row_details.this, "data inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(row_details.this, "no data inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private byte[] imageViewtobyte(ImageView imageView) {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[]bytearray=stream.toByteArray();
        return bytearray;
    }
}
