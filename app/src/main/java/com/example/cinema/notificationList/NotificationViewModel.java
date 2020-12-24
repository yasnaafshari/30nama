package com.example.cinema.notificationList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.core.DataCallBack;

import java.util.List;

public class NotificationViewModel extends ViewModel {
    MutableLiveData<List<NotificationModel>> notificationLiveData = new MutableLiveData<>();

    public LiveData<List<NotificationModel>> getNotifications(String token){
        NotificationRepository repository = new NotificationRepository();

        if(notificationLiveData.getValue()== null){
        repository.getNotifications(token, new DataCallBack<List<NotificationModel>>() {
            @Override
            public void onSuccess(List<NotificationModel> data) {
                notificationLiveData.setValue(data);
            }

            @Override
            public void onFailure(String onFailureNote) {

            }
        });}
        return notificationLiveData;
    }


}
