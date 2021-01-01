package com.example.cinema.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    private List<String> categoriesList;

    public CategoriesAdapter(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View categoriesView = inflater.inflate(R.layout.item_categories, parent, false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(categoriesView);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        String categoriesText = categoriesList.get(position);
        TextView textView = holder.categoriesName;
        textView.setText(categoriesText);

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}
