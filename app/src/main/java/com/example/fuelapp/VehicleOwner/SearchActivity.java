package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.R;
import com.example.fuelapp.Register.RegisterActivity;
import com.example.fuelapp.Register.VehicleRegisterActivity;
import com.example.fuelapp.Station.StationActivity;

public class SearchActivity extends AppCompatActivity {

    Button btnView;
    TextView txtLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String email = intent.getStringExtra("email");

        btnView = findViewById(R.id.btn_view);
        txtLocation = findViewById(R.id.location);

        txtLocation.setText(email);



        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, ViewActivity.class);
                startActivity(intent);

            }
        });

    }
}