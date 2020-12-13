package com.example.cinema.homePage;

import com.google.gson.annotations.SerializedName;

public class Title {
    @SerializedName("Url")
    public String url;
    @SerializedName("ImageUrl")
    public String imageUrl;
    @SerializedName("Name")
    public String name;
    public int id;
    public String nonce;

    public Title(String url, String imageUrl, String name, int id) {
        this.url = url;
        this.imageUrl = imageUrl;
        this.name = name;
        this.id = id;
    }
}
