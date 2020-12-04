package com.example.cinema.profile;

import com.google.gson.annotations.SerializedName;

public class ProfileModel {
    @SerializedName("name")
    String username;
    @SerializedName("subscription_status")
    String subscriptionStatus;

    public ProfileModel(String username, String subscriptionStatus) {
        this.username = username;
        this.subscriptionStatus = subscriptionStatus;
    }
}
