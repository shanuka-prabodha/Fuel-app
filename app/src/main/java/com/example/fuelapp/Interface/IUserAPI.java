package com.example.fuelapp.Interface;

import com.example.fuelapp.Model.LoginRequest;
import com.example.fuelapp.Model.Station;
import com.example.fuelapp.Model.UserReponse;
import com.example.fuelapp.Model.User;
import com.example.fuelapp.Model.UserLoginResponse;
import com.example.fuelapp.Station.StationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUserAPI {

    @POST("api/user/login")
    Call<UserLoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/user")
    Call<UserReponse> getUsers();

    @GET("api/Station")
    Call<List<Station>> getStation();

    @GET("api/user/get/{id}")
    Call<User> getOneUser(@Path("id") String id);

    @POST("api/user")
    Call<UserLoginResponse> SaveUser(@Body User user);

    @GET("api/station/get/{id}")
    Call<StationResponse> getStations(@Path("id") String id);
}
