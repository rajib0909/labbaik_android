package com.labbaiik.app.view.activity;

import static com.labbaiik.app.utill.SharedPrefUtil.getBooleanSetting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityIntroBinding;
import com.labbaiik.app.view.LoginActivity;
import com.labbaiik.app.view.MainActivity;
import com.labbaiik.app.view.activity.introFragment.IntroOneFragment;
import com.labbaiik.app.view.activity.introFragment.IntroThreeFragment;
import com.labbaiik.app.view.activity.introFragment.IntroTwoFragment;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding binding;
    Animation btnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getBooleanSetting(this, "isLoggedIn", false)) {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        binding.welcomeViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        binding.dotLayout.setupWithViewPager(binding.welcomeViewPager, true);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                binding.welcomeViewPager.setCurrentItem(1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        binding.welcomeViewPager.setCurrentItem(2);
                    }
                }, 2000);
            }
        }, 2000);

        binding.btnGetStarted.setOnClickListener(l->{
            startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            finish();
        });

        binding.btnSignIn.setOnClickListener(l->{
            startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            finish();
        });

        binding.ifta.setVisibility(View.VISIBLE);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.centertoup);
        binding.ifta.setAnimation(btnAnimation);

    }



    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new IntroOneFragment();
                case 1:
                    return new IntroTwoFragment();
                case 2:
                    return new IntroThreeFragment();

                default:
                    break;
            }
            return null;
        }


        @Override
        public int getCount() {
            return 3;
        }
    }
}