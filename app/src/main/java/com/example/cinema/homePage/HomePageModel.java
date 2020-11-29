package com.example.cinema.homePage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomePageModel {
    List<Title> featured;
    List<Title> movies;
    List<Title> tvShows;
    @SerializedName("featured_title")
    SuggestionTitle suggestionTitle;

}
