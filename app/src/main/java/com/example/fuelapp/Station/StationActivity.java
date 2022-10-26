package com.example.fuelapp.Station;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.R;

import retrofit2.Call;

public class StationActivity extends AppCompatActivity {

    //buttons and text fields initialization
    private Button btn1, btn2;
    private TextView txtPetrolAvailable, txtDisealAvailable, txtPetrolLength, txtDisealLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_station);

        btn1 = findViewById(R.id.updatePetrol);
        btn2 = findViewById(R.id.updateDiseal);
        txtPetrolAvailable = findViewById(R.id.pAvailable);
        txtDisealAvailable = findViewById(R.id.dAvailable);
        txtPetrolLength = findViewById(R.id.pLength);
        txtDisealLength = findViewById(R.id.dLength);

        Intent intent = getIntent();
        System.out.println( intent.getStringExtra("station")) ;

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<StationResponse> call = iUserAPI.getStations("63540401d26b8b17b97cdd6e");

        Log.e("StationActivity", "Dinisuru " );

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showUpdate("12", "Available", "2020-08-7 9.00 A.M.");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdate("23", "Finished", "2020-08-7 9.00 A.M.");
            }
        });


    }

    //
    private void showUpdate(final String Id, final String question, String answer) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.update, null);
        builder.setView(view);

        final EditText fuelStatus = (EditText) view.findViewById(R.id.status);
        TextView statusFuel = view.findViewById(R.id.status);
        statusFuel.setText(question);
        final EditText timeArrival = (EditText) view.findViewById(R.id.time);
        TextView arrivalTime = view.findViewById(R.id.time);
        arrivalTime.setText(answer);
        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("" + question);

        final AlertDialog alert = builder.create();
        alert.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = fuelStatus.getText().toString().trim();
                String answer = timeArrival.getText().toString().trim();

                if (TextUtils.isEmpty(question)) {
                    fuelStatus.setError("Required");
                    timeArrival.setError("Required");
                    return;
                }

                if (TextUtils.isEmpty(answer)) {
                    fuelStatus.setError("Required");
                    return;
                }
                alert.dismiss();
            }
        });


    }
}