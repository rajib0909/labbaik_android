package com.labbaiik.app.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentCalenderBinding;
import com.labbaiik.app.databinding.FragmentConnectBinding;
import com.labbaiik.app.view.MainActivity;


public class ConnectFragment extends Fragment {
    FragmentConnectBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_connect, container, false);
        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());


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