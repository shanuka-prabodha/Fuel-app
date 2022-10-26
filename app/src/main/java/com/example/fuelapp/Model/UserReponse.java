package com.example.fuelapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


//user response model class
public class UserReponse {

    @SerializedName("success") //success code
    @Expose
    private Boolean success;
    @SerializedName("data") //user object list
    @Expose
    private List<User> data = null;
    @SerializedName("msg") //message
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}