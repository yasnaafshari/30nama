package com.example.cinema.profileLists;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinema.R;
import com.example.cinema.core.Network;
import com.example.cinema.homePage.Title;
import com.example.cinema.titleslist.TitlesListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileListsFragment extends Fragment {

    public static final String DOWNLOADS = "downloads";
    public static final String WATCH_LIST = "watchList";
    public static final String FAVOURITES = "favourites";
    private static final String KEY_LIST_NAME = "listName";

    public static ProfileListsFragment newInstance(String listName) {

        Bundle args = new Bundle();
        args.putString(KEY_LIST_NAME,listName);
        ProfileListsFragment fragment = new ProfileListsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_lists, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView listRecycler = view.findViewById(R.id.listRecycler);
        listRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ProfileListsService service = Network.retrofit.create(ProfileListsService.class);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String token = preferences.getString("token", null);
        Callback<List<Title>> listCallback = new Callback<List<Title>>() {
            @Override
            public void onResponse(Call<List<Title>> call, Response<List<Title>> response) {
                List<Title> list = response.body();
                listRecycler.setAdapter(new TitlesListAdapter(list));

            }

            @Override
            public void onFailure(Call<List<Title>> call, Throwable t) {

            }
        };
        String listName = getArguments().getString(KEY_LIST_NAME);
        switch (listName) {
            case DOWNLOADS:
                service.getDownload(token).enqueue(listCallback);
            case WATCH_LIST:
                service.getWatchList(token).enqueue(listCallback);
            case FAVOURITES:
                service.getFavourite(token).enqueue(listCallback);

        }




    }

}
