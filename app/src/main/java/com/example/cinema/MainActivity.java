package com.example.cinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.cinema.login.LoginFragment;
import com.example.cinema.mainContent.MainContentFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isLoggedIn()) {
            replaceCurrentFragment(new MainContentFragment());
        }
        else
            replaceCurrentFragment(new LoginFragment());
    }
    private void replaceCurrentFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentView, fragment).addToBackStack(fragment.toString());
        ft.commit();
    }
    private boolean isLoggedIn() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("token", null);
        return token != null;
    }
}