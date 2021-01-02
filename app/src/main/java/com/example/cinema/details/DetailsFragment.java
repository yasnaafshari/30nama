package com.example.cinema.details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cinema.R;
import com.example.cinema.core.BaseFragment;
import com.example.cinema.homePage.TitlesListFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends BaseFragment {
    Spinner qualitySpinner;
    RecyclerView linksRecycler;
    Spinner seasonSpinner;
    DetailsViewModel mDetailsViewModel;

    public static DetailsFragment newInstance(String url, int titleType) {

        Bundle args = new Bundle();
        args.putInt("type", titleType);
        args.putString("url", url);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDetailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        qualitySpinner = getView().findViewById(R.id.qualitySpinner);
        seasonSpinner = getView().findViewById(R.id.seasonSpinner);
        linksRecycler = getView().findViewById(R.id.linksRecycler);
        setUpDetails();
    }

    private void setUpDetails() {

        int titleType = getArguments().getInt("type", 0);
        String token = getToken();
        String url = getArguments().getString("url");

        fetchDetails(titleType, token, url);
    }

    private void fetchDetails(int titleType, String token, String url) {
        mDetailsViewModel.getOnError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showError(s);
            }
        });
        if (titleType == TitlesListFragment.TITLE_TYPE_MOVIE) {

            fetchMovies(url);

        } else if (titleType == TitlesListFragment.TITLE_TYPE_TV_SHOW) {

            fetchTvShows(token, url);
        }
    }

    private void fetchMovies(String url) {
        mDetailsViewModel.fetchTitleDetails(url).observe(getViewLifecycleOwner(), new Observer<TvShowsModel.TitlesDetailsModel>() {
            @Override
            public void onChanged(TvShowsModel.TitlesDetailsModel titlesDetailsModel) {
                setTitlesDetails(titlesDetailsModel);
            }
        });
    }

    private void fetchTvShows(String token, String url) {
        mDetailsViewModel.fetchTvShows(token, url).observe(getViewLifecycleOwner(), new Observer<TvShowsModel>() {
            @Override
            public void onChanged(TvShowsModel tvShowsModel) {
                setTitlesDetails(tvShowsModel.details);
                setUpSeasonsSpinner(tvShowsModel);
                setUpQualitySpinner(tvShowsModel, 0);
                setUpDownloadLinks(tvShowsModel, 0, 0);
            }
        });
    }

    private String getToken() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getString("token", null);
    }

    @Override
    public void onRetryClicked() {
        setUpDetails();
    }

    private void handleQualitySelection(TvShowsModel tvShowsModel) {
        qualitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setUpDownloadLinks(tvShowsModel, seasonSpinner.getSelectedItemPosition(), position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void handleSeasonSelection(TvShowsModel tvShowsModel) {
        seasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setUpQualitySpinner(tvShowsModel, position);
                setUpDownloadLinks(tvShowsModel, position, 0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpSeasonsSpinner(TvShowsModel tvShowsModel) {
        List<String> seasonNames = getSeasonNames(tvShowsModel);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), R.layout.item_details_selection_spinner, seasonNames);
        adapter.setDropDownViewResource(R.layout.item_details_selection_spinner);
        seasonSpinner.setAdapter(adapter);
        handleSeasonSelection(tvShowsModel);
    }

    private List<String> getSeasonNames(TvShowsModel tvShowsModel) {
        List<String> seasonNames = new ArrayList<>();
        for (TvShowsModel.SeasonPack seasonPack : tvShowsModel.mSeasonPack) {
            seasonNames.add(seasonPack.name);
        }
        return seasonNames;
    }

    private void setTitlesDetails(TvShowsModel.TitlesDetailsModel details) {
        TextView story = getView().findViewById(R.id.detailsStory);
        TextView title = getView().findViewById(R.id.detailsTitle);
        ImageView imageView = getView().findViewById(R.id.detailsImage);
        Picasso.get().load(details.imageUrl).into(imageView);
        story.setText(details.story);
        title.setText(details.title);
    }


    private void setUpDownloadLinks(TvShowsModel tvShowsModel, int position, int qualityPosition) {
        linksRecycler.setAdapter(new LinksAdapter(tvShowsModel.mSeasonPack.get(position).seasons.get(qualityPosition).downloadLinks));
        linksRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void setUpQualitySpinner(TvShowsModel tvShowsModel, int position) {

        List<String> seasonDescriptions = getSeasonQuality(tvShowsModel, position);
        ArrayAdapter<String> qualityAdapter = new ArrayAdapter(getContext(), R.layout.item_details_selection_spinner, seasonDescriptions);
        qualityAdapter.setDropDownViewResource(R.layout.item_details_selection_spinner);
        qualitySpinner.setAdapter(qualityAdapter);
        handleQualitySelection(tvShowsModel);
    }

    private List<String> getSeasonQuality(TvShowsModel tvShowsModel, int position) {
        List<String> seasonDescriptions = new ArrayList<>();
        for (TvShowsModel.Season season : tvShowsModel.mSeasonPack.get(position).seasons) {

            seasonDescriptions.add(season.descriptions.get(3));
        }
        return seasonDescriptions;
    }
}