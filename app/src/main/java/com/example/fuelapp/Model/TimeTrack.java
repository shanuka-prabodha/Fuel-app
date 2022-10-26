package com.example.fuelapp.Model;

//Time track model class
public class TimeTrack {

    private String name;
    private String start;
    private String exit;
    private String wait;

    public TimeTrack(String name, String start, String exit, String wait) {
        this.name = name;
        this.start = start;
        this.exit = exit;
        this.wait = wait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getWait() {
        return wait;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }
}

