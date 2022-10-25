package com.example.fuelapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.LoginRequest;
import com.example.fuelapp.Model.UserLoginResponse;
import com.example.fuelapp.R;
import com.example.fuelapp.Register.RegisterActivity;
import com.example.fuelapp.Register.VehicleRegisterActivity;
import com.example.fuelapp.Station.StationActivity;
import com.example.fuelapp.VehicleOwner.SearchActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private TextView signup;
    private Button loginbutton;
    private TextView username;
    private TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup=findViewById(R.id.signup);
        loginbutton = findViewById(R.id.btnlogin) ;
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

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, StationActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }

    private void onSignup(){

        String user = username.getText().toString();
        String pass = password.getText().toString();

        LoginRequest loginRequest = new LoginRequest(user,pass);

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<UserLoginResponse> call = iUserAPI.login(loginRequest);


        call.enqueue(new Callback<UserLoginResponse>() {

            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                Log.e("RegisterActivity", "Response code " +response.code());
       
                if (response.code() == 200) {
                    if (response.body().getData().getRole().contentEquals("user")) {
                        Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                        intent.putExtra("id",response.body().getData().getId());
                        intent.putExtra("email",response.body().getData().getEmail());
                        startActivity(intent);

                    }else{
                        Intent intent = new Intent(LoginActivity.this, StationActivity.class);
                        startActivity(intent);
                    }

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Log.e("RegisterActivity", String.valueOf(t));
            }
        });


    }


}