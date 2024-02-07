package com.labbaiik.app.view.fragments.question;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.labbaiik.app.R;
import com.labbaiik.app.adapter.ShowDuaListAdapter;
import com.labbaiik.app.adapter.ShowPrimaryQuestionListAdapter;
import com.labbaiik.app.databinding.FragmentQuestionsBinding;
import com.labbaiik.app.view.LoginActivity;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.view.SplashActivity;
import com.labbaiik.app.viewModel.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuestionsFragment extends Fragment {

    FragmentQuestionsBinding questionsBinding;
    private ShowPrimaryQuestionListAdapter adapter;
    private boolean askQuestion = false;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        questionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container, false);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

//        questionsBinding.btnLogin.setOnClickListener(l->startActivity( new Intent(getContext(), LoginActivity.class)));
//
//
//
//        questionsBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (questionsBinding.radioQuestions.isChecked()){
//                    questionsBinding.questionsLayout.setVisibility(View.VISIBLE);
//                    questionsBinding.videosLayout.setVisibility(View.GONE);
//                }else {
//                    questionsBinding.questionsLayout.setVisibility(View.GONE);
//                    questionsBinding.videosLayout.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        viewModel.getAllQuestion();
        observerAllQuestionsViewModel();
        questionsBinding.loading.setVisibility(View.VISIBLE);

        adapter = new ShowPrimaryQuestionListAdapter(new ArrayList<>(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        questionsBinding.questionList.setLayoutManager(layoutManager);

        questionsBinding.btnPrivateQuestion.setVisibility(View.GONE);
        questionsBinding.btnPublicQuestion.setVisibility(View.GONE);

        questionsBinding.btnAskQuestion.setOnClickListener(l -> {
            if (!askQuestion) {
                questionsBinding.btnPrivateQuestion.setVisibility(View.VISIBLE);
                questionsBinding.btnPublicQuestion.setVisibility(View.VISIBLE);
                questionsBinding.tvAskQuestion.setVisibility(View.INVISIBLE);
                askQuestion = true;
                questionsBinding.btnAskQuestion.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.color_red_day_night)));
                questionsBinding.btnAskQuestion.setRotation(45);
            } else {
                questionsBinding.btnPrivateQuestion.setVisibility(View.GONE);
                questionsBinding.btnPublicQuestion.setVisibility(View.GONE);
                questionsBinding.tvAskQuestion.setVisibility(View.VISIBLE);
                questionsBinding.btnAskQuestion.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.theme_color)));
                questionsBinding.btnAskQuestion.setRotation(0);
                askQuestion = false;
            }

        });

        adapter.setOnClickQuestion(new ShowPrimaryQuestionListAdapter.OnClickQuestionList() {
            @Override
            public void onClickQuestion(String data) {

            }

            @Override
            public void onClickReportToMufti(String data) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navigation_report);
            }
        });

        questionsBinding.btnPrivateQuestion.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_ask_private_question);
        });
        questionsBinding.btnPublicQuestion.setOnClickListener(l -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.navigation_ask_public_question);
        });


        return questionsBinding.getRoot();
    }

    private void observerAllQuestionsViewModel() {
        viewModel.fetchAllQuestionMutableLiveData.observe(
                getActivity(),
                allQuestion -> {
                    //isLoad = true;
                    if (allQuestion != null) {
                        if (allQuestion.getData().get(0).getSuccess()) {
                            questionsBinding.questionList.setAdapter(adapter);
                            adapter.updateDuaList(allQuestion.getData().get(0).getQuestion());
                            adapter.notifyDataSetChanged();
                            questionsBinding.loading.setVisibility(View.GONE);
                        }

                        viewModel.fetchAllQuestionMutableLiveData = new MutableLiveData<>();

                    }

                }
        );
        viewModel.fetchAllQuestionLoadError.observe(
                getViewLifecycleOwner(), isError -> {
                    if (isError != null) {
                        if (isError) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            questionsBinding.loading.setVisibility(View.GONE);
                        }
                        viewModel.fetchAllQuestionLoadError = new MutableLiveData<>();
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
}