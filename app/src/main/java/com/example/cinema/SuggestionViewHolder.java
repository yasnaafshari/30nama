package com.example.cinema;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SuggestionViewHolder extends RecyclerView.ViewHolder {
    TextView yTextView;
    ImageView yImageView;

    public SuggestionViewHolder(@NonNull View itemView) {
        super(itemView);
        yImageView = itemView.findViewById(R.id.suggestionImage);
        yTextView = itemView.findViewById(R.id.suggestionText);
    }
}
