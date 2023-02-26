package com.labbaiik.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    static NavController navController;
    ActivityMainBinding mainBinding;
    static BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        navigationView = mainBinding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_questions, R.id.navigation_video_list, R.id.navigation_profile, R.id.navigation_more)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(mainBinding.navView, navController);


    }

    /**
     * Hide bottom Navigation
     */
    public static void hideBottomNav() {
        navigationView.setVisibility(View.GONE);
    }


    /**
     * Show bottom Navigation
     */
    public static void showBottomNav() {
        navigationView.setVisibility(View.VISIBLE);
    }
}