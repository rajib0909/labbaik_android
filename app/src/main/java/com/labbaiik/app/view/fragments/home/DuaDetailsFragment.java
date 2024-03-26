package com.labbaiik.app.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentDuaDetailsBinding;
import com.labbaiik.app.model.duaList.Dua;
import com.labbaiik.app.view.MainActivity;

public class DuaDetailsFragment extends Fragment {

    FragmentDuaDetailsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dua_details, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());
        String duaString = getArguments().getString("dua");

        Log.d("Tanvir >> dua >> ", duaString);

        // Convert JSON string to object using GSON
        Gson gson = new Gson();
        Dua dua = gson.fromJson(duaString , Dua.class);

        binding.toolbarTxt.setText("দু‘আ");
        binding.duaTitle.setText(dua.getTitle());
        binding.duaArbi.setText(dua.getArabic());
        binding.duaBangla.setText(Html.fromHtml(dua.getBanglaMeaning()));



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