package com.labbaiik.app.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityMuftiImageSetBinding;
import com.labbaiik.app.view.SignUpMuftiActivity;

public class MuftiImageSetActivity extends AppCompatActivity {

    ActivityMuftiImageSetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mufti_image_set);

        binding.btnSave.setOnClickListener(l->{
            startActivity(new Intent(MuftiImageSetActivity.this, MuftiWelcomeActivity.class));
            finishAffinity();
        });
    }
}