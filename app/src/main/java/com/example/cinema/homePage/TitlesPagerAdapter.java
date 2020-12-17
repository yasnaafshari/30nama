package com.example.cinema.homePage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TitlesPagerAdapter extends FragmentStatePagerAdapter {
    HomePageModel yHomePageModel;


    public TitlesPagerAdapter(@NonNull FragmentManager fm, int behavior, HomePageModel homePageModel) {
        super(fm, behavior);
        yHomePageModel = homePageModel;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                List<Title> suggestion = new ArrayList<>();
                suggestion.add(new Title(yHomePageModel.suggestionTitle.url, yHomePageModel.suggestionTitle.imageUrl, yHomePageModel.suggestionTitle.title, 0));
                return new TitlesListFragment(suggestion, TitlesListFragment.TITLE_TYPE_MOVIE);
            case 3:
                return new TitlesListFragment(yHomePageModel.featured, TitlesListFragment.TITLE_TYPE_MOVIE);
            case 1:
                return new TitlesListFragment(yHomePageModel.movies, TitlesListFragment.TITLE_TYPE_MOVIE);
            case 2:
                return new TitlesListFragment(yHomePageModel.tvShows, TitlesListFragment.TITLE_TYPE_TV_SHOW);
            default:
                return new TitlesListFragment(yHomePageModel.tvShows, TitlesListFragment.TITLE_TYPE_TV_SHOW);

        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SUGGESTION";
            case 1:
                return "MOVIES";
            case 2:
                return "TV SHOWS";
            default:
                return "FEATURED";
        }
    }
}
