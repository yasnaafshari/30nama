package com.example.cinema.notificationList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NotificationService {
    @GET("notifications")
    Call<List<NotificationModel>> getNotification(@Header("token") String token);


}
