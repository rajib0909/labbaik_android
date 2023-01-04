package com.labbaiik.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityLoginBinding;
import com.labbaiik.app.view.activity.SignUpSelectionDialog;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
//
//        Window window = getWindow();
//        WindowManager.LayoutParams winParams = window.getAttributes();
//        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        window.setAttributes(winParams);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

//        Glide.with(this)
//                .load(R.drawable.signin_draw)
//                .into(loginBinding.loginDrawable);



        loginBinding.btnSignUp.setOnClickListener(l -> {
            SignUpSelectionDialog signUpSelectionDialog = new SignUpSelectionDialog();
            signUpSelectionDialog.show(getSupportFragmentManager(),
                    "ModalBottomSheet");
           // startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
        loginBinding.btnSignIn.setOnClickListener(l -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });


    }
}