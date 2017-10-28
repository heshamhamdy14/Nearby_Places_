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

import java.util.ArrayList;

public class favourite extends AppCompatActivity {

    Toolbar toolbar;
    ListView lst_fav_places;
    DatabaseHandler db=new DatabaseHandler(this);

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

        lst_fav_places=(ListView)findViewById(R.id.listviewfav);
        ArrayList<String> thelist=new ArrayList<>();
        Cursor data=db.getlistcontents();

        if (data.getCount()==0)
        {
            Toast.makeText(favourite.this, "data base is empty", Toast.LENGTH_SHORT).show();
        }
        else{

            while (data.moveToNext()){
                thelist.add(data.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                lst_fav_places.setAdapter(listAdapter);
            }
        }

    }
}
