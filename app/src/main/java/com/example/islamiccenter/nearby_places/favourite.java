package com.example.islamiccenter.nearby_places;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.islamiccenter.nearby_places.PlaceModelData.PlaceModel;
import com.example.islamiccenter.nearby_places.adapter.PlaceAdapter;
import com.example.islamiccenter.nearby_places.adapter.favourite_adapter;

import java.util.ArrayList;

public class favourite extends AppCompatActivity {

    Toolbar toolbar;
    ListView lst_fav_places;
    DatabaseHandler db=new DatabaseHandler(this);
    favourite_adapter adapter;
    PlaceModel[] placeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        toolbar = (Toolbar) findViewById(R.id.toolbarfav);
        setSupportActionBar(toolbar);
        ImageView logout = (ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(favourite.this, Sign_in.class);
                startActivity(intent);
            }

        });

        placeModel=new PlaceModel[1];

        lst_fav_places=(ListView)findViewById(R.id.listviewfav);
            adapter= new favourite_adapter(favourite.this,placeModel);
            lst_fav_places.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            }
        }



