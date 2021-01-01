package com.example.cinema.categories;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {
    TextView categoriesName;
    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        categoriesName = itemView.findViewById(R.id.categoriesText);

    }
}
