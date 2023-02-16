package com.labbaiik.app.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentDuaDetailsBinding;

public class DuaDetailsFragment extends Fragment {

    FragmentDuaDetailsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dua_details, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());

        binding.toolbarTxt.setText("ইমান নবায়নের দু‘আ");


        return binding.getRoot();
    }
}