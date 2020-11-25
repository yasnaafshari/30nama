package com.example.cinema.details;

import com.google.gson.annotations.SerializedName;

public class TitlesDetailsModel {
    public String title;
    public String story;
    @SerializedName("image_url")
    public String imageUrl;

}
