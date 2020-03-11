package com.example.skylink.Training;

public class Training_Model {

    String title,start_date,end_date,venue,details;

    public Training_Model(String title, String start_date, String end_date, String venue, String details) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.venue = venue;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}


