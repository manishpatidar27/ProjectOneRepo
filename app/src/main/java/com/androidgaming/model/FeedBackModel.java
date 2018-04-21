package com.androidgaming.model;

/**
 * Created by Manish Patidar on 21-04-2018.
 */
public class FeedBackModel {

    String  name;
    String  patron_comment;
    String  date;
    String  location;
    String  time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatron_comment() {
        return patron_comment;
    }

    public void setPatron_comment(String patron_comment) {
        this.patron_comment = patron_comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
