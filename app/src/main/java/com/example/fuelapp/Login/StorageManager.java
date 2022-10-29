package com.example.fuelapp.Login;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageManager {
    private SharedPreferences.Editor editor;
    private Context _context;
    public SharedPreferences pref;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "_store";

    public StorageManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setToken(String Token) {
        editor.putString("Token", Token);
        System.out.println("token saved" + " " + Token);
        editor.commit();
    }
    public String getToken() {
        return pref.getString("Token", "");
    }

    public void setQueJoin(Boolean queJoin , String station) {
        editor.putBoolean("Joined", queJoin);
        editor.putString("Station", station);
        editor.commit();
    }

    public Boolean getQueJoin() {
        return pref.getBoolean("Joined", false);
    }

    public String getJoinStation() {
        return pref.getString("Station", "");
    }

    public void setFuelType(String fuelType) {
        editor.putString("FuelType", fuelType);
        editor.commit();
    }

    public String getFuelType() {
        return pref.getString("FuelType", "");
    }

    public void setAdminStation(String station) {
        editor.putString("AdminStation", station);
        editor.commit();
    }
    public String getAdminStation() {
        return pref.getString("AdminStation", "");
    }

}

