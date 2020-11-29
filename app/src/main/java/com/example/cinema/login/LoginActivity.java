package com.example.cinema.login;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.cinema.core.Network;
import com.example.cinema.homePage.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    final CaptchaModel[] captchaModel = {null};
    LoginService service = Network.retrofit.create(LoginService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        String token = preferences.getString("token", null);
        if(token != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Button retryButton = findViewById(R.id.retry);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCaptch();
            }
        });
        Button button = findViewById(R.id.signInButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPass = findViewById(R.id.editTextPass);
                EditText editTextCaptcha = findViewById(R.id.captcha);
                String email = editTextEmail.getText().toString();
                String password = editTextPass.getText().toString();
                String captcha = editTextCaptcha.getText().toString();
                LoginInformation loginInformation = new LoginInformation(email,password,captcha, captchaModel[0].session);
                service.login(loginInformation).enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                       String token = response.body().data;
                        preferences.edit().putString("token",token).commit();

                    }

                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                    }
                });

            }
        });
        getCaptch();

    }
    void getCaptch() {
        service.getCaptcha().enqueue(new Callback<CaptchaModel>() {
            @Override
            public void onResponse(Call<CaptchaModel> call, Response<CaptchaModel> response) {
                captchaModel[0] = response.body();
                byte[] decodedString = Base64.decode(captchaModel[0].captcha, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ImageView image = findViewById(R.id.imageView);
                image.setImageBitmap(decodedByte);
            }

            @Override
            public void onFailure(Call<CaptchaModel> call, Throwable t) {

            }
        });
    }
}