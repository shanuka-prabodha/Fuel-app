package com.example.fuelapp.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fuelapp.Model.JsonPlaceHolderApi;
import com.example.fuelapp.Model.User;
import com.example.fuelapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationRegisterActivity extends AppCompatActivity {

    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.fuelapp.R.layout.activity_station_register);

        resultTextView = findViewById(R.id.result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<User>> call = jsonPlaceHolderApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    resultTextView.setText("Code: " + response.code());
                    return;
                }

                List<User> users = response.body();

                for (User post : users) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    resultTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                resultTextView.setText(t.getMessage());
            }
        });
    }
}