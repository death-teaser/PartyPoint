package com.example.root.partypoint;

import java.io.Serializable;

/**
 * Created by root on 30/9/17.
 */

public class restaurantInfo implements Serializable {
    String name;
    String url;
    String cuisines;
    String location;
    String average_cost_for_two;
    String photos_url;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setAverage_cost_for_two(String average_cost_for_two) {
        this.average_cost_for_two = average_cost_for_two;
    }

    public String getAverage_cost_for_two() {
        return average_cost_for_two;
    }

    public void setPhotos_url(String photos_url) {
        this.photos_url = photos_url;
    }

    public String getPhotos_url() {
        return photos_url;
    }
}
