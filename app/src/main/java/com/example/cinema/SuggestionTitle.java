package com.example.cinema;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuggestionTitle {
    public String url;
    public String title;
    @SerializedName("image_url")
    public String imageUrl;
    List<String> highlights;
    @SerializedName("imdb_score")
    public double imdbScore;
    @SerializedName("cinema_score")
    public double cinemaScore;
}
