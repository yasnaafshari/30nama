package com.example.cinema.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cinema.R;
import com.example.cinema.core.DataCallBack;
import com.example.cinema.homePage.TitlesListFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DetailPageRepository yDetailPageRepository = new DetailPageRepository();
    Spinner qualitySpinner;
    RecyclerView linksRecycler;
    Spinner seasonSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        qualitySpinner = findViewById(R.id.qualitySpinner);
        seasonSpinner = findViewById(R.id.seasonSpinner);
        linksRecycler = findViewById(R.id.linksRecycler);
        int titleType = getIntent().getIntExtra("type", 0);
        String token = getToken();
        String url = getIntent().getStringExtra("url");

        fetchDetails(titleType, token, url);
    }

    private void fetchDetails(int titleType, String token, String url) {
        if (titleType == TitlesListFragment.TITLE_TYPE_MOVIE) {

            fetchTitleDetails();

        } else if (titleType == TitlesListFragment.TITLE_TYPE_TV_SHOW) {

            fetchTvShows(token, url);
        }
    }

    private String getToken() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("token", null);
    }

    private void fetchTvShows(String token, String url) {
        yDetailPageRepository.getTvShows(url, token, new DataCallBack<TvShowsModel>() {
            @Override
            public void onSuccess(TvShowsModel tvShowsModel) {
                setTitlesDetails(tvShowsModel.details);
                setUpSeasonsSpinner(tvShowsModel);
                setUpQualitySpinner(tvShowsModel, 0);
                setUpDownloadLinks(tvShowsModel,0,0);


            }


            @Override
            public void onFailure(String onFailureNote) {

            }
        });
    }

    private void handleQualitySelection(TvShowsModel tvShowsModel) {
        qualitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setUpDownloadLinks(tvShowsModel,seasonSpinner.getSelectedItemPosition(), position);
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
                setUpDownloadLinks(tvShowsModel,position,0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpSeasonsSpinner(TvShowsModel tvShowsModel) {
        List<String> seasonNames = getSeasonNames(tvShowsModel);
        ArrayAdapter<String> adapter = new ArrayAdapter(DetailsActivity.this, android.R.layout.simple_spinner_item, seasonNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
        TextView story = findViewById(R.id.detailsStory);
        TextView title = findViewById(R.id.detailsTitle);
        ImageView imageView = findViewById(R.id.detailsImage);
        Picasso.get().load(details.imageUrl).into(imageView);
        story.setText(details.story);
        title.setText(details.title);
    }

    private void fetchTitleDetails() {
        yDetailPageRepository.getDetailedPage(getIntent().getStringExtra("url"), new DataCallBack<TvShowsModel.TitlesDetailsModel>() {
            @Override
            public void onSuccess(TvShowsModel.TitlesDetailsModel titlesDetailsModel) {
                setTitlesDetails(titlesDetailsModel);

            }


            @Override
            public void onFailure(String onFailureNote) {

            }
        });
    }

    private void setUpDownloadLinks(TvShowsModel tvShowsModel,int position, int qualityPosition) {
        linksRecycler.setAdapter(new LinksAdapter(tvShowsModel.mSeasonPack.get(position).seasons.get(qualityPosition).downloadLinks));
        linksRecycler.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, RecyclerView.VERTICAL, false));
    }

    private void setUpQualitySpinner(TvShowsModel tvShowsModel, int position) {

        List<String> seasonDescriptions = getSeasonQuality(tvShowsModel,position);
        ArrayAdapter<String> qualityAdapter = new ArrayAdapter<>(DetailsActivity.this, android.R.layout.simple_spinner_item, seasonDescriptions);
        qualityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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