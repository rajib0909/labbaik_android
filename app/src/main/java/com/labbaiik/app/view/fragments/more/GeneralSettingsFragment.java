package com.labbaiik.app.view.fragments.more;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentGeneralSettingsBinding;
import com.labbaiik.app.view.MainActivity;

public class GeneralSettingsFragment extends Fragment {
    FragmentGeneralSettingsBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_general_settings, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());

        binding.btnThemeSettings.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_theme);
        });

        binding.btnLocationSettings.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_location);
        });

        binding.btnChangeLanguage.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_change_language);
        });

        binding.btnFeedback.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_feedback);
        });

        binding.btnDonate.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_donate);
        });


        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity.hideBottomNav();
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity.showBottomNav();
    }
}