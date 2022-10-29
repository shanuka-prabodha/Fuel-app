package com.example.fuelapp.Model;

import android.os.Build;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller{

    //get retrofit instance
    private static  Retrofit retrofit;

    static final String BASE_URL = "http://192.168.1.44:5000/";
   //get ip address of the api hosted server
    //if you are using locallohoast use 10.0.2.2 instead of

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}