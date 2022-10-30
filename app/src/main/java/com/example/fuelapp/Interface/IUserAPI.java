package com.example.fuelapp.Interface;

import com.example.fuelapp.Model.LoginRequest;
import com.example.fuelapp.Model.Queue;
import com.example.fuelapp.Model.QueueAdd;
import com.example.fuelapp.Model.QueueList;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserAPI {
    
    //user login api call with LoginRequest Body
    @POST("api/user/login")
    Call<UserLoginResponse> login(@Body LoginRequest loginRequest);

    //get all users api call
    @GET("api/user")
    Call<UserReponse> getUsers();

    //get station list api call
    @GET("api/Station")
    Call<List<Station>> getAllStation();

    //get single user api call
    @GET("api/user/get/{id}")
    Call<User> getOneUser(@Path("id") String id);

    //ligin user api call
    @POST("api/user")
    Call<UserLoginResponse> SaveUser(@Body User user);

    //get single station api call
    @GET("api/station/get/{id}")
    Call<StationResponse> getStations(@Path("id") String id);

    @POST("api/station/updateQue/{id}/{type}")
    Call<StationResponse> updateStationQue(@Path("id") String id,@Path("type") int type ,@Body User user);

    @PUT("api/station/update/{id}/{available}/{type}/{date}")
    Call<StationResponse> updateStationDetails(@Path("id") String id,@Path("available") Boolean available,@Path("type") String type,@Path("date") String date);

    @GET("api/queue/{station}/{fueltype}")
    Call<QueueList> getQueue(@Path("station") String station, @Path("fueltype") String fueltype);

    @POST("api/queue")
    Call<QueueAdd> addQueue(@Body Queue queue);

}
