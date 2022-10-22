package com.example.fuelapp.Database;

import android.provider.BaseColumns;

public class DBMaster {

    private DBMaster() {

    }


    public static class Login implements BaseColumns {
        public static final String TABLE_NAME = "login_table";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
    }

}
