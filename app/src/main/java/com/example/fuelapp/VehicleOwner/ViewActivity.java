package com.example.fuelapp.VehicleOwner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fuelapp.R;

public class ViewActivity extends AppCompatActivity {

    private TextView txtPetrolAvailable, txtDisealAvailable, txtPetrolLength, txtDisealLength ;
    Button btnMoreInfo, btnDMoreInfo;
    ImageButton btnPetrolView, btnDisealView;
    TextView  pnextTime,dNextTime;

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
        String id = intent.getStringExtra("station");
        String name = intent.getStringExtra("name");
        boolean ispetrol = intent.getBooleanExtra("ispetrol", true);
        int pbike = intent.getIntExtra("pbike", 0);
        int pcar = intent.getIntExtra("pcar", 0);
        int pother = intent.getIntExtra("pother", 0);
        boolean isdiesel = intent.getBooleanExtra("isdiesel", true);
        int dbus = intent.getIntExtra("dbus", 0);
        int dvan = intent.getIntExtra("dvan", 0);
        int dother = intent.getIntExtra("dother", 0);
        String pnextarival = intent.getStringExtra("pnextarival");
        String dnextarival = intent.getStringExtra("dnextarival");

        if (ispetrol) {
            txtPetrolAvailable.setText("Available");
        } else {
            txtPetrolAvailable.setText("Finished");
        }
        if (isdiesel) {
            txtDisealAvailable.setText("Available");
        } else {
            txtDisealAvailable.setText("Finished");
        }


        int petrolQueue = pbike+ pcar + pother;
        int disealQueue = dbus + dvan + dother;

        Integer pqueue = new Integer(petrolQueue);
        Integer dqueue = new Integer(disealQueue);

        txtPetrolLength.setText(pqueue.toString());
        txtDisealLength.setText(dqueue.toString());

//        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
//        Call<StationResponse> call = iUserAPI.getStations("63540401d26b8b17b97cdd6e");
//
//        call.enqueue(new Callback<StationResponse>() {
//            @Override
//            public void onResponse(Call<StationResponse> call, Response<StationResponse> response) {
//                Log.e("StationActivity", "Response code " + response.code());
//
//                if (response.code() == 200) {
//                    if (response.body().getData().getIspetrol()) {
//                        txtPetrolAvailable.setText("Available");
//                    } else {
//                        txtPetrolAvailable.setText("Finished");
//                    }
//
//                    if (response.body().getData().getIsdiesel()) {
//                        txtDisealAvailable.setText("Available");
//                    } else {
//                        txtDisealAvailable.setText("Finished");
//                    }
//
//
//
//                } else {
//                    Log.e("StationActivity", "Exit ");
////                    Intent intent = new Intent(StationActivity.this, SearchActivity.class);
////                    startActivity(intent);
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<StationResponse> call, Throwable t) {
//                Log.e("RegisterActivity", String.valueOf(t));
//            }
//        });

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
                showPetrolView("12", Integer.toString(pcar), Integer.toString(pbike), Integer.toString(pother) ,pnextarival );

            }
        });

        btnDisealView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDisealView("10",  Integer.toString(dbus), Integer.toString(dvan), Integer.toString(dother),dnextarival);

            }
        });

    }


    private void showPetrolView(final String Id, final String carLength, final String bikeLength, final String otherLength, final String next) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.queue, null);
        builder.setView(view);

        TextView carL = view.findViewById(R.id.carPL);
        carL.setText(carLength);


        TextView bikeL = view.findViewById(R.id.bikePL);
        bikeL.setText(bikeLength);

        TextView otherL = view.findViewById(R.id.otherPL);
        otherL.setText(otherLength);

        TextView nextArrival = view.findViewById(R.id.pTime);
        nextArrival.setText(next);

        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("");

        final AlertDialog alert = builder.create();
        alert.show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


    }

    private void showDisealView(final String Id, final String busLength, final String vanLength,  final String otherLength, final String next) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.diseal_queue, null);
        builder.setView(view);

        TextView carL = view.findViewById(R.id.carPL);
        carL.setText(busLength);

        TextView vanL = view.findViewById(R.id.vanPL);
        vanL.setText(vanLength);

        TextView otherL = view.findViewById(R.id.otherPL);
        otherL.setText(otherLength);


        TextView nextArrival = view.findViewById(R.id.dTime);
        nextArrival.setText(next);

        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("");

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