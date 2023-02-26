package com.labbaiik.app.view.fragments.question;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentAskPublicQuestionBinding;
import com.labbaiik.app.view.MainActivity;


public class AskPublicQuestionFragment extends Fragment {
    FragmentAskPublicQuestionBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ask_public_question, container, false);
        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());


        binding.btnSubmitToAnswer.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_ask_question_successful);
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