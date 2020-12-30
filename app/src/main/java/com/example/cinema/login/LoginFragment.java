package com.example.cinema.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cinema.R;
import com.example.cinema.MainActivity;
import com.example.cinema.mainContent.MainContentFragment;

public class LoginFragment extends Fragment {
    final CaptchaModel[] captchaModel = {null};
    SharedPreferences preferences;
    LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        setupNewCaptcha();
        setupLogin();
    }

    private void setupLogin() {
        Button loginButton = getView().findViewById(R.id.signInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest loginRequest = getLoginInformation();
                login(loginRequest);

            }
        });
    }

    private LoginRequest getLoginInformation() {
        EditText editTextEmail = getView().findViewById(R.id.editTextEmail);
        EditText editTextPass = getView().findViewById(R.id.editTextPass);
        EditText editTextCaptcha = getView().findViewById(R.id.captcha);
        String email = editTextEmail.getText().toString();
        String password = editTextPass.getText().toString();
        String captcha = editTextCaptcha.getText().toString();
        return new LoginRequest(email,password,captcha, captchaModel[0].session);
    }

    private void login(LoginRequest loginRequest) {
        loginViewModel.login(loginRequest).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String token) {
                preferences.edit().putString("token", token).commit();
                startMainActivity();
            }
        });

    }

    private void setupNewCaptcha() {
        Button retryButton = getView().findViewById(R.id.retry);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getCaptcha();
            }
        });
    }

    private void startMainActivity() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentView, new MainContentFragment());
        ft.commit();
    }

    private void getCaptcha() {
        loginViewModel.getCaptcha().observe(this, new Observer<CaptchaModel>() {
            @Override
            public void onChanged(CaptchaModel captchaModel) {
                LoginFragment.this.captchaModel[0] = captchaModel;
                byte[] decodedString = Base64.decode(captchaModel.captcha, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ImageView image = getView().findViewById(R.id.imageView);
                image.setImageBitmap(decodedByte);
            }
        });
    }

}