package com.example.cinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.cinema.R;
import com.example.cinema.categories.CategoriesFragment;
import com.example.cinema.homePage.HomeFragment;
import com.example.cinema.login.LoginActivity;
import com.example.cinema.notificationList.NotificationFragment;
import com.example.cinema.profile.ProfileFragment;
import com.example.cinema.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homePage: {
                        replaceMainContentFragment(new HomeFragment());
                        break;
                    }
                    case R.id.profile: {

                        replaceMainContentFragment(new ProfileFragment());
                        break;
                    }
                    case R.id.search: {
                        replaceMainContentFragment(new SearchFragment());
                        break;
                    }
                    case R.id.notification:{
                        replaceMainContentFragment(new NotificationFragment());
                        break;
                    }
                }

                return true;
            }
        });
        replaceMainContentFragment(new HomeFragment());


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.categories:
                        replaceMainContentFragment(new CategoriesFragment());
                        break;
                    case R.id.top250:
                        replaceMainContentFragment(new CategoriesFragment());
                        break;
                    case R.id.logout:
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                       preferences.edit().putString("token", null).commit();
                       startActivity(new Intent(MainActivity.this, LoginActivity.class));
                       finish();

                }
                return false;
            }
        });
    }

    private void replaceMainContentFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentView, fragment).addToBackStack(fragment.toString());
        ft.commit();
    }
}