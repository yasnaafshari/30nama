package com.example.cinema.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {
TextView nText;
ImageView nImageView;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        nText = itemView.findViewById(R.id.newsTitle);
        nImageView = itemView.findViewById(R.id.newsImage);
    }
}
