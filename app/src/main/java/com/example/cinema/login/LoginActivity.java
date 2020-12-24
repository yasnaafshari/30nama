package com.example.cinema.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cinema.R;
import com.example.cinema.MainActivity;
import com.example.cinema.core.DataCallBack;

public class LoginActivity extends AppCompatActivity {
    final CaptchaModel[] captchaModel = {null};
    SharedPreferences preferences;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        if(isLoggedIn()) {
            startMainActivity();
        }
        setupNewCaptcha();
        setupLogin();

    }

    private void setupLogin() {
        Button loginButton = findViewById(R.id.signInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest loginRequest = getLoginInformation();
                login(loginRequest);

            }
        });
    }

    private LoginRequest getLoginInformation() {
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPass = findViewById(R.id.editTextPass);
        EditText editTextCaptcha = findViewById(R.id.captcha);
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
        Button retryButton = findViewById(R.id.retry);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getCaptcha();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isLoggedIn() {
        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        String token = preferences.getString("token", null);
        return token != null;
    }

    private void getCaptcha() {
        loginViewModel.getCaptcha().observe(this, new Observer<CaptchaModel>() {
            @Override
            public void onChanged(CaptchaModel captchaModel) {
                LoginActivity.this.captchaModel[0] = captchaModel;
                byte[] decodedString = Base64.decode(captchaModel.captcha, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ImageView image = findViewById(R.id.imageView);
                image.setImageBitmap(decodedByte);
            }
        });
    }

}