package com.example.cinema.notificationList;

public class NotificationModel {
    public String title;
    public String text;
    public boolean is_read;

    public NotificationModel(String title, String text,boolean is_read) {
        this.title = title;
        this.text = text;
        this.is_read = is_read;

    }
}
