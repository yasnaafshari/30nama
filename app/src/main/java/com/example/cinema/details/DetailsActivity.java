package com.example.cinema.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cinema.R;
import com.example.cinema.homePage.TitlesListFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DetailPageRepository yDetailPageRepository = new DetailPageRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int titleType = getIntent().getIntExtra("type", 0);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("token", null);
        String url = getIntent().getStringExtra("url");
        setContentView(R.layout.activity_details);
        if (titleType == TitlesListFragment.TITLE_TYPE_MOVIE) {

            yDetailPageRepository.getDetailedPage(getIntent().getStringExtra("url"), new DetailPageRepository.DetailsPageCallBack() {
                @Override
                public void onSuccess(TvShowsModel.TitlesDetailsModel titlesDetailsModel) {
                    TextView story = findViewById(R.id.detailsStory);
                    TextView title = findViewById(R.id.detailsTitle);
                    ImageView imageView = findViewById(R.id.detailsImage);
                    Picasso.get().load(titlesDetailsModel.imageUrl).into(imageView);
                    story.setText(titlesDetailsModel.story);
                    title.setText(titlesDetailsModel.title);

                }


                @Override
                public void onFailure(String onFailureNote) {

                }
            });


        } else if (titleType == TitlesListFragment.TITLE_TYPE_TV_SHOW) {

            yDetailPageRepository.getTvShows(url, token, new DetailPageRepository.TvShowsCallBack() {
                @Override
                public void onSuccess(TvShowsModel tvShowsModel) {
                    TextView story = findViewById(R.id.detailsStory);
                    TextView title = findViewById(R.id.detailsTitle);
                    ImageView imageView = findViewById(R.id.detailsImage);
                    Picasso.get().load(tvShowsModel.details.imageUrl).into(imageView);
                    story.setText(tvShowsModel.details.story);
                    title.setText(tvShowsModel.details.title);
                    Spinner seasonSpinner = findViewById(R.id.seasonSpinner);
                    List<String> seasonNames = new ArrayList<>();
                    List<String> seasonDescriptions = new ArrayList<>();
                    for (TvShowsModel.SeasonPack seasonPack : tvShowsModel.mSeasonPack) {
                        seasonNames.add(seasonPack.name);
                    }
                    for (TvShowsModel.Season season : tvShowsModel.mSeasonPack.get(0).seasons) {
                        seasonDescriptions.add(season.descriptions.get(3));
                    }
                    Spinner qualitySpinner = findViewById(R.id.qualitySpinner);

                    ArrayAdapter<String> qualityAdapter = new ArrayAdapter<>(DetailsActivity.this, android.R.layout.simple_spinner_item, seasonDescriptions);
                    qualityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    qualitySpinner.setAdapter(qualityAdapter);

                    ArrayAdapter<String> adapter = new ArrayAdapter(DetailsActivity.this, android.R.layout.simple_spinner_item, seasonNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    seasonSpinner.setAdapter(adapter);
                    RecyclerView linksRecycler = findViewById(R.id.linksRecycler);
                    linksRecycler.setAdapter(new LinksAdapter(tvShowsModel.mSeasonPack.get(0).seasons.get(0).downloadLinks));
                    linksRecycler.setLayoutManager(new LinearLayoutManager(DetailsActivity.this,RecyclerView.VERTICAL,false));

                }


                @Override
                public void onFailure(String onFailureNote) {

                }
            });
        }
    }
}