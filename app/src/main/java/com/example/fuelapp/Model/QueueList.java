package com.example.fuelapp.Model;

import java.util.List;


import com.example.fuelapp.Model.Queue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class QueueList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Queue> data = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Queue> getData() {
        return data;
    }

    public void setData(List<Queue> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}