package com.example.fuelapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class QueueAdd {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Queue data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Queue getData() {
        return data;
    }

    public void setData(Queue data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}