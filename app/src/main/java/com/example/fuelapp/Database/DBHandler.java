package com.example.fuelapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fuelapp.Model.LocalStationLogin;
import com.example.fuelapp.Model.LocalVehicleLogin;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    //Login database creation using sqlite database

    public static final String DATABASE_NAME = "login.db";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create User table
        String CREATE_LOGIN_USER = "CREATE TABLE " + DBMaster.Login.TABLE_NAME + "(" + DBMaster.Login._ID + " INTEGER PRIMARY KEY," + DBMaster.Login.COLUMN_EMAIL + " TEXT," + DBMaster.Login.COLUMN_PASSWORD + " TEXT," + DBMaster.Login.COLUMN_FUEL_TYPE + " TEXT," + DBMaster.Login.COLUMN_ID + " TEXT )";

        db.execSQL(CREATE_LOGIN_USER);


        String CREATE_STATION_USER = "CREATE TABLE " + DBMaster.LoginStation.TABLE_NAME + "(" + DBMaster.LoginStation._ID + " INTEGER PRIMARY KEY," + DBMaster.LoginStation.COLUMN_EMAIL + " TEXT," + DBMaster.LoginStation.COLUMN_PASSWORD + " TEXT," + DBMaster.LoginStation.COLUMN_ID + " TEXT )";
        db.execSQL(CREATE_STATION_USER);

    }


    //save login info method
    public long saveVehicleOwner(String email, String password, String type , String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBMaster.Login.COLUMN_EMAIL, email);
        cv.put(DBMaster.Login.COLUMN_PASSWORD, password);
        cv.put(DBMaster.Login.COLUMN_FUEL_TYPE, type);
        cv.put(DBMaster.Login.COLUMN_ID, id);
        return db.insert(DBMaster.Login.TABLE_NAME, null, cv);
    }



    public long saveStationOwner(String email, String password , String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBMaster.LoginStation.COLUMN_EMAIL, email);
        cv.put(DBMaster.LoginStation.COLUMN_PASSWORD, password);
        cv.put(DBMaster.LoginStation.COLUMN_ID, id);
        return db.insert(DBMaster.LoginStation.TABLE_NAME, null, cv);
    }


    public ArrayList<LocalVehicleLogin> readVehicleLogins() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + DBMaster.Login.TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<LocalVehicleLogin> localVehicleLogins = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                localVehicleLogins.add(new LocalVehicleLogin(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return localVehicleLogins;
    }

    public ArrayList<LocalStationLogin> readStationLogins() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + DBMaster.LoginStation.TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<LocalStationLogin> localStationLogins = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                localStationLogins.add(new LocalStationLogin(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)
                       ));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return localStationLogins;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

