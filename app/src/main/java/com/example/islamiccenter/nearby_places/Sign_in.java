package com.example.islamiccenter.nearby_places;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_in extends AppCompatActivity {
    DatabaseHandler db =new DatabaseHandler(this);
    EditText user ;
    EditText pass ;
    Button signin ;
    TextView signup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signup =(TextView)findViewById(R.id.textView2signup);

    }
    public void btn_signin(View view) {
        boolean result =db.Ch(user.getText().toString(),pass.getText().toString());

        if (result) {
            Toast.makeText(getApplicationContext(), "Login successful, redirecting to Home Page.", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid credentials, please try again.", Toast.LENGTH_LONG).show();

        }
        Intent intent=new Intent(Sign_in.this,PlaceSearch.class);
        startActivity(intent);

    }


    public void btn_signup(View view) {
        Intent myintent = new Intent(Sign_in.this,Sign_up.class);
        startActivity(myintent);
    }
}
