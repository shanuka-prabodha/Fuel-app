package com.example.fuelapp.Station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.R;
import com.example.fuelapp.VehicleOwner.SearchActivity;

import java.util.Calendar;

import retrofit2.Call;

public class StationActivity extends AppCompatActivity {

    //buttons and text fields initialization
    private Button btn1, btn2;
    private TextView txtPetrolAvailable, txtDisealAvailable, txtPetrolLength, txtDisealLength;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_station);

        btn1 = findViewById(R.id.pJoin);
        btn2 = findViewById(R.id.updateDiseal);
        txtPetrolAvailable = findViewById(R.id.pAvailable);
        txtDisealAvailable = findViewById(R.id.dAvailable);
        txtPetrolLength = findViewById(R.id.pLength);
        txtDisealLength = findViewById(R.id.dLength);

        Intent intent = getIntent();
        System.out.println(intent.getStringExtra("station"));

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<StationResponse> call = iUserAPI.getStations("63540401d26b8b17b97cdd6e");

        Log.e("StationActivity", "Dinisuru ");

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
    private void showUpdate(final String Id, final String status, String time) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.update, null);
        builder.setView(view);

        Spinner fuelStatus = view.findViewById(R.id.status);
//        TextView statusFuel = view.findViewById(R.id.status);
//        fuelStatus.set(status);

        //Date picker
        final EditText dateArrival = (EditText) view.findViewById(R.id.nextDate);
        dateArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        StationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                TextView arrivalDate = view.findViewById(R.id.nextDate);
                arrivalDate.setText(date);
            }
        };


        //Time picker
        final EditText timeArrival = (EditText) view.findViewById(R.id.time);
        timeArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        StationActivity.this, mTimeSetListener, 24, 60, true);

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();

            }
        });


        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                TextView arrivalTime = view.findViewById(R.id.time);

                String timeIN = "";
                if (i < 13)
                    timeIN = "AM";
                else timeIN = "PM";

                String time = i + "." + i1 + timeIN;
                arrivalTime.setText(time);
            }
        };


        final Button button = (Button) view.findViewById(R.id.queueOkBtn);


        builder.setTitle("" + status);

        final AlertDialog alert = builder.create();
        alert.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = fuelStatus.getSelectedItem().toString().trim();
                String time = timeArrival.getText().toString().trim();

                if (TextUtils.isEmpty(status)) {
//                    fuelStatus.seter("Required");
                    timeArrival.setError("Required");
                    return;
                }

                if (TextUtils.isEmpty(time)) {
//                    fuelStatus.setError("Required");
                    return;
                }
                alert.dismiss();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        Intent in = new Intent(StationActivity.this, LoginActivity.class);
        startActivity(in);

        Toast.makeText(this, "You logged out", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}