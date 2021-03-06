package com.example.cinema.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksViewHolder> {
    List<TvShowsModel.Episode> episodes;

    public LinksAdapter(List<TvShowsModel.Episode> episodes) {
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public LinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_links, parent, false);
        return new LinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinksViewHolder holder, int position) {
        TvShowsModel.Episode episode = episodes.get(position);
        holder.episodeButton.setText(episode.name);
        holder.downloadUrl = episode.downloadLink;
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }
}
