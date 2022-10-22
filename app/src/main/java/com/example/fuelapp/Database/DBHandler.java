package com.example.fuelapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "login.db";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_USER =
                "CREATE TABLE " + DBMaster.Login.TABLE_NAME + "(" +
                        DBMaster.Login._ID + " INTEGER PRIMARY KEY," +
                        DBMaster.Login.COLUMN_EMAIL + " TEXT," +
                        DBMaster.Login.COLUMN_PASSWORD + " TEXT )";

        db.execSQL(CREATE_LOGIN_USER);

    }



    public long saveUser(String email, String password) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBMaster.Login.COLUMN_EMAIL, email);
        cv.put(DBMaster.Login.COLUMN_PASSWORD, password);
        return db.insert(DBMaster.Login.TABLE_NAME, null, cv);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

