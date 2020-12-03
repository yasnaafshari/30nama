package com.example.cinema.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.cinema.R;

public class ProfileFragment extends Fragment {
    ProfileRepository yProfileRepository = new ProfileRepository();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yProfileRepository.getProfile("token", new ProfileRepository.ProfileCallback() {
            @Override
            public void onSuccess(ProfileModel profileModel) {
                TextView username = getView().findViewById(R.id.username);
                username.setText(profileModel.username);
                TextView subscriptionStatus = getView().findViewById(R.id.subscriptionStatus);
                subscriptionStatus.setText(profileModel.subscriptionStatus);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
