package com.example.cinema.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinema.R;
import com.example.cinema.core.DataCallBack;
import com.example.cinema.login.LoginActivity;
import com.example.cinema.profileLists.ProfileListsFragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button watchList = getView().findViewById(R.id.watchList);
        Button downloadList = getView().findViewById(R.id.downloadList);
        Button favourites = getView().findViewById(R.id.favourites);
        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceLists(ProfileListsFragment.FAVOURITES);
            }
        });
        downloadList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceLists(ProfileListsFragment.DOWNLOADS);
            }
        });
        watchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceLists(ProfileListsFragment.WATCH_LIST);
            }
        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String token = preferences.getString("token", null);


        ProfileViewModel viewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        viewModel.getProfile(token).observe(getViewLifecycleOwner(), new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {

                TextView username = getView().findViewById(R.id.username);
                username.setText(profileModel.username);
                TextView subscriptionStatus = getView().findViewById(R.id.subscriptionStatus);
                subscriptionStatus.setText(profileModel.subscriptionStatus);
            }
        });


    }

    void replaceLists(String listName) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentView, ProfileListsFragment.newInstance(listName)).addToBackStack(listName);
        ft.commit();
    }
}
