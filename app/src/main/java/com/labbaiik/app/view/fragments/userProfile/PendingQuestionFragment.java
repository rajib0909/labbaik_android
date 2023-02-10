package com.labbaiik.app.view.fragments.userProfile;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentPendingQuestionBinding;


public class PendingQuestionFragment extends Fragment {
    FragmentPendingQuestionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pending_question, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());

        return binding.getRoot();
    }
}