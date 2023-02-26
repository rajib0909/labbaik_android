package com.labbaiik.app.view.fragments.more;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentDonateBinding;
import com.labbaiik.app.view.MainActivity;

public class DonateFragment extends Fragment {
    FragmentDonateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_donate, container, false);

        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());

        binding.whyDonateToLabbaiik.setOnClickListener(l -> {
            WhyDonateDialog whyDonateDialog = new WhyDonateDialog();
            whyDonateDialog.show(getActivity().getSupportFragmentManager(),
                    "ModalBottomSheet");
        });

        binding.btnBkashSelect.setVisibility(View.VISIBLE);
        binding.btnBkash.setOnClickListener(l->{
            if (binding.btnBkashSelect.getVisibility() != View.GONE)
                binding.btnBkashSelect.setVisibility(View.GONE);
            else
                binding.btnBkashSelect.setVisibility(View.VISIBLE);
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