package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.R;
import com.example.fuelapp.Station.StationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {

    private TextView txtPetrolAvailable, txtDisealAvailable, txtPetrolLength, txtDisealLength;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        txtPetrolAvailable = findViewById(R.id.pAvailable);
        txtDisealAvailable = findViewById(R.id.dAvailable);
        txtPetrolLength = findViewById(R.id.pLength);
        txtDisealLength = findViewById(R.id.dLength);

        Intent intent = getIntent();
        System.out.println( intent.getStringExtra("station")) ;

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<StationResponse> call = iUserAPI.getStations("63540401d26b8b17b97cdd6e");

        Log.e("StationActivity", "Dinisuru " );
        call.enqueue(new Callback<StationResponse>() {



            @Override
            public void onResponse(Call<StationResponse> call, Response<StationResponse> response) {
                Log.e("StationActivity", "Response code " + response.code());

                if (response.code() == 200) {
                    if(response.body().getData().getIspetrol()){
                        txtPetrolAvailable.setText("Available");
                    }else{
                        txtPetrolAvailable.setText("Finished");
                    }

                    if(response.body().getData().getIsdiesel()){
                        txtDisealAvailable.setText("Available");
                    }else{
                        txtDisealAvailable.setText("Finished");
                    }

                    int petrolQueue = response.body().getData().getPcar() + response.body().getData().getPbike() + response.body().getData().getPother();
                    int disealQueue = response.body().getData().getDvan() + response.body().getData().getDbus() + response.body().getData().getDother();

                    Integer pqueue = new Integer(petrolQueue);
                    Integer dqueue = new Integer(disealQueue);

                    txtPetrolLength.setText(pqueue.toString());
                    txtDisealLength.setText(dqueue.toString());

                } else {
                    Log.e("StationActivity", "Exit " );
//                    Intent intent = new Intent(StationActivity.this, SearchActivity.class);
//                    startActivity(intent);
                }


            }

            @Override
            public void onFailure(Call<StationResponse> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });



    }
}