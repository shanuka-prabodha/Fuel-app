package com.example.fuelapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


//Usr login response model class
public class UserLoginResponse {

    @SerializedName("token") //user login token
    @Expose
    private String token;
    @SerializedName("success") //response code
    @Expose
    private Boolean success;
    @SerializedName("data") //user object
    @Expose
    private User data = null;
    @SerializedName("msg") //message
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
