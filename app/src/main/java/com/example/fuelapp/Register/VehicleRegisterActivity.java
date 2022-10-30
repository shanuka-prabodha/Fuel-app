package com.example.fuelapp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Login.LoginActivity;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.User;
import com.example.fuelapp.Model.UserLoginResponse;
import com.example.fuelapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRegisterActivity extends AppCompatActivity {

    //buttons and text fields initialization
    private TextView vehicleNo;
    private Spinner fuelType;
    private Spinner vehicleType;
    private TextView email;
    private TextView password;
    private Button register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_vehicle_register);
        
        vehicleNo = findViewById(R.id.editTextTextPersonName);
        fuelType = findViewById(R.id.spnFuel);
        vehicleType = findViewById(R.id.spnVehicle);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtpassword);
        register = findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();

            }
        });
        
    }

    //vehicle user registration method
    protected void onRegister() {
        String vehicleNo = this.vehicleNo.getText().toString();
        String fuelType = this.fuelType.getSelectedItem().toString();
        String vehicleType = this.vehicleType.getSelectedItem().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        //empty field validation
        if(vehicleNo.isEmpty()){
            this.vehicleNo.setError("Vehicle No is required");
            this.vehicleNo.requestFocus();
            return;
        }
        
        if(email.isEmpty()){
        this.email.setError("Email is required");
        this.email.requestFocus();
        return;
        }

        if(password.isEmpty() || password.length() < 6){
        this.password.setError("Password must be at least 6 characters");
        this.password.requestFocus();
        return;
         }
        String encryptedPassword = Controller.encrypt(password);


        User user = new User(email, encryptedPassword,"user",fuelType,vehicleType,vehicleNo , "");//create user object with overloaded constructor

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<UserLoginResponse> call = iUserAPI.SaveUser(user); // save user details to the database

        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                Log.e("Response", "Response code : "+ response.code());//response code print
                if(response.code() == 200){
                    Log.e("Response", "test : "+response.body().getData().getId());
                    Log.e("Response", "test2 : "+response.body().getData().getRole());

                    Toast.makeText(VehicleRegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    //display a success message

                    Intent intent = new Intent(VehicleRegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(VehicleRegisterActivity.this, "Registration Fail", Toast.LENGTH_SHORT).show();
                //display a fail message
            }
        });

        
        
    }
    
}