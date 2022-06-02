package com.labbaiik.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding signUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);


        Window window = getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(winParams);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        signUpBinding.btnUser.setOnClickListener(l -> {
           startActivity(new Intent(SignUpActivity.this, SignUpUserActivity.class));
        });

        signUpBinding.btnMufti.setOnClickListener(l -> {
           startActivity(new Intent(SignUpActivity.this, SignUpMuftiActivity.class));
        });

        signUpBinding.btnSignIn.setOnClickListener(l -> {
            finish();
        });



    }
}