package com.labbaiik.app.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Runnable runnable;
    int i;
    final Handler handler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        binding.btnPrayerTime.setOnClickListener(l->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_prayer_time);
        });

        binding.btnDua.setOnClickListener(l->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_dua);
        });


        timer();
        return binding.getRoot();
    }

    public void timer() {
        i = 100;
        binding.countProgress.setProgress(100);

        runnable = new Runnable() {
            @Override
            public void run() {
                if (i >= 0) {
                    binding.countNumber.setText(String.valueOf(i));
                    binding.countProgress.setProgress(i);
                    i--;
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }
}