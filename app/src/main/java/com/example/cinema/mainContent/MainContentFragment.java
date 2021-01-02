package com.example.cinema.mainContent;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.cinema.R;
import com.example.cinema.categories.CategoriesFragment;
import com.example.cinema.homePage.HomeFragment;
import com.example.cinema.login.LoginFragment;
import com.example.cinema.news.NewsFragment;
import com.example.cinema.notificationList.NotificationFragment;
import com.example.cinema.profileLists.ProfileListsFragment;
import com.example.cinema.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DrawerLayout drawerLayout = getView().findViewById(R.id.drawer);


        BottomNavigationView bottomNavigationView = getView().findViewById(R.id.bottomNavigationBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homePage: {
                        replaceCurrentFragment(new HomeFragment());
                        break;
                    }
                    case R.id.search: {
                        replaceCurrentFragment(new SearchFragment());
                        break;
                    }
                    case R.id.notification: {
                        replaceCurrentFragment(new NotificationFragment());
                        break;
                    }
                }
                return true;
            }
        });
        replaceCurrentFragment(new HomeFragment());


        NavigationView navigationView = getView().findViewById(R.id.nav_view);
        TextView username = navigationView.getHeaderView(0).findViewById(R.id.username);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userName = sharedPreferences.getString("username", "Yasna Afshari");
        username.setText(userName);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.categories:
                        replaceCurrentFragment(new CategoriesFragment());
                        break;
                    case R.id.top250:
                        replaceCurrentFragment(new CategoriesFragment());
                        break;
                    case R.id.logout:
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        preferences.edit().putString("token", null).commit();
                        replaceMainFragment(new LoginFragment());
                        break;
                    case R.id.news:
                        replaceCurrentFragment(new NewsFragment());
                        break;
                    case R.id.downloadList:
                        replaceCurrentFragment(ProfileListsFragment.newInstance(ProfileListsFragment.DOWNLOADS));
                        break;
                    case R.id.watchList:
                        replaceCurrentFragment(ProfileListsFragment.newInstance(ProfileListsFragment.WATCH_LIST));
                        break;
                    case R.id.favouritesList:
                        replaceCurrentFragment(ProfileListsFragment.newInstance(ProfileListsFragment.FAVOURITES));
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }


    private void replaceCurrentFragment(Fragment fragment) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragmentView, fragment).addToBackStack(fragment.toString());
        ft.commit();
    }

    private void replaceMainFragment(Fragment fragment) {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentView, fragment).addToBackStack(fragment.toString());
        ft.commit();
    }
}

