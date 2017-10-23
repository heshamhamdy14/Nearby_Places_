package com.example.islamiccenter.nearby_places;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by islamic center on 22/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String dbName = "signup_data";

    public DatabaseHandler(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE signs (id INTEGER PRIMARY KEY AUTOINCREMENT , full_name TEXT , email TEXT , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS signs");
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


}
