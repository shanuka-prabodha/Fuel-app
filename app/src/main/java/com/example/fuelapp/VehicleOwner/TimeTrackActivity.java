package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.Queue;
import com.example.fuelapp.Model.QueueList;
import com.example.fuelapp.Model.TimeTrack;
import com.example.fuelapp.Model.User;
import com.example.fuelapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String stationID = getIntent().getStringExtra("station");
        String fuelType = getIntent().getStringExtra("fuelType");


        search(stationID,fuelType);

    }

    public void search(String stationID , String fuelType){

//        trackList = new ArrayList<>();
//        TimeTrack timeTrack = new TimeTrack("BBG-2345","4.00 PM","4.20 PM","20 min");
//        trackList.add(timeTrack);
//        trackList.add(timeTrack);
//        trackList.add(timeTrack);
//        trackList.add(timeTrack);
//        trackList.add(timeTrack);

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<QueueList> call = iUserAPI.getQueue(stationID,fuelType);

        call.enqueue(new Callback<QueueList>() {
            @Override
            public void onResponse(Call<QueueList> call, Response<QueueList> response) {
                trackList = new ArrayList<>();

                Log.e("TimeTrackActivity", "onResponse: " + response.code());

                if(response.code() == 200){

                    for(Queue queue : response.body().getData()){

                        String startTime = queue.getStart().substring(0,5);
                        String endTime = queue.getEnd().substring(0,5);

                        //convert string to time
                        int startHour = Integer.parseInt(startTime.substring(0,2));
                        int startMin = Integer.parseInt(startTime.substring(3,5));
                        int endHour = Integer.parseInt(endTime.substring(0,2));
                        int endMin = Integer.parseInt(endTime.substring(3,5));
                        int totalMin = (endHour*60+endMin)-(startHour*60+startMin);
                        int hour = totalMin/60;
                        int min = totalMin%60;
                        String totalTime = hour+"h "+min+"m";

                        TimeTrack timeTrack = new TimeTrack(queue.getVehicleno(),queue.getStart(),queue.getEnd(),totalTime);

                        trackList.add(timeTrack);
                    }
                    TimeTrackAdapter timeTrackAdapter = new TimeTrackAdapter(getApplicationContext(),trackList);
                    recyclerView.setAdapter(timeTrackAdapter);
                }else {
                    Toast.makeText(TimeTrackActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<QueueList> call, Throwable t) {
                Toast.makeText(TimeTrackActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });





    }





}