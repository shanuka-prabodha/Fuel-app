package com.example.fuelapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Database.DBHandler;
import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.LocalStationLogin;
import com.example.fuelapp.Model.LocalVehicleLogin;
import com.example.fuelapp.Model.LoginRequest;
import com.example.fuelapp.Model.UserLoginResponse;
import com.example.fuelapp.R;
import com.example.fuelapp.Register.RegisterActivity;
import com.example.fuelapp.Register.VehicleRegisterActivity;
import com.example.fuelapp.Station.StationActivity;
import com.example.fuelapp.VehicleOwner.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

//buttons and text fields initialization

    private TextView signup;
    private Button loginbutton;
    private TextView username;
    private TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = findViewById(R.id.signup);
        loginbutton = findViewById(R.id.btnlogin);
        username = findViewById(R.id.loginUserNameId);
        password = findViewById(R.id.loginPasswordId);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignup();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    //singnup function
    private void onSignup() {

        String user = username.getText().toString();
        String pass = password.getText().toString();

        String encryptedPassword = Controller.encrypt(pass);
        //craete login user object
        LoginRequest loginRequest = new LoginRequest(user, encryptedPassword);


        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        //call login api
        Call<UserLoginResponse> call = iUserAPI.login(loginRequest);


        call.enqueue(new Callback<UserLoginResponse>() {


            //api call response
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                Log.e("RegisterActivity", "Response code " + response.code());

                //check response code
                //depend on response redirect user to relevent page or display error messages

                if (response.code() == 200) {
                    //save user id in local storage
                    username.setText("");
                    password.setText("");

                    StorageManager storeManager = new StorageManager(getApplicationContext());
                    storeManager.setToken(response.body().getData().getId());

                    if (response.body().getData().getRole().contentEquals("user")) {

                        storeManager.setFuelType(response.body().getData().getFueltype());
                        DBHandler dbHandler = new DBHandler(LoginActivity.this);
                        long val = 0;
                        ArrayList<LocalVehicleLogin> localVehicleLogins = dbHandler.readVehicleLogins();
                        boolean isExist = false;
                        //Check user is already save in the local database.
                        for (LocalVehicleLogin vehicleLogin : localVehicleLogins) {
                            if (vehicleLogin.getEmail().toLowerCase().contains(user.toLowerCase())) {
                                isExist = true;
                            }
                        }

                        // user not exist save the user in the local database
                        if (!isExist) {
                            val = dbHandler.saveVehicleOwner(
                                    response.body().getData().getEmail(),
                                    pass,
                                    response.body().getData().getFueltype(),
                                    response.body().getData().getId()

                            );
                        }

                        if (val > 0) {
//                            Toast.makeText(LoginActivity.this, "Vehicle login Successfully", Toast.LENGTH_SHORT).show();

                        } else {
//                            Toast.makeText(LoginActivity.this, "Not Successfully", Toast.LENGTH_SHORT).show();
                        }


                        Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                        intent.putExtra("id", response.body().getData().getId());
                        intent.putExtra("email", response.body().getData().getEmail());
                        startActivity(intent);

                    } else {

                        storeManager.setAdminStation(response.body().getData().getStation());

                       // Toast.makeText(LoginActivity.this, response.body().getData().getStation(), Toast.LENGTH_SHORT).show();
                        DBHandler dbHandler = new DBHandler(LoginActivity.this);

                        long val = 0;
                        ArrayList<LocalStationLogin> localStationLogins = dbHandler.readStationLogins();

                        boolean isExist = false;
                        for (LocalStationLogin vehicleLogin : localStationLogins) {
                            if (vehicleLogin.getEmail().toLowerCase().contains(user.toLowerCase())) {
                                isExist = true;
                            }
                        }
                        if (!isExist) {
                            val = dbHandler.saveStationOwner(
                                    response.body().getData().getEmail(),
                                    pass,
                                    response.body().getData().getId()

                            );
                        }


                        if (val > 0) {
//                            Toast.makeText(LoginActivity.this, "Vehicle login Successfully", Toast.LENGTH_SHORT).show();

                        } else {
//                            Toast.makeText(LoginActivity.this, "Not Successfully", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(LoginActivity.this, StationActivity.class);
                        startActivity(intent);
                    }

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }

            //api call error
            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });


    }


}