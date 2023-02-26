package com.labbaiik.app.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.adapter.ShowDuaListAdapter;
import com.labbaiik.app.databinding.FragmentDuaBinding;
import com.labbaiik.app.view.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class DuaFragment extends Fragment {
    FragmentDuaBinding binding;
    private ShowDuaListAdapter adapter;
    private List<String> duaArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dua, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());

        duaArray = new ArrayList<>();
        duaArray.add("সেভকৃত দু‘আ");
        duaArray.add("ইমান ও আকাইদ");
        duaArray.add("পোষাক-পরিচ্ছদ");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");
        duaArray.add("খাবার-দাবার");

        adapter = new ShowDuaListAdapter(new ArrayList<>(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.duaList.setLayoutManager(layoutManager);
        binding.duaList.setAdapter(adapter);
        adapter.updateDuaList(duaArray);
        adapter.notifyDataSetChanged();

        binding.btnDuaDetails.setOnClickListener(l->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_dua_details);
        });

        binding.btnDuaDetails1.setOnClickListener(l->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_dua_details);
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