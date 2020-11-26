package com.example.cinema;

import com.google.gson.annotations.SerializedName;

public class Title {
    @SerializedName("Url")
    public String url;
    @SerializedName("ImageUrl")
    public String imageUrl;
    @SerializedName("Name")
    public String name;

    public Title(String url, String imageUrl, String name) {
        this.url = url;
        this.imageUrl = imageUrl;
        this.name = name;
    }
}
