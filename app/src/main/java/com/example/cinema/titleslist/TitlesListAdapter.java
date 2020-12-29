package com.example.cinema.titleslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.homePage.Title;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TitlesListAdapter extends RecyclerView.Adapter<TitlesListViewHolder> {
    List<Title> titles;
    int titleType;
    TitlesListViewHolder.OnItemListener mOnItemListener;

    public TitlesListAdapter(List<Title> titles, int titleType, TitlesListViewHolder.OnItemListener onItemListener) {
        this.titles = titles;
        this.titleType = titleType;
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public TitlesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_title, parent, false);
        return new TitlesListViewHolder(contactView, titleType, mOnItemListener);

    }

    @Override
    public void onBindViewHolder(@NonNull TitlesListViewHolder holder, int position) {
        holder.textView.setText(titles.get(position).name);
        Picasso.get().load(titles.get(position).imageUrl).into(holder.imageView);
        holder.url = titles.get(position).url;
        holder.id = titles.get(position).id;
        holder.nonce = titles.get(position).nonce;

    }


    @Override
    public int getItemCount() {
        return titles.size();
    }
}
