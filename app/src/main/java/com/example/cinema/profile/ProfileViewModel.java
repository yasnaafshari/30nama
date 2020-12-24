package com.example.cinema.profile;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinema.R;
import com.example.cinema.core.DataCallBack;

public class ProfileViewModel extends ViewModel {
    private ProfileRepository yProfileRepository = new ProfileRepository();
    private MutableLiveData<ProfileModel> mProfileModelMutableLiveData = new MutableLiveData<>();

    public LiveData<ProfileModel> getProfile(String token) {
        if (mProfileModelMutableLiveData.getValue() == null) {
            yProfileRepository.getProfile(token, new DataCallBack<ProfileModel>() {
                @Override
                public void onSuccess(ProfileModel profileModel) {
                    mProfileModelMutableLiveData.setValue(profileModel);

                }

                @Override
                public void onFailure(String onFailureNote) {

                }
            });
        }
        return mProfileModelMutableLiveData;

    }

}
