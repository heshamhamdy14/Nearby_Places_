package com.example.islamiccenter.nearby_places;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.islamiccenter.nearby_places.PlaceModelData.PlaceModel;
import com.example.islamiccenter.nearby_places.adapter.PlaceAdapter;
import com.example.islamiccenter.nearby_places.adapter.favourite_adapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlaceSearch extends AppCompatActivity {

    EditText txtsearch ;
    ImageButton search ;
    ListView lstplaces;
    ListView lst_fav_places;
    PlaceModel[] placemodels;
    PlaceAdapter placesadapter;
    favourite_adapter adapter;
    ProgressDialog progressDialog;
    Gson gson =new Gson();
Toolbar toolbar;
    DatabaseHandler db=new DatabaseHandler(this);
boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search);

        lst_fav_places=(ListView)findViewById(R.id.lst_fav_places);
        lstplaces=(ListView)findViewById(R.id.list_places);
        search=(ImageButton)findViewById(R.id.imageButtonsaerch);
        txtsearch =(EditText)findViewById(R.id.txtsearch);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView logout=(ImageView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlaceSearch.this,Sign_in.class);
                startActivity(intent);
            }
        });
        ImageView love=(ImageView)findViewById(R.id.love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PlaceSearch.this,favourite.class);
                startActivity(intent);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&key=AIzaSyAd7Ga7dxZ2It9hNnFzjxNoa0J8Wnk_Vps&type="+txtsearch.getText().toString();
                Log.d("zamel", "onClick: "+url);
                GetPlaces getPlaces =new GetPlaces();
                getPlaces.execute(url);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    class GetPlaces extends AsyncTask<String , Void ,PlaceModel[] > {

        protected void onPreExecute() {

            progressDialog =new ProgressDialog(PlaceSearch.this);
            progressDialog.setMessage("loading.....");
            progressDialog.show();
        }
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            return response.body().string();
        }

        protected PlaceModel[] doInBackground(String... url) {
            try {
                String s = run(url[0]);
                Log.d("hesham", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                placemodels = gson.fromJson(jsonArray.toString(), PlaceModel[].class);
                Log.d("zamel", "doInBackground: "+placemodels.length);
                return placemodels;

            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(PlaceModel[]placesModels) {
            progressDialog.dismiss();
            if(placesModels!=null) {
                placesadapter = new PlaceAdapter(PlaceSearch.this, placemodels);
                lstplaces.setAdapter(placesadapter);
                lstplaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent =new Intent(PlaceSearch.this,row_details.class);
                        intent.putExtra("placemodels",(Serializable) placemodels[i]);
                        startActivity(intent);

                    }
                });

            }
          adapter =new favourite_adapter(PlaceSearch.this,placemodels);
            lst_fav_places.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }
}












