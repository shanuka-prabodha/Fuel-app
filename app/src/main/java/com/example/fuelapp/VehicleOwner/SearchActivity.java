package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.R;
import com.example.fuelapp.Register.RegisterActivity;
import com.example.fuelapp.Register.VehicleRegisterActivity;
import com.example.fuelapp.Station.StationActivity;
import com.example.fuelapp.Station.StationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    //buttons and text fields initialization
    Button btnView;
    TextView txtLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String email = intent.getStringExtra("email");
        String pStatus = new String();
        String dStatus= "";
        String pLength= "";
        String dLength= "";

        StationResponse stationResponse ;

        btnView = findViewById(R.id.btn_view);
        txtLocation = findViewById(R.id.location);

        txtLocation.setText("Pilimathalawa");

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<StationResponse> call = iUserAPI.getStations("63540401d26b8b17b97cdd6e");//pass station id to this method
        //get station data using station id
        call.enqueue(new Callback<StationResponse>() {

            @Override
            public void onResponse(Call<StationResponse> call, Response<StationResponse> response) {
                Log.e("StationActivity", "Response code " + response.code());

                if (response.code() == 200) {

                    btnView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SearchActivity.this, ViewActivity.class);
                            intent.putExtra("station" , response.body().getData().getName());
                            startActivity(intent);

                        }
                    });

                } else {
                    Log.e("StationActivity", "Exit " );
                }


            }

            @Override
            public void onFailure(Call<StationResponse> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });



    }
}