package com.labbaiik.app.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.FragmentQuestionsBinding;
import com.labbaiik.app.view.LoginActivity;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.view.SplashActivity;


public class QuestionsFragment extends Fragment {

    FragmentQuestionsBinding questionsBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        questionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_questions, container, false);

        questionsBinding.btnLogin.setOnClickListener(l->startActivity( new Intent(getContext(), LoginActivity.class)));



        questionsBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (questionsBinding.radioQuestions.isChecked()){
                    questionsBinding.questionsLayout.setVisibility(View.VISIBLE);
                    questionsBinding.videosLayout.setVisibility(View.GONE);
                }else {
                    questionsBinding.questionsLayout.setVisibility(View.GONE);
                    questionsBinding.videosLayout.setVisibility(View.VISIBLE);
                }
            }
        });



        return questionsBinding.getRoot();
    }
}