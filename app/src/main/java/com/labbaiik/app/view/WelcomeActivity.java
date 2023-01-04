package com.labbaiik.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomeBinding welcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
//        Window window = getWindow();
//        WindowManager.LayoutParams winParams = window.getAttributes();
//        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        window.setAttributes(winParams);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        String userType = getIntent().getStringExtra("UserType");
        if (userType!= null)
//            if (userType.equalsIgnoreCase("mufti")){
//                welcomeBinding.welcomeText.setText(getText(R.string.welcome_text_mufti));
//                welcomeBinding.tvUserName.setText("Mufti Ahsan Ullah");
//            }


        welcomeBinding.btnGoHome.setOnClickListener(l->{
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finishAffinity();
        });

    }
}