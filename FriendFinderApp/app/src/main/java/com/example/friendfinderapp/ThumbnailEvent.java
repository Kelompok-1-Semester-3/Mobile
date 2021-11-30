package com.example.friendfinderapp;

public class ThumbnailEvent {
    private String thumbnail_event_name, thumbnail_event_data, category;
    private int thumbnail_image;

    public ThumbnailEvent(String thumbnail_event_name, String thumbnail_event_data, String category, int thumbnail_image) {
        this.thumbnail_event_name = thumbnail_event_name;
        this.thumbnail_event_data = thumbnail_event_data;
        this.category = category;
        this.thumbnail_image = thumbnail_image;
    }

    public String getThumbnail_event_name() {
        return thumbnail_event_name;
    }

    public void setThumbnail_event_name(String thumbnail_event_name) {
        this.thumbnail_event_name = thumbnail_event_name;
    }

    public String getThumbnail_event_data() {
        return thumbnail_event_data;
    }

    public void setThumbnail_event_data(String thumbnail_event_data) {
        this.thumbnail_event_data = thumbnail_event_data;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(int thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }
}
