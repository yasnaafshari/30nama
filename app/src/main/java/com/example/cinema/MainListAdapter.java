package com.example.cinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.titleslist.TitlesListAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    HomePageModel homePageModel;
    public MainListAdapter(HomePageModel mHomePageModel){
         this.homePageModel = mHomePageModel;
    }

    @Override
    public int getItemViewType(int position) {
        if(position<3){
            return 25;
        }
        else{
            return 50;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType ==25) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View titleView = inflater.inflate(R.layout.item_list_titles, parent, false);
            return new MainListViewHolder(titleView);
        }
        else{
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View titleView = inflater.inflate(R.layout.item_suggestion, parent, false);
            return new SuggestionViewHolder(titleView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < 3) {
            MainListViewHolder listViewHolder = (MainListViewHolder) holder;
            listViewHolder.titlesRecycler.setLayoutManager(new LinearLayoutManager(listViewHolder.titlesRecycler.getContext(), LinearLayoutManager.HORIZONTAL, false));
            List<Title> titles;
            switch (position) {
                case 0:
                    titles = homePageModel.featured;
                    break;
                case 1:
                    titles = homePageModel.movies;
                    break;
                case 2:
                    titles = homePageModel.tvShows;
                    break;
                default:
                    titles = homePageModel.featured;
            }
            TitlesListAdapter titlesListAdapter = new TitlesListAdapter(titles);
            listViewHolder.titlesRecycler.setAdapter(titlesListAdapter);
        }
        else {
            SuggestionViewHolder suggestionViewHolder = (SuggestionViewHolder) holder;
            suggestionViewHolder.yTextView.setText(homePageModel.suggestionTitle.title);
            Picasso.get().load(homePageModel.suggestionTitle.imageUrl).into(suggestionViewHolder.yImageView);

        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
