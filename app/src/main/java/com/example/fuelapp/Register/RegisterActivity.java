package com.example.fuelapp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fuelapp.R;

public class RegisterActivity extends AppCompatActivity {

//buttons and text fields initialization
    private Button User;
    private Button Stations;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_register);

        User = findViewById(R.id.vehicle);
        Stations = findViewById(R.id.station);

        //button click event for user registration page
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, VehicleRegisterActivity.class);
                startActivity(intent);

            }
        });

        //button click event for station registration page
        Stations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(RegisterActivity.this, StationRegisterActivity.class);
               startActivity(intent);

            }
        });

    }
}