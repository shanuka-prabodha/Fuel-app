package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fuelapp.Model.TimeTrack;
import com.example.fuelapp.R;

import java.util.ArrayList;

//Track fuel station time spend activity
public class TimeTrackActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<TimeTrack> trackList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_track);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        search();

    }


    public void search( ){

        trackList = new ArrayList<>();
        TimeTrack timeTrack = new TimeTrack("BBG-2345","4.00 PM","4.20 PM","20 min");
        trackList.add(timeTrack);
        trackList.add(timeTrack);
        trackList.add(timeTrack);
        trackList.add(timeTrack);
        trackList.add(timeTrack);


        TimeTrackAdapter timeTrackAdapter = new TimeTrackAdapter(getApplicationContext(),trackList);
        recyclerView.setAdapter(timeTrackAdapter);
    }
}