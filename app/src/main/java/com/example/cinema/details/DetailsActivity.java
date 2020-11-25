package com.example.cinema.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinema.HomePageModel;
import com.example.cinema.HomePageService;
import com.example.cinema.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cinama.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TitlesDetailsService service = retrofit.create(TitlesDetailsService.class);
        service.getTitlesDetails(getIntent().getStringExtra("url")).enqueue(new Callback<TitlesDetailsModel>() {
            @Override
            public void onResponse(Call<TitlesDetailsModel> call, Response<TitlesDetailsModel> response) {
                TitlesDetailsModel titlesDetailsModel = response.body();
                TextView story = findViewById(R.id.detailsStory);
                TextView title = findViewById(R.id.detailsTitle);
                ImageView imageView = findViewById(R.id.detailsImage);
                Picasso.get().load(titlesDetailsModel.imageUrl).into(imageView);
                story.setText(titlesDetailsModel.story);
                title.setText(titlesDetailsModel.title);
            }

            @Override
            public void onFailure(Call<TitlesDetailsModel> call, Throwable t) {

            }
        });

    }
}