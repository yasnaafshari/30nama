package com.example.cinema.notificationList;

import android.util.Log;

import com.example.cinema.core.Network;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {
    public void getNotifications(String token,NotificationCallback callback) {
        List<NotificationModel> notificationModelList = new ArrayList<>();
        notificationModelList.add(new NotificationModel("new episode", "his dark material"));
        notificationModelList.add(new NotificationModel("new episode", "dexter"));

        callback.onSuccess(notificationModelList);
//        Network.retrofit.create(NotificationService.class).getNotification(token).enqueue(new Callback<List<NotificationModel>>() {
//            @Override
//            public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
//                List<NotificationModel> notificationModelList = response.body();
//                callback.onSuccess(notificationModelList);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<NotificationModel>> call, Throwable t) {
//                callback.onFailure();
//            }
//        });

    }

    public interface NotificationCallback {
        void onSuccess(List<NotificationModel> notificationModels);

        void onFailure();

    }
}
