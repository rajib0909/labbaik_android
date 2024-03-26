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
import android.widget.Toast;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentTermsConditionBinding;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.viewModel.ViewModel;

public class TermsConditionFragment extends Fragment {

    FragmentTermsConditionBinding binding;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_terms_condition, container, false);
        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);


        binding.loading.setVisibility(View.VISIBLE);
        viewModel.getTermsCondition();
        observeTermsCondition();



        return binding.getRoot();

    }


    private void observeTermsCondition() {
        viewModel.termsConditionResponseData.observe(
                getActivity(),
                response -> {
                    if (response.getData() != null) {
                        if (response.getData().get(0).getTerm().getDetails() != null){
                            binding.tvTermsCondition.setText(  Html.fromHtml(response.getData().get(0).getTerm().getDetails()) );
                        }

                        binding.loading.setVisibility(View.GONE);
                        viewModel.termsConditionResponseData = new MutableLiveData<>();

                    }

                }
        );
        viewModel.termsConditionResponseLoadError.observe(
                getViewLifecycleOwner(), isError -> {
                    if (isError != null) {
                        if (isError) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            binding.loading.setVisibility(View.GONE);
                        }
                        viewModel.termsConditionResponseLoadError = new MutableLiveData<>();
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