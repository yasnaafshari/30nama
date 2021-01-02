package com.example.cinema.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    List<NewsModel> newsList;

    public NewsAdapter(List<NewsModel> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context newsContext = parent.getContext();
        LayoutInflater newsInflater = LayoutInflater.from(newsContext);
        View newsView = newsInflater.inflate(R.layout.item_news, parent,false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(newsView);
        return  newsViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel news = newsList.get(position);
        TextView newsText = holder.nText;
        newsText.setText(news.nText);
        Picasso.get().load(news.url).into(holder.nImageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
