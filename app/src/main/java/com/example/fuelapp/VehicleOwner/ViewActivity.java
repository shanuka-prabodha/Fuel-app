package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    Button btnMoreInfo ,btnDMoreInfo ;
    ImageButton btnPetrolView , btnDisealView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        txtPetrolAvailable = findViewById(R.id.pAvailable);
        txtDisealAvailable = findViewById(R.id.dAvailable);
        txtPetrolLength = findViewById(R.id.pLength);
        txtDisealLength = findViewById(R.id.dLength);
        btnMoreInfo = findViewById(R.id.btnPMore);
        btnDMoreInfo = findViewById(R.id.btnDMore);
        btnPetrolView = findViewById(R.id.pViewBtn);
        btnDisealView = findViewById(R.id.dView);

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

        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, TimeTrackActivity.class);
                startActivity(intent);

            }
        });

        btnDMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, TimeTrackActivity.class);
                startActivity(intent);

            }
        });


        btnPetrolView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPetrolView("12", "2", "3","3","3");

            }
        });

        btnDisealView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDisealView("10", "5", "9","1","6");

            }
        });

    }



    private void showPetrolView(final String Id, final String carLength, final String vanLength,final String bikeLength, final String otherLength) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.queue, null);
        builder.setView(view);

        TextView carL = view.findViewById(R.id.carPL);
        carL.setText(carLength);

        TextView vanL = view.findViewById(R.id.vanPL);
        vanL.setText(carLength);

        TextView bikeL = view.findViewById(R.id.bikePL);
        bikeL.setText(carLength);

        TextView otherL = view.findViewById(R.id.otherPL);
        otherL.setText(carLength);

        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("" );

        final AlertDialog alert = builder.create();
        alert.show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });



    }

    private void showDisealView(final String Id, final String carLength, final String vanLength,final String bikeLength, final String otherLength) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.diseal_queue, null);
        builder.setView(view);

        TextView carL = view.findViewById(R.id.carPL);
        carL.setText(carLength);

        TextView vanL = view.findViewById(R.id.vanPL);
        vanL.setText(carLength);

        TextView otherL = view.findViewById(R.id.otherPL);
        otherL.setText(carLength);

        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("" );

        final AlertDialog alert = builder.create();
        alert.show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });



    }
}