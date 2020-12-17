package com.example.cinema.details;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.core.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LinksViewHolder extends RecyclerView.ViewHolder {
    String linkText;
    TextView episodeLink;
    String url;
    TextView episodeName;

    public LinksViewHolder(@NonNull View itemView) {
        super(itemView);
        episodeLink = itemView.findViewById(R.id.episodeLink);
        episodeName = itemView.findViewById(R.id.episodeName);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        String token = preferences.getString("token", null);

        episodeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
