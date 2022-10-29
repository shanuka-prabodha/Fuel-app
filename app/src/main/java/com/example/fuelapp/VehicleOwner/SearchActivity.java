package com.example.fuelapp.VehicleOwner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.Login.StorageManager;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.Station;
import com.example.fuelapp.Model.TimeTrack;
import com.example.fuelapp.R;
import com.example.fuelapp.Register.RegisterActivity;
import com.example.fuelapp.Register.VehicleRegisterActivity;
import com.example.fuelapp.Station.StationActivity;
import com.example.fuelapp.Station.StationResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class SearchActivity extends AppCompatActivity {

    //buttons and text fields initialization

    RecyclerView recyclerView;
    ArrayList<Station> stationList;
    SearchView searchView;
    SearchAdapter searchAdapter;
    Button btnview;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


//        StorageManager storeManager = new StorageManager(getApplicationContext());
//        storeManager.getToken();
//
//        System.out.println( "storeManager.getToken() +++++++++++++++++++++++++++++++++");
//        System.out.println( storeManager.getToken());

        recyclerView = findViewById(R.id.searchrecycleview);
        searchView = findViewById(R.id.searchview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        getAllStations();


        searchAdapter = new SearchAdapter(SearchActivity.this, stationList);
        recyclerView.setAdapter(searchAdapter);


    }


    @Override
    public void onStart() {
        super.onStart();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }


    public void getAllStations() {

        stationList = new ArrayList<>();

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<List<Station>> call = iUserAPI.getAllStation();

        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                Log.e("StationActivity", "Response code " + response.code());

                if (response.code() == 200) {

                    for (Station station : response.body()) {
                        stationList.add(station);
                    }
                    SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(), stationList);
                    recyclerView.setAdapter(searchAdapter);

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });


    }


    public void search(String str) {
        ArrayList<Station> mylist = new ArrayList<>();
        for (Station object : stationList) {
            if (object.getName().toLowerCase().contains(str.toLowerCase())) {
                mylist.add(object);
            }
        }
        SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(), mylist);
        recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        Intent in = new Intent(SearchActivity.this, LoginActivity.class);
        startActivity(in);

        Toast.makeText(this, "You logged out", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

}