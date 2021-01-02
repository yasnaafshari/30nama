package com.example.cinema.homePage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinema.R;
import com.example.cinema.details.DetailsFragment;
import com.example.cinema.titleslist.TitlesListAdapter;
import com.example.cinema.titleslist.TitlesListViewHolder;

import java.util.List;

public class TitlesListFragment extends Fragment {

    List<Title> titles;
    int titleType;
    public final static int TITLE_TYPE_MOVIE = 0;
    public final static int TITLE_TYPE_TV_SHOW = 1;

    public TitlesListFragment(List<Title> titles, int titleType) {
        this.titles = titles;
        this.titleType = titleType;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = getView().findViewById(R.id.titlesRecycler);
        recyclerView.setAdapter(new TitlesListAdapter(titles, titleType, new TitlesListViewHolder.OnItemListener() {
            @Override
            public void onItemClick(String url, int titleType) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentView, DetailsFragment.newInstance(url,titleType)).addToBackStack("details fragment");
                ft.commit();
            }
        }));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_titles_list, container, false);
    }
}