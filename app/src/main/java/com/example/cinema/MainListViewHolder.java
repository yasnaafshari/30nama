package com.example.cinema;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainListViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView titlesRecycler;
    public MainListViewHolder(@NonNull View itemView) {
        super(itemView);
        titlesRecycler = itemView.findViewById(R.id.titlesRecycler);


    }

}
