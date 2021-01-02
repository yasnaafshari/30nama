package com.example.cinema.categories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinema.R;
import com.example.cinema.core.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class CategoriesFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setUpCategories();
    }

    private void setUpCategories() {
        RecyclerView recyclerView = getView().findViewById(R.id.categoriesRecyclerView);
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Anime");
        categoriesList.add("Film");
        categoriesList.add("TV shows");
        categoriesList.add("Animation");
        categoriesList.add("Documentary");
        categoriesList.add("Mini Series");
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesList);
        recyclerView.setAdapter(categoriesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onRetryClicked() {
        setUpCategories();
    }
}