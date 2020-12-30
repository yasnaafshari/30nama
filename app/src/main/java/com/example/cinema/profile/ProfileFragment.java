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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinema.R;
import com.example.cinema.core.BaseFragment;
import com.example.cinema.profileLists.ProfileListsFragment;

public class ProfileFragment extends BaseFragment {
    ProfileViewModel viewModel;

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


        viewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        setUpProfile();
        viewModel.getErrorMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showError(s);
            }
        });

    }

    private void setUpProfile() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String token = preferences.getString("token", null);
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

    @Override
    public void onRetryClicked() {
        setUpProfile();
    }
}
