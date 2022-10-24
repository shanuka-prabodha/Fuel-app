package com.example.fuelapp.Station;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fuelapp.R;

public class StationActivity extends AppCompatActivity {

    Button btn1 , btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_station);

        btn1 = findViewById(R.id.updatePetrol);
        btn2 = findViewById(R.id.updateDiseal);

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

    private void showUpdate(final String Id , final String question , String answer) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.update,null);
        builder.setView(view);

        final EditText fuelStatus = (EditText) view.findViewById(R.id.status);
        TextView statusFuel = view.findViewById(R.id.status);
        statusFuel.setText(question);
        final EditText timeArrival = (EditText) view.findViewById(R.id.time);
        TextView arrivalTime = view.findViewById(R.id.time);
        arrivalTime.setText(answer);
        final Button button = (Button) view.findViewById(R.id.updatePop);


        builder.setTitle(""+question);

        final AlertDialog alert = builder.create();
        alert.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = fuelStatus.getText().toString().trim();
                String answer = timeArrival.getText().toString().trim();

                if(TextUtils.isEmpty(question)) {
                    fuelStatus.setError("Required");
                    timeArrival.setError("Required");
                    return;
                }

                if(TextUtils.isEmpty(answer)) {
                    fuelStatus.setError("Required");
                    return;
                }
                alert.dismiss();
            }
        });



    }
}