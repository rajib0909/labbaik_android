package com.labbaiik.app.view.fragments.userProfile;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    private boolean isEditClick = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        binding.btnCorrectionReq.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_correction_request);
        });

        binding.btnPublicQuestionAnswer.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_answer_fragment);
        });

        binding.btnPrivateQuestionAnswer.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_answer_fragment);
        });

        binding.btnPublicPendingQuestion.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_pending_question);
        });

        binding.btnPrivatePendingQuestion.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_pending_question);
        });

        binding.btnAllPublicQuestion.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_all_question);
        });

        binding.btnAllPrivateQuestion.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_all_question);
        });

        binding.btnEdit.setOnClickListener(l->{
            if (!isEditClick){
                binding.tvName.setVisibility(View.GONE);
                binding.tvEmail.setVisibility(View.GONE);
                binding.tvGender.setVisibility(View.GONE);
                binding.etName.setVisibility(View.VISIBLE);
                binding.etEmail.setVisibility(View.VISIBLE);
                binding.etGender.setVisibility(View.VISIBLE);
                isEditClick= true;
            }else {
                binding.tvName.setVisibility(View.VISIBLE);
                binding.tvEmail.setVisibility(View.VISIBLE);
                binding.tvGender.setVisibility(View.VISIBLE);
                binding.etName.setVisibility(View.GONE);
                binding.etEmail.setVisibility(View.GONE);
                binding.etGender.setVisibility(View.GONE);
                isEditClick= false;
            }

        });

        return binding.getRoot();
    }
}