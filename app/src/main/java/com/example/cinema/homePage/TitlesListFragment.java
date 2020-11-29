package com.example.cinema.homePage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinema.R;
import com.example.cinema.titleslist.TitlesListAdapter;

import java.util.List;

public class TitlesListFragment extends Fragment {

//    int position;
    List<Title> titles;
    public TitlesListFragment(List<Title> titles){
        this.titles = titles;
    }

//    public TitlesListFragment(int position) {
//        this.position = position;
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = getView().findViewById(R.id.titlesRecycler);
        recyclerView.setAdapter(new TitlesListAdapter(titles));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_titles_list, container, false);
    }
}