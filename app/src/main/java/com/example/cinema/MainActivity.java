package com.example.cinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cinema.R;
import com.example.cinema.homePage.HomeFragment;
import com.example.cinema.notificationList.NotificationFragment;
import com.example.cinema.profile.ProfileFragment;
import com.example.cinema.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        HomeFragment homeFragment = new HomeFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homePage: {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentView, homeFragment);
                        ft.commit();


                        break;
                    }
                    case R.id.profile: {

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        ft.replace(R.id.fragmentView, new ProfileFragment());
                        ft.commit();
                        break;
                    }
                    case R.id.search: {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentView, new SearchFragment());
                        ft.commit();
                        break;
                    }
                    case R.id.notification:{
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentView, new NotificationFragment());
                        ft.commit();
                        break;
                    }
                }

                return true;
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentView, homeFragment);
        ft.commit();


    }
}