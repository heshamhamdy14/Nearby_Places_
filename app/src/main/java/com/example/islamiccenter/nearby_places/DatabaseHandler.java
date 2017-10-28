package com.example.islamiccenter.nearby_places;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.media.Rating;
import android.widget.RatingBar;

/**
 * Created by islamic center on 22/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String dbName = "Zamel";

    public DatabaseHandler(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE signs (id INTEGER PRIMARY KEY AUTOINCREMENT , full_name TEXT , email TEXT , password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE favourits( id INTEGER PRIMARY KEY AUTOINCREMENT , image_view TEXT , name TEXT , category TEXT , opening_hourse TEXT , rate FLOAT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS signs");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS favourits");
        onCreate(sqLiteDatabase);
    }

    public boolean inserData(String fullname, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("full_name",fullname);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("signs",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    boolean Ch(String full_name , String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        String select = "select * from signs where full_name =? and password =?";

        Cursor res = db.rawQuery(select,new String[]{full_name ,password});
        if(res.moveToFirst())
            return true;
        else
            return false;

    }

    public boolean savedata(byte[] image_view, String name, String category, String opening_hourse, float rate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("image_view", image_view);
        contentValues.put("name",name);
        contentValues.put("category",category);
        contentValues.put("opening_hourse",opening_hourse);
        contentValues.put("rate", rate);
        long result = db.insert("favourits",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

public Cursor getlistcontents()
{
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor data=db.rawQuery("select * from favourits",null);
    return data;
}

}
