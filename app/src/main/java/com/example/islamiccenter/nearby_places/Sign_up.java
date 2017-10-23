package com.example.islamiccenter.nearby_places;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    ImageView photo ;
    EditText fullname ;
    EditText email ;
    EditText pass ;
    Button signup;
    TextView signin;
    ImageButton imageButton;
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;

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
