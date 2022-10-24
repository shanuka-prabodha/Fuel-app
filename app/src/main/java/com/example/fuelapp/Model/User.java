package com.example.fuelapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("fueltype")
    @Expose
    private String fueltype;
    @SerializedName("vehicletype")
    @Expose
    private String vehicletype;
    @SerializedName("vehicleno")
    @Expose
    private String vehicleno;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("fuelcompany")
    @Expose
    private String fuelcompany;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFuelcompany() {
        return fuelcompany;
    }

    public void setFuelcompany(String fuelcompany) {
        this.fuelcompany = fuelcompany;
    }

    public User(String email, String password, String role, String city, String fuelcompany) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.city = city;
        this.fuelcompany = fuelcompany;
    }

    public User(String email, String password, String role, String fueltype, String vehicletype, String vehicleno) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.fueltype = fueltype;
        this.vehicletype = vehicletype;
        this.vehicleno = vehicleno;
        this.city = city;
        this.fuelcompany = fuelcompany;
    }
}