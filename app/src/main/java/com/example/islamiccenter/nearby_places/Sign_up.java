package com.example.islamiccenter.nearby_places;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Sign_up extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    ImageView photo ;
    EditText fullname ;
    EditText email ;
    EditText pass ;
    Button signup;
    TextView signin;
    ImageButton imageButton;
    TextView date;
    private static final int SELECT_PICTURE = 1;
     Calendar myCalendar ;
    DatePickerDialog.OnDateSetListener datePickerListener;


    private String selectedImagePath;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        photo = (ImageView)findViewById(R.id.imageView);
        imageButton=(ImageButton)findViewById(R.id.imageButton2);
        fullname=(EditText)findViewById(R.id.editTextfulname);
        email=(EditText)findViewById(R.id.editText2email);
        pass=(EditText)findViewById(R.id.password );
        signup=(Button)findViewById(R.id.btn_signup);
        signin=(TextView)findViewById(R.id.textView2signin);



        date = (TextView) findViewById(R.id.textViewbirthdate);

         datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

                date.setText(sdf.format(myCalendar.getTime()));

            }

        };


        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Sign_up.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    public void signupclick(View view) {
        boolean result =db.inserData(fullname.getText().toString(),email.getText().toString(),pass.getText().toString());
        if (result) {
            Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "no data inserted", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(Sign_up.this,Sign_in.class);
        startActivity(intent);
    }

    public void btn_signin(View view) {
        Intent intent=new Intent(Sign_up.this,Sign_in.class);
        startActivity(intent);
    }
    public void btn_pick_photo(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                photo.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
