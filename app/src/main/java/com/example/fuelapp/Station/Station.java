package com.example.fuelapp.Station;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Station model class
public class Station {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ispetrol")
    @Expose
    private Boolean ispetrol;
    @SerializedName("pbike")
    @Expose
    private Integer pbike;
    @SerializedName("pcar")
    @Expose
    private Integer pcar;
    @SerializedName("pother")
    @Expose
    private Integer pother;
    @SerializedName("isdiesel")
    @Expose
    private Boolean isdiesel;
    @SerializedName("dbus")
    @Expose
    private Integer dbus;
    @SerializedName("dvan")
    @Expose
    private Integer dvan;
    @SerializedName("dother")
    @Expose
    private Integer dother;
    @SerializedName("pnextarival")
    @Expose
    private String pnextarival;
    @SerializedName("dnextarival")
    @Expose
    private String dnextarival;

    public Station(String id, String name, Boolean ispetrol, Integer pbike, Integer pcar, Integer pother, Boolean isdiesel, Integer dbus, Integer dvan, Integer dother, String pnextarival, String dnextarival) {
    }

    public Station() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIspetrol() {
        return ispetrol;
    }

    public void setIspetrol(Boolean ispetrol) {
        this.ispetrol = ispetrol;
    }

    public Integer getPbike() {
        return pbike;
    }

    public void setPbike(Integer pbike) {
        this.pbike = pbike;
    }

    public Integer getPcar() {
        return pcar;
    }

    public void setPcar(Integer pcar) {
        this.pcar = pcar;
    }

    public Integer getPother() {
        return pother;
    }

    public void setPother(Integer pother) {
        this.pother = pother;
    }

    public Boolean getIsdiesel() {
        return isdiesel;
    }

    public void setIsdiesel(Boolean isdiesel) {
        this.isdiesel = isdiesel;
    }

    public Integer getDbus() {
        return dbus;
    }

    public void setDbus(Integer dbus) {
        this.dbus = dbus;
    }

    public Integer getDvan() {
        return dvan;
    }

    public void setDvan(Integer dvan) {
        this.dvan = dvan;
    }

    public Integer getDother() {
        return dother;
    }

    public void setDother(Integer dother) {
        this.dother = dother;
    }

    public String getPnextarival() {
        return pnextarival;
    }

    public void setPnextarival(String pnextarival) {
        this.pnextarival = pnextarival;
    }

    public String getDnextarival() {
        return dnextarival;
    }

    public void setDnextarival(String dnextarival) {
        this.dnextarival = dnextarival;
    }

}