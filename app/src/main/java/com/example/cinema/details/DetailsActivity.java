package com.example.cinema.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinema.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    DetailPageRepository yDetailPageRepository = new DetailPageRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        yDetailPageRepository.getDetailedPage(getIntent().getStringExtra("url"), new DetailPageRepository.DetailsPageCallBack() {
            @Override
            public void onSuccess(TitlesDetailsModel titlesDetailsModel) {
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


    }
}