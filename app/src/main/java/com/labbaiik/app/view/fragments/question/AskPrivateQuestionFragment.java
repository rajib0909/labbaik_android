package com.labbaiik.app.view.fragments.question;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentAskPrivateQuestionBinding;
import com.labbaiik.app.model.questionCategory.Category;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.viewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class AskPrivateQuestionFragment extends Fragment {
    FragmentAskPrivateQuestionBinding binding;
    private ViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ask_private_question, container, false);
        binding.btnBack.setOnClickListener(l -> getActivity().onBackPressed());
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);


        binding.btnSubmitToAnswer.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_ask_question_successful);
        });
        binding.btnDonate.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_donate);
        });

        binding.loading.setVisibility(View.VISIBLE);
        viewModel.allQuestionCategory();
        observeAllQuestionCategory();

        return binding.getRoot();
    }


    private void observeAllQuestionCategory() {
        viewModel.questionCategory.observe(
                getActivity(),
                categoryResponse -> {
                    if (categoryResponse.getData() != null) {

                        List<String> categoryList = new ArrayList<>();
                        if (categoryResponse.getData().get(0).getCategory() != null) {
                            for (Category category : categoryResponse.getData().get(0).getCategory()) {
                                categoryList.add(category.getCatName());
                            }
                            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, categoryList);
                            binding.spinnerCategory.setAdapter(categoryAdapter);
                        }


                        binding.loading.setVisibility(View.GONE);
                        viewModel.questionCategory = new MutableLiveData<>();

                    }

                }
        );
        viewModel.questionCategoryLoadError.observe(
                getViewLifecycleOwner(), isError -> {
                    if (isError != null) {
                        if (isError) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            binding.loading.setVisibility(View.GONE);
                        }
                        viewModel.questionCategoryLoadError = new MutableLiveData<>();
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