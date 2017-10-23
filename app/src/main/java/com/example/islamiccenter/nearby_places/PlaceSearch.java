package com.example.islamiccenter.nearby_places;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.islamiccenter.nearby_places.PlaceModelData.PlaceModel;
import com.example.islamiccenter.nearby_places.adapter.PlaceAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlaceSearch extends AppCompatActivity {

    EditText txtsearch ;
    ImageButton search ;
    ListView lstplaces;
    PlaceModel[] placemodels;
    PlaceAdapter placesadapter;
    ProgressDialog progressDialog;
    Gson gson =new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search);

        txtsearch =(EditText)findViewById(R.id.txtsearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtsearch.getText().toString();

                GetPlaces getPlaces =new GetPlaces();
                String url ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type="+txtsearch+"&keyword=cruise&key=AIzaSyAd7Ga7dxZ2It9hNnFzjxNoa0J8Wnk_Vps";
                getPlaces.execute(url);
            }
        });
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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.icons_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent=new Intent(PlaceSearch.this,Sign_in.class);
                startActivity(intent);
                return true;
            case R.id.love:

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
