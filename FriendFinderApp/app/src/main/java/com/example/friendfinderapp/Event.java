package com.example.friendfinderapp;

public class Event {
    private String event_name, event_date;
    private int event_image;

    public Event(String event_name, String event_date, int event_image) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_image = event_image;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public int getEvent_image() {
        return event_image;
    }

    public void setEvent_image(int event_image) {
        this.event_image = event_image;
    }
}
