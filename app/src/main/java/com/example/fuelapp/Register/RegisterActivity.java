package com.example.fuelapp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fuelapp.R;

public class RegisterActivity extends AppCompatActivity {


    private Button User;
    private Button Stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_register);

        User = findViewById(R.id.user);
        Stations = findViewById(R.id.admin);

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, VehicleRegisterActivity.class);
                startActivity(intent);
            }
        });

        Stations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, StationRegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}