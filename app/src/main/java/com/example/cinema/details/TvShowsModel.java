package com.example.cinema.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TvShowsModel {
    public TitlesDetailsModel details;
    @SerializedName("season_packs")
    public List<SeasonPack> mSeasonPack;

    public class TitlesDetailsModel {
        public String title;
        public String story;
        @SerializedName("image_url")
        public String imageUrl;
        public String nonce;
    }

    public class SeasonPack {
        String name;
        List<Season> seasons;

    }

    public class Season {
        @SerializedName("download_links")
        List<Episode> downloadLinks;
        List<String> descriptions;

    }

    public class Episode {
        @SerializedName("download_link")
        String downloadLink;
        String name;
    }
}

