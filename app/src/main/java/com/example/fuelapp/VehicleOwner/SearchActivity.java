package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.Station;
import com.example.fuelapp.Model.TimeTrack;
import com.example.fuelapp.R;
import com.example.fuelapp.Register.RegisterActivity;
import com.example.fuelapp.Register.VehicleRegisterActivity;
import com.example.fuelapp.Station.StationActivity;
import com.example.fuelapp.Station.StationResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnItemClickListener, PopupMenu.OnMenuItemClickListener {

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

        recyclerView = findViewById(R.id.searchrecycleview);
        searchView = findViewById(R.id.searchview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        getAllStations();


        searchAdapter = new SearchAdapter(SearchActivity.this, stationList);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.setOnItemClickListener(SearchActivity.this);

    }


    @Override
    public void onStart() {
        super.onStart();

        if(searchView != null){
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


    public void getAllStations(){

        stationList = new ArrayList<>();

        Station station1 =  new Station("1","Peradeniya",true, 2,1,4,true,2,4,1,"2022-4-20 12.00PM","2022-4-20 12.00PM");
        Station station2 =  new Station("2","Gannoruwa",false, 2,1,4,true,2,4,1,"2022-4-20 12.00PM","2022-4-20 12.00PM");
        Station station3 =  new Station("3","Pilimatalawa",true, 2,1,4,false,2,4,1,"2022-4-20 12.00PM","2022-4-20 12.00PM");
        Station station4 =  new Station("4","Kandy",true, 2,1,4,true,2,4,1,"2022-4-20 12.00PM","2022-4-20 12.00PM");


        stationList.add(station1);
        stationList.add(station2);
        stationList.add(station3);
        stationList.add(station4);

        SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(),stationList);
        recyclerView.setAdapter(searchAdapter);
    }


    public void search(String str){
        ArrayList<Station> mylist = new ArrayList<>();
        for(Station object : stationList){
            if (object.getName().toLowerCase().contains(str.toLowerCase())){
                mylist.add(object);
            }
        }
        SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(),mylist);
        recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    @Override
    public void onItemClick(int position) {

        Station selectedItem = stationList.get(position);
        System.out.println(selectedItem.getName());

        Intent in = new Intent(SearchActivity.this, ViewActivity.class);
        in.putExtra("id",selectedItem.getId());
        in.putExtra("name",selectedItem.getName());
        in.putExtra("isPetrol",selectedItem.getIspetrol());
        in.putExtra("isDiseal",selectedItem.getIsdiesel());
        in.putExtra("pbike",selectedItem.getPbike());
        in.putExtra("pcar",selectedItem.getPcar());
        in.putExtra("pother",selectedItem.getPother());
        in.putExtra("dbus",selectedItem.getDbus());
        in.putExtra("dvan",selectedItem.getDvan());
        in.putExtra("dother",selectedItem.getDother());
        in.putExtra("pnextarival",selectedItem.getPnextarival());
        in.putExtra("dnextarival",selectedItem.getDnextarival());
        startActivity(in);
    }
}