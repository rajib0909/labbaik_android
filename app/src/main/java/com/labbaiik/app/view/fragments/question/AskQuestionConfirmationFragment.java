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
import com.labbaiik.app.databinding.FragmentAskQuestionConfirmationBinding;

public class AskQuestionConfirmationFragment extends Fragment {
    FragmentAskQuestionConfirmationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ask_question_confirmation, container, false);
        binding.btnGoHome.setOnClickListener(l->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_questions);
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}