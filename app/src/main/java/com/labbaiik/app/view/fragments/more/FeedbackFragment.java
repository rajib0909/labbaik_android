package com.labbaiik.app.view.fragments.more;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentFeedbackBinding;
import com.labbaiik.app.view.MainActivity;


public class FeedbackFragment extends Fragment {
    FragmentFeedbackBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback, container, false);
        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());
        binding.btnCancel.setOnClickListener(l -> getActivity().onBackPressed());

        String[] feedbackArray = {"Feature requests", " Bug reports", "Questions", "Reviews on public sites", "Praise"};
        ArrayAdapter<String> feedbackAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, feedbackArray);

        binding.spinnerGender.setAdapter(feedbackAdapter);


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




