package com.example.cinema.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
    TextView episodeLink;
    String downloadUrl;
    TextView episodeName;

    public LinksViewHolder(@NonNull View itemView) {
        super(itemView);
        episodeLink = itemView.findViewById(R.id.episodeLink);
        episodeName = itemView.findViewById(R.id.episodeName);

        episodeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(downloadUrl);
                Intent downloadIntent  = new Intent(Intent.ACTION_VIEW,uri);
                v.getContext().startActivity(downloadIntent);
            }
        });
    }
}
