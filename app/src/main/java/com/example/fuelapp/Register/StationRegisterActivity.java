package com.example.fuelapp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.Station;
import com.example.fuelapp.Model.User;
import com.example.fuelapp.Model.UserLoginResponse;
import com.example.fuelapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationRegisterActivity extends AppCompatActivity {

    //buttons and text fields initialization
    private Spinner city;
    private Spinner company;
    private TextView email;
    private TextView password;
    private Button register;
    List<String> stationList;

    String stationId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_station_register);

        city = findViewById(R.id.city);
        company = findViewById(R.id.company);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.btnregister);

        getAllStations();






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(StationRegisterActivity.this, "Register", Toast.LENGTH_SHORT).show();
                onRegister();
//                Toast.makeText(StationRegisterActivity.this, stationId, Toast.LENGTH_SHORT).show();

            }
        });





    }

    //station user registration method
    public void onRegister() {

        String city = this.city.getSelectedItem().toString();
        String company = this.company.getSelectedItem().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        //empty field validation
//        if(city.isEmpty()){
//            this.city.setError("City is required");
//            this.city.requestFocus();
//            return;
//        }

        if(email.isEmpty()){
            this.email.setError("Email is required");
            this.email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            this.password.setError("Password is required");
            this.password.requestFocus();
            return;
        }

        if(password.isEmpty() || password.length() < 6){
            this.password.setError("Password must be at least 6 characters");
            this.password.requestFocus();
            return;
        }

        //encrypt password
        String encryptedPassword = Controller.encrypt(password);

        User user = new User(email, encryptedPassword,"admin","",company,stationId); //create user object with overloaded constructor

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<UserLoginResponse> call = iUserAPI.SaveUser(user); //Station user registration api call

        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                Log.e("Response", "Response code : "+ response.code());
                //log the response code
                if(response.code() == 200){
                    Log.e("Response", "test : "+response.body().getData().getId());
                    Log.e("Response", "test2 : "+response.body().getData().getRole());

                    Toast.makeText(StationRegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(StationRegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(StationRegisterActivity.this, "Registration Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getAllStations() {

        stationList = new ArrayList<>();

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<List<Station>> call = iUserAPI.getAllStation();

        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                Log.e("StationActivity", "Response code " + response.code());

                if (response.code() == 200) {

                    ArrayList<Station> stations = new ArrayList<>();

                    for (Station station : response.body()) {
                        stations.add(new Station(station.getId(), station.getName()));
                    }

                    ArrayAdapter<Station>  dataAdapter = new ArrayAdapter<>(StationRegisterActivity.this,android.R.layout.simple_spinner_item,stations);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city.setAdapter(dataAdapter);


                    city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            Station stn = (Station) adapterView.getItemAtPosition(i);

                            stationId= stn.getId();
//                            Toast.makeText(StationRegisterActivity.this, stn.getId(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });


    }
}