package com.example.fuelapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Queue {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("vehicleno")
    @Expose
    private String vehicleno;
    @SerializedName("fueltype")
    @Expose
    private String fueltype;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;

    public Queue(String station,String userid, String vehicleno, String fuelType, String queJoin, String toString) {

        this.station = station;
        this.userid = userid;
        this.vehicleno = vehicleno;
        this.fueltype = fuelType;
        this.start = queJoin;
        this.end = toString;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}