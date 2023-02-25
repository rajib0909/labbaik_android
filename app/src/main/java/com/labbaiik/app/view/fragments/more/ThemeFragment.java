package com.labbaiik.app.view.fragments.more;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentThemeBinding;

public class ThemeFragment extends Fragment {
    FragmentThemeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_theme, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO){
            binding.radioThemeLight.setChecked(true);
        }else {
            binding.radioThemeDark.setChecked(true);
        }
//        switch (currentNightMode) {
//            case Configuration.UI_MODE_NIGHT_NO:
//                binding.radioThemeLight.setChecked(true);
//                // Night mode is not active, we're in day time
//            case Configuration.UI_MODE_NIGHT_YES:
//                binding.radioThemeDark.setChecked(true);
//                // Night mode is active, we're at night!
//            case Configuration.UI_MODE_NIGHT_UNDEFINED:
//                // We don't know what mode we're in, assume notnight
//        }


        binding.themeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.radioThemeLight.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });


        return binding.getRoot();
    }
}