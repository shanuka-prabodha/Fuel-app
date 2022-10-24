package com.example.fuelapp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelapp.Interface.IUserAPI;
import com.example.fuelapp.Model.Controller;
import com.example.fuelapp.Model.User;
import com.example.fuelapp.Model.UserLoginResponse;
import com.example.fuelapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationRegisterActivity extends AppCompatActivity {

    private TextView city;
    private Spinner company;
    private TextView email;
    private TextView password;
    private Button register;

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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(StationRegisterActivity.this, "Register", Toast.LENGTH_SHORT).show();
                onRegister();

            }
        });

    }

    public void onRegister() {

        String city = this.city.getText().toString();
        String company = this.company.getSelectedItem().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        if(city.isEmpty()){
            this.city.setError("City is required");
            this.city.requestFocus();
            return;
        }

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

        User user = new User(email, password,"admin",city,company);

        IUserAPI iUserAPI = Controller.getRetrofit().create(IUserAPI.class);
        Call<UserLoginResponse> call = iUserAPI.SaveUser(user);

        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                Log.e("Response", "Response code : "+ response.code());
                if(response.code() == 200){
                    Log.e("Response", "test : "+response.body().getData().getId());
                    Log.e("Response", "test2 : "+response.body().getData().getRole());

                    Toast.makeText(StationRegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(StationRegisterActivity.this, "Registration Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}