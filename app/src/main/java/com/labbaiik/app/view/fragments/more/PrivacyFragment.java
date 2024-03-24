package com.labbaiik.app.view.fragments.more;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentPrivacyBinding;
import com.labbaiik.app.model.questionCategory.Category;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.viewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class PrivacyFragment extends Fragment {

    FragmentPrivacyBinding binding;
    private ViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_privacy, container, false);
        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);


        binding.loading.setVisibility(View.VISIBLE);
        viewModel.getPrivacy();
        observePrivacy();



        return binding.getRoot();
    }

    private void observePrivacy() {
        viewModel.privacyResponseMutableLiveData.observe(
                getActivity(),
                privacyResponse -> {
                    if (privacyResponse.getData() != null) {
                        if (privacyResponse.getData().get(0).getPolicy().getDetails() != null){
                            binding.tvPrivacy.setText(  Html.fromHtml(privacyResponse.getData().get(0).getPolicy().getDetails()) );
                        }


                        binding.loading.setVisibility(View.GONE);
                        viewModel.privacyResponseMutableLiveData = new MutableLiveData<>();

                    }

                }
        );
        viewModel.privacyResponseLoadError.observe(
                getViewLifecycleOwner(), isError -> {
                    if (isError != null) {
                        if (isError) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            binding.loading.setVisibility(View.GONE);
                        }
                        viewModel.privacyResponseLoadError = new MutableLiveData<>();
                    }
                }
        );

//        viewModel.noInternet.observe(
//                getViewLifecycleOwner(), isError -> {
//                    if (isError != null) {
//                        if (isError) {
//                            Glide.with(getContext()).load(R.drawable.no_internet_1).into(homeBinding.noInternet);
//                            homeBinding.loading.setVisibility(View.GONE);
//                            homeBinding.noInternet.setVisibility(View.VISIBLE);
//                            homeBinding.photoList.setVisibility(View.VISIBLE);
//                        }
//                        viewModel.noInternet = new MutableLiveData<>();
//                    }
//                }
//        );

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