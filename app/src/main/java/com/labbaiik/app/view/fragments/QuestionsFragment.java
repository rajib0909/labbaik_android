package com.labbaiik.app.view.fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.labbaiik.app.R;
import com.labbaiik.app.adapter.ShowDuaListAdapter;
import com.labbaiik.app.adapter.ShowPrimaryQuestionListAdapter;
import com.labbaiik.app.databinding.FragmentQuestionsBinding;
import com.labbaiik.app.view.LoginActivity;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.view.SplashActivity;

import java.util.ArrayList;
import java.util.List;


public class QuestionsFragment extends Fragment {

    FragmentQuestionsBinding questionsBinding;
    private ShowPrimaryQuestionListAdapter adapter;
    private List<String> questionArray;
    private boolean askQuestion= false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        questionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_questions, container, false);

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

        questionArray = new ArrayList<>();
        questionArray.add("সেভকৃত দু‘আ");
        questionArray.add("ইমান ও আকাইদ");
        questionArray.add("পোষাক-পরিচ্ছদ");
        questionArray.add("খাবার-দাবার");
        questionArray.add("খাবার-দাবার");
        questionArray.add("খাবার-দাবার");
        questionArray.add("খাবার-দাবার");
        questionArray.add("খাবার-দাবার");
        questionArray.add("সেভকৃত দু‘আ");
        questionArray.add("ইমান ও আকাইদ");
        questionArray.add("পোষাক-পরিচ্ছদ");
        questionArray.add("খাবার-দাবার");

        adapter = new ShowPrimaryQuestionListAdapter(new ArrayList<>(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        questionsBinding.questionList.setLayoutManager(layoutManager);
        questionsBinding.questionList.setAdapter(adapter);
        adapter.updateDuaList(questionArray);
        adapter.notifyDataSetChanged();

        questionsBinding.btnPrivateQuestion.setVisibility(View.GONE);
        questionsBinding.btnPublicQuestion.setVisibility(View.GONE);

        questionsBinding.btnAskQuestion.setOnClickListener(l->{
            if (!askQuestion){
                questionsBinding.btnPrivateQuestion.setVisibility(View.VISIBLE);
                questionsBinding.btnPublicQuestion.setVisibility(View.VISIBLE);
                questionsBinding.tvAskQuestion.setVisibility(View.INVISIBLE);
                askQuestion = true;
                questionsBinding.btnAskQuestion.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.color_red_day_night)));
                questionsBinding.btnAskQuestion.setRotation(45);
            }else {
                questionsBinding.btnPrivateQuestion.setVisibility(View.GONE);
                questionsBinding.btnPublicQuestion.setVisibility(View.GONE);
                questionsBinding.tvAskQuestion.setVisibility(View.VISIBLE);
                questionsBinding.btnAskQuestion.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.theme_color)));
                questionsBinding.btnAskQuestion.setRotation(0);
                askQuestion = false;
            }

        });


        return questionsBinding.getRoot();
    }
}