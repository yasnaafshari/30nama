package com.example.cinema.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.core.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LinksViewHolder extends RecyclerView.ViewHolder {
    String downloadUrl;
    Button episodeButton;

    public LinksViewHolder(@NonNull View itemView) {
        super(itemView);
        episodeButton = itemView.findViewById(R.id.episodeButton);
        episodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(downloadUrl);
                Intent downloadIntent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(downloadIntent);
            }
        });
    }
}
